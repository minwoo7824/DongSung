package com.kmw.dongsung.Commons;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


import androidx.annotation.Nullable;

import com.kmw.dongsung.Activitys.LoginActivity;

public class NotDeathService extends Service {

    private String TAG = "BootUpService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent1 = new Intent(this, LoginActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent1);
        Log.i(TAG,"onCreate");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // 서비스가 호출될 때마다 실행
        return super.onStartCommand(intent,START_NOT_STICKY,startId);
    }

    @Override
    public ComponentName startForegroundService(Intent service) {
        Log.i(TAG,"startForegroundService");
        return super.startForegroundService(service);
    }

    @Override
    public boolean stopService(Intent name) {
        Log.i(TAG,"stopService");
        return super.stopService(name);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG,"onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.i(TAG,"onRebind");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        Intent intent1 = new Intent(this, LoginActivity.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent1);
    }
}
