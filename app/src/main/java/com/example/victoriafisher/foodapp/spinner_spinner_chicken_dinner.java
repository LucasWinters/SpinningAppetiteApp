package com.example.victoriafisher.foodapp;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * controls the spinner page. It takes the list of restaurant it gets back from the Google Json
 * and sets each text view to one of the options from the list. the text views are on timer to
 * allow them to run through the array list eventual picking one for the user
 */

public class spinner_spinner_chicken_dinner extends AppCompatActivity {

    public int count = 0;
    public int count1 = 1;
    public int count2 = 2;
    public int count3 = 3;
    public int count4 = 4;

    private Button spinBtn;
    private TextView option1;
    private TextView option2;
    private TextView option3;
    private TextView option4;
    private TextView option5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinner_spinner_chicken_dinner);

        final List<String> listStrings = new ArrayList<String>();

        if(getIntent().hasExtra("namesArray")) {
            String namesArray[] = getIntent().getExtras().getStringArray("namesArray");
            while (listStrings.size() < 5){
                for(int i = 0; i < namesArray.length; i++){
                    listStrings.add(namesArray[i]);
                }
            }
        }

        spinBtn = (Button) findViewById(R.id.spinBtn);
        option1 = (TextView) findViewById(R.id.option1);
        option2 = (TextView) findViewById(R.id.option2);
        option3 = (TextView) findViewById(R.id.option3);
        option4 = (TextView) findViewById(R.id.option4);
        option5 = (TextView) findViewById(R.id.option5);

        /**
        * each of these is a timer. One for every button to allow the function to act like
         * a price is right wheel. these each make sure that evey text view is displaying
         * a unique item from the array list.
         */

        spinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new CountDownTimer(3000, 200){
                    public void onTick(long millisUntilFinished){
                            option5.setText(listStrings.get(count4));

                            if (count4 == (listStrings.size() - 1)) {
                                count4 = 0;

                            } else {
                                count4++;
                            }
                    }
                    public void onFinish(){
                    }
                }.start();

                new CountDownTimer(3000, 200){
                    public void onTick(long millisUntilFinished){
                        option1.setText(listStrings.get(count));
                        if (count == (listStrings.size() - 1)) {
                            count = 0;
                        } else {
                            count++;
                        }
                    }
                    public void onFinish(){
                    }
                }.start();

                new CountDownTimer(3000, 200){
                    public void onTick(long millisUntilFinished){
                        option2.setText(listStrings.get(count1));
                        if (count1 == (listStrings.size() - 1)) {
                            count1 = 0;
                        } else {
                            count1++;
                        }
                    }
                    public void onFinish(){
                    }
                }.start();

                new CountDownTimer(3000, 200){

                    public void onTick(long millisUntilFinished){
                        option3.setText(listStrings.get(count2));
                        if (count2 == (listStrings.size() - 1)) {
                            count2 = 0;
                        } else {
                            count2++;
                        }
                    }
                    public void onFinish(){
                    }
                }.start();

                new CountDownTimer(3000, 200){
                    public void onTick(long millisUntilFinished){
                        option4.setText(listStrings.get(count3));
                        if (count3 == (listStrings.size() - 1)) {
                            count3 = 0;
                        } else {
                            count3++;
                        }
                    }
                    public void onFinish(){
                    }
                }.start();
            }
        });
    }


}

