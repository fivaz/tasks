package com.example.pontes_stefane_esig.myapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

abstract class DAO extends SQLiteOpenHelper {

    DAO(Context context, int version) {
        super(context, "trello", null, version);
    }

    /*
    public List<Card> getAll(Project project) {
        String sql = "SELECT * FROM " + TABLE + "WHERE project_id = ?";
        SQLiteDatabase db = getReadableDatabase();
        String[] args = new String[]{String.valueOf(project.getId())};
        Cursor c = db.rawQuery(sql, args);

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

    public void insert(Card project) {
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(TABLE, null, getValues(project));
        project.setId(id);
    }

    @NonNull
    private ContentValues getValues(Card project) {
        ContentValues data = new ContentValues();
        data.put("name", project.getName());
        data.put("points", project.getPoints());
        data.put("project_id", project.getProject_id());
        return data;
    }

    public void delete(Card project) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE, "id = ?", getPK(project));
    }

    @NonNull
    private String[] getPK(Card project) {
        return new String[]{String.valueOf(project.getId())};
    }

    public void update(Card project) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE, getValues(project), "id = ?", getPK(project));
    }
    */
}
