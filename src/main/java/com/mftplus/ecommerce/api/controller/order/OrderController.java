package com.mftplus.ecommerce.api.controller.order;

import com.fasterxml.jackson.annotation.JsonView;
import com.mftplus.ecommerce.model.entity.Order;
import com.mftplus.ecommerce.model.entity.User;
import com.mftplus.ecommerce.model.entity.Views;
import com.mftplus.ecommerce.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @JsonView(Views.Order.class)
    @PreAuthorize("hasRole('user')")
    @GetMapping
    public List<Order> findOrdersByUser(@AuthenticationPrincipal User user){
        return orderService.findOrdersByUser(user);
    }
}
