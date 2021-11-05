package com.example.moviequote;

import java.util.ArrayList;
import java.lang.Math;

public class Show {

    private final int id;
    private final String title;
    private ArrayList<Quote> quotes;
    private final int noQuotes;

    public Show(int id, String title, ArrayList<Quote> quotes) {
        this.id = id;
        this.title = title;
        this.quotes = quotes;
        this.noQuotes = quotes.size();
    }

    public Quote getRandomQuote() {
        int i = (int)((Math.random()*noQuotes));
        return quotes.get(i);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Quote> getQuotes() {
        return quotes;
    }

    public int getNoQuotes() {
        return noQuotes;
    }
}