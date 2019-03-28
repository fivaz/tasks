package com.example.pontes_stefane_esig.myapplication.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.pontes_stefane_esig.myapplication.models.CurrentState;
import com.example.pontes_stefane_esig.myapplication.models.Project;

import java.util.ArrayList;
import java.util.List;

public class CurrentStateDAO extends DAO {

    private final String TABLE_NAME = TB_CURRENT_STATE_NAME;

    public CurrentStateDAO(Context context) {
        super(context);
    }

    //TODO use prepared statements
    public List<CurrentState> getAll(Project project) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE project_id = " + project.getId();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<CurrentState> currentStates = new ArrayList<>();
        while (cursor.moveToNext())
            currentStates.add(buildCurrentState(cursor));
        cursor.close();
        return currentStates;
    }

    @NonNull
    private CurrentState buildCurrentState(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        double points_done = cursor.getDouble(cursor.getColumnIndex("points_done"));
        int time_part = cursor.getInt(cursor.getColumnIndex("time_part"));
        long project_id = cursor.getLong(cursor.getColumnIndex("project_id"));

        return new CurrentState(id, points_done, time_part, project_id);
    }

    //TODO Put this in the class DAO
    public void insert(CurrentState currentState) {
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(TABLE_NAME, null, getValues(currentState));
        currentState.setId(id);
    }

    @NonNull
    private ContentValues getValues(CurrentState currentState) {
        ContentValues data = new ContentValues();
        data.put("points_done", currentState.getPointsDone());
        data.put("time_part", currentState.getTimePart());
        data.put("project_id", currentState.getProject_id());
        return data;
    }

    //TODO Put this in the class DAO
    public void delete(CurrentState currentState) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", getPK(currentState));
    }

    //TODO Put this in the class DAO
    @NonNull
    private String[] getPK(CurrentState currentState) {
        return new String[]{String.valueOf(currentState.getId())};
    }

    //TODO Put this in the class DAO
    public void update(CurrentState currentState) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, getValues(currentState), "id = ?", getPK(currentState));
    }
}
