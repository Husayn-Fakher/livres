package com.example.betwe.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class FinalPrice extends AppCompatActivity {

    TextView priceTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_price);

        priceTV = (TextView) findViewById(R.id.priceTV);

        float finalPrice;

        if (savedInstanceState == null) {



            Bundle extras = getIntent().getExtras();
            if(extras == null) {

                finalPrice= -1;
            } else {
                finalPrice= extras.getFloat("price");
            }
        } else {
            finalPrice = (Float) savedInstanceState.getSerializable("price");
        }


        priceTV.setText("Prix Total "+finalPrice);
    }
}
