package com.example.pontes_stefane_esig.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

            title = getString(R.string.listt_edit);
        } else {
            project_id = intent.getLongExtra("project_id", 0);
            position = intent.getIntExtra("position", 0);

            title = getString(R.string.listt_new);
        }

        setTitle(title);
    }

    public void listSubmit() {
        if (helper.isOk()) {
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.listt_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_form_ok)
            listSubmit();
        return super.onOptionsItemSelected(item);
    }
}
