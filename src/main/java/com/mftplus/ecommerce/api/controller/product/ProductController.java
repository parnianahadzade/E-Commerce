package com.mftplus.ecommerce.api.controller.product;

import com.mftplus.ecommerce.api.dto.SearchRequest;
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
    public List<Product> findProducts
            (@RequestParam(value = "category", required = false) List<String> categoryNames,
             @RequestParam(value = "brand", required = false) String brandName,
             @RequestParam(value = "name", required = false) String name,
             @RequestParam(value = "color", required = false) String color,
             @RequestParam(value = "price", required = false) String price,
             @RequestParam(value = "offPercent", required = false) String offPercent){

        SearchRequest request = new SearchRequest();
        request.setName(name);
        request.setCategoryNames(categoryNames);
        request.setBrandName(brandName);
        return productService.findAllByCriteria(request);

    }

}
