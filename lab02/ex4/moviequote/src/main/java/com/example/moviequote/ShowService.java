package com.example.moviequote;

import java.util.ArrayList;
import java.util.Arrays;

public class ShowService {

    public ArrayList<Show> shows = new ArrayList<Show>();

    public ShowService() {
        this.shows.add(new Show(0, "The Good Doctor",
                new ArrayList<Quote>(Arrays.asList(new Quote("There’s a cure for youth and stupidity, time and experience.", "The Good Doctor"),
                new Quote("If you’re a good person, every now and again, you’re gonna feel like an idiot.", "The Good Doctor"),
                new Quote("No one is like everyone. We’re all unique. But we’re also the same.", "The Good Doctor")))));
        this.shows.add(new Show(1, "The Book Thief",
                new ArrayList<Quote>(Arrays.asList(new Quote("In my job, I'm always seeing humans at their best, and their worst. I see their ugliness, and their beauty. And I wonder how the same thing can be both.", "The Book Thief"),
                new Quote("Memory is the scribe of the soul.", "The Book Thief"),
                new Quote("Write. In my religion we're taught that every living thing, every leaf, every bird, is only alive because it contains the secretword for life. That's the only difference between us and a lump of clay. A word. Words are life, Liesel.", "The Book Thief")))));
        this.shows.add(new Show(2, "The Office",
                new ArrayList<Quote>(Arrays.asList(new Quote("If I don’t have some cake soon, I might die.", "The Office"),
                new Quote("There’s a lot of beauty in ordinary things. Isn’t that kind of the point?", "The Office"),
                new Quote("And I knew exactly what to do. But in a much more real sense, I had no idea what to do.", "The Office")))));
        this.shows.add(new Show(3, "Friends",
                new ArrayList<Quote>(Arrays.asList(new Quote("They don’t know that we know they know we know.", "Friends"),
                new Quote("Come on, Ross, you’re a paleontologist. Dig a little deeper.", "Friends"),
                new Quote("I wish I could, but I don’t want to.", "Friends")))));
    }

    public ArrayList<Show> getShows() {
        return shows;
    }

    public Show getElem(int i) {
        return shows.get(i);
    }

    public int getSize() {
        return shows.size();
    }
}