package com.example.lab_8.services;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.security.Provider;

public class service2  extends Service {

    public static final String TAG = service1.class.getName();
    private boolean running = false;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        Toast.makeText(this,"Service created", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        running = false;
        Toast.makeText(this,"Service Stopped", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (running) Toast.makeText(this,TAG+ "Service is already running",Toast.LENGTH_SHORT);
       else {
           final int currentId = startId;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    long timeout = System.currentTimeMillis() + 30000;
                    while(running && System.currentTimeMillis() < timeout){
                        synchronized(this){
                            sendMessage(currentId);
                            try{
                                wait(5000);
                            }catch (Exception e){
                                e.printStackTrace();

                            }
                        }
                        stopSelf();
                    }
                }


            };
            Thread worker = new Thread(runnable);
            worker.start();
        }
        return Service.START_STICKY;
    }

    private void sendMessage(int id) {
        final int currentId = id;
        Handler handler = new Handler(service2.this.getMainLooper());
        handler.post(() -> Toast.makeText(this,TAG + "Service "+ currentId + "isworking", Toast.LENGTH_SHORT).show());
    }

}
