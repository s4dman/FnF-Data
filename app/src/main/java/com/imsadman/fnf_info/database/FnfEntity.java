package com.imsadman.fnf_info.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fnf")
public class FnfEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "date_of_birth")
    private String dob;
    private String email;
    private String phoneNumber;
    private String facebook;
    private String instagram;
    private String address;
    private String postalCode;
    private String city;

    public FnfEntity(String name, String dob, String email, String phoneNumber, String facebook, String instagram, String address, String postalCode, String city) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.facebook = facebook;
        this.instagram = instagram;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getFacebook() {
        return facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }
}