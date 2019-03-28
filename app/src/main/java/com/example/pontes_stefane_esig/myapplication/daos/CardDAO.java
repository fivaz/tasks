package com.example.pontes_stefane_esig.myapplication.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.pontes_stefane_esig.myapplication.models.Card;
import com.example.pontes_stefane_esig.myapplication.models.Listt;

import java.util.ArrayList;
import java.util.List;

public class CardDAO extends DAO {

    private final String TABLE_NAME = TB_CARD_NAME;

    public CardDAO(Context context) {
        super(context);
    }

    //TODO use prepared statements
    public List<Card> getAll(Listt listt) {
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE listt_id = " + listt.getId() + " ORDER BY POSITION ASC";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Card> cards = new ArrayList<>();
        while (cursor.moveToNext())
            cards.add(buildCard(cursor));
        cursor.close();
        return cards;
    }

    @NonNull
    private Card buildCard(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        double points = cursor.getDouble(cursor.getColumnIndex("points"));
        int position = cursor.getInt(cursor.getColumnIndex("position"));
        long listt_id = cursor.getLong(cursor.getColumnIndex("listt_id"));

        return new Card(id, name, points, position, listt_id);
    }

    //TODO Put this in the class DAO
    public void insert(Card card) {
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(TABLE_NAME, null, getValues(card));
        card.setId(id);
    }

    @NonNull
    private ContentValues getValues(Card card) {
        ContentValues data = new ContentValues();
        data.put("name", card.getName());
        data.put("points", card.getPoints());
        data.put("position", card.getPosition());
        data.put("listt_id", card.getListt_id());
        return data;
    }

    //TODO Put this in the class DAO
    public void delete(Card card) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", getPK(card));
    }

    //TODO Put this in the class DAO
    @NonNull
    private String[] getPK(Card card) {
        return new String[]{String.valueOf(card.getId())};
    }

    //TODO Put this in the class DAO
    public void update(Card card) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, getValues(card), "id = ?", getPK(card));
    }
}
