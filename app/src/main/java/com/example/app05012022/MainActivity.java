package com.example.app05012022;


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
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.app05012022.databinding.ActivityDigitalSignatureBinding;
import com.example.app05012022.databinding.ActivityLoginBinding;
import com.example.app05012022.databinding.ActivityMapsBinding;
import com.example.app05012022.databinding.ActivityNotificationBinding;
import com.example.app05012022.databinding.ActivityPhoneVerifyOtpBinding;
import com.example.app05012022.databinding.ActivityTabLayoutBinding;
import com.example.app05012022.databinding.FragmentCameraBinding;
import com.example.app05012022.databinding.FragmentHomeBinding;
import com.example.app05012022.databinding.FragmentInterviewBinding;
import com.example.app05012022.databinding.NavHeaderMainBinding;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.app05012022.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private ActivityLoginBinding loginBinding;
    private FragmentInterviewBinding interviewBinding;
    private ActivityDigitalSignatureBinding signatureBinding;
    private ActivityPhoneVerifyOtpBinding otpBinding;
    private ActivityTabLayoutBinding layoutBinding;
    private ActivityMapsBinding mapsBinding;
    private FragmentCameraBinding cameraBinding;
    private NavHeaderMainBinding navHeaderMainBinding;
    Boolean loginSuccess = true;
    private int IMG_REQUEST = 21;
    ImageView imageView;

    private  TabLayout tabLayout;
    private  ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        signatureBinding = ActivityDigitalSignatureBinding.inflate(getLayoutInflater());
        mapsBinding = ActivityMapsBinding.inflate(getLayoutInflater());
        layoutBinding = ActivityTabLayoutBinding.inflate(getLayoutInflater());
        cameraBinding = FragmentCameraBinding.inflate(getLayoutInflater());
        navHeaderMainBinding = NavHeaderMainBinding.inflate(getLayoutInflater());

        imageView = findViewById(R.id.imageView2);




//        tabLayout = findViewById(R.id.tablaoutViewHome);
//        viewPager = findViewById(R.id.viewpager);

//        tabLayout.setupWithViewPager(viewPager);
//        VPadapter sectionsPagerAdapter = new VPadapter( getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);




//        ViewPager viewPager = activityTabLayoutBinding.viewpager;
//        viewPager.setAdapter(sectionsPagerAdapter);
//        TabLayout tabs = activityTabLayoutBinding.tablaoutView;
//        tabs.setupWithViewPager(viewPager);

//        navHeaderMainBinding.candidateName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
//                startService(intent);
//            }
//        });


        if (loginSuccess == false) {
            setContentView(binding.getRoot());

        }

//        notificationDialog();
//        interviewBinding.button5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                notificationDialog();
//            }
//        });


//        interviewBinding.button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                intent.setType("image/*");
//                intent.setAction(Intent.ACTION_GET_CONTENT);
//                startActivityForResult(intent,IMG_REQUEST);
//
//            }
//        });

        if (loginSuccess == true) {
            setContentView(binding.getRoot());
            setSupportActionBar(binding.appBarMain.toolbar);
            DrawerLayout drawer = binding.drawerLayout;
            NavigationView navigationView = binding.navView;
//            binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//                }
//            });
            mAppBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.nav_home, R.id.nav_apply,R.id.nav_interviewProcess,R.id.nav_createResume,
                    R.id.nav_scanDoc,R.id.nav_profile,R.id.nav_apply,R.id.nav_game,R.id.nav_scanDoc,R.id.nav_dashboard,R.id.nav_game,R.id.nav_learn)
                    .setOpenableLayout(drawer)
                    .build();
            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
            NavigationUI.setupWithNavController(navigationView, navController);
        }



    }

    ////////////////////////////onItemSelect///////////////////
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this, Login.class);
                startActivity(intent);
                return true;
            case R.id.nofi:
                Intent i = new Intent(MainActivity.this, NotificationActivity.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        getMenuInflater().inflate(R.menu.menu_signature, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityLoginBinding binding;
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),path);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void notificationDialog() {
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "channel_01";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint
                    ("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "My Notifications", NotificationManager.IMPORTANCE_MAX);
            notificationChannel.setDescription("Hi this is Channel description");
//        notificationChannel.enableLights(true);
//        notificationChannel.setLightColor(Color.WHITE);
//        notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
//        notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("Sample")
                //.setPriority(Notification.PRIORITY_MAX)
                .setContentTitle("App running status")
                .setContentText("App is successfully Run")
                .setContentInfo("Information");
        notificationManager.notify(1, notificationBuilder.build());
    }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//private void enableMyLocation() {
//    if (ContextCompat.checkSelfPermission(this,
//            Manifest.permission.ACCESS_FINE_LOCATION)
//            == PackageManager.PERMISSION_GRANTED) {
//        mMap.setMyLocationEnabled(true);
//    } else {
//        ActivityCompat.requestPermissions(this, new String[]
//                        {Manifest.permission.ACCESS_FINE_LOCATION},
//                REQUEST_LOCATION_PERMISSION);
//    }
//}
//    @Override
//    public void onRequestPermissionsResult(int requestCode,
//                                           @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        // Check if location permissions are granted and if so enable the
//        // location data layer.
//        switch (requestCode) {
//            case REQUEST_LOCATION_PERMISSION:
//                if (grantResults.length > 0
//                        && grantResults[0]
//                        == PackageManager.PERMISSION_GRANTED) {
//                    enableMyLocation();
//                    break;
//                }
//        }
//    }



}