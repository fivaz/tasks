package com.example.pontes_stefane_esig.myapplication.converters;

import android.content.Context;

import com.example.pontes_stefane_esig.myapplication.daos.ListtDAO;
import com.example.pontes_stefane_esig.myapplication.daos.ProjectDAO;
import com.example.pontes_stefane_esig.myapplication.daos.UserDAO;
import com.example.pontes_stefane_esig.myapplication.models.Listt;
import com.example.pontes_stefane_esig.myapplication.models.Project;
import com.example.pontes_stefane_esig.myapplication.models.User;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

public class ProjectInJSON {

    private Context context;
    private JSONStringer json;
    private User user;

    public ProjectInJSON(Context context, User user) {
        this.context = context;
        json = new JSONStringer();
        this.user = user;
    }

    public String getJSON() {
        return json.toString();
    }

    public void convert() {
        try {
            buildHeader();
            buildUser();
            buildFooter();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void buildHeader() throws JSONException {
        json.object()
                .key("user");
    }

    private void buildUser() throws JSONException {
        json.object()
                .key("first_name").value(user.getFirstName())
                .key("last_name").value(user.getLastName())
                .key("email").value(user.getEmail())
                .key("password").value(user.getPassword());
        buildProjects();
        json.endObject();
    }

    private void buildProjects() throws JSONException {
        ProjectDAO projectDAO = new ProjectDAO(context);
        List<Project> projects = projectDAO.getAll(user);
        projectDAO.close();

        json.key("projects").array();

        for (Project project : projects)
            buildProject(project);

        json.endArray();
    }

    private void buildProject(Project project) throws JSONException {
        json.object()
                .key("id").value(project.getId())
                .key("name").value(project.getName())
                .key("start_at").value(project.getStartAtToString())
                .key("end_at").value(project.getEndAtToString())
                .key("isArchived").value(project.isArchived());
        buildListts(project);
        json.endObject();
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
    }
}
