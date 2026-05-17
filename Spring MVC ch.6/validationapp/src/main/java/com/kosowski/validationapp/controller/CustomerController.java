package com.kosowski.validationapp.controller;

import com.kosowski.validationapp.model.Customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @GetMapping("/")
    public String showCustomerForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-form";
    }

    @PostMapping("/processForm")
    public String processCustomerForm(
            @Valid @ModelAttribute("customer") Customer customer,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "customer-form";
        } else {
            return "customer-confirmation";
        }
    }
}
