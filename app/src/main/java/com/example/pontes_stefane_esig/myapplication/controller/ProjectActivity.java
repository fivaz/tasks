package com.example.pontes_stefane_esig.myapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.dao.ListtDAO;
import com.example.pontes_stefane_esig.myapplication.dao.ProjectDAO;
import com.example.pontes_stefane_esig.myapplication.model.Card;
import com.example.pontes_stefane_esig.myapplication.model.Listt;
import com.example.pontes_stefane_esig.myapplication.model.Project;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectActivity extends AppCompatActivity {

    private ListView lvLists;
    private Project project;
    private TextView tvProjectInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        lvLists = findViewById(R.id.lv_lists);
        tvProjectInfo = findViewById(R.id.tv_project_info);

        Intent intent = getIntent();
        project = (Project) intent.getSerializableExtra("project");

        registerForContextMenu(lvLists);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadLists();
        updateView();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem edit = menu.add("Edit");
        MenuItem delete = menu.add("Delete");

        edit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Card card = (Card) lvLists.getItemAtPosition(info.position);

                Intent goToForm = new Intent(ProjectActivity.this, CardFormActivity.class);
                goToForm.putExtra("project", project);
                goToForm.putExtra("card", card);
                startActivity(goToForm);
                return false;
            }
        });

        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Project project = (Project) lvLists.getItemAtPosition(info.position);

                ProjectDAO dao = new ProjectDAO(ProjectActivity.this);
                dao.delete(project);
                dao.close();

                loadLists();
                updateView();
                return false;
            }
        });
    }

    void loadLists() {
        //TODO put the DAO in the Model
        ListtDAO dao = new ListtDAO(this);
        project.setLists(dao.getAll(project));
        dao.close();
    }

    private void updateView() {
        tvProjectInfo.setText(project.getName() + " " + project.getTotal());

        ArrayAdapter<Listt> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, project.getLists());
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.listt_item, list);
        lvLists.setAdapter(adapter);
    }

    public void goToListForm(View view) {
//        Toast.makeText(this, "Test", Toast.LENGTH_SHORT).show();
        Intent intentGoToListForm = new Intent(this, ListtFormActivity.class);
//        intentGoToListForm.putExtra("project", project);
        startActivity(intentGoToListForm);
    }
}
