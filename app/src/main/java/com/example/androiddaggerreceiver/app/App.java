package com.example.androiddaggerreceiver.app;


import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;

import com.example.androiddaggerreceiver.data.di.component.DaggerAppDaggerComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class App extends DaggerApplication {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    private static final String CHANNEL_1 = "channel1";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_1, "New chennel", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("This is a new Chennel 1");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }
    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppDaggerComponent.builder().create(this);
    }

    public static Context getContext() {
        return context;
    }
}
