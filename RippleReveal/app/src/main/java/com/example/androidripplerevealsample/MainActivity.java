package com.example.androidripplerevealsample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView imgBackground;
    Button btnReveal, btnHide, btnTopLeft, btnTopRight, btnBottomLeft, btnBottomRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setListeners();
    }

    private void initViews(){
        imgBackground = (ImageView) findViewById(R.id.imgBackground);
        btnReveal= (Button) findViewById(R.id.btnReveal);
        btnHide= (Button) findViewById(R.id.btnHide);
        btnTopLeft= (Button) findViewById(R.id.btnTopLeft);
        btnTopRight= (Button) findViewById(R.id.btnTopRight);
        btnBottomLeft= (Button) findViewById(R.id.btnBottomLeft);
        btnBottomRight= (Button) findViewById(R.id.btnBottomRight);

    }

    private void setListeners(){
        btnReveal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the center for the clipping circle
                // get the final radius for the clipping circle
                int cx = imgBackground.getWidth()/ 2;
                int cy =  imgBackground.getHeight()/ 2;
                float finalRadius = (float) Math.hypot(cx, cy);

                setRevealAnimation(cx, cy, finalRadius);
            }
        });

        btnTopLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the top left corner
                int cx = 0;
                int cy = 0;
                setRevealAnimation(cx, cy, calculateRevealRadius());
            }
        });
        btnTopRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the top right corner
                int cx = imgBackground.getWidth();
                int cy = 0;
                setRevealAnimation(cx, cy, calculateRevealRadius());
            }
        });

        btnBottomLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the bottom left corner
                int cx = 0;
                int cy = imgBackground.getHeight();
                setRevealAnimation(cx, cy, calculateRevealRadius());
            }
        });

        btnBottomRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // get the bottom right corner
                int cx = imgBackground.getWidth();
                int cy = imgBackground.getHeight();
                setRevealAnimation(cx, cy, calculateRevealRadius());
            }
        });

        btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if we're running on Android 5.0 or higher
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    // Call some material design APIs here
                    // get the center for the clipping circle
                    int cx = imgBackground.getWidth() / 2;
                    int cy = imgBackground.getHeight() / 2;

                    // get the initial radius for the clipping circle
                    float initialRadius = (float) Math.hypot(cx, cy);

                    // create the animation (the final radius is zero)
                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(imgBackground, cx, cy, initialRadius, 0);
                    anim.setDuration(500);//500 ms
                    // make the view invisible when the animation is done
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            imgBackground.setVisibility(View.INVISIBLE);
                        }
                    });

                    // start the animation
                    anim.start();
                } else {
                    // Implement this feature without material design
                    imgBackground.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    private void  setRevealAnimation(int cx, int cy,  float finalRadius){
        // Check if we're running on Android 5.0 or higher
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Call some material design APIs here


            // create the animator for this view (the start radius is zero)
            Animator anim =
                    ViewAnimationUtils.createCircularReveal(imgBackground, cx, cy, 0, finalRadius);
            anim.setDuration(500);
            // make the view visible and start the animation
            imgBackground.setVisibility(View.VISIBLE);
            anim.start();
        } else {
            // Implement this feature without material design
            imgBackground.setVisibility(View.VISIBLE);
        }
    }

    private float calculateRevealRadius(){
        int imageWidth =  imgBackground.getWidth();
        int imageHeight =  imgBackground.getHeight();
        float finalRadius = (float) Math.hypot(imageWidth, imageHeight);
        return  finalRadius;
    }
}
