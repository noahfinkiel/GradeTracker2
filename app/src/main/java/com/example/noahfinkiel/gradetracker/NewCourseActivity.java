package com.example.noahfinkiel.gradetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.noahfinkiel.mygradetracker.R;

import java.util.ResourceBundle;

/**
 * Created by noahfinkiel on 2017-12-23.
 */

public class NewCourseActivity extends Activity implements View.OnClickListener {

    private EditText courseName;
    private EditText homework;
    private EditText homeworkWeight;
    private EditText midterm1;
    private EditText midterm1Weight;
    private EditText midterm2;
    private EditText midterm2Weight;
    private EditText quizzes;
    private EditText quizzesWeight;
    private EditText clickers;
    private EditText clickersWeight;
    private EditText labs;
    private EditText labsWeight;
    private EditText tutorials;
    private EditText tutorialsWeight;
    private EditText finals;
    private EditText finalWeight;
    private EditText other;
    private EditText otherWeight;



    // Sets up content view and button
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.new_course_activity);

        View add = (Button) findViewById(R.id.addButton);

        add.setOnClickListener(this);

    }


    //Effects: gets EditText values, creates GradingCategories, creates a course then adds grading categories to course
    // and adds course to GradeCalcManager
    @Override
    public void onClick(View view) {

        courseName = (EditText) findViewById(R.id.courseName);
        homework = (EditText) findViewById(R.id.assignment);
        homeworkWeight = (EditText) findViewById(R.id.assignmentWeight);
        midterm1 = (EditText) findViewById(R.id.Midterm1);
        midterm1Weight = (EditText) findViewById(R.id.Midter1Weight);
        midterm2 = (EditText) findViewById(R.id.Midterm2);
        midterm2Weight  = (EditText) findViewById(R.id.Midterm2Weight);
        quizzes = (EditText) findViewById(R.id.Quizzes);
        quizzesWeight = (EditText) findViewById(R.id.QuizzesWeight);
        clickers = (EditText) findViewById(R.id.Clickers);
        clickersWeight = (EditText) findViewById(R.id.ClickersWeight);
        labs = (EditText) findViewById(R.id.Labs);
        labsWeight = (EditText) findViewById(R.id.LabsWeight);
        tutorials = (EditText) findViewById(R.id.Tutorials);
        tutorialsWeight = (EditText) findViewById(R.id.TutorialsWeight);
        finals = (EditText) findViewById(R.id.Final);
        finalWeight = (EditText) findViewById(R.id.FinalWeight);other = (EditText) findViewById(R.id.Other);
        otherWeight = (EditText) findViewById(R.id.OtherWeight);


        if (!(courseName == null)) {
            Course course = new Course(courseName.getText().toString());


            if (!((homework == null) || (homeworkWeight==null) || (homework.getText().toString().isEmpty()) || (homeworkWeight.getText().toString().isEmpty()))) {
                GradingCategory homeworkCat = new GradingCategory("Assignments", course, Integer.parseInt(homeworkWeight.getText().toString()));
                homeworkCat.addGrade(new Grade(Double.parseDouble(homework.getText().toString())));
                course.addCategory(homeworkCat);
            }

            if (!((midterm1==null) || (midterm1Weight==null) || (midterm1.getText().toString().isEmpty()) || (midterm1Weight.getText().toString().isEmpty()))) {
                GradingCategory midterm1Cat = new GradingCategory("Midterm 1", course, Integer.parseInt(midterm1Weight.getText().toString()));
                midterm1Cat.addGrade(new Grade(Double.parseDouble(midterm1.getText().toString())));
                course.addCategory(midterm1Cat);
            }

            if (!((midterm2==null) || (midterm2Weight==null) || (midterm2.getText().toString().isEmpty()) || (midterm2Weight.getText().toString().isEmpty()))) {
                GradingCategory midterm2Cat = new GradingCategory("Midterm 2", course, Integer.parseInt(midterm2Weight.getText().toString()));
                midterm2Cat.addGrade(new Grade(Double.parseDouble(midterm2.getText().toString())));
                course.addCategory(midterm2Cat);

            }

            if (!((quizzes == null) || (quizzesWeight== null) || (quizzes.getText().toString().isEmpty()) || (quizzesWeight.getText().toString().isEmpty()))) {
                GradingCategory quizzesCat = new GradingCategory("Quizzes", course, Integer.parseInt(quizzesWeight.getText().toString()));
                quizzesCat.addGrade(new Grade(Double.parseDouble(quizzes.getText().toString())));
                course.addCategory(quizzesCat);
            }

            if (!((clickers==null) || (clickersWeight==null) || (clickers.getText().toString().isEmpty()) || (clickersWeight.getText().toString().isEmpty()))) {
                GradingCategory clickersCat = new GradingCategory("Clickers", course, Integer.parseInt(clickersWeight.getText().toString()));
                clickersCat.addGrade(new Grade(Double.parseDouble(clickers.getText().toString())));
                course.addCategory(clickersCat);
            }

            if (!((labs==null) || (labsWeight==null) || (labs.getText().toString().isEmpty()) || (labsWeight.getText().toString().isEmpty()))) {
                GradingCategory labsCat = new GradingCategory("Labs", course, Integer.parseInt(labsWeight.getText().toString()));
                labsCat.addGrade(new Grade(Double.parseDouble(labs.getText().toString())));
                course.addCategory(labsCat);
            }

            if (!((tutorials==null)|| (tutorialsWeight==null) || (tutorials.getText().toString().isEmpty()) ||(tutorialsWeight.getText().toString().isEmpty()))) {
                GradingCategory tutorialsCat = new GradingCategory("Tutorials", course, Integer.parseInt(tutorialsWeight.getText().toString()));
                tutorialsCat.addGrade(new Grade(Double.parseDouble(tutorials.getText().toString())));
                course.addCategory(tutorialsCat);
            }

            if (!((finals==null) || (finalWeight==null) || (finals.getText().toString().isEmpty()) || (finalWeight.getText().toString().isEmpty()))) {
                GradingCategory finalsCat = new GradingCategory("Final", course, Integer.parseInt(finalWeight.getText().toString()));
                finalsCat.addGrade(new Grade(Double.parseDouble(finals.getText().toString())));
                course.addCategory(finalsCat);
            }

            if (!((other==null)|| (otherWeight==null) || (other.getText().toString().isEmpty())|| (otherWeight.getText().toString().isEmpty()))) {
                GradingCategory otherCat = new GradingCategory("Other", course, Integer.parseInt(otherWeight.getText().toString()));
                otherCat.addGrade(new Grade(Double.parseDouble(other.getText().toString())));
                course.addCategory(otherCat);
            }


            GradeCalcManager.getInstance().addCourse(course);

            String message = "";
            setResult(1, (new Intent()).putExtra("MESSAGE", message));
            finish();
        }

        finish();


    }



}