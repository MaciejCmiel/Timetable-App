package com.example.macx.projecttimetable.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.macx.projecttimetable.R;

/**
 * Implementation of App Widget functionality.
 */
public class WidgetProvider extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        /*
        // tutorialspoint suggestion
        //PendingIntent pending = PendingIntent.getActivity(context, 0, intent, 0);
        String time = "10:00 - 11:00";
        String name = "Automatyka";
        String localizatoin = "GG 306";
        String type = "W";
        String cycle = "I połowa sem.";

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);


        views.setTextViewText(R.id.time_text_view, time);

        views.setTextViewText(R.id.subject_name_text_view, name);
        views.setTextViewText(R.id.location_text_view, localizatoin);
        views.setTextViewText(R.id.type_of_classes, type);
        views.setTextViewText(R.id.cycle_text_view, cycle);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
        */

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Log.i(WidgetProvider.class.getName(), "TEST: onUpdate");
        final int N = appWidgetIds.length;
		/*int[] appWidgetIds holds ids of multiple instance of your widget
		 * meaning you are placing more than one widgets on your homescreen*/
        for (int i = 0; i < N; ++i) {
            RemoteViews remoteViews = updateWidgetListView(context, appWidgetIds[i]);
            appWidgetManager.updateAppWidget(appWidgetIds[i], remoteViews);
        }
        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

    private RemoteViews updateWidgetListView(Context context, int appWidgetId) {

        Log.i(WidgetProvider.class.getName(), "TEST: updateWidgetListView");

        //which layout to show on widget
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget);

        //RemoteViews Service needed to provide adapter for ListView
        Intent svcIntent = new Intent(context, WidgetService.class);
        //passing app widget id to that RemoteViews Service
        svcIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        //setting a unique Uri to the intent
        //don't know its purpose to me right now
        svcIntent.setData(Uri.parse(svcIntent.toUri(Intent.URI_INTENT_SCHEME)));
        //setting adapter to listview of the widget
        remoteViews.setRemoteAdapter(appWidgetId, R.id.list_view, svcIntent);
        //setting an empty view in case of no data
        remoteViews.setEmptyView(R.id.list, R.id.empty_view);
        return remoteViews;
    }
}

