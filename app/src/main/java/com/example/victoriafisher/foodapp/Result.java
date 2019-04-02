package com.example.victoriafisher.foodapp;

/**
 * store the data form the Json retrieved by the API
 * this includes the picture, name, price, rating and address.
 */
public class Result {
    String icon; // icon URL
    String id;
    String name; // name of restaurant
    String place_id; // id and place_id are two different variables
    int price_level; // 0-4
    double rating; // 0.0 - 5.0
    String vicinity; // address
    String image;
}
