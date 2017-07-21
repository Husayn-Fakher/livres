package com.example.betwe.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Synopsis extends AppCompatActivity {

    TextView synopsisTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synopsis);

        synopsisTV = (TextView) findViewById(R.id.synopsisText);

        String synopsis = "no data";

        if (savedInstanceState == null) {

            Bundle extras = getIntent().getExtras();
            if(extras == null) {

                synopsis = "no data";
            } else {
                synopsis = extras.getString("synopsis");
            }
        } else {
            synopsis = (String) savedInstanceState.getSerializable("synopsis");
        }

        synopsisTV.setText(synopsis);
    }
}
