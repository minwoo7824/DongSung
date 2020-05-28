package com.kmw.dongsung.Commons;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.things.update.StatusListener;
import com.google.android.things.update.UpdateManager;
import com.google.android.things.update.UpdateManagerStatus;
import com.google.android.things.update.UpdatePolicy;
import com.kmw.dongsung.Activitys.LoginActivity;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class UpdateService extends Service {

    private String TAG = "UpdateService";
    UpdateManager updateManager;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG,"service onCreate");

        updateManager = UpdateManager.getInstance();
        UpdatePolicy policy = new UpdatePolicy.Builder()
                .setPolicy(UpdatePolicy.POLICY_APPLY_ONLY)
                .setApplyDeadline(5L, TimeUnit.DAYS)
                .build();
        updateManager.setPolicy(policy);

        updateManager.setChannel("update-channel");

        updateManager.addStatusListener(updateStatusListener);

        updateManager.performUpdateNow(UpdatePolicy.POLICY_APPLY_ONLY);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        updateManager.removeStatusListener(updateStatusListener);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG,"service onBind");
        return null;
    }

    private void sendMessage(String sendMessage,String status){
        Log.d("messageService", "Broadcasting message");
        Intent intent = new Intent("update");
        intent.putExtra("message", sendMessage);
        intent.putExtra("status",status);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    private StatusListener updateStatusListener = new StatusListener() {
        @Override
        public void onStatusUpdate(UpdateManagerStatus status) {
            Log.i(TAG,"service updateStatusListener");
//            updateManager.performUpdateNow(UpdatePolicy.POLICY_APPLY_ONLY);
            Log.i(TAG,"service updateStatusListener status : " + status.currentState);

            //STATE_CHECKING_FOR_UPDATES 1
            //STATE_DOWNLOADING_UPDATE 3
            //STATE_FINALIZING_UPDATE 5
            //STATE_IDLE 0
            //STATE_REPORTING_ERROR 7
            //STATE_UPDATED_NEEDS_REBOOT 6
            //STATE_UPDATE_AVAILABLE 2

            switch (status.currentState) {
                case UpdateManagerStatus.STATE_UPDATE_AVAILABLE:
                    Log.i(TAG,"service STATE_UPDATE_AVAILABLE");
                    break;
                case UpdateManagerStatus.STATE_DOWNLOADING_UPDATE:
                    int percent = (int) (status.pendingUpdateInfo.downloadProgress * 100);
                    Log.i(TAG,"service STATE_DOWNLOADING_UPDATE " + percent + "%");
                    sendMessage("system","");
                    String send = "" + percent + "% Updating...";
                    break;
                case UpdateManagerStatus.STATE_FINALIZING_UPDATE:
                    Log.i(TAG,"service STATE_FINALIZING_UPDATE");
                    /* Download complete */
                    send = "rebooting...";
//                    sendMessage(send,1);
                    break;
                case UpdateManagerStatus.STATE_UPDATED_NEEDS_REBOOT:
                    /* Reboot device to apply update */
                    send = "finish...";
//                    sendMessage(send,2);
                    try {
                        Runtime.getRuntime().exec("reboot");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
//                    sendMessage("",2);
                    break;
            }
        }
    };
}
