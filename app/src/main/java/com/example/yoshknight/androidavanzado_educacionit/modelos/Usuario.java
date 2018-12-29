package com.example.yoshknight.androidavanzado_educacionit.modelos;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName="usuarios")
public class Usuario {
    @PrimaryKey
    private int id;

    private String name;

    @SerializedName("username")
    @ColumnInfo(name = "user_name")
    private String userName;

    private String email;
    @Ignore
    private Address address;

    private String phone;

    private String website;

    @Ignore
    private Company company;

    public Usuario(int id, String name, String userName, String email,  String phone, String website) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.address = null;
        this.phone = phone;
        this.website = website;
        this.company = null;
    }

    public Usuario(int id, String name, String userName, String email, Address address, String phone, String website, Company company) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.phone = phone;
        this.website = website;
        this.company = company;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
