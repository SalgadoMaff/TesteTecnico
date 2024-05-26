package com.example.testetecnico.controller;

import com.example.testetecnico.entities.Profile;
import com.example.testetecnico.services.AccountService;
import com.example.testetecnico.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private AccountService accountService;


    @GetMapping("/account/{id}/profiles/")
    public String getProfiles(@PathVariable Long id, Model model) {
        try {
            var account = accountService.findById(id);
            if(account.isPresent()){
                model.addAttribute("account",account.get());
                return "profiles";

            }else{
                throw new UsernameNotFoundException("not found");
            }
        } catch (AccessDeniedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied", e);
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);

        }

    }

    @GetMapping("/account/{id}/register-profile")
    public String showRegisterProfileForm(@PathVariable Long id, Model model) {
        model.addAttribute("accountId", id);
        return "register-profile";
    }

    @PostMapping("/account/{id}/register-profile")
    public String addProfile(@PathVariable Long id, @RequestParam String profileName) {
        var account=accountService.findById(id);
        Profile profile = new Profile();
        profile.setName(profileName);
        profileService.addProfile(account.get(),profile);

        return "redirect:/account/" + id + "/profiles/";

    }

}