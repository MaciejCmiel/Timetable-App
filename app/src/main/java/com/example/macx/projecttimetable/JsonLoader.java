package com.example.macx.projecttimetable;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import com.example.macx.projecttimetable.activities.DayChoiceActivity;

import java.io.File;
import java.io.FileOutputStream;


/**
 * Created by MacX on 2017-12-30.
 */

public class JsonLoader extends AsyncTaskLoader<String> {

    public static final String LOG_TAG = DayChoiceActivity.class.getName();

    /**
     * Query URL
     */
    private String url;

    /**
     * Constructs a new {@link JsonLoader}.
     *
     * @param context of the activity
     * @param url     to load data from
     */
    public JsonLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "TEST: onStartLoading() called ");
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        Log.i(LOG_TAG, "TEST: calling loadInBackground ");
        if (url == null) {
            return null;
        }

        // Perform the network request, get the JSON response, and return it as a string
        String jsonResponseToGo = QueryUtils.getJsonResponse(url);

        FileOutputStream outputStream;
        Context ctx = getContext();

        try {
            outputStream = ctx.openFileOutput("JSON_File", Context.MODE_PRIVATE);
            outputStream.write(jsonResponseToGo.getBytes());
            outputStream.close();
            Log.i(JsonLoader.class.getName(), "TEST: file write ");

        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonResponseToGo;
    }
}