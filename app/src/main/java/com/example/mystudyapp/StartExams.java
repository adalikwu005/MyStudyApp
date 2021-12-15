package com.example.mystudyapp;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.Nullable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;


public class StartExams extends AppCompatActivity {
    private final int REQUEST_CODE_QUIZ = 1;
    public static String EXTRA_COURSES = "extraCourses";
    public static String EXTRA_CATEGORY_ID = "extraCategory";
    public static String EXTRA_CATEGORY_NAME = "extraCategoryName";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";

    private TextView textViewHighScore;
    private Spinner spinnerCourses;
    private Spinner spinnerCategory;


    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_exams);

        textViewHighScore = findViewById(R.id.text_view_highscore);
        spinnerCourses = findViewById(R.id.spinner_courses);
        spinnerCategory = findViewById(R.id.spinner_category);


        loadCourses();
        loadCategories();
        loadHighScore();

        Button buttonStartQuiz = findViewById(R.id.button_start_quiz);
        buttonStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startQuiz();
            }
        });
    }


    private void startQuiz() {

        //Spinner Controls on selected categories



        Category selectedCategory = (Category) spinnerCategory.getSelectedItem();
        int categoryID = selectedCategory.getId();

        String categoryName = selectedCategory.getName();
        String courses = spinnerCourses.getSelectedItem().toString();

        Intent intent = new Intent(StartExams.this, QuizActivity.class);
        intent.putExtra(EXTRA_CATEGORY_ID, categoryID);
        intent.putExtra(EXTRA_CATEGORY_NAME, categoryName);
        intent.putExtra(EXTRA_COURSES, courses);
        startActivityForResult(intent, REQUEST_CODE_QUIZ);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_QUIZ) {
            if (resultCode == RESULT_OK) {
                int score = data.getIntExtra(QuizActivity.EXTRA_SCORE, 0);
                if (score > highScore) {
                    updateHighScore(score);
                }
            }
        }
    }

    private void loadCategories() {
        QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
        List<Category>  categories = dbHelper.getAllCategories();

        ArrayAdapter<Category> adapterCategories = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, categories);
        spinnerCategory.setAdapter(adapterCategories);

    }

    private void loadCourses() {
        String[] courses = Question.getAllCourses();

        ArrayAdapter<String> adapterCourses = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, courses);
        adapterCourses.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(adapterCourses);

    }

    private void loadHighScore() {
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highScore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighScore.setText("HighScore: " + highScore);

    }

    private void updateHighScore(int highScoreNew) {
        highScore = highScoreNew;
        textViewHighScore.setText("HighScore: " + highScore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highScore);
        editor.apply();
    }

    }
