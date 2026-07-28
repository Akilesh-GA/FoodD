package com.example.foodd;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.progressindicator.LinearProgressIndicator;

@SuppressLint("CustomSplashScreen")
public class SplashActivity extends AppCompatActivity{

    LinearProgressIndicator progressIndicator;
    TextView splashText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        progressIndicator = findViewById(R.id.splash_progressbar);
        splashText = findViewById(R.id.splash_text);

        Handler handler = new Handler();

        Runnable runnable = new Runnable() {
            int progress = 0;
            @Override
            public void run() {

                progress += 10;
                progressIndicator.setProgressCompat(progress, true);

                if(progress < 100) {

                    if(progress >= 0 && progress < 20) {
                        splashText.setText(getString(R.string.splash_text_one));
                    } else if(progress >= 20 && progress < 40) {
                        splashText.setText(getString(R.string.splash_text_two));
                    } else if(progress >= 40 && progress < 60) {
                        splashText.setText(getString(R.string.splash_text_three));
                    } else if(progress >= 60 && progress < 80) {
                        splashText.setText(getString(R.string.splash_text_four));
                    } else {
                        splashText.setText(getString(R.string.splash_text_five));
                    }

                    handler.postDelayed(this, 500);

                } else {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

        handler.post(runnable);
    }
}
