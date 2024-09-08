package com.mftplus.ecommerce.api.controller.color;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.api.dto.ColorSaveDTO;
import com.mftplus.ecommerce.exception.DuplicateException;
import com.mftplus.ecommerce.exception.InvalidDataException;
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
import java.util.Objects;

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

    //color find by name
    @GetMapping("/findBy")
    @JsonView(Views.ColorName.class)
    public List<Color> findColorsByNameStartsWith(@RequestParam(required = false, value = "colorName") String colorName) throws NoContentException, InvalidDataException {
        if (colorName == null) {
            throw new InvalidDataException("نام رنگ وارد نشده است.");
        }

        List<Color> colors = colorService.findByNameStartsWithIgnoreCaseAndDeletedFalse(colorName);

        if (colors.isEmpty()) {
            throw new NoContentException("موردی یافت نشد.");
        }

        return colors;
    }

    //color find by id
    @GetMapping("/id/{colorId}")
    @JsonView(Views.Color.class)
    public Color findColorById(@PathVariable Long colorId) throws NoContentException {
        return colorService.findByIdAndDeletedFalse(colorId);
    }

    //color find all pageable
    @GetMapping
    @JsonView(Views.Color.class)
    public List<Color> findColors(@RequestParam(required = false, value = "pageNumber") Integer pageNumber,
                                  @RequestParam(required = false, value = "colorName") String colorName) throws NoContentException, InvalidDataException {
        if (pageNumber == null) {
            throw new InvalidDataException("شماره صفحه وارد نشده است.");
        }

        if (colorName != null) {
            return colorService.findByNameStartsWithIgnoreCaseAndDeletedFalse(colorName);
        }

        int pageSize = 10;

        List<Color> colors = colorService.findAllByDeletedFalse(pageNumber, pageSize);

        if (colors.isEmpty()) {
            throw new NoContentException("موردی یافت نشد.");
        }

        return colors;
    }

    //color save
    @PostMapping("/admin/save")
    public ResponseEntity<ApiResponse> saveColor(@Valid @RequestBody ColorSaveDTO colorSaveDTO,
                                       BindingResult result) throws DuplicateException {

        //validation
        ApiResponse response = validationComponent.handleValidationErrors(result);

        if (response.getFieldErrors() != null) {
            return ResponseEntity.badRequest().body(response);
        }

        colorService.findByNameAndDeletedFalseWithOutReturn(colorSaveDTO.getName());

        Color color = new Color();
        color.setName(colorSaveDTO.getName());
        color.setHexCode(colorSaveDTO.getHexCode());
        colorService.save(color);

        response.setSuccess(true);
        response.setSuccessMessage("رنگ با موفقیت ایجاد شد.");

        Map<String, Object> data = new HashMap<>();
        data.put("color", color);
        response.setData(data);

        return ResponseEntity.ok(response);
    }

    //color update
    @PutMapping("/admin/update/{colorId}")
    public ResponseEntity<ApiResponse> updateColor(@Valid @RequestBody ColorSaveDTO colorSaveDTO,
                                                   BindingResult result, @PathVariable Long colorId) throws NoContentException, DuplicateException {

        Color color = colorService.findByIdAndDeletedFalse(colorId);

        //validating inputs
        ApiResponse response = validationComponent.handleValidationErrors(result);

        if (response.getFieldErrors() != null) {
            return ResponseEntity.badRequest().body(response);
        }

        if (!Objects.equals(color.getName(), colorSaveDTO.getName())) {
            colorService.findByNameAndDeletedFalseWithOutReturn(colorSaveDTO.getName());
        }

        color.setId(color.getId());
        color.setName(colorSaveDTO.getName());
        color.setHexCode(colorSaveDTO.getHexCode());
        colorService.update(color);

        response.setSuccess(true);
        response.setSuccessMessage("رنگ با موفقیت بروزرسانی شد.");

        Map<String, Object> data = new HashMap<>();
        data.put("color", color);
        response.setData(data);

        return ResponseEntity.ok(response);

    }

    //color logical remove
    @DeleteMapping("/admin/delete/{colorId}")
    public ResponseEntity<ApiResponse> logicalRemoveColor(@PathVariable Long colorId) throws NoContentException {

        ApiResponse response = new ApiResponse();

        colorService.logicalRemove(colorId);

        response.setSuccess(true);
        response.setSuccessMessage("رنگ با موفقیت حذف شد.");

        Map<String, Object> data = new HashMap<>();
        data.put("colorId", colorId);
        response.setData(data);

        return ResponseEntity.ok(response);
    }
}
