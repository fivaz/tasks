package com.example.pontes_stefane_esig.myapplication.model;

public class Card extends Model {

    private String name;
    private double points;
    private long list_id;

    public Card() {
    }

    public Card(String name, double points) {
        this.name = name;
        this.points = points;
    }

    public Card(long id, String name, double points, long list_id) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.list_id = list_id;
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

    public long getList_id() {
        return list_id;
    }

    public void setList_id(long list_id) {
        this.list_id = list_id;
    }

    @Override
    public String toString() {
        return name + " - " + points;
    }
}
