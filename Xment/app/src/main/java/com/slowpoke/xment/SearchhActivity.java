package com.slowpoke.xment;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewTreeObserver;

public class SearchhActivity extends AppCompatActivity {


    private View searchLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.do_not_move, R.anim.do_not_move);
        setContentView(R.layout.activity_searchh);

        searchLayout = findViewById(R.id.search_layout);

        if(savedInstanceState == null){
            searchLayout.setVisibility(View.INVISIBLE);

            final ViewTreeObserver viewTreeObserver = searchLayout.getViewTreeObserver();

            if(viewTreeObserver.isAlive()){
                viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        circularRevealActivity();
                        searchLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });
            }

        }
    }

    private void circularRevealActivity(){
        int cx = searchLayout.getRight();
        int cy = searchLayout.getTop();

        float finalRadius = Math.max(searchLayout.getWidth(), searchLayout.getHeight());

        Animator circularReveal = ViewAnimationUtils.createCircularReveal(
                searchLayout,
                cx,
                cy,
                0,
                finalRadius
        );


        circularReveal.setDuration(500);
        searchLayout.setVisibility(View.VISIBLE);
        circularReveal.start();
    }

    //for adjusting the starting point of animation(in case)
    private int getDips(int dps){
        Resources resources = getResources();
        return (int) TypedValue.applyDimension(
               TypedValue.COMPLEX_UNIT_DIP,
               dps,
               resources.getDisplayMetrics()
        );
    }

    @Override
    public void onBackPressed(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            int cx = searchLayout.getWidth();
            int cy = 0;
            float finalRadius = Math.max(searchLayout.getWidth(),searchLayout.getHeight());
            Animator circularReveal = ViewAnimationUtils.createCircularReveal(searchLayout, cx, cy, finalRadius, 0);

            circularReveal.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    searchLayout.setVisibility(View.INVISIBLE);
                    finish();
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
            circularReveal.setDuration(300);
            circularReveal.start();

        }
        else {
            super.onBackPressed();
        }

    }
}