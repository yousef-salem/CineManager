package com.youssef.CineManager.repository;

import com.youssef.CineManager.model.Movie;
import com.youssef.CineManager.model.UserMovieRating;
import com.youssef.CineManager.security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMovieRatingRepository extends JpaRepository<UserMovieRating, UserMovieRating.UserMovieRatingId> {
    Optional<UserMovieRating> findByUserAndMovie(User user, Movie movie);
}