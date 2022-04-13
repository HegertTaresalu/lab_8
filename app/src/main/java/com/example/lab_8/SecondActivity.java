package com.example.lab_8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.TextView;

import com.example.lab_8.services.Service3;
import com.example.lab_8.services.Service4;

public class SecondActivity extends AppCompatActivity  implements ServiceConnection {
    private boolean isBound = false;
    private TextView txtResult;
    private Service3 service3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txtResult = findViewById(R.id.txtResult);
        findViewById(R.id.dateBtn).setOnClickListener(view -> {
            if(isBound) txtResult.setText(service3.getCurrentDate());
        });

        findViewById(R.id.timeBtn).setOnClickListener(view -> {
            if (isBound)txtResult.setText(service3.getCurrentTime());
        });
        findViewById(R.id.connectBtn).setOnClickListener(view -> {
           if (!isBound){
               Intent boundIntent = new Intent(this, Service3.class);
               bindService(boundIntent,this, Context.BIND_AUTO_CREATE);
           }
        });

        findViewById(R.id.startRingingBtn).setOnClickListener(view -> {
            startService(new Intent(SecondActivity.this, Service4.class));
        });
        findViewById(R.id.stopRingingBtn).setOnClickListener(view -> {
            stopService(new Intent(SecondActivity.this,Service4.class));
        });
    }


    @Override
    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Service3.MyBinder myBinder = (Service3.MyBinder) iBinder;
        service3 = myBinder.getService();
        isBound = true;
        txtResult.setText("Connected");
    }

    @Override
    public void onServiceDisconnected(ComponentName componentName) {
        isBound = false;
        service3 = null;
    }
}