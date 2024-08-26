package com.mftplus.ecommerce.api.controller.category;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.model.entity.Category;
import com.mftplus.ecommerce.model.entity.Views;
import com.mftplus.ecommerce.service.impl.CategoryServiceImpl;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${apiPrefix}/category")
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    public CategoryController(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }


    @JsonView(Views.Category.class)
    @GetMapping
    public Category findCategories() throws NoContentException {
        return categoryService.findByNameAndDeletedFalse("digikala");
    }

}
