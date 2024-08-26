package com.mftplus.ecommerce.api.controller.order;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.api.dto.OrderSaveDTO;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.exception.UserIdentification;
import com.mftplus.ecommerce.model.entity.*;
import com.mftplus.ecommerce.model.entity.enums.OrderStatus;
import com.mftplus.ecommerce.service.impl.InventoryServiceImpl;
import com.mftplus.ecommerce.service.impl.OrderInventoryServiceImpl;
import com.mftplus.ecommerce.service.impl.OrderServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/order")
public class OrderController {

    private final OrderServiceImpl orderService;

    private final InventoryServiceImpl inventoryService;

    private final OrderInventoryServiceImpl orderInventoryService;

    public OrderController(OrderServiceImpl orderService, InventoryServiceImpl inventoryService, OrderInventoryServiceImpl orderInventoryService) {
        this.orderService = orderService;
        this.inventoryService = inventoryService;
        this.orderInventoryService = orderInventoryService;
    }

    @JsonView(Views.OrderList.class)
    @PreAuthorize("hasRole('user')")
    @GetMapping
    public List<Order> findOrdersByUser(@AuthenticationPrincipal User user){
        return orderService.findByUserAndDeletedFalse(user);
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


    // TODO: 7/30/2024 transactional 
    @Transactional(rollbackOn = {NoContentException.class})
    @PostMapping("/save")
    public ResponseEntity saveOrder(@AuthenticationPrincipal User user, @RequestBody List<OrderSaveDTO> orderSaveDTOS) throws UserIdentification, NoContentException {
        if (!user.isIdentified()){
            throw new UserIdentification("Logged in User is not Identified!");
        }
        Order order = new Order();
        order.setUser(user);
        order.setAddress(user.getPerson().getAddress());
        order.setDateCreated(LocalDate.now());
        order.setOrderStatus(OrderStatus.waitingForPayment);
        orderService.save(order);


        for (OrderSaveDTO orderSaveDTO : orderSaveDTOS) {
            Inventory originalInventory = inventoryService.findByIdAndDeletedFalse(orderSaveDTO.getInventory().getId());

            if (originalInventory.getQuantity() <= orderSaveDTO.getQuantity()){
                throw new NoContentException("not Enough inventory");
            }

            OrderInventory orderInventory = new OrderInventory();
            orderInventory.setOrder(order);
            orderInventory.setInventory(originalInventory);
            orderInventory.setQuantity(orderSaveDTO.getQuantity());

            orderInventoryService.save(orderInventory);

            Integer newInventoryQuantity = originalInventory.getQuantity() - orderSaveDTO.getQuantity();
            originalInventory.setQuantity(newInventoryQuantity);
            inventoryService.update(originalInventory);

        }

        return ResponseEntity.ok().build();
    }
}
