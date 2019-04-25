package com.example.pontes_stefane_esig.myapplication.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.daos.ProjectsUsersMapDAO;
import com.example.pontes_stefane_esig.myapplication.daos.UserDAO;
import com.example.pontes_stefane_esig.myapplication.models.ProjectsUsersMap;
import com.example.pontes_stefane_esig.myapplication.models.User;

public class InviteFormActivity extends AppCompatActivity {

    private long project_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_form);

        project_id = getIntent().getLongExtra("project_id", 0);

        setTitle("Ajouter d'autres utilisateurs");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.card_form, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_form_ok){
            submit();
        }
        return super.onOptionsItemSelected(item);
    }

    private void submit() {
        EditText inputEmail = findViewById(R.id.et_invitation_user_email);
        String email = inputEmail.getText().toString();

        UserDAO userDAO = new UserDAO(this);
        User user = userDAO.getUserByEmail(email);
        userDAO.close();

        if(user != null){
            ProjectsUsersMap projectsUsersMap = new ProjectsUsersMap();
            projectsUsersMap.setProjectId(project_id);
            projectsUsersMap.setUserId(user.getId());

            ProjectsUsersMapDAO projectsUsersMapDAO = new ProjectsUsersMapDAO(this);
            projectsUsersMapDAO.insert(projectsUsersMap);
            projectsUsersMapDAO.close();
            Toast.makeText(this, String.format("L'utilisateur %s a été ajouté dans le Projet", user.getFirstName()), Toast.LENGTH_SHORT).show();
            finish();
        }else{
            inputEmail.setError("Cet adresse mail n'existe pas");
        }
    }
}
