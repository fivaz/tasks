package com.example.pontes_stefane_esig.myapplication.models;

import com.example.pontes_stefane_esig.myapplication.converters.DateConverter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Project extends Model {

    private String name;
    private String startAt;
    private String endAt;
    private boolean isArchived;
    private long userId;
    private List<Listt> listts;
    private List<CurrentState> currentStates;

    public Project() {
        isArchived = false;
        listts = new ArrayList<>();
        currentStates = new ArrayList<>();
    }

    public Project(long id, String name, String startAt, String endAt, long userId) {
        this.id = id;
        this.name = name;
        this.startAt = startAt;
        this.endAt = endAt;
        this.isArchived = false;
        this.userId = userId;
        listts = new ArrayList<>();
        currentStates = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        this.isArchived = archived;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<Listt> getListts() {
        return listts;
    }

    public void setListts(List<Listt> listts) {
        this.listts = listts;
    }

    public List<CurrentState> getCurrentStates() {
        return currentStates;
    }

    public void setCurrentStates(List<CurrentState> currentStates) {
        this.currentStates = currentStates;
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
        long startAtLong = DateConverter.toLong(startAt);
        long endAtLong = DateConverter.toLong(endAt);
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
                ", startAt='" + startAt + '\'' +
                ", endAt='" + endAt + '\'' +
                ", isArchived=" + isArchived +
                ", userId=" + userId +
                ", listts=" + listts +
                ", currentStates=" + currentStates +
                '}';
    }
}
