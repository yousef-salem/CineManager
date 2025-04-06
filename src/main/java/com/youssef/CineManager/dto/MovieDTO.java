package com.youssef.CineManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private String title;
    private String year;
    private String imdbID;
    private String type;
    private String poster;
}
