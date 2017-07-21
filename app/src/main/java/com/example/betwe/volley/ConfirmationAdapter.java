package com.example.betwe.volley;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by betwe on 7/21/2017.
 */

public class ConfirmationAdapter extends RecyclerView.Adapter<ConfirmationAdapter.MyViewHolder>{

    public static ArrayList<Book> arrayList = new ArrayList<>();
    Context context;


    public ConfirmationAdapter(Context context){


        this.arrayList = DisplayList.getPurchasedBooks();


        this.context = context;


    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item2,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ConfirmationAdapter.MyViewHolder holder, int position) {

        holder.title.setText(arrayList.get(position).getTitle());
        Picasso.with(context).load(arrayList.get(position).getCover()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title;
        ImageView imageView;


        public MyViewHolder(View itemView) {
            super(itemView);

            title     = (TextView) itemView.findViewById(R.id.bookTitle2);
            imageView = (ImageView) itemView.findViewById(R.id.urlImage2);

        }
    }
}
