package com.example.santa2019.Model;

import java.io.Serializable;

public class Image implements Serializable {
    int id;

    public Image(int id) {
        this.id = id;
    }
    public Image() {

    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
