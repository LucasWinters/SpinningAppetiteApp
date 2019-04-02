package com.example.victoriafisher.foodapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


/**
 * sets the main screen for user.
 * pulls the star xml and allows user in input information
 */
public class MainActivity extends AppCompatActivity {
    private static SeekBar price_bar;
    private static TextView price_view;

    private static SeekBar distance_Bar;
    private static TextView distance_View;

    public static UserInput userInput = new UserInput();
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        setDistance_Bar();
        setPrice_Bar();
    }


    /**
     * sets what ever the user moves the price bar too. It displays the number they are on
     * screen and sets the textview.
     */
    public void setPrice_Bar() {
        price_bar = findViewById(R.id.priceBar);
        price_bar.setMax(4);
        price_view = findViewById(R.id.priceID);

        price_bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int progress_number;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        progress_number = i;
                        if (price_bar.getProgress() == 0){
                            price_view.setText("$");
                            return;
                        }
                        if (price_bar.getProgress() == 1){
                            price_view.setText("$$");
                            return;
                        }
                        if (price_bar.getProgress() == 2){
                            price_view.setText("$$$");
                            return;
                        }
                        if (price_bar.getProgress() == 3){
                            price_view.setText("$$$$");
                            return;
                        }
                        if (price_bar.getProgress() == 4){
                            price_view.setText("$$$$$");
                            return;
                        }


                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );
        Log.i("setPriceBar()", "You just updated the price bar");
    }


    /**
     * sets what ever the user moves the distance bar too. It displays the number they are on
     * screen and sets the textview.
     */
    public void setDistance_Bar() {

        distance_Bar = findViewById(R.id.distanceBar);
        distance_View = findViewById(R.id.distanceID);
        distance_Bar.setMax(30);

        distance_Bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    int distanceProgressValue;

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                        distanceProgressValue = i;
                        distance_View.setText(distance_Bar.getProgress() + "mi");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                }
        );
        Log.i("setDistanceBar()", "You just updated the distance bar");

    }

    /**
     * sets the users price and distance
     * activates the location feature, grabbing there latitude and longitude
     * requests permission if location not on.
     * and sends you to the next xml document
     * @param view
     */
    public void findOnClick(View view){
        // TODO: Start using the Places API.
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION))
            {
                Toast.makeText(this, "This app requires location permission.", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
            else {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }

            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Button goBtn = findViewById(R.id.goBtn);

        userInput.setMaxPrice(price_bar.getProgress());
        userInput.setMaxDistance(distance_Bar.getProgress());

        Intent goIntent = new Intent(MainActivity.this, GenreActivity.class);
        startActivity(goIntent);



    }

    /**
     * ask's the user has for permission to use current location.
     * informs the user if location is on to let them know if the app will work
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Location Granted, This App will function correctly", Toast.LENGTH_LONG).show();
                // permission was granted, yay! Do the
                // location-related task you need to do.

            } else {
                Toast.makeText(this, "Location Not Granted, This App will not function correctly", Toast.LENGTH_LONG).show();
                // permission denied, boo! Disable the
                // functionality that depends on this permission.
            }
        }
        else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
