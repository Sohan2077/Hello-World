package com.slowpoke.projectx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.slowpoke.projectx.R;


import java.sql.Array;
import java.util.ArrayList;

public class Average_Speed extends AppCompatActivity {


    FloatingActionButton fabBut;

    //back button
    ConstraintLayout backBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_average__speed);

        //expand();
        InstantiateAlgorithm();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                CheckEmpty();
                handler.postDelayed(this,100);
            }
        }, 100);


        fabBut = findViewById(R.id.fab);
        fabBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //result
            }
        });

        //back button
        backBut = findViewById(R.id.back_button);
        backBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    //for showing result
    public float val1,val2,val3;
    public String word1,word2,word3 = "0";


    //expand
    LinearLayout expandBtn;

    public ArrayList<EditText> averageSpeed = new ArrayList<EditText>();


    //variables
    boolean slot1,slot2,slot3 = false;
    LinearLayout button1,button2,button3;
    LinearLayout colorBtn_1,colorBtn_2,colorBtn_3;
    TextView textClr_1,textClr_2,textClr_3;
    boolean isClickled_1,  isClickled_2,  isClickled_3 = false;

    public void InstantiateAlgorithm()
    {

        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);

        //changing color of buttons
        colorBtn_1 = findViewById(R.id.color_change_btn1);
        colorBtn_2 = findViewById(R.id.color_change_btn2);
        colorBtn_3 = findViewById(R.id.color_change_btn3);

        //text change colors
        textClr_1 = findViewById(R.id.text_color_1);
        textClr_2 = findViewById(R.id.text_color_2);
        textClr_3 = findViewById(R.id.text_color_3);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(whichSlot == 1)
                {
                    Toast.makeText(getApplicationContext(), "deselect", Toast.LENGTH_SHORT).show();
                }

                else if(!isClickled_1)
                {
                    textClr_1.setTextColor(getResources().getColor(R.color.bold_color));
                    colorBtn_1.setBackgroundColor(getResources().getColor(R.color.light_color));

                    isClickled_1 = true;
                    slot1 = true;
                }

                else
                {
                    textClr_1.setTextColor(getResources().getColor(R.color.white_text));
                    colorBtn_1.setBackgroundColor(getResources().getColor(R.color.bold_color));
                    isClickled_1 = false;
                    slot1 = false;
                    whichSlot = 0;
                }


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(whichSlot == 2)
                {
                    Toast.makeText(getApplicationContext(), "deselect", Toast.LENGTH_SHORT).show();
                }

                else if(!isClickled_2)
                {
                    textClr_2.setTextColor(getResources().getColor(R.color.bold_color));
                    colorBtn_2.setBackgroundColor(getResources().getColor(R.color.light_color));
                    slot2 = true;
                    isClickled_2 = true;
                }

                else
                {
                    textClr_2.setTextColor(getResources().getColor(R.color.white_text));
                    colorBtn_2.setBackgroundColor(getResources().getColor(R.color.bold_color));
                    isClickled_2 = false;
                    slot2 = false;
                    whichSlot = 0;
                }

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(whichSlot == 3)
                {
                    Toast.makeText(getApplicationContext(), "deselect", Toast.LENGTH_SHORT).show();
                }

                else if(!isClickled_3)
                {
                    slot3 = true;
                    textClr_3.setTextColor(getResources().getColor(R.color.bold_color));
                    colorBtn_3.setBackgroundColor(getResources().getColor(R.color.light_color));
                    isClickled_3 = true;
                }

                else
                {
                    textClr_3.setTextColor(getResources().getColor(R.color.white_text));
                    colorBtn_3.setBackgroundColor(getResources().getColor(R.color.bold_color));
                    isClickled_3 = false;
                    whichSlot = 0;
                    slot3 = false;
                }

            }
        });

    }


    //this number specifies which slot is empty
    int whichSlot;

    public void CheckEmpty()
    {
        if (slot1 && slot2)
        {
            whichSlot = 3;
        }
        else if(slot2 && slot3)
        {
            whichSlot = 1;
        }
        else if(slot3 && slot1)
        {
            whichSlot = 2;
        }
    }

    public void AverageSpeed(){

        CheckEmpty();
        float speed,distance,time;

        speed = val1;
        distance = val2;
        time = val3;

        if(whichSlot == 1)
        {
            speed = distance/time;
            Toast.makeText(getApplicationContext(), "Speed: " + speed + "/h", Toast.LENGTH_SHORT).show();
        }
        else if(whichSlot == 2)
        {
            distance = speed * time;
            Toast.makeText(getApplicationContext(), "Distance: " + distance + "km", Toast.LENGTH_SHORT).show();
        }
        else if(whichSlot == 3)
        {
            time = distance / speed;
            Toast.makeText(getApplicationContext(), "Time: " + time + "h", Toast.LENGTH_SHORT).show();
        }

    }

    //not in use
    TextView expandLayout;
    public void expand()
    {
        expandLayout = findViewById(R.id.details_out);

        expandBtn = findViewById(R.id.expandBtnn);

        expandBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(expandLayout.getVisibility() == View.GONE)
                {
                    TransitionManager.beginDelayedTransition(expandBtn, new AutoTransition());
                    expandLayout.setVisibility(View.VISIBLE);
                }
                else
                {
                    TransitionManager.beginDelayedTransition(expandBtn, new AutoTransition());
                    expandLayout.setVisibility(View.GONE);
                }
            }
        });
    }
}