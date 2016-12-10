package com.example.joseluisgd.ulimarket.interfaces;

import com.example.joseluisgd.ulimarket.clases.Producto;
import com.example.joseluisgd.ulimarket.clases.Respuesta;
import com.example.joseluisgd.ulimarket.clases.RespuestaProducto;
import com.example.joseluisgd.ulimarket.clases.Usuario;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by joseluisgd on 11/28/16.
 */

public interface IService {
    @POST("/register")
    Call<Respuesta> registrar(@Body Usuario usuario);

    @POST("/login")
    Call<Respuesta> login(@Body Usuario usuario);

    @POST("/has/product")
    Call<RespuestaProducto> hasProduct(@Body Usuario usuario);

    @POST("/register/product")
    Call<Respuesta> addProducto(@Body Producto producto);

    @POST("/forgotpassword")
    Call<Respuesta> forgotPassword(@Body Usuario usuario);




}
