package com.example.mystudyapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.example.mystudyapp.QuizContract.*;


import java.util.ArrayList;
import java.util.List;


public class QuizDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "MyAweSomeQuiz.db";
    private static final int DATABASE_VERSION = 1;

    private static QuizDbHelper instance;

    private SQLiteDatabase db;

    private QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static synchronized QuizDbHelper getInstance(Context context) {
        if (instance == null) {
            instance = new QuizDbHelper(context.getApplicationContext());
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;

        final String SQL_CREATE_CATEGORIES_TABLE = "CREATE TABLE " +
                CategoriesTable.TABLE_NAME + "( " +
                CategoriesTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoriesTable.COLUMN_NAME + " TEXT " +
                ")";

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionTable.TABLE_NAME + " ( " +
                QuestionTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionTable.COLUMN_QUESTION + " TEXT, " +
                QuestionTable.COLUMN_IMAGE + " TEXT, " +
                QuestionTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionTable.COLUMN_ANSWER_NR + " INTEGER, " +
                QuestionTable.COLUMN_COURSES + " TEXT, " +
                QuestionTable.COLUMN_CATEGORY_ID + " INTEGER, " +
                "FOREIGN KEY(" + QuestionTable.COLUMN_CATEGORY_ID + ") REFERENCES " +
                CategoriesTable.TABLE_NAME + "(" + CategoriesTable._ID + ")" + "ON DELETE CASCADE" +
                ")";

        db.execSQL(SQL_CREATE_CATEGORIES_TABLE);
        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillCategoriesTable();
        fillQuestionsTable();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + CategoriesTable.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + QuestionTable.TABLE_NAME);
        onCreate(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    private void fillCategoriesTable() {
        //Year One
        categoryFirstSemesterYear1();
        categorySecondSemesterYear1();
/*      //Year Two
        categoryFirstSemesterYear2();
        categorySecondSemesterYear2();

        //Year Three
        categoryFirstSemesterYear3();
        categorySecondSemesterYear3();

        //Year Four
        categoryFirstSemesterYear4();
        categorySecondSemesterYear4();
*/
    }

    //Year One CATEGORIES
    private void categoryFirstSemesterYear1() {
        Category c1 = new Category("INTRO TO PROBLEM SOLVING");
        addCategory(c1);
        Category c2 = new Category("INTRO TO COMPUTER SCIENCE");
        addCategory(c2);
        Category c3 = new Category("GENERAL MATHEMATICS I");
        addCategory(c3);
        Category c4 = new Category("GENERAL PHYSICS");
        addCategory(c4);
        Category c5 = new Category("PRACTICAL PHYSICS ");
        addCategory(c5);
        Category c6 = new Category("GENERAL CHEMISTRY ");
        addCategory(c6);
        Category c7 = new Category("GENERAL BIOLOGY");
        addCategory(c7);
        Category c8 = new Category("USE OF ENGLISH AND COMMUNICATION SKILLS");
        addCategory(c8);
        Category c9 = new Category("PHILOSOPHY AND LOGIC");
        addCategory(c9);
    }
    private void categorySecondSemesterYear1() {
        Category c1 = new Category("INTRO TO CSC USING BASIC");
        addCategory(c1);
        Category c2 = new Category("PASCAL PROGRAMMING");
        addCategory(c2);
        Category c3 = new Category("INTRODUCTION TO INTERNET");
        addCategory(c3);
        Category c4 = new Category("GENERAL MATHEMATICS II");
        addCategory(c4);
        Category c5 = new Category("GENERAL MATHEMATICS III");
        addCategory(c5);
        Category c6 = new Category("GENERAL PHYSICS II");
        addCategory(c6);
        Category c7 = new Category("INTRODUCTION TO STATISTICS");
        addCategory(c7);
        Category c8 = new Category("USE OF ENGLISH AND COMMUNICATION SKILLS II");
        addCategory(c8);
        Category c9 = new Category("NIGERIAN PEOPLE AND CULTURE");
        addCategory(c9);
        Category c10 = new Category("HISTORY AND PHILOSOPHY OF SCIENCE");
        addCategory(c10);
    }

/*
    //Year Two
    private void categoryFirstSemesterYear2() {
        Category c1 = new Category("INTRO TO PROBLEM SOLVING");
        addCategory(c1);
        Category c2 = new Category("INTRO TO COMPUTER SCIENCE");
        addCategory(c2);
        Category c3 = new Category("GENERAL MATHEMATICS I");
        addCategory(c3);
        Category c4 = new Category("GENERAL PHYSICS");
        addCategory(c4);
        Category c5 = new Category("PRACTICAL PHYSICS ");
        addCategory(c5);
        Category c6 = new Category("GENERAL CHEMISTRY ");
        addCategory(c6);
        Category c7 = new Category("GENERAL BIOLOGY");
        addCategory(c7);
        Category c8 = new Category("USE OF ENGLISH AND COMMUNICATION SKILLS");
        addCategory(c8);
        Category c9 = new Category("PHILOSOPHY AND LOGIC");
        addCategory(c9);
    }
    private void categorySecondSemesterYear2() {
        Category c1 = new Category("INTRO TO CSC USING BASIC");
        addCategory(c1);
        Category c2 = new Category("PASCAL PROGRAMMING");
        addCategory(c2);
        Category c3 = new Category("INTRODUCTION TO INTERNET");
        addCategory(c3);
        Category c4 = new Category("GENERAL MATHEMATICS II");
        addCategory(c4);
        Category c5 = new Category("GENERAL MATHEMATICS III");
        addCategory(c5);
        Category c6 = new Category("GENERAL PHYSICS II");
        addCategory(c6);
        Category c7 = new Category("INTRODUCTION TO STATISTICS");
        addCategory(c7);
        Category c8 = new Category("USE OF ENGLISH AND COMMUNICATION SKILLS II");
        addCategory(c8);
        Category c9 = new Category("NIGERIAN PEOPLE AND CULTURE");
        addCategory(c9);
        Category c10 = new Category("HISTORY AND PHILOSOPHY OF SCIENCE");
        addCategory(c10);
    }
    //Year Three
    private void categoryFirstSemesterYear3() {
        Category c1 = new Category("INTRO TO PROBLEM SOLVING");
        addCategory(c1);
        Category c2 = new Category("INTRO TO COMPUTER SCIENCE");
        addCategory(c2);
        Category c3 = new Category("GENERAL MATHEMATICS I");
        addCategory(c3);
        Category c4 = new Category("GENERAL PHYSICS");
        addCategory(c4);
        Category c5 = new Category("PRACTICAL PHYSICS ");
        addCategory(c5);
        Category c6 = new Category("GENERAL CHEMISTRY ");
        addCategory(c6);
        Category c7 = new Category("GENERAL BIOLOGY");
        addCategory(c7);
        Category c8 = new Category("USE OF ENGLISH AND COMMUNICATION SKILLS");
        addCategory(c8);
        Category c9 = new Category("PHILOSOPHY AND LOGIC");
        addCategory(c9);
    }
    private void categorySecondSemesterYear3() {
        Category c1 = new Category("INTRO TO CSC USING BASIC");
        addCategory(c1);
        Category c2 = new Category("PASCAL PROGRAMMING");
        addCategory(c2);
        Category c3 = new Category("INTRODUCTION TO INTERNET");
        addCategory(c3);
        Category c4 = new Category("GENERAL MATHEMATICS II");
        addCategory(c4);
        Category c5 = new Category("GENERAL MATHEMATICS III");
        addCategory(c5);
        Category c6 = new Category("GENERAL PHYSICS II");
        addCategory(c6);
        Category c7 = new Category("INTRODUCTION TO STATISTICS");
        addCategory(c7);
        Category c8 = new Category("USE OF ENGLISH AND COMMUNICATION SKILLS II");
        addCategory(c8);
        Category c9 = new Category("NIGERIAN PEOPLE AND CULTURE");
        addCategory(c9);
        Category c10 = new Category("HISTORY AND PHILOSOPHY OF SCIENCE");
        addCategory(c10);
    }
    //Year Four
    private void categoryFirstSemesterYear4() {
        Category c1 = new Category("INTRO TO PROBLEM SOLVING");
        addCategory(c1);
        Category c2 = new Category("INTRO TO COMPUTER SCIENCE");
        addCategory(c2);
        Category c3 = new Category("GENERAL MATHEMATICS I");
        addCategory(c3);
        Category c4 = new Category("GENERAL PHYSICS");
        addCategory(c4);
        Category c5 = new Category("PRACTICAL PHYSICS ");
        addCategory(c5);
        Category c6 = new Category("GENERAL CHEMISTRY ");
        addCategory(c6);
        Category c7 = new Category("GENERAL BIOLOGY");
        addCategory(c7);
        Category c8 = new Category("USE OF ENGLISH AND COMMUNICATION SKILLS");
        addCategory(c8);
        Category c9 = new Category("PHILOSOPHY AND LOGIC");
        addCategory(c9);
    }
    private void categorySecondSemesterYear4() {
        Category c1 = new Category("INTRO TO CSC USING BASIC");
        addCategory(c1);
        Category c2 = new Category("PASCAL PROGRAMMING");
        addCategory(c2);
        Category c3 = new Category("INTRODUCTION TO INTERNET");
        addCategory(c3);
        Category c4 = new Category("GENERAL MATHEMATICS II");
        addCategory(c4);
        Category c5 = new Category("GENERAL MATHEMATICS III");
        addCategory(c5);
        Category c6 = new Category("GENERAL PHYSICS II");
        addCategory(c6);
        Category c7 = new Category("INTRODUCTION TO STATISTICS");
        addCategory(c7);
        Category c8 = new Category("USE OF ENGLISH AND COMMUNICATION SKILLS II");
        addCategory(c8);
        Category c9 = new Category("NIGERIAN PEOPLE AND CULTURE");
        addCategory(c9);
        Category c10 = new Category("HISTORY AND PHILOSOPHY OF SCIENCE");
        addCategory(c10);
    }

*/

    private void addCategory(Category category) {
        ContentValues cv = new ContentValues();
        cv.put(CategoriesTable.COLUMN_NAME, category.getName());
        db.insert(CategoriesTable.TABLE_NAME, null, cv);
    }

    private void fillQuestionsTable() {
        filQuestionsIntroToProblemSolving();
        fillQuestionsIntroToCsc();
    }

    //YEAR ONE FIRST SEMESTER
    private void filQuestionsIntroToProblemSolving() {
        Question q1 = new Question("The sequence of instructions within an algorithm or program is referred as _______", null, "A", "B", "B", 3, Question.COURSE_CSC_1101, Category.INTRO_TO_PROBLEM_SOLVING);
        addQuestion(q1);
        Question q2 = new Question("This term refers in meaning to a named store of value in algorithm program _______", null, "A", "B", "C", 3, Question.COURSE_CSC_1101, Category.INTRO_TO_PROBLEM_SOLVING);
        addQuestion(q2);
        Question q3 = new Question("All algorithms are programs, but not all programs are algorithms _______", null, "A", "B", "C", 3, Question.COURSE_CSC_1101, Category.INTRO_TO_PROBLEM_SOLVING);
        addQuestion(q3);
        Question q4 = new Question("This refers to the consideration of logical operation independent of actual physical implementation _______", null, "True", "False", "C", 3, Question.COURSE_CSC_1101, Category.INTRO_TO_PROBLEM_SOLVING);
        addQuestion(q4);
        Question q5 = new Question("A _______ is a scheme for organizing data within computing system, or a particular implementation of any such scheme.", null, "True", "Chukwudi", "Chukwuemeka", 3, Question.COURSE_CSC_1101, Category.INTRO_TO_PROBLEM_SOLVING);
        addQuestion(q5);
        Question q6 = new Question("An _______ is a style in which an algorithm is designed so as to achieve certain types of control flow, and by so doing meet certain design requirements for that algorithm.", null, "True", "Chukwudi", "Chukwuemeka", 3, Question.COURSE_CSC_1101, Category.INTRO_TO_PROBLEM_SOLVING);
        addQuestion(q6);
        Question q7 = new Question("The shortest control flow path through an algorithm comprises its _______ case.", null, "True", "Chukwudi", "Chukwuemeka", 3, Question.COURSE_CSC_1101, Category.INTRO_TO_PROBLEM_SOLVING);
        addQuestion(q7);
        Question q8 = new Question("A modular number system is one in which numbers are considered in terms of _________ relative to a common base.", null, "True", "Chukwudi", "Chukwuemeka", 3, Question.COURSE_CSC_1101, Category.INTRO_TO_PROBLEM_SOLVING);
        addQuestion(q8);
        Question q9 = new Question("This term, hinting at something fake, refers to the literal representation of algorithms in design __________.", null, "True", "Chukwudi", "Chukwuemeka", 3, Question.COURSE_CSC_1101, Category.INTRO_TO_PROBLEM_SOLVING);
        addQuestion(q9);
        Question q10 = new Question("This LATIN term is used to describe the logical analysis of algorithm performance _________", null, "True", "Chukwudi", "Chukwuemeka", 3, Question.COURSE_CSC_1101, Category.INTRO_TO_PROBLEM_SOLVING);
        addQuestion(q10);
        Question q11 = new Question("Outline, then describe the FOUR stages of the Problem Solving Process, using a problem you have drawn up. Be original with this.", null, "True", "Chukwudi", "Chukwuemeka", 3, Question.COURSE_CSC_1101, Category.INTRO_TO_PROBLEM_SOLVING);
        addQuestion(q11);
        Question q12 = new Question("Describe TWO algorithm design patterns you can recall, highlighting, with examples:\na. Their similarities; and\nb. The difference between them", null, "True", "Chukwudi", "Chukwuemeka", 3, Question.COURSE_CSC_1101, Category.INTRO_TO_PROBLEM_SOLVING);
        addQuestion(q12);
        Question q13 = new Question("Write a simple algorithm to determine the smallest element in an array of integers.", null, "True", "Chukwudi", "Chukwuemeka", 3, Question.COURSE_CSC_1101, Category.INTRO_TO_PROBLEM_SOLVING);
        addQuestion(q13);
        Question q14 = new Question("Write a simple algorithm to find the lowest common multiple of two integers", null, "True", "Chukwudi", "Chukwuemeka", 3, Question.COURSE_CSC_1101, Category.INTRO_TO_PROBLEM_SOLVING);
        addQuestion(q14);
        Question q15 = new Question("COMPUTE the following:\nI.\tBinary: 11110102\nII.\tOctal\t________\nIII.\tDecimal\t________\nIV.\tHexadecimal\t________", null, "A", "B", "C", 2, Question.COURSE_CSC_1101, Category.INTRO_TO_PROBLEM_SOLVING);
        addQuestion(q15);
    }
    private void fillQuestionsIntroToCsc() {
        Question q1 = new Question("The first high level programming language used in the computer ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q1);
        Question q2 = new Question("Microprocessor was a technology of the ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q2);
        Question q3 = new Question("___________ generation of computer began the use of keyboard as input device.", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q3);
        Question q4 = new Question("COMPUTE the following:\nI. Binary: 1111010^2\nII. Octal ___________\nIII. Decimal ___________\nIV. Hexadecimal ___________", null, "A", "B", "C", 1, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q4);
        Question q5 = new Question("COMPUTE the following:\nI. Binary ___________\nII. Octal ___________\nIII. Decimal: 114^10\nIV. Hexadecimal ___________", null, "A", "B", "C", 1, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q5);
        Question q6 = new Question("COMPUTE the following:\nI. Binary ___________\nII. Octal: 324^8\nIII. Decimal ___________\nIV. Hexadecimal: ABC^16", null, "A", "B", "C", 1, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q6);
        Question q7 = new Question("COMPUTE the following:\nI. Binary ___________\nII. Octal ___________\nIII. Decimal ___________\nIV. Hexadecimal ___________", null, "A", "B", "C", 1, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q7);
        Question q8 = new Question("Data that cannot be sub divided is called ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q8);
        Question q9 = new Question("Find the sum of the following:\n1112 + 101112 ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q9);
        Question q10 = new Question("Find the sum of the following:\n1258 + 1618 ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q10);
        Question q11 = new Question("Find the sum of the following:\n4A2316 + BC9116 ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q11);
        Question q12 = new Question("Find the sum of the following:\n111112 + 100012 ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q12);
        Question q13 = new Question("Subtract the following:\n71708 - 57768 ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q13);
        Question q14 = new Question("Subtract the following:\nAA238 - 9B778 ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q14);
        Question q15 = new Question("___________ is a device that is used to segment a large computer network into two.", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q15);
        Question q16 = new Question("Typically, hard disk drives employ ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q16);
        Question q17 = new Question("___________ is a memory chip that stores data permanently and is unchangeable.", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q17);
        Question q18 = new Question("A ___________ is a special message in ring topology used for transmission.", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q18);
        Question q19 = new Question("A collection of related programs that function together is called ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q19);
        Question q20 = new Question("The internal hardware components of the central processing unit include:", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q20);
        Question q21 = new Question("What does TCP/IP mean ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q21);
        Question q22 = new Question("Bit is the acronym for ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q22);
        Question q23 = new Question("We use ___________ program to look through the internet.", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q23);
        Question q24 = new Question("What is the full meaning of MODEM", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q24);
        Question q25 = new Question("The device that translate information from one network to another is called ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q25);
        Question q26 = new Question("In a computer network, the transmissions medium can be ___________ and ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q26);
        Question q27 = new Question("The steps in developing a computer program and it diagrammatic representation are called ___________ and ___________ respectively.", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q27);
        Question q28 = new Question("Telephone transmission is an example of ___________ transmission", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q28);
        Question q29 = new Question("In the history of computer the oldest device in computing is ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q29);
        Question q30 = new Question("The first calculator was built by ___________ in 1642.", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q30);
        Question q31 = new Question("The first large scale digital electronic computer is called ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q31);
        Question q32 = new Question("The first commercially available computer was ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q32);
        Question q33 = new Question("How many kilobytes make one byte ___________", null, "A", "B", "C", 2, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q33);
        Question q34 = new Question("Network hardware devices include the following:\nI. __________\nII. __________\nIII. __________\nIV. __________", null, "A", "B", "C", 1, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q34);
        Question q35 = new Question("Apart from computer virus, what are the other hardware threats?\nI. __________\nII. __________\nIII. __________\nIV. __________", null, "A", "B", "C", 1, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q35);
        Question q36 = new Question("In terms of purpose computer can classified into:\nI. __________\nII. __________\nIII. __________\nIV. __________", null, "A", "B", "C", 1, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q36);
        Question q37 = new Question("What are the equivalents of the following human organs in a computer accordingly: SKIN, HEART and BRAIN\nI. __________\nII. __________\nIII. __________\nIV. __________", null, "A", "B", "C", 1, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q37);
        Question q38 = new Question("The activities involve in data processing include:\nI. __________\nII. __________\nIII. __________\nIV. __________", null, "A", "B", "C", 1, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q38);
        Question q39 = new Question("Give four examples of expert systems software.\nI. __________\nII. __________\nIII. __________\nIV. __________", null, "Anna\nAnna\nAnna\nAnna", "Benedict,Benedict,Benedict,Benedict", "C", 1, Question.COURSE_CSC_1103, Category.INTRO_TO_COMPUTER_SC);
        addQuestion(q39);

    }

    //YEAR TWO SECOND SEMESTER


    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionTable.COLUMN_IMAGE, question.getImageLink());
        cv.put(QuestionTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        cv.put(QuestionTable.COLUMN_COURSES, question.getCourses());
        cv.put(QuestionTable.COLUMN_CATEGORY_ID, question.getCategoryID());
        db.insert(QuestionTable.TABLE_NAME, null, cv);

    }

    public List<Category> getAllCategories() {
        List<Category> categoryList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + CategoriesTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(c.getInt(c.getColumnIndex(CategoriesTable._ID)));
                category.setName(c.getString(c.getColumnIndex(CategoriesTable.COLUMN_NAME)));
                categoryList.add(category);
            } while (c.moveToNext());
        }

        c.close();
        return categoryList;
    }

    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setImageLink(c.getString(c.getColumnIndex(QuestionTable.COLUMN_IMAGE)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setCourses(c.getString(c.getColumnIndex(QuestionTable.COLUMN_COURSES)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);

            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

    public ArrayList<Question> getQuestions(int categoryID, String courses) {
        ArrayList<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();

        String selection = QuestionTable.COLUMN_CATEGORY_ID + " = ? " +
                " AND " + QuestionTable.COLUMN_COURSES + " = ? ";
        String[] selectionArgs = new String[]{String.valueOf(categoryID), courses};

        Cursor c = db.query(
                QuestionTable.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setId(c.getInt(c.getColumnIndex(QuestionTable._ID)));
                question.setQuestion(c.getString(c.getColumnIndex(QuestionTable.COLUMN_QUESTION)));
                question.setImageLink(c.getString(c.getColumnIndex(QuestionTable.COLUMN_IMAGE)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionTable.COLUMN_OPTION3)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_ANSWER_NR)));
                question.setCourses(c.getString(c.getColumnIndex(QuestionTable.COLUMN_COURSES)));
                question.setCategoryID(c.getInt(c.getColumnIndex(QuestionTable.COLUMN_CATEGORY_ID)));
                questionList.add(question);

            } while (c.moveToNext());
        }
        c.close();
        return questionList;
    }
}
