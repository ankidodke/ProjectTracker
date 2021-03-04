package com.git.projecttracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    DrawerLayout drawerLayout;
    ImageView imgMenu, imgClose;

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);
        imgMenu = findViewById(R.id.imgDrawer);
        imgClose = findViewById(R.id.imgClose);
        drawerLayout = findViewById(R.id.drawerLayout);
        imgMenu.setOnClickListener(this);
        imgClose.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == imgMenu) {
            openDrawer(drawerLayout);
        } else if (view == imgClose) {
            closeDrawer(drawerLayout);
        }
    }

    public void ClickHome(View view) {
        closeDrawer(drawerLayout);
    }

    public void ClickProjcet(View view) {
        startActivity(new Intent(this, ProjectActivity.class));
        closeDrawer(drawerLayout);
    }

    public void ClickTask(View view) {

    }
}