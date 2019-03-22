package com.example.pontes_stefane_esig.myapplication.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.pontes_stefane_esig.myapplication.model.Card;
import com.example.pontes_stefane_esig.myapplication.model.Listt;

import java.util.ArrayList;
import java.util.List;

public class CardDAO extends DAO {

    public CardDAO(Context context) {
        super(context);
    }

    public List<Card> getAll(Listt listt) {
        String sql = "SELECT * FROM " + TB_CARD_NAME + " WHERE listt_id = " + listt.getId();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);

        List<Card> cards = new ArrayList<>();
        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("id"));
            String name = c.getString(c.getColumnIndex("name"));
            double points = c.getDouble(c.getColumnIndex("points"));
            long listt_id = c.getLong(c.getColumnIndex("listt_id"));

            Card card = new Card(id, name, points, listt_id);
            cards.add(card);
        }
        c.close();
        return cards;
    }

    public void insert(Card card) {
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(TB_CARD_NAME, null, getValues(card));
        card.setId(id);
    }

    @NonNull
    private ContentValues getValues(Card card) {
        ContentValues data = new ContentValues();
        data.put("name", card.getName());
        data.put("points", card.getPoints());
        data.put("listt_id", card.getListt_id());
        return data;
    }
    
    public void delete(Card card) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TB_CARD_NAME, "id = ?", getPK(card));
    }

    @NonNull
    private String[] getPK(Card card) {
        return new String[]{String.valueOf(card.getId())};
    }

    public void update(Card card) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(TB_CARD_NAME, getValues(card), "id = ?", getPK(card));
    }
}
