package com.example.app05012022;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app05012022.databinding.ActivityRegistationBinding;
import com.example.app05012022.databinding.CompleteDialogBinding;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class SendOtp extends AppCompatActivity {
    private ActivityRegistationBinding registationBinding;
    private CompleteDialogBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        final ProgressBar progressBar = findViewById(R.id.progressbar);

        binding = CompleteDialogBinding.inflate(getLayoutInflater());

        binding.buttonGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "hiiii", Toast.LENGTH_SHORT).show();
                if (registationBinding.inputnumber.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                registationBinding.buttonPhoneGetOTP.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + registationBinding.inputnumber.getText().toString(),
                        60, TimeUnit.SECONDS,
                        SendOtp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                registationBinding.buttonPhoneGetOTP.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                registationBinding.buttonPhoneGetOTP.setVisibility(View.VISIBLE);
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                registationBinding.buttonPhoneGetOTP.setVisibility(View.VISIBLE);
                                Intent intent = new Intent(getApplicationContext(), VerifyPhoneOtpActivity.class);
                                intent.putExtra("Mobile", registationBinding.inputnumber.getText().toString());
                                intent.putExtra("verificationId", verificationId);
                                startActivity(intent);
                            }
                        });

            }
        });
    }
}
