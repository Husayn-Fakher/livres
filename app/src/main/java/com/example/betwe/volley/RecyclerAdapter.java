package com.example.betwe.volley;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by betwe on 7/18/2017.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    public static ArrayList<Book> arrayList = new ArrayList<>();
    Context context;

    public RecyclerAdapter(ArrayList<Book> arrayList, Context context){

        this.arrayList = arrayList;
        this.context = context;

    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.title.setText(arrayList.get(position).getTitle());
        Picasso.with(context).load(arrayList.get(position).getCover()).into(holder.imageView);
        final Book book = arrayList.get(position);

        final boolean checked = book.getChecked();

        if(checked)
        {

            holder.checkBox.setChecked(true);

        }
    else{

            holder.checkBox.setChecked(false);
        }

       holder.checkBox.setOnClickListener(new View.OnClickListener(){


           @Override
           public void onClick(View v) {

               if(((CheckBox)v).isChecked()){

                   book.setChecked(true);

               }else{

                   book.setChecked(false);
               }
           }
       });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView title;
        ImageView imageView;
        CheckBox checkBox;

        public MyViewHolder(View itemView) {
            super(itemView);

            title     = (TextView) itemView.findViewById(R.id.bookTitle);
            imageView = (ImageView) itemView.findViewById(R.id.urlImage);
            imageView.setOnClickListener(this);

            checkBox  = (CheckBox) itemView.findViewById(R.id.checkBox);

        }

        @Override
        public void onClick(View v) {

            int pos = getAdapterPosition();
            String synopsis = arrayList.get(pos).getSynopsis();


            Intent i = new Intent(v.getContext(),Synopsis.class);
            i.putExtra("synopsis",synopsis);

             v.getContext().startActivity(i);

        }
    }





}
