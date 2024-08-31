package com.example.harvestsphere;  // Replace with your package name

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class facts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts);  // Make sure this matches your XML layout file name
    }

    // Method to open the first link
    public void openLink1(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.breedr.co/news/what-are-the-new-farming-techniques"));  // Replace with your actual URL
        startActivity(browserIntent);
    }

    // Method to open the second link
    public void openLink2(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://environment.co/10-best-farming-techniques/"));  // Replace with your actual URL
        startActivity(browserIntent);
    }

    // Method to open the third link
    public void openLink3(View view) {
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://tracextech.com/7-best-practices-in-sustainable-agriculture/"));  // Replace with your actual URL
        startActivity(browserIntent);
    }
}
