package com.example.pontes_stefane_esig.myapplication.activities;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.pontes_stefane_esig.myapplication.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

import static java.util.Arrays.asList;

public class BurnDownChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burn_down_chart);

        int timeParts = 4;
        float totalPoints = 200f;
        List<Float> points_done = asList(0f, 120f, 150f);

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
        Log.e("actualData :", actualData.toString());
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
        while (actualData.size() < timeParts+1) {
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

        dataSet.setColor(getResources().getColor(color));
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

        dataSet.setColor(getResources().getColor(R.color.blue));
        dataSet.setLineWidth(2.5f);
        dataSet.setCircleRadius(4f);
        return dataSet;
    }
}
