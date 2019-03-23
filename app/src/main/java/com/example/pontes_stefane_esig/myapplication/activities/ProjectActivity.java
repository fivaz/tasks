package com.example.pontes_stefane_esig.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.adapters.ListtAdapter;
import com.example.pontes_stefane_esig.myapplication.helpers.MyItemTouchHelperCallback;
import com.example.pontes_stefane_esig.myapplication.daos.CardDAO;
import com.example.pontes_stefane_esig.myapplication.daos.ListtDAO;
import com.example.pontes_stefane_esig.myapplication.models.Listt;
import com.example.pontes_stefane_esig.myapplication.models.Project;

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
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
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

    void loadAll() {
        loadLists();
        loadCards();
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

        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(adapter, "horizontal");
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(lvLists);
    }

    public void goToListForm(View view) {
        Intent intent = new Intent(this, ListtFormActivity.class);
        intent.putExtra("project", project);
        startActivity(intent);
    }
}