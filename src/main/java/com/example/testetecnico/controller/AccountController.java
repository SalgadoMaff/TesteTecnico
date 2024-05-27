package com.example.testetecnico.controller;

import com.example.testetecnico.entities.Account;
import com.example.testetecnico.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/login")
    public String handleLogin() {
        return "custom_login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        Account account = new Account();
        model.addAttribute("user", account);
        return "register";
    }

    @PostMapping("/register")
    public String registerAccount(Account account) {
        accountService.save(account);
        return "redirect:/login";
    }

}
