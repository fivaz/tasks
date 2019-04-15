package com.example.pontes_stefane_esig.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.daos.UserDAO;
import com.example.pontes_stefane_esig.myapplication.helpers.UserHelper;
import com.example.pontes_stefane_esig.myapplication.helpers.UserHelper;
import com.example.pontes_stefane_esig.myapplication.models.User;
import com.example.pontes_stefane_esig.myapplication.models.User;

public class UserFormActivity extends AppCompatActivity {

    private UserHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_form);

        helper = new UserHelper(this);

        Intent intent = getIntent();

        long user_id = intent.getLongExtra("user_id", 0);

        String title;

        if (user_id != 0) {
            UserDAO dao = new UserDAO(this);
            User user = dao.get(user_id);
            helper.setUser(user);

            title = getString(R.string.user_edit);
        } else {
            title = getString(R.string.user_new);
        }

        setTitle(title);
    }

    public void userSubmit() {
        if (helper.isOk()) {
            User user = helper.getUser();
            UserDAO dao = new UserDAO(this);

            String message;

            if (user.getId() == 0) {
                dao.insert(user);

                message = String.format(getString(R.string.user_added), user.getFirstName());
            } else {
                dao.update(user);
                message = String.format(getString(R.string.user_updated), user.getFirstName());
            }
            dao.close();

            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.card_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_form_ok)
            userSubmit();
        return super.onOptionsItemSelected(item);
    }
}
