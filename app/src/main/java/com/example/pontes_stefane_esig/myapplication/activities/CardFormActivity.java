package com.example.pontes_stefane_esig.myapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.daos.CardDAO;
import com.example.pontes_stefane_esig.myapplication.helpers.CardHelper;
import com.example.pontes_stefane_esig.myapplication.models.Card;
import com.example.pontes_stefane_esig.myapplication.models.Listt;

public class CardFormActivity extends AppCompatActivity {

    private Listt listt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_form);
        listt = (Listt) getIntent().getSerializableExtra("listt");
    }

    public void cardSubmit(View view) {
        Card card = new CardHelper(this).getCard();
        card.setListt_id(listt.getId());
        CardDAO dao = new CardDAO(this);
        dao.insert(card);
        dao.close();

        Toast.makeText(this, card.toString(), Toast.LENGTH_SHORT).show();
        finish();
    }
}
