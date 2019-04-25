package com.example.pontes_stefane_esig.myapplication.daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.pontes_stefane_esig.myapplication.models.ProjectsUsersMap;
import com.example.pontes_stefane_esig.myapplication.models.ProjectsUsersMap;
import com.example.pontes_stefane_esig.myapplication.models.User;

import java.util.ArrayList;
import java.util.List;

//TODO use prepared statements
//TODO after update and insert
public class ProjectsUsersMapDAO extends DAO {


    private final String TABLE_NAME = TB_PROJECTS_USERS_MAP_NAME;
    private final String SELECT_STATEMENT = "SELECT * FROM " + TABLE_NAME;
    private final String SELECT_WHERE = SELECT_STATEMENT + " WHERE id = %d";
    private final String SELECT_ALL = SELECT_STATEMENT + " WHERE user_id = %d";
    private Context context;

    public ProjectsUsersMapDAO(Context context) {
        super(context);
        this.context = context;
    }

    public ProjectsUsersMap get(long id) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format(SELECT_WHERE, id);
        Cursor cursor = db.rawQuery(sql, null);
        ProjectsUsersMap projectsUsersMap = null;
        if (cursor.moveToFirst())
            projectsUsersMap = buildProjectsUsersMap(cursor);
        cursor.close();
        return projectsUsersMap;
    }

    public List<ProjectsUsersMap> getAll(User user) {
        SQLiteDatabase db = getReadableDatabase();
        String sql = String.format(SELECT_ALL, user.getId());
        Cursor cursor = db.rawQuery(sql, null);
        List<ProjectsUsersMap> projectsUsersMaps = new ArrayList<>();
        while (cursor.moveToNext())
            projectsUsersMaps.add(buildProjectsUsersMap(cursor));
        cursor.close();
        return projectsUsersMaps;
    }

    @NonNull
    private ProjectsUsersMap buildProjectsUsersMap(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex("id"));
        int project_id = cursor.getInt(cursor.getColumnIndex("project_id"));
        int user_id = cursor.getInt(cursor.getColumnIndex("user_id"));

        return new ProjectsUsersMap(id, project_id, user_id);
    }

    public void insert(ProjectsUsersMap projectsUsersMap) {
        SQLiteDatabase db = getWritableDatabase();
        long id = db.insert(DAO.TB_PROJECTS_USERS_MAP_NAME, null, getValues(projectsUsersMap));
        projectsUsersMap.setId(id);
    }

    @NonNull
    private ContentValues getValues(ProjectsUsersMap projectsUsersMap) {
        ContentValues data = new ContentValues();
        if (projectsUsersMap.getId() != 0)
            data.put("id", projectsUsersMap.getId());
        data.put("project_id", projectsUsersMap.getProjectId());
        data.put("user_id", projectsUsersMap.getUserId());
        return data;
    }

    public void delete(ProjectsUsersMap projectsUsersMap) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?", getPK(projectsUsersMap));
    }

    @NonNull
    private String[] getPK(ProjectsUsersMap projectsUsersMap) {
        return new String[]{String.valueOf(projectsUsersMap.getId())};
    }

    public void update(ProjectsUsersMap projectsUsersMap) {
        SQLiteDatabase db = getWritableDatabase();
        db.update(TABLE_NAME, getValues(projectsUsersMap), "id = ?", getPK(projectsUsersMap));
    }

    public void save(ProjectsUsersMap projectsUsersMap) {
        if (get(projectsUsersMap.getId()) == null) {
            insert(projectsUsersMap);
        } else {
            update(projectsUsersMap);
        }
    }
}
