package com.example.noahfinkiel.gradetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.noahfinkiel.mygradetracker.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener{

    private ListView listView;
    private CourseAdapter courseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);

        // get the button reference
        // button is subclass of view

        View v = findViewById(R.id.button1);

        v.setOnClickListener(this);

        courseAdapter = new CourseAdapter(this, GradeCalcManager.getInstance().getCourses());

        listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(courseAdapter);

        listView.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(MainActivity.this, UpdateActivity.class);

        Course course = GradeCalcManager.getInstance().getCourses().get(position);
        intent.putExtra("position", position);

        this.startActivityForResult(intent, 1);


    }




    //Effects: handles button click, creates new intent and sends to NewCourseActivity to wait for result
    @Override
    public void onClick(View arg) {
        if (arg.getId() == R.id.button1) {
            Intent intent = new Intent(this, NewCourseActivity.class);
            this.startActivityForResult(intent, 1);
        }
    }

    //Effects: if request code is 1, notifies adapter that the data set has changed
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data ) {
        super.onActivityResult(requestCode,resultCode,data);

        if ((requestCode == 1)) {
            courseAdapter.notifyDataSetChanged();
        }
    }





}
