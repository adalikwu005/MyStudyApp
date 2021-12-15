package com.example.mystudyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CgpaCalculator extends AppCompatActivity {

    Button test;
    TextView cgpa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgpa_calculator);

        test = findViewById(R.id.test);
        cgpa = findViewById(R.id.cgpa);

        cgpa.setText("CGPA Calculator");

        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cgpa.getText().toString().equals("CGPA Calculator")){
                    cgpa.setText("Sorry Not the Same");

                } else {
                    cgpa.setText("Welcome to CGPA CLASS");
                }

            }
        });
    }

}
