package com.example.homework2.models;

public class User extends Entertainment {

    public String name;
    public String id;
    public String username;
    public String email;
    public String address;
    public String street;
    public String suite;
    public String city;
    public String zipcode;
    public String geo;
    public String lat;
    public String lng;
    public String phone;
    public String website;
    public String company;
    public String companyName;
    public String catchPhrase;
    public String bs;


    public User(String name, String id, String username, String email,
                String street, String suite, String city, String zipcode, String lat,
                String lng, String phone, String website, String companyName,
                String catchPhrase, String bs)
    {
        super(EntertainmentType.USER);
        this.email=email;
        this.id=id;
        this.name=name;
        this.username=username;
        this.street=street;
        this.suite=suite;
        this.city=city;
        this.zipcode=zipcode;
        this.lat=lat;
        this.lng=lng;
        this.phone=phone;
        this.website=website;
        this.companyName=companyName;
        this.catchPhrase=catchPhrase;
        this.bs=bs;

    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getGeo() {
        return geo;
    }

    public String getLat() {
        return lat;
    }

    public String getStreet() {
        return street;
    }

    public String getLng() {
        return lng;
    }

    public String getPhone() {
        return phone;
    }

    public String getSuite() {
        return suite;
    }

    public String getWebsite() {
        return website;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getCompany() {
        return company;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setGeo(String geo) {
        this.geo = geo;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
