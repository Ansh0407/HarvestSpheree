package com.example.harvestsphere;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import org.json.JSONObject;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity implements PaymentResultListener {

    private EditText amountEditText;
    private Button startPaymentBtn;
    private ListView paymentHistoryListView;
    private ArrayList<String> paymentHistory;
    private ArrayAdapter<String> paymentHistoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        amountEditText = findViewById(R.id.amountEditText);
        startPaymentBtn = findViewById(R.id.startPaymentBtn);
        paymentHistoryListView = findViewById(R.id.paymentHistoryListView);

        // Initialize payment history list and adapter
        paymentHistory = new ArrayList<>();
        paymentHistoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, paymentHistory);
        paymentHistoryListView.setAdapter(paymentHistoryAdapter);

        startPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String amount = amountEditText.getText().toString();
                if (!amount.isEmpty()) {
                    startPayment(Integer.parseInt(amount));
                } else {
                    Toast.makeText(PaymentActivity.this, "Enter a valid amount", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void startPayment(int amountInRupees) {
        Checkout checkout = new Checkout();
        checkout.setKeyID("rzp_test_uDyxnT2mA1Wctb");

        try {
            JSONObject options = new JSONObject();
            options.put("name", "HarvestSphere");
            options.put("description", "Test payment");
            options.put("currency", "INR");
            int amountInPaise = amountInRupees * 100;
            options.put("amount", amountInPaise);

            JSONObject prefill = new JSONObject();
            prefill.put("email", "test@example.com");
            prefill.put("contact", "9876543210");

            options.put("prefill", prefill);

            checkout.open(PaymentActivity.this, options);

        } catch (Exception e) {
            Toast.makeText(this, "Error in payment: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentSuccess(String razorpayPaymentID) {
        Toast.makeText(this, "Payment Successful: " + razorpayPaymentID, Toast.LENGTH_SHORT).show();
        // Add payment success record to history
        paymentHistory.add("Payment ID: " + razorpayPaymentID);
        paymentHistoryAdapter.notifyDataSetChanged(); // Refresh ListView
    }

    @Override
    public void onPaymentError(int code, String response) {
        Toast.makeText(this, "Payment failed: " + response, Toast.LENGTH_SHORT).show();
    }
}