package com.youssef.CineManager.service;

import com.youssef.CineManager.dto.MovieRatingDTO;
import com.youssef.CineManager.exception.NotFoundResourceException;
import com.youssef.CineManager.mapper.UserRatingMapper;
import com.youssef.CineManager.model.Movie;
import com.youssef.CineManager.model.UserMovieRating;
import com.youssef.CineManager.repository.MovieRepository;
import com.youssef.CineManager.repository.UserMovieRatingRepository;
import com.youssef.CineManager.security.model.User;
import com.youssef.CineManager.security.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieRatingServiceImple  implements MovieRatingSericve{
    private final UserMovieRatingRepository userMovieRatingRepository;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;

    private final UserRatingMapper userRatingMapper;

    @Transactional
    @Override
    public MovieRatingDTO rateMovie(MovieRatingDTO request) {
        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new NotFoundResourceException("Movie not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new NotFoundResourceException("User not found"));

        UserMovieRating userMovieRating = userMovieRatingRepository.findByUserAndMovie(user, movie)
                .orElse(new UserMovieRating());

        if (userMovieRating.getId() == null) {
            userMovieRating.setId(new UserMovieRating.UserMovieRatingId(user.getId(), movie.getImdbID()));
            userMovieRating.setUser(user);
            userMovieRating.setMovie(movie);
        }

            userMovieRating.setRating(request.getRating());

            userMovieRating.setLiked(request.getLiked());


       MovieRatingDTO movieRatingDTO = userRatingMapper.toMovieRatingDTO(userMovieRatingRepository.save(userMovieRating));
        return movieRatingDTO;
    }

    @Override
    public MovieRatingDTO getUserMovieRating(Long userid, String movieId) {
        Optional<UserMovieRating> rating = userMovieRatingRepository.findByUserAndMovie(

                userRepository.findById(userid)
                        .orElseThrow(() -> new NotFoundResourceException("User not found"))
                ,
                movieRepository.findById(movieId)
                        .orElseThrow(() -> new NotFoundResourceException("Movie not found")));
        if (!rating.isPresent()) {
            throw new NotFoundResourceException("Rating not found");
        }
            return userRatingMapper.toMovieRatingDTO(rating.get());

    }
}
