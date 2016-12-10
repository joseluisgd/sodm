package com.example.joseluisgd.ulimarket.clases;


import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;


public class Usuario {

    @SerializedName("user")
    @Expose
    private int user;
    @SerializedName("pwd")
    @Expose
    private String pwd;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("car")
    @Expose
    private String car;
    @SerializedName("sex")
    @Expose
    private String sex;
    @SerializedName("email")
    @Expose
    private String email;

    public Usuario(int user, String pwd, String name, String car, String sex, String email) {
        this.user = user;
        this.pwd = pwd;
        this.name = name;
        this.car = car;
        this.sex = sex;
        this.email = email;
    }

    public Usuario() {

    }

    public Usuario(int user, String pwd) {
        this.user = user;
        this.pwd = pwd;
    }

    public Usuario(String email, int user) {
        this.email = email;
        this.user = user;
    }

    public Usuario(int user) {
        this.user = user;
    }

    /**
     *
     *
     * @return
     * The user
     */
    public int getUser() {
        return user;
    }

    /**
     *
     * @param user
     * The user
     */
    public void setUser(int user) {
        this.user = user;
    }

    /**
     *
     * @return
     * The pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     *
     * @param pwd
     * The pwd
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
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
     * The car
     */
    public String getCar() {
        return car;
    }

    /**
     *
     * @param car
     * The car
     */
    public void setCar(String car) {
        this.car = car;
    }

    /**
     *
     * @return
     * The sex
     */
    public String getSex() {
        return sex;
    }

    /**
     *
     * @param sex
     * The sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
