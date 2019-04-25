package com.example.pontes_stefane_esig.myapplication.models;


public class ProjectsUsersMap extends Model{

    private long projectId;
    private long userId;

    public ProjectsUsersMap() {
    }

    public ProjectsUsersMap(long id, long projectId, long userId) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
