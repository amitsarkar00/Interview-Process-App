package com.example.app05012022;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.app05012022.databinding.ActivityDigitalSignatureBinding;
import com.example.app05012022.databinding.ActivityLoginBinding;
import com.example.app05012022.databinding.ActivityMainBinding;
import com.example.app05012022.databinding.CompleteDialogBinding;

public class Login extends AppCompatActivity {
    private ActivityLoginBinding loginBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(loginBinding.getRoot());
        loginBinding.buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Registation.class);
                startActivity(intent);
            }
        });
        loginBinding.buttonLoginPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,SendOtp.class);
                startActivity(intent);

            }
        });
//        if (loginSuccess == false){
//            loginBinding.button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getApplicationContext(),Registation.class);
//                    startActivity(intent);
//                }
//            });
//        }
    }
    void  openDialogSignature() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        LayoutInflater alert = LayoutInflater.from(getApplicationContext());
        CompleteDialogBinding alertDialogBinding = CompleteDialogBinding.inflate(alert);
        builder.setView(alertDialogBinding.getRoot());
        builder.setPositiveButton("submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Submit", Toast.LENGTH_SHORT).show();
//                notify.notificationDialog();
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.create();
        builder.show();
    }
}