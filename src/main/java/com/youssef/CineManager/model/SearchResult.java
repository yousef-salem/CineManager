package com.youssef.CineManager.model;

import com.youssef.CineManager.dto.MovieDTO;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class SearchResult implements Serializable {
    private List<MovieDTO> search;
    private String totalResults;
    private String response;

}
