package com.example.pontes_stefane_esig.myapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

abstract class DAO extends SQLiteOpenHelper {

    DAO(Context context, int version) {
        super(context, "trello", null, version);
    }
}
