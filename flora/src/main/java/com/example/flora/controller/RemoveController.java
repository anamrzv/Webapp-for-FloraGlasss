package com.example.flora.controller;

import com.example.flora.domain.Order;
import com.example.flora.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RemoveController {
    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/order/{id}/remove")
    public String deleteOrder(@PathVariable(value = "id") long id, Model model) {
        if (!orderRepository.existsById(id)) {
            return "redirect:/home";
        }
        Order order = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(order);
        return "redirect:/table";
    }
}
