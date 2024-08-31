package com.example.harvestsphere;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class bidding extends AppCompatActivity {

    private String name, quantity, price, productID, formerID, retailerID;
    private EditText biddingPrice;
    private TextView ProductName, Quantity, BasePrice;
    ArrayList<String> productDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bidding);

        // Get intent and retrieve product details
        Intent intent = new Intent(bidding.this, biditem.class);
                       /* intent.putExtra("FORMER_ID", formerID);
                        intent.putExtra("PRODUCT_ID", productID);
                        intent.putExtra("BIDDING_PRICE", biddingPrice.getText().toString());
                        intent.putExtra("RETAILER_ID", retailerID);*/
        startActivity(intent);

        if (productDetails != null) {
            name = productDetails.get(2);
            productID = productDetails.get(1);
            quantity = productDetails.get(5);
            formerID = productDetails.get(4);
            price = productDetails.get(6);
        } else {
           // Toast.makeText(this, "Product details not available", Toast.LENGTH_SHORT).show();
            finish(); // If no product details are available, end the activity
            return;
        }

        // Setup dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Start Bidding");

        // Inflate the bidding XML layout
        LayoutInflater layoutInflater = getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.bidding, null);

        // Initialize UI components
        ProductName = view.findViewById(R.id.ProductName);
        Quantity = view.findViewById(R.id.Quantity);
        BasePrice = view.findViewById(R.id.Price);
        biddingPrice = view.findViewById(R.id.BiddingPrice);

        // Check if views are properly initialized
        if (ProductName == null || Quantity == null || BasePrice == null || biddingPrice == null) {
            Log.e("bidding", "View initialization failed. Check the XML layout.");
            return; // Exit the method if any view is null
        }

        // Set initial product values
        ProductName.setText(name);
        Quantity.setText(quantity);
        BasePrice.setText(price);

        // Set dialog view
        builder.setView(view);

        // Set positive button to start bidding
        builder.setPositiveButton("START", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // Check if bidding price is greater than the base price
                try {
                    int biddingAmount = Integer.parseInt(biddingPrice.getText().toString());
                    int basePrice = Integer.parseInt(price);

                    if (biddingAmount < basePrice) {
                        Toast.makeText(bidding.this, "Please Enter a Value Greater than the price", Toast.LENGTH_SHORT).show();
                        dialogInterface.dismiss();
                    } else {
                        // Start new activity for bid item
                        Intent intent = new Intent(bidding.this, biditem.class);
                       /* intent.putExtra("FORMER_ID", formerID);
                        intent.putExtra("PRODUCT_ID", productID);
                        intent.putExtra("BIDDING_PRICE", biddingPrice.getText().toString());
                        intent.putExtra("RETAILER_ID", retailerID);*/
                        startActivity(intent);

                        dialogInterface.dismiss();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(bidding.this, "Invalid price format", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set negative button to cancel and go back to retailer screen
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent1 = new Intent(getApplicationContext(), retailer.class);
                startActivity(intent1);
            }
        });

        // Show dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
