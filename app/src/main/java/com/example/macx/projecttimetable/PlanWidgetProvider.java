package com.example.macx.projecttimetable;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.os.Bundle;
import android.widget.RemoteViews;

import java.util.ArrayList;

/**
 * Implementation of App Widget functionality.
 */
public class PlanWidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Bundle options = appWidgetManager.getAppWidgetOptions(appWidgetId);
        int height = options.getInt(AppWidgetManager.OPTION_APPWIDGET_MAX_HEIGHT);
        RemoteViews views;

        if (height > 300) {
            views = getSingleItemRemoteView(context);
        } else {
            views = getListRemoteView(context);
        }


        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private static RemoteViews getSingleItemRemoteView(Context context){

        String time = "10:00 - 11:00";
        String name = "Automatyka";
        String localizatoin = "GG 306";
        String type = "W";
        String cycle = "I połowa sem.";

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_subject_list_item);

        views.setTextViewText(R.id.time_text_view, time);

        views.setTextViewText(R.id.subject_name_text_view, name);
        views.setTextViewText(R.id.location_text_view, localizatoin);
        views.setTextViewText(R.id.type_of_classes, type);
        views.setTextViewText(R.id.cycle_text_view, cycle);

        return views;
    }

    private static RemoteViews getListRemoteView (Context context){
        return null;
    }
    
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

