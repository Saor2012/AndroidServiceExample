package com.example.androiddaggerreceiver.presentation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationManagerCompat;

import com.example.androiddaggerreceiver.R;
import com.example.androiddaggerreceiver.app.App;
import com.example.androiddaggerreceiver.data.ServicePos;
import com.example.androiddaggerreceiver.databinding.ActivityMainBinding;

import java.io.Serializable;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements IMainPresenter.View {
    @Inject
    IMainPresenter.Presenter presenter;
    private NotificationManagerCompat notificationManager;
    private ActivityMainBinding binding;
    private Context insertContext;
    private final int REQUEST_CODE = 1004;
    private final String REQUEST_ACTION_CONTEXT = "REQUEST_ACTION_CONTEXT";
    private final String REQUEST_ACTION_VALUE = "REQUEST_ACTION_VALUE";
    private final String RESULT_ACTION_CONTEXT = "RESULT_ACTION_CONTEXT";
    private final String RESULT_ACTION_VALUE = "RESULT_ACTION_VALUE";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        //byte[] res = intent.getByteArrayExtra(RESULT_ACTION_VALUE);
        presenter.init();
        startService(new Intent(this,ServicePos.class));
        //notificationManager = new NotificationManagerCompat().from(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE){
            if (resultCode == RESULT_OK){
                if (data != null && data.getByteArrayExtra(RESULT_ACTION_VALUE) != null){
                    //String base64 = data.getStringExtra(RESULT_ACTION);
                    byte[] response = data.getByteArrayExtra(RESULT_ACTION_VALUE);
                }
            }
        }
    }



    @Override
    public void sendQuery(byte[] byteArray) {
        startActivityForResult(new Intent()
            .setClassName("com.example.androiddaggerservice.presentation",
                    "com.example.androiddaggerservice.presentation.MainActivity")
            .putExtra(REQUEST_ACTION_VALUE, "")
            .putExtra(REQUEST_ACTION_CONTEXT, (Serializable) getApplicationContext()), REQUEST_CODE);
    }

    @Override
    public void toast(String messag) {
        Toast.makeText(this,messag,Toast.LENGTH_LONG).show();
    }

    @Override
    public void startNewService() {
        startService(new Intent(App.getContext(), ServicePos.class));
    }

    @Override
    public void stopNewService() {
        stopService(new Intent(App.getContext(), ServicePos.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.startView(this);
        presenter.init();
    }

    @Override
    protected void onDestroy() {
        if (presenter != null){
            presenter.detachView();
            presenter = null;
        }
        super.onDestroy();
    }
}
