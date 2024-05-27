package com.example.testetecnico.controller;

import com.example.testetecnico.entities.Profile;
import com.example.testetecnico.services.AccountService;
import com.example.testetecnico.services.CustomUserDetails;
import com.example.testetecnico.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/profiles")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private AccountService accountService;


    @GetMapping
    public String getProfiles(Model model) {
        Long id = getCredentialId();
        try {
            var account = accountService.findById(id);
            if (account.isPresent()) {
                model.addAttribute("account", account.get());
                return "profiles";

            } else {
                throw new UsernameNotFoundException("not found");
            }
        } catch (AccessDeniedException e) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Access Denied", e);
        } catch (UsernameNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);

        }

    }


    @GetMapping("/register-profile")
    public String showRegisterProfileForm(Model model) {
        Long id = getCredentialId();
        model.addAttribute("accountId", id);
        return "register-profile";
    }

    @PostMapping("/register-profile")
    public String addProfile(@RequestParam String profileName) {
        Long id = getCredentialId();
        var account = accountService.findById(id);
        Profile profile = new Profile();
        profile.setName(profileName);
        profileService.addProfile(account.get(), profile);

        return "redirect:/profiles";

    }

    private static Long getCredentialId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long id = userDetails.getId();
        return id;
    }

}