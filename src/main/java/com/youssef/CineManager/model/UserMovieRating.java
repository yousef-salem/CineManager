package com.youssef.CineManager.model;

import com.youssef.CineManager.security.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "user_movie_ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserMovieRating {
    @EmbeddedId
    private UserMovieRatingId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("movieId")
    @JoinColumn(name = "movie_id")
    private Movie movie;

    private Integer rating;
    private Boolean liked;

    @Embeddable
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserMovieRatingId implements Serializable {
        private Long userId;
        private String movieId;
    }
}
