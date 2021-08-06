package com.example.flora.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {

    @GetMapping("/")
    public String showHomePage(Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }
}
