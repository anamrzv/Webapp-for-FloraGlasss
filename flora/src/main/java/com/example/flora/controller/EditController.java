package com.example.flora.controller;

import com.example.flora.domain.Order;
import com.example.flora.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Optional;

@Controller
public class EditController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/order/{id}/edition")
    public String showEditPage(@PathVariable(value = "id") long id, Model model) {
        if (!orderRepository.existsById(id)) {
            return "redirect:/home";
        }
        Optional<Order> order = orderRepository.findById(id);
        LinkedList<Order> orders = new LinkedList<>();
        order.ifPresent(orders::add);
        model.addAttribute("orders", orders);
        return "/edition";
    }

    @PostMapping("/order/{id}/edition")
    public String editOrder(@PathVariable(value = "id") long id, @RequestParam String customer, @RequestParam int price, @RequestParam String phone, @RequestParam String address, @RequestParam String orderDate, @RequestParam String deliveryDate, @RequestParam String source, @RequestParam String comments, Model model) {
        if (!orderRepository.existsById(id)) {
            return "redirect:/home";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (comments.isEmpty()) comments = "--";
        Order editedOrder = orderRepository.findById(id).orElseThrow();
        if (editedOrder!=null) {
            editedOrder.setCustomer(customer);
            editedOrder.setPrice(price);
            editedOrder.setPhone(phone);
            editedOrder.setAddress(address);
            editedOrder.setOrderDate(LocalDate.parse(orderDate, formatter));
            editedOrder.setDeliveryDate(LocalDate.parse(deliveryDate, formatter));
            editedOrder.setSource(source);
            editedOrder.setComments(comments);
        }
        orderRepository.save(editedOrder);
        return "redirect:/table";
    }

}
