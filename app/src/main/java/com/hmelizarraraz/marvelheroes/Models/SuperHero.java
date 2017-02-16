package com.hmelizarraraz.marvelheroes.Models;

/**
 * Created by heriberto on 15/02/17.
 */

public class SuperHero {

    private int id;
    private String name;
    private String description;
    private Thumbnail thumbail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Thumbnail getThumbail() {
        return thumbail;
    }

    public void setThumbail(Thumbnail thumbail) {
        this.thumbail = thumbail;
    }
}
