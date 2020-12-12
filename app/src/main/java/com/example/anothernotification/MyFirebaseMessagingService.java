package com.example.anothernotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private NotificationManager notificationManager;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onCreate() {
        super.onCreate();
        notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
    }
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String title =remoteMessage.getNotification().getTitle();
        String messageBody = remoteMessage.getNotification().getBody();
        Log.e("TAG", "Got Notification" );
        Notification notification = new NotificationCompat.Builder(this, "chanel")
                .setContentTitle(title)
                .setContentText(messageBody)
                .setSmallIcon(R.drawable.drmc_logo)
                .setContentInfo("DRMC")
                .setDefaults(Notification.DEFAULT_ALL)
                .setPriority(Notification.PRIORITY_HIGH)
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .build();

        notificationManager.notify(1, notification);
    }
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        Log.d("NewToken", s);
    }
}
