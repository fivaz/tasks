package com.example.pontes_stefane_esig.myapplication.converters;

import android.content.Context;

import com.example.pontes_stefane_esig.myapplication.daos.ListtDAO;
import com.example.pontes_stefane_esig.myapplication.models.Listt;
import com.example.pontes_stefane_esig.myapplication.models.Project;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

public class ProjectConverter {

    private Context context;
    private JSONStringer json;

    public ProjectConverter(Context context) {
        this.context = context;
        json = new JSONStringer();
    }

    public String getJSON() {
        return json.toString();
    }

    public void buildAll(Project project) {
        try {
            buildHeader();
            buildProject(project);
            buildListts(project);
            buildFooter();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void buildHeader() throws JSONException {
        json.object()
                .key("project")
                .object();
    }

    private void buildProject(Project project) throws JSONException {
        json.key("id").value(project.getId());
        json.key("name").value(project.getName());
        json.key("start_at").value(project.getStartAtToString());
        json.key("end_at").value(project.getEndAtToString());
        json.key("isArchived").value(project.isArchived());
    }

    private void buildListts(Project project) throws JSONException {
        ListtDAO listtDAO = new ListtDAO(context);
        List<Listt> listts = listtDAO.getAll(project);
        listtDAO.close();

        json.key("listts").array();

        for (Listt listt : listts)
            buildListt(listt);

        json.endArray();
    }

    private void buildListt(Listt listt) throws JSONException {
        json.object()
                .key("id").value(listt.getId())
                .key("name").value(listt.getName())
                .key("position").value(listt.getPosition())
                .key("project_id").value(listt.getProject_id())
                .key("isDone").value(listt.isDone())
                .key("isArchived").value(listt.isArchived());
        json.endObject();
    }

    private void buildFooter() throws JSONException {
        json.endObject();
        json.endObject();
    }
}
