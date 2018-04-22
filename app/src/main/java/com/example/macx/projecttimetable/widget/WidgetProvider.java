package com.example.macx.projecttimetable.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.macx.projecttimetable.R;

import java.util.Calendar;

/**
 * Implementation of App Widget functionality.
 */
public class WidgetProvider extends AppWidgetProvider {

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

        remoteViews.setTextViewText(R.id.day_title, getDay(context));
        //setting adapter to listview of the widget
        remoteViews.setRemoteAdapter(appWidgetId, R.id.list_view, svcIntent);
        //setting an empty view in case of no data
        remoteViews.setEmptyView(R.id.list, R.id.empty_view);
        return remoteViews;
    }

    private static String getDay(Context context) {

        int day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        int resourceId = day == 2 ? R.string.day_1 : day == 3 ? R.string.day_2 :
                         day == 4 ? R.string.day_3 : day == 5 ? R.string.day_4 :
                         day == 6 ? R.string.day_5 : day == 7 ? R.string.day_6 : R.string.day_7;
        return context.getResources().getString(resourceId);
    }
}

