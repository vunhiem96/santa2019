package com.example.santa2019.Model;

public class WallpaperFavorites {
    int id;
    int name;

    public WallpaperFavorites() {

    }

    public WallpaperFavorites(int id, int name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
}
