package com.example.victoriafisher.foodapp;

/**
 * A class to hold data to build a Google Nearby Search Request
 * format: https://maps.googleapis.com/maps/api/place/nearbysearch/output?parameters
 * where output is json or xml - we are using json
 *  example: ...json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=YOUR_API_KEY
 */

public class Request {

    private String base; // the base of the URL we will build

    // required parameters

    private String key; // application's API key(web API key, not android)
    private String location; // latitude/longitude
    private String radius; // distance in meters. max = 50,000


    // optional parameters

    // A term to be matched against all content that Google
    // has indexed for this place -> restaurant genre
    private String keyword;

    private int minprice; // 0-4
    private int maxprice; // 0-4
    private String price;
    private String type; // this will always be 'restaurant'

    // constructor sets the base and type since those are always the same
    Request() {
        base = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
        location = "location=";
        radius = "&radius=";
        minprice = 0;
        maxprice = 0;
        price = "";
        type = "&type=restaurant";
        keyword = "&keyword=";
        key = "&key=AIzaSyAtyoanSHv7oH1-9JlpV5A7dVutqt8XLm0";

    }

    public void setLocation(double lat, double lon){
        location += String.valueOf(lat) + "," + String.valueOf(lon);
    }
    public void setRadius(int miles) {
        int meters = miles*1609;
        radius += String.valueOf(meters);
    }
    public void setPrice(int priceTier){
        minprice = priceTier;
        maxprice = priceTier;
        price = "&minprice=" + minprice + "&maxprice=" + maxprice;
    }
    public void setPrice(int minPriceTier, int maxPriceTier){
        minprice = minPriceTier;
        maxprice = maxPriceTier;
        price = "&minprice=" + minprice + "&maxprice=" + maxprice;
    }
    public void setKeyword(String genre) {
        keyword += genre;
    }
    // call this method after setting all of the values
    public String buildRequest(){
        String request = base + location + radius + price + type + keyword + key;
        return request;
    }



}
