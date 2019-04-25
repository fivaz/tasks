package com.example.pontes_stefane_esig.myapplication.models;

public class Card extends Model {

    private String name;
    private double points;
    private int position;
    private long listtId;

    public Card(){

    }

    public Card(String name, double points) {
        this.name = name;
        this.points = points;
    }

    public Card(long id, String name, double points, int position, long listtId) {
        this.id = id;
        this.name = name;
        this.points = points;
        this.position = position;
        this.listtId = listtId;
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

    public long getListtId() {
        return listtId;
    }

    public void setListtId(long listtId) {
        this.listtId = listtId;
    }

    @Override
    public String toString() {
        return "Card{" +
                "name='" + name + '\'' +
                ", points=" + points +
                ", position=" + position +
                ", listtId=" + listtId +
                ", id=" + id +
                '}';
    }
}
