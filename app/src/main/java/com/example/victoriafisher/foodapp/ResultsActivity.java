package com.example.victoriafisher.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * reads the information form the Json and stores is nicely into a list for views to see
 */
public class ResultsActivity extends AppCompatActivity {

    String namesArray[];

    /**
     * makes the list view, makes sure everything has a place to go one retrieved
     * Sets up the buttons so they are ready to be clicked
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        ScrollView sv = new ScrollView(this);
        sv.setPadding(5, 5, 5, 5);
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setPadding(5, 5, 5, 5);
        ll.setDividerPadding(5);
        sv.addView(ll); // sv can only have one view, but we can add multiple views to ll

        TextView tv = new TextView(this);

        String response = "";

        if (getIntent().hasExtra("Response")) {
            response = getIntent().getExtras().getString("Response");
            Log.d("receivedIntent", "Received intent with " + response);
        }
        else {
            tv.setText("erp...");
        }

        ll.addView(tv);

        if (response != ""){
            final ArrayList<Result> results = createResultFromJSON(response);

            for(int i = 0; i < results.size(); i++) {
                LinearLayout llh1 = new LinearLayout(this);
                llh1.setOrientation(LinearLayout.HORIZONTAL);

                ImageView icon = new ImageView(this); // TODO
                ImageDownloader imageDownloader = new ImageDownloader(icon);
                imageDownloader.execute(results.get(i).icon);
                
//                ImageView image = new ImageView(this); // TODO
//                ImageDownloader imageDownloader = new ImageDownloader(image);
//                imageDownloader.execute(results.get(i).image);

                TextView name = new TextView(this);
                TextView price_level = new TextView(this);
                TextView rating = new TextView(this);
                TextView vicinity = new TextView(this);
                TextView space = new TextView(this);

                //icon.setImageDrawable(LoadImageFromWebOperations(results.get(i).icon));
                name.setText("Name: " + results.get(i).name);
                name.setTextSize(18);

                price_level.setText("Price Level: " + results.get(i).price_level);
                price_level.setTextSize(14);
                rating.setText("Rating: " + String.valueOf(results.get(i).rating));
                rating.setTextSize(14);
                vicinity.setText("Address: " + results.get(i).vicinity);
                vicinity.setTextSize(14);
                space.setText("     ");

                llh1.addView(icon);
                llh1.addView(name);
                ll.addView(llh1);
                ll.addView(price_level);
                ll.addView(rating);
                ll.addView(vicinity);
                ll.addView(space);

            }
            Button helpMe = new Button(this);
            helpMe.setText("Help Me Choose!");
            helpMe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    namesArray = new String[results.size()];
                    for(int i = 0; i < results.size(); i++) {
                        namesArray[i] = results.get(i).name;
                    }
                    Intent findIntent = new Intent(ResultsActivity.this, spinner_spinner_chicken_dinner.class);
                    findIntent.putExtra("namesArray", namesArray);
                    startActivity(findIntent);
                }
            });
            ll.addView(helpMe);
            this.setContentView(sv);
        }
    } // onCreate()


    /**
     * This is where the Json is read and the information gets stored
     * into the list view that was set-up above.
     * @param text
     * @return
     */
    public ArrayList<Result> createResultFromJSON(String text) {
        ArrayList<Result> results = new ArrayList<>();
        String json = text;
        try {
            JSONObject obj = new JSONObject(json);
            JSONArray m_jArry = obj.getJSONArray("results");

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                //JSONArray photosArray = m_jArry.getJSONObject(i).getJSONArray("photos");
                //JSONObject pho_inside = photosArray.getJSONObject(1);
                Result r = new Result();

                r.icon = jo_inside.getString("icon");
                //r.image = pho_inside.getString("html_attributions");

                r.id = jo_inside.getString("id");
                r.name = jo_inside.getString("name");
                r.place_id = jo_inside.getString("place_id");
                r.price_level = jo_inside.getInt("price_level");
                r.rating = jo_inside.getDouble("rating");
                r.vicinity = jo_inside.getString("vicinity");

                //Add your values in your `ArrayList` as below:
                results.add(r);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return results;
    }

}
