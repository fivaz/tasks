package com.example.pontes_stefane_esig.myapplication.model;

import java.util.List;

public class Project extends Model{

    private String name;
    private List<Listt> lists;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Listt> getLists() {
        return lists;
    }

    public void setLists(List<Listt> lists) {
        this.lists = lists;
    }

    public void addList(Listt listt) {
        this.lists.add(listt);
    }

    public double getTotal() {
        double total = 0;
        for (Listt listt : lists)
            total += listt.getTotal();
        return total;
    }

    @Override
    public String toString() {
        return name;
    }
}
