package com.example.pontes_stefane_esig.myapplication.model;

import java.io.Serializable;
import java.util.List;

public class Project implements Serializable {

    private long id;
    private String name;
    private List<Card> cards;

    public Project() {
    }

    public Project(String name) {
        this.name = name;
    }

    public Project(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public double getTotal() {
        double total = 0;
        for (Card card : cards)
            total += card.getPoints();
        return total;
    }

    @Override
    public String toString() {
        return name;
    }
}
