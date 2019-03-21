package com.example.pontes_stefane_esig.myapplication.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.dao.ListtDAO;
import com.example.pontes_stefane_esig.myapplication.helper.ListtHelper;
import com.example.pontes_stefane_esig.myapplication.model.Listt;
import com.example.pontes_stefane_esig.myapplication.model.Project;

public class ListtFormActivity extends AppCompatActivity {

    private Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listt_form);
        project = (Project) getIntent().getSerializableExtra("project");
    }

    public void listSubmit(View view) {
        Listt listt = new ListtHelper(this).getListt();
        listt.setProject_id(project.getId());

        ListtDAO dao = new ListtDAO(this);
        dao.insert(listt);
        dao.close();

        Toast.makeText(this, listt.toString(), Toast.LENGTH_SHORT).show();
    }
}
