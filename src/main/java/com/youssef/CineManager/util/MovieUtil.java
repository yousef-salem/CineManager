package com.youssef.CineManager.util;



import com.youssef.CineManager.dto.MovieDTO;
import com.youssef.CineManager.exception.NotFoundResourceException;
import com.youssef.CineManager.model.Movie;
import com.youssef.CineManager.model.Rating;
import com.youssef.CineManager.model.SearchResult;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class MovieUtil {

    @Value("${omdb.api.key}")
    private String apiKey;

    @Value("${omdb.api.url}")
    private String apiUrl;



    public Movie extractMovieFromResponse(Map<String, Object> movieData) {

        if (movieData != null && "True".equals(movieData.get("Response"))) {
            Movie movie = new Movie();
            movie.setImdbID((String) movieData.get("imdbID"));
            movie.setTitle((String) movieData.get("Title"));
            movie.setYear((String) movieData.get("Year"));
            movie.setRated((String) movieData.get("Rated"));
            movie.setReleased((String) movieData.get("Released"));
            movie.setRuntime((String) movieData.get("Runtime"));
            movie.setGenre((String) movieData.get("Genre"));
            movie.setDirector((String) movieData.get("Director"));
            movie.setWriter((String) movieData.get("Writer"));
            movie.setActors((String) movieData.get("Actors"));
            movie.setPlot((String) movieData.get("Plot"));
            movie.setLanguage((String) movieData.get("Language"));
            movie.setCountry((String) movieData.get("Country"));
            movie.setAwards((String) movieData.get("Awards"));
            movie.setPoster((String) movieData.get("Poster"));
            movie.setMetascore((String) movieData.get("Metascore"));
            movie.setImdbRating((String) movieData.get("imdbRating"));
            movie.setImdbVotes((String) movieData.get("imdbVotes"));
            movie.setType((String) movieData.get("Type"));
            movie.setDvd((String) movieData.get("DVD"));
            movie.setBoxOffice((String) movieData.get("BoxOffice"));
            movie.setProduction((String) movieData.get("Production"));
            movie.setWebsite((String) movieData.get("Website"));
            movie.setResponse((String) movieData.get("Response"));


            List<Rating> ratings = new ArrayList<>();
            if (movieData.containsKey("Ratings") && movieData.get("Ratings") instanceof List) {
                List<Map<String, String>> ratingsData = (List<Map<String, String>>) movieData.get("Ratings");
                for (Map<String, String> ratingData : ratingsData) {
                    Rating rating = new Rating();
                    rating.setSource(ratingData.get("Source"));
                    rating.setValue(ratingData.get("Value"));
                    ratings.add(rating);
                }
            }
            movie.setRatings(ratings);

            return movie;
        }
        String errorMessage = (String) movieData.get("Error");
        throw new NotFoundResourceException(errorMessage != null ? errorMessage : "Resource not found");
    }

    public SearchResult extractSearchResultFromResponse(Map<String, Object> searchData) {
        if (searchData != null && "True".equals(searchData.get("Response"))) {
            SearchResult result = new SearchResult();


            result.setTotalResults((String) searchData.get("totalResults"));
            result.setResponse((String) searchData.get("Response"));


            List<MovieDTO> movies = new ArrayList<>();
            if (searchData.containsKey("Search") && searchData.get("Search") instanceof List) {
                List<Map<String, String>> moviesData = (List<Map<String, String>>) searchData.get("Search");

                for (Map<String, String> movieData : moviesData) {
                    MovieDTO movie = new MovieDTO();
                    movie.setTitle(movieData.get("Title"));
                    movie.setYear(movieData.get("Year"));
                    movie.setImdbID(movieData.get("imdbID"));
                    movie.setType(movieData.get("Type"));
                    movie.setPoster(movieData.get("Poster"));

                    movies.add(movie);
                }
            }

            result.setSearch(movies);
            return result;
        }
        String errorMessage = (String) searchData.get("Error");
        throw new NotFoundResourceException(errorMessage != null ? errorMessage : "Resource not found");
    }
}