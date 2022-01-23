package com.slowpoke.projectx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.slowpoke.projectx.R;


import java.sql.Array;
import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        //result button
        Button resultButton = findViewById(R.id.result_button);
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InstantiateAlgorithm();
                AverageSpeed();
            }
        });
    }



    public boolean slot1,slot2,slot3;
    public float val1,val2,val3;
    public String word1,word2,word3 = "0";

    public ArrayList<EditText> averageSpeed = new ArrayList<EditText>();

    public void InstantiateAlgorithm()
    {

        //finding edittext
        averageSpeed.add(this.<EditText>findViewById(R.id.speed));
        averageSpeed.add(this.<EditText>findViewById(R.id.distance));
        averageSpeed.add(this.<EditText>findViewById(R.id.time));

        //values from edittext
        word1 =  averageSpeed.get(0).getText().toString();
        word2 =  averageSpeed.get(1).getText().toString();
        word3 =  averageSpeed.get(2).getText().toString();

        val1 = Float.parseFloat(0 + word1);
        val2 = Float.parseFloat(0 + word2);
        val3 = Float.parseFloat(0 + word3);
    }


    public void CheckEmpty()
    {

        if(!averageSpeed.get(0).getText().toString().equals("") && !averageSpeed.get(1).getText().toString().equals("") && !averageSpeed.get(2).getText().toString().equals(""))
        {
            Toast.makeText(getApplicationContext(), "What are you looking for?", Toast.LENGTH_SHORT).show();
        }

        else
        {
            if(averageSpeed.get(0).getText().toString().equals("") && !averageSpeed.get(1).getText().toString().equals("") && !averageSpeed.get(2).getText().toString().equals(""))
            {
                slot1 = true;
            }

            else if(averageSpeed.get(1).getText().toString().equals("") && !averageSpeed.get(0).getText().toString().equals("") && !averageSpeed.get(2).getText().toString().equals(""))
            {
                slot2 = true;
            }

            else if(averageSpeed.get(2).getText().toString().equals("") && !averageSpeed.get(0).getText().toString().equals("") && !averageSpeed.get(1).getText().toString().equals(""))
            {
                slot3 = true;
            }

            else
            {
                Toast.makeText(getApplicationContext(), "Please input the desired values", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void AverageSpeed(){

        CheckEmpty();
        float speed,distance,time;

        speed = val1;
        distance = val2;
        time = val3;

        if(slot1)
        {
            speed = distance/time;
            Toast.makeText(getApplicationContext(), "Speed: " + speed + "/h", Toast.LENGTH_SHORT).show();
        }
        else if(slot2)
        {
            distance = speed * time;
            Toast.makeText(getApplicationContext(), "Distance: " + distance + "km", Toast.LENGTH_SHORT).show();
        }
        else if(slot3)
        {
            time = distance / speed;
            Toast.makeText(getApplicationContext(), "Time: " + time + "h", Toast.LENGTH_SHORT).show();
        }
        slot1 = false; slot2 = false; slot3 = false;


        
    }
}