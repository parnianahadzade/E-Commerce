package com.mftplus.ecommerce.api.controller.color;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.api.dto.ColorSaveDTO;
import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.exception.component.ApiExceptionComponent;
import com.mftplus.ecommerce.exception.dto.ApiExceptionResponse;
import com.mftplus.ecommerce.model.entity.Color;
import com.mftplus.ecommerce.model.entity.Views;
import com.mftplus.ecommerce.service.ColorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/color")
public class ColorController {

    private final ColorService colorService;

    public ColorController(ColorService colorService) {
        this.colorService = colorService;
    }

    @JsonView(Views.ColorName.class)
    @GetMapping("/name")
    public List<Color> findColorsByNameStartsWith(@RequestParam(value = "colorName") String colorName) {
        return colorService.findByNameStartsWithIgnoreCaseAndDeletedFalse(colorName);
    }

    @PostMapping("/admin/save")
    public ResponseEntity saveColor(@Valid @RequestBody ColorSaveDTO colorSaveDTO,
                                       BindingResult result) throws DuplicateException {

        //validating inputs
        ResponseEntity<ApiExceptionResponse> responseEntity = ApiExceptionComponent.handleValidationErrors(result);
        if (responseEntity != null) {
            return responseEntity;
        }

        try {
            colorService.findByNameAndDeletedFalse(colorSaveDTO.getName());
            throw new DuplicateException("رنگ با این نام وجود دارد.");

        } catch (NoContentException e) {
            Color color = new Color();
            color.setName(colorSaveDTO.getName());
            color.setHexCode(colorSaveDTO.getHexCode());
            colorService.save(color);
        }

        return ResponseEntity.ok().build();
    }
}
