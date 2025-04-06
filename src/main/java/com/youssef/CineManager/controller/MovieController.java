package com.youssef.CineManager.controller;

import com.youssef.CineManager.dto.BulkMovieDTO;
import com.youssef.CineManager.dto.MovieDTO;
import com.youssef.CineManager.dto.MovieDetailDTO;

import com.youssef.CineManager.model.Movie;
import com.youssef.CineManager.response.ApiResponse;
import com.youssef.CineManager.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{imdbId}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public MovieDetailDTO getMovieById(@PathVariable String imdbId) {
        return movieService.findByImdbId(imdbId);
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Page<MovieDTO> getAllMovies(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
        return movieService.findAllMovies(page, size);
    }

    @PostMapping("/bulk")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Movie> addBulkMovies(@RequestBody BulkMovieDTO bulkMovieDto) {
        return movieService.saveBulkMovies(bulkMovieDto);
    }

    @DeleteMapping("/bulk")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteBulkMovies(@RequestBody BulkMovieDTO bulkMovieDto) {
        movieService.deleteBulkMovies(bulkMovieDto);
    }

    @GetMapping("/filter")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Page<MovieDTO> filterMovies(
            @RequestParam(required = false) String year,
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "30") int size) {
        return movieService.filterMovies(year, title, page, size );
    }
}
