package com.example.pontes_stefane_esig.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.pontes_stefane_esig.myapplication.model.Listt;
import com.example.pontes_stefane_esig.myapplication.model.Project;

import java.util.ArrayList;
import java.util.List;

public class ListtDAO extends DAO {
//    private Context context;

    public ListtDAO(Context context) {
        super(context);
//        this.context = context;
    }

    public List<Listt> getAll(Project project) {
        String sql = "SELECT * FROM " + TB_LISTT_NAME + " WHERE project_id = " + project.getId();
        SQLiteDatabase db = getReadableDatabase();
        //TODO prepared statement
//        String[] args = new String[]{String.valueOf(project.getId())};
//        Cursor c = db.rawQuery(sql, args);
        Cursor c = db.rawQuery(sql, null);

        List<Listt> listts = new ArrayList<>();

        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("id"));
            String name = c.getString(c.getColumnIndex("name"));
            long project_id = c.getLong(c.getColumnIndex("project_id"));

            Listt listt = new Listt(id, name, project_id);
            listts.add(listt);
        }
        c.close();
        return listts;
    }

    /*
    public List<Listt> getAllCascade(Project project) {
        String sql = "SELECT * FROM " + TABLE + " WHERE project_id = " + project.getId();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Listt> listts = new ArrayList<>();

        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("id"));
            String name = c.getString(c.getColumnIndex("name"));
            long project_id = c.getLong(c.getColumnIndex("project_id"));

            Listt listt = new Listt(id, name, project_id);

            CardDAO dao = new CardDAO(context);
            listt.setCards(dao.getAll(listt));
            dao.close();

            listts.add(listt);
        }
        c.close();
        return listts;
    }
    */

    public void insert(Listt listt) {
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(TB_LISTT_NAME, null, getValues(listt));
        listt.setId(id);
    }

    @NonNull
    private ContentValues getValues(Listt listt) {
        ContentValues data = new ContentValues();
        data.put("name", listt.getName());
        data.put("project_id", listt.getProject_id());
        return data;
    }

    //TODO test this
//    @Override
//    protected ContentValues getValues(Model model) {
//        Card card = (Card) model;
//        ContentValues data = new ContentValues();
//        data.put("name", card.getName());
//        data.put("points", card.getPoints());
//        data.put("project_id", card.getList_id());
//        return data;
//    }

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