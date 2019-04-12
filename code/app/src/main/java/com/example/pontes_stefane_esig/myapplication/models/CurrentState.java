package com.example.pontes_stefane_esig.myapplication.models;

public class CurrentState  extends Model {

    private double pointsDone;
    private int timeBlock;
    private long projectId;

    public CurrentState(long id, double pointsDone, int timeBlock, long projectId) {
        this.id = id;
        this.pointsDone = pointsDone;
        this.timeBlock = timeBlock;
        this.projectId = projectId;
    }

    public CurrentState(double pointsDone, int timeBlock, long projectId) {
        this.pointsDone = pointsDone;
        this.timeBlock = timeBlock;
        this.projectId = projectId;
    }

    public double getPointsDone() {
        return pointsDone;
    }

    public void setPointsDone(double pointsDone) {
        this.pointsDone = pointsDone;
    }

    public int getTimeBlock() {
        return timeBlock;
    }

    public void setTimeBlock(int timeBlock) {
        this.timeBlock = timeBlock;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "CurrentState{" +
                "pointsDone=" + pointsDone +
                ", timeBlock=" + timeBlock +
                ", projectId=" + projectId +
                ", id=" + id +
                '}';
    }
}
