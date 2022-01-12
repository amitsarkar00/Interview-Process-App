package com.example.app05012022.ui;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.provider.MediaStore;
import android.telephony.TelephonyCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app05012022.Login;
import com.example.app05012022.MainActivity;
import com.example.app05012022.Notify;
import com.example.app05012022.R;
import com.example.app05012022.databinding.ActivityDigitalSignatureBinding;
import com.example.app05012022.databinding.ActivityLoginBinding;
import com.example.app05012022.databinding.ActivityTermsBinding;
import com.example.app05012022.databinding.FragmentHomeBinding;
import com.example.app05012022.databinding.FragmentInterviewBinding;
import com.example.app05012022.ui.home.HomeViewModel;

import java.io.IOException;

import javax.xml.transform.Result;


public class Interview extends Fragment {

    private FragmentInterviewBinding binding;
    private ActivityDigitalSignatureBinding signatureBinding;
    private int IMG_REQUEST = 21;
    private Bitmap bitmap;
    MainActivity myObj = new MainActivity(); // Create an object of Main
    Notify notify = new Notify();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentInterviewBinding.inflate(inflater, container, false);
        signatureBinding = ActivityDigitalSignatureBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
//        View root = signatureBinding.getRoot();



        binding.button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), Login.class);
//                startActivity(intent);
//                Toast.makeText(getContext(), "Please wait our team will connect to you  ", Toast.LENGTH_SHORT).show();
                openDialogTerms();
            }
        });
        binding.button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, IMG_REQUEST);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && data != null){

            try {
                Uri path = data.getData();
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                binding.imageView2.setImageBitmap(bitmap);
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
    void  openDialogTerms() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater alert = LayoutInflater.from(getContext());
        ActivityTermsBinding alertDialogBinding = ActivityTermsBinding.inflate(alert);
        builder.setView(alertDialogBinding.getRoot());
        builder.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openDialogSignature();
//                Toast.makeText(getContext(), "Submit", Toast.LENGTH_SHORT).show();
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
    void  openDialogSignature() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater alert = LayoutInflater.from(getContext());
        ActivityDigitalSignatureBinding alertDialogBinding = ActivityDigitalSignatureBinding.inflate(alert);
        builder.setView(alertDialogBinding.getRoot());
        builder.setPositiveButton("submit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Submit", Toast.LENGTH_SHORT).show();
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