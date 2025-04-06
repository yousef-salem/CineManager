package com.youssef.CineManager.mapper;


import com.youssef.CineManager.dto.MovieDTO;
import com.youssef.CineManager.dto.MovieDetailDTO;
import com.youssef.CineManager.model.Movie;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {RatingMapper.class})
public interface MovieMapper {

    MovieDTO toMovieDTO(Movie movie);

    List<MovieDTO> toMovieDTOList(List<Movie> movies);

    MovieDetailDTO toMovieDetailDTO(Movie movie);

    Movie toMovie(MovieDetailDTO movieDetailDTO);
}