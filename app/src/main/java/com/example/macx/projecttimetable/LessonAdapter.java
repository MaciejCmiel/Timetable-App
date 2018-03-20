package com.example.macx.projecttimetable;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by MacX on 2017-12-14.
 * An {@link LessonAdapter} knows how to create a list item layout for each Lesson
 * in the data source (a list of {@link Lesson} objects).
 */

public class LessonAdapter extends ArrayAdapter<Lesson> {

    public LessonAdapter(Activity context, ArrayList<Lesson> lessons) {
        super(context, 0, lessons);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.subject_list_item_new, parent, false);
        }

        Lesson currentLesson = getItem(position);

        // Find the TextView in subject_list_item_new layout with id subject_name_text_view
        TextView subjectTextView = (TextView) listItemView.findViewById(R.id.subject_name_text_view);
        // get the subject name from the current Lesson and set this text to TextView
        subjectTextView.setText(currentLesson.getSubjectName());

        TextView lecturerTextView = (TextView) listItemView.findViewById(R.id.lecturer_text_view);
        lecturerTextView.setText(currentLesson.getLecturer());

        TextView locationTextView = (TextView) listItemView.findViewById(R.id.location_text_view);
        locationTextView.setText(currentLesson.getLocation());

        TextView typeOfClassesTextView = (TextView) listItemView.findViewById(R.id.type_of_classes);
        typeOfClassesTextView.setText(currentLesson.getTypeOfClasses());

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.time_text_view);
        timeTextView.setText(formatTime(currentLesson.getStartTime(), currentLesson.getEndTime()));

        TextView cycleTextView = (TextView) listItemView.findViewById(R.id.cycle_text_view);
        cycleTextView.setText(currentLesson.getCycle());

        return listItemView;
    }

    /**
     *
     * @param startTime
     * @param endTime
     *
     * Return String with start and end time of lesson
     */
    private String formatTime(String startTime, String endTime) {

        return (startTime + "-" + endTime);
    }


}
