package com.example.pontes_stefane_esig.myapplication.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.dao.ProjectDAO;
import com.example.pontes_stefane_esig.myapplication.model.Project;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private ListView lvProjects;
    private List<Project> projects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvProjects = findViewById(R.id.lv_projects);
        Button btNew = findViewById(R.id.bt_new_project);

        lvProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View item, int position, long id) {
                Project project = (Project) lvProjects.getItemAtPosition(position);

                Intent goToProject = new Intent(HomeActivity.this, ProjectActivity.class);
                goToProject.putExtra("project", project);
                startActivity(goToProject);
            }
        });


        btNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoToProjectForm = new Intent(HomeActivity.this, ProjectFormActivity.class);
                startActivity(intentGoToProjectForm);
            }
        });

        registerForContextMenu(lvProjects);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadProjects();
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
                Project project = (Project) lvProjects.getItemAtPosition(info.position);

                Intent goToForm = new Intent(HomeActivity.this, ProjectFormActivity.class);
                goToForm.putExtra("project", project);
                startActivity(goToForm);
                return false;
            }
        });

        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Project project = (Project) lvProjects.getItemAtPosition(info.position);

                ProjectDAO dao = new ProjectDAO(HomeActivity.this);
                dao.delete(project);
                dao.close();

                loadProjects();
                return false;
            }
        });
    }

    void loadProjects() {
        ProjectDAO dao = new ProjectDAO(this);
        projects = dao.getAll();
        dao.close();
    }

    private void updateView() {
        ArrayAdapter<Project> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, projects);
        lvProjects.setAdapter(adapter);
    }
}
