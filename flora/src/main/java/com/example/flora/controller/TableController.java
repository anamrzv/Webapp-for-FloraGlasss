package com.example.flora.controller;

import com.example.flora.domain.Order;
import com.example.flora.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Map;

@Controller
public class TableController {
    @Autowired
    OrderRepository orderRepository;

    @GetMapping("/table")
    public String showListOfOrders(Model model) {
        Iterable<Order> orders = orderRepository.findAll();
        model.addAttribute("ordersList", orders);
        model.addAttribute("month", LocalDate.now().getMonth());
        return "table";
    }
}
