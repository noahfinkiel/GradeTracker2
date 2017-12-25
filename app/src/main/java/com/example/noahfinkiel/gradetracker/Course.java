package com.example.noahfinkiel.gradetracker;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Observable;
import java.util.Observer;
import java.util.Set;

/**
 * Created by noahfinkiel on 2017-12-23.
 */
public class Course implements Observer {

    private String name;
    private Set<GradingCategory> categories;
    private double totalPercentGrade;


    // Effects: Constructs a Course with given name and empty categories
    public Course(String name) {
        this.name = name;
        this.categories = new HashSet<>();
        totalPercentGrade = 100;

    }

    // Modifies: this
    //Effects: adds the given grading category
    public void addCategory(GradingCategory category) {
        categories.add(category);
        calculatePercentGrade();
    }

    // Effects: updates the course grade
    public void update(Observable observable, Object o) {

        calculatePercentGrade();
    }


    // Modifies: this
    // Effects: calculates updated course grade
    public void calculatePercentGrade() {
        double totalMarks = 0;
        double marksOutOf = 0;

        for (GradingCategory category: categories) {
            if (!(category.getGrades().isEmpty())) {
                totalMarks += ((category.getCurrentPercentGrade()/(double)100) * (double)category.getWeight());
                marksOutOf += (double)category.getWeight();
            }
        }

        if (!(marksOutOf==0)) {
            this.totalPercentGrade = (100 * (totalMarks/marksOutOf));
        }
        else {
            totalPercentGrade = 100;
        }

    }

    // getters and setters

    public double getTotalPercentGrade() {

        return totalPercentGrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<GradingCategory> getCategories() {
        return categories;
    }

    // Requires: categories contains category with given name
    public GradingCategory getCategory(String s) {
        for (GradingCategory g: categories) {
            if (s.equals(g.getName())) {
                return  g;
            }
        }

        throw new NoSuchElementException();
    }

    // Courses are equal if their names are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return name != null ? name.equals(course.name) : course.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}

