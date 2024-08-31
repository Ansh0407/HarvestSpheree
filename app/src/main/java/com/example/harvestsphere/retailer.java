package com.example.harvestsphere;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class retailer extends AppCompatActivity {

    String[] vegetables = {"Tomato","Broccoli","Brussels sprouts","Cabbage","Lettuce","Spinach","Carrot","Beetroot","Pea","Turnip","Cauliflower","Drumstick","Beans","Round Beans","Okra","Garlic","Onion","Potato","Ginger","Radish","Sweet Potato"};
    String[] fruits = {"Kiwi","Orange","Banana","Apple","Pineapple","Pomegranate","Blackberry","Strawberry","Avocado","Blueberry"};
    String[] flowers = {"Alstroemeria","Amaranthus","Amaryllis","Calla","Chrysanthemum","Daffodil","Dahlia","Red Rose","Hibiscus","Lily","Sunflower","Tulip"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.retailer);

        final ListView retailerListView = findViewById(R.id.retialerCheckList);

        final ArrayList<String> checkList = new ArrayList<>();
        checkList.add("Vegetables");
        checkList.add("Fruits");
        checkList.add("Flowers");

        final ArrayAdapter<String> retailerList = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, checkList);
        retailerListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE);
        retailerListView.setAdapter(retailerList);

        final ArrayList<String> checkedArrayList = new ArrayList<>();
        Button submit = findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray checked = retailerListView.getCheckedItemPositions();
                checkedArrayList.clear();

                // Iterate through the checked items and add them to the checkedArrayList
                for (int i = 0; i < checkList.size(); i++) {
                    if (checked.get(i)) {
                        checkedArrayList.add(checkList.get(i));
                    }
                }

                // Log the checked items for debugging
                Log.d("RetailerActivity", "Checked List: " + checkedArrayList.toString());

                // Pass the checked items to the FarmersInfo activity
                Intent intent3 = new Intent(getApplicationContext(), biditem.class);
                //intent3.putStringArrayListExtra("CheckList", checkedArrayList);
                startActivity(intent3);
            }
        });
    }
}
