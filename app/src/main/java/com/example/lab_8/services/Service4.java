package com.example.lab_8.services;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.core.view.KeyEventDispatcher;

import com.example.lab_8.SecondActivity;

import java.util.Locale;


public class Service4 extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private MediaPlayer player = null;
    public static final String TAG = Service1.class.getName();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (player == null) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    synchronized (this) {
                        try {

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        Log.i(TAG, "run, make it stop ringing");
                        Intent ringing = new Intent();
                        ringing.setAction(Intent.ACTION_MAIN);
                        ringing.addCategory(Intent.CATEGORY_LAUNCHER);
                        ringing.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        ComponentName componentName = new ComponentName(Service4.this, SecondActivity.class);
                        ringing.setComponent(componentName);
                        startActivity(ringing);

                    }
                }
            };
            player = MediaPlayer.create(Service4.this, Settings.System.DEFAULT_RINGTONE_URI);
            player.setLooping(true);
            player.start();
            Thread worker = new Thread(runnable);
            worker.start();

        }

        return START_STICKY;

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (player != null){player.stop();}

    }
}

