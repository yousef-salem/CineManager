package com.youssef.CineManager.service;

import com.youssef.CineManager.dto.BulkMovieDTO;
import com.youssef.CineManager.dto.MovieDTO;
import com.youssef.CineManager.dto.MovieDetailDTO;
import com.youssef.CineManager.model.Movie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MovieService {


    MovieDetailDTO findByImdbId(String imdbId);

    Page<MovieDTO> findAllMovies(int page, int size);

    List<Movie> saveBulkMovies(BulkMovieDTO bulkMovieDto);

    void deleteBulkMovies(BulkMovieDTO bulkMovieDto);

    Page<MovieDTO> filterMovies(String year, String title, int page, int size);
}