package com.example.jobportal.controller;

import com.example.jobportal.entity.Role;
import com.example.jobportal.entity.User;
import com.example.jobportal.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "register";
        }
        
        if (userService.findByEmail(user.getEmail()).isPresent()) {
            result.rejectValue("email", null, "There is already an account registered with that email");
            return "register";
        }

        // By default we don't allow Admin registration from public form
        if (user.getRole() == null || user.getRole() == Role.ADMIN) {
            user.setRole(Role.STUDENT); 
        }

        userService.save(user);
        return "redirect:/login?success";
    }
}
