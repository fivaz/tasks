package com.example.pontes_stefane_esig.myapplication.helpers;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.pontes_stefane_esig.myapplication.converters.JSONinSQL;
import com.example.pontes_stefane_esig.myapplication.converters.ObjectInJSON;
import com.example.pontes_stefane_esig.myapplication.daos.CardDAO;
import com.example.pontes_stefane_esig.myapplication.daos.ListtDAO;
import com.example.pontes_stefane_esig.myapplication.daos.ProjectDAO;
import com.example.pontes_stefane_esig.myapplication.daos.UserDAO;
import com.example.pontes_stefane_esig.myapplication.models.Card;
import com.example.pontes_stefane_esig.myapplication.models.Listt;
import com.example.pontes_stefane_esig.myapplication.models.Project;
import com.example.pontes_stefane_esig.myapplication.models.User;

import java.util.List;

public class DataBaseSync {

    private Context context;

    public DataBaseSync(Context context) {
        this.context = context;
    }

    public void download(String dataJSON) {
        List<User> users = JSONinSQL.convertAll(dataJSON);
        setUsers(users);
    }

    private void setUsers(List<User> users) {
        UserDAO userDAO = new UserDAO(context);
        for (User user : users) {
            userDAO.save(user);
            setProjects(user.getProjects());
        }
        userDAO.close();
    }

    private void setProjects(List<Project> projects) {
        ProjectDAO projectDAO = new ProjectDAO(context);
        for (Project project : projects) {
            projectDAO.save(project);
            setListts(project.getListts());
        }
        projectDAO.close();
    }

    private void setListts(List<Listt> listts) {
        ListtDAO listtDAO = new ListtDAO(context);
        for (Listt listt : listts) {
            listtDAO.save(listt);
            setCards(listt.getCards());
        }
        listtDAO.close();
    }

    private void setCards(List<Card> cards) {
        CardDAO cardDAO = new CardDAO(context);
        for (Card card : cards) {
            cardDAO.save(card);
        }
        cardDAO.close();
    }

    public String upload() {
        List<User> users = getUsers();

        System.err.println(users);

        return ObjectInJSON.convert(users);
    }

    @NonNull
    private List<User> getUsers() {
        UserDAO userDAO = new UserDAO(context);
        List<User> users = userDAO.getAll();
        userDAO.close();

        for (User user : users) {
            List<Project> projects = getProjects(user);
            user.setProjects(projects);
        }

        return users;
    }

    @NonNull
    private List<Project> getProjects(User user) {
        ProjectDAO projectDAO = new ProjectDAO(context);
        List<Project> projects = projectDAO.getAll(user);
        projectDAO.close();

        for (Project project : projects) {
            List<Listt> listts = getListts(project);
            project.setListts(listts);
        }

        return projects;
    }

    @NonNull
    private List<Listt> getListts(Project project) {
        ListtDAO listtDAO = new ListtDAO(context);
        List<Listt> listts = listtDAO.getAll(project);
        listtDAO.close();

        for (Listt listt : listts) {
            List<Card> cards = getCards(listt);
            listt.setCards(cards);
        }

        return listts;
    }

    @NonNull
    private List<Card> getCards(Listt listt) {
        CardDAO cardDAO = new CardDAO(context);
        List<Card> cards = cardDAO.getAll(listt);
        cardDAO.close();
        return cards;
    }
}
