package com.example.joseluisgd.ulimarket.clases;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Producto {

    @SerializedName("user")
    @Expose
    private String user;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("stock")
    @Expose
    private int stock;

    public Producto(String user, String name, int price, String location, int stock) {
        this.user = user;
        this.name = name;
        this.price = price;
        this.location = location;
        this.stock = stock;
    }

    public Producto(String name, String location, int stock, int price) {
        this.name = name;
        this.location = location;
        this.stock = stock;
        this.price = price;
    }

    public String getUser() {

        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
    public int getPrice() {
        return price;
    }

    /**
     *
     * @param price
     * The price
     */
    public void setPrice(int price) {
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
    public int getStock() {
        return stock;
    }

    /**
     *
     * @param stock
     * The stock
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

}