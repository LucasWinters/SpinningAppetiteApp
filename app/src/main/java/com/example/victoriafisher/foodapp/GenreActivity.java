package com.example.victoriafisher.foodapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static com.example.victoriafisher.foodapp.MainActivity.userInput;

/**
 * allows the users to pick a genre of food, then collects information form previous page
 * to add it all to the API request, then sends the request and get data back.
 */

public class GenreActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{

    private FusedLocationProviderClient mFusedLocationClient;
    private Request grequest;
    private RequestQueue requestQueue;

    private String ENDPOINT = "";
    private String rawResult;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre_activity);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        grequest = new Request();
        requestQueue = Volley.newRequestQueue(getApplicationContext());

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            grequest.setLocation(location.getLatitude(), location.getLongitude());
                        }
                        else {
                            grequest.setLocation(37.5407246, -77.4360481);
                        }
                    }
                });

    }

    boolean selectedMex = false;
    boolean selectedAsi = false;
    boolean selectedAmer = false;
    boolean selectedIta = false;
    boolean selectedInd = false;
    boolean selectedGre = false;
    boolean selectedMed = false;

// these will set the user input to true
    public void mexicanOnClick(View view){
        final Button mexicanBtn = (Button) findViewById(R.id.mexicanBtn);

                if (selectedMex == false){
                    selectedMex = true;
                    userInput.mexican = true;
                    mexicanBtn.setBackgroundResource(R.drawable.tacosselected);
                }
                else{
                    selectedMex = false;
                    userInput.mexican = false;
                    mexicanBtn.setBackgroundResource(R.drawable.tacos);
                }


    }

    public void asianOnClick(View view){
        final Button asianBtn = (Button) findViewById(R.id.asianBtn);

        if (selectedAsi == false){
            selectedAsi = true;
            userInput.asian = true;
            asianBtn.setBackgroundResource(R.drawable.asianselected);
        }
        else{
            selectedAsi = false;
            userInput.asian = false;
            asianBtn.setBackgroundResource(R.drawable.asian);
        }

    }

    public void americanOnClick(View view){
        final Button americanBtn = (Button) findViewById(R.id.americanBtn);

                if (selectedAmer == false){
                    selectedAmer = true;
                    userInput.american = true;
                    americanBtn.setBackgroundResource(R.drawable.americanselected);
                }
                else{
                    selectedAmer = false;
                    userInput.american = false;
                    americanBtn.setBackgroundResource(R.drawable.american);
                }

    }

    public void italianOnClick(View view){
        final Button italianBtn = (Button) findViewById(R.id.italianBtn);

            if (selectedIta == false){
                selectedIta = true;
                userInput.italian = true;
                italianBtn.setBackgroundResource(R.drawable.italianselected);
            }
            else{
                selectedIta = false;
                userInput.italian = false;
                italianBtn.setBackgroundResource(R.drawable.italian);
            }

        }

    public void indianOnClick(View view){
        final Button indianBtn = (Button) findViewById(R.id.indianBtn);

                if (selectedInd == false){
                    selectedInd = true;
                    userInput.indian = true;
                    indianBtn.setBackgroundResource(R.drawable.indianselected);
                }
                else{
                    selectedInd = false;
                    userInput.indian = false;
                    indianBtn.setBackgroundResource(R.drawable.indian);
                }

    }

    public void greekOnClick(View view){
        final Button greekBtn = (Button) findViewById(R.id.greekBtn);

                if (selectedGre == false){
                    selectedGre = true;
                    userInput.greek = true;
                    greekBtn.setBackgroundResource(R.drawable.greekselected);
                }
                else{
                    selectedGre = false;
                    userInput.greek = false;
                    greekBtn.setBackgroundResource(R.drawable.greek);
                }

    }

    public void mediterraneanOnClick(View view){
        final Button mediterraneanBtn = (Button) findViewById(R.id.mediterraneanBtn);

                if (selectedMed == false){
                    selectedMed = true;
                    userInput.mediterranean = true;
                    mediterraneanBtn.setBackgroundResource(R.drawable.mediterraneanselected);
                }
                else{
                    selectedMed = false;
                    userInput.mediterranean = false;
                    mediterraneanBtn.setBackgroundResource(R.drawable.mediterranean);
                }

    }

    private void fetchData(){
        StringRequest request = new StringRequest(StringRequest.Method.GET, ENDPOINT, onLoaded, onError);
        requestQueue.add(request);
    }

    private final Response.Listener<String> onLoaded = new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            Log.i("Response", response);
            rawResult = response;
            Intent startIntent = new Intent(getApplicationContext(), ResultsActivity.class);
            startIntent.putExtra("Response", rawResult);
            startActivity(startIntent);
        }
    };

    private final Response.ErrorListener onError = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Log.e("Response", error.toString());
        }
    };


    /**
     * determines what button what pressed above and grabs all the information to send
     * to googles API and class the fetchData function which sends the request for the information.
     * @param view
     */
    public void findRestaurant(View view){

        if (userInput.mexican == true){
            userInput.addGenre("Mexican");
            Log.d("GenreActivity", "Mexican was passed");
        }

        if (userInput.asian == true){
            userInput.addGenre("Asian");
            Log.d("GenreActivity", "Asian was passed");
        }

        if (userInput.american == true){
            userInput.addGenre("American");
            Log.d("GenreActivity", "American was passed");
        }

        if (userInput.italian == true){
            userInput.addGenre("Italian");
            Log.d("GenreActivity", "Italian was passed");
        }

        if (userInput.indian == true){
            userInput.addGenre("Indian");
            Log.d("GenreActivity", "Indian was passed");
        }

        if (userInput.greek == true){
            userInput.addGenre("Greek");
            Log.d("GenreActivity", "Greek was passed");
        }

        if (userInput.mediterranean == true){
            userInput.addGenre("Mediterranean");
            Log.d("GenreActivity", "Mediterranean was passed");
        }

        grequest.setRadius(userInput.getMaxDistance());
        grequest.setPrice(0, userInput.getMaxPrice());
        grequest.setKeyword("");
        grequest.setKeyword(userInput.getGenre()[0]);

        ENDPOINT = grequest.buildRequest();
        Log.i("Request", ENDPOINT);
        fetchData();

    }

}
