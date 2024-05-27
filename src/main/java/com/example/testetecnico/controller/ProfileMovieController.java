package com.example.testetecnico.controller;

import com.example.testetecnico.dto.MoviePageDto;
import com.example.testetecnico.entities.Account;
import com.example.testetecnico.integration.tmdb.TheMovieDbIntegrationService;
import com.example.testetecnico.services.AccountService;
import com.example.testetecnico.services.CustomUserDetails;
import com.example.testetecnico.services.MovieService;
import com.example.testetecnico.services.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileMovieController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private TheMovieDbIntegrationService theMovieDbIntegrationService;
    @Autowired
    private MovieService movieService;

    @GetMapping("/{id}")
    public String handleLand(@PathVariable Long id) {
        if (checkAuth(id, getAccount())) {
            return "redirect:{id}/movies";
        } else {
            return "authError";
        }

    }


    @GetMapping("/{id}/movies")
    public String handleSearch(Model model,
                               @PathVariable Long id,
                               @RequestParam(required = false) String query,
                               @RequestParam(defaultValue = "1") Integer page) {

        Boolean auth = checkAuth(id, getAccount());
        if (auth) {
            MoviePageDto moviePageDto = theMovieDbIntegrationService.searchMovies(query, page);

            if (query == null) query = "";

            model.addAttribute("movies", moviePageDto.getMovies());
            model.addAttribute("page", moviePageDto.getPage());
            model.addAttribute("pageSize", moviePageDto.getMovies().size());
            model.addAttribute("totalPages", moviePageDto.getTotalPages());
            model.addAttribute("totalResults", moviePageDto.getTotalResults());
            model.addAttribute("profileId", id);
            model.addAttribute("query", query);
            return "movies";
        } else {
            return "authError";
        }
    }

    @PostMapping("/{profileId}/plantowatch/{movieId}")
    public String handlePlanToWatchPost(@PathVariable Long profileId, @PathVariable Long movieId) {
        if (checkAuth(profileId, getAccount())) {
            profileService.addMovieToPlanToWatch(profileId, movieId);
            return "redirect:/profile/" + profileId.toString() + "/movies";
        }
        return "authError";
    }

    @PostMapping("/{profileId}/watched/{movieId}")
    public String handleWatchedPost(@PathVariable Long profileId, @PathVariable Long movieId) {
        if (checkAuth(profileId, getAccount())) {
            profileService.addMovieToWatched(profileId, movieId);
            return "redirect:/profile/" + profileId.toString() + "/movies";
        }
        return "authError";
    }

    private static Boolean checkAuth(Long id, Optional<Account> account) {
        Boolean auth = false;
        for (int i = 0; i < account.get().getProfiles().size(); i++) {
            if (account.get().getProfiles().get(i).getId() == id) {
                auth = true;
                break;
            }
        }
        return auth;
    }

    private Optional<Account> getAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        Long accountId = userDetails.getId();
        var account = accountService.findById(accountId);
        return account;
    }

}