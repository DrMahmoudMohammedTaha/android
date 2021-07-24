package com.example.dell.movieapp;


import java.io.Serializable;

public class movie implements Serializable{

    movie(String t , String i)
    {
        title = t;
        Image = i;
    }
    movie()
    {


    }
    String id = "";
    String title = "";
    String Image = "";
    String date = "" ;
    String overview = "";
    String trailers[];
    review [] reviews ;

}
