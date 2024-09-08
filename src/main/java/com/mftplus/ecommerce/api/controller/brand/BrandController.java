package com.mftplus.ecommerce.api.controller.brand;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.api.dto.BrandSaveDTO;
import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.InvalidDataException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.exception.component.ApiValidationComponent;
import com.mftplus.ecommerce.exception.dto.ApiResponse;
import com.mftplus.ecommerce.model.entity.Brand;
import com.mftplus.ecommerce.model.entity.Views;
import com.mftplus.ecommerce.service.BrandService;
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
@RequestMapping("${apiPrefix}/brand")
@Slf4j
@CrossOrigin
public class BrandController {

    private final BrandService brandService;

    private final ApiValidationComponent validationComponent;

    public BrandController(BrandService brandService, ApiValidationComponent validationComponent) {
        this.brandService = brandService;
        this.validationComponent = validationComponent;
    }

    @GetMapping("/findBy")
    @JsonView(Views.BrandName.class)
    public List<Brand> findBrandsByNameStartsWith(@RequestParam(required = false, value = "brandName") String brandName) throws InvalidDataException, NoContentException {
        if (brandName == null) {
            throw new InvalidDataException("نام دسته بندی وارد نشده است.");
        }

        List<Brand> brands = brandService.findByNameStartsWithIgnoreCaseAndDeletedFalse(brandName);

        if (brands.isEmpty()) {
            throw new NoContentException("موردی یافت نشد.");
        }

        return brands;
    }

    @PostMapping("/admin/save")
    public ResponseEntity<ApiResponse> saveBrand(@Valid @RequestBody BrandSaveDTO brandSaveDTO,
                                                 BindingResult result) throws DuplicateException {
        //validating inputs
        ApiResponse response = validationComponent.handleValidationErrors(result);

        if (response.getFieldErrors() != null) {
            return ResponseEntity.badRequest().body(response);
        }

        brandService.findByNameAndDeletedFalseWithOutReturn(brandSaveDTO.getName());

        Brand brand = new Brand();
        brand.setName(brandSaveDTO.getName());
        brand.setExplanation(brandSaveDTO.getExplanation());
        brandService.save(brand);

        response.setSuccess(true);
        response.setSuccessMessage("برند با موفقیت ایجاد شد.");

        Map<String, Object> data = new HashMap<>();
        data.put("brand", brand);
        response.setData(data);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/admin/update/{brandId}")
    public ResponseEntity<ApiResponse> updateBrand(@Valid @RequestBody BrandSaveDTO brandSaveDTO,
                                                      BindingResult result, @PathVariable Long brandId) throws NoContentException, DuplicateException {

        Brand brand = brandService.findByIdAndDeletedFalse(brandId);

        //validating inputs
        ApiResponse response = validationComponent.handleValidationErrors(result);

        if (response.getFieldErrors() != null) {
            return ResponseEntity.badRequest().body(response);
        }

        if (!Objects.equals(brand.getName(), brandSaveDTO.getName())) {
            brandService.findByNameAndDeletedFalseWithOutReturn(brandSaveDTO.getName());
        }

        brand.setId(brand.getId());
        brand.setName(brandSaveDTO.getName());
        brand.setExplanation(brandSaveDTO.getExplanation());
        brandService.update(brand);

        response.setSuccess(true);
        response.setSuccessMessage("برند با موفقیت بروزرسانی شد.");

        Map<String, Object> data = new HashMap<>();
        data.put("brand", brand);
        response.setData(data);

        return ResponseEntity.ok(response);

    }
}
