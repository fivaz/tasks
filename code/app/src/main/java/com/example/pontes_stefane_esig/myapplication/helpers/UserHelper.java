package com.example.pontes_stefane_esig.myapplication.helpers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.models.User;

public class UserHelper {

    private final EditText inputFirstName;
    private final EditText inputLastName;
    private final EditText inputEmail;
    private final EditText inputPassword;
    private User user;
    private Context context;

    public UserHelper(AppCompatActivity context) {
        inputFirstName = context.findViewById(R.id.et_user_first_name);
        inputLastName = context.findViewById(R.id.et_user_last_name);
        inputEmail = context.findViewById(R.id.et_user_email);
        inputPassword = context.findViewById(R.id.et_user_password);
        this.context = context;
        user = new User();
    }

    public User getUser() {
        String firstName = inputFirstName.getText().toString();
        String lastName = inputLastName.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    public void setUser(User user) {
        inputFirstName.setText(user.getFirst_name());
        inputLastName.setText(user.getLast_name());
        inputEmail.setText(user.getEmail());
        inputPassword.setText(user.getPassword());
        this.user = user;
    }

    public boolean isOk() {
        String firstName = inputFirstName.getText().toString();
        String lastName = inputLastName.getText().toString();
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        if (firstName.isEmpty()) {
            String message = context.getString(R.string.error_msg_first_name_required);
            inputFirstName.setError(message);
            return false;
        }
        if (lastName.isEmpty()) {
            String message = context.getString(R.string.error_msg_last_name_required);
            inputLastName.setError(message);
            return false;
        }
        if (email.isEmpty()) {
            String message = context.getString(R.string.error_msg_email_required);
            inputEmail.setError(message);
            return false;
        }
        if (password.isEmpty()) {
            String message = context.getString(R.string.error_msg_password_required);
            inputPassword.setError(message);
            return false;
        }
        return true;
    }
}
