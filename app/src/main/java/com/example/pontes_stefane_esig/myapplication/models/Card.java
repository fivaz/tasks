package com.example.pontes_stefane_esig.myapplication.models;

public class Card extends Model {

    private String name;
    private double points;
    private int position;
    private long listt_id;

    public Card(String name, double points) {
        this.name = name;
        this.points = points;
    }

    public Card(long id, String name, double points, int position, long listt_id) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.position = position;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public long getListt_id() {
        return listt_id;
    }

    public void setListt_id(long listt_id) {
        this.listt_id = listt_id;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", position=" + position +
                ", listt_id=" + listt_id +
                ", id=" + id +
                '}';
    }
}
