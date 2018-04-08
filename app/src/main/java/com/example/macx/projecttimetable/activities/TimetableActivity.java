package com.example.macx.projecttimetable.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.macx.projecttimetable.Lesson;
import com.example.macx.projecttimetable.LessonAdapter;
import com.example.macx.projecttimetable.QueryUtils;
import com.example.macx.projecttimetable.R;

import java.util.ArrayList;


/**
 * Created by MacX on 2017-11-21.
 */

public class TimetableActivity extends AppCompatActivity {

    /** TextView that is displayed when the list is empty */
    private TextView emptyStateTextView;
    /** ImageView that is displayed when the list is empty */
    private ImageView emptyImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_list);

        int day = getIntent().getIntExtra("DAY_OF_WEEK", 0);
        String jsonResponse = getIntent().getStringExtra("JSON_RESPONSE");

        ArrayList<Lesson> lessons = QueryUtils.extractLessons(day, jsonResponse);

        // Find a reference to the {@link ListView} in the layout
        ListView dayListView = (ListView) findViewById(R.id.list);

        emptyImageView = (ImageView) findViewById(R.id.empty_state_image);
        dayListView.setEmptyView(emptyImageView);

        emptyStateTextView = (TextView) findViewById(R.id.empty_text_view);
        dayListView.setEmptyView(emptyStateTextView);


        if (lessons.size() != 0) {

            final LessonAdapter itemsAdapter = new LessonAdapter(this, lessons);

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            dayListView.setAdapter(itemsAdapter);

        } else {

            // Set empty state text to display
            emptyStateTextView.setText(R.string.no_lessons_for_today);
            emptyImageView.setImageResource(R.drawable.wink_smile);
        }


    }
}
