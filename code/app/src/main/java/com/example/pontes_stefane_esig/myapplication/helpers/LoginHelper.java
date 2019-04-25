package com.example.pontes_stefane_esig.myapplication.helpers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.models.User;

public class LoginHelper {

    private final EditText inputEmail;
    private final EditText inputPassword;
    private User user;
    private Context context;

    public LoginHelper(AppCompatActivity context) {
        inputEmail = context.findViewById(R.id.et_login_email);
        inputPassword = context.findViewById(R.id.et_login_password);
        this.context = context;
        user = new User();
    }

    public User getUser() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

        user.setEmail(email);
        user.setPassword(password);
        return user;
    }

    public void setUser(User user) {
        inputEmail.setText(user.getEmail());
        inputPassword.setText(user.getPassword());
        this.user = user;
    }

    public boolean isOk() {
        String email = inputEmail.getText().toString();
        String password = inputPassword.getText().toString();

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
