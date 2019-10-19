package com.example.timnorton_finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.TextView;

import com.r0adkll.slidr.Slidr;

public class SplashActivity extends AppCompatActivity {

    private TextView mTvSwipe;
    private final Handler mHandler = new Handler();
    private Runnable mRunnable;
    private String mSwipeArrows = ">   >   >   >   ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Slidr.attach(this);

        mTvSwipe = findViewById(R.id.tvSwipe);

        animateSwipe();
    }

    @Override
    protected void onPause() {
        super.onPause();

        mHandler.removeCallbacks(mRunnable);
    }

    private void animateSwipe() {
        mRunnable = new Runnable() {
            @Override
            public void run() {
                mTvSwipe.setText(mSwipeArrows);

                mSwipeArrows = mSwipeArrows.substring(mSwipeArrows.length() - 2, mSwipeArrows.length() - 1)
                        + mSwipeArrows.substring(0, mSwipeArrows.length() - 1);
                mHandler.postDelayed(mRunnable, 150);
            }
        };
        mHandler.postDelayed(mRunnable, 0);
    }
}
