package com.example.pontes_stefane_esig.myapplication.activities;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.daos.ProjectDAO;
import com.example.pontes_stefane_esig.myapplication.helpers.ProjectHelper;
import com.example.pontes_stefane_esig.myapplication.models.Project;

import java.util.Calendar;

public class ProjectFormActivity extends AppCompatActivity implements View.OnClickListener {

    private ProjectHelper helper;

    private Button btDatePickerStart;
    private Button btTimePickerStart;
    private Button btDatePickerEnd;
    private Button btTimePickerEnd;

    private EditText etDatePickerStart;
    private EditText etTimePickerStart;
    private EditText etDatePickerEnd;
    private EditText etTimePickerEnd;

    //TODO when I'm updating, the time that is shown is the current time, not the previous time set
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

        helper = new ProjectHelper(this);

        Intent intent = getIntent();
        long project_id = intent.getLongExtra("project_id", 0);

        String title;

        if (project_id != 0) {
            ProjectDAO dao = new ProjectDAO(this);
            Project project = dao.get(project_id);
            helper.setProject(project);

            title = getString(R.string.project_edit);
        } else {
            title = getString(R.string.project_new);
        }

        setTitle(title);
    }

    public void projectSubmit() {
        if (helper.isOk()) {
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
    }

    //TODO heure début ne pourra pas être après l'heure fin
    @Override
    public void onClick(View view) {

        if (view == btDatePickerStart) {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            etDatePickerStart.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (view == btTimePickerStart) {
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);

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

        if (view == btDatePickerEnd) {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                            etDatePickerEnd.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (view == btTimePickerEnd) {
            final Calendar c = Calendar.getInstance();
            int mHour = c.get(Calendar.HOUR_OF_DAY);
            int mMinute = c.get(Calendar.MINUTE);

            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            etTimePickerEnd.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.project_form, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_form_ok)
            projectSubmit();
        return super.onOptionsItemSelected(item);
    }
}
