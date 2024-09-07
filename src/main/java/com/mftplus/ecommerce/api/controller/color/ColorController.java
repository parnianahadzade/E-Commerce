package com.mftplus.ecommerce.api.controller.color;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.api.dto.ColorSaveDTO;
import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.exception.component.ApiValidationComponent;
import com.mftplus.ecommerce.exception.dto.ApiResponse;
import com.mftplus.ecommerce.model.entity.Color;
import com.mftplus.ecommerce.model.entity.Views;
import com.mftplus.ecommerce.service.ColorService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${apiPrefix}/color")
@Slf4j
@CrossOrigin
public class ColorController {

    private final ColorService colorService;

    private final ApiValidationComponent validationComponent;

    public ColorController(ColorService colorService, ApiValidationComponent validationComponent) {
        this.colorService = colorService;
        this.validationComponent = validationComponent;
    }

    @JsonView(Views.ColorName.class)
    @GetMapping("/name/{colorName}")
    public List<Color> findColorsByNameStartsWith(@PathVariable String colorName) {
        return colorService.findByNameStartsWithIgnoreCaseAndDeletedFalse(colorName);
    }

    @PostMapping("/admin/save")
    public ResponseEntity saveColor(@Valid @RequestBody ColorSaveDTO colorSaveDTO,
                                       BindingResult result) throws DuplicateException {

        //validation
        ApiResponse response = validationComponent.handleValidationErrors(result);

        if (response.getFieldErrors() != null) {
            return ResponseEntity.badRequest().body(response);
        }

        try {
            colorService.findByNameAndDeletedFalse(colorSaveDTO.getName());
            throw new DuplicateException("رنگ با این نام وجود دارد.");

        } catch (NoContentException e) {
            Color color = new Color();
            color.setName(colorSaveDTO.getName());
            color.setHexCode(colorSaveDTO.getHexCode());
            colorService.save(color);

            response.setSuccess(true);
            response.setSuccessMessage("رنگ با موفقیت ایجاد شد.");

            Map<String, Object> data = new HashMap<>();
            data.put("color", color);
            response.setData(data);
        }

        return ResponseEntity.ok(response);
    }
}
