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

import java.util.ArrayList;


/**
 * Created by MacX on 2018-04-08.
 */

public class ListProvider implements RemoteViewsFactory {

    private ArrayList<Lesson> lessons = new ArrayList<Lesson>();
    private Context context = null;
    private int appWidgetId;

    public ListProvider(Context context, Intent intent) {
        this.context = context;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);

        populateLessons();
    }

    private static final String JSON_RESPONSE = "{\"username\":\"cmielm\",\"semester\":\"2017L\",\"firstname\":\"Maciej\",\"lastname\":\"Ćmiel\",\"studentNo\":\"279090\",\"planItems\":[{\"id\":22351,\"courseName\":\"Elektronika z techniką cyfrową 3\",\"courseNumber\":\"1DR1411\",\"courseVersion\":\"A\",\"teachers\":[\"doc. dr inż. Paweł Fabijański\",\"dr inż. Tadeusz Płatek\",\"mgr inż. Marta Grzegorczyk\"],\"startTime\":\"04:15:00 PM\",\"endTime\":\"06:00:00 PM\",\"dayOfWeek\":\"3\",\"cycle\":\"co tydzień\",\"groups\":[\"Grupa 3\"],\"building\":\"Stara Kotłownia\",\"room\":\"2A\",\"typeOfClasses\":\"L\",\"courseNameShort\":\"ETECYL3\",\"buildingShort\":\"SK\",\"cycleShort\":\"SEM\"},{\"id\":22157,\"courseName\":\"Maszyny i mikromaszyny elektryczne\",\"courseNumber\":\"1DR1412\",\"courseVersion\":\"A\",\"teachers\":[\"prof. nzw. dr hab. inż. Andrzej Pochanke\"],\"startTime\":\"12:15:00 PM\",\"endTime\":\"02:00:00 PM\",\"dayOfWeek\":\"3\",\"cycle\":\"II połowa sem.\",\"groups\":[\"Grupa 3\",\"Grupa 1\",\"Grupa 2\"],\"building\":\"Stara Kotłownia\",\"room\":\"107a\",\"typeOfClasses\":\"W\",\"courseNameShort\":\"MASZMI\",\"buildingShort\":\"SK\",\"cycleShort\":\"2PS\"},{\"id\":22155,\"courseName\":\"Maszyny i mikromaszyny elektryczne\",\"courseNumber\":\"1DR1412\",\"courseVersion\":\"A\",\"teachers\":[\"prof. nzw. dr hab. inż. Andrzej Pochanke\"],\"startTime\":\"02:15:00 PM\",\"endTime\":\"04:00:00 PM\",\"dayOfWeek\":\"1\",\"cycle\":\"co tydzień\",\"groups\":[\"Grupa 2\",\"Grupa 3\",\"Grupa 1\"],\"building\":\"Stara Kotłownia\",\"room\":\"107a\",\"typeOfClasses\":\"W\",\"courseNameShort\":\"MASZMI\",\"buildingShort\":\"SK\",\"cycleShort\":\"SEM\"},{\"id\":22156,\"courseName\":\"Oprogramowanie robotów\",\"courseNumber\":\"1DR1413\",\"courseVersion\":\"A\",\"teachers\":[\"dr inż. Dariusz Sobczuk\"],\"startTime\":\"12:15:00 PM\",\"endTime\":\"02:00:00 PM\",\"dayOfWeek\":\"3\",\"cycle\":\"I połowa sem.\",\"groups\":[\"Grupa 3\",\"Grupa 1\",\"Grupa 2\"],\"building\":\"Stara Kotłownia\",\"room\":\"107a\",\"typeOfClasses\":\"W\",\"courseNameShort\":\"OPRO\",\"buildingShort\":\"SK\",\"cycleShort\":\"1PS\"},{\"id\":22290,\"courseName\":\"Podstawy automatyki lab\",\"courseNumber\":\"1DR1414\",\"courseVersion\":\"A\",\"teachers\":[\"prof. nzw. dr hab. inż. Dominik Sierociuk\",\"dr inż. Rafał Łopatka\"],\"startTime\":\"08:15:00 AM\",\"endTime\":\"12:00:00 PM\",\"dayOfWeek\":\"4\",\"cycle\":\"zaj. nieparz.\",\"groups\":[\"Grupa 3\"],\"building\":\"Stara Kotłownia\",\"room\":\"201\",\"typeOfClasses\":\"L\",\"courseNameShort\":\"PAL\",\"buildingShort\":\"SK\",\"cycleShort\":\"NP\"},{\"id\":22357,\"courseName\":\"Podstawy robotyki\",\"courseNumber\":\"1DR1415\",\"courseVersion\":\"A\",\"teachers\":[\"dr inż. Piotr Fabijański\"],\"startTime\":\"02:15:00 PM\",\"endTime\":\"04:00:00 PM\",\"dayOfWeek\":\"3\",\"cycle\":\"co tydzień\",\"groups\":[\"Grupa 3\"],\"building\":\"Gmach Mechaniki\",\"room\":\"203\",\"typeOfClasses\":\"C\",\"courseNameShort\":\"POR\",\"buildingShort\":\"GM\",\"cycleShort\":\"SEM\"},{\"id\":22353,\"courseName\":\"Podstawy robotyki\",\"courseNumber\":\"1DR1415\",\"courseVersion\":\"A\",\"teachers\":[\"dr inż. Dariusz Sobczuk\"],\"startTime\":\"08:15:00 AM\",\"endTime\":\"12:00:00 PM\",\"dayOfWeek\":\"5\",\"cycle\":\"II połowa sem.\",\"groups\":[\"Grupa 3\"],\"building\":\"Stara Kotłownia\",\"room\":\"06\",\"typeOfClasses\":\"L\",\"courseNameShort\":\"POR\",\"buildingShort\":\"SK\",\"cycleShort\":\"2PS\"},{\"id\":22159,\"courseName\":\"Podstawy teorii sterowania\",\"courseNumber\":\"1DR1416\",\"courseVersion\":\"A\",\"teachers\":[\"dr inż. Rafał Łopatka\"],\"startTime\":\"02:15:00 PM\",\"endTime\":\"04:00:00 PM\",\"dayOfWeek\":\"4\",\"cycle\":\"co tydzień\",\"groups\":[\"Grupa 1\",\"Grupa 2\",\"Grupa 3\"],\"building\":\"Stara Kotłownia\",\"room\":\"107a\",\"typeOfClasses\":\"W\",\"courseNameShort\":\"TESA\",\"buildingShort\":\"SK\",\"cycleShort\":\"SEM\"},{\"id\":22158,\"courseName\":\"Przetworniki pomiarowe\",\"courseNumber\":\"1DR1417\",\"courseVersion\":\"A\",\"teachers\":[\"dr inż. Bogdan Dziadak\",\"prof. dr hab. inż. Andrzej Michalski\"],\"startTime\":\"12:15:00 PM\",\"endTime\":\"02:00:00 PM\",\"dayOfWeek\":\"4\",\"cycle\":\"co tydzień\",\"groups\":[\"Grupa 1\",\"Grupa 2\",\"Grupa 3\"],\"building\":\"Gmach Główny PW\",\"room\":\"306\",\"typeOfClasses\":\"W\",\"courseNameShort\":\"PRZE\",\"buildingShort\":\"GG\",\"cycleShort\":\"SEM\"},{\"id\":22153,\"courseName\":\"Sterowanie mikroprocesorowe\",\"courseNumber\":\"1DR1418\",\"courseVersion\":\"A\",\"teachers\":[\"dr inż. Maciej Dzieniakowski\"],\"startTime\":\"10:15:00 AM\",\"endTime\":\"12:00:00 PM\",\"dayOfWeek\":\"1\",\"cycle\":\"co tydzień\",\"groups\":[\"Grupa 1\",\"Grupa 2\",\"Grupa 3\"],\"building\":\"Stara Kotłownia\",\"room\":\"107a\",\"typeOfClasses\":\"W\",\"courseNameShort\":\"STEM\",\"buildingShort\":\"SK\",\"cycleShort\":\"SEM\"},{\"id\":22154,\"courseName\":\"Teoria przekształtników\",\"courseNumber\":\"1DR1419\",\"courseVersion\":\"A\",\"teachers\":[\"dr hab. inż. Jacek Rąbkowski\"],\"startTime\":\"12:15:00 PM\",\"endTime\":\"02:00:00 PM\",\"dayOfWeek\":\"1\",\"cycle\":\"co tydzień\",\"groups\":[\"Grupa 1\",\"Grupa 2\",\"Grupa 3\"],\"building\":\"Stara Kotłownia\",\"room\":\"107a\",\"typeOfClasses\":\"W\",\"courseNameShort\":\"TEP\",\"buildingShort\":\"SK\",\"cycleShort\":\"SEM\"},{\"id\":22349,\"courseName\":\"Teoria przekształtników\",\"courseNumber\":\"1DR1419\",\"courseVersion\":\"A\",\"teachers\":[\"dr inż. Piotr Grzejszczak\",\"dr inż. Mariusz Zdanowski\"],\"startTime\":\"04:15:00 PM\",\"endTime\":\"07:00:00 PM\",\"dayOfWeek\":\"4\",\"cycle\":\"co tydzień\",\"groups\":[\"Grupa 3\"],\"building\":\"Gmach Elektrotechniki\",\"room\":\"007b\",\"typeOfClasses\":\"L\",\"courseNameShort\":\"TEP\",\"buildingShort\":\"GE-b\",\"cycleShort\":\"SEM\"},{\"id\":22162,\"courseName\":\"Wychowanie fizyczne 4\",\"courseNumber\":\"1DW1401\",\"courseVersion\":\"A\",\"teachers\":[],\"startTime\":\"12:15:00 PM\",\"endTime\":\"02:00:00 PM\",\"dayOfWeek\":\"2\",\"cycle\":\"co tydzień\",\"groups\":[\"Grupa 3\",\"Grupa 1\",\"Grupa 2\"],\"building\":\"DS Riviera\",\"room\":\"Studium WF\",\"typeOfClasses\":\"C\",\"courseNameShort\":\"WF4\",\"buildingShort\":\"RIV\",\"cycleShort\":\"SEM\"}]}";

    private void populateLessons() {

        lessons = QueryUtils.extractLessons(1, JSON_RESPONSE);

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
