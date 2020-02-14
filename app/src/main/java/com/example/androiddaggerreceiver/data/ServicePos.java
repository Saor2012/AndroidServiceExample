package com.example.androiddaggerreceiver.data;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import dagger.android.DaggerService;

public class ServicePos extends DaggerService {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onCreate() {
        super.onCreate();

        Log.e("ServicePos","ok onCreate");
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
