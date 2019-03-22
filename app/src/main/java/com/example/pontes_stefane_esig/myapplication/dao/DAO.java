package com.example.pontes_stefane_esig.myapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

abstract class DAO extends SQLiteOpenHelper {

    DAO(Context context) {
        super(context, "trello", null, 1);
    }
}
