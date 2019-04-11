package com.example.pontes_stefane_esig.myapplication.converters;

import com.example.pontes_stefane_esig.myapplication.models.All;
import com.example.pontes_stefane_esig.myapplication.models.User;
import com.google.gson.Gson;

import java.util.List;

public class JSONinSQL {

    public static List<User> convertAll(String allJSON) {
        All all = new Gson().fromJson(allJSON, All.class);
        return all.getUsers();
    }
}
