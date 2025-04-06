package com.youssef.CineManager.service;

import com.youssef.CineManager.dto.MovieDTO;
import com.youssef.CineManager.model.Movie;
import com.youssef.CineManager.model.SearchResult;
import com.youssef.CineManager.util.MovieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class OmdbServiceImpl implements OmdbService {

    @Value("${omdb.api.key}")
    private String apiKey;

    @Value("${omdb.api.url}")
    private String apiUrl;

    @Autowired
    MovieUtil movieUtil;

    RestTemplate restTemplate;

    public OmdbServiceImpl() {
        this.restTemplate = new RestTemplate();
    }


    public SearchResult searchMovies(String query, Integer page, Integer year) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("type", "movie")
                .queryParam("s", query)
                .queryParam("page", page)
                .queryParam("apikey", apiKey);

        if (year != null) {
            uriBuilder.queryParam("y", year);
        }

        String url = uriBuilder.toUriString();

        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        Map<String, Object> searchData = response.getBody();

       return movieUtil.extractSearchResultFromResponse(searchData);
    }

    @Override
    public Movie getMovieDetails(String imdbId) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("i", imdbId)
                .queryParam("apikey", apiKey);

        String url = uriBuilder.toUriString();
        ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
        Map<String, Object> movieData = response.getBody();


        return movieUtil.extractMovieFromResponse(movieData);
    }
}
