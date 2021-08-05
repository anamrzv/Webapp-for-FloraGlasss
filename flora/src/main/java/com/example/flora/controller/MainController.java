package com.example.flora.controller;

import com.example.flora.domain.Order;
import com.example.flora.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/")
    public String welcome(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String, Object> model) {
        model.put("name", name);
        return "welcome";
    }

    @GetMapping("/start")
    public String showListOfOrders(Map<String, Object> model) {
        Iterable<Order> orders = orderRepository.findAll();
        model.put("ordersList", orders);
        return "start";
    }

    @GetMapping("/add")
    public String add(){
        return "add";
    }

    @PostMapping("/add")
    public String addNewOrder(@RequestParam String customer, @RequestParam int price, @RequestParam String phone, @RequestParam String address, @RequestParam String orderDate, @RequestParam String deliveryDate, @RequestParam String source, @RequestParam String comments) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Order newOrder = new Order.Builder()
                .withCustomer(customer)
                .withPrice(price)
                .withPhone(phone)
                .withAddress(address)
                .withOrderDate(LocalDate.parse(orderDate,formatter))
                .withDeliveryDate(LocalDate.parse(deliveryDate,formatter))
                .withComments(comments)
                .withSource(source)
                .build();
        if (!orderRepository.getListOfOrders().getLast().getOrderDate().getMonth().equals(newOrder.getOrderDate().getMonth()))
            newOrder.setNumInThisMonth(1);
        else newOrder.setNumInThisMonth(orderRepository.getListOfOrders().getLast().getNumInThisMonth()+1);
        orderRepository.save(newOrder);
        return "redirect:/start";
    }
}
