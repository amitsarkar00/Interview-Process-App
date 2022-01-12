package com.example.app05012022;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app05012022.databinding.ActivityRegistationBinding;
import com.example.app05012022.databinding.ActivityPhoneVerifyOtpBinding;
import com.example.app05012022.databinding.FragmentProfileBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Registation extends AppCompatActivity {
    private ActivityRegistationBinding binding;
    private ActivityPhoneVerifyOtpBinding otpBinding;
    String verifyId = null;
    int code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegistationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setupPhoneOTPInputs();
        setupEmailOTPInputs();
//////////////////////////////////////////getOTP Phone/////////////////////////////////////////////////////
        binding.buttonPhoneGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = binding.inputnumber.getText().toString();
                if(phone.trim().isEmpty() || phone.length() != 10)
                {
                    Toast.makeText(Registation.this,"Enter Phone Number",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    SharedPreferences shrd = getSharedPreferences("demo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = shrd.edit();
                    editor.putString("str2", phone);
                    editor.apply();
                    Toast.makeText(Registation.this, "save", Toast.LENGTH_SHORT).show();

                binding.progressbar.setVisibility(View.VISIBLE);
                binding.buttonPhoneGetOTP.setVisibility(View.INVISIBLE);

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+ binding.inputnumber.getText().toString(),
                        60, TimeUnit.SECONDS,
                        Registation.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks(){

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential)
                            {
                                binding.progressbar.setVisibility(View.GONE);
                                binding.buttonPhoneGetOTP.setVisibility(View.VISIBLE);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e)
                            {
                                binding.progressbar.setVisibility(View.GONE);
                                binding.buttonPhoneGetOTP.setVisibility(View.VISIBLE);
                                Toast.makeText(Registation.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String verificationId,@NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)
                            {
                                binding.progressbar.setVisibility(View.GONE);
                                binding.buttonPhoneGetOTP.setVisibility(View.VISIBLE);
                                verifyId = verificationId;
//                                if(binding.inputcodephone1.getText().toString().trim().isEmpty()
//                                        ||binding.inputcodephone2.getText().toString().trim().isEmpty()
//                                        ||binding.inputcodephone3.getText().toString().trim().isEmpty()
//                                        ||binding.inputcodephone4.getText().toString().trim().isEmpty()
//                                        ||binding.inputcodephone5.getText().toString().trim().isEmpty()
//                                        ||binding.inputcodephone6.getText().toString().trim().isEmpty())
//                                {
//                                    Toast.makeText(Registation.this,"Please Enter Valid Code",Toast.LENGTH_SHORT).show();
//                                    return;
//
//                                }
//                                String code = binding.inputcodephone1.getText().toString() +
//                                        binding.inputcodephone2.getText().toString() +
//                                        binding.inputcodephone3.getText().toString() +
//                                        binding.inputcodephone4.getText().toString() +
//                                        binding.inputcodephone5.getText().toString() +
//                                        binding.inputcodephone6.getText().toString();
//                                if(verificationId != null)
//                                {
//                                    binding.progressbar.setVisibility(View.VISIBLE);
//                                    binding.buttonPhoneVerify.setVisibility(View.INVISIBLE);
//                                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
//                                            verificationId,code);
//                                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
//                                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                                @Override
//                                                public void onComplete(@NonNull Task<AuthResult> task) {
//                                                    binding.progressbar.setVisibility(View.GONE);
//                                                    binding.buttonPhoneVerify.setVisibility(View.VISIBLE);
//                                                    if (task.isSuccessful())
//                                                    {
//                                                        Toast.makeText(Registation.this,"Number Verified",Toast.LENGTH_SHORT).show();
//                                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
////                                                        startActivity(intent);
//                                                    }
//                                                    else
//                                                    {
//                                                        Toast.makeText(Registation.this,"The verification code entered is incorrect",Toast.LENGTH_SHORT).show();
//                                                    }
//                                                }
//                                            });
//                                }
                            }
                        });
            }}
        });

