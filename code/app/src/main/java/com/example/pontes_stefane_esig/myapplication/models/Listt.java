package com.example.pontes_stefane_esig.myapplication.models;

import android.content.Context;

import com.example.pontes_stefane_esig.myapplication.daos.CardDAO;

import java.util.ArrayList;
import java.util.List;

public class Listt extends Model {

    private long projectId;
    private String name;
    private int position;
    private boolean isDone;
    private boolean isArchived;
    private List<Card> cards;

    private Context context;

    public Listt() {
        isDone = false;
        isArchived = false;
        cards = new ArrayList<>();
    }

    public Listt(String name) {
        this.name = name;
        isDone = false;
        this.isArchived = false;
        cards = new ArrayList<>();
    }

    public Listt(Context context, long id, String name, int position, boolean isDone, long projectId) {
        this.context = context;
        this.id = id;
        this.name = name;
        this.position = position;
        this.isDone = isDone;
        this.projectId = projectId;
        this.isArchived = false;
        cards = new ArrayList<>();
    }

    public long getProjectId() {
        return projectId;
    }

    public void setProjectId(long projectId) {
        this.projectId = projectId;
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

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
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
            card.setListtId(id);
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
                ", projectId=" + projectId +
                ", name='" + name + '\'' +
                ", position=" + position +
                ", isDone=" + isDone +
                '}';
    }
}
