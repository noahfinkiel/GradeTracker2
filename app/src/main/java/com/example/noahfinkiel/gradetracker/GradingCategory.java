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


    public GradingCategory(Course course, int weight) {
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

    public double getCurrentPercentGrade() {
        return currentPercentGrade;
    }

    public List<Grade> getGrades() {

        return grades;
    }


    // Effects: adds grade to current total
    public void addGrade(Grade grade) {
        grades.add(grade);
        currentTotalPercent+= grade.getGrade();
        currentPercentGrade = currentTotalPercent / (this.grades.size());
        setChanged();
        notifyObservers(this);
    }



}
