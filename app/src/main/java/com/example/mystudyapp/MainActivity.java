package com.example.mystudyapp;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    //Variables
 //   Animation topAnim, bottomAnim;
   // ImageView appLogo;
    //TextView appName, appSlogan;

   // Toolbar toolbar;

    private static int SPLASH_TIME_OUT = 4000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);



        //Animations

      //  topAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        //bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);


        //Hooks
       // appLogo = findViewById(R.id.app_image_logo);
        //appName = findViewById(R.id.dashboard_app_name);
        //appSlogan = findViewById(R.id.text_slogan_name);

        //appLogo.setAnimation(topAnim);
        //appName.setAnimation(bottomAnim);
       // appSlogan.setAnimation(bottomAnim);


    /*    new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, AppDashboard.class);

                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair<View, String>(appLogo, "app_image_logo");
                pairs[1] = new Pair<View, String>(appName, "dashboard_app_name");

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, pairs);
                    startActivity(intent, options.toBundle());
                }

            }
        }, SPLASH_TIME_OUT);*/


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(MainActivity.this, AppDashboard.class);
                startActivity(homeIntent);
                finish();
            }
        }, SPLASH_TIME_OUT);


    }

}
