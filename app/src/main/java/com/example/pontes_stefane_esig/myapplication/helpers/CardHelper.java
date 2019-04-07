package com.example.pontes_stefane_esig.myapplication.helpers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.models.Card;

public class CardHelper {

    private final EditText inputName;
    private final EditText inputPoints;
    private Card card;
    private Context context;

    public CardHelper(AppCompatActivity context) {
        inputName = context.findViewById(R.id.et_card_name);
        inputPoints = context.findViewById(R.id.et_card_points);
        this.context = context;
        card = new Card();
    }

    public Card getCard() {
        String name = inputName.getText().toString();
        String points = inputPoints.getText().toString();

        if (points.isEmpty())
            points = "50";

        card.setName(name);
        card.setPoints(Double.parseDouble(points));

        return card;
    }

    public void setCard(Card card) {
        inputName.setText(card.getName());
        inputPoints.setText(String.valueOf(card.getPoints()));
        this.card = card;
    }

    public boolean isOk() {
        String name = inputName.getText().toString();

        if (name.isEmpty()) {
            String message = context.getString(R.string.error_msg_name_required);
            inputName.setError(message);
            return false;
        }
        return true;
    }
}
