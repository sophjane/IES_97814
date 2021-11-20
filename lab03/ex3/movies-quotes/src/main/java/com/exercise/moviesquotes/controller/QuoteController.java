package com.exercise.moviesquotes.controller;

import com.exercise.moviesquotes.model.Quote;
import com.exercise.moviesquotes.model.Movie;
import com.exercise.moviesquotes.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class QuoteController {

    @Autowired
    private MovieService service;

    @PostMapping("/addQuote")
    public Quote addQuote(@RequestBody Quote quote) { return service.saveQuote(quote); }

    @PostMapping("/addQuotes")
    public List<Quote> addQuotes(@RequestBody List<Quote> quotes) { return service.saveQuotes(quotes); }

    @GetMapping("/quotes")
    public List<Quote> findAllQuotes() {
        return service.getQuotes();
    }

    @GetMapping("/quotes/{movie}")
    public List<Quote> findQuotesByMovie(@PathVariable String movie) { return service.getQuotesByMovie(movie); }

    @PutMapping("/updateQuote")
    public Quote updateQuote(@RequestBody Quote quote) {
        return service.updateQuote(quote);
    }

    @DeleteMapping("/deleteQuote/{id}")
    public String deleteQuote(@PathVariable int id) {
        return service.deleteQuote(id);
    }

    @GetMapping("/randomQuote/{movie}")
    public Quote randomQuote(@PathVariable String movie) { return service.getRandomQuoteByMovie(movie); }

}