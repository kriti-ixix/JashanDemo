package com.kriti.jashandemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class CheckOtp extends AppCompatActivity {

    EditText editText; Button button;
    String code = "", userCode = "";
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_otp);
        editText = findViewById(R.id.checkOtpEditText);
        button = findViewById(R.id.checkOtpButton);

        code = getIntent().getStringExtra("Code");
        mAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userCode = editText.getText().toString();
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(code, userCode);
                signInWithPhoneAuthCredential(credential);
            }
        });
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential)
    {
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Checking", "signInWithCredential:success");
                    Toast.makeText(CheckOtp.this, "OTP was correct", Toast.LENGTH_LONG).show();
                    FirebaseUser user = task.getResult().getUser();
                    // Update UI
                }
                else
                    {
                    // Sign in failed, display a message and update the UI
                    Log.w("Checking", "signInWithCredential:failure", task.getException());
                    Toast.makeText(CheckOtp.this, "OTP was incorrect", Toast.LENGTH_LONG).show();

                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        Log.d("Checking", "Invalid credential");
                    }
                }
            }
        });
    }
}