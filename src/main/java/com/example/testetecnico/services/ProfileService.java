package com.example.testetecnico.services;

import com.example.testetecnico.dto.MovieDto;
import com.example.testetecnico.entities.*;
import com.example.testetecnico.integration.tmdb.TheMovieDbIntegrationService;
import com.example.testetecnico.repositories.MovieRepository;
import com.example.testetecnico.repositories.PlanToWatchRepository;
import com.example.testetecnico.repositories.ProfileRepository;
import com.example.testetecnico.repositories.WatchedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PlanToWatchRepository planToWatchRepository;
    @Autowired
    private WatchedRepository watchedRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheMovieDbIntegrationService theMovieDbIntegrationService;


    public boolean canAddProfile(Account account) {
        return account.getProfiles().size() < 4;
    }

    public boolean noProfiles(Account account) {
        return account.getProfiles().isEmpty();
    }


    public Profile addProfile(Account account, Profile profile) {
        if (canAddProfile(account)) {
            if (noProfiles((account))) {
                profile.setAccount(account);
                return profileRepository.save(profile);
            } else {
                for (int i = 0; i < account.getProfiles().size(); i++) {
                    if (account.getProfiles().get(i).getName().equals(profile.getName())) {
                        break;
                    } else if (i == account.getProfiles().size() - 1) {
                        profile.setAccount(account);
                        return profileRepository.save(profile);
                    }
                }
            }
        }
        return null;
    }

    public PlanToWatch addMovieToPlanToWatch(Long profileId, Long movieId) {

        Optional<Profile> profile = profileRepository.findById(profileId);
        if (profile.isEmpty()) {
            return null;
        }
        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isEmpty()) {
            MovieDto movieDto = theMovieDbIntegrationService.getMovieById(movieId);
            movieRepository.save(movieDto.dtoToEntity());

            PlanToWatch pwt = PlanToWatch.builder()
                    .movieId(movieDto.getId())
                    .profileId(profile.get().getId())
                    .build();
            return planToWatchRepository.save(pwt);
        }
        Optional<Watched> watched = watchedRepository.findByProfileIdAndMovieId(profileId, movieId);
        if (watched.isPresent()) return null;

        Optional<PlanToWatch> pwt = planToWatchRepository.findByProfileIdAndMovieId(profileId, movieId);
        if (pwt.isEmpty()) {
            PlanToWatch planToWatch = PlanToWatch.builder()
                    .movieId(movie.get().getId())
                    .profileId(profile.get().getId())
                    .build();

            return planToWatchRepository.save(planToWatch);
        }

        return null;

    }

    public Watched addMovieToWatched(Long profileId, Long movieId) {
        Optional<Profile> profile = profileRepository.findById(profileId);
        if (profile.isEmpty()) {
            return null;
        }

        Optional<Movie> movie = movieRepository.findById(movieId);
        if (movie.isEmpty()) {
            MovieDto movieDto = theMovieDbIntegrationService.getMovieById(movieId);
            movieRepository.save(movieDto.dtoToEntity());
            Watched watched = Watched.builder()
                    .profileId(profile.get().getId())
                    .movieId(movieId)
                    .build();
            return watchedRepository.save(watched);
        }

        Optional<Watched> w = watchedRepository.findByProfileIdAndMovieId(profileId, movieId);
        if (w.isEmpty()) {
            ifPresentRemoveMovieFromPlanToWatch(profileId, movieId);

            Watched watched = Watched.builder()
                    .profileId(profile.get().getId())
                    .movieId(movie.get().getId())
                    .build();
            return watchedRepository.save(watched);
        }

        return null;

    }

    public void ifPresentRemoveMovieFromPlanToWatch(Long profileId, Long movieId) {
        Optional<PlanToWatch> pwt = planToWatchRepository.findByProfileIdAndMovieId(profileId, movieId);
        if (pwt.isPresent()) {
            planToWatchRepository.delete(pwt.get());
        }
    }

    public Profile getProfileWithWatchedList(Long profileId) {
        Optional<Profile> profile = profileRepository.findById(profileId);
        if (profile.isEmpty()) {
            return null;
        }
        Optional<List<Watched>> watched = watchedRepository.getAllById(profileId);
        if (watched.get().size() == 0) return profile.get();
        var w = watched.get().stream().toList();

        for (int i = 0; i < w.size(); i++) {
            Optional<Movie> movie = movieRepository.findById(w.get(i).getMovieId());
            profile.get().getWatched().add(movie.get());
        }

        return profile.get();

    }

    public Profile getProfileWithPlanToWatchList(Long profileId) {
        Optional<Profile> profile = profileRepository.findById(profileId);
        if (profile.isEmpty()) {
            return null;
        }
        Optional<List<PlanToWatch>> pwt = planToWatchRepository.getAllById(profileId);
        if (pwt.get().size() == 0) return profile.get();
        var p = pwt.get().stream().toList();

        for (int i = 0; i < p.size(); i++) {

            Optional<Movie> movie = movieRepository.findById(p.get(i).getMovieId());
            profile.get().getWatched().add(movie.get());
        }

        return profile.get();
    }
}
