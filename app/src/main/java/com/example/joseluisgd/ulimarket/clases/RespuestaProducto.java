package com.example.joseluisgd.ulimarket.clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RespuestaProducto {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private long price;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("stock")
    @Expose
    private long stock;

    public RespuestaProducto(String name, long price, String location, long stock) {
        this.name = name;
        this.price = price;
        this.location = location;
        this.stock = stock;
    }

    public RespuestaProducto() {

    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The price
     */
    public long getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(long price) {
        this.price = price;
    }

    /**
     *
     * @return
     * The location
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return
     * The stock
     */
    public long getStock() {
        return stock;
    }

    /**
     *
     * @param stock
     * The stock
     */
    public void setStock(long stock) {
        this.stock = stock;
    }

}