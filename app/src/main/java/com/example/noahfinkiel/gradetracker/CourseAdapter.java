package com.example.noahfinkiel.gradetracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.noahfinkiel.mygradetracker.R;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by noahfinkiel on 2017-12-23.
 */

public class CourseAdapter extends ArrayAdapter<Course> {
    public CourseAdapter(Context context, List<Course> courses) {
        super(context, 0, courses);
    }

    //Effects: creates the view for the Course in position
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Course course = getItem(position);

        View view = convertView;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.adapter_layout, parent, false);
        }



        TextView name = (TextView) view.findViewById(R.id.tvName);
        TextView grade = (TextView) view.findViewById(R.id.tvGrade);

        if (!(course == null)) {

            if (!(name == null)) {
                name.setText(course.getName());
                if (!(grade==null))
                    grade.setText("                     " + Double.toString(course.getTotalPercentGrade()) + "%");
            }
        }
        return view;


    }


}

