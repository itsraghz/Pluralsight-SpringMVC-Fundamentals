package com.pluralsight.conference.controller;

import com.pluralsight.conference.model.Registration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    /*@GetMapping("registration")
    public String getRegistration(Map<String, Object> model) {
        System.out.println("RegistrationController - register() method invoked");
        return "registration";
    }*/

    @GetMapping("registration")
    public String getRegistration(@ModelAttribute("registration") Registration registration) {
        System.out.println("RegistrationController - register() method invoked");
        return "registration";
    }

    @PostMapping("registration")
    public String addRegistration(@ModelAttribute("registration") Registration registration) {
        System.out.println("RegistrationController - addRegistration() method invoked for : " + registration.getName());
        return "registration";
    }
}
