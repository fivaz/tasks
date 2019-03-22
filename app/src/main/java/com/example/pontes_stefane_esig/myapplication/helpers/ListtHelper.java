package com.example.pontes_stefane_esig.myapplication.helpers;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.models.Listt;

public class ListtHelper {

    private final EditText inputName;

    public ListtHelper(AppCompatActivity context) {
        inputName = context.findViewById(R.id.et_listt_name);
    }

    public Listt getListt() {
        return new Listt(inputName.getText().toString());
    }
}
