package com.chaohappy.demo;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value="/home")
    public String showHomePage(Map<String, Object> model) {
        model.put("spittles", "sssssssss");
        return "home";
    }
}