package com.example.pontes_stefane_esig.myapplication.activities;

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
import com.example.pontes_stefane_esig.myapplication.converters.SQLinJSON;
import com.example.pontes_stefane_esig.myapplication.daos.ProjectDAO;
import com.example.pontes_stefane_esig.myapplication.daos.UserDAO;
import com.example.pontes_stefane_esig.myapplication.models.Project;
import com.example.pontes_stefane_esig.myapplication.models.User;

import java.util.List;

public class ProjectsActivity extends AppCompatActivity {

    private ListView lvProjects;
    private List<Project> projects;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);

        lvProjects = findViewById(R.id.lv_projects);
        Button btNew = findViewById(R.id.bt_new_project);

        long user_id = getIntent().getLongExtra("user_id", 0);
        UserDAO userDao = new UserDAO(this);
        user = userDao.get(user_id);
        userDao.close();

        lvProjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> list, View item, int position, long id) {
                Project project = (Project) lvProjects.getItemAtPosition(position);

                Intent goToProject = new Intent(ProjectsActivity.this, ProjectActivity.class);
                goToProject.putExtra("project_id", project.getId());
                startActivity(goToProject);
            }
        });

        btNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentGoToProjectForm = new Intent(ProjectsActivity.this, ProjectFormActivity.class);
                intentGoToProjectForm.putExtra("user_id", user.getId());
                startActivity(intentGoToProjectForm);
            }
        });

        registerForContextMenu(lvProjects);

        setTitle(getString(R.string.home_title));

        SQLinJSON converter = new SQLinJSON(this, user);
        converter.convert();
        System.err.println(converter.getJSON());
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshView();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem edit = menu.add(R.string.project_edit);
        MenuItem delete = menu.add(R.string.project_delete);

        edit.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Project project = (Project) lvProjects.getItemAtPosition(info.position);

                Intent goToForm = new Intent(ProjectsActivity.this, ProjectFormActivity.class);
                goToForm.putExtra("project_id", project.getId());
                startActivity(goToForm);
                return false;
            }
        });

        delete.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Project project = (Project) lvProjects.getItemAtPosition(info.position);

                ProjectDAO dao = new ProjectDAO(ProjectsActivity.this);
                dao.delete(project);
                dao.close();

                refreshView();
                return false;
            }
        });
    }

    void refreshView() {
        loadProjects();
        updateView();
    }

    void loadProjects() {
        ProjectDAO dao = new ProjectDAO(this);
        projects = dao.getAll(user);
        dao.close();
    }

    private void updateView() {
        ArrayAdapter<Project> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, projects);
        lvProjects.setAdapter(adapter);
    }
}
