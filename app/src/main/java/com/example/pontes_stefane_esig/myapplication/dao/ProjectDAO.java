package com.example.pontes_stefane_esig.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;

import com.example.pontes_stefane_esig.myapplication.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ProjectDAO extends SQLiteOpenHelper {

    private final String TABLE = "project";

    public ProjectDAO(Context context) {
        super(context, "project", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " (id INTEGER PRIMARY KEY, name TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE;
        db.execSQL(sql);
        onCreate(db);
    }

    public List<Project> getAll() {
        String sql = "SELECT * FROM " + TABLE;
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        List<Project> projects = new ArrayList<>();
        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("id"));
            String name = c.getString(c.getColumnIndex("name"));
            Project project = new Project();
            project.setId(id);
            project.setName(name);
            projects.add(project);
        }
        c.close();
        return projects;
    }

    public void insert(Project project) {
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(TABLE, null, getValues(project));
        project.setId(id);
    }

    @NonNull
    private ContentValues getValues(Project project) {
        ContentValues data = new ContentValues();
        data.put("name", project.getName());
        return data;
    }

    public void delete(Project project) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, "id = ?", getPK(project));
    }

    @NonNull
    private String[] getPK(Project project) {
        return new String[]{String.valueOf(project.getId())};
    }

    public void update(Project project) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE, getValues(project), "id = ?", getPK(project));
    }
}
