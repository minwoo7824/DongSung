package com.kmw.dongsung.Activitys;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.google.firebase.iid.FirebaseInstanceId;
import com.kmw.dongsung.BuildConfig;
import com.kmw.dongsung.Commons.Https.HttpClient;
import com.kmw.dongsung.Commons.ServerUrl.Server;
import com.kmw.dongsung.Commons.Utils;
import com.kmw.dongsung.Func.InfiniteFunc.InfinitePagerAdapter;
import com.kmw.dongsung.Func.InfiniteFunc.InfiniteViewPager;
import com.kmw.dongsung.Func.SangGaPagerAdapter;
import com.kmw.dongsung.R;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.DataFormatException;

import static android.content.Intent.makeRestartActivityTask;
import static com.kmw.dongsung.Commons.Utils.Collapse;
import static com.kmw.dongsung.Commons.Utils.Expand;
import static com.kmw.dongsung.Commons.Utils.JsonIntIsNullCheck;
import static com.kmw.dongsung.Commons.Utils.JsonIsNullCheck;
import static com.kmw.dongsung.Commons.Utils.moneyFormatToWon;

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
public class InternetSangGaActivity extends Activity implements View.OnClickListener {

    private String TAG = "InternetSangGaActivity";
    InfiniteViewPager viewPager;
    InfinitePagerAdapter infinitePagerAdapter;
    SangGaPagerAdapter adater;
    LinearLayout linearBg;
    TextView txtTime;

    public static final String TIME_SERVER = "pool.ntp.org";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat hangulDateFormat = new SimpleDateFormat("MM월 dd일 HH시 mm분 ");
    String success = "";
    Calendar calendar;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    int mode = 1;

    ArrayList<String> imgPathList    = new ArrayList<>();
    ArrayList<String> titleList      = new ArrayList<>();
    ArrayList<String> dmNameList     = new ArrayList<>();
    ArrayList<String> genderList     = new ArrayList<>();
    ArrayList<String> ageList        = new ArrayList<>();
    ArrayList<String> religionList   = new ArrayList<>();
    ArrayList<String> positionList   = new ArrayList<>();
    ArrayList<String> insertTimeList = new ArrayList<>();
    ArrayList<String> checkInList    = new ArrayList<>();
    ArrayList<String> checkOutList   = new ArrayList<>();
    ArrayList<String> locationList   = new ArrayList<>();
    ArrayList<String> buildingList   = new ArrayList<>();
    ArrayList<String> floorList      = new ArrayList<>();
    ArrayList<String> totalList      = new ArrayList<>();
    ArrayList<String> cmNameList     = new ArrayList<>();
    ArrayList<String> cmPhoneList    = new ArrayList<>();
    ArrayList<String> rstTotalList   = new ArrayList<>();

    Timer timer;
    int position = 0;
    Handler handler;
    Runnable Update;

    //hover
    LinearLayout linearHover;
    LinearLayout linearHoverVisible;

    private Button btnLogout,btnReboot,btnWifi,btnAuto,btnManual;
    private LinearLayout linearPopupVisible,linearHoverGone;
    private LinearLayout linearWifiPopupVisible;
    private ImageView imgWifiClose;
    private TextView txtVersion;

    private int mPopupStatus;
    private static final int POPUP_LOGOUT = 0;
    private static final int POPUP_REBOOT = 1;
    private static final int POPUP_WIFI = 2;
    private static final int POPUP_AUTO = 3;
    private static final int POPUP_MANUAL = 4;

    private TextView                mTextViewTitle;
    private TextView                mTextViewContents;
    private Button                  mBtnOK;
    private Button                  mBtnCancel;
    private LinearLayout wifiListView;

    private WifiManager wifiManager;
    private List<ScanResult> scanDatas;
    NetworkInfo wifi;
    ConnectivityManager manager;
    ArrayList<String> wifiSSIDArray = new ArrayList<>();
    InputMethodManager inputMethodManager;
    EditText editTextToken;
    private TextView txtSystemUpdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_sang_ga);

        pref = getSharedPreferences("dongsung",MODE_PRIVATE);
        editor = pref.edit();

        mode = pref.getInt("calculateFlag",0);
