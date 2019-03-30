package com.example.pontes_stefane_esig.myapplication.models;

import android.content.Context;

import com.example.pontes_stefane_esig.myapplication.daos.CardDAO;

import java.util.ArrayList;
import java.util.List;

public class Listt extends Model {

    private long project_id;
    private String name;
    private int position;
    private boolean isDone;
    private List<Card> cards;

    private Context context;

    public Listt(String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    public Listt(Context context, long id, String name, int position, boolean isDone, long project_id) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.position = position;
        this.isDone = isDone;
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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
        CardDAO dao = new CardDAO(context);
        for (int i = 0; i < cards.size(); i++) {
            Card card = cards.get(i);
            card.setPosition(i);
            card.setListt_id(id);
            dao.update(card);
        }
        dao.close();
    }

    public double getTotal() {
        double total = 0;
        for (Card card : cards)
            total += card.getPoints();
        return total;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "Listt{" +
                "id=" + id +
                ", project_id=" + project_id +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", isDone=" + isDone +
                '}';
    }
}
