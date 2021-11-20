package com.exercise.moviesquotes.repository;

import com.exercise.moviesquotes.model.Movie;
import com.exercise.moviesquotes.model.Quote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuoteRepository extends JpaRepository<Quote,Integer> {
    List<Quote> findByMovie(Movie movie);
}