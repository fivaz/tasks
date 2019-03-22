package com.example.pontes_stefane_esig.myapplication.model;

import java.util.ArrayList;
import java.util.List;

public class Project extends Model {

    private String name;
    private List<Listt> listts;

    public Project() {
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

    public void addList(Listt listt) {
        this.listts.add(listt);
    }

    @Override
    public String toString() {
        return name;
    }
}
