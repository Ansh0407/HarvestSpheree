package com.example.harvestsphere;

import android.app.Application;
import com.razorpay.Checkout;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Preload Razorpay SDK
        Checkout.preload(getApplicationContext());

}
}