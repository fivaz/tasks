package com.example.pontes_stefane_esig.myapplication.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.pontes_stefane_esig.myapplication.models.Listt;
import com.example.pontes_stefane_esig.myapplication.models.Project;

import java.util.ArrayList;
import java.util.List;

public class ListtDAO extends DAO {

    private final String TABLE_NAME = TB_LISTT_NAME;

    //TODO put this in the class DAO as protected
    private final String SELECT_STATEMENT = "SELECT * FROM " + TABLE_NAME;
    private final String SELECT_FROM_STATEMENT = SELECT_STATEMENT + " WHERE id = %d";

    private Context context;

    public ListtDAO(Context context) {
        super(context);
        this.context = context;
    }

    public Listt get(long id) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format(SELECT_FROM_STATEMENT, id);
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToFirst();
        Listt listt = buildListt(cursor);
        cursor.close();
        return listt;
    }

    public List<Listt> getAll(Project project) {
        String sql = "SELECT * FROM " + TB_LISTT_NAME + " WHERE project_id = " + project.getId() + " ORDER BY POSITION ASC";
        SQLiteDatabase db = getReadableDatabase();
        List<Listt> listts = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext())
            listts.add(buildListt(cursor));
        cursor.close();
        return listts;
    }

    @NonNull
    private Listt buildListt(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        int position = cursor.getInt(cursor.getColumnIndex("position"));
        int isDoneInt = cursor.getInt(cursor.getColumnIndex("isDone"));
        boolean isDone = (isDoneInt == 1);
        long project_id = cursor.getLong(cursor.getColumnIndex("project_id"));

        return new Listt(context, id, name, position, isDone, project_id);
    }

    public void insert(Listt listt) {
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(TB_LISTT_NAME, null, getValues(listt));
        listt.setId(id);
    }

    @NonNull
    private ContentValues getValues(Listt listt) {
        ContentValues data = new ContentValues();
        data.put("name", listt.getName());
        data.put("position", listt.getPosition());
        data.put("isDone", listt.isDone());
        data.put("project_id", listt.getProject_id());
        return data;
    }

    public void delete(Listt listt) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TB_LISTT_NAME, "id = ?", getPK(listt));
    }

    @NonNull
    private String[] getPK(Listt listt) {
        return new String[]{String.valueOf(listt.getId())};
    }

    public void update(Listt listt) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(TB_LISTT_NAME, getValues(listt), "id = ?", getPK(listt));
    }
}
