package com.example.pontes_stefane_esig.myapplication.models;

import java.util.ArrayList;
import java.util.List;

public class All {
    private List<User> users;

    public All(List<User> users) {
        this.users = users;
    }

    public All() {
        users = new ArrayList<>();
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "All{" +
                "users=" + users +
                '}';
    }
}
