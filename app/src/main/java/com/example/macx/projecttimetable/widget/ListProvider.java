package com.example.macx.projecttimetable.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService.RemoteViewsFactory;

import com.example.macx.projecttimetable.Lesson;
import com.example.macx.projecttimetable.QueryUtils;
import com.example.macx.projecttimetable.R;

import java.io.FileInputStream;
import java.util.ArrayList;


/**
 * Created by MacX on 2018-04-08.
 */

public class ListProvider implements RemoteViewsFactory {

    private ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    private Context context = null;
    private int appWidgetId;

    public ListProvider(Context context, Intent intent, String jsonResponse) {
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        populateLessons(jsonResponse);
    }

    private void populateLessons(String jsonResponse) {

        lessons = QueryUtils.extractLessons(1, jsonResponse);
    }

    @Override
    public int getCount() {
        return lessons.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        final RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_subject_list_item);

        Log.i(ListProvider.class.getName(), "TEST: getViewAt");

        Lesson currentLesson = lessons.get(position);

        views.setTextViewText(R.id.time_text_view, (formatTime(currentLesson.getStartTime(), currentLesson.getEndTime())));
        views.setTextViewText(R.id.subject_name_text_view, currentLesson.getSubjectName());
        views.setTextViewText(R.id.location_text_view, currentLesson.getLocation());
        views.setTextViewText(R.id.type_of_classes, currentLesson.getTypeOfClasses());
        views.setTextViewText(R.id.cycle_text_view, currentLesson.getCycle());

        return views;
    }

    private String formatTime(String startTime, String endTime) {

        return (startTime + "-" + endTime);
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {

    }

    @Override
    public void onDestroy() {

    }
}
