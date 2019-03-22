package com.example.pontes_stefane_esig.myapplication.model;

public class Card extends Model{

    private String name;
    private double points;
    private long listt_id;

    public Card(long id, String name, double points, long listt_id) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.listt_id = listt_id;
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

    public long getListt_id() {
        return listt_id;
    }

    public void setListt_id(long listt_id) {
        this.listt_id = listt_id;
    }
}
