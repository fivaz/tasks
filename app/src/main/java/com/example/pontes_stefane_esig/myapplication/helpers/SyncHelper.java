package com.example.pontes_stefane_esig.myapplication.helpers;

import android.content.Context;

import com.example.pontes_stefane_esig.myapplication.daos.ListtDAO;
import com.example.pontes_stefane_esig.myapplication.models.Listt;
import com.example.pontes_stefane_esig.myapplication.models.Project;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

public class SyncHelper {

    private Context context;

    public SyncHelper(Context context) {
        this.context = context;
    }

    public String convertAll(Project project) {
        StringBuilder json = new StringBuilder();

        try {
            String header = buildHeader();
            json.append(header);

            String projectJSON = convertProject(project);
            json.append(projectJSON);

            String listtsJSON = convertListts(project);
            json.append(listtsJSON);

            String footer = buildFooter();
            json.append(footer);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString();
    }

    private String buildHeader() throws JSONException {
        JSONStringer js = new JSONStringer();

        js.object();
        js.key("project");
        js.array();

        return js.toString();
    }

    private String convertProject(Project project) throws JSONException {
        JSONStringer js = new JSONStringer();

        js.object();
        js.key("id").value(project.getId());
        js.key("name").value(project.getName());
        js.key("start_at").value(project.getStart_at());
        js.key("end_at").value(project.getEnd_at());
        js.key("isArchived").value(project.isArchived());
        js.endObject();

        return js.toString();
    }

    private String convertListts(Project project) throws JSONException {
        ListtDAO listtDAO = new ListtDAO(context);
        List<Listt> listts = listtDAO.getAll(project);

        StringBuilder listtsJSON = new StringBuilder();

        JSONStringer js = new JSONStringer();

        js.object();
        js.key("listts");
        js.array();

        for (Listt listt : listts) {
            String listtJSON = convertListt(listt);
            listtsJSON.append(listtJSON);
        }

        js.endArray();
        js.endObject();

        return listtsJSON.toString();
    }

    private String convertListt(Listt listt) {
        JSONStringer js = new JSONStringer();

        try {
            js.object();
            js.key("id").value(listt.getId());
            js.key("name").value(listt.getName());
            js.key("position").value(listt.getPosition());
            js.key("project_id").value(listt.getProject_id());
            js.key("isDone").value(listt.isDone());
            js.key("isArchived").value(listt.isArchived());
            js.endObject();

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return js.toString();
    }

    private String buildFooter() throws JSONException {
        JSONStringer js = new JSONStringer();

        js.endArray();
        js.endObject();

        return js.toString();
    }
}
