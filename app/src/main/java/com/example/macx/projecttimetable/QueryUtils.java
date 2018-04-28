package com.example.macx.projecttimetable;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static com.example.macx.projecttimetable.activities.DayChoiceActivity.LOG_TAG;


/**
 * Created by MacX on 2017-12-21.
 *
 * Helper methods related to requesting and receiving Lesson timetable from ISOD.
 */


public final class QueryUtils {

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils.
     */

    private QueryUtils() {
    }

    public static String getJsonResponse(String requestUrl) {


        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error closing input stream", e);
        }

        // Return the JSON Response
        return jsonResponse;
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the lesson JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        Log.i(LOG_TAG, "jsonResponse = " + jsonResponse);
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }


    /**
     * Return a list of {@link Lesson} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Lesson> extractLessons(int chosenDayOfWeek, String jsonResponse) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        // Create an empty ArrayList that we can start adding Lessons to it
        ArrayList<Lesson> lessons = new ArrayList<>();


        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            JSONObject root = new JSONObject(jsonResponse);

            JSONArray arrayOfLessons = root.getJSONArray("planItems");


            // building up a list of Lesson objects with the corresponding data, for specified day.
            for (int i = 0; i < arrayOfLessons.length(); i++) {

                // Get a single lesson at position i within the list of lessons
                JSONObject currentLesson = arrayOfLessons.getJSONObject(i);

                // Extract the value for the key called "dayOfWeek"
                int dayOfWeek = currentLesson.getInt("dayOfWeek");

                // check if dayOfWeek is equal to chosen day by the user
                // if yes then extract data for this lesson and add to the list
                if (dayOfWeek + 1 == chosenDayOfWeek) {
                    JSONArray teachers = currentLesson.getJSONArray("teachers");
                    String lecturer;

                    //check if there is something in teachers array
                    if (teachers.length() != 0) {
                        lecturer = teachers.getString(0);
                    } else {
                        lecturer = "";
                    }
                    // Extract the value for the key called "startTime"
                    String startTime = convertHours(currentLesson.getString("startTime"));
                    // Extract the value for the key called "endTime"
                    String endTime = convertHours(currentLesson.getString("endTime"));
                    // Extract the value for the key called "subjectName"
                    String subjectName = currentLesson.getString("courseName");
                    // Extract the value for the keys called "buildingShort" and "room" and concatenate them
                    String location = currentLesson.getString("buildingShort") + " " + currentLesson.getString("room");
                    String typeOfClasses = currentLesson.getString("typeOfClasses");
                    String cycle = currentLesson.getString("cycle");


                    Lesson lesson = new Lesson(dayOfWeek, startTime, endTime, subjectName, lecturer, location, typeOfClasses, cycle);
                    lessons.add(lesson);
                }

            }

        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // sort the lessons by startTime before return the list
        Collections.sort(lessons, new Comparator<Lesson>() {
            @Override
            public int compare(Lesson lesson1, Lesson lesson2)
            {
                return  lesson1.getStartTime().compareTo(lesson2.getStartTime());
            }
        });

        return lessons;
    }

    /**
     * This method is converting time format from 12 hours to 24, and deleting redundant informations
     *
     * @param time is the time to convert, startTime or endTime
     * @return converted time
     */
    private static String convertHours(String time) {
        String formattedTime;
        if (time.endsWith("PM")) {
            String firstTwo = time.substring(0, 2);
            String rest = time.substring(2);
            int firstTwoInt = Integer.parseInt(firstTwo);
            if (firstTwoInt < 12) {
                firstTwoInt = firstTwoInt + 12;
            }
            formattedTime = (firstTwoInt + rest).substring(0, 5);
        } else {
            formattedTime = time.substring(0, 5);
        }
        return formattedTime;
    }

}

