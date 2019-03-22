package com.example.pontes_stefane_esig.myapplication.helper;

import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

import com.example.pontes_stefane_esig.myapplication.R;
import com.example.pontes_stefane_esig.myapplication.model.Project;

public class ProjectFormHelper {

    private final EditText inputName;
    private Project project;

    public ProjectFormHelper(AppCompatActivity context) {
        inputName = context.findViewById(R.id.et_project_name);
        project = new Project();
    }

    public Project getProject() {
        project.setName(inputName.getText().toString());
        return project;
    }

    public void setProject(Project project) {
        inputName.setText(project.getName());
        this.project = project;
    }
}
