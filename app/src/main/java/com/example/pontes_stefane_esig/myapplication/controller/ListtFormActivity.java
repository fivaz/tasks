package com.example.pontes_stefane_esig.myapplication.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.dao.ListtDAO;
import com.example.pontes_stefane_esig.myapplication.helper.ListtFormHelper;
import com.example.pontes_stefane_esig.myapplication.model.Listt;
import com.example.pontes_stefane_esig.myapplication.model.Project;

public class ListtFormActivity extends AppCompatActivity {

//    private ListtFormHelper helper;
//    private Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listt_form);

//        helper = new ListtFormHelper(this);
//
//        Intent intent = getIntent();
//        project = (Project) intent.getSerializableExtra("project");
//        Listt listt = (Listt) intent.getSerializableExtra("listt");
//        if (listt != null)
//            helper.setList(listt);
    }

//    public void listtSubmit(View view) {
//        Listt listt = helper.getList();
//        ListtDAO dao = new ListtDAO(this);
//        if (listt.getId() != 0)
//            dao.update(listt);
//        else{
//            listt.setProject_id(project.getId());
//            dao.insert(listt);
//        }
//        dao.close();
//
//        Toast.makeText(this, listt.toString(), Toast.LENGTH_SHORT).show();
//        goToProjectPage();
//    }
//
//    void goToProjectPage() {
//        Intent projectPage = new Intent(this, ProjectActivity.class);
//        projectPage.putExtra("project", project);
//        startActivity(projectPage);
//    }
}
