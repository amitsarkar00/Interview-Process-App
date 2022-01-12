package com.example.app05012022;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.example.app05012022.ui.Interview;

public class Notify {

//    public void notificationDialog() {
//        Context context;
//        NotificationManager notificationManager = (NotificationManager)Interview.getSystemService(Context.NOTIFICATION_SERVICE);
//        String NOTIFICATION_CHANNEL_ID = "channel_01";
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            @SuppressLint
//                    ("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
//                    "My Notifications", NotificationManager.IMPORTANCE_MAX);
//            notificationChannel.setDescription("Hi this is Channel description");
////        notificationChannel.enableLights(true);
////        notificationChannel.setLightColor(Color.WHITE);
////        notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
////        notificationChannel.enableVibration(true);
//            notificationManager.createNotificationChannel(notificationChannel);
//        }
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID);
//        notificationBuilder.setAutoCancel(true)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setTicker("Sample")
//                //.setPriority(Notification.PRIORITY_MAX)
//                .setContentTitle("App running status")
//                .setContentText("App is successfully Run")
//                .setContentInfo("Information");
//        notificationManager.notify(1, notificationBuilder.build());
//    }
}
