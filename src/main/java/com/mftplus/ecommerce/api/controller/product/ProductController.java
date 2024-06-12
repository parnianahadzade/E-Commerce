package com.mftplus.ecommerce.api.controller.product;

import com.mftplus.ecommerce.model.entity.Product;
import com.mftplus.ecommerce.service.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findProducts(){
        return productService.findProducts();
    }

    @GetMapping("/category/{categoryName}")
    public List<Product> findByCategoryName(@PathVariable String categoryName){
        return productService.findByCategoriesName(categoryName);
    }
}
