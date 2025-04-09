package com.youssef.CineManager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO implements Serializable {
    private String title;
    private String year;
    private String imdbID;
    private String type;
    private String poster;
}
