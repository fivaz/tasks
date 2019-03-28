package com.example.pontes_stefane_esig.myapplication.helpers;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.models.Project;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ProjectFormHelper {

    private final EditText inputName;
    private final EditText inputStartDate;
    private final EditText inputStartTime;
    private final EditText inputEndDate;
    private final EditText inputEndTime;
    private Project project;
    private DateFormat dateFormat;

    public ProjectFormHelper(AppCompatActivity context) {
        dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm", Locale.FRENCH);

        inputName = context.findViewById(R.id.et_project_name);
        inputStartDate = context.findViewById(R.id.et_start_date);
        inputStartTime = context.findViewById(R.id.et_start_time);
        inputEndDate = context.findViewById(R.id.et_end_date);
        inputEndTime = context.findViewById(R.id.et_end_time);
        project = new Project();
    }

    public Project getProject() {
        try {
            Date start_date_formatted = dateFormat.parse(inputStartDate.getText().toString() + " " + inputStartTime.getText().toString());

            Date end_date_formatted = dateFormat.parse(inputEndDate.getText().toString() + " " + inputEndTime.getText().toString());

            project.setName(inputName.getText().toString());
            project.setStart_at(start_date_formatted);
            project.setEnd_at(end_date_formatted);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return project;
    }

    public void setProject(Project project) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.FRENCH);
        DateFormat timeFormat = new SimpleDateFormat("hh:mm", Locale.FRENCH);

        inputName.setText(project.getName());

        inputStartDate.setText(dateFormat.format(project.getStart_at()));
        inputStartTime.setText(timeFormat.format(project.getStart_at()));

        inputEndDate.setText(dateFormat.format(project.getEnd_at()));
        inputEndTime.setText(timeFormat.format(project.getEnd_at()));

        this.project = project;
    }
}
