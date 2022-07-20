package com.hcl.ecommerce.Model;

public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String country;
    private String phonenumber;
    private int zip;
    private String state;
    private String address;
    private String city;
    private String email;
    private String password;

    private String usertype;


    public User(){

    }
    public User(String firstname, String email, String password, String usertype, String address, String city, int zip, String state, String country, String lastname, String phonenumber) {
        this.firstname = firstname;
        this.email = email;
        this.password = password;
        this.usertype = usertype;
        this.address = address;
        this.city = city;
        this.zip = zip;
        this.state = state;
        this.country = country;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
    }

    public User(int id, String firstname, String email, String password, String usertype, String address, String city, int zip, String state, String country, String lastname, String phonenumber) {
        this.id = id;
        this.firstname =firstname;
        this.usertype = usertype;
        this.email = email;
        this.password = password;
        this.address = address;
        this.city = city;
        this.country = country;
        this.zip = zip;
        this.state = state;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", country='" + country + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", zip=" + zip +
                ", state='" + state + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", usertype='" + usertype + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
//Alex Comment