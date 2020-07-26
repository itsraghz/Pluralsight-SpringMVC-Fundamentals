package com.pluralsight.conference.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class GreetingController {

    @GetMapping("greeting")
    public String greeting(Map<String, Object> model) {
        System.out.println("GreetingController - greeting() method invoked");
        model.put("message", "Hello Raghs");
        return "greeting";
    }
}
