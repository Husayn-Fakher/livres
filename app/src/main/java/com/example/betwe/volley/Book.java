package com.example.betwe.volley;

/**
 * Created by betwe on 7/18/2017.
 */

public class Book {

    private String isbn,title,price,coverUrl,synopsis;
    private boolean checked;

    public Book(String isbn, String title,String price,String coverUrl,String synopsis,boolean checked){

        this.isbn = isbn;
        this.title = title;
        this.price = price;
        this.coverUrl = coverUrl;
        this.checked = checked;
        this.synopsis = synopsis;

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

    public String getSynopsis(){

        return synopsis;
    }


    public void setChecked(boolean status){

        checked = status;
    }


}
