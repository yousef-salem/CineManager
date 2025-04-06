package com.youssef.CineManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieRatingDTO {
    private Long userId;
    private String movieId;
    private Integer rating; // null if just updating like/dislike
    private Boolean liked; // null if just updating rating
}