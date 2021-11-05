package com.example.moviequote;

public class Quote {

    private String quote;
    private String show;

    public Quote(String quote, String show) {
        this.quote = quote;
        this.show = show;
    }

    public String getQuote() {
        return quote;
    }

    public String getShow() {
        return show;
    }
}