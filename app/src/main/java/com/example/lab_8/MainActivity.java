package com.example.lab_8;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.lab_8.services.Service1;
import com.example.lab_8.services.Service2;

public class MainActivity extends AppCompatActivity {

    Button service1Btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.service1Btn).setOnClickListener(view -> {
            Intent service1_intent = new Intent(getApplicationContext(), Service1.class);
            service1_intent.putExtra("number", 100);
            startService(service1_intent);

        });

        findViewById(R.id.service2StartBtn).setOnClickListener(view -> {
            Intent service2_start = new Intent(getApplicationContext(), Service2.class);
            startService(service2_start);
        });

        findViewById(R.id.Service2StopBtn).setOnClickListener(view -> {
            Intent service2_stop = new Intent(getApplicationContext(), Service2.class);
            stopService(service2_stop);
        });

        findViewById(R.id.changeActivity).setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this,SecondActivity.class));

        });
    }
}