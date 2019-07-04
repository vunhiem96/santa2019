package com.example.santa2019.Model;

public class Ring {
    public String id;
    public String name, drution;

    public Ring(String imageId, String name, String drution) {
        this.id = id;
        this.name = name;
        this.drution = drution;
    }

    public Ring(String id) {
        this.id = id;
    }

    public Ring() {
    }

    public String id() {
        return id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrution() {
        return drution;
    }

    public void setDrution(String drution) {
        this.drution = drution;
    }
}


