package com.example.pontes_stefane_esig.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.daos.UserDAO;
import com.example.pontes_stefane_esig.myapplication.helpers.LoginHelper;
import com.example.pontes_stefane_esig.myapplication.models.User;

public class HomeActivity extends AppCompatActivity {

    private LoginHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        helper = new LoginHelper(this);
    }

    public void register(View view) {
        Intent intent = new Intent(this, UserFormActivity.class);
        startActivity(intent);
    }

    public void signIn(View view) {
        User user = helper.getUser();
        UserDAO dao = new UserDAO(this);
        if (dao.checkUser(user)) {
            user = dao.getUserByLogin(user);
            Intent intent = new Intent(this, ProjectsActivity.class);
            intent.putExtra("user_id", user.getId());
            startActivity(intent);
        } else {
            invalideLogin();
        }
    }

    private void invalideLogin() {
        String message = this.getString(R.string.error_msg_invalide_login);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
