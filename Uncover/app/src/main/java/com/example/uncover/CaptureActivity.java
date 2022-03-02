package com.example.uncover;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class CaptureActivity extends AppCompatActivity {

    ImageView image = null;
    public static final int CAMERA_REQUEST = 1, EXTR_REQUEST = 10;

    String isOpen;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        if(getSupportActionBar() != null)
        {
            getSupportActionBar().hide();
        }


        image = findViewById(R.id.action_image);

        //to know which one is opened camera or gallery
        isOpen = getIntent().getStringExtra("isCam");

        if(isOpen.contains("camera")){
            try {
                TakePicture();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        else if(isOpen.contains("gallery"))
        {
            getImage();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST && resultCode == RESULT_OK)
        {
            Bitmap captureImg = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(captureImg);
        }
        else if(requestCode == 10 && resultCode == RESULT_OK){

            try{
                Uri uri = data.getData();
                image.setImageURI(uri);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else finish(); overridePendingTransition(0,0);
    }

    private void TakePicture()
    {
        Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(takePicIntent, CAMERA_REQUEST);
    }

    private void getImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Tittle"),EXTR_REQUEST);
    }


}