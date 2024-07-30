package com.mftplus.ecommerce.api.controller.product;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.api.dto.*;
import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.*;
import com.mftplus.ecommerce.model.entity.enums.Size;
import com.mftplus.ecommerce.service.impl.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${apiPrefix}/product")
@CrossOrigin
public class ProductController {

    private final ProductServiceImpl productService;

    private final CategoryServiceImpl categoryService;

    private final BrandServiceImpl brandService;

    private final ColorServiceImpl colorService;

    private final ImageServiceImpl imageService;


    public ProductController(ProductServiceImpl productService, CategoryServiceImpl categoryService, BrandServiceImpl brandService, ColorServiceImpl colorService, ImageServiceImpl imageService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.colorService = colorService;
        this.imageService = imageService;
    }

    @JsonView(Views.ProductList.class)
    @GetMapping
    public List<Product> findProducts
            (@RequestParam(value = "categoryId", required = false) Integer categoryId,
             @RequestParam(value = "brand", required = false) String brandName,
             @RequestParam(value = "name", required = false) String name,
             @RequestParam(value = "minPrice", required = false) Integer minPrice,
             @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
             @RequestParam(value = "enableOff", required = false) boolean enableOff,
             @RequestParam(value = "pageNumber")int pageNumber){

        SearchRequest request = new SearchRequest();
        request.setName(name);
        request.setCategoryId(categoryId);
        request.setBrandName(brandName);
        request.setMinPrice(minPrice);
        request.setMaxPrice(maxPrice);
        request.setEnableOff(enableOff);
        request.setPageNumber(pageNumber);

        return productService.findAllByCriteria(request);

    }

    @JsonView(Views.singleProduct.class)
    @GetMapping(value = "/id/{id}")
    public Product findById(@PathVariable Long id) throws NoContentException {
        return productService.findByIdAndDeletedFalse(id);
    }

    @GetMapping(value = "/{id}/code/{code}")
    public List<Product> findByIdNotAndCode(@PathVariable String code, @PathVariable Long id) {
        return productService.findByIdNotAndCode(id,code);
    }


    //todo : needs re check for efficiency
    @Transactional(rollbackOn = {NoContentException.class, IOException.class, DuplicateException.class})
    @PostMapping(value = "/admin/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity saveProduct(
            @Valid @RequestPart("productBody") ProductSaveDTO productSaveDTO,
            BindingResult result,
            @RequestPart("files")MultipartFile[] files,
            @RequestPart("mainFile")MultipartFile mainFile
            ) throws NoContentException, IOException, DuplicateException {

        if (result.hasErrors()) {

            List<InputFieldError> fieldErrorList = result.getFieldErrors().stream()
                    .map(error -> new InputFieldError(error.getField(), error.getDefaultMessage()))
                    .collect(Collectors.toList());

            ValidationResponse validationResponse = new ValidationResponse(fieldErrorList);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationResponse);

        }

        Product product = new Product();

        productService.findByNameAndDeletedFalse(productSaveDTO.getProductName());
        product.setName(productSaveDTO.getProductName());

        product.setCode(productSaveDTO.getCode());
        product.setDescription(productSaveDTO.getDescription());
        product.setMaterial(productSaveDTO.getMaterial());
        product.setPattern(productSaveDTO.getPattern());
        product.setHeight(productSaveDTO.getHeight());
        product.setPrice(productSaveDTO.getPrice());
        product.setOffPercent(productSaveDTO.getOffPercent());

        //images
        List<Image> images = new ArrayList<>();
        for (MultipartFile file : files) {
            Image image = imageService.uploadImageToFileSystem(file);
            images.add(image);

            product.setImages(images);
        }

        //main image
        Image mainImage = imageService.uploadImageToFileSystem(mainFile);
        product.setMainImage(mainImage);

        //category
        String categoryName = productSaveDTO.getCategoryName();
        Category mainCategory = categoryService.findByNameAndDeletedFalse(categoryName);
        List<Category> categories = new ArrayList<>();
        for (String categoryNameOriginal : mainCategory.getCategoryPath()) {
            Category category = categoryService.findByNameAndDeletedFalse(categoryNameOriginal);
            categories.add(category);

            product.setCategories(categories);
        }

        //brand
        Brand brand = brandService.findByIdAndDeletedFalse(productSaveDTO.getBrandId());
        product.setBrand(brand);

        //color
        Color color = colorService.findByIdAndDeletedFalse(productSaveDTO.getColorId());
        product.setColor(color);


        Product product1 = productService.save(product);

        //inventory
        List<InventorySaveDTO> inventorySaveDTOS = productSaveDTO.getInventorySaveDTOS();
        List<Inventory> inventories = new ArrayList<>();
        for (InventorySaveDTO inventorySaveDTO : inventorySaveDTOS) {

            Inventory inventory = new Inventory();

            inventory.setQuantity(inventorySaveDTO.getQuantity());

            Size.findByTitle(inventorySaveDTO.getSize());
            inventory.setSize(Size.valueOf(inventorySaveDTO.getSize()));


            inventory.setProduct(product1);

            inventories.add(inventory);

            product.setInventories(inventories);
        }

        productService.save(product);

        return ResponseEntity.ok().build();
    }



}
