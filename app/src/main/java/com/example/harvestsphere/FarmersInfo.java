package com.example.harvestsphere;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FarmersInfo extends AppCompatActivity {

    static ArrayList<String> checkedList;
    RecyclerView myrv;
    List<FarmerProductDetails> lstFarmerDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.farmers_info);

        myrv = findViewById(R.id.recycleRetailer);

        // Initialize checkedList from Intent
        Intent intent = getIntent();
        checkedList = intent.getStringArrayListExtra("CheckList");

        if (checkedList == null) {
            checkedList = new ArrayList<>(); // Avoid null pointer exception
        }

        // Initialize RecyclerView
        myrv.setLayoutManager(new GridLayoutManager(this, 1));

        // Fetch data from Firebase
        fetchProductDetailsFromFirebase();
    }

    private void fetchProductDetailsFromFirebase() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Table Name Here");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lstFarmerDetails.clear(); // Clear existing data
                String name = "Farmer";
                int count = 1;

                for (DataSnapshot formerKey : dataSnapshot.getChildren()) {
                    for (DataSnapshot productKey : formerKey.getChildren()) {
                        ProductDetails productDetails = productKey.getValue(ProductDetails.class);

                        if (productDetails != null && checkedList.contains(productDetails.ProductCategory)) {
                            lstFarmerDetails.add(new FarmerProductDetails(
                                    productKey.getKey(),
                                    productDetails.ProductName,
                                    productDetails.ProductCategory,
                                    formerKey.getKey(),
                                    name + count,
                                    productDetails.ProductBasePrice,
                                    productDetails.ProdQuantity
                            ));

                            Log.i("Value1", productDetails.ProdQuantity + " " + productDetails.ProductCategory + " " + productDetails.ProductBasePrice + " " + productDetails.ProductName);
                        }
                    }
                    count++;
                }

                // Update RecyclerView with new data
                FarmerDetailsAdapter myAdapter = new FarmerDetailsAdapter(FarmersInfo.this, lstFarmerDetails);
                myrv.setAdapter(myAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("FirebaseError", "Error: " + databaseError.getMessage());
            }
        });
    }
}
