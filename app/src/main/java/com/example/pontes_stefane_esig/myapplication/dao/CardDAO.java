package com.example.pontes_stefane_esig.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.pontes_stefane_esig.myapplication.model.Card;
import com.example.pontes_stefane_esig.myapplication.model.Project;

import java.util.ArrayList;
import java.util.List;

public class CardDAO extends SQLiteOpenHelper {

    private final String TABLE = "card";

    public CardDAO(Context context) {
        super(context, "project", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " (" +
                "id INTEGER PRIMARY KEY, " +
                "name TEXT, " +
                "points REAL, " +
                "project_id INTEGER, " +
                "FOREIGN KEY(project_id) REFERENCES projet(id)" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE;
        db.execSQL(sql);
        onCreate(db);
    }

    public List<Card> getAll(Project project) {
        String sql = "SELECT * FROM " + TABLE + " WHERE project_id = " + project.getId();
        SQLiteDatabase db = getReadableDatabase();
//        String[] args = new String[]{String.valueOf(project.getId())};
//        Cursor c = db.rawQuery(sql, args);
        Cursor c = db.rawQuery(sql, null);

        List<Card> cards = new ArrayList<>();
        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("id"));
            String name = c.getString(c.getColumnIndex("name"));
            double points = c.getDouble(c.getColumnIndex("points"));
            long project_id = c.getLong(c.getColumnIndex("project_id"));

            Card card = new Card(id, name, points, project_id);
            cards.add(card);
        }
        c.close();
        return cards;
    }

    public void insert(Card card) {
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(TABLE, null, getValues(card));
        card.setId(id);
    }

    @NonNull
    private ContentValues getValues(Card card) {
        ContentValues data = new ContentValues();
        data.put("name", card.getName());
        data.put("points", card.getPoints());
        data.put("project_id", card.getProject_id());
        return data;
    }

    public void delete(Card card) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, "id = ?", getPK(card));
    }

    @NonNull
    private String[] getPK(Card card) {
        return new String[]{String.valueOf(card.getId())};
    }

    public void update(Card card) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE, getValues(card), "id = ?", getPK(card));
    }
}
