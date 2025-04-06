package com.youssef.CineManager.controller;

import com.youssef.CineManager.model.Movie;
import com.youssef.CineManager.model.SearchResult;
import com.youssef.CineManager.service.OmdbService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies/omdb")
@CrossOrigin("*")
public class OmdbController {
    private final OmdbService omdbService;

    public OmdbController(OmdbService omdbService) {
        this.omdbService = omdbService;
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    public SearchResult searchMovies(
            @RequestParam(defaultValue = "") String query,
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(required = false) Integer year) {

        return omdbService.searchMovies(query, page, year);
    }

    @GetMapping("/{imdbId}")
    @PreAuthorize("hasRole('ADMIN')")
    public Movie getMovieDetails(@PathVariable String imdbId) {
        return omdbService.getMovieDetails(imdbId);

    }
}