package com.mftplus.ecommerce.api.controller.category;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.api.dto.CategorySaveDTO;
import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.InvalidDataException;
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
import java.util.Objects;

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

    //category find by first category
    @GetMapping
    @JsonView(Views.Category.class)
    public Category findCategories() throws NoContentException {
        return categoryService.findByIdAndDeletedFalse(1L);
    }

    //category find by name
    @GetMapping("/findBy")
    @JsonView(Views.CategoryName.class)
    public List<Category> findCategoriesByNameStartsWith(@RequestParam(required = false, value = "categoryName") String categoryName) throws InvalidDataException, NoContentException {
        if (categoryName == null) {
            throw new InvalidDataException("نام دسته بندی وارد نشده است.");
        }

        List<Category> categories = categoryService.findByNameStartsWithIgnoreCaseAndDeletedFalse(categoryName);

        if (categories.isEmpty()) {
            throw new NoContentException("موردی یافت نشد.");
        }

        return categories;
    }

    //category find by id
    @GetMapping("/id/{categoryId}")
    @JsonView(Views.Category.class)
    public Category findCategoryById(@PathVariable Long categoryId) throws NoContentException {
        return categoryService.findByIdAndDeletedFalse(categoryId);
    }

    //category save
    @PostMapping("/admin/save")
    public ResponseEntity<ApiResponse> saveCategory(@Valid @RequestBody CategorySaveDTO categorySaveDTO,
                                       BindingResult result) throws NoContentException, DuplicateException {

        //validating inputs
        ApiResponse response = validationComponent.handleValidationErrors(result);

        if (response.getFieldErrors() != null) {
            return ResponseEntity.badRequest().body(response);
        }

        Category parentCategory = categoryService.findByIdAndDeletedFalse(categorySaveDTO.getParentId());

        categoryService.findByNameAndDeletedFalseWithOutReturn(categorySaveDTO.getName());

        Category category = new Category();
        category.setName(categorySaveDTO.getName());
        category.setParentCategory(parentCategory);
        categoryService.save(category);

        response.setSuccess(true);
        response.setSuccessMessage("دستبه بندی با موفقیت ایجاد شد.");

        Map<String, Object> data = new HashMap<>();
        data.put("category", category);
        response.setData(data);

        return ResponseEntity.ok(response);
    }

    //category update
    @PutMapping("/admin/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@Valid @RequestBody CategorySaveDTO categorySaveDTO,
                                         BindingResult result, @PathVariable Long categoryId) throws NoContentException, DuplicateException {

        Category category = categoryService.findByIdAndDeletedFalse(categoryId);

        //validating inputs
        ApiResponse response = validationComponent.handleValidationErrors(result);

        if (response.getFieldErrors() != null) {
            return ResponseEntity.badRequest().body(response);
        }

        Category parentCategory = categoryService.findByIdAndDeletedFalse(categorySaveDTO.getParentId());

        if (!Objects.equals(category.getName(), categorySaveDTO.getName())) {
            categoryService.findByNameAndDeletedFalseWithOutReturn(categorySaveDTO.getName());
        }

        category.setId(category.getId());
        category.setName(categorySaveDTO.getName());
        category.setParentCategory(parentCategory);
        categoryService.update(category);

        response.setSuccess(true);
        response.setSuccessMessage("دستبه بندی با موفقیت بروزرسانی شد.");

        Map<String, Object> data = new HashMap<>();
        data.put("category", category);
        response.setData(data);

        return ResponseEntity.ok(response);

    }

    //category logical remove
    @DeleteMapping("/admin/delete/{categoryId}")
    public ResponseEntity<ApiResponse> logicalRemoveCategory(@PathVariable Long categoryId) throws NoContentException {

        ApiResponse response = new ApiResponse();

        categoryService.logicalRemove(categoryId);

        response.setSuccess(true);
        response.setSuccessMessage("دسته بندی با موفقیت حذف شد.");

        Map<String, Object> data = new HashMap<>();
        data.put("categoryId", categoryId);
        response.setData(data);

        return ResponseEntity.ok(response);
    }

}
