package com.exercise.moviesquotes.service;

import com.exercise.moviesquotes.model.Movie;
import com.exercise.moviesquotes.model.Quote;
import com.exercise.moviesquotes.repository.MovieRepository;
import com.exercise.moviesquotes.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private QuoteRepository quoteRepository;

    /***MOVIE***/

    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> saveMovies(List<Movie> movies) {
        return movieRepository.saveAll(movies);
    }

    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieById(int id) {
        return movieRepository.findById(id).orElse(null);
    }

    public Movie getMovieByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    public String deleteMovie(int id) {
        movieRepository.deleteById(id);
        return "movie removed !! " + id;
    }

    public Movie updateMovie(Movie movie) {
        Movie existingMovie = movieRepository.findById(movie.getId()).orElse(null);
        existingMovie.setTitle(movie.getTitle());
        existingMovie.setYear(movie.getYear());
        return movieRepository.save(existingMovie);
    }

    /***QUOTE***/

    public Quote saveQuote(Quote quote) {
        return quoteRepository.save(quote);
    }

    public List<Quote> saveQuotes(List<Quote> quotes) {
        return quoteRepository.saveAll(quotes);
    }

    public List<Quote> getQuotes() {
        return quoteRepository.findAll();
    }

    public List<Quote> getQuotesByMovie(String movie) {
        for (Movie m : movieRepository.findAll()) {
            if(m.getTitle().equals(movie)) {
                return quoteRepository.findByMovie(m);
            }
        }
        return new ArrayList<Quote>();
    }

    public Quote getRandomQuoteByMovie(String movie) {
        for (Movie m : movieRepository.findAll()) {
            if(m.getTitle().equals(movie)) {
                List<Quote> movieQuotes = quoteRepository.findByMovie(m);
                int i = (int)((Math.random()*movieQuotes.size()));
                return movieQuotes.get(i);
            }
        }
        return new Quote();
    }

    public String deleteQuote(int id) {
        quoteRepository.deleteById(id);
        return "quote removed !! " + id;
    }

    public Quote updateQuote(Quote quote) {
        Quote existingQuote = quoteRepository.findById(quote.getId()).orElse(null);
        existingQuote.setQuote(quote.getQuote());
        existingQuote.setMovie(quote.getMovie());
        return quoteRepository.save(existingQuote);
    }
}