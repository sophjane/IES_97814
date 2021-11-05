package com.example.moviequote;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.lang.Math;


@RestController
public class MovieQuoteController {

    ShowService shows = new ShowService();

    @GetMapping("/api/quote")
    public Quote getRandomQuote() {
        int i = (int)((Math.random()*shows.getSize()));
        return shows.getElem(i).getRandomQuote();
    }

    @GetMapping("/api/shows")
    public ArrayList<Show> getShows() {
        return shows.getShows();
    }

    @GetMapping("/api/quotes")
    public Quote getRandomQuoteFromMovie(@RequestParam(value = "show", defaultValue = "0") int show) {
        return shows.getElem(show).getRandomQuote();
    }
}