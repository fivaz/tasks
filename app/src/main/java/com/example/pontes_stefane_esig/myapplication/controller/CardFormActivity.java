package com.example.pontes_stefane_esig.myapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.dao.CardDAO;
import com.example.pontes_stefane_esig.myapplication.helper.CardFormHelper;
import com.example.pontes_stefane_esig.myapplication.model.Card;

public class CardFormActivity extends AppCompatActivity {

    private CardFormHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_form);

        helper = new CardFormHelper(this);

        Intent intent = getIntent();
        Card project = (Card) intent.getSerializableExtra("card");
        if (project != null)
            helper.setCard(project);
    }

    public void submit(View view) {
        Card card = helper.getCard();

        CardDAO dao = new CardDAO(this);
        if (card.getId() != 0)
            dao.update(card);
        else
            dao.insert(card);
        dao.close();

        Toast.makeText(this, card.toString(), Toast.LENGTH_SHORT).show();
        goToProjectPage();
    }

    void goToProjectPage() {
        Intent projectPage = new Intent(this, ProjectActivity.class);
        startActivity(projectPage);
    }
}
