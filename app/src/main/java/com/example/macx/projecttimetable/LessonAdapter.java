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

        ViewHolder holder;

        // Check if the existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.subject_list_item_new, parent, false);

            holder = new ViewHolder();
            holder.subjectTextView =  convertView.findViewById(R.id.subject_name_text_view);
            holder.lecturerTextView =  convertView.findViewById(R.id.lecturer_text_view);
            holder.locationTextView =  convertView.findViewById(R.id.location_text_view);
            holder.typeOfClassesTextView =  convertView.findViewById(R.id.type_of_classes);
            holder.timeTextView =  convertView.findViewById(R.id.time_text_view);
            holder.cycleTextView =  convertView.findViewById(R.id.cycle_text_view);
        } else{
            holder = (ViewHolder) convertView.getTag();
        }

        Lesson currentLesson = getItem(position);

        holder.subjectTextView.setText(currentLesson.getSubjectName());
        holder.lecturerTextView.setText(currentLesson.getLecturer());
        holder.locationTextView.setText(currentLesson.getLocation());
        holder.typeOfClassesTextView.setText(currentLesson.getTypeOfClasses());
        holder.timeTextView.setText(formatTime(currentLesson.getStartTime(), currentLesson.getEndTime()));
        holder.cycleTextView.setText(currentLesson.getCycle());

        return convertView;
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

    static class ViewHolder {
        private TextView subjectTextView;
        private TextView lecturerTextView;
        private TextView locationTextView;
        private TextView typeOfClassesTextView;
        private TextView timeTextView;
        private TextView cycleTextView;
    }

}
