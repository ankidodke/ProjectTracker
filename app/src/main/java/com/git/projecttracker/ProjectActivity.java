package com.git.projecttracker;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProjectActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView rcyProjectDetails;
    RecyclerView.LayoutManager layoutManager;
    ProjectAdapter projectAdapter;
    ArrayList<ProjectModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.project_activity);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        rcyProjectDetails = findViewById(R.id.rcyProject);
        layoutManager = new LinearLayoutManager(this);
        rcyProjectDetails.setLayoutManager(layoutManager);
        projectAdapter = new ProjectAdapter(list);
        rcyProjectDetails.setAdapter(projectAdapter);

        list.add(new ProjectModel("project name : anki", "project desc: aaa", "Date :aaaa"));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}