package com.validity.monolithstarter.domain;

/**
 * Record class is a bean representation of each row in CSV file.
 */
public class Record {
    
    int id;
    String first_name;
    String last_name;
    String company;
    String email;
    String address1;
    String address2;
    String zip;
    String city;
    String state_long;
    String state;
    String phone;

    public Record(int id, String first_name, String last_name, String company, String email, String address1, String address2, String zip, String city, String state_long, String state, String phone) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.company = company;
        this.email = email;
        this.address1 = address1;
        this.address2 = address2;
        this.zip = zip;
        this.city = city;
        this.state_long = state_long;
        this.state = state;
        this.phone = phone;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return this.first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return this.last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCompany() {
        return this.company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress1() {
        return this.address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return this.address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState_long() {
        return this.state_long;
    }

    public void setState_long(String state_long) {
        this.state_long = state_long;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}