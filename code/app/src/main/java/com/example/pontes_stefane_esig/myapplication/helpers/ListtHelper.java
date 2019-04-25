package com.example.pontes_stefane_esig.myapplication.helpers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.models.Listt;

public class ListtHelper {

    private final EditText inputName;
    private Listt listt;
    private Context context;

    public ListtHelper(AppCompatActivity context) {
        inputName = context.findViewById(R.id.et_listt_name);
        this.context = context;
        listt = new Listt();
    }

    public Listt getListt() {
        String name = inputName.getText().toString();
        listt.setName(name);

        if (name.equalsIgnoreCase("done"))
            listt.setDone(true);
        else
            listt.setDone(false);

        return listt;
    }

    public void setListt(Listt listt) {
        inputName.setText(listt.getName());
        this.listt = listt;
    }

    public boolean isOk() {
        String name = inputName.getText().toString();

        if (name.isEmpty()) {
            String message = context.getString(R.string.error_msg_name_required);
            inputName.setError(message);
            return false;
        }
        return true;
    }
}
