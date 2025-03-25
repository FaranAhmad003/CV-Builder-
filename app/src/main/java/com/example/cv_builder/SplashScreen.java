package com.example.cv_builder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {
    private View rootView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        rootView = findViewById(android.R.id.content);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Fade out animation
            rootView.animate()
                    .alpha(0f)
                    .setDuration(1000)
                    .withEndAction(() -> {
                        startActivity(new Intent(SplashScreen.this, MainActivity.class));
                        finish();
                    });
        }, 2000);
    }
}
