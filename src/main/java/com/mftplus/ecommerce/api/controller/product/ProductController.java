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
    public List<Product> findProducts(@RequestParam(value = "categoryName", required = false) String categoryName,
                                      @RequestParam(value = "brandName", required = false) String brandName){
        if (categoryName != null && brandName != null) {
            return productService.findByBrandNameAndCategoriesName(brandName, categoryName);
        }else if (categoryName != null) {
            return productService.findByCategoriesName(categoryName);
        }else if (brandName != null) {
            return productService.findByBrandName(brandName);
        }

        return productService.findProducts();
    }

}
