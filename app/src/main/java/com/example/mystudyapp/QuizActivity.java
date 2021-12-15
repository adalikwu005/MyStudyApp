package com.example.mystudyapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;


public class QuizActivity extends AppCompatActivity {
    public static final String EXTRA_SCORE = "extraScore";
    private static final long COUNTDOWN_IN_MILLS = 30000;


    //Values to save when device is rotated
    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILLIS_LEFT = "keyMillisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    private TextView textViewQuestion;
    private TextView textViewAnswer;
    private ImageView imageViewCorrectWrong;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private TextView textViewCategory;
    private TextView textViewCountdown;
    private TextView textViewcourses;
    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private Button buttonConfirmNext;

    private Handler handler = new Handler();
    private ImageButton btReadText;
    private TextToSpeech mTTS;

    private ColorStateList textColorDefaultRb;
    private ColorStateList getTextColorDefaultCd;

    private CountDownTimer countDownTimer;
    private long timeLeftInMillis;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private long backPressedTime;
    TextView tvImageCheckQues;
    ImageView imageLink;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        imageLink = findViewById(R.id.image_link);
        tvImageCheckQues = findViewById(R.id.tvImageQuesCheck);

        //Read Questions using TextToSpeech
        btReadText = findViewById(R.id.icon_read_text);
        mTTS = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = mTTS.setLanguage(Locale.UK);

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        btReadText.setEnabled(true);
                    }
                } else {
                    Log.e("TTS", "Initialization failed");
                }
            }
        });

        btReadText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btReadText.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.enable_read_text));
                        speak();
                    }
                }, 500);

            }
        });

        //Text views
        textViewQuestion = findViewById(R.id.text_view_question);
        imageViewCorrectWrong = findViewById(R.id.image_correct_wrong);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        textViewCountdown = findViewById(R.id.text_view_countdown);
        textViewcourses = findViewById(R.id.text_view_courses);
        textViewCategory = findViewById(R.id.text_view_category);
        textViewAnswer = findViewById(R.id.text_view_answer);

        //Option button
        rbGroup = findViewById(R.id.radio_group);
        rb1 = findViewById(R.id.radio_button1);
        rb2 = findViewById(R.id.radio_button2);
        rb3 = findViewById(R.id.radio_button3);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);

        //Text Color
        textColorDefaultRb = rb1.getHintTextColors();
        getTextColorDefaultCd = textViewCountdown.getTextColors();

        Intent intent = getIntent();
        int categoryID = intent.getIntExtra(StartExams.EXTRA_CATEGORY_ID, 0);
        String categoryName = intent.getStringExtra(StartExams.EXTRA_CATEGORY_NAME);
        String courses = intent.getStringExtra(StartExams.EXTRA_COURSES);


        //Level of student should be added
        textViewCategory.setText("Category: " + categoryName);
        textViewcourses.setText("Course: " + courses);

        if (savedInstanceState == null) {
            QuizDbHelper dbHelper = QuizDbHelper.getInstance(this);
            questionList = dbHelper.getQuestions(categoryID, courses);
            questionCountTotal = questionList.size();
            Collections.shuffle(questionList);
            textViewAnswer.setVisibility(View.GONE);
            imageViewCorrectWrong.setVisibility(View.GONE);
            imageLink.setVisibility(View.GONE);

            showNextQuestion();

        } else {
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            questionCountTotal = questionList.size();
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            score = savedInstanceState.getInt(KEY_SCORE);
            timeLeftInMillis = savedInstanceState.getLong(KEY_MILLIS_LEFT);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if (!answered) {
                startCountDown();
            } else {
                updateCountDownText();
                showSolution();
            }
        }

        buttonConfirmNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });
    }


    private void showNextQuestion() {
        rb1.setTextColor(textColorDefaultRb);
        rb2.setTextColor(textColorDefaultRb);
        rb3.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();

        textViewAnswer.setVisibility(View.INVISIBLE);
        imageViewCorrectWrong.setVisibility(View.INVISIBLE);

        rb1.setClickable(true);
        rb2.setClickable(true);
        rb3.setClickable(true);


        if (questionCounter < questionCountTotal) {
            currentQuestion = questionList.get(questionCounter);

            //tvImageCheckQues.setText(currentQuestion.getImageLink());

            textViewQuestion.setText(currentQuestion.getQuestion());

            if (currentQuestion.getImageLink() != null) {
                imageLink.setImageResource(R.drawable.correct_answer);
                imageLink.setVisibility(View.VISIBLE);
            } else {
                imageLink.setVisibility(View.GONE);
            }


            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            questionCounter++;
            textViewQuestionCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Confirm");

            timeLeftInMillis = COUNTDOWN_IN_MILLS;
            startCountDown();
        } else {
            finishQuiz();
        }
    }

    private void startCountDown() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMillis = 0;
                updateCountDownText();
                checkAnswer();
            }
        }.start();
    }

    private void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewCountdown.setText(timeFormatted);

        if (timeLeftInMillis < 10000) {
            textViewCountdown.setTextColor(Color.RED);
        } else {
            textViewCountdown.setTextColor(getTextColorDefaultCd);
        }
    }

    private void checkAnswer() {
        answered = true;
        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentQuestion.getAnswerNr()) {
            score++;
            textViewScore.setText("Score: " + score);
            imageViewCorrectWrong.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.correct_answer));
        } else {
            imageViewCorrectWrong.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.wrong_answer));
        }
        showSolution();
    }

    private void showSolution() {

        rb1.setClickable(false);
        rb2.setClickable(false);
        rb3.setClickable(false);

        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        textViewAnswer.setVisibility(View.INVISIBLE);

        switch (currentQuestion.getAnswerNr()) {
            case 1:
                rb1.setTextColor(Color.GREEN);

                textViewQuestion.setText(currentQuestion.getQuestion());
                currentQuestion.getAnswerNr();

                if (currentQuestion.getAnswerNr() == 1) {
                    textViewAnswer.setVisibility(View.VISIBLE);
                    imageViewCorrectWrong.setVisibility(View.VISIBLE);
                    textViewAnswer.setText("Answer:\n" + currentQuestion.getOption1());
                } else {
                    textViewAnswer.setText(null);
                }
                //  textViewQuestion.setText("Answer 1 is correct");
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                textViewQuestion.setText(currentQuestion.getQuestion());
                currentQuestion.getAnswerNr();

                if (currentQuestion.getAnswerNr() == 2) {
                    textViewAnswer.setVisibility(View.VISIBLE);
                    imageViewCorrectWrong.setVisibility(View.VISIBLE);
                    textViewAnswer.setText("Answer:\n" + currentQuestion.getOption2());
                } else {
                    textViewAnswer.setText(null);
                }
                //   textViewQuestion.setText("Answer 2 is correct");
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
                imageViewCorrectWrong.setVisibility(View.VISIBLE);
                textViewQuestion.setText(currentQuestion.getQuestion());
                currentQuestion.getAnswerNr();

                if (currentQuestion.getAnswerNr() == 3) {
                    textViewAnswer.setVisibility(View.VISIBLE);
                    textViewAnswer.setText("Answer:\n" + currentQuestion.getOption3());
                } else {
                    textViewAnswer.setText(null);
                }
                //   textViewQuestion.setText("Answer 3 is correct");
                break;
        }

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Next");
        } else {
            buttonConfirmNext.setText("Finish");
        }
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishQuiz();
        } else {
            Toast.makeText(this, "Press back again to finish", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {

        if (mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }

        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESTION_COUNT, questionCounter);
        outState.putLong(KEY_MILLIS_LEFT, timeLeftInMillis);
        outState.putBoolean(KEY_ANSWERED, answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }

    private void speak() {
        String text = textViewQuestion.getText().toString();
        // String text2 = textViewAnswer.getText().toString();
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        // mTTS.speak(text2, TextToSpeech.QUEUE_FLUSH,null);
    }


}
