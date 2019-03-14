package com.example.pontes_stefane_esig.myapplication.model;

import java.io.Serializable;

public class Project implements Serializable {

    private long id;
    private String name;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
