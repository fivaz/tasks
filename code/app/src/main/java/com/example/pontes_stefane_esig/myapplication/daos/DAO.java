package com.example.pontes_stefane_esig.myapplication.daos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DAO extends SQLiteOpenHelper {

    static final String TB_USER_NAME = "user";
    static final String TB_PROJECT_NAME = "project";
    static final String TB_LISTT_NAME = "listt";
    static final String TB_CARD_NAME = "card";
    static final String TB_CURRENT_STATE_NAME = "current_state";

    private final String CREATE_TABLE_USER_STATEMENT =
            "CREATE TABLE " + TB_USER_NAME + " (" +
                    "id INTEGER PRIMARY KEY, " +
                    "first_name TEXT NOT NULL," +
                    "last_name TEXT NOT NULL," +
                    "email TEXT NOT NULL," +
                    "password TEXT NOT NULL" +
                    ")";
    private final String CREATE_TABLE_PROJECT_STATEMENT =
            "CREATE TABLE " + TB_PROJECT_NAME + " (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name TEXT NOT NULL," +
                    "start_at DATETIME NOT NULL," +
                    "end_at DATETIME NOT NULL," +
                    "isArchived TINYINT DEFAULT 0" +
                    ")";
    private final String CREATE_TABLE_LISTT_STATEMENT =
            "CREATE TABLE " + TB_LISTT_NAME + " (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "position INT NOT NULL, " +
                    "project_id INTEGER," +
                    "isDone TINYINT DEFAULT 0," +
                    "isArchived TINYINT DEFAULT 0," +
                    "FOREIGN KEY(project_id) REFERENCES " + TB_PROJECT_NAME + "(id)" +
                    ")";
    private final String CREATE_TABLE_CARD_STATEMENT =
            "CREATE TABLE " + TB_CARD_NAME + " (" +
                    "id INTEGER PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "points REAL NOT NULL, " +
                    "position INT NOT NULL, " +
                    "listt_id INTEGER, " +
                    "FOREIGN KEY(listt_id) REFERENCES " + TB_LISTT_NAME + "(id)" +
                    ")";
    private final String CREATE_TABLE_CURRENT_STATE_STATEMENT =
            "CREATE TABLE " + TB_CURRENT_STATE_NAME + " (" +
                    "id INTEGER PRIMARY KEY, " +
                    "points_done REAL NOT NULL, " +
                    "time_block INT NOT NULL, " +
                    "project_id INT, " +
                    "FOREIGN KEY(project_id) REFERENCES " + TB_PROJECT_NAME + "(id)" +
                    ")";

    private final String DROP_STATEMENT = "DROP TABLE IF EXISTS ";

    DAO(Context context) {
        super(context, "trello", null, 11);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Creating tables");
        db.execSQL(CREATE_TABLE_USER_STATEMENT);
        db.execSQL(CREATE_TABLE_PROJECT_STATEMENT);
        db.execSQL(CREATE_TABLE_CURRENT_STATE_STATEMENT);
        db.execSQL(CREATE_TABLE_LISTT_STATEMENT);
        db.execSQL(CREATE_TABLE_CARD_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        System.out.println("updating tables");
        dropAll(db);
        onCreate(db);
    }

    private void dropAll(SQLiteDatabase db) {
        db.execSQL(DROP_STATEMENT + TB_CARD_NAME);
        db.execSQL(DROP_STATEMENT + TB_LISTT_NAME);
        db.execSQL(DROP_STATEMENT + TB_CURRENT_STATE_NAME);
        db.execSQL(DROP_STATEMENT + TB_PROJECT_NAME);
        db.execSQL(DROP_STATEMENT + TB_USER_NAME);
    }
}
