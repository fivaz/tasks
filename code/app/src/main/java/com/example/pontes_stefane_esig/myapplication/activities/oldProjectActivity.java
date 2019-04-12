package com.example.pontes_stefane_esig.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.adapters.ProjectAdapter;
import com.example.pontes_stefane_esig.myapplication.converters.DateConverter;
import com.example.pontes_stefane_esig.myapplication.daos.CardDAO;
import com.example.pontes_stefane_esig.myapplication.daos.CurrentStateDAO;
import com.example.pontes_stefane_esig.myapplication.daos.ListtDAO;
import com.example.pontes_stefane_esig.myapplication.daos.ProjectDAO;
import com.example.pontes_stefane_esig.myapplication.helpers.SyncDataTask;
import com.example.pontes_stefane_esig.myapplication.helpers.MyItemTouchHelperCallback;
import com.example.pontes_stefane_esig.myapplication.models.CurrentState;
import com.example.pontes_stefane_esig.myapplication.models.Listt;
import com.example.pontes_stefane_esig.myapplication.models.Project;

import java.util.Calendar;
import java.util.Date;

//TODO delete this later
public class oldProjectActivity extends AppCompatActivity {

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

        setTitle(project.getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshView();
        checkCurrentState();
    }

    public void checkCurrentState() {
        Date now = Calendar.getInstance().getTime();
        //TODO check it later
        long end_at_long = DateConverter.toLong(project.getEndAt());
        long start_at_long = DateConverter.toLong(project.getStartAt());
        if (now.getTime() > end_at_long)
            Log.e("A P#checkCurrentState", "your project has already ended");
        if (now.getTime() < start_at_long)
            Log.e("A P#checkCurrentState", "your project hasn't started yet");
        else {
            if (hasTimeBlockChanged())
                addNewCurrentState();
            if (hasPointsDoneChanged())
                updateCurrentState();
            else
                Log.e("A P#checkCurrentState", "there is no new current state yet");
        }
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
            int lastTimeBlock = lastCurrentState.getTimeBlock();
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

//        tvCurrentTimePart.setText(String.valueOf(project.getCurrentTimeBlock()));

//        ProjectAdapter adapter = new ProjectAdapter(this, project.getListts());
//        rvLists.setAdapter(adapter);

//        ItemTouchHelper.Callback callback = new MyItemTouchHelperCallback(adapter, "horizontal");
//        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
//        touchHelper.attachToRecyclerView(rvLists);

        invalidateOptionsMenu();
    }

    public void goToListForm(View view) {
        Intent intent = new Intent(this, ListtFormActivity.class);
        intent.putExtra("project_id", project.getId());
        intent.putExtra("position", project.getListts().size());
        startActivity(intent);
    }

    public void goToBurnDownChart() {
        Intent intent = new Intent(this, BurnDownChartActivity.class);
        intent.putExtra("project_id", project.getId());

        intent.putExtra("project_total", project.getTotal());
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.project, menu);

        MenuItem item = menu.findItem(R.id.it_project_total);
        item.setTitle(String.valueOf(project.getTotal()));

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*if (item.getItemId() == R.id.menu_check_burndown_chart)
            goToBurnDownChart();

        if (item.getItemId() == R.id.menu_upload)
            uploadAll();*/

        return super.onOptionsItemSelected(item);
    }

    private void uploadAll() {
        new SyncDataTask(this).execute(SyncDataTask.UPLOAD);
    }
}