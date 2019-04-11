package com.example.pontes_stefane_esig.myapplication.models;

import com.example.pontes_stefane_esig.myapplication.converters.DateConverter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Project extends Model {

    private String name;
    private String start_at;
    private String end_at;
    private boolean isArchived;
    private long user_id;
    private List<Listt> listts;

    public Project() {
        isArchived = false;
        listts = new ArrayList<>();
    }

    public Project(long id, String name, String start_at, String end_at, long user_id) {
        this.id = id;
        this.name = name;
        this.start_at = start_at;
        this.end_at = end_at;
        this.isArchived = false;
        this.user_id = user_id;
        listts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_at() {
        return start_at;
    }

    public void setStart_at(String start_at) {
        this.start_at = start_at;
    }

    public String getEnd_at() {
        return end_at;
    }

    public void setEnd_at(String end_at) {
        this.end_at = end_at;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
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

    public double getPointsDone() {
        for (Listt listt : listts) {
            if (listt.isDone()) {
                return listt.getTotal();
            }
        }
        return 0;
    }

    public int getCurrentTimeBlock() {
        long startAtLong = DateConverter.toLong(start_at);
        long endAtLong = DateConverter.toLong(end_at);
        long projectTimeLong = (endAtLong - startAtLong);
        long timeBlockLong = projectTimeLong / 5;
        long currentPartTimeLong = startAtLong;
        long nowLong = (Calendar.getInstance().getTime()).getTime();
        int i = 0;

        while (nowLong > currentPartTimeLong && i < 5) {
            currentPartTimeLong += timeBlockLong;
            i++;
        }
        return i;
    }

    public CurrentState buildNewCurrentState() {
        return new CurrentState(getPointsDone(), getCurrentTimeBlock(), getId());
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", start_at='" + start_at + '\'' +
                ", end_at='" + end_at + '\'' +
                ", isArchived=" + isArchived +
                ", user_id=" + user_id +
                ", listts=" + listts +
                '}';
    }
}
