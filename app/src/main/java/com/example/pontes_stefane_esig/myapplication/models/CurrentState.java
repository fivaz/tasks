package com.example.pontes_stefane_esig.myapplication.models;

public class CurrentState  extends Model {

    private double pointsDone;
    private int timePart;
    private long project_id;

    public CurrentState(int id, double pointsDone, int timePart, long project_id) {
        this.id = id;
        this.pointsDone = pointsDone;
        this.timePart = timePart;
        this.project_id = project_id;
    }

    public CurrentState(double pointsDone, int timePart, long project_id) {
        this.pointsDone = pointsDone;
        this.timePart = timePart;
        this.project_id = project_id;
    }

    public double getPointsDone() {
        return pointsDone;
    }

    public void setPointsDone(double pointsDone) {
        this.pointsDone = pointsDone;
    }

    public int getTimePart() {
        return timePart;
    }

    public void setTimePart(int timePart) {
        this.timePart = timePart;
    }

    public long getProject_id() {
        return project_id;
    }

    public void setProject_id(long project_id) {
        this.project_id = project_id;
    }

    @Override
    public String toString() {
        return "CurrentState{" +
                "pointsDone=" + pointsDone +
                ", timePart=" + timePart +
                ", project_id=" + project_id +
                ", id=" + id +
                '}';
    }
}
