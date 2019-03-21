package com.example.pontes_stefane_esig.myapplication.helper;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.model.Listt;

public class ListFormHelper {

    private final EditText inputName;
    private Listt list;

    public ListFormHelper(AppCompatActivity context) {
        inputName = context.findViewById(R.id.et_card_name);
        list = new Listt();
    }

    public Listt getList() {
        list.setName(inputName.getText().toString());
        return list;
    }

    public void setList(Listt list) {
        inputName.setText(list.getName());
        this.list = list;
    }
}
