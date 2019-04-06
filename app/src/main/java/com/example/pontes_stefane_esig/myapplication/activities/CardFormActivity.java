package com.example.pontes_stefane_esig.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.daos.CardDAO;
import com.example.pontes_stefane_esig.myapplication.helpers.CardHelper;
import com.example.pontes_stefane_esig.myapplication.models.Card;

public class CardFormActivity extends AppCompatActivity {

    private long listt_id;
    private int position;
    CardHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_form);

        helper = new CardHelper(this);

        Intent intent = getIntent();

        long card_id = intent.getLongExtra("card_id", 0);

        if (card_id == 0) {
            listt_id = intent.getLongExtra("listt_id", 0);
            position = intent.getIntExtra("position", 0);
        } else {
            CardDAO dao = new CardDAO(this);
            Card card = dao.get(card_id);
            helper.setCard(card);
        }
    }

    public void cardSubmit(View view) {
        Card card = helper.getCard();
        CardDAO dao = new CardDAO(this);

        if (card.getId() == 0) {
            card.setListt_id(listt_id);
            card.setPosition(position);
            dao.insert(card);
        } else
            dao.update(card);
        dao.close();

        Toast.makeText(this, card.toString(), Toast.LENGTH_SHORT).show();
        finish();
    }
}
