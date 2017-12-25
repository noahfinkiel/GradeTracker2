package com.example.noahfinkiel.gradetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.noahfinkiel.mygradetracker.R;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by noahfinkiel on 2017-12-24.
 */

public class UpdateActivity extends Activity implements View.OnClickListener {

    private Course course;
    int position;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.update_activity);

        position = getIntent().getIntExtra("position", 0);

        course = GradeCalcManager.getInstance().getCourses().get(position);

        if (!(course == null)) {


            Set<GradingCategory> categories = course.getCategories();

            Map<String, Integer> resources = new HashMap<>();

            // assigns all id to the name of the grading category using a hashmap


            resources.put("Assignments", R.id.assignmentUpdate);
            resources.put("AssignmentsWeight", R.id.assignmentWeightUpdate);
            resources.put("Midterm 1", R.id.Midterm1Update);
            resources.put("Midterm 1Weight", R.id.Midter1WeightUpdate);
            resources.put("Midterm 2", R.id.Midterm2Update);
            resources.put("Midterm 2Weight", R.id.Midterm2WeightUpdate);
            resources.put("Quizzes", R.id.QuizzesUpdate);
            resources.put("QuizzesWeight", R.id.QuizzesWeightUpdate);
            resources.put("Clickers", R.id.ClickersUpdate);
            resources.put("ClickersWeight", R.id.ClickersWeightUpdate);
            resources.put("Labs", R.id.LabsUpdate);
            resources.put("LabsWeight", R.id.LabsWeightUpdate);
            resources.put("Tutorials", R.id.TutorialsUpdate);
            resources.put("TutorialsWeight", R.id.TutorialsWeightUpdate);
            resources.put("Final", R.id.FinalUpdate);
            resources.put("FinalWeight", R.id.FinalWeightUpdate);
            resources.put("Other", R.id.OtherUpdate);
            resources.put("OtherWeight", R.id.OtherWeightUpdate);

            // Sets the courseName view to current courseName
            courseName = (EditText) findViewById(R.id.courseNameUpdate);
            courseName.setText(course.getName());

            for (GradingCategory category : categories) {

                if (!(category == null)) {
                    List<Grade> grades = category.getGrades();


                    if (!((grades == null) || (grades.isEmpty()))) {

                        double grade = category.getCurrentPercentGrade();


                        // gets the EditText corresponding to the GradingCategory from resources and assigns it the current percent grade and weight
                        EditText editText = (EditText) findViewById(resources.get(category.getName()));
                        EditText editTextWeight = (EditText) findViewById(resources.get(category.getName() + "Weight"));

                        editText.setHint(category.getName() + " grade: " + grade + "%");
                        editTextWeight.setHint(category.getName() + " weight: " + category.getWeight() + "%");
                        editText.setTextSize(18);
                        editTextWeight.setTextSize(18);
                    }
                }
            }

            View update = (Button) findViewById(R.id.updateButton);
            update.setOnClickListener(this);

            View delete = (Button) findViewById(R.id.deleteButton);

            delete.setOnClickListener(this);


        }

    }

    //Effects: handles button clicks, if button is delete button then deletes course, if button is update button then updates info
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.deleteButton) {
            GradeCalcManager.getInstance().removeCourseAtPosition(position);
            String message = "";
            setResult(1, new Intent());
            finish();
        }

        else {
            courseName = (EditText) findViewById(R.id.courseNameUpdate);
            homework = (EditText) findViewById(R.id.assignmentUpdate);
            homeworkWeight = (EditText) findViewById(R.id.assignmentWeightUpdate);
            midterm1 = (EditText) findViewById(R.id.Midterm1Update);
            midterm1Weight = (EditText) findViewById(R.id.Midter1WeightUpdate);
            midterm2 = (EditText) findViewById(R.id.Midterm2Update);
            midterm2Weight = (EditText) findViewById(R.id.Midterm2WeightUpdate);
            quizzes = (EditText) findViewById(R.id.QuizzesUpdate);
            quizzesWeight = (EditText) findViewById(R.id.QuizzesWeightUpdate);
            clickers = (EditText) findViewById(R.id.ClickersUpdate);
            clickersWeight = (EditText) findViewById(R.id.ClickersWeightUpdate);
            labs = (EditText) findViewById(R.id.LabsUpdate);
            labsWeight = (EditText) findViewById(R.id.LabsWeightUpdate);
            tutorials = (EditText) findViewById(R.id.TutorialsUpdate);
            tutorialsWeight = (EditText) findViewById(R.id.TutorialsWeightUpdate);
            finals = (EditText) findViewById(R.id.FinalUpdate);
            finalWeight = (EditText) findViewById(R.id.FinalWeightUpdate);
            other = (EditText) findViewById(R.id.OtherUpdate);
            otherWeight = (EditText) findViewById(R.id.OtherWeightUpdate);


            Set<GradingCategory> categories = course.getCategories();

            Map<EditText, String> editCategories = new LinkedHashMap<>();
            List<EditText> editCategoriesWeight = new LinkedList<>();


            // puts and category editText values into a map with corresponding category names, also puts the
            // corresponding categories Weights at the same index in editCategoriesWeight list

            editCategories.put(homework, "Assignments");
            editCategoriesWeight.add(homeworkWeight);
            editCategories.put(midterm1, "Midterm 1");
            editCategoriesWeight.add(midterm1Weight);
            editCategories.put(midterm2, "Midterm 2");
            editCategoriesWeight.add(midterm2Weight);
            editCategories.put(quizzes, "Quizzes");
            editCategoriesWeight.add(quizzesWeight);
            editCategories.put(clickers, "Clickers");
            editCategoriesWeight.add(clickersWeight);
            editCategories.put(labs, "Labs");
            editCategoriesWeight.add(labsWeight);
            editCategories.put(tutorials, "Tutorials");
            editCategoriesWeight.add(tutorialsWeight);
            editCategories.put(finals, "Final");
            editCategoriesWeight.add(finalWeight);
            editCategories.put(other, "Other");
            editCategoriesWeight.add(otherWeight);

            Iterator weightIterator = editCategoriesWeight.iterator();

            // iterates through keys of editCategories and uses values to find courses that already exist, then updates all editText info
            for (EditText category : editCategories.keySet()) {

                EditText weight = (EditText) weightIterator.next();

                // Checks to see if category is null or empty, if not then proceeds to use category data
                if (!((category == null) || (category.getText().toString().isEmpty()))) {
                    GradingCategory cat = new GradingCategory(editCategories.get(category), course, 0);

                    // checks to see if categoryWeight is null, if not proceeds to use category weight for course
                    if (!((weight == null) || weight.getText().toString().isEmpty())) {

                        // checks to see if this grading category already exists, if so, sets the grade and weight to given ones, if not
                        // adds this new grading category to course with given grade and weight
                        if (categories.contains(cat)) {
                            GradingCategory thisCategory = course.getCategory(editCategories.get(category));
                            thisCategory.setWeight(Integer.parseInt(weight.getText().toString()));
                            thisCategory.setCurrentPercentGrade(new Grade(Double.parseDouble(category.getText().toString())));

                        }

                        // adds new grading category with given grade and weight
                        else {
                            course.addCategory(cat);
                            cat.setWeight(Integer.parseInt(category.getText().toString()));
                            cat.setCurrentPercentGrade(new Grade(Double.parseDouble(category.getText().toString())));
                        }
                    }

                    // if the course contains the given category, then sets the current percent grade, if not then does not add grade
                    // because weight is null
                    else {

                        if ((categories.contains(cat))) {
                            GradingCategory thisCategory = course.getCategory(editCategories.get(category));
                            thisCategory.setCurrentPercentGrade(new Grade(Double.parseDouble(category.getText().toString())));
                        }
                    }
                }

                else {
                    // if the category weight is not null, then checks to see if the category already exists
                    if (!((weight == null) || (weight.getText().toString().isEmpty()))) {
                        GradingCategory cat = new GradingCategory(editCategories.get(category), course, 0);

                        // If the category already exists then sets weight to given weight, if it doesn't then does nothing
                        if (categories.contains(cat)) {
                            GradingCategory thisCat = course.getCategory(editCategories.get(category));
                            thisCat.setWeight(Integer.parseInt(weight.getText().toString()));
                        }
                    }
                }
            }

            // sets result code to one so course adapter updates
            setResult(1, new Intent());
            finish();


        }

    }

}