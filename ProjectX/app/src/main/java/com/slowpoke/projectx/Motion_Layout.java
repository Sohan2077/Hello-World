package com.slowpoke.projectx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Toast;

import com.facebook.shimmer.Shimmer;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Motion_Layout extends AppCompatActivity {

    private ArrayList<Formula_Text> formulaList;
    private RecyclerView recyclerView;
    private MyAdapter.RecyclerViewOnClick listener;

    //private final Context context;
    ConstraintLayout backBut;


    //shimmer layout
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motion__layout);

        recyclerView = findViewById(R.id.recycyler_view);
        formulaList = new ArrayList<>();

        setFormulaInfo();
        setAdapter();

        backBut = findViewById(R.id.back_button);
        backBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        shimmerFrameLayout = findViewById(R.id.shimmer);
        shimmerFrameLayout.startShimmer();

        Load();
    }

    private void setAdapter()
    {
        setOnClickListner();
        MyAdapter adapter = new MyAdapter(formulaList, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListner()
    {
        listener = new MyAdapter.RecyclerViewOnClick() {

            @Override
            public void onClick(View v, int position) {

                final Intent intent;
                switch(position)
                {
                    case 0:
                        startActivity(new Intent(Motion_Layout.this, Average_Speed.class));
                        break;

                    case 1:
                        startActivity(new Intent(Motion_Layout.this, Displacement.class));
                        break;
                }

            }

        };


    }

    private void setFormulaInfo()
    {
        formulaList.add(new Formula_Text("Find Average Speed?"));
        formulaList.add(new Formula_Text("Find Displacement?"));
        formulaList.add(new Formula_Text("Newtons's First Law"));
        formulaList.add(new Formula_Text("Newtons's Second Law"));

    }

    private void Load()
    {
        new CountDownTimer(1000,1000)
        {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish()
            {
                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerView.setVisibility(View.VISIBLE);
            }
        }.start();
    }
}