package com.example.pontes_stefane_esig.myapplication.converters;

import com.example.pontes_stefane_esig.myapplication.models.All;
import com.example.pontes_stefane_esig.myapplication.models.Card;
import com.example.pontes_stefane_esig.myapplication.models.CurrentState;
import com.example.pontes_stefane_esig.myapplication.models.Listt;
import com.example.pontes_stefane_esig.myapplication.models.Project;
import com.example.pontes_stefane_esig.myapplication.models.User;

import org.json.JSONException;
import org.json.JSONStringer;

public class ObjectToJSON {

    private JSONStringer json;

    public ObjectToJSON() {
        json = new JSONStringer();
    }

    public String convert(All all) {
        try {
            buildAll(all);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    private void buildAll(All all) throws JSONException {
        json.object();
        buildUsers(all);
        json.endObject();
    }

    private void buildUsers(All all) throws JSONException {
        json.key("users").array();

        for (User user : all.getUsers())
            buildUser(user);

        json.endArray();
    }

    private void buildUser(User user) throws JSONException {
        json.object()
                .key("id").value(user.getId())
                .key("firstName").value(user.getFirstName())
                .key("lastName").value(user.getLastName())
                .key("email").value(user.getEmail())
                .key("password").value(user.getPassword());
        buildProjects(user);
        json.endObject();
    }

    private void buildProjects(User user) throws JSONException {
        json.key("projects").array();

        for (Project project : user.getProjects())
            buildProject(project);

        json.endArray();
    }

    private void buildProject(Project project) throws JSONException {
        json.object()
                .key("id").value(project.getId())
                .key("name").value(project.getName())
                .key("startAt").value(project.getStartAt())
                .key("endAt").value(project.getEndAt())
                .key("isArchived").value(project.isArchived())
                .key("userId").value(project.getUserId());
        buildListts(project);
        buildCurrentStates(project);
        json.endObject();
    }

    private void buildCurrentStates(Project project) throws JSONException {
        json.key("currentStates").array();

        for (CurrentState currentState : project.getCurrentStates())
            buildCurrentState(currentState);

        json.endArray();
    }

    private void buildCurrentState(CurrentState currentState) throws JSONException {
        json.object()
                .key("id").value(currentState.getId())
                .key("pointsDone").value(currentState.getPointsDone())
                .key("timeBlock").value(currentState.getTimeBlock())
                .key("projectId").value(currentState.getProjectId());
        json.endObject();
    }

    private void buildListts(Project project) throws JSONException {
        json.key("listts").array();

        for (Listt listt : project.getListts())
            buildListt(listt);

        json.endArray();
    }

    private void buildListt(Listt listt) throws JSONException {
        json.object()
                .key("id").value(listt.getId())
                .key("name").value(listt.getName())
                .key("position").value(listt.getPosition())
                .key("isDone").value(listt.isDone())
                .key("isArchived").value(listt.isArchived())
                .key("projectId").value(listt.getProjectId());
        buildCards(listt);
        json.endObject();
    }

    private void buildCards(Listt listt) throws JSONException {
        json.key("cards").array();

        for (Card card : listt.getCards())
            buildCard(card);

        json.endArray();
    }

    private void buildCard(Card card) throws JSONException {
        json.object()
                .key("id").value(card.getId())
                .key("name").value(card.getName())
                .key("points").value(card.getPoints())
                .key("position").value(card.getPosition())
                .key("listtId").value(card.getListtId());
        json.endObject();
    }
}
