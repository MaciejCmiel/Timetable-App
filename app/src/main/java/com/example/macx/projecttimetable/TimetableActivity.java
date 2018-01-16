package com.example.macx.projecttimetable;

import android.app.LoaderManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by MacX on 2017-11-21.
 */

public class TimetableActivity extends AppCompatActivity {

    /** TextView that is displayed when the list is empty */
    private TextView mEmptyStateTextView;
    /** ImageView that is displayed when the list is empty */
    private ImageView mEmptyImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.day_list);

        int day = getIntent().getIntExtra("DAY_OF_WEEK", 0);
        String jsonResponse = getIntent().getStringExtra("JSON_RESPONSE");

        ArrayList<Lesson> lessons = QueryUtils.extractLessons(day, jsonResponse);

        // Find a reference to the {@link ListView} in the layout
        ListView dayListView = (ListView) findViewById(R.id.list);

        mEmptyImageView = (ImageView) findViewById(R.id.empty_state_image);
        dayListView.setEmptyView(mEmptyImageView);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_text_view);
        dayListView.setEmptyView(mEmptyStateTextView);


        if (lessons.size() != 0) {

            final LessonAdapter itemsAdapter = new LessonAdapter(this, lessons);

            // Set the adapter on the {@link ListView}
            // so the list can be populated in the user interface
            dayListView.setAdapter(itemsAdapter);

        } else {

            // Set empty state text to display "Nie masz tego dnia żadnych zajęć. Miłego Dnia!"
            mEmptyStateTextView.setText(R.string.no_lessons_for_today);
            mEmptyImageView.setImageResource(R.drawable.wink_smile);
        }


    }
}
