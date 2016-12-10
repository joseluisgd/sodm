package com.example.joseluisgd.ulimarket.conexion;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by joseluisgd on 11/28/16.
 */

public class Conexion {
    public static final String BASE_URL = "https://webservice-ulimarket.herokuapp.com/";

    //public static final String BASE_URL = "http://192.168.2.50:3500/";
    public Retrofit getConexion(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return  retrofit;
    }
}