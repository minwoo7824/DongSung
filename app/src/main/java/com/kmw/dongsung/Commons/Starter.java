package com.kmw.dongsung.Commons;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.kmw.dongsung.SplashActivity;

public class Starter extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Intent i = new Intent(context, SplashActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