//////////////////////////////////////////otpVerifyPhone///////////////////////////////////////////////////////
        binding.buttonPhoneVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);

                if(binding.inputcodephone1.getText().toString().trim().isEmpty()
                        ||binding.inputcodephone2.getText().toString().trim().isEmpty()
                        ||binding.inputcodephone3.getText().toString().trim().isEmpty()
                        ||binding.inputcodephone4.getText().toString().trim().isEmpty()
                        ||binding.inputcodephone5.getText().toString().trim().isEmpty()
                        ||binding.inputcodephone6.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(Registation.this,"Please Enter Valid Code",Toast.LENGTH_SHORT).show();
                    return;

                }
                String code = binding.inputcodephone1.getText().toString() +
                        binding.inputcodephone2.getText().toString() +
                        binding.inputcodephone3.getText().toString() +
                        binding.inputcodephone4.getText().toString() +
                        binding.inputcodephone5.getText().toString() +
                        binding.inputcodephone6.getText().toString();
                if(verifyId != null)
                {
                    binding.progressbar.setVisibility(View.VISIBLE);
                    binding.buttonPhoneVerify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verifyId,code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    binding.progressbar.setVisibility(View.GONE);
                                    binding.buttonPhoneVerify.setVisibility(View.VISIBLE);
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(Registation.this,"Number Verified",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                                        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(i);
                                    }
                                    else
                                    {
                                        Toast.makeText(Registation.this,"The verification code entered is incorrect",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

//////////////////////////////////////////getOtpEmail/////////////////////////////////////////////////////////
        binding.buttonEmailGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = binding.inputemail.getText().toString();
                if(phone.trim().isEmpty())
                {
                    Toast.makeText(Registation.this,"Enter Email Id",Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    SharedPreferences shrd = getSharedPreferences("demo", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = shrd.edit();
                    editor.putString("str2", phone);
                    editor.apply();
                    Toast.makeText(Registation.this, "save", Toast.LENGTH_SHORT).show();

                    binding.progressbar2.setVisibility(View.VISIBLE);
                    binding.buttonEmailGetOTP.setVisibility(View.INVISIBLE);


                    Random random = new Random();
                    code = random.nextInt(8999) + 1000;
                    String url = "www.shivila.tech/otp/sendEmail.php";
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(Registation.this, response, Toast.LENGTH_SHORT).show();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Registation.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
                            params.put("email",binding.inputemail.getText().toString());
                            params.put("code",String.valueOf(code));
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
//                String email = binding.inputemail.getText().toString();
//                if (  email.length() > 0  ) {
//                    SharedPreferences shrd = getSharedPreferences("demo", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = shrd.edit();
//                    editor.putString("str3", email);
//                    editor.apply();
//                    Toast.makeText(Registation.this, "save", Toast.LENGTH_SHORT).show();
//                }
//                else {
//                    Toast.makeText(getApplicationContext(), "Please Enter Your Details", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(Registation.this, VerifyPhoneOtpActivity.class);
//                    startActivity(intent);
//
//                }
            }
        });

////////////////////////////////////////VerifyEmail///////////////////////////////////////////////////////////////////
        binding.buttonEmailVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(binding.inputcode1.getText().toString().trim().isEmpty()
                        ||binding.inputcode2.getText().toString().trim().isEmpty()
                        ||binding.inputcode3.getText().toString().trim().isEmpty()
                        ||binding.inputcode4.getText().toString().trim().isEmpty()
                        ||binding.inputcode5.getText().toString().trim().isEmpty()
                        ||binding.inputcode6.getText().toString().trim().isEmpty())
                {
                    Toast.makeText(Registation.this,"Please Enter Valid Code",Toast.LENGTH_SHORT).show();
                    return;

                }
                String code = binding.inputcode1.getText().toString() +
                        binding.inputcode2.getText().toString() +
                        binding.inputcode3.getText().toString() +
                        binding.inputcode4.getText().toString() +
                        binding.inputcode5.getText().toString() +
                        binding.inputcode6.getText().toString();
                if(verifyId != null)
                {
                    binding.progressbar.setVisibility(View.VISIBLE);
                    binding.buttonEmailVerify.setVisibility(View.INVISIBLE);
                    PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                            verifyId,code
                    );
                    FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    binding.progressbar.setVisibility(View.GONE);
                                    binding.buttonEmailVerify.setVisibility(View.VISIBLE);
                                    if (task.isSuccessful())
                                    {
                                        Toast.makeText(Registation.this,"Number Verified",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast.makeText(Registation.this,"The verification code entered is incorrect",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            }
        });

////////////////////////////////////////login////////////////////////////////////////////////////////////////////////
        binding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });

    }


//    public void sendVerifyMail(View view) {
//        Random random = new Random();
//        code = random.nextInt(8999) + 1000;
//        String url = "www.shivila.tech/otp/sendEmail.php";
//        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                Toast.makeText(Registation.this, response, Toast.LENGTH_SHORT).show();
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(Registation.this, error.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        }) {
//            @Nullable
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                params.put("email",binding.inputemail.getText().toString());
//                params.put("code",String.valueOf(code));
//                return params;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }
/////////////////////////////////phone/////////////////////////////////////////////////////////////
    private void setupPhoneOTPInputs() {
        binding.inputcodephone1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    binding.inputcodephone2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputcodephone2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    binding.inputcodephone3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputcodephone3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    binding.inputcodephone4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputcodephone4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    binding.inputcodephone5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputcodephone5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    binding.inputcodephone6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
/////////////////////////////////email/////////////////////////////////////////////////////////////
    private void setupEmailOTPInputs() {
        binding.inputcode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    binding.inputcode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputcode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    binding.inputcode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputcode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    binding.inputcode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputcode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    binding.inputcode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        binding.inputcode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()) {
                    binding.inputcode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}