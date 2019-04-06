package com.example.pontes_stefane_esig.myapplication.helpers;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.models.Card;

public class CardHelper {

    private final EditText inputName;
    private final EditText inputPoints;
    private Card card;

    public CardHelper(AppCompatActivity context) {
        inputName = context.findViewById(R.id.et_card_name);
        inputPoints = context.findViewById(R.id.et_card_points);
        card = new Card();
    }

    public Card getCard() {
        String name = inputName.getText().toString();
        double points = Double.parseDouble(inputPoints.getText().toString());

        card.setName(name);
        card.setPoints(points);
        return card;
    }

    public void setCard(Card card) {
        inputName.setText(card.getName());
        inputPoints.setText(String.valueOf(card.getPoints()));
        this.card = card;
    }
}
