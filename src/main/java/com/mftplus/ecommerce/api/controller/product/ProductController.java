package com.mftplus.ecommerce.api.controller.product;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.api.dto.*;
import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.InvalidDataException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.exception.component.ApiValidationComponent;
import com.mftplus.ecommerce.exception.dto.ApiResponse;
import com.mftplus.ecommerce.model.entity.*;
import com.mftplus.ecommerce.model.entity.enums.Size;
import com.mftplus.ecommerce.service.impl.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${apiPrefix}/product")
@Slf4j
@CrossOrigin
public class ProductController {

    private final ProductServiceImpl productService;

    private final CategoryServiceImpl categoryService;

    private final BrandServiceImpl brandService;

    private final ColorServiceImpl colorService;

    private final ImageServiceImpl imageService;

    private final ApiValidationComponent validationComponent;

    private final InventoryServiceImpl inventoryService;


    public ProductController(ProductServiceImpl productService, CategoryServiceImpl categoryService, BrandServiceImpl brandService, ColorServiceImpl colorService, ImageServiceImpl imageService, ApiValidationComponent validationComponent, InventoryServiceImpl inventoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.colorService = colorService;
        this.imageService = imageService;
        this.validationComponent = validationComponent;
        this.inventoryService = inventoryService;
    }

    //product find all by criteria
    @GetMapping
    @JsonView(Views.ProductList.class)
    public List<Product> findProducts
            (@RequestParam(value = "categoryId", required = false) Integer categoryId,
             @RequestParam(value = "brand", required = false) String brandName,
             @RequestParam(value = "name", required = false) String name,
             @RequestParam(value = "minPrice", required = false) Integer minPrice,
             @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
             @RequestParam(value = "enableOff", required = false) boolean enableOff,
             @RequestParam(value = "pageNumber", required = false)Integer pageNumber) throws InvalidDataException, NoContentException {

        if (pageNumber == null) {
            throw new InvalidDataException("شماره صفحه وارد نشده است.");
        }

        ProductSearchRequest request = new ProductSearchRequest();
        request.setName(name);
        request.setCategoryId(categoryId);
        request.setBrandName(brandName);
        request.setMinPrice(minPrice);
        request.setMaxPrice(maxPrice);
        request.setEnableOff(enableOff);
        request.setPageNumber(pageNumber);

        List<Product> products = productService.findAllByCriteria(request);

        if (products.isEmpty()) {
            throw new NoContentException("موردی یافت نشد.");
        }

        return products;

    }

    //product find by id
    @GetMapping(value = "/id/{id}")
    @JsonView(Views.singleProduct.class)
    public Product findById(@PathVariable Long id) throws NoContentException, InvalidDataException {
        return productService.findById(id);
    }

    //product find by id not and code, used for finding similar products
    @GetMapping(value = "/id/{id}/code/{code}")
    @JsonView(Views.ProductList.class)
    public List<Product> findByIdNotAndCode(@PathVariable String code, @PathVariable Long id) {
        Integer pageNumber = 1;
        Integer pageSize = 10;

        return productService.findByIdNotAndCode(id,code,pageNumber,pageSize);
    }


    //product save
    @PostMapping(value = "/admin/save")
    @Transactional(rollbackOn = {NoContentException.class, DuplicateException.class})
    public ResponseEntity<ApiResponse> saveProduct(@Valid @RequestBody ProductSaveDTO productSaveDTO,
                                      BindingResult result) throws NoContentException, DuplicateException {

        //validating inputs
        ApiResponse response = validationComponent.handleValidationErrors(result);

        if (response.getFieldErrors() != null) {
            return ResponseEntity.badRequest().body(response);
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

        //main image
        Image mainImage = imageService.findByIdAndDeletedFalse(productSaveDTO.getMainImageId());
        product.setMainImage(mainImage);

        //images
        List<Long> imageIds = productSaveDTO.getImageIds();
        List<Image> images = new ArrayList<>();
        for (Long imageId : imageIds) {
            Image image = imageService.findByIdAndDeletedFalse(imageId);
            images.add(image);
        }
        product.setImages(images);

        //category
        Long categoryId = productSaveDTO.getCategoryId();
        Category mainCategory = categoryService.findByIdAndDeletedFalse(categoryId);
        product.setMainCategory(mainCategory);
        List<Category> categories = new ArrayList<>();
        for (String categoryNameOriginal : mainCategory.getCategoryPath()) {
            Category category = categoryService.findByNameAndDeletedFalse(categoryNameOriginal);
            categories.add(category);

        }
        product.setCategories(categories);

        //brand
        Brand brand = brandService.findByIdAndDeletedFalse(productSaveDTO.getBrandId());
        product.setBrand(brand);

        //color
        Color color = colorService.findByIdAndDeletedFalse(productSaveDTO.getColorId());
        product.setColor(color);


        productService.save(product);

        //inventory
        List<InventorySaveDTO> inventorySaveDTOS = productSaveDTO.getInventories();
        for (InventorySaveDTO inventorySaveDTO : inventorySaveDTOS) {

            Inventory inventory = new Inventory();

            inventory.setQuantity(inventorySaveDTO.getQuantity());

//            Size.findByTitle(inventorySaveDTO.getSize());
            inventory.setSize(Size.valueOf(inventorySaveDTO.getSize()));


            inventory.setProduct(product);

            inventoryService.save(inventory);
        }

        response.setSuccess(true);
        response.setSuccessMessage("کالا با موفقیت ایجاد شد.");

        return ResponseEntity.ok(response);
    }

    //product update
    @PutMapping("/admin/update/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable Long productId) {
        return null;
    }

    //product logical remove
    @DeleteMapping("/admin/delete/{productId}")
    public ResponseEntity<ApiResponse> logicalRemoveProduct(@PathVariable Long productId) throws NoContentException {

        ApiResponse response = new ApiResponse();

        productService.logicalRemove(productId);

        response.setSuccess(true);
        response.setSuccessMessage("کالا با موفقیت حذف شد.");

        Map<String, Object> data = new HashMap<>();
        data.put("productId", productId);
        response.setData(data);

        return ResponseEntity.ok(response);
    }



}
