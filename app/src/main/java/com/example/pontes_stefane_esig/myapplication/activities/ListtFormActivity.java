package com.example.pontes_stefane_esig.myapplication.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.daos.ListtDAO;
import com.example.pontes_stefane_esig.myapplication.helpers.ListtHelper;
import com.example.pontes_stefane_esig.myapplication.models.Listt;

public class ListtFormActivity extends AppCompatActivity {

    private long project_id;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listt_form);
        project_id = getIntent().getLongExtra("project_id", 0);
        position = getIntent().getIntExtra("position", 0);
    }

    public void listSubmit(View view) {
        Listt listt = new ListtHelper(this).getListt();
        listt.setProject_id(project_id);
        listt.setPosition(position);

        ListtDAO dao = new ListtDAO(this);
        dao.insert(listt);
        dao.close();

        Toast.makeText(this, listt.toString(), Toast.LENGTH_SHORT).show();
        finish();
    }
}
