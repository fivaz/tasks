package com.example.pontes_stefane_esig.myapplication.models;

import java.util.ArrayList;
import java.util.List;

public class Project extends Model {

    private String name;
    private List<Listt> listts;

    public Project() {
        listts = new ArrayList<>();
    }

    public Project(long id, String name) {
        this.id = id;
        this.name = name;
        listts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Listt> getListts() {
        return listts;
    }

    public void setListts(List<Listt> listts) {
        this.listts = listts;
    }

    public double getTotal() {
        double total = 0;
        for (Listt listt : listts)
            total += listt.getTotal();
        return total;
    }

    @Override
    public String toString() {
        return name;
    }
}
