package com.example.pontes_stefane_esig.myapplication.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.pontes_stefane_esig.myapplication.converters.DateConverter;
import com.example.pontes_stefane_esig.myapplication.models.Listt;
import com.example.pontes_stefane_esig.myapplication.models.Project;
import com.example.pontes_stefane_esig.myapplication.models.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//TODO use prepared statements
//TODO after update and insert
public class ProjectDAO extends DAO {

    private final String SELECT_STATEMENT = "SELECT * FROM " + TB_PROJECT_NAME;
    private final String SELECT_WHERE = SELECT_STATEMENT + " WHERE id = %d";
    private final String SELECT_ALL = SELECT_STATEMENT + " WHERE user_id = %d AND isArchived = 0";
    private Context context;

    public ProjectDAO(Context context) {
        super(context);
        this.context = context;
    }

    public Project get(long id) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format(SELECT_WHERE, id);
        Cursor cursor = db.rawQuery(sql, null);
        Project project = null;
        if (cursor.moveToFirst())
            project = buildProject(cursor);
        cursor.close();
        return project;
    }

    public List<Project> getAll(User user) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format(SELECT_ALL, user.getId());
        Cursor cursor = db.rawQuery(sql, null);
        List<Project> projects = new ArrayList<>();
        while (cursor.moveToNext())
            projects.add(buildProject(cursor));
        cursor.close();
        return projects;
    }

    @NonNull
    private Project buildProject(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        long start_at_millliseconds = cursor.getLong(cursor.getColumnIndex("start_at"));
        Date start_at = new Date(start_at_millliseconds);
        String start_atEN = DateConverter.toStringEN(start_at);
        long end_at_millliseconds = cursor.getLong(cursor.getColumnIndex("end_at"));
        Date end_at = new Date(end_at_millliseconds);
        String end_atEN = DateConverter.toStringEN(end_at);
        long user_id = cursor.getLong(cursor.getColumnIndex("user_id"));

        return new Project(id, name, start_atEN, end_atEN, user_id);
    }

    public void insert(Project project) {
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(DAO.TB_PROJECT_NAME, null, getValues(project));

        if (project.getId() == 0)
            buildDefaultListts(id);

        project.setId(id);
    }

    private void buildDefaultListts(long id) {
        Listt listtTODO = new Listt();
        listtTODO.setProject_id(id);
        listtTODO.setName("TODO");
        listtTODO.setPosition(0);

        Listt listtDOING = new Listt();
        listtDOING.setProject_id(id);
        listtDOING.setName("DOING");
        listtDOING.setPosition(1);

        Listt listtDONE = new Listt();
        listtDONE.setProject_id(id);
        listtDONE.setName("DONE");
        listtDONE.setPosition(2);
        listtDONE.setDone(true);

        ListtDAO listtDAO = new ListtDAO(context);
        listtDAO.insert(listtTODO);
        listtDAO.insert(listtDOING);
        listtDAO.insert(listtDONE);
        listtDAO.close();
    }

    @NonNull
    private ContentValues getValues(Project project) {
        ContentValues data = new ContentValues();
        data.put("name", project.getName());

        String start_atEN = project.getStart_at();
        long start_at_long = DateConverter.toLong(start_atEN);
        String end_atEN = project.getEnd_at();
        long end_at_long = DateConverter.toLong(end_atEN);

        if (project.getId() != 0)
            data.put("id", project.getId());
        data.put("start_at", start_at_long);
        data.put("end_at", end_at_long);
        data.put("isArchived", project.isArchived());
        data.put("user_id", project.getUser_id());
        return data;
    }

    public void delete(Project project) {
        SQLiteDatabase db = getWritableDatabase();
        project.setArchived(true);
        db.update(DAO.TB_PROJECT_NAME, getValues(project), "id = ?", getPK(project));
    }

    @NonNull
    private String[] getPK(Project project) {
        return new String[]{String.valueOf(project.getId())};
    }

    public void update(Project project) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(DAO.TB_PROJECT_NAME, getValues(project), "id = ?", getPK(project));
    }

    public void save(Project project) {
        if (get(project.getId()) == null) {
            insert(project);
        } else {
            update(project);
        }
    }
}
