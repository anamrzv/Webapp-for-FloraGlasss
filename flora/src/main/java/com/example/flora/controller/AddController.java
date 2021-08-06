package com.example.flora.controller;

import com.example.flora.domain.Order;
import com.example.flora.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Controller
public class AddController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/add")
    public String add(Model model) {
        return "add";
    }

    @PostMapping("/add")
    public String addNewOrder(@RequestParam String customer, @RequestParam int price, @RequestParam String phone, @RequestParam String address, @RequestParam String orderDate, @RequestParam String deliveryDate, @RequestParam String source, @RequestParam String comments, Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (comments.isEmpty()) comments = "--";
        Order newOrder = new Order.Builder()
                .withCustomer(customer)
                .withPrice(price)
                .withPhone(phone)
                .withAddress(address)
                .withOrderDate(LocalDate.parse(orderDate, formatter))
                .withDeliveryDate(LocalDate.parse(deliveryDate, formatter))
                .withComments(comments)
                .withSource(source)
                .build();
        if (!orderRepository.getListOfOrders().getLast().getOrderDate().getMonth().equals(newOrder.getOrderDate().getMonth()))
            newOrder.setNumInThisMonth(1);
        else newOrder.setNumInThisMonth(orderRepository.getListOfOrders().getLast().getNumInThisMonth() + 1);
        orderRepository.save(newOrder);
        return "redirect:/start";
    }
}
