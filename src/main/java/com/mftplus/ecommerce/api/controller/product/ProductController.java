package com.mftplus.ecommerce.api.controller.product;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.api.dto.InventoryBody;
import com.mftplus.ecommerce.api.dto.ProductBody;
import com.mftplus.ecommerce.api.dto.SearchRequest;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.*;
import com.mftplus.ecommerce.model.entity.enums.Size;
import com.mftplus.ecommerce.service.impl.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
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

    @JsonView(Views.Product.class)
    @GetMapping
    public List<Product> findProducts
            (@RequestParam(value = "categoryId", required = false) Integer categoryId,
             @RequestParam(value = "brand", required = false) String brandName,
             @RequestParam(value = "name", required = false) String name,
             @RequestParam(value = "minPrice", required = false) Integer minPrice,
             @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
             @RequestParam(value = "enableOff", required = false) boolean enableOff){

        SearchRequest request = new SearchRequest();
        request.setName(name);
        request.setCategoryId(categoryId);
        request.setBrandName(brandName);
        request.setMinPrice(minPrice);
        request.setMaxPrice(maxPrice);
        request.setEnableOff(enableOff);

        return productService.findAllByCriteria(request);

    }


    //todo : needs re check for efficiency
    @Transactional
    @PostMapping(value = "/save", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Product> saveProduct(
            @Valid @RequestPart("productBody") ProductBody body,
                @RequestPart("files")MultipartFile[] files,
                    @RequestPart("mainFile")MultipartFile mainFile) throws NoContentException, IOException {

        Product product = new Product();

        product.setCode(body.getCode());
        product.setName(body.getProductName());
        product.setShortDescription(body.getShortDescription());
        product.setLongDescription(body.getLongDescription());
        product.setPrice(body.getPrice());
        product.setOffPercent(body.getOffPercent());

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
        String categoryName = body.getCategoryName();
        Category mainCategory = categoryService.findByNameAndDeletedFalse(categoryName);
        List<Category> categories = new ArrayList<>();
        for (String categoryNameOriginal : mainCategory.getCategoryPath()) {
            Category category = categoryService.findByNameAndDeletedFalse(categoryNameOriginal);
            categories.add(category);

            product.setCategories(categories);
        }

        //brand
        Brand brand = brandService.findByIdAndDeletedFalse(body.getBrandId());
        product.setBrand(brand);

        //color
        Color color = colorService.findByIdAndDeletedFalse(body.getColorId());
        product.setColor(color);


        Product product1 = productService.save(product);

        //inventory
        List<InventoryBody> inventoryBodies = body.getInventoryBodies();
        List<Inventory> inventories = new ArrayList<>();
        for (InventoryBody inventoryBody : inventoryBodies) {

            Inventory inventory = new Inventory();

            inventory.setQuantity(inventoryBody.getQuantity());
            inventory.setSize(Size.valueOf(inventoryBody.getSize()));
            inventory.setProduct(product1);

            inventories.add(inventory);

            product.setInventories(inventories);
        }

        productService.save(product);

        return ResponseEntity.ok().build();
    }

}
