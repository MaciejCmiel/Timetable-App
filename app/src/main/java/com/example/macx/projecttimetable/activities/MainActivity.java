package com.example.macx.projecttimetable.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.macx.projecttimetable.R;

public class MainActivity extends AppCompatActivity {


    private Spinner semesterSpinner;
    private String chosenSemester;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        semesterSpinner = (Spinner) findViewById(R.id.semester_spinner);
        ArrayAdapter adapter3 = ArrayAdapter.createFromResource(this, R.array.semestr_array, android.R.layout.simple_spinner_item);
        semesterSpinner.setAdapter(adapter3);


        semesterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView toastText = (TextView) view;
                //Toast.makeText(getApplicationContext(), "Selected: " + toastText.getText(), Toast.LENGTH_SHORT).show();
                chosenSemester = (String) toastText.getText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Button timeTable = (Button) findViewById(R.id.search_button);

        timeTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String finalUrl = formQueryUrl();

                Intent timeTableIntent = new Intent(MainActivity.this, DayChoiceActivity.class);
                timeTableIntent.putExtra("QUERY_URL", finalUrl);
                startActivity(timeTableIntent);
            }
        });

    }

    /**
     * This method return string with formed query url
     *
     */
    private String formQueryUrl() {

        String formedUrl = "https://isod.ee.pw.edu.pl/isod-portal/wapi?q=myplan&username=" + getUserName()
                + "&apikey=" + getApiKey() + "&semester=" + chosenSemester;

        Log.i(MainActivity.class.getName(), " TEST URL: " + formedUrl);

        return formedUrl;
    }

    /**
     * @return userName from user input
     */
    private String getUserName() {
        EditText text = (EditText) findViewById(R.id.user_name);
        String userName = text.getText().toString();
        return userName;
    }

    /**
     * @return apiKey from user input
     */
    private String getApiKey() {
        EditText text = (EditText) findViewById(R.id.api_key);
        String apiKey = text.getText().toString();
        return apiKey;
    }

}

