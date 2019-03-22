package com.example.pontes_stefane_esig.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.adapter.ListtAdapter;
import com.example.pontes_stefane_esig.myapplication.dao.CardDAO;
import com.example.pontes_stefane_esig.myapplication.dao.ListtDAO;
import com.example.pontes_stefane_esig.myapplication.model.Card;
import com.example.pontes_stefane_esig.myapplication.model.Listt;
import com.example.pontes_stefane_esig.myapplication.model.Project;

import java.util.List;

public class ProjectActivity extends AppCompatActivity {

    private RecyclerView lvLists;
    private Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        project = (Project) getIntent().getSerializableExtra("project");

        lvLists = findViewById(R.id.rv_listts);

        lvLists.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        lvLists.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshView();
    }

    void refreshView() {
        loadAll();
        updateView();
    }

    void loadAll(){
        loadLists();
//        loadCards();

//        ListtDAO dao = new ListtDAO(this);
//        project.setListts(dao.getAllCascade(project));
//        dao.close();
    }

    void loadLists() {
        //TODO put the DAO in the Model
        ListtDAO dao = new ListtDAO(this);
        project.setListts(dao.getAll(project));
        dao.close();
    }

    void loadCards() {
        for (Listt listt : project.getListts()) {
            CardDAO dao = new CardDAO(this);
            listt.setCards(dao.getAll(listt));
            dao.close();
        }
    }

    private void updateView() {
        TextView tvProjectInfo = findViewById(R.id.tv_project_info);
        tvProjectInfo.setText(project.getName());

        ListtAdapter adapter = new ListtAdapter(this, project.getListts());
        lvLists.setAdapter(adapter);
    }

    public void goToListForm(View view) {
        Intent goToListForm = new Intent(this, ListtFormActivity.class);
        goToListForm.putExtra("project", project);
        startActivity(goToListForm);
    }
}
