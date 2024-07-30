package com.mftplus.ecommerce.api.controller.order;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.api.dto.OrderBody;
import com.mftplus.ecommerce.exception.NoContentException;
import com.mftplus.ecommerce.exception.UserNotIdentified;
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

    @JsonView(Views.Order.class)
    @PreAuthorize("hasRole('user')")
    @GetMapping
    public List<Order> findOrdersByUser(@AuthenticationPrincipal User user){
        return orderService.findByUserAndDeletedFalse(user);
    }

    // TODO: 7/30/2024 transactional 
    @Transactional(rollbackOn = {NoContentException.class})
    @PostMapping("/save")
    public ResponseEntity saveOrder(@AuthenticationPrincipal User user, @RequestBody List<OrderBody> orderBodies) throws UserNotIdentified, NoContentException {
        if (!user.isIdentified()){
            throw new UserNotIdentified("Logged in User is not Identified!");
        }
        Order order = new Order();
        order.setUser(user);
        order.setAddress(user.getPerson().getAddress());
        order.setDateCreated(LocalDate.now());
        order.setOrderStatus(OrderStatus.paying);
        orderService.save(order);


        for (OrderBody orderBody : orderBodies) {
            Inventory originalInventory = inventoryService.findByIdAndDeletedFalse(orderBody.getInventory().getId());

            if (originalInventory.getQuantity() <= orderBody.getQuantity()){
                throw new NoContentException("not Enough inventory");
            }

            OrderInventory orderInventory = new OrderInventory();
            orderInventory.setOrder(order);
            orderInventory.setInventory(originalInventory);
            orderInventory.setQuantity(orderBody.getQuantity());

            orderInventoryService.save(orderInventory);

            Integer newInventoryQuantity = originalInventory.getQuantity() - orderBody.getQuantity();
            originalInventory.setQuantity(newInventoryQuantity);
            inventoryService.update(originalInventory);

        }

        return ResponseEntity.ok().build();
    }
}
