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

        List<Float> idealData = new ArrayList<>();
        idealData.add(150f);
        idealData.add(100f);
        idealData.add(50f);
        idealData.add(0f);

        List<Float> actualData = new ArrayList<>();
        actualData.add(150f);
        actualData.add(150f);
        actualData.add(30f);
        actualData.add(0f);

        buildBurnDownChart(idealData, actualData);
//        barChart();
//        lineChart();
//        lineChart2();
    }

    private void buildBurnDownChart(List<Float> idealData, List<Float> actualData) {
        Utils.init(this);
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        LineDataSet ideaDataSet = buildIdealDataSet(idealData);
        LineDataSet actualDataSet = buildActualDataSet(actualData);
        dataSets.add(ideaDataSet);
        dataSets.add(actualDataSet);

        LineData data = new LineData(dataSets);
        LineChart chart = new LineChart(this);
        setContentView(chart);
        chart.setData(data);
        chart.invalidate();
    }

    @NonNull
    private LineDataSet buildActualDataSet(List<Float> actualData) {
        return buildDataSet(actualData, "actual task remaning", R.color.red);
    }

    @NonNull
    private LineDataSet buildIdealDataSet(List<Float> idealData) {
        return buildDataSet(idealData, "ideal task remaning", R.color.blue);
    }

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

    private void lineChart() {
//        Utils.init(this);

        ArrayList<ILineDataSet> datasets = new ArrayList<>();

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 150f));
        entries.add(new Entry(1, 100f));
        entries.add(new Entry(2, 50f));
        entries.add(new Entry(3, 0f));

        LineDataSet dataset = new LineDataSet(entries, "text");

        dataset.setColor(ColorTemplate.VORDIPLOM_COLORS[2]);

        dataset.setLineWidth(2.5f);
        dataset.setCircleRadius(4f);

        datasets.add(dataset);

        ArrayList<Entry> entries1 = new ArrayList<>();
        entries1.add(new Entry(0, 150f));
        entries1.add(new Entry(1, 150f));
        entries1.add(new Entry(2, 30f));
        entries1.add(new Entry(3, 0f));

        LineDataSet dataset1 = new LineDataSet(entries1, "text1");

        dataset1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);

        dataset1.setLineWidth(2.5f);
        dataset1.setCircleRadius(4f);

        datasets.add(dataset1);

        LineData data = new LineData(datasets);

        LineChart chart = new LineChart(this);
        setContentView(chart);

        chart.setData(data);
        chart.invalidate();
    }

    private void lineChart2() {
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();

        for (int z = 0; z < 3; z++) {

            ArrayList<Entry> values = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                double val = (Math.random() * 10) + 3;
                values.add(new Entry(i, (float) val));
            }

            LineDataSet d = new LineDataSet(values, "DataSet " + (z + 1));
            d.setLineWidth(2.5f);
            d.setCircleRadius(4f);

            int color = 1;
            d.setColor(color);
            d.setCircleColor(color);
            dataSets.add(d);
        }

//        // make the first DataSet dashed
//        ((LineDataSet) dataSets.get(0)).enableDashedLine(10, 10, 0);
//        ((LineDataSet) dataSets.get(0)).setColors(ColorTemplate.VORDIPLOM_COLORS);
//        ((LineDataSet) dataSets.get(0)).setCircleColors(ColorTemplate.VORDIPLOM_COLORS);

        LineData data = new LineData(dataSets);

        LineChart chart = new LineChart(this);
        setContentView(chart);

        chart.setData(data);
        chart.invalidate();
    }

    private void barChart() {
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0, 4f));
        entries.add(new BarEntry(1, 8f));
        entries.add(new BarEntry(2, 6f));
        entries.add(new BarEntry(3, 12f));
        entries.add(new BarEntry(4, 18f));
        entries.add(new BarEntry(5, 9f));

        BarDataSet dataset = new BarDataSet(entries, "# of Calls");

        BarChart chart = new BarChart(this);
        setContentView(chart);

        BarData data = new BarData(dataset);
        chart.setData(data);
    }
}
