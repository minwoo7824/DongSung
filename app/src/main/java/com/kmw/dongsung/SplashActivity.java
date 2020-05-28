package com.kmw.dongsung;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.Log;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;
import com.kmw.dongsung.Activitys.DeceasedImgActivity;
import com.kmw.dongsung.Activitys.DisabledActivity;
import com.kmw.dongsung.Activitys.InternetBinsoActivity;
import com.kmw.dongsung.Activitys.InternetEntryActivity;
import com.kmw.dongsung.Activitys.InternetGeneralActivity;
import com.kmw.dongsung.Activitys.InternetSangGaActivity;
import com.kmw.dongsung.Activitys.LoginActivity;
import com.kmw.dongsung.Activitys.SpecialActivity;
import com.kmw.dongsung.Activitys.TestActivity;
import com.kmw.dongsung.Commons.Https.HttpClient;
import com.kmw.dongsung.Commons.ServerUrl.Server;
import com.kmw.dongsung.Commons.UpdateService;
import com.kmw.dongsung.Commons.Utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import static com.kmw.dongsung.Commons.Utils.JsonIntIsNullCheck;
import static com.kmw.dongsung.Commons.Utils.JsonIsNullCheck;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class SplashActivity extends Activity implements View.OnClickListener {

    private String TAG = "SplashActivity";
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String token = "";
    String finalpath = "";
    int sum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pref = getSharedPreferences("dongsung",MODE_PRIVATE);
        editor = pref.edit();

        token = FirebaseInstanceId.getInstance().getToken();

        Log.i(TAG,"errorExit : " + pref.getString("errorExit",""));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (pref.getString("raspberryId","").length() != 0){
                    if(getNetworkState() != null && getNetworkState().isConnected()) {
                        if (pref.getString("errorExit","").equals("1")){
                            editor.putString("errorExit","");
                            editor.apply();
                            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }else{
                            mHandler.sendEmptyMessage(0);
                        }
                    }else{
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }else{

                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        },1000);
    }

    public Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (sum == 5){
                Intent i = getBaseContext().getPackageManager().
                        getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }

            if (token == null){
                sum++;
                token = FirebaseInstanceId.getInstance().getToken();
                mHandler.sendEmptyMessageDelayed(0,1000);
            }else if (token.length() == 0){
                sum++;
                token = FirebaseInstanceId.getInstance().getToken();
                mHandler.sendEmptyMessageDelayed(0,1000);
            }else{
                editor.putString("token",token);
                editor.apply();
                new LoginNetWork().execute(pref.getString("raspberryId",""),token);
                mHandler.removeMessages(0);
            }
        }
    };

    public NetworkInfo getNetworkState() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mHandler != null){
            mHandler.removeMessages(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public class LoginNetWork extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpClient.Builder http = new HttpClient.Builder("POST", Server.loginUrl());

            http.addOrReplace("raspberryId",strings[0]);
            http.addOrReplace("deviceCode",strings[1]);

            HttpClient post = http.create();
            post.request();
            String body = post.getBody();

            Log.i(TAG, "LoginNetWork check : " + body);

            return body;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.length() != 0){
                try {
                    JSONObject jsonObject = new JSONObject(s);

                    editor.putString("raspberryId",JsonIsNullCheck(jsonObject,"RASPBERRY_ID"));
                    editor.putString("funeralNo",JsonIsNullCheck(jsonObject,"FUNERAL_NO"));
                    editor.putString("classificationName",JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME"));
                    editor.putString("appellation",JsonIsNullCheck(jsonObject,"APPELLATION"));
                    editor.putString("raspberryConnectionNo",JsonIsNullCheck(jsonObject,"RASPBERRY_CONNECTION_NO"));
                    editor.putInt("calculateFlag",JsonIntIsNullCheck(jsonObject,"CALCULATE_FLAG"));
                    editor.apply();

                    if (JsonIsNullCheck(jsonObject,"FUNERAL_FLAG").equals("1")){
                        if (!(JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").contains("영정") || JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").contains("특수"))){
                            Intent intent = new Intent(SplashActivity.this, UpdateService.class);
                            startService(intent);
                        }

                        if (JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").contains("빈소")){
                            Intent intent = new Intent(SplashActivity.this, InternetBinsoActivity.class);
                            startActivity(intent);
                        }else if (JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").contains("입관")){
                            Intent intent = new Intent(SplashActivity.this, InternetEntryActivity.class);
                            startActivity(intent);
                        }else if (JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").contains("종합")){
                            Intent intent = new Intent(SplashActivity.this, InternetGeneralActivity.class);
                            startActivity(intent);
                        }else if (JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").contains("상가")){
                            Intent intent = new Intent(SplashActivity.this, InternetSangGaActivity.class);
                            startActivity(intent);
                        }else if (JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").contains("특수")){
                            Intent intent = new Intent(SplashActivity.this, SpecialActivity.class);
                            startActivity(intent);
                        }else if (JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").contains("영정")){
                            Intent intent = new Intent(SplashActivity.this, DeceasedImgActivity.class);
                            startActivity(intent);
                        }else if (JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").length() == 0){
                            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }else{
                        Intent intent = new Intent(SplashActivity.this, DisabledActivity.class);
                        startActivity(intent);
                    }
                }catch (JSONException e){

                }
            }else{
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }
}
