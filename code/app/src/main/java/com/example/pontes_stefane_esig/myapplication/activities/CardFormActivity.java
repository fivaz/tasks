package com.example.pontes_stefane_esig.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        String title;

        if (card_id != 0) {
            CardDAO dao = new CardDAO(this);
            Card card = dao.get(card_id);
            helper.setCard(card);

            title = getString(R.string.card_edit);
        } else {
            listt_id = intent.getLongExtra("listt_id", 0);
            position = intent.getIntExtra("position", 0);

            title = getString(R.string.card_new);
        }

        setTitle(title);
    }

    public void cardSubmit() {
        if (helper.isOk()) {
            Card card = helper.getCard();
            CardDAO dao = new CardDAO(this);

            String message;

            if (card.getId() == 0) {
                card.setListtId(listt_id);
                card.setPosition(position);
                dao.insert(card);

                message = "tâche " + card.getName() + " ajoutée";

            } else {
                dao.update(card);

                message = "tâche " + card.getName() + " modifiée";
            }
            dao.close();

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.card_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_form_ok)
            cardSubmit();
        return super.onOptionsItemSelected(item);
    }
}
