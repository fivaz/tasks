package com.example.pontes_stefane_esig.myapplication.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pontes_stefane_esig.myapplication.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class BurnDownChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_burn_down_chart);
//        barChart();
        lineChart();
    }

    private void lineChart() {
        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 4f));
        entries.add(new Entry(1, 8f));
        entries.add(new Entry(2, 6f));
        entries.add(new Entry(3, 12f));
        entries.add(new Entry(4, 18f));
        entries.add(new Entry(5, 9f));

        LineDataSet dataset = new LineDataSet(entries, "text");

        LineChart chart = new LineChart(this);
        setContentView(chart);

        LineData data = new LineData(dataset);
        chart.setData(data);
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
