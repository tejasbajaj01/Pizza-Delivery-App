package com.example.pixxa.model;

public class User {
    private int id;
    private String username;
    private String name;
    private String email;
    private String phone;
    private String password;

    public User (int id, String name, String username, String phone, String email, String password){
        this.id= id;
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;

    }
    public User (String name, String username, String phone, String email, String password) {
        this.name = name;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){this.name = name;}
    public String getUsername(){return username;}
    public void setUsername(String username){this.username = username;}
    public String getPhone(){return phone;}
    public void setPhone(String phone){this.phone = phone;}
    public String getEmail(){return email;}
    public void  setEmail(String email){this.email = email;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}


}
