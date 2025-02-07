package com.example.harvestsphere;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class otp extends AppCompatActivity {

    private EditText otpEdit;
    private String OTP;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.otp);

        Button mVerifyCodeBtn = findViewById(R.id.verifybtn);
        otpEdit = findViewById(R.id.mobile);

        firebaseAuth = FirebaseAuth.getInstance();

        OTP = getIntent().getStringExtra("auth");
        mVerifyCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String verification_code = otpEdit.getText().toString();
                if(!verification_code.isEmpty()){
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(OTP , verification_code);
                    signIn(credential);
                }else{
                    Toast t = Toast.makeText(otp.this, "Please Enter OTP", Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });
    }
    private void signIn(PhoneAuthCredential credential){
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    sendToMain();
                }else{
                    Toast.makeText(otp.this, "Verification Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser !=null){
            sendToMain();
        }
    }

    private void sendToMain(){
        startActivity(new Intent(otp.this , MainActivity.class));
        finish();
    }
    private void toEmailSignUp(){
        startActivity(new Intent(otp.this , EmailSignUp.class));
        finish();
    }
    @Override
    public void onBackPressed() {
//        finishAffinity();
        startActivity(new Intent(this, com.example.harvestsphere.MobileSignUp.class));
        finish();
    }
}