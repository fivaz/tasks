package com.example.pontes_stefane_esig.myapplication.converters;

import android.content.Context;

import com.example.pontes_stefane_esig.myapplication.daos.CardDAO;
import com.example.pontes_stefane_esig.myapplication.daos.ListtDAO;
import com.example.pontes_stefane_esig.myapplication.daos.ProjectDAO;
import com.example.pontes_stefane_esig.myapplication.daos.UserDAO;
import com.example.pontes_stefane_esig.myapplication.models.Card;
import com.example.pontes_stefane_esig.myapplication.models.Listt;
import com.example.pontes_stefane_esig.myapplication.models.Project;
import com.example.pontes_stefane_esig.myapplication.models.User;

import java.util.List;

public class ObjectToSQL {

    private Context context;

    public ObjectToSQL(Context context) {
        this.context = context;
    }

    public void setUsers(List<User> users) {
        UserDAO userDAO = new UserDAO(context);
        for (User user : users) {
            userDAO.save(user);
            setProjects(user.getProjects());
        }
        userDAO.close();
    }

    public void setProjects(List<Project> projects) {
        ProjectDAO projectDAO = new ProjectDAO(context);
        for (Project project : projects) {
            projectDAO.save(project);
            setListts(project.getListts());
        }
        projectDAO.close();
    }

    public void setListts(List<Listt> listts) {
        ListtDAO listtDAO = new ListtDAO(context);
        for (Listt listt : listts) {
            listtDAO.save(listt);
            setCards(listt.getCards());
        }
        listtDAO.close();
    }

    public void setCards(List<Card> cards) {
        CardDAO cardDAO = new CardDAO(context);
        for (Card card : cards) {
            cardDAO.save(card);
        }
        cardDAO.close();
    }
}
