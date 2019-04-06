package com.example.pontes_stefane_esig.myapplication.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.pontes_stefane_esig.myapplication.models.Project;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO use prepared statements
public class ProjectDAO extends DAO {

    private final String SELECT_STATEMENT = "SELECT * FROM " + TB_PROJECT_NAME;
    private final String SELECT_WHERE = SELECT_STATEMENT + " WHERE id = %d";
    private final String SELECT_ALL = SELECT_STATEMENT + " WHERE isArchived = 0";

    public ProjectDAO(Context context) {
        super(context);
    }

    public Project get(long id) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format(SELECT_WHERE, id);
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        Project project = buildProject(cursor);
        cursor.close();
        return project;
    }

    public List<Project> getAll() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(SELECT_ALL, null);
        List<Project> projects = new ArrayList<>();
        while (cursor.moveToNext())
            projects.add(buildProject(cursor));
        cursor.close();
        return projects;
    }

    @NonNull
    private Project buildProject(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        long start_at_millliseconds = cursor.getLong(cursor.getColumnIndex("start_at"));
        Date start_at = new Date(start_at_millliseconds);
        long end_at_millliseconds = cursor.getLong(cursor.getColumnIndex("end_at"));
        Date end_at = new Date(end_at_millliseconds);

        return new Project(id, name, start_at, end_at);
    }

    public void insert(Project project) {
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(DAO.TB_PROJECT_NAME, null, getValues(project));
        project.setId(id);
    }

    @NonNull
    private ContentValues getValues(Project project) {
        ContentValues data = new ContentValues();
        data.put("name", project.getName());
        data.put("start_at", project.getStart_at().getTime());
        data.put("end_at", project.getEnd_at().getTime());
        data.put("isArchived", project.isArchived());
        return data;
    }

    public void delete(Project project) {
        SQLiteDatabase db = getWritableDatabase();
        project.setArchived(true);
        db.update(DAO.TB_PROJECT_NAME, getValues(project),"id = ?", getPK(project));
    }

    @NonNull
    private String[] getPK(Project project) {
        return new String[]{String.valueOf(project.getId())};
    }

    public void update(Project project) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(DAO.TB_PROJECT_NAME, getValues(project), "id = ?", getPK(project));
    }
}
