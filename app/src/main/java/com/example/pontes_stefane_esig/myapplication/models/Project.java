package com.example.pontes_stefane_esig.myapplication.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Project extends Model {

    private String name;
    private Date start_at;
    private Date end_at;
    private List<Listt> listts;

    public Project() {
        listts = new ArrayList<>();
    }

    public Project(long id, String name, Date start_at, Date end_at) {
        this.id = id;
        this.name = name;
        this.start_at = start_at;
        this.end_at = end_at;
        listts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart_at() {
        return start_at;
    }

    public void setStart_at(Date start_at) {
        this.start_at = start_at;
    }

    public Date getEnd_at() {
        return end_at;
    }

    public void setEnd_at(Date end_at) {
        this.end_at = end_at;
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

//    @Override
//    public String toString() {
//        return "Project{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", start_at=" + start_at +
//                ", end_at=" + end_at +
//                ", listts=" + listts +
//                '}';
//    }

        @Override
    public String toString() {
        return name;
    }
}
