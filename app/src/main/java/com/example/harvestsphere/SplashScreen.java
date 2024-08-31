package com.example.harvestsphere;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class SplashScreen extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge mode for the activity
        enableEdgeToEdge();

        setContentView(R.layout.activity_splash_screen);

        // Apply window insets to adjust the padding of the view with id 'main'
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return WindowInsetsCompat.CONSUMED;
        });

        // Initialize the handler
        handler = new Handler(Looper.getMainLooper());

        // Delay for 3 seconds and then start Login activity
        handler.postDelayed(this::startLoginActivity, 3000);
    }

    private void startLoginActivity() {
        // Check if the activity is st0ill in a valid state before starting the new activity
        if (!isFinishing() && !isDestroyed()) {
            startActivity(new Intent(SplashScreen.this, MobileSignUp.class));
            finish(); // Optional: Finish SplashScreen to prevent returning to it
        }
    }

    private void enableEdgeToEdge() {
        WindowInsetsControllerCompat windowInsetsController =
                ViewCompat.getWindowInsetsController(getWindow().getDecorView());

        if (windowInsetsController != null) {
            windowInsetsController.setSystemBarsBehavior(
                    WindowInsetsControllerCompat.BEHAVIOR_SHOW_BARS_BY_SWIPE
            );
            windowInsetsController.hide(WindowInsetsCompat.Type.systemBars());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Clean up handler to avoid memory leaks
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }
}
