package com.example.pontes_stefane_esig.myapplication.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.daos.ProjectDAO;
import com.example.pontes_stefane_esig.myapplication.helpers.ProjectFormHelper;
import com.example.pontes_stefane_esig.myapplication.models.Project;

import java.util.Calendar;

public class ProjectFormActivity extends AppCompatActivity implements View.OnClickListener {

    private ProjectFormHelper helper;

    private Button btDatePickerStart;
    private Button btTimePickerStart;
    private Button btDatePickerEnd;
    private Button btTimePickerEnd;

    private EditText etDatePickerStart;
    private EditText etTimePickerStart;
    private EditText etDatePickerEnd;
    private EditText etTimePickerEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_form);

        btDatePickerStart = findViewById(R.id.bt_start_date);
        btTimePickerStart = findViewById(R.id.bt_start_time);
        btDatePickerEnd = findViewById(R.id.bt_end_date);
        btTimePickerEnd = findViewById(R.id.bt_end_time);

        etDatePickerStart = findViewById(R.id.et_start_date);
        etTimePickerStart = findViewById(R.id.et_start_time);
        etDatePickerEnd = findViewById(R.id.et_end_date);
        etTimePickerEnd = findViewById(R.id.et_end_time);

        btDatePickerStart.setOnClickListener(this);
        btTimePickerStart.setOnClickListener(this);
        btDatePickerEnd.setOnClickListener(this);
        btTimePickerEnd.setOnClickListener(this);

        helper = new ProjectFormHelper(this);

        Intent intent = getIntent();
        long project_id = intent.getLongExtra("project_id", 0);

        if (project_id != 0) {
            ProjectDAO dao = new ProjectDAO(this);
            Project project = dao.get(project_id);
            helper.setProject(project);
        }
    }

    public void projectSubmit(View view) {
        Project project = helper.getProject();

        ProjectDAO dao = new ProjectDAO(this);
        if (project.getId() != 0)
            dao.update(project);
        else
            dao.insert(project);
        dao.close();

        Toast.makeText(this, project.toString(), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view == btDatePickerStart) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            etDatePickerStart.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (view == btTimePickerStart) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            etTimePickerStart.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }
}
