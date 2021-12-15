package com.example.mystudyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class AppDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    //Variable
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    MenuItem menuItem;
    CardView starExam, cgpaCalculator, crutechNewsForum, searchPastQuestion, visitSchoolSite, aboutUs;


    private Object toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_app_dashboard);

        /*-----------------------------Hooks------------------------------*/
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);

        //Dashboard Hooks
        starExam = findViewById(R.id.dashboard_start_exam);
        cgpaCalculator = findViewById(R.id.dashboard_cgpa_calculator);
        crutechNewsForum = findViewById(R.id.dashboard_crutech_news_forum);
        searchPastQuestion = findViewById(R.id.dashboard_search_question);
        visitSchoolSite = findViewById(R.id.dashboard_visit_school_website);
        aboutUs = findViewById(R.id.dashboard_about_us);

        /*-----------------------------Tool Bar------------------------------*/
        setSupportActionBar((androidx.appcompat.widget.Toolbar) toolbar);

        /*-----------------------------Navigation Drawer Menu------------------------------*/

        //Hide or show items

        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.nav_login).setVisible(false);
        menu.findItem(R.id.nav_logout).setVisible(true);
        menu.findItem(R.id.nav_profile).setVisible(true);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, (androidx.appcompat.widget.Toolbar) toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);

        //Dashboard items on select
        //Start Exams
        starExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nav_start = new Intent(AppDashboard.this, StartExams.class);
                startActivity(nav_start);
            }
        });

        //CGPA Calculator
        cgpaCalculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nav_cgpa_calculator = new Intent(AppDashboard.this, CgpaCalculator.class);
                startActivity(nav_cgpa_calculator);
            }
        });

        //Search for past questions
        searchPastQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent nav_search_for_past_questions = new Intent(AppDashboard.this, SearchPastquestion.class);
                startActivity(nav_search_for_past_questions);
            }
        });
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer((GravityCompat.START));
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                break;

            case R.id.nav_start_exams:
                Intent nav_start = new Intent(AppDashboard.this, StartExams.class);
                startActivity(nav_start);
                break;

            case R.id.nav_cgpa_calculator:
                Intent nav_cgpa_calculator = new Intent(AppDashboard.this, CgpaCalculator.class);
                startActivity(nav_cgpa_calculator);
                break;

            case R.id.nav_search:
                Intent nav_searchpastquestion = new Intent(AppDashboard.this, SearchPastquestion.class);
                startActivity(nav_searchpastquestion);
                break;

            case R.id.nav_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
