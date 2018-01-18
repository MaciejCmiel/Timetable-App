package com.example.macx.projecttimetable;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


/**
 * Created by MacX on 2018-01-07.
 */

public class DayChoiceActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String> {

    private static final int JSON_LOADER_ID = 1;
    public static final String LOG_TAG = DayChoiceActivity.class.getName();
    private String mJsonResponseToGo = "";
    private String queryUrl = "";
    private TextView mWrongDataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day_choice);

        // get the query url from previous activity
        queryUrl = getIntent().getStringExtra("QUERY_URL");
        Log.i(TimetableActivity.class.getName(), " TEST" + queryUrl);

        //hide the days of week to show indicator
        View days = findViewById(R.id.days_container);
        days.setVisibility(View.INVISIBLE);

        // hide the wrong data text view
        mWrongDataTextView = (TextView) findViewById(R.id.wrong_data_text_view);
        mWrongDataTextView.setVisibility(View.INVISIBLE);

        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter
            loaderManager.initLoader(JSON_LOADER_ID, null, this);

        } else {

            // Hide loading indicator because there is no internet connection
            View loadingIndicator = findViewById(R.id.progress_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mWrongDataTextView.setVisibility(View.VISIBLE);
            mWrongDataTextView.setText(R.string.no_internet_connection);
        }

    }


    @Override
    public Loader<String> onCreateLoader(int i, Bundle bundle) {

        Log.i(LOG_TAG, "TEST: onCreateLoader");
        // Create a new loader for the given URL
        return new JsonLoader(this, queryUrl);

    }

    // Do the same as postExecute
    //Take returned JsonLoader from onCreateLoader
    @Override
    public void onLoadFinished(Loader<String> loader, String jsonResponse) {

        // Hide loading indicator because the data has been loaded
        View loadingIndicator = findViewById(R.id.progress_indicator);
        loadingIndicator.setVisibility(View.GONE);
        View days = findViewById(R.id.days_container);

        if (jsonResponse != null) {

            if (jsonResponse.startsWith("Exception")) {
                mWrongDataTextView.setVisibility(View.VISIBLE);
                mWrongDataTextView.setText(jsonResponse + "\n \n Skontaktuj się z:  " +
                        "isod.wsparcie@iem.pw.edu.pl" +
                        "\nlub autorem aplikacji: " +
                        "\nmaciej.cmiel.S@ee.pw.edu.pl");
            } else if (jsonResponse.startsWith("Brak")) {
                mWrongDataTextView.setVisibility(View.VISIBLE);
                jsonResponse = jsonResponse.replace("Proszę skontaktować się z isod.wsparcie@iem.pw.edu.pl", " ");
                mWrongDataTextView.setText(jsonResponse);
            } else {

                // set the days container to be visible
                days.setVisibility(View.VISIBLE);

                mJsonResponseToGo = jsonResponse;

                //Find the view that shows each day
                TextView monday = (TextView) findViewById(R.id.monday);
                TextView tuesday = (TextView) findViewById(R.id.tuesday);
                TextView wednesday = (TextView) findViewById(R.id.wednesday);
                TextView thursday = (TextView) findViewById(R.id.thursday);
                TextView friday = (TextView) findViewById(R.id.friday);

                monday.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        // create new Intent to open TimetableActivity
                        Intent dayIntent = new Intent(DayChoiceActivity.this, TimetableActivity.class);
                        dayIntent.putExtra("DAY_OF_WEEK", 1);
                        dayIntent.putExtra("JSON_RESPONSE", mJsonResponseToGo);

                        //start new activity
                        startActivity(dayIntent);
                    }
                });

                tuesday.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent dayIntent = new Intent(DayChoiceActivity.this, TimetableActivity.class);
                        dayIntent.putExtra("DAY_OF_WEEK", 2);
                        dayIntent.putExtra("JSON_RESPONSE", mJsonResponseToGo);

                        startActivity(dayIntent);
                    }

                });

                wednesday.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent dayIntent = new Intent(DayChoiceActivity.this, TimetableActivity.class);
                        dayIntent.putExtra("DAY_OF_WEEK", 3);
                        dayIntent.putExtra("JSON_RESPONSE", mJsonResponseToGo);

                        startActivity(dayIntent);
                    }

                });

                thursday.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent dayIntent = new Intent(DayChoiceActivity.this, TimetableActivity.class);
                        dayIntent.putExtra("DAY_OF_WEEK", 4);
                        dayIntent.putExtra("JSON_RESPONSE", mJsonResponseToGo);

                        startActivity(dayIntent);
                    }

                });

                friday.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        Intent dayIntent = new Intent(DayChoiceActivity.this, TimetableActivity.class);
                        dayIntent.putExtra("DAY_OF_WEEK", 5);
                        dayIntent.putExtra("JSON_RESPONSE", mJsonResponseToGo);

                        startActivity(dayIntent);
                    }

                });

            }
        }
    }


    @Override
    public void onLoaderReset(Loader<String> loader) {
        // Loader reset, so we can clear out our existing data.
        mJsonResponseToGo = "";
    }


}
