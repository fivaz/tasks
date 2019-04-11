package com.example.pontes_stefane_esig.myapplication.converters;

import com.example.pontes_stefane_esig.myapplication.models.All;
import com.example.pontes_stefane_esig.myapplication.models.User;
import com.google.gson.Gson;

import java.util.List;

public class ObjectInJSON {

    public static String convert(List<User> users) {
        All all = new All(users);

        System.err.println(all);

        return new Gson().toJson(all);
    }
}
