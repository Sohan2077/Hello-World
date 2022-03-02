package com.example.uncover;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.SparseArray;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {


    Button btOpenCam;
    FloatingActionButton fmain,fcam,fexternnal;
    float translationYaxis = 100f;
    Boolean fabOpen = false;
    OvershootInterpolator interpolator = new OvershootInterpolator();

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{
                            Manifest.permission.CAMERA
                    },
                    100);
        }


        //
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toogle);
        toogle.syncState();





        ShowFabMenu();
    }

    private void ShowFabMenu() {
        fmain = findViewById(R.id.fab_main);
        fcam = findViewById(R.id.fab_camera);
        fexternnal = findViewById(R.id.fab_gallery);

        fcam.setAlpha(0f);
        fexternnal.setAlpha(0f);


        fcam.setTranslationY(translationYaxis);
        fexternnal.setTranslationY(translationYaxis);

        fmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fabOpen){
                    CloseMenu();
                }
                else{
                    OpenMenu();
                }
            }
        });

        fcam.setOnClickListener(v -> {
            Intent intenti =  new Intent(MainActivity.this,CaptureActivity.class);
            intenti.putExtra("isCam","camera");
            startActivity(intenti);
        });

        fexternnal.setOnClickListener(v -> {
            Intent intent =  new Intent(MainActivity.this,CaptureActivity.class);
            intent.putExtra("isCam","gallery");
            startActivity(intent);
        });
    }

    private void OpenMenu() {


        fabOpen = ! fabOpen;
        fmain.setImageResource(R.drawable.ic_baseline_clear_24);
        fcam.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
        fexternnal.animate().translationY(0f).alpha(1f).setInterpolator(interpolator).setDuration(300).start();
    }

    private void CloseMenu() {

        fabOpen = ! fabOpen;
        fmain.setImageResource(R.drawable.ic_baseline_add_24);
        fcam.animate().translationY(translationYaxis).alpha(0f).setInterpolator(interpolator).setDuration(300).start();
        fexternnal.animate().translationY(translationYaxis).alpha(0f).setInterpolator(interpolator).setDuration(300).start();

    }


    @Override
    public void onBackPressed()
    {
        if(drawerLayout.isDrawerOpen((GravityCompat.START)))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

}
