package com.youssef.CineManager.service;

import com.youssef.CineManager.model.Movie;
import com.youssef.CineManager.model.SearchResult;
import org.springframework.http.ResponseEntity;

public interface OmdbService {
    SearchResult searchMovies(String query, Integer page, Integer year);
    Movie getMovieDetails(String imdbId);
}