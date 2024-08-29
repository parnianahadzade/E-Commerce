package com.mftplus.ecommerce.api.controller.category;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.api.dto.CategorySaveDTO;
import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.exception.component.ApiExceptionComponent;
import com.mftplus.ecommerce.exception.dto.ApiExceptionResponse;
import com.mftplus.ecommerce.model.entity.Category;
import com.mftplus.ecommerce.model.entity.Views;
import com.mftplus.ecommerce.service.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @JsonView(Views.CategoryName.class)
    @GetMapping("/name")
    public List<Category> findCategoriesByNameStartsWith(@RequestParam(value = "categoryName") String categoryName) {
        return categoryService.findByNameStartsWithIgnoreCaseAndDeletedFalse(categoryName);
    }

    @PostMapping("/admin/save")
    public ResponseEntity saveCategory(@Valid @RequestBody CategorySaveDTO categorySaveDTO,
                                       BindingResult result) throws NoContentException, DuplicateException {

        //validating inputs
        ResponseEntity<ApiExceptionResponse> responseEntity = ApiExceptionComponent.handleValidationErrors(result);
        if (responseEntity != null) {
            return responseEntity;
        }

        Category parentCategory = categoryService.findByIdAndDeletedFalse(categorySaveDTO.getParentId());

        try {
            categoryService.findByNameAndDeletedFalse(categorySaveDTO.getName());
            throw new DuplicateException("category name : " + categorySaveDTO.getName() + " already exists!");

        } catch (NoContentException e) {
            Category category = new Category();
            category.setName(categorySaveDTO.getName());
            category.setParentCategory(parentCategory);
            categoryService.save(category);
        }

        return ResponseEntity.ok().build();
    }

}
