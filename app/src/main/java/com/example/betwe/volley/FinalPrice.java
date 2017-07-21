package com.example.betwe.volley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FinalPrice extends AppCompatActivity {

    TextView priceTV;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
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


        recyclerView = (RecyclerView) findViewById(R.id.recycler2);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        adapter = new ConfirmationAdapter(this);
        recyclerView.setAdapter(adapter);
    }


    public void confirmPurchase(View view){

        Toast.makeText(this,"Confirmation Recu ",Toast.LENGTH_SHORT).show();
        finish();

    }


}
