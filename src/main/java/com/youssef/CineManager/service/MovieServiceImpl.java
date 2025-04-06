package com.youssef.CineManager.service;

import com.youssef.CineManager.dto.BulkMovieDTO;
import com.youssef.CineManager.dto.MovieDTO;
import com.youssef.CineManager.dto.MovieDetailDTO;
import com.youssef.CineManager.mapper.MovieMapper;
import com.youssef.CineManager.model.Movie;
import com.youssef.CineManager.repository.MovieRepository;
import com.youssef.CineManager.util.MovieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;
    private final OmdbService omdbService;
    private final MovieMapper movieMapper;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, OmdbService omdbService, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.omdbService = omdbService;
        this.movieMapper = movieMapper;
    }

    @Override
    public MovieDetailDTO findByImdbId(String imdbId) {
        Movie movie = movieRepository.findById(imdbId)
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + imdbId));

        return movieMapper.toMovieDetailDTO(movie);
    }

    @Override
    public Page<MovieDTO> findAllMovies(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage = movieRepository.findAll(pageable);

        List<MovieDTO> movieDTOs = moviePage.getContent().stream()
                .map(movieMapper::toMovieDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(movieDTOs, pageable, moviePage.getTotalElements());
    }

    @Override
    public List<Movie> saveBulkMovies(BulkMovieDTO bulkMovieDto) {
        List<Movie> moviesToSave = new ArrayList<>();

        for (String id : bulkMovieDto.getIds()) {

            Movie movie = omdbService.getMovieDetails(id); ;
            if (movie != null) {
                moviesToSave.add(movie);
            }
        }

        return movieRepository.saveAll(moviesToSave);
    }

    @Override
    public void deleteBulkMovies(BulkMovieDTO bulkMovieDto) {
        List<Movie> moviesToDelete = movieRepository.findByImdbIDIn(bulkMovieDto.getIds());
        movieRepository.deleteAll(moviesToDelete);
    }

    @Override
    public Page<MovieDTO> filterMovies(String year, String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Movie> moviePage;

        if (year != null && title != null) {
            System.out.println("Year and Title filter applied");
            moviePage = movieRepository.findByYearAndTitleContainingIgnoreCase(year, title, pageable);
        } else if (year != null) {
            System.out.println("Year filter applied");
            moviePage = movieRepository.findByYear(year, pageable);
        } else if (title != null) {
            System.out.println("Title filter applied");
            moviePage = movieRepository.findByTitleContainingIgnoreCase(title, pageable);
        } else {
            System.out.println("No filter applied");
            moviePage = movieRepository.findAll(pageable);
        }

        List<MovieDTO> movieDTOs = moviePage.getContent().stream()
                .map(movieMapper::toMovieDTO)
                .collect(Collectors.toList());

        return new PageImpl<>(movieDTOs, pageable, moviePage.getTotalElements());
    }
}