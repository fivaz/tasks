package com.example.pontes_stefane_esig.myapplication.helpers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.pontes_stefane_esig.myapplication.converters.JSONtoObject;
import com.example.pontes_stefane_esig.myapplication.converters.ObjectToJSON;
import com.example.pontes_stefane_esig.myapplication.converters.ObjectToSQL;
import com.example.pontes_stefane_esig.myapplication.converters.SQLtoObject;
import com.example.pontes_stefane_esig.myapplication.models.All;

public class SyncDataTask extends AsyncTask<Integer, Object, String> {

    public static final Integer DOWNLOAD = 1;
    public static final Integer UPLOAD = 0;
    @SuppressLint("StaticFieldLeak")
    private Context context;

    public SyncDataTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Integer[] params) {
        if (params[0].equals(DOWNLOAD))
            return download();
        else if (params[0].equals(UPLOAD))
            return upload();
        else
            return "some error";
    }

    @NonNull
    private String download() {
        //get data from the server
        WebClient client = new WebClient();
        String allJSON = client.downloadAll();
//        System.err.println(allJSON);

        //convert it to Java Object
        All all = JSONtoObject.convertAll(allJSON);
//        System.err.println(all);

        //insert it into local database
        ObjectToSQL objectToSQL = new ObjectToSQL(context);
        objectToSQL.setAll(all);

        return "database downloaded!";
    }

    private String upload() {
        All all = new All();
        //get current data from database in java object
        SQLtoObject sqLtoObject = new SQLtoObject(context);
        sqLtoObject.getUsers(all);

        //convert it to json
        String allJSON = new ObjectToJSON().convert(all);
//        System.err.println(allJSON);

        //send it to the server
        WebClient client = new WebClient();
        //System.out.println(response);

        client.uploadAll(allJSON);

        return "database uploaded!";
    }
    /*
    @Override
    protected void onPostExecute(String message) {
        if (message.equals("database uploaded!"))
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
    */
}