package com.youssef.CineManager.model;

import com.youssef.CineManager.dto.MovieDTO;

import java.util.List;

public class SearchResult {
    private List<MovieDTO> search;
    private String totalResults;
    private String response;

    // Getters and setters
    public List<MovieDTO> getSearch() {
        return search;
    }

    public void setSearch(List<MovieDTO> search) {
        this.search = search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
