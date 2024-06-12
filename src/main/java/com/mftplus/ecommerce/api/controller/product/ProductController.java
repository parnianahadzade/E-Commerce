package com.mftplus.ecommerce.api.controller.product;

import com.mftplus.ecommerce.model.entity.Product;
import com.mftplus.ecommerce.service.impl.ProductServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> findProducts(@RequestParam(value = "categoryName", required = false) String categoryName){
        if (categoryName == null) {
            return productService.findProducts();
        }
        return productService.findByCategoriesName(categoryName);
    }

}
