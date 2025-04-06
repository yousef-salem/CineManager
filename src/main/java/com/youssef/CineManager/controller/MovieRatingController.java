package com.youssef.CineManager.controller;


import com.youssef.CineManager.dto.MovieRatingDTO;
import com.youssef.CineManager.mapper.UserRatingMapper;
import com.youssef.CineManager.model.UserMovieRating;
import com.youssef.CineManager.service.MovieRatingSericve;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/api/movies")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MovieRatingController {
    private final MovieRatingSericve movieRatingService;


    @PostMapping("/rate")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MovieRatingDTO> rateMovie(@RequestBody MovieRatingDTO request) {
        ;
        return ResponseEntity.ok(movieRatingService.rateMovie(request));
    }

    @GetMapping("/user-rating/{movieId}/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<MovieRatingDTO> getUserRating(@PathVariable Long userId,
                                                         @PathVariable String movieId) {
        MovieRatingDTO rating = movieRatingService.getUserMovieRating(userId, movieId);
        return ResponseEntity.ok(rating);
    }
}