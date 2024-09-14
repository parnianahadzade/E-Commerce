package com.mftplus.ecommerce.api.controller.order;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.mfathi91.time.PersianDate;
import com.mftplus.ecommerce.api.dto.OrderSaveDTO;
import com.mftplus.ecommerce.api.dto.OrderSearchRequest;
import com.mftplus.ecommerce.exception.InvalidDataException;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.exception.UserIdentificationException;
import com.mftplus.ecommerce.exception.component.ApiValidationComponent;
import com.mftplus.ecommerce.exception.dto.ApiResponse;
import com.mftplus.ecommerce.model.entity.*;
import com.mftplus.ecommerce.model.entity.enums.OrderStatus;
import com.mftplus.ecommerce.service.impl.InventoryServiceImpl;
import com.mftplus.ecommerce.service.impl.OrderInventoryServiceImpl;
import com.mftplus.ecommerce.service.impl.OrderServiceImpl;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/order")
@Slf4j
@CrossOrigin
public class OrderController {

    private final OrderServiceImpl orderService;

    private final InventoryServiceImpl inventoryService;

    private final OrderInventoryServiceImpl orderInventoryService;

    private final ApiValidationComponent validationComponent;

    public OrderController(OrderServiceImpl orderService, InventoryServiceImpl inventoryService, OrderInventoryServiceImpl orderInventoryService, ApiValidationComponent validationComponent) {
        this.orderService = orderService;
        this.inventoryService = inventoryService;
        this.orderInventoryService = orderInventoryService;
        this.validationComponent = validationComponent;
    }

    //order find by user
    @GetMapping
    @JsonView(Views.OrderList.class)
    public List<Order> findOrdersByUser(@AuthenticationPrincipal User user){
        return orderService.findByUserAndDeletedFalse(user);
    }

    @GetMapping("/admin/all")
    @JsonView(Views.OrderList.class)
    public List<Order> findOrders
            (@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
             @RequestParam(value = "orderStatus", required = false) String orderStatus,
             @RequestParam(value = "firstName", required = false) String firstName,
             @RequestParam(value = "lastName", required = false) String lastName,
             @RequestParam(value = "trackingCode", required = false) String trackingCode,
             @RequestParam(value = "startFaDateCreated", required = false) String startFaDateCreated,
             @RequestParam(value = "endFaDateCreated", required = false) String endFaDateCreated) throws InvalidDataException, NoContentException {

        if (pageNumber == null) {
            throw new InvalidDataException("شماره صفحه وارد نشده است.");
        }

        OrderSearchRequest request = new OrderSearchRequest();
        request.setOrderStatus(orderStatus);
        request.setPageNumber(pageNumber);
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setTrackingCode(trackingCode);
        if (startFaDateCreated != null && endFaDateCreated != null) {
            request.setStartDateCreated(PersianDate.parse(startFaDateCreated).toGregorian());
            request.setEndDateCreated(PersianDate.parse(endFaDateCreated).toGregorian());
        }


        List<Order> orders = orderService.findAllByCriteria(request);

        if (orders.isEmpty()) {
            throw new NoContentException("موردی یافت نشد.");
        }

        return orders;
    }

    //waitingForPayment and successfulPayOrValidated
    @JsonView(Views.OrderList.class)
    @GetMapping("/current")
    public List<Order> findOrderByUserAndCurrentOrderStatus(@AuthenticationPrincipal User user){
        return orderService.findOrdersWaitingOrValidatedAndUserAndDeletedFalse(user);
    }

    //failedPayOrCanceled
    @JsonView(Views.OrderList.class)
    @GetMapping("/canceled")
    public List<Order> findOrderByUserAndCanceledOrderStatus(@AuthenticationPrincipal User user){
        return orderService.findCanceledOrdersAndUserAndDeletedFalse(user);
    }

    //delivered
    @JsonView(Views.OrderList.class)
    @GetMapping("/delivered")
    public List<Order> findOrderByUserAndDeliveredOrderStatus(@AuthenticationPrincipal User user){
        return orderService.findDeliveredOrdersAndUserAndDeletedFalse(user);
    }

    //order save
    @PostMapping("/save")
    @Transactional(rollbackOn = {NoContentException.class})
    public ResponseEntity<ApiResponse> saveOrder(@RequestBody List<OrderSaveDTO> orderSaveDTOS,
                                                 BindingResult result, @AuthenticationPrincipal User user) throws UserIdentificationException, NoContentException {
        if (!user.isIdentified()){
            throw new UserIdentificationException("پروفایل کاربر قبلا ساخته نشده.");
        }

        //validating inputs
        ApiResponse response = validationComponent.handleValidationErrors(result);

        if (response.getFieldErrors() != null) {
            return ResponseEntity.badRequest().body(response);
        }

        if (orderSaveDTOS == null || orderSaveDTOS.isEmpty()) {
            throw new NoContentException("سبد خرید شما خالی است.");
        }

        Order order = new Order();
        order.setUser(user);
        order.setAddress(user.getPerson().getAddress());
        order.setDateCreated(LocalDate.now());
        order.setOrderStatus(OrderStatus.waitingForPayment);
        orderService.save(order);

        //todo : recheck
        order.setTrackingCode(String.valueOf(order.getDateCreated()).replace("-", "") + order.getId());
        orderService.update(order);



        for (OrderSaveDTO orderSaveDTO : orderSaveDTOS) {
            Inventory originalInventory = inventoryService.findByIdAndDeletedFalse(orderSaveDTO.getInventory().getId());

            if (originalInventory.getQuantity() < orderSaveDTO.getQuantity()){
                throw new NoContentException(" موجودی برای " + originalInventory.getProduct().getName() + " کافی نمی باشد.");
            }

            OrderInventory orderInventory = new OrderInventory();
            orderInventory.setOrder(order);
            orderInventory.setInventory(originalInventory);
            orderInventory.setQuantity(orderSaveDTO.getQuantity());
            orderInventory.setSingleProductPrice(originalInventory.getProduct().getPrice());
            orderInventory.setSingleProductOffPercent(originalInventory.getProduct().getOffPercent());

            orderInventoryService.save(orderInventory);

            Integer newInventoryQuantity = originalInventory.getQuantity() - orderSaveDTO.getQuantity();
            originalInventory.setQuantity(newInventoryQuantity);
            inventoryService.update(originalInventory);

        }

        response.setSuccess(true);
        response.setSuccessMessage("سفارش با موفقیت ثبت شد.");

        return ResponseEntity.ok(response);
    }
}
