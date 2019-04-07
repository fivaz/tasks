package com.example.pontes_stefane_esig.myapplication.helpers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.models.Project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProjectHelper {

    private final EditText inputName;
    private final EditText inputStartDate;
    private final EditText inputStartTime;
    private final EditText inputEndDate;
    private final EditText inputEndTime;
    private Context context;
    private Project project;

    private static String datePattern = "dd/MM/yyyy";
    private static String timePattern = "HH:mm";
    private static String dateTimePattern = datePattern + " " + timePattern;

    public ProjectHelper(AppCompatActivity context) {
        inputName = context.findViewById(R.id.et_project_name);
        inputStartDate = context.findViewById(R.id.et_start_date);
        inputStartTime = context.findViewById(R.id.et_start_time);
        inputEndDate = context.findViewById(R.id.et_end_date);
        inputEndTime = context.findViewById(R.id.et_end_time);
        this.context = context;
        project = new Project();
    }

    public Project getProject() {
        DateFormat dateTimeFormat = new SimpleDateFormat(dateTimePattern, Locale.FRENCH);

        String name = inputName.getText().toString();
        String startDateString = inputStartDate.getText().toString();
        String startTimeString = inputStartTime.getText().toString();
        String endDateString = inputStartDate.getText().toString();
        String endTimeString = inputEndTime.getText().toString();

        try {
            Date endDate = dateTimeFormat.parse(endDateString + " " + endTimeString);
            Date startDate = dateTimeFormat.parse(startDateString + " " + startTimeString);

            project.setName(name);
            project.setStart_at(startDate);
            project.setEnd_at(endDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return project;
    }

    public void setProject(Project project) {
        inputName.setText(project.getName());

        DateFormat dateFormat = new SimpleDateFormat(datePattern, Locale.FRENCH);
        DateFormat timeFormat = new SimpleDateFormat(timePattern, Locale.FRENCH);

        inputStartDate.setText(dateFormat.format(project.getStart_at()));
        inputStartTime.setText(timeFormat.format(project.getStart_at()));

        inputEndDate.setText(dateFormat.format(project.getEnd_at()));
        inputEndTime.setText(timeFormat.format(project.getEnd_at()));

        this.project = project;
    }

    public boolean isOk() {
        String name = inputName.getText().toString();
        String startDate = inputStartDate.getText().toString();
        String startTime = inputStartTime.getText().toString();
        String endDate = inputEndDate.getText().toString();
        String endTime = inputEndTime.getText().toString();

        if (name.isEmpty()) {
            String message = context.getString(R.string.error_msg_name_required);
            inputName.setError(message);
            return false;
        }
        if (startDate.isEmpty()) {
            String message = context.getString(R.string.error_msg_start_date_required);
            inputStartDate.setError(message);
            return false;
        }
        if (startTime.isEmpty()) {
            String message = context.getString(R.string.error_msg_start_hour_required);
            inputStartTime.setError(message);
            return false;
        }
        if (endDate.isEmpty()) {
            String message = context.getString(R.string.error_msg_end_date_required);
            inputEndDate.setError(message);
            return false;
        }
        if (endTime.isEmpty()) {
            String message = context.getString(R.string.error_msg_end_hour_required);
            inputEndTime.setError(message);
            return false;
        }
        return true;
    }
}
