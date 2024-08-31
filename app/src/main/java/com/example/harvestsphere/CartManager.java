package com.example.harvestsphere;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;

public class CartManager {

    private SharedPreferences sharedPreferences;

    public CartManager(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void addItemToCart(String itemName, int quantity) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(itemName, quantity);
        editor.apply();
    }

    public Map<String, ?> getCartItems() {
        return sharedPreferences.getAll();
    }
}
