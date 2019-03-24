package com.example.pontes_stefane_esig.myapplication.models;

import android.content.Context;

import com.example.pontes_stefane_esig.myapplication.daos.CardDAO;

import java.util.ArrayList;
import java.util.List;

public class Listt extends Model {

    private long project_id;
    private String name;
    private List<Card> cards;
    private Context context;

    public Listt(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    public Listt(Context context, long id, String name, long project_id) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.project_id = project_id;
        cards = new ArrayList<>();
    }

    public long getProject_id() {
        return project_id;
    }

    public void setProject_id(long project_id) {
        this.project_id = project_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
        if (context != null) {
            CardDAO dao = new CardDAO(context);
            for (Card card : cards) {
                card.setListt_id(id);
                dao.update(card);
            }
            dao.close();
        }
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return name;
    }
}
