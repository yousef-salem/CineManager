package com.youssef.CineManager.mapper;


import com.youssef.CineManager.dto.MovieRatingDTO;
import com.youssef.CineManager.model.UserMovieRating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserRatingMapper {

    @Mapping(target = "movieId", source = "movie.imdbID")
    @Mapping(target = "userId", source = "user.id")
    MovieRatingDTO toMovieRatingDTO(UserMovieRating userMovieRating);
}
