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
import com.example.pontes_stefane_esig.myapplication.daos.ProjectDAO;
import com.example.pontes_stefane_esig.myapplication.helpers.MyItemTouchHelperCallback;
import com.example.pontes_stefane_esig.myapplication.daos.CardDAO;
import com.example.pontes_stefane_esig.myapplication.daos.ListtDAO;
import com.example.pontes_stefane_esig.myapplication.models.Listt;
import com.example.pontes_stefane_esig.myapplication.models.Project;

public class ProjectActivity extends AppCompatActivity {

    private RecyclerView rvLists;
    private Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        long project_id = getIntent().getLongExtra("project_id", 0);
        ProjectDAO dao = new ProjectDAO(this);
        project = dao.get(project_id);

        rvLists = findViewById(R.id.rv_listts);
        rvLists.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rvLists.setLayoutManager(layoutManager);
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
        rvLists.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(adapter, "horizontal");
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(rvLists);
    }

    public void goToListForm(View view) {
        Intent intent = new Intent(this, ListtFormActivity.class);
        intent.putExtra("project_id", project.getId());
        startActivity(intent);
    }
}