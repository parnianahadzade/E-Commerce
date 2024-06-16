package com.mftplus.ecommerce.api.controller.product;

import com.mftplus.ecommerce.api.dto.InventoryBody;
import com.mftplus.ecommerce.api.dto.ProductBody;
import com.mftplus.ecommerce.api.dto.SearchRequest;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.*;
import com.mftplus.ecommerce.service.impl.*;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductServiceImpl productService;

    private final CategoryServiceImpl categoryService;

    private final BrandServiceImpl brandService;

    private final InventoryServiceImpl inventoryService;

    private final ColorServiceImpl colorService;

    public ProductController(ProductServiceImpl productService, CategoryServiceImpl categoryService, BrandServiceImpl brandService, InventoryServiceImpl inventoryService, ColorServiceImpl colorService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.inventoryService = inventoryService;
        this.colorService = colorService;
    }

    @GetMapping
    public List<Product> findProducts
            (@RequestParam(value = "category", required = false) List<String> categoryNames,
             @RequestParam(value = "brand", required = false) String brandName,
             @RequestParam(value = "name", required = false) String name,
             @RequestParam(value = "color", required = false) String color,
             @RequestParam(value = "minPrice", required = false) Integer minPrice,
             @RequestParam(value = "maxPrice", required = false) Integer maxPrice,
             @RequestParam(value = "minOffPercent", required = false) Integer minOffPercent){

        SearchRequest request = new SearchRequest();
        request.setName(name);
        request.setCategoryNames(categoryNames);
        request.setBrandName(brandName);
        request.setMinPrice(minPrice);
        request.setMaxPrice(maxPrice);
        request.setMinOffPercent(minOffPercent);
        return productService.findAllByCriteria(request);

    }


    //todo : needs re check for efficiency
    @Transactional
    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody ProductBody body) throws NoContentException {
        Product product = new Product();

        product.setName(body.getProductName());
        product.setShortDescription(body.getShortDescription());
        product.setLongDescription(body.getLongDescription());

        List<Long> categoryIds = body.getCategoryIds();
        List<Category> categories = new ArrayList<>();
        for (Long categoryId : categoryIds) {
            Category category = categoryService.findById(categoryId);
            categories.add(category);

            product.setCategories(categories);
        }

        Brand brand = brandService.findById(body.getBrandId());
        product.setBrand(brand);

        Product product1 = productService.save(product);

        List<InventoryBody> inventoryBodies = body.getInventoryBodies();
        List<Inventory> inventories = new ArrayList<>();
        for (InventoryBody inventoryBody : inventoryBodies) {
            Color color = colorService.findById(inventoryBody.getColorId());

            Inventory inventory = new Inventory();

            inventory.setColor(color);
            inventory.setQuantity(inventoryBody.getQuantity());
            inventory.setPrice(inventoryBody.getPrice());
            inventory.setOffPercent(inventoryBody.getOffPercent());
            inventory.setProduct(product1);

            inventories.add(inventory);

            product.setInventories(inventories);
        }

        productService.save(product);

        return ResponseEntity.ok().build();

    }

}
