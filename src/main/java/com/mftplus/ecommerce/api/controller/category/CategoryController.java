package com.mftplus.ecommerce.api.controller.category;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.api.dto.CategorySaveDTO;
import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.exception.component.ApiValidationComponent;
import com.mftplus.ecommerce.exception.dto.ApiResponse;
import com.mftplus.ecommerce.model.entity.Category;
import com.mftplus.ecommerce.model.entity.Views;
import com.mftplus.ecommerce.service.impl.CategoryServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${apiPrefix}/category")
@Slf4j
@CrossOrigin
public class CategoryController {

    private final CategoryServiceImpl categoryService;

    private final ApiValidationComponent validationComponent;

    public CategoryController(CategoryServiceImpl categoryService, ApiValidationComponent validationComponent) {
        this.categoryService = categoryService;
        this.validationComponent = validationComponent;
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
        ApiResponse response = validationComponent.handleValidationErrors(result);

        if (response.getFieldErrors() != null) {
            return ResponseEntity.badRequest().body(response);
        }

        Category parentCategory = categoryService.findByIdAndDeletedFalse(categorySaveDTO.getParentId());

        try {
            categoryService.findByNameAndDeletedFalse(categorySaveDTO.getName());
            throw new DuplicateException("دسته بندی با این نام وجود دارد.");

        } catch (NoContentException e) {
            Category category = new Category();
            category.setName(categorySaveDTO.getName());
            category.setParentCategory(parentCategory);
            categoryService.save(category);

            response.setSuccess(true);
            response.setSuccessMessage("دستبه بندی با موفقیت ایجاد شد.");

            Map<String, Object> data = new HashMap<>();
            data.put("category", category);
            response.setData(data);
        }

        return ResponseEntity.ok(response);
    }

}
