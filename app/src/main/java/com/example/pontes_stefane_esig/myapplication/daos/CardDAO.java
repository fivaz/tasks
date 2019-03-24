package com.example.pontes_stefane_esig.myapplication.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.pontes_stefane_esig.myapplication.models.Card;
import com.example.pontes_stefane_esig.myapplication.models.Listt;

import java.util.ArrayList;
import java.util.List;

public class CardDAO extends DAO {

    public CardDAO(Context context) {
        super(context);
    }

    //TODO use prepared statements
    public List<Card> getAll(Listt listt) {
        String sql = "SELECT * FROM " + TB_CARD_NAME + " WHERE listt_id = " + listt.getId() + " ORDER BY POSITION ASC";
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);

        List<Card> cards = new ArrayList<>();
        while (cursor.moveToNext()) {
            Card card = buildCard(cursor);
//            Log.e("gettingAll Cards", card.toString());
            cards.add(card);
        }
        cursor.close();
        return cards;
    }

    private List<Card> sortList(List<Card> cards) {
        ArrayList<Card> sortedList = new ArrayList<>();
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            int position = card.getPosition();
            if (position > sortedList.size())
                sortedList.add(card);
            else
                sortedList.add(position, card);
        }
        return sortedList;
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

//        Log.e("Inserting: ", card.toString());

        long id = db.insert(TB_CARD_NAME, null, getValues(card));
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
        db.delete(TB_CARD_NAME, "id = ?", getPK(card));
    }

    //TODO Put this in the class DAO
    @NonNull
    private String[] getPK(Card card) {
        return new String[]{String.valueOf(card.getId())};
    }

    //TODO Put this in the class DAO
    public void update(Card card) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(TB_CARD_NAME, getValues(card), "id = ?", getPK(card));
    }
}
