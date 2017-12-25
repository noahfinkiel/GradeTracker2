package com.example.noahfinkiel.gradetracker;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * Created by noahfinkiel on 2017-12-23.
 */

public class GradingCategory extends Observable {

    private String name;
    private int weight;
    private double currentPercentGrade;
    private List<Grade> grades;
    private double currentTotalPercent;


    //Effects: constructs a new GradingCategory with given course and percent weight
    public GradingCategory(String name, Course course, int weight) {
       this.name = name;
        addObserver(course);
        this.weight = weight;
        grades = new LinkedList<>();

    }


    //getters and setters for fields

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight)  {
        this.weight = weight;
        setChanged();
        notifyObservers();
    }

    public double getCurrentPercentGrade() {
        return currentPercentGrade;
    }

    public List<Grade> getGrades() {

        return grades;
    }


    //Modifies: this
    // Effects: adds grade to current percent total, notifies the course a grade has changed
    // this function is not necessary until I add more things to the program
    public void addGrade(Grade grade) {
        grades.add(grade);
        currentTotalPercent+= grade.getGrade();
        currentPercentGrade = currentTotalPercent / (this.grades.size());
        setChanged();
        notifyObservers(this);
    }

    //Effects: sets current percent grade to the given grade
    public void setCurrentPercentGrade(Grade grade) {
        grades.clear();
        grades.add(grade);
        currentPercentGrade = grade.getGrade();
        currentTotalPercent = grade.getGrade();
        setChanged();
        notifyObservers();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GradingCategory category = (GradingCategory) o;

        return name != null ? name.equals(category.name) : category.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
