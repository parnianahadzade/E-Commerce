package com.mftplus.ecommerce.api.controller.category;

import com.mftplus.ecommerce.model.entity.Category;
import com.mftplus.ecommerce.service.impl.CategoryServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public List<Category> findCategories(@RequestParam(value = "categoryName", required = false) String categoryName){
        Optional<Category> categoryOptional = categoryService.findByName(categoryName);
        if (categoryOptional.isPresent()){
            return categoryOptional.get().getCategories();
        }
        return null;
    }

}
