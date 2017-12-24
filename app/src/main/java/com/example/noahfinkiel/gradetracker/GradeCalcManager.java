package com.example.noahfinkiel.gradetracker;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by noahfinkiel on 2017-12-23.
 */

public class GradeCalcManager {
    private static final GradeCalcManager instance = new GradeCalcManager();
    private List<Course> courses;

    // Effects: Constructs a GradeCalcManager
    private GradeCalcManager() {
        this.courses = new LinkedList<>();
    }

    //Effects: returns the instance of GradeCalcManager
    public static GradeCalcManager getInstance() {
        return instance;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public List<Course> getCourses() {
        return courses;
    }

}
