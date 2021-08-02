package com.example.flora.controller;

import com.example.flora.domain.Order;
import com.example.flora.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/")
    public String welcome(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Map<String,Object> model){
        model.put("name",name);
        return "welcome";
    }

    @GetMapping("/start")
    public String start(Map<String,Object> model){
        Iterable<Order> orders = orderRepository.findAll();
        model.put("ordersList", orders);
        return "start";
    }

    @GetMapping("/add")
    public String add(Map<String,Object> model){
        return "add";
    }

    /*@GetMapping("/hello")
    public String hello(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name", name);
        return "hello";
    }

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/")
    public String getOrderPage(Model model) {
        List<Order> orders = ordersService.findAll();
        model.addAttribute("orderList", orders);
        return "order";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(){
        return "index";
    }

    @RequestMapping("/welcome")
    public String getWelcome(){
        return "welcome";
    }*/

}
