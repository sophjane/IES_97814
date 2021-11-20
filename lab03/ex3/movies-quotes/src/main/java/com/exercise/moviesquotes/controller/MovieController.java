package com.exercise.moviesquotes.controller;

import com.exercise.moviesquotes.model.Movie;
import com.exercise.moviesquotes.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class MovieController {

    @Autowired
    private MovieService service;

    /***MOVIE***/
    @PostMapping("/addMovie")
    public Movie addMovie(@RequestBody Movie movie) {
        return service.saveMovie(movie);
    }

    @PostMapping("/addMovies")
    public List<Movie> addMovies(@RequestBody List<Movie> movies) {
        return service.saveMovies(movies);
    }

    @GetMapping("/movies")
    public List<Movie> findAllMovies() { return service.getMovies(); }

    @GetMapping("/movieById/{id}")
    public Movie findMovieById(@PathVariable int id) { return service.getMovieById(id); }

    @GetMapping("/movieByTitle/{title}")
    public Movie findMovieByTitle(@PathVariable String title) { return service.getMovieByTitle(title); }

    @PutMapping("/updateMovie")
    public Movie updateMovie(@RequestBody Movie movie) {
        return service.updateMovie(movie);
    }

    @DeleteMapping("/deleteMovie/{id}")
    public String deleteMovie(@PathVariable int id) {
        return service.deleteMovie(id);
    }

}