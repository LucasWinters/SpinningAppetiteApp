package com.example.victoriafisher.foodapp;

import android.util.Log;

/**
 * Created by victoriafisher on 2/28/18.
 * When the user puts information in by clicking buttons
 * it gets stored into the UserInput class.
 */

public class UserInput {

    private int maxPrice;
    private int maxDistance;
    public boolean mexican;
    public boolean indian;
    public boolean asian;
    public boolean american;
    public boolean greek;
    public boolean italian;
    public boolean mediterranean;
    private String[] genre;



    /**
     * Created by victoriafisher on 2/28/18.
     * Default constructor for the UserInput, it sets everything to Null
     * so its ready for the users input
     */

   public UserInput(){
        maxPrice = 0;
        maxDistance = 10;
        mediterranean = false;
        mexican = false;
        indian = false;
        asian = false;
        american = false;
        greek = false;
        italian = false;
        genre = new String[]{"","","","","","",""};;

    }

    public int getMaxPrice(){return maxPrice;}

    /**
     * just sets the max price if its in the range allowed
     * @param maxPrice
     */

    public void setMaxPrice(int maxPrice){

        if (maxPrice > 0 && maxPrice <= 5) {
            this.maxPrice = maxPrice;
        }
        else {
            this.maxPrice = 0;
        }
    }



    public int getMaxDistance(){
        return maxDistance;
    }

    /**
     * sets the maxDistance if in allowed range
     * @param maxDistance
     */

    public void setMaxDistance(int maxDistance){

        if(maxDistance > 0 && maxDistance <= 30) {
            this.maxDistance = maxDistance;
        }
        else {
            this.maxDistance = 10;
        }
    }



    public String[] getGenre() {
        return genre;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }


    /**
     * adds what genre you picked to an array so it can send it to the API request later.
     * @param s
     */
    public void addGenre(String s){
        for (int i = 0; i < 7; i++){
            if (genre[i] == ""){
                genre[i] = s;
                //Log.d("UserInput", "add to Genre has been called");
                return;
            }
        }

    }

}


