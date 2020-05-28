package com.kmw.dongsung.Activitys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.kmw.dongsung.BuildConfig;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kmw.dongsung.Commons.Https.HttpClient;
import com.kmw.dongsung.Commons.ServerUrl.Server;
import com.kmw.dongsung.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

import static com.kmw.dongsung.Commons.Utils.JsonIsNullCheck;

public class DisabledActivity extends Activity {

    private String TAG = "DisabledActivity";
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disabled);

        pref = getSharedPreferences("dongsung",MODE_PRIVATE);
        editor = pref.edit();

    }

    String intentData = "";

    private BroadcastReceiver mUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub // Get extra data included in the Intent
            String message = intent.getStringExtra("message");
            String status = intent.getStringExtra("status");
            String data = intent.getStringExtra("data");

            if (message.equals("check") && status.equals("connect")){
                intentData = data;
                autorize();
            }else if (message.equals("disable")){
                new LoginNetWork().execute(pref.getString("raspberryId",""), FirebaseInstanceId.getInstance().getToken());
            }
            //else if 활성여부 (재부팅시키면될듯)
        }
    };

    void autorize(){

        if(getNetworkState() != null && getNetworkState().isConnected()) {
            if (getNetworkState().getType() == ConnectivityManager.TYPE_WIFI){
                new ConnectNetWork().execute(getIpAddress(),"",intentData);
            }else{
                WebView webView = new WebView(this);

                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);

                        view.loadUrl("javascript:window.Android.getHtml(document.getElementsByTagName('body')[0].innerHTML);"); //<html></html> 사이에 있는 모든 html을 넘겨준다.
                    }
                });

                webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
                webView.setNetworkAvailable(true);
                webView.getSettings().setJavaScriptEnabled(true); //Javascript를 사용하도록 설정
                webView.getSettings().setDomStorageEnabled(true);
                webView.addJavascriptInterface(new MyJavascriptInterface(), "Android");

                webView.loadUrl("http://ipecho.net/plain");
            }
        }
    }

    public class MyJavascriptInterface {
        @JavascriptInterface
        public void getHtml(String html) { //위 자바스크립트가 호출되면 여기로 html이 반환됨
            System.out.println(html);
            new ConnectNetWork().execute(getIpAddress(),html,intentData);
        }
    }

    public String getIpAddress(){

        try{
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface networkInterface : interfaces){
                List<InetAddress> addrs = Collections.list(networkInterface.getInetAddresses());

                for (InetAddress addr : addrs){
                    if (!addr.isLoopbackAddress()){
                        String sAddr = addr.getHostAddress();

                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (isIPv4){
                            return sAddr;
                        }
                    }
                }
            }
        }catch (Exception e){

        }
        return "";
    }

    public NetworkInfo getNetworkState() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    public class ConnectNetWork extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpClient.Builder http = new HttpClient.Builder("POST", Server.updateRbControl());
            http.addOrReplace("raspberryId",pref.getString("raspberryId",""));
            http.addOrReplace("systemFlag","1");
            http.addOrReplace("privateIp",strings[0]);
            http.addOrReplace("autorizedIp",strings[1]);

            HttpClient post = http.create();
            post.request();
            String body = post.getBody();

            Log.i(TAG, "ConnectNetWork check : " + body);

            return body;
        }

        @Override
        protected void onPostExecute(String s) {

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver( mUpdateReceiver, new IntentFilter("update"));
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver( mUpdateReceiver);
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

                    if (JsonIsNullCheck(jsonObject,"FUNERAL_FLAG").equals("1")){
                        try {
                            Runtime.getRuntime().exec("reboot");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }catch (JSONException e){

                }
            }else{

            }
        }
    }
}
