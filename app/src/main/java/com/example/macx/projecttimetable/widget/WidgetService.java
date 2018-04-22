package com.example.macx.projecttimetable.widget;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViewsService;
import android.widget.Toast;

import java.io.FileInputStream;

public class WidgetService extends RemoteViewsService {
	/*
	 * So pretty simple just defining the Adapter of the listview
	 * here Adapter is ListProvider
	 * */

	@Override
	public RemoteViewsFactory onGetViewFactory(Intent intent) {
		int appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		Log.i(WidgetService.class.getName(), "TEST: SERVICE TESTING");
		return (new ListProvider(this.getApplicationContext(), intent, readFile("JSON_File")));
	}

	private String readFile (String fileName){
		String jsonResponse = "";
		Context ctx = getApplicationContext();

		try{
			FileInputStream inputStream = ctx.openFileInput(fileName);
			int size = inputStream.available();
			byte[] buffer = new byte[size];
			inputStream.read(buffer);
			inputStream.close();
			jsonResponse = new String(buffer);
			Log.i(WidgetService.class.getName(), "TEST: file read: " + jsonResponse);

		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(WidgetService.this, "Error ocurred when reading the file.", Toast.LENGTH_SHORT);
		}
		return jsonResponse;
	}

}
