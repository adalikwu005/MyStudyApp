package com.example.mystudyapp;

import androidx.annotation.NonNull;

public class Category {
    //FIRST SEMESTER YEAR I COURSES
    public static final int INTRO_TO_PROBLEM_SOLVING = 1;
    public static final int INTRO_TO_COMPUTER_SC = 2;
    public static final int GENERAL_MATHEMATICS_I = 3;
    public static final int GENERAL_PHYSICS = 4;
    public static final int PRACTICAL_PHYSICS = 5;
    public static final int GENERAL_CHEMISTRY = 6;
    public static final int GENERAL_BIOLOGY = 7;
    public static final int USE_OF_ENGLISH_AND_COMMUNICATION_SKILL = 8;
    public static final int PHILOSOPHY_AND_LOGIC = 9;

    //SECOND SEMESTER YEAR I COURSES
    public static final int INTRO_TO_CSC_USING_BASIC = 10;
    public static final int PASCAL_PROGRAMMING = 11;
    public static final int INTRO_TO_INTERNET = 12;
    public static final int GENERAL_MATHEMATICS_II = 13;
    public static final int GENERAL_MATHEMATICS_III = 14;
    public static final int GENERAL_PHYSICS_II = 15;
    public static final int INTRO_TO_STATISTICS = 16;
    public static final int USE_OF_ENGLISH_AND_COMMUNICATION_SKILL_II = 17;
    public static final int NIGERIA_PEOPLE_AND_CULTURE = 18;
    public static final int HISTORY_AND_PHILOSOPHY_OF_SCIENCE = 19;

    //FIRST SEMESTER YEAR 2 COURSES
    public static final int COMPUTER_PROGRAMMING = 20;
    public static final int COMPUTER_ORGANISATION_AND_ASSEMBLY_LANG_PROGRAMMING = 21;
    public static final int OPERATING_SYSTEM_I = 22;
    public static final int LINEAR_ALGEBRA = 23;
    public static final int MATHEMATICAL_METHODS = 24;
    public static final int PEACE_AND_CONFLICT_STUDIES = 25;
    public static final int ENTREPRENEURSHIP_STUDY_I = 26;

    //SECOND SEMESTER YEAR 2 COURSES
    public static final int COMPUTER_PROGRAMMING_II = 27;
    public static final int FUNDAMENTALS_OF_DATA_STRUCTURE = 28;
    public static final int OPERATING_SYSTEM_II = 29;
    public static final int SWITCHING_ALGEBRA_AND_DISCRETE_STRUCTURES = 30;
    public static final int INTRO_DIGITAL_DESIGN = 31;
    public static final int LINEAR_ALGEBRA_II = 32;
    public static final int ENTREPRENEURSHIP_II = 33;

    //FIRST SEMESTER YEAR 3 COURSES
    //SECOND SEMESTER YEAR 3 COURSES

    //FIRST SEMESTER YEAR 4 COURSES
    //SECOND SEMESTER YEAR 4 COURSES





    private int id;
    private String name;

    public Category () {

    }

    public Category(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
      return getName();
    }
}
