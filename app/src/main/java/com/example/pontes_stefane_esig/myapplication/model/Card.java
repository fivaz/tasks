package com.example.pontes_stefane_esig.myapplication.model;

import java.io.Serializable;

public class Card implements Serializable {

    private long id;
    private String name;
    private double points;
    private long project_id;

    public Card() {
    }

    public Card(String name, double points) {
        this.name = name;
        this.points = points;
    }

    public Card(long id, String name, double points, long project_id) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.project_id = project_id;
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

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public long getProject_id() {
        return project_id;
    }

    public void setProject_id(long project_id) {
        this.project_id = project_id;
    }

    @Override
    public String toString() {
        return name + " - " + points;
    }
}
