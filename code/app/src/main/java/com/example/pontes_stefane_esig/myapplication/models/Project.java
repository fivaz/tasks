package com.example.pontes_stefane_esig.myapplication.models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class Project extends Model {

    private String name;
    private Date start_at;
    private Date end_at;
    private boolean isArchived;
    private List<Listt> listts;

    public Project() {
        isArchived = false;
        listts = new ArrayList<>();
    }

    public Project(long id, String name, Date start_at, Date end_at) {
        this.id = id;
        this.name = name;
        this.start_at = start_at;
        this.end_at = end_at;
        this.isArchived = false;
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

    public String getStartAtToString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(start_at);
    }

    public void setStart_at(Date start_at) {
        this.start_at = start_at;
    }

    public Date getEnd_at() {
        return end_at;
    }

    public String getEndAtToString(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(end_at);
    }

    public void setEnd_at(Date end_at) {
        this.end_at = end_at;
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
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

    @Override
    public String toString() {
        return name;
    }

    public String formatDate(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.FRENCH);
        return dateFormat.format(date);
    }

    public int getCurrentTimeBlock() {
        long startAtLong = start_at.getTime();
        long endAtLong = end_at.getTime();
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

}
