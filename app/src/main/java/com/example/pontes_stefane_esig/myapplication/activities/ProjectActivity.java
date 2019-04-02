package com.example.pontes_stefane_esig.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.adapters.ListtAdapter;
import com.example.pontes_stefane_esig.myapplication.daos.CardDAO;
import com.example.pontes_stefane_esig.myapplication.daos.CurrentStateDAO;
import com.example.pontes_stefane_esig.myapplication.daos.ListtDAO;
import com.example.pontes_stefane_esig.myapplication.daos.ProjectDAO;
import com.example.pontes_stefane_esig.myapplication.helpers.MyItemTouchHelperCallback;
import com.example.pontes_stefane_esig.myapplication.models.CurrentState;
import com.example.pontes_stefane_esig.myapplication.models.Listt;
import com.example.pontes_stefane_esig.myapplication.models.Project;

import java.util.Calendar;
import java.util.Date;

public class ProjectActivity extends AppCompatActivity {

    private RecyclerView rvLists;
    private Project project;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);

        long project_id = getIntent().getLongExtra("project_id", 0);
        ProjectDAO dao = new ProjectDAO(this);
        project = dao.get(project_id);

        rvLists = findViewById(R.id.rv_listts);
        rvLists.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rvLists.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshView();
        checkCurrentState();
    }

    private void checkCurrentState() {
        Date now = Calendar.getInstance().getTime();

        if (now.getTime() > project.getEnd_at().getTime())
            Log.e("A P#checkCurrentState", "your project has ended yet");
        if (now.getTime() < project.getStart_at().getTime())
            Log.e("A P#checkCurrentState", "your project hasn't started yet");
        else if (hasTimeBlockChanged())
            addNewCurrentState();
        else if (hasPointsDoneChanged())
            updateCurrentState();
        else
            Log.e("A P#checkCurrentState", "there is no new current state yet");
    }

    private void updateCurrentState() {
        CurrentStateDAO dao = new CurrentStateDAO(this);
        CurrentState lastCurrentState = dao.getLast(project);
        lastCurrentState.setPointsDone(project.getPointsDone());
        dao.update(lastCurrentState);
        dao.close();
    }

    private boolean hasPointsDoneChanged() {
        CurrentStateDAO dao = new CurrentStateDAO(this);
        CurrentState lastCurrentState = dao.getLast(project);
        dao.close();
        if (lastCurrentState == null) {
            return false;
        } else {
            double pointsDone = lastCurrentState.getPointsDone();
            return (project.getPointsDone() != pointsDone);
        }
    }

    private void addNewCurrentState() {
        CurrentState currentState = project.buildNewCurrentState();
        CurrentStateDAO dao = new CurrentStateDAO(this);
        dao.close();
        dao.insert(currentState);
    }

    private boolean hasTimeBlockChanged() {
        CurrentStateDAO dao = new CurrentStateDAO(this);
        CurrentState lastCurrentState = dao.getLast(project);
        dao.close();
        if (lastCurrentState == null) {
            return true;
        } else {
            int lastTimeBlock = lastCurrentState.getTimePart();
            Log.e("A P#checkNewCurrentS", "lastTimeBlock: " + lastTimeBlock);
            return (project.getCurrentTimeBlock() > lastTimeBlock);
        }
    }

    void refreshView() {
        loadAll();
        updateView();
    }

    void loadAll() {
        loadLists();
        loadCards();
    }

    void loadLists() {
        //TODO put the DAO in the Model
        ListtDAO dao = new ListtDAO(this);
        project.setListts(dao.getAll(project));
        dao.close();
    }

    void loadCards() {
        for (Listt listt : project.getListts()) {
            CardDAO dao = new CardDAO(this);
            listt.setCards(dao.getAll(listt));
            dao.close();
        }
    }

    private void updateView() {
        TextView tvProjectName = findViewById(R.id.tv_project_name);
        TextView tvProjectTotal = findViewById(R.id.tv_project_total);
        TextView tvCurrentTimePart = findViewById(R.id.tv_current_time_part);

        tvProjectName.setText(project.getName());
        tvProjectTotal.setText(String.valueOf(project.getTotal()));
        tvCurrentTimePart.setText(String.valueOf(project.getCurrentTimeBlock()));

        ListtAdapter adapter = new ListtAdapter(this, project.getListts());
        rvLists.setAdapter(adapter);

        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(adapter, "horizontal");
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(rvLists);
    }

    public void goToListForm(View view) {
        Intent intent = new Intent(this, ListtFormActivity.class);
        intent.putExtra("project_id", project.getId());
        intent.putExtra("position", project.getListts().size());
        startActivity(intent);
    }

    public void goToBurnDownChart(View view) {
        Intent intent = new Intent(this, BurnDownChartActivity.class);
        intent.putExtra("project_id", project.getId());

        intent.putExtra("project_total", project.getTotal());
        startActivity(intent);
    }
}