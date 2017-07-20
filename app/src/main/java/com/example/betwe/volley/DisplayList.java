package com.example.betwe.volley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DisplayList extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<Book> arrayList = new ArrayList<>();
    int totalPrice = 0;

    String json_url = "http://henri-potier.xebia.fr/books";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_list);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);



        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,json_url,(String) null,new Response.Listener<JSONArray>(){

            @Override
            public void onResponse(JSONArray response) {

                int count = 0;

                while(count < response.length()){

                    try {
                        JSONObject jsonObject = response.getJSONObject(count);
                        Book book = new Book(jsonObject.getString("isbn"),jsonObject.getString("title"),jsonObject.getString("price"),jsonObject.getString("cover"),false);

                        arrayList.add(book);
                        count++;

                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
                adapter = new RecyclerAdapter(arrayList,DisplayList.this);
                recyclerView.setAdapter(adapter);
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(DisplayList.this,"Error...", Toast.LENGTH_LONG).show();
                error.printStackTrace();

            }
        }
        );

        MySingleton.getInstance(DisplayList.this).addToRequestQueue(jsonArrayRequest);


    }


    public void selectBooks(View view){

        String ISBNs="";
        totalPrice = 0;
        boolean noPurchase = false;

        for(Book currentBook:RecyclerAdapter.arrayList)
        {
            if(currentBook.getChecked())
            {
                ISBNs = currentBook.getIsbn()+","+ISBNs;
                totalPrice = totalPrice + Integer.parseInt(currentBook.getPrice());

            }

        }

        if(ISBNs.equals("")){

            noPurchase = true;

        }

        String offer_url = "http://henri-potier.xebia.fr/books/"+ISBNs+"/commercialOffers";

        if(!noPurchase){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, offer_url,

                new Response.Listener<String>(){

                    @Override
                    public void onResponse(String response) {



                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray  jsonArray = jsonObject.getJSONArray("offers");

                            JSONObject Percentage = jsonArray.getJSONObject(0);
                            int percentage = Percentage.getInt("value");
                            int deduction = 0;
                            int sliceValue  = 0;
                            int sliceDeduction = 0;

                            if(jsonArray.length()>1)
                            {  JSONObject Deduction = jsonArray.getJSONObject(1);
                               JSONObject Slice = jsonArray.getJSONObject(2);
                                 deduction  = Deduction.getInt("value");
                                 sliceValue = Slice.getInt("sliceValue");
                                 sliceDeduction = Slice.getInt("value");
                            }


                            Log.d("VoDe","Total price before discounts "+totalPrice);
                            Log.d("VoDe","The deduction is "+deduction);

                            float payablePrice = (totalPrice -((float)(percentage * totalPrice)/100)) - deduction ;

                            Log.d("VoDe","After percentage and deduction "+payablePrice);

                            if(sliceValue>0) {
                                payablePrice = payablePrice - (totalPrice / sliceValue) * sliceDeduction;

                            }

                            Intent intent = new Intent(DisplayList.this, FinalPrice.class);
                            intent.putExtra("price",payablePrice);


                            Log.d("VoDe","final price "+payablePrice);

                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
            }
        });


        MySingleton.getInstance(DisplayList.this).addToRequestQueue(stringRequest);
    }else{

            Toast.makeText(DisplayList.this,"Pas d'achat", Toast.LENGTH_LONG).show();


        }

    }


}
