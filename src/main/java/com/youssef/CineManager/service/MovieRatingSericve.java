package com.youssef.CineManager.service;

import com.youssef.CineManager.dto.MovieRatingDTO;
import com.youssef.CineManager.model.UserMovieRating;
import com.youssef.CineManager.security.model.User;

import java.util.Optional;

public interface MovieRatingSericve {

    MovieRatingDTO rateMovie(MovieRatingDTO request);

    MovieRatingDTO getUserMovieRating(Long userid, String movieId);
}
