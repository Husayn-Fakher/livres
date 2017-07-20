package com.example.betwe.volley;

/**
 * Created by betwe on 7/18/2017.
 */

public class Book {

    private String isbn,title,price,coverUrl;
    private boolean checked;

    public Book(String isbn, String title,String price,String coverUrl,boolean checked){

        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.coverUrl = coverUrl;
        this.checked = checked;

    }

    public String getIsbn(){

        return isbn;

    }

    public String getTitle(){

        return title;
    }

    public String getPrice(){

        return price;
    }

    public String getCover(){

        return coverUrl;
    }

    public boolean getChecked(){

        return checked;
    }

    public void setChecked(boolean status){

        checked = status;
    }


}
