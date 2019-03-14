package com.example.pontes_stefane_esig.myapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.dao.CardDAO;
import com.example.pontes_stefane_esig.myapplication.model.Card;
import com.example.pontes_stefane_esig.myapplication.model.Project;

import java.util.List;

public class ProjectActivity extends AppCompatActivity {

    private ListView lvCards;
    private Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        lvCards = findViewById(R.id.lv_cards);

        Intent intent = getIntent();
        project = (Project) intent.getSerializableExtra("project");
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadCards();
    }

    void loadCards() {
        CardDAO dao = new CardDAO(this);
        List<Card> cards = dao.getAll(project);
        dao.close();

        ArrayAdapter<Card> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cards);
        lvCards.setAdapter(adapter);
    }
//
//    public void goToForm(View view) {
//        Intent intentGoToProjectForm = new Intent(this, CardFormActivity.class);
//        startActivity(intentGoToProjectForm);
//    }
}
