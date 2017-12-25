package com.example.noahfinkiel.gradetracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.noahfinkiel.mygradetracker.R;

import java.util.HashMap;
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

            if (!(courseName == null)) {

                course.setName(courseName.getText().toString());

                // Checks to see if homework is null or empty, if not then proceeds to use homework data
               if (!((homework == null) || (homework.getText().toString().isEmpty()))) {

                   GradingCategory homeworkCat = new GradingCategory("Assignments", course, 0);

                   // checks to see if homeworkWeight is null, if not proceeds to use homework weight for course
                   if (!((homeworkWeight == null) || (homeworkWeight.getText().toString().isEmpty()))) {

                       // checks to see if this grading category already exists, if so, sets the grade and weight to given ones, if not
                       // adds this new grading category to course with given grade and weight
                       if ((categories.contains(homeworkCat))) {
                           GradingCategory category = course.getCategory("Assignments");
                           category.setWeight(Integer.parseInt(homeworkWeight.getText().toString()));

                           category.setCurrentPercentGrade(new Grade(Double.parseDouble(homework.getText().toString())));
                       }

                       // adds new grading category with given grade and weight
                       else {
                           homeworkCat.setWeight(Integer.parseInt(homeworkWeight.getText().toString()));
                           homeworkCat.setCurrentPercentGrade(new Grade(Double.parseDouble(homework.getText().toString())));
                           course.addCategory(homeworkCat);
                       }

                   }

                   // if the course contains the given category, then sets the current percent grade, if not then does not add grade
                   // because weight is null
                   else {

                       if ((categories.contains(homeworkCat))) {
                           GradingCategory category = course.getCategory("Assignments");
                           category.setCurrentPercentGrade(new Grade(Double.parseDouble(homework.getText().toString())));
                       }
                   }

               }

               else {
                   // if the homework weight is not null, then checks to see if the category already exists
                   if (!((homeworkWeight == null) || (homeworkWeight.getText().toString().isEmpty()))) {
                       GradingCategory homeworkCat = new GradingCategory("Assignments", course, 0);

                       // If the category already exists then sets weight to given weight, if it doesn't then does nothing
                       if (categories.contains(homeworkCat)) {
                           GradingCategory category = course.getCategory("Assignments");
                           category.setWeight(Integer.parseInt(homeworkWeight.getText().toString()));
                       }
                   }
               }



                // Checks to see if homework is null or empty, if not then proceeds to use homework data
                if (!((midterm1 == null) || (midterm1.getText().toString().isEmpty()))) {

                    GradingCategory midterm1Cat = new GradingCategory("Midterm 1", course, 0);

                    // checks to see if homeworkWeight is null, if not proceeds to use homework weight for course
                    if (!((midterm1Weight == null) || (midterm1Weight.getText().toString().isEmpty()))) {

                        // checks to see if this grading category already exists, if so, sets the grade and weight to given ones, if not
                        // adds this new grading category to course with given grade and weight
                        if ((categories.contains(midterm1Cat))) {
                            GradingCategory category = course.getCategory("Midterm 1");
                            category.setWeight(Integer.parseInt(midterm1Weight.getText().toString()));

                            category.setCurrentPercentGrade(new Grade(Double.parseDouble(midterm1.getText().toString())));
                        }

                        // adds new grading category with given grade and weight
                        else {
                            course.addCategory(midterm1Cat);
                            midterm1Cat.setWeight(Integer.parseInt(midterm1Weight.getText().toString()));
                            midterm1Cat.setCurrentPercentGrade(new Grade(Double.parseDouble(midterm1.getText().toString())));

                        }

                    }

                    // if the course contains the given category, then sets the current percent grade, if not then does not add grade
                    // because weight is null
                    else {

                        if ((categories.contains(midterm1Cat))) {
                            GradingCategory category = course.getCategory("Midterm 1");
                            category.setCurrentPercentGrade(new Grade(Double.parseDouble(midterm1.getText().toString())));
                        }
                    }

                }

                else {
                    // if the homework weight is not null, then checks to see if the category already exists
                    if (!((midterm1Weight == null) || (midterm1Weight.getText().toString().isEmpty()))) {
                        GradingCategory midterm1Cat = new GradingCategory("Midterm 1", course, 0);

                        // If the category already exists then sets weight to given weight, if it doesn't then does nothing
                        if (categories.contains(midterm1Cat)) {
                            GradingCategory category = course.getCategory("Midterm 1");
                            category.setWeight(Integer.parseInt(midterm1Weight.getText().toString()));
                        }
                    }
                }

                // Checks to see if homework is null or empty, if not then proceeds to use homework data
                if (!((midterm2 == null) || (midterm2.getText().toString().isEmpty()))) {

                    GradingCategory midterm2Cat = new GradingCategory("Midterm 2", course, 0);

                    // checks to see if homeworkWeight is null, if not proceeds to use homework weight for course
                    if (!((midterm2Weight == null) || (midterm2Weight.getText().toString().isEmpty()))) {

                        // checks to see if this grading category already exists, if so, sets the grade and weight to given ones, if not
                        // adds this new grading category to course with given grade and weight
                        if ((categories.contains(midterm2Cat))) {
                            GradingCategory category = course.getCategory("Midterm 2");
                            category.setWeight(Integer.parseInt(midterm2Weight.getText().toString()));

                            category.setCurrentPercentGrade(new Grade(Double.parseDouble(midterm2.getText().toString())));
                        }

                        // adds new grading category with given grade and weight
                        else {
                            course.addCategory(midterm2Cat);
                            midterm2Cat.setWeight(Integer.parseInt(midterm2Weight.getText().toString()));
                            midterm2Cat.setCurrentPercentGrade(new Grade(Double.parseDouble(midterm2.getText().toString())));

                        }

                    }

                    // if the course contains the given category, then sets the current percent grade, if not then does not add grade
                    // because weight is null
                    else {

                        if ((categories.contains(midterm2Cat))) {
                            GradingCategory category = course.getCategory("Midterm 2");
                            category.setCurrentPercentGrade(new Grade(Double.parseDouble(midterm2.getText().toString())));
                        }
                    }

                }

                else {
                    // if the homework weight is not null, then checks to see if the category already exists
                    if (!((midterm2Weight == null) || (midterm2Weight.getText().toString().isEmpty()))) {
                        GradingCategory homeworkCat = new GradingCategory("Midterm 2", course, 0);

                        // If the category already exists then sets weight to given weight, if it doesn't then does nothing
                        if (categories.contains(homeworkCat)) {
                            GradingCategory category = course.getCategory("Midterm 2");
                            category.setWeight(Integer.parseInt(midterm2Weight.getText().toString()));
                        }
                    }
                }

                // Checks to see if homework is null or empty, if not then proceeds to use homework data
                if (!((quizzes == null) || (quizzes.getText().toString().isEmpty()))) {

                    GradingCategory quizzesCat = new GradingCategory("Quizzes", course, 0);

                    // checks to see if homeworkWeight is null, if not proceeds to use homework weight for course
                    if (!((quizzes == null) || (quizzes.getText().toString().isEmpty()))) {

                        // checks to see if this grading category already exists, if so, sets the grade and weight to given ones, if not
                        // adds this new grading category to course with given grade and weight
                        if ((categories.contains(quizzesCat))) {
                            GradingCategory category = course.getCategory("Quizzes");
                            category.setWeight(Integer.parseInt(quizzesWeight.getText().toString()));

                            category.setCurrentPercentGrade(new Grade(Double.parseDouble(quizzes.getText().toString())));
                        }

                        // adds new grading category with given grade and weight
                        else {
                            course.addCategory(quizzesCat);
                            quizzesCat.setWeight(Integer.parseInt(homeworkWeight.getText().toString()));
                            quizzesCat.setCurrentPercentGrade(new Grade(Double.parseDouble(homework.getText().toString())));

                        }

                    }

                    // if the course contains the given category, then sets the current percent grade, if not then does not add grade
                    // because weight is null
                    else {

                        if ((categories.contains(quizzesCat))) {
                            GradingCategory category = course.getCategory("Quizzes");
                            category.setCurrentPercentGrade(new Grade(Double.parseDouble(quizzes.getText().toString())));
                        }
                    }

                }

                else {
                    // if the homework weight is not null, then checks to see if the category already exists
                    if (!((quizzesWeight == null) || (quizzesWeight.getText().toString().isEmpty()))) {
                        GradingCategory quizzesCat = new GradingCategory("Quizzes", course, 0);

                        // If the category already exists then sets weight to given weight, if it doesn't then does nothing
                        if (categories.contains(quizzesCat)) {
                            GradingCategory category = course.getCategory("Quizzes");
                            category.setWeight(Integer.parseInt(quizzesWeight.getText().toString()));
                        }
                    }
                }

                // Checks to see if homework is null or empty, if not then proceeds to use homework data
                if (!((clickers == null) || (clickers.getText().toString().isEmpty()))) {

                    GradingCategory clickersCat = new GradingCategory("Clickers", course, 0);

                    // checks to see if homeworkWeight is null, if not proceeds to use homework weight for course
                    if (!((clickers == null) || (clickers.getText().toString().isEmpty()))) {

                        // checks to see if this grading category already exists, if so, sets the grade and weight to given ones, if not
                        // adds this new grading category to course with given grade and weight
                        if ((categories.contains(clickersCat))) {
                            GradingCategory category = course.getCategory("Clickers");
                            category.setWeight(Integer.parseInt(clickersWeight.getText().toString()));

                            category.setCurrentPercentGrade(new Grade(Double.parseDouble(clickers.getText().toString())));
                        }

                        // adds new grading category with given grade and weight
                        else {
                            course.addCategory(clickersCat);
                            clickersCat.setWeight(Integer.parseInt(clickersWeight.getText().toString()));
                            clickersCat.setCurrentPercentGrade(new Grade(Double.parseDouble(clickers.getText().toString())));

                        }

                    }

                    // if the course contains the given category, then sets the current percent grade, if not then does not add grade
                    // because weight is null
                    else {

                        if ((categories.contains(clickersCat))) {
                            GradingCategory category = course.getCategory("Clickers");
                            category.setCurrentPercentGrade(new Grade(Double.parseDouble(homework.getText().toString())));
                        }
                    }

                }

                else {
                    // if the homework weight is not null, then checks to see if the category already exists
                    if (!((clickersWeight == null) || (clickersWeight.getText().toString().isEmpty()))) {
                        GradingCategory clickerskCat = new GradingCategory("Clickers", course, 0);

                        // If the category already exists then sets weight to given weight, if it doesn't then does nothing
                        if (categories.contains(clickerskCat)) {
                            GradingCategory category = course.getCategory("Clickers");
                            category.setWeight(Integer.parseInt(clickersWeight.getText().toString()));
                        }
                    }
                }

                // Checks to see if homework is null or empty, if not then proceeds to use homework data
                if (!((labs == null) || (labs.getText().toString().isEmpty()))) {

                    GradingCategory labsCat = new GradingCategory("Labs", course, 0);

                    // checks to see if homeworkWeight is null, if not proceeds to use homework weight for course
                    if (!((labsWeight == null) || (labsWeight.getText().toString().isEmpty()))) {

                        // checks to see if this grading category already exists, if so, sets the grade and weight to given ones, if not
                        // adds this new grading category to course with given grade and weight
                        if ((categories.contains(labsCat))) {
                            GradingCategory category = course.getCategory("Labs");
                            category.setWeight(Integer.parseInt(labsWeight.getText().toString()));

                            category.setCurrentPercentGrade(new Grade(Double.parseDouble(labs.getText().toString())));
                        }

                        // adds new grading category with given grade and weight
                        else {
                            course.addCategory(labsCat);
                            labsCat.setWeight(Integer.parseInt(labsWeight.getText().toString()));
                            labsCat.setCurrentPercentGrade(new Grade(Double.parseDouble(labsWeight.getText().toString())));

                        }

                    }

                    // if the course contains the given category, then sets the current percent grade, if not then does not add grade
                    // because weight is null
                    else {

                        if ((categories.contains(labsCat))) {
                            GradingCategory category = course.getCategory("Labs");
                            category.setCurrentPercentGrade(new Grade(Double.parseDouble(labs.getText().toString())));
                        }
                    }

                }

                else {
                    // if the homework weight is not null, then checks to see if the category already exists
                    if (!((labsWeight == null) || (labsWeight.getText().toString().isEmpty()))) {
                        GradingCategory labsCat = new GradingCategory("Labs", course, 0);

                        // If the category already exists then sets weight to given weight, if it doesn't then does nothing
                        if (categories.contains(labsCat)) {
                            GradingCategory category = course.getCategory("Labs");
                            category.setWeight(Integer.parseInt(labsWeight.getText().toString()));
                        }
                    }
                }

                // Checks to see if homework is null or empty, if not then proceeds to use homework data
                if (!((tutorials == null) || (tutorials.getText().toString().isEmpty()))) {

                    GradingCategory tutorialsCat = new GradingCategory("Tutorials", course, 0);

                    // checks to see if homeworkWeight is null, if not proceeds to use homework weight for course
                    if (!((tutorialsWeight == null) || (tutorialsWeight.getText().toString().isEmpty()))) {

                        // checks to see if this grading category already exists, if so, sets the grade and weight to given ones, if not
                        // adds this new grading category to course with given grade and weight
                        if ((categories.contains(tutorialsCat))) {
                            GradingCategory category = course.getCategory("Tutorials");
                            category.setWeight(Integer.parseInt(tutorialsWeight.getText().toString()));

                            category.setCurrentPercentGrade(new Grade(Double.parseDouble(tutorials.getText().toString())));
                        }

                        // adds new grading category with given grade and weight
                        else {
                            course.addCategory(tutorialsCat);
                            tutorialsCat.setWeight(Integer.parseInt(tutorialsWeight.getText().toString()));
                            tutorialsCat.setCurrentPercentGrade(new Grade(Double.parseDouble(tutorials.getText().toString())));

                        }

                    }

                    // if the course contains the given category, then sets the current percent grade, if not then does not add grade
                    // because weight is null
                    else {

                        if ((categories.contains(tutorialsCat))) {
                            GradingCategory category = course.getCategory("Tutorials");
                            category.setCurrentPercentGrade(new Grade(Double.parseDouble(tutorials.getText().toString())));
                        }
                    }

                }

                else {
                    // if the homework weight is not null, then checks to see if the category already exists
                    if (!((tutorialsWeight == null) || (tutorialsWeight.getText().toString().isEmpty()))) {
                        GradingCategory tutorialsCat = new GradingCategory("Tutorials", course, 0);

                        // If the category already exists then sets weight to given weight, if it doesn't then does nothing
                        if (categories.contains(tutorialsCat)) {
                            GradingCategory category = course.getCategory("Tutorials");
                            category.setWeight(Integer.parseInt(tutorialsWeight.getText().toString()));
                        }
                    }
                }

                // Checks to see if homework is null or empty, if not then proceeds to use homework data
                if (!((finals == null) || (finals.getText().toString().isEmpty()))) {

                    GradingCategory finalCat = new GradingCategory("Final", course, 0);

                    // checks to see if homeworkWeight is null, if not proceeds to use homework weight for course
                    if (!((finalWeight == null) || (finalWeight.getText().toString().isEmpty()))) {

                        // checks to see if this grading category already exists, if so, sets the grade and weight to given ones, if not
                        // adds this new grading category to course with given grade and weight
                        if ((categories.contains(finalCat))) {
                            GradingCategory category = course.getCategory("Final");
                            category.setWeight(Integer.parseInt(finalWeight.getText().toString()));

                            category.setCurrentPercentGrade(new Grade(Double.parseDouble(finals.getText().toString())));
                        }

                        // adds new grading category with given grade and weight
                        else {
                            course.addCategory(finalCat);
                            finalCat.setWeight(Integer.parseInt(finalWeight.getText().toString()));
                            finalCat.setCurrentPercentGrade(new Grade(Double.parseDouble(finals.getText().toString())));

                        }

                    }

                    // if the course contains the given category, then sets the current percent grade, if not then does not add grade
                    // because weight is null
                    else {

                        if ((categories.contains(finalCat))) {
                            GradingCategory category = course.getCategory("Final");
                            category.setCurrentPercentGrade(new Grade(Double.parseDouble(finals.getText().toString())));
                        }
                    }

                }

                else {
                    // if the homework weight is not null, then checks to see if the category already exists
                    if (!((finalWeight == null) || (finalWeight.getText().toString().isEmpty()))) {
                        GradingCategory finalCat = new GradingCategory("Final", course, 0);

                        // If the category already exists then sets weight to given weight, if it doesn't then does nothing
                        if (categories.contains(finalCat)) {
                            GradingCategory category = course.getCategory("Final");
                            category.setWeight(Integer.parseInt(finalWeight.getText().toString()));
                        }
                    }
                }

                // Checks to see if homework is null or empty, if not then proceeds to use homework data
                if (!((other == null) || (otherWeight.getText().toString().isEmpty()))) {

                    GradingCategory otherCat = new GradingCategory("Other", course, 0);

                    // checks to see if homeworkWeight is null, if not proceeds to use homework weight for course
                    if (!((otherWeight == null) || (otherWeight.getText().toString().isEmpty()))) {

                        // checks to see if this grading category already exists, if so, sets the grade and weight to given ones, if not
                        // adds this new grading category to course with given grade and weight
                        if ((categories.contains(otherCat))) {
                            GradingCategory category = course.getCategory("Other");
                            category.setWeight(Integer.parseInt(otherWeight.getText().toString()));

                            category.setCurrentPercentGrade(new Grade(Double.parseDouble(other.getText().toString())));
                        }

                        // adds new grading category with given grade and weight
                        else {
                            course.addCategory(otherCat);
                            otherCat.setWeight(Integer.parseInt(otherWeight.getText().toString()));
                            otherCat.setCurrentPercentGrade(new Grade(Double.parseDouble(other.getText().toString())));
                        }

                    }

                    // if the course contains the given category, then sets the current percent grade, if not then does not add grade
                    // because weight is null
                    else {

                        if ((categories.contains(otherCat))) {
                            GradingCategory category = course.getCategory("Other");
                            category.setCurrentPercentGrade(new Grade(Double.parseDouble(other.getText().toString())));
                        }
                    }

                }

                else {
                    // if the homework weight is not null, then checks to see if the category already exists
                    if (!((otherWeight == null) || (otherWeight.getText().toString().isEmpty()))) {
                        GradingCategory otherCat = new GradingCategory("Other", course, 0);

                        // If the category already exists then sets weight to given weight, if it doesn't then does nothing
                        if (categories.contains(otherCat)) {
                            GradingCategory category = course.getCategory("Other");
                            category.setWeight(Integer.parseInt(otherWeight.getText().toString()));
                        }
                    }
                }
            }

            setResult(1, new Intent());
            finish();

        }

    }

}