//        mode = 2;

        FindViewById();

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        wifi = manager.getActiveNetworkInfo();

        if (wifi.isConnected()){
            Log.i(TAG,"wifi connect");
            wifiManager.setWifiEnabled(true);
        }else{
            Log.i(TAG,"wifi disconnect");
            wifiManager.setWifiEnabled(true);
        }
    }

    void FindViewById(){
        viewPager = (InfiniteViewPager)findViewById(R.id.view_pager_sang_ga);
        linearBg = (LinearLayout)findViewById(R.id.linear_internet_sanga_bg);
        txtTime = (TextView)findViewById(R.id.txt_internet_sanga_time);

        if (mode == 1){
            linearBg.setBackgroundResource(R.drawable.img_sanga_version1_bg);
        }else{
            linearBg.setBackgroundResource(R.drawable.img_sanga_version2_bg);
        }

        //hover
        linearHover = (LinearLayout)findViewById(R.id.linear_hover);
        linearHoverVisible = (LinearLayout)findViewById(R.id.linear_hover_visible);
        linearHoverGone = (LinearLayout)findViewById(R.id.linear_hover_gone);
        btnLogout = (Button)findViewById(R.id.btn_hover_logout);
        btnReboot = (Button)findViewById(R.id.btn_hover_reboot);
        btnWifi = (Button)findViewById(R.id.btn_hover_wifi);
        btnAuto = (Button)findViewById(R.id.btn_hover_auto);
        btnManual = (Button)findViewById(R.id.btn_hover_manual);
        linearWifiPopupVisible = (LinearLayout)findViewById(R.id.linear_wifi_popup_visible);
        linearPopupVisible = (LinearLayout)findViewById(R.id.linear_popup_visible);
        imgWifiClose = (ImageView)findViewById(R.id.img_wifi_popup_close);
        wifiListView = (LinearLayout) findViewById(R.id.list_wifi);

        mTextViewTitle    = (TextView)findViewById(R.id.TextView_Popup_Basic_Title);
        mTextViewContents = (TextView)findViewById(R.id.TextView_Popup_Basic_Contents);
        mBtnOK            = (Button)findViewById(R.id.Btn_Popup_Basic_Confirm);
        mBtnCancel = (Button)findViewById(R.id.Btn_Popup_Basic_Cancel);
        txtVersion = (TextView)findViewById(R.id.txt_hover_mode_network);
        txtSystemUpdate = (TextView)findViewById(R.id.txt_hover_system_update);

        btnLogout.setOnClickListener(this);
        btnReboot.setOnClickListener(this);
        btnWifi.setOnClickListener(this);
        btnAuto.setOnClickListener(this);
        btnManual.setOnClickListener(this);
        imgWifiClose.setOnClickListener(this);

        mBtnCancel.setOnClickListener(mPopupListenerCancel);
        mBtnOK.setOnClickListener(mPopupListenerOk);

        linearHover.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_HOVER_ENTER : {
                        Log.i(TAG,"enter");
                        linearHoverVisible.setVisibility(View.VISIBLE);

                        if(getNetworkState() != null && getNetworkState().isConnected()) {
                            if (getNetworkState().getType() == ConnectivityManager.TYPE_WIFI){
                                txtVersion.setText("Mode. Ver " + BuildConfig.VERSION_NAME + "  |  Wifi 연결  |  " + pref.getString("raspberryId",""));
                            }else{
                                txtVersion.setText("Mode. Ver " + BuildConfig.VERSION_NAME + "  |  Network 연결  |  " + pref.getString("raspberryId",""));
                            }
                        } else {
                            txtVersion.setText("Mode. Ver " + BuildConfig.VERSION_NAME + "  |  Network 미연결  |  " + pref.getString("raspberryId",""));
                        }

                        break;
                    }

                    case MotionEvent.ACTION_HOVER_MOVE : {
                        Log.i(TAG,"move");
                        break;
                    }

                    case MotionEvent.ACTION_HOVER_EXIT : {
                        Log.i(TAG,"exit");
                        break;
                    }
                }
                return false;
            }
        });

        linearHoverGone.setOnHoverListener(new View.OnHoverListener() {
            @Override
            public boolean onHover(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_HOVER_ENTER : {
                        Log.i(TAG,"enter");
                        linearHoverVisible.setVisibility(View.GONE);
                        break;
                    }
                }
                return false;
            }
        });
    }

    public NetworkInfo getNetworkState() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo;
    }

    public class TimeNetWork extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            NTPUDPClient lNTPUDPClient = new NTPUDPClient();

            lNTPUDPClient.setDefaultTimeout(3000);

            long returnTime = 0;

            try {

                lNTPUDPClient.open();

                InetAddress lInetAddress = InetAddress.getByName(TIME_SERVER);

                TimeInfo lTimeInfo = lNTPUDPClient.getTime(lInetAddress);

                // returnTime =  lTimeInfo.getReturnTime(); // local time

                returnTime = lTimeInfo.getMessage().getTransmitTimeStamp().getTime();   //server time

                success = simpleDateFormat.format(new Date(returnTime));

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lNTPUDPClient.close();
            }
            return success;
        }

        @Override
        protected void onPostExecute(String s) {
            Log.i("tag","time : " + s);

            try {
                Date date = simpleDateFormat.parse(s);
                calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.HOUR,9);

                txtTime.setText(hangulDateFormat.format(calendar.getTime()));

//                for (int i = 0; i < checkOutList.size(); i++){
//                    if (checkOutList.get(i).equals(txtTime.getText().toString())){
//                        new BinsoListNetWork().execute();
//                    }
//                }
            } catch (ParseException e) {
                txtTime.setText("시간정보 불러오는중입니다..");
                e.printStackTrace();
            }
        }
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
                Date date = new Date(System.currentTimeMillis());
                calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.HOUR,9);

                txtTime.setText(hangulDateFormat.format(calendar.getTime()));

            // 메세지를 처리하고 또다시 핸들러에 메세지 전달 (1000ms 지연)
            mHandler.sendEmptyMessageDelayed(0,1000 * 60);
        }
    };

    public class BinsoListNetWork extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpClient.Builder http = new HttpClient.Builder("POST", Server.sangaListUrl());

            http.addOrReplace("order","EXPOSURE ASC, APPELLATION ASC");
            http.addOrReplace("statusPlate","true");
            http.addOrReplace("eventAliveFlag","true");
            http.addOrReplace("eventAliveFlag","true");
            http.addOrReplace("funeralNo",pref.getString("funeralNo",""));

            HttpClient post = http.create();
            post.request();
            String body = post.getBody();

            Log.i(TAG, "BinsoListNetWork check : " + body);

            return body;
        }

        @Override
        protected void onPostExecute(String s) {

            imgPathList    = new ArrayList<>();
            titleList      = new ArrayList<>();
            dmNameList     = new ArrayList<>();
            genderList     = new ArrayList<>();
            ageList        = new ArrayList<>();
            religionList   = new ArrayList<>();
            positionList   = new ArrayList<>();
            insertTimeList = new ArrayList<>();
            checkInList    = new ArrayList<>();
            checkOutList   = new ArrayList<>();
            locationList   = new ArrayList<>();
            buildingList   = new ArrayList<>();
            floorList      = new ArrayList<>();
            totalList      = new ArrayList<>();
            cmNameList     = new ArrayList<>();
            cmPhoneList    = new ArrayList<>();
            rstTotalList   = new ArrayList<>();

            if (s.length() != 0){
                try {
                    JSONArray jsonArray = new JSONArray(s);

                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);
                        imgPathList.add(JsonIsNullCheck(object,"DM_PHOTO"));
                        titleList.add(JsonIsNullCheck(object,"APPELLATION"));
                        dmNameList.add(JsonIsNullCheck(object,"DM_NAME"));
                        genderList.add(JsonIsNullCheck(object,"DM_GENDER"));
                        ageList.add(JsonIsNullCheck(object,"DM_AGE"));
                        religionList.add(JsonIsNullCheck(object,"DM_RELIGION_NAME"));
                        positionList.add(JsonIsNullCheck(object,"DM_POSITION"));

                        try {
                            insertTimeList.add(hangulDateFormat.format(simpleDateFormat.parse(JsonIsNullCheck(object,"ENTRANCE_ROOM_DT").replace("T"," "))));
                        }catch (ParseException e){
                            insertTimeList.add("");
                        }
                        try {
                            if (JsonIsNullCheck(object,"ENTRANCE_ROOM_NO").length() == 0 && JsonIsNullCheck(object,"FUNERAL_NO").length() != 0){
                                checkInList.add("-");
                            }else{
                                checkInList.add(hangulDateFormat.format(simpleDateFormat.parse(JsonIsNullCheck(object,"ENTRANCE_ROOM_START_DT").replace("T"," "))));
                            }

                        }catch (ParseException e){
                            checkInList.add("");
                        }

                        if (JsonIntIsNullCheck(object,"CARRYING_YN") == 0 && JsonIsNullCheck(object,"FUNERAL_NO").length() != 0){
                            checkOutList.add("미 정");
                        }else{
                            try {
                                checkOutList.add(hangulDateFormat.format(simpleDateFormat.parse(JsonIsNullCheck(object,"CARRYING_DT").replace("T"," "))));
                            }catch (ParseException e){
                                checkOutList.add("");
                            }
                        }

                        cmNameList.add(JsonIsNullCheck(object,"CM_NAME"));
                        cmPhoneList.add(JsonIsNullCheck(object,"CM_PHONE"));

                        if (JsonIsNullCheck(object,"BURIAL_PLOT_NAME").length() != 0){
                            locationList.add(JsonIsNullCheck(object,"BURIAL_PLOT_NAME"));
                        }else if (JsonIsNullCheck(object,"FUNERAL_NO").length() != 0){
                            locationList.add("미  정");
                        }else{
                            locationList.add("");
                        }

                        buildingList.add(JsonIsNullCheck(object,"CARRYING_PLACE"));
                        floorList.add(JsonIsNullCheck(object,"FLOOR"));
                        Log.i(TAG,"total : " + JsonIntIsNullCheck(object,"RST_PRICE"));
                        if (JsonIntIsNullCheck(object,"RST_PRICE") == 0){
                            totalList.add("-");
                        }else{
                            totalList.add(moneyFormatToWon(JsonIntIsNullCheck(object,"RST_PRICE")));
                        }
                    }

                    if (jsonArray.length() > 0){
                        adater = new SangGaPagerAdapter(getLayoutInflater(),InternetSangGaActivity.this,mode,(int) Math.ceil(imgPathList.size()/(double)6));

                        adater.addItem(imgPathList, titleList, dmNameList, genderList, ageList, religionList, positionList, insertTimeList,
                                checkInList, checkOutList, locationList, buildingList, floorList, totalList, cmNameList, cmPhoneList);
                        infinitePagerAdapter = new InfinitePagerAdapter(adater);
                        viewPager.setAdapter(infinitePagerAdapter);

                        Log.i(TAG,"position : " + (int) Math.ceil(imgPathList.size()/(double)6));

                        handler = new Handler() {
                            public void handleMessage(Message msg) {
                                viewPager.setCurrentItem(position++,false);
                                handler.sendEmptyMessageDelayed(0, 5 * 1000);
                            }
                        };

                        if (imgPathList.size() > 6){
                            handler.sendEmptyMessage(0);
                        }
                    }
                }catch (JSONException e){

                }
            }
        }
    }

    private View.OnClickListener mPopupListenerCancel = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            linearPopupVisible.setVisibility(View.GONE);
        }
    };

    private View.OnClickListener mPopupListenerOk = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            linearPopupVisible.setVisibility(View.GONE);
            if (mPopupStatus == POPUP_LOGOUT){
                editor.putString("raspberryId","");
                editor.apply();
                Intent intent = new Intent(InternetSangGaActivity.this, LoginActivity.class);
                startActivity(intent);
                onBackPressed();
            }else if (mPopupStatus == POPUP_REBOOT){
                try {
                    Runtime.getRuntime().exec("reboot");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if (mPopupStatus == POPUP_WIFI){

            }else if (mPopupStatus == POPUP_AUTO){

            }else if (mPopupStatus == POPUP_MANUAL){
                Intent intent = new Intent(InternetSangGaActivity.this, ModeSelectActivity.class);
                startActivity(intent);
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_hover_logout : {
                mPopupStatus = 0;

                linearPopupVisible.setVisibility(View.VISIBLE);
                mBtnOK.setVisibility(View.VISIBLE);
                mBtnCancel.setVisibility(View.VISIBLE);
                mTextViewTitle.setText("로그아웃 알림");
                mTextViewContents.setText("로그아웃하시겠습니까?");

                break;
            }
            case R.id.btn_hover_reboot : {
                mPopupStatus = 1;

                linearPopupVisible.setVisibility(View.VISIBLE);
                mBtnOK.setVisibility(View.VISIBLE);
                mBtnCancel.setVisibility(View.VISIBLE);
                mTextViewTitle.setText("재부팅 알림");
                mTextViewContents.setText("재부팅하시겠습니까?");
                break;
            }
            case R.id.btn_hover_wifi : {
                mPopupStatus = 2;

                IntentFilter intentFilter = new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION);
                intentFilter.addAction(WifiManager.NETWORK_IDS_CHANGED_ACTION);
                intentFilter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION); // 와이파이상태
                intentFilter.addAction(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION); // AP 리스트 검색
                intentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);
                intentFilter.addAction(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION); // AP가 검색이 되면 이벤트가 들어옮
                intentFilter.addAction(WifiManager.EXTRA_SUPPLICANT_ERROR);
                intentFilter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION); //와이파이 활성화
                intentFilter.addAction(WifiManager.RSSI_CHANGED_ACTION); // 안테나 감도 변경
                registerReceiver(receiver, intentFilter);
                wifiManager.startScan();

                linearWifiPopupVisible.setVisibility(View.VISIBLE);
                break;
            }
            case R.id.btn_hover_auto : {
                mPopupStatus = 3;
                break;
            }
            case R.id.btn_hover_manual : {
                mPopupStatus = 4;

                linearPopupVisible.setVisibility(View.VISIBLE);
                mBtnOK.setVisibility(View.VISIBLE);
                mBtnCancel.setVisibility(View.VISIBLE);
                mTextViewTitle.setText("수동모드 알림");
                mTextViewContents.setText("수동모드 화면으로 전환하시겠습니까?");
                break;
            }
            case R.id.img_wifi_popup_close : {
                linearWifiPopupVisible.setVisibility(View.GONE);
                if (editTextToken != null){
                    inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(editTextToken.getWindowToken(), 0);
                }
                break;
            }
        }
    }

    void WifiListItem(final String ssid, int level, final String bssid){
        View listView = getLayoutInflater().inflate(R.layout.view_wifi_list_item_layout,null);
        LinearLayout linearParent = (LinearLayout)listView.findViewById(R.id.linear_wifi_list_item_parent);
        TextView txtSsid = (TextView)listView.findViewById(R.id.txt_wifi_list_item_ssid);
        ImageView imgLevel = (ImageView)listView.findViewById(R.id.img_wifi_list_item_level);
        final LinearLayout linearVisible = (LinearLayout)listView.findViewById(R.id.linear_wifi_edit_parent);
        final EditText edtPassword = (EditText)listView.findViewById(R.id.edt_wifi_password);
        Button btnDone = (Button)listView.findViewById(R.id.btn_wifi_done);

        Log.i(TAG,"bssid : " + bssid);

        wifiListView.addView(listView);

        txtSsid.setText(ssid);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextToken = edtPassword;
                String format = String.format("\"%s\"", ssid);

//                try {
//                    Runtime.getRuntime().exec("am startservice -n com.google.wifisetup/.WifiSetupService -a WifiSetupService.Connect -e ssid " + format + " -e passphrase " + edtPassword.getText().toString());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                String networkSSID = ssid;
//                String networkPass = edtPassword.getText().toString();
//
//                WifiConfiguration conf = new WifiConfiguration();
//                conf.SSID = "\"" + networkSSID + "\"";
//
//                if (bssid.contains("WEP")){
//                    conf.wepKeys[0] = "\"" + networkPass + "\"";
//                    conf.wepTxKeyIndex = 0;
//                    conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
//                    conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
//                }else if (bssid.contains("WPA")){
//                    conf.SSID = String.format("\"%s\"", ssid);
//                    conf.preSharedKey = "\""+ networkPass +"\"";
//                }else {
//                    conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
//                }
//
//                WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//                wifiManager.addNetwork(conf);
//
//                List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
//                for( WifiConfiguration i : list ) {
//                    if(i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {
//                        wifiManager.disconnect();
//                        wifiManager.enableNetwork(i.networkId, true);
//                        wifiManager.reconnect();
//
//                        break;
//                    }
//                }

                WifiConfiguration wifiConfig = new WifiConfiguration();
                wifiConfig.SSID = String.format("\"%s\"", ssid);
                wifiConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
                wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
                wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                wifiConfig.preSharedKey = "\"".concat(edtPassword.getText().toString()).concat("\"");

                wifiManager.addNetwork(wifiConfig);
                int netId = wifiManager.addNetwork(wifiConfig);

                List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
                for( WifiConfiguration i : list ) {
                    if(i.SSID != null && i.SSID.equals("\"" + ssid + "\"")) {
                        wifiManager.disconnect();
                        wifiManager.enableNetwork(i.networkId, true);
                        wifiManager.reconnect();
                        break;
                    }
                }
            }
        });

        linearParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wifi.isConnected()){
                    Log.i(TAG,"wifi connect");
                }else{
                    Log.i(TAG,"wifi disconnect");
                    wifiManager.setWifiEnabled(true);
                }
                editTextToken = edtPassword;
                if (linearVisible.getVisibility() == View.GONE){
                    Expand(linearVisible);
                }else{
                    Collapse(linearVisible);
                }
            }
        });
    }

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();
            if(intent.getAction().equals(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION)) {
                scanDatas = wifiManager.getScanResults();

                for (int i = 0; i < scanDatas.size(); i++){
                    Log.i(TAG,"scan : " + scanDatas.get(i));
                    if (wifiSSIDArray.contains(scanDatas.get(i).SSID)){

                    }else{
                        WifiListItem(scanDatas.get(i).SSID,scanDatas.get(i).level,scanDatas.get(i).capabilities);
                        wifiSSIDArray.add(scanDatas.get(i).SSID);
                    }
                }
            }else if(intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
                Log.i(TAG,"network state");
            }else if (intent.getAction().equals(WifiManager.WIFI_STATE_CHANGED_ACTION)){

            }else if (intent.getAction().equals(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION)) {

                SupplicantState supl_state=((SupplicantState)intent.getParcelableExtra(WifiManager.EXTRA_NEW_STATE));

                switch(supl_state){
                    case ASSOCIATED:Log.i("SupplicantState", "ASSOCIATED");
                        break;
                    case ASSOCIATING:Log.i("SupplicantState", "ASSOCIATING");
                        break;
                    case AUTHENTICATING:Log.i("SupplicantState", "Authenticating...");
                        break;
                    case COMPLETED: {
                        Log.i("SupplicantState", "Connected");
                        Toast.makeText(context, "wifi 연결됨", Toast.LENGTH_SHORT).show();
                        break;
                    }
                    case DISCONNECTED:Log.i("SupplicantState", "Disconnected");
                        break;
                    case DORMANT:Log.i("SupplicantState", "DORMANT");
                        break;
                    case FOUR_WAY_HANDSHAKE:Log.i("SupplicantState", "FOUR_WAY_HANDSHAKE");
                        break;
                    case GROUP_HANDSHAKE:Log.i("SupplicantState", "GROUP_HANDSHAKE");
                        break;
                    case INACTIVE:Log.i("SupplicantState", "INACTIVE");
                        break;
                    case INTERFACE_DISABLED:Log.i("SupplicantState", "INTERFACE_DISABLED");
                        break;
                    case INVALID:Log.i("SupplicantState", "INVALID");
                        break;
                    case SCANNING:Log.i("SupplicantState", "SCANNING");
                        break;
                    case UNINITIALIZED:Log.i("SupplicantState", "UNINITIALIZED");
                        break;
                    default:Log.i("SupplicantState", "Unknown");
                        break;
                }

                int supl_error=intent.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR, -1);
                Log.i(TAG,"suple error : " + supl_error);
                if(supl_error==WifiManager.ERROR_AUTHENTICATING){
                    Log.i("ERROR_AUTHENTICATING", "ERROR_AUTHENTICATING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    Toast.makeText(context, "비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    Log.i("ERROR_AUTHENTICATING", "ERROR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                }
            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver( mUpdateReceiver, new IntentFilter("update"));
        new BinsoListNetWork().execute();

        if (mHandler != null){
            mHandler.removeMessages(0);
        }

        if (handler != null){
            handler.removeMessages(0);
        }

        mHandler.sendEmptyMessage(0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver( mUpdateReceiver);
        if (mHandler != null){
            mHandler.removeMessages(0);
        }
        if (handler != null){
            handler.removeMessages(0);
        }
    }

    String intentData = "";

    private BroadcastReceiver mUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub // Get extra data included in the Intent
            String message = intent.getStringExtra("message");
            String status = intent.getStringExtra("status");
            String data = intent.getStringExtra("data");

            if (message.equals("update") && status.equals("상가")){
                if (handler != null){
                    handler.removeMessages(0);
                }
                new BinsoListNetWork().execute();
            }else if (message.equals("system")){
                txtSystemUpdate.setVisibility(View.VISIBLE);
            }else if (message.equals("check") && status.equals("connect")){
                intentData = data;
                autorize();
            }else if (message.equals("check") && status.equals("상가")){
                mPopupStatus = 5;

                linearPopupVisible.setVisibility(View.VISIBLE);
                mTextViewTitle.setText("연결체크 알림");
                mTextViewContents.setText("연결상태가 확인되었습니다.\n"+"v "+BuildConfig.VERSION_NAME);
                mBtnOK.setVisibility(View.GONE);
                mBtnCancel.setVisibility(View.GONE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        linearPopupVisible.setVisibility(View.GONE);
                    }
                },10000);
            }else if (message.equals("disable")){
                new LoginNetWork().execute(pref.getString("raspberryId",""), FirebaseInstanceId.getInstance().getToken());
            }
        }
    };

    class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread thread, Throwable ex) {
            // 이곳에서 로그를 남기는 작업을 하면 된다.

            Log.e(TAG, "error -----------------> " + ex.toString());
            editor.putString("errorExit","1");
            editor.apply();
            moveTaskToBack(true);
            finish();

//            Intent intent22 = getPackageManager().getLaunchIntentForPackage(getPackageName());
//            startActivity(intent22);

            PackageManager packageManager = getPackageManager();
            Intent intent = packageManager.getLaunchIntentForPackage(getPackageName());
            ComponentName componentName = intent.getComponent();
            Intent mainIntent = makeRestartActivityTask(componentName);
            startActivity(mainIntent);
            System.exit(0);
        }
    }

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

                    if (JsonIsNullCheck(jsonObject,"FUNERAL_FLAG").equals("0")){
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
