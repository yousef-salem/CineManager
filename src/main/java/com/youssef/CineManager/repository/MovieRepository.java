package com.youssef.CineManager.repository;

import com.youssef.CineManager.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface MovieRepository extends JpaRepository<Movie, String> {

    Page<Movie> findAll(Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE m.year = :year AND LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Movie> findByYearAndTitleContainingIgnoreCase(@Param("year") String year, @Param("title") String title, Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE LOWER(m.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    Page<Movie> findByTitleContainingIgnoreCase(@Param("title") String title, Pageable pageable);

    @Query("SELECT m FROM Movie m WHERE m.year = :year")
    Page<Movie> findByYear(@Param("year") String year, Pageable pageable);

    List<Movie> findByImdbIDIn(List<String> ids);
}