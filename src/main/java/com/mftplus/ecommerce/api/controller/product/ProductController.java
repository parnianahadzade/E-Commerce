package com.mftplus.ecommerce.api.controller.product;

import com.mftplus.ecommerce.model.entity.Product;
import com.mftplus.ecommerce.service.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findProducts(){
        return productService.findProducts();
    }
}
