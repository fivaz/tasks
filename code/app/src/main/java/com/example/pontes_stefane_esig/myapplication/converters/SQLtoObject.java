package com.example.pontes_stefane_esig.myapplication.converters;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.pontes_stefane_esig.myapplication.daos.CardDAO;
import com.example.pontes_stefane_esig.myapplication.daos.ListtDAO;
import com.example.pontes_stefane_esig.myapplication.daos.ProjectDAO;
import com.example.pontes_stefane_esig.myapplication.daos.UserDAO;
import com.example.pontes_stefane_esig.myapplication.models.All;
import com.example.pontes_stefane_esig.myapplication.models.Card;
import com.example.pontes_stefane_esig.myapplication.models.Listt;
import com.example.pontes_stefane_esig.myapplication.models.Project;
import com.example.pontes_stefane_esig.myapplication.models.User;

import java.util.List;

public class SQLtoObject {

    private Context context;

    public SQLtoObject(Context context) {
        this.context = context;
    }

    public void getUsers(All all) {
        UserDAO userDAO = new UserDAO(context);
        List<User> users = userDAO.getAll();
        userDAO.close();

        for (User user : users)
            getProjects(user);

        all.setUsers(users);
    }

    public void getProjects(User user) {
        ProjectDAO projectDAO = new ProjectDAO(context);
        List<Project> projects = projectDAO.getAll(user);
        projectDAO.close();

        for (Project project : projects)
            getListts(project);

        user.setProjects(projects);
    }

    public void getListts(Project project) {
        ListtDAO listtDAO = new ListtDAO(context);
        List<Listt> listts = listtDAO.getAll(project);
        listtDAO.close();

        for (Listt listt : listts)
            getCards(listt);

        project.setListts(listts);
    }

    public void getCards(Listt listt) {
        CardDAO cardDAO = new CardDAO(context);
        List<Card> cards = cardDAO.getAll(listt);
        cardDAO.close();

        listt.setCards(cards);
    }

    /*
    public void download(String dataJSON) {
        List<User> users = JSONtoObject.convertAll(dataJSON);
        setUsers(users);
    }
    */

    /*
    public String upload() {
        List<User> users = getUsers();

        System.err.println(users);

        return ObjectInJSON.convert(users);
    }
    */
}
