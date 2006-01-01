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
                AverageSpeed();
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

        //inputs visibility gone
        InputsGone();

        //edittexts
        value_of_inputField_1 = findViewById(R.id.input_field_1);
        value_of_inputField_2 = findViewById(R.id.input_field_2);
        value_of_inputField_3 = findViewById(R.id.input_field_3);
    }



    //expand
    LinearLayout expandBtn;

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
                    input_layout_1.setVisibility(View.VISIBLE);
                }

                else
                {
                    textClr_1.setTextColor(getResources().getColor(R.color.white_text));
                    colorBtn_1.setBackgroundColor(getResources().getColor(R.color.bold_color));
                    isClickled_1 = false;
                    slot1 = false;
                    whichSlot = 0;
                    value_of_inputField_1.setText("");
                    input_layout_1.setVisibility(View.GONE);
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
                    input_layout_2.setVisibility(View.VISIBLE);
                }

                else
                {
                    textClr_2.setTextColor(getResources().getColor(R.color.white_text));
                    colorBtn_2.setBackgroundColor(getResources().getColor(R.color.bold_color));
                    isClickled_2 = false;
                    slot2 = false;
                    whichSlot = 0;
                    value_of_inputField_2.setText("");
                    input_layout_2.setVisibility(View.GONE);
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
                    input_layout_3.setVisibility(View.VISIBLE);
                }

                else
                {
                    textClr_3.setTextColor(getResources().getColor(R.color.white_text));
                    colorBtn_3.setBackgroundColor(getResources().getColor(R.color.bold_color));
                    isClickled_3 = false;
                    whichSlot = 0;
                    slot3 = false;
                    value_of_inputField_3.setText("");
                    input_layout_3.setVisibility(View.GONE);

                }

            }
        });

    }


    //this number specifies which slot is empty
    int whichSlot;

    EditText value_of_inputField_1, value_of_inputField_2 , value_of_inputField_3;
    //for showing result
    public String word1,word2 ,word3;

    //value which have been converted to string now to float
    public float val1,val2,val3;


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

        word1 = value_of_inputField_1.getText().toString();
        word2 = value_of_inputField_2.getText().toString();
        word3 = value_of_inputField_3.getText().toString();

        val1 = Float.parseFloat( 0 + word1);
        val2 = Float.parseFloat( 0 + word2);
        val3 = Float.parseFloat( 0 + word3);

    }



    TextView resultShow, indicate;
    ConstraintLayout resultLayout;
    public void AverageSpeed(){

        CheckEmpty();
        float speed,distance,time;

        resultShow = findViewById(R.id.result_show_text);
        indicate = findViewById(R.id.indication);
        resultLayout = findViewById(R.id.result_layout);

        resultLayout.setVisibility(View.VISIBLE);

        speed = val1;
        distance = val2;
        time = val3;

        if(whichSlot == 1)
        {
            speed = distance/time;
            resultShow.setText(String.valueOf(speed) + "/Hr");
            indicate.setText("Speed ~~");
        }
        else if(whichSlot == 2)
        {
            distance = speed * time;
            resultShow.setText(String.valueOf(distance) + "km");
            indicate.setText("Distance ~~");
        }
        else if(whichSlot == 3)
        {
            time = distance / speed;
            resultShow.setText(String.valueOf(time) +"Hr");
            indicate.setText("Time ~~");
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

    CardView input_layout_1 ,input_layout_2 ,input_layout_3;
    public void InputsGone()
    {
        input_layout_1 = findViewById(R.id.input_1);
        input_layout_2 = findViewById(R.id.input_2);
        input_layout_3 = findViewById(R.id.input_3);
    }
}