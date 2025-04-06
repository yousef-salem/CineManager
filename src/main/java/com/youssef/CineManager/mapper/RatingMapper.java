package com.youssef.CineManager.mapper;

import com.youssef.CineManager.dto.RatingDTO;
import com.youssef.CineManager.model.Rating;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    RatingDTO toRatingDTO(Rating rating);

    List<RatingDTO> toRatingDTOList(List<Rating> ratings);

    Rating toRating(RatingDTO ratingDTO);

    List<Rating> toRatingList(List<RatingDTO> ratingDTOs);
}