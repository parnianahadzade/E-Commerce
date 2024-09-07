package com.mftplus.ecommerce.api.controller.brand;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.api.dto.BrandSaveDTO;
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
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<ApiResponse> saveBrand(@Valid @RequestBody BrandSaveDTO brandSaveDTO) {
        return null;
    }
}
