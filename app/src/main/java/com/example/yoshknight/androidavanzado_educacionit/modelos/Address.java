package com.example.yoshknight.androidavanzado_educacionit.modelos;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class Address {
    private String street;

    private String suite;

    private String city;

    @SerializedName("zipcode")
    private String zipCode;

    private HashMap geo;

    public Address(String street, String suite, String city, String zipCode, HashMap geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipCode = zipCode;
        this.geo = geo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public HashMap getGeo() {
        return geo;
    }

    public void setGeo(HashMap geo) {
        this.geo = geo;
    }
}

