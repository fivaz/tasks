package com.example.pontes_stefane_esig.myapplication.helper;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.model.Card;
import com.example.pontes_stefane_esig.myapplication.model.Project;

public class CardFormHelper {

    private final EditText inputName;
    private final EditText inputPoints;
    private Card card;

    public CardFormHelper(AppCompatActivity context) {
        inputName = context.findViewById(R.id.et_card_name);
        inputPoints = context.findViewById(R.id.et_card_points);
        card = new Card();
    }

    public Card getCard() {
        card.setName(inputName.getText().toString());
        card.setPoints(Double.valueOf(inputPoints.getText().toString()));
        return card;
    }

    public void setCard(Card card) {
        inputName.setText(card.getName());
        inputName.setText(String.valueOf(card.getPoints()));
        this.card = card;
    }
}
