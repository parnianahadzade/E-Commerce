package com.mftplus.ecommerce.api.controller.category;

import com.mftplus.ecommerce.model.entity.Category;
import com.mftplus.ecommerce.service.impl.CategoryServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("{categoryName}")
    public List<Category> findCategoryByName(@PathVariable String categoryName){
        return categoryService.findByName(categoryName);
    }

}
