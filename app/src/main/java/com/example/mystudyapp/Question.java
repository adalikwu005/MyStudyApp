package com.example.mystudyapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    public static final String COURSE_CSC_1101 = "CSC 1101";
    public static final String COURSE_CSC_1103 = "CSC 1103";
    public static final String COURSE_CSC_1104 = "CSC 1104";

    private int id;
    private String question;
    private String imageLink;
    private String option1;
    private String option2;
    private String option3;
    private int answerNr;
    private String courses;
    private int categoryID;

    public Question() {
    }

    public Question(String question, String imageLink, String option1, String option2, String option3, int answerNr, String courses, int categoryID) {
        this.question = question;
        this.imageLink = imageLink;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.answerNr = answerNr;
        this.courses = courses;
        this.categoryID = categoryID;
    }

    protected Question(Parcel in) {
        id = in.readInt();
        question = in.readString();
        imageLink = in.readString();
        option1 = in.readString();
        option2 = in.readString();
        option3 = in.readString();
        answerNr = in.readInt();
        courses = in.readString();
        categoryID = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(question);
        dest.writeString(imageLink);
        dest.writeString(option1);
        dest.writeString(option2);
        dest.writeString(option3);
        dest.writeInt(answerNr);
        dest.writeString(courses);
        dest.writeInt(categoryID);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public int getAnswerNr() {
        return answerNr;
    }

    public void setAnswerNr(int answerNr) {
        this.answerNr = answerNr;
    }

    public String getCourses() {
        return courses;
    }

    public void setCourses(String courses) {
        this.courses = courses;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }


    public static String[] getAllCourses() {
        return new String[]{
                COURSE_CSC_1101,
                COURSE_CSC_1103,
                COURSE_CSC_1104
        };
    }
}
