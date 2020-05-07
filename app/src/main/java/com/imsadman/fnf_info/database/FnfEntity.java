package com.imsadman.fnf_info.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "fnf")
public class FnfEntity {

    @PrimaryKey(autoGenerate = false)
    private int id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "date_of_birth")
    private String dob;
    private String email;
    private String phone_number;
    private String facebook;
    private String instagram;
    private String address;
    private String postalCode;
    private String city;

    public FnfEntity(int id, String name, String dob, String email, String phone_number, String facebook, String instagram, String address, String postalCode, String city) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.phone_number = phone_number;
        this.facebook = facebook;
        this.instagram = instagram;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
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

    public String getPhone_number() {
        return phone_number;
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
