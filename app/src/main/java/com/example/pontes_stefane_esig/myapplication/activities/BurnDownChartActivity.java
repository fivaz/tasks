package com.example.pontes_stefane_esig.myapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.daos.CurrentStateDAO;
import com.example.pontes_stefane_esig.myapplication.daos.ProjectDAO;
import com.example.pontes_stefane_esig.myapplication.models.CurrentState;
import com.example.pontes_stefane_esig.myapplication.models.Project;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class BurnDownChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burn_down_chart);

        Intent intent = getIntent();
        long project_id = intent.getLongExtra("project_id", 0);
        float totalPoints = (float) intent.getDoubleExtra("project_total", 0);
        ProjectDAO projectDAO = new ProjectDAO(this);
        Project project = projectDAO.get(project_id);
        int timeParts = 5;

        CurrentStateDAO currentStateDAO = new CurrentStateDAO(this);
        List<CurrentState> currentStates = currentStateDAO.getAll(project);

        List<Float> points_done = new ArrayList<>();
        for (CurrentState currentState : currentStates)
            points_done.add((float) currentState.getPointsDone());

        buildBurnDownChart(points_done, totalPoints, timeParts);
    }

    private void buildBurnDownChart(List<Float> points_done, float totalPoints, int timeParts) {
        Utils.init(this);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        LineDataSet ideaDataSet = buildActualDataSet(points_done, totalPoints, timeParts);
        LineDataSet actualDataSet = buildIdealDataSet(totalPoints, timeParts);
        dataSets.add(ideaDataSet);
        dataSets.add(actualDataSet);

        LineData data = new LineData(dataSets);
        LineChart chart = new LineChart(this);
        setContentView(chart);
        chart.setData(data);
        chart.invalidate();
    }

    @NonNull
    private LineDataSet buildActualDataSet(List<Float> points_done, float totalPoints, int timeParts) {
        List<Float> actualData = buildActualData(points_done, totalPoints, timeParts);
        Log.e("B A#buildActualDataSet", "actualData: " + actualData.toString());
        return buildDataSet(actualData, "actual task remaning", R.color.red);
    }

    @NonNull
    private List<Float> buildActualData(List<Float> points_done, float totalPoints, int timeParts) {
        List<Float> actualData = new ArrayList<>();
        actualData.add(totalPoints);
        int i;
        for (i = 0; i < points_done.size(); i++) {
            float actualDatum = totalPoints - points_done.get(i);
            actualData.add(actualDatum);
        }
        while (actualData.size() < timeParts + 1) {
            float actualDatum = totalPoints - points_done.get(i - 1);
            actualData.add(actualDatum);
        }
        return actualData;
    }

    @NonNull
    private LineDataSet buildDataSet(List<Float> data, String label, int color) {
        ArrayList<Entry> entries = new ArrayList<>();

        for (int i = 0; i < data.size(); i++)
            entries.add(new Entry(i, data.get(i)));

        LineDataSet dataSet = new LineDataSet(entries, label);

        dataSet.setColor(ContextCompat.getColor(this, color));
        dataSet.setLineWidth(2.5f);
        dataSet.setCircleRadius(4f);
        return dataSet;
    }

    @NonNull
    private LineDataSet buildIdealDataSet(float totalPoints, int timeParts) {
        ArrayList<Entry> entries = new ArrayList<>();

        timeParts = timeParts + 1;
        //50 = 150 / (4-1) -> 150 / 30
        float eachTotalPoint = totalPoints / (timeParts - 1);

        for (int i = 0; i < timeParts; i++)
            //50 * (4 - (0+1)) -> 50 * (4 - 1) -> 50 * 3 = 150;
            //50 * (4 - (1+1)) -> 50 * (4 - 2) -> 50 * 2 = 100;
            //50 * (4 - (2+1)) -> 50 * (4 - 3) -> 50 * 1 = 50;
            //50 * (4 - (3+1)) -> 50 * (4 - 4) -> 50 * 0 = 0;
            entries.add(new Entry(i, eachTotalPoint * (timeParts - (i + 1))));

        LineDataSet dataSet = new LineDataSet(entries, "ideal task remaning");

        dataSet.setColor(ContextCompat.getColor(this, R.color.blue));
        dataSet.setLineWidth(2.5f);
        dataSet.setCircleRadius(4f);
        return dataSet;
    }
}
