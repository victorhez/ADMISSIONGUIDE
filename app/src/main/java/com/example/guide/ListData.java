package com.example.guide;

public class ListData {
    private String id;
    private String name;
    private String movie;

    public  ListData(){


    }

    public ListData(String id, String name, String movie) {
        this.id = id;
        this.name = name;
        this.movie = movie;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMovie() {
        return movie;
    }
}

