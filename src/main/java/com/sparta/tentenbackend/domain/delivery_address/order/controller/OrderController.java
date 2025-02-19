package com.sparta.tentenbackend.domain.delivery_address.order.controller;

import com.sparta.tentenbackend.domain.delivery_address.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
