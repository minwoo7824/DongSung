package com.kmw.dongsung.Commons.Https;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;

import android.util.Log;


import androidx.core.app.NotificationCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.things.update.StatusListener;
import com.google.android.things.update.UpdateManager;
import com.google.android.things.update.UpdateManagerStatus;
import com.google.android.things.update.UpdatePolicy;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.kmw.dongsung.Commons.UpdateService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "FirebaseMsgService";
    NotificationCompat.Builder notificationBuilder;
    Notification notification;
    NotificationManager notificationManager;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    public void onCreate() {
        pref = getSharedPreferences("dongsung",MODE_PRIVATE);
        editor = pref.edit();
    }

    public MyFirebaseMessagingService() {
        super();
    }

    // [START receive_message]
    @SuppressLint("WrongThread")
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.i(TAG,"remoteMessage = "+remoteMessage);

        if (remoteMessage != null){
            Log.i(TAG,"remoteMessage = "+remoteMessage);
            Log.i(TAG,"remoteMessage.getData().get(title) = "+remoteMessage.getData().get("title"));
            Log.i(TAG,"remoteMessage.getData().get(body) = "+remoteMessage.getData().get("body"));
            Log.i(TAG,"remoteMessage.getData().get(title) = "+remoteMessage.getNotification().getTitle());
            Log.i(TAG,"remoteMessage.getData().get(body) = "+remoteMessage.getNotification().getBody());
            Log.i(TAG,"remoteMessage.getData().get(body) = "+remoteMessage.getData());

            if (remoteMessage.getNotification().getTitle().equals("1")){
                if (pref.getString("classificationName","").contains("빈소")){
                    if (remoteMessage.getNotification().getBody() != null){
                        if (remoteMessage.getNotification().getBody().contains(pref.getString("raspberryConnectionNo",""))){
                            sendMessage("update","빈소",remoteMessage.getNotification().getBody());
                        }
                    }else{
                        sendMessage("update","빈소","");
                    }
                }else if (pref.getString("classificationName","").contains("종합")){
                    sendMessage("update","종합","");
                }else if (pref.getString("classificationName","").contains("입관")){
                    sendMessage("update","입관","");
                }else if (pref.getString("classificationName","").contains("상가")){
                    sendMessage("update","상가","");
                }else if (pref.getString("classificationName","").contains("특수")){
                    sendMessage("update","특수","");
                }else if (pref.getString("classificationName","").contains("영정")){
                    if (remoteMessage.getNotification().getBody() != null){
                            sendMessage("update","영정",remoteMessage.getNotification().getBody());

                    }else{
                        sendMessage("update","영정","");
                    }
                }
            }else if (remoteMessage.getNotification().getTitle().equals("2")){
                if (pref.getString("classificationName","").contains("빈소")){
                    sendMessage("check","빈소","");
                }else if (pref.getString("classificationName","").contains("종합")){
                    sendMessage("check","종합","");
                }else if (pref.getString("classificationName","").contains("입관")){
                    sendMessage("check","입관","");
                }else if (pref.getString("classificationName","").contains("상가")){
                    sendMessage("check","상가","");
                }else if (pref.getString("classificationName","").contains("특수")){
                    sendMessage("check","특수","");
                }else if (pref.getString("classificationName","").contains("영정")){
                    sendMessage("check","영정","");
                }

            }else if (remoteMessage.getNotification().getTitle().equals("3")){
                Intent i = getBaseContext().getPackageManager().
                        getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }else if (remoteMessage.getNotification().getTitle().equals("4")){
                try {
                    Runtime.getRuntime().exec("reboot");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (remoteMessage.getNotification().getTitle().equals("5")){
                if (isServiceRunningCheck()){
                    UpdateManager updateManager = UpdateManager.getInstance();
                    UpdatePolicy policy = new UpdatePolicy.Builder()
                            .setPolicy(UpdatePolicy.POLICY_APPLY_ONLY)
                            .setApplyDeadline(5L, TimeUnit.DAYS)
                            .build();
                    updateManager.setPolicy(policy);
                    updateManager.setChannel("update-channel");
                    updateManager.addStatusListener(updateStatusListener);
                    updateManager.performUpdateNow(UpdatePolicy.POLICY_APPLY_ONLY);

                }else{
                    Intent intent = new Intent(this, UpdateService.class);
                    startService(intent);
                }
            }else if (remoteMessage.getNotification().getTitle().equals("6")){
                sendMessage("check","connect",remoteMessage.getData().toString());
            }else if (remoteMessage.getNotification().getTitle().equals("7")){
                sendMessage("disable","disable","");
            }else if (remoteMessage.getNotification().getTitle().equals("8")){
                editor.putString("raspberryId",remoteMessage.getNotification().getBody().toString());
                editor.apply();
                Intent i = getBaseContext().getPackageManager().
                        getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        }
    }

    private void sendMessage(String sendMessage,String status,String data){
        Intent intent = new Intent("update");
        intent.putExtra("message", sendMessage);
        intent.putExtra("status",status);
        intent.putExtra("data",data);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }

    public boolean isServiceRunningCheck() {
        ActivityManager manager = (ActivityManager) this.getSystemService(Activity.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            Log.i(TAG,"service.service.getClassName() : " + service.service.getClassName());
            if ("com.kmw.dongsung.Commons.UpdateService".equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
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
                    sendMessage("system","","");
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
