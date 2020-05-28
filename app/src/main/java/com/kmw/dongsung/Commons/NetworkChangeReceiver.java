package com.kmw.dongsung.Commons;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.kmw.dongsung.Activitys.DeceasedImgActivity;
import com.kmw.dongsung.Activitys.InternetBinsoActivity;
import com.kmw.dongsung.Activitys.InternetEntryActivity;
import com.kmw.dongsung.Activitys.InternetGeneralActivity;
import com.kmw.dongsung.Activitys.InternetSangGaActivity;
import com.kmw.dongsung.Activitys.LoginActivity;
import com.kmw.dongsung.Activitys.SpecialActivity;
import com.kmw.dongsung.Commons.Https.HttpClient;
import com.kmw.dongsung.Commons.ServerUrl.Server;
import com.kmw.dongsung.SplashActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static com.kmw.dongsung.Commons.Utils.JsonIntIsNullCheck;
import static com.kmw.dongsung.Commons.Utils.JsonIsNullCheck;

public class NetworkChangeReceiver extends BroadcastReceiver {

    private String TAG = "NetworkChangeReceiver";
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    public void onReceive(final Context context, final Intent intent) {

        boolean status = NetworkUtil.getConnectivityStatusString(context);

        pref = context.getSharedPreferences("dongsung",Context.MODE_PRIVATE);
        editor = pref.edit();

        if (new ServiceManager(context).isNetworkAvailable()){
            try {
                if (pref.getString("raspberryId","").length() != 0){
                    Runtime.getRuntime().exec("reboot");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class ServiceManager {

        Context context;

        public ServiceManager(Context base) {
            context = base;
        }

        public boolean isNetworkAvailable() {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    }
}
