package com.example.pontes_stefane_esig.myapplication.activities;

import android.content.Intent;
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
    private ListtHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listt_form);

        helper = new ListtHelper(this);
        Intent intent = getIntent();
        long listtId = intent.getLongExtra("listt_id", 0);

        String title;

        if (listtId != 0) {
            ListtDAO dao = new ListtDAO(this);
            Listt listt = dao.get(listtId);
            helper.setListt(listt);

            title = getString(R.string.listt_update);
        } else {
            project_id = intent.getLongExtra("project_id", 0);
            position = intent.getIntExtra("position", 0);

            title = getString(R.string.listt_new);
        }

        setTitle(title);
    }

    public void listSubmit(View view) {
        Listt listt = helper.getListt();
        ListtDAO dao = new ListtDAO(this);

        if (listt.getId() == 0) {
            listt.setProject_id(project_id);
            listt.setPosition(position);
            dao.insert(listt);
        } else
            dao.update(listt);
        dao.close();

        Toast.makeText(this, listt.toString(), Toast.LENGTH_SHORT).show();
        finish();
    }
}
