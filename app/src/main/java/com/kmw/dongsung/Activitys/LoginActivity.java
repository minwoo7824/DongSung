package com.kmw.dongsung.Activitys;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
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
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.kmw.dongsung.BuildConfig;
import com.kmw.dongsung.Commons.HangulInput;
import com.kmw.dongsung.Commons.HangulVersionArray;
import com.kmw.dongsung.Commons.Https.HttpClient;
import com.kmw.dongsung.Commons.NetworkChangeReceiver;
import com.kmw.dongsung.Commons.ServerUrl.Server;
import com.kmw.dongsung.Commons.UpdateService;
import com.kmw.dongsung.Commons.Wifi.InfWIFIConsts;
import com.kmw.dongsung.Commons.Wifi.WIFIUtil;
import com.kmw.dongsung.R;
import com.kmw.dongsung.SplashActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import static com.kmw.dongsung.Commons.Utils.Collapse;
import static com.kmw.dongsung.Commons.Utils.Expand;
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
public class LoginActivity extends Activity implements View.OnClickListener {

    String TAG = "LoginActivity";
    Button btnNext;
    LinearLayout linearLogin;
    EditText edtLogin;
    HangulInput hangulInput;
    InputMethodManager inputMethodManager;
    TextView txtIpAddress;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    boolean shiftStatus = false;
    boolean hanYoung = false;

    String token = "";
    int sum = 0;

    //hover
    LinearLayout linearHover;
    LinearLayout linearHoverVisible;

    private Button btnLogout,btnReboot,btnWifi,btnAuto,btnManual;
    private LinearLayout linearPopupVisible,linearHoverGone;
    private LinearLayout linearWifiPopupVisible;
    private ImageView imgWifiClose;
    private TextView txtVersion;
    private LinearLayout wifiListView;

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

    ProgressDialog progressDialog;

    private WifiManager wifiManager;
    private List<ScanResult> scanDatas;
    NetworkInfo wifi;
    ConnectivityManager manager;
    ArrayList<String> wifiSSIDArray = new ArrayList<>();
    EditText editTextToken;

    Intent beforeIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        beforeIntent = getIntent();

        pref = getSharedPreferences("dongsung",MODE_PRIVATE);
        editor = pref.edit();

        FindViewById();

        ipAddress();

        token = FirebaseInstanceId.getInstance().getToken();

        Log.i(TAG,"getIp : " + getIpAddress());

        Log.i(TAG,"token : " + token);

        inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(edtLogin.getWindowToken(), 0);

        hangulInput = new HangulInput(edtLogin.onCreateInputConnection(new EditorInfo()));

        edtLogin.setImeOptions(EditorInfo.IME_ACTION_GO);

        edtLogin.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId){
                    case EditorInfo.IME_ACTION_GO:
                        if (edtLogin.getText().toString().length() != 0){
                            if(getNetworkState() != null && getNetworkState().isConnected()) {
                                mHandler.sendEmptyMessage(0);
                            } else {
                                Toast.makeText(LoginActivity.this, "네트워크상태를 체크해주세요.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        }
                        break;
                }
                return true;
            }
        });

        edtLogin.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    Log.i(TAG,"i : " + i + " keyEvent : " + keyEvent.getKeyCode());

                    if (i == 66){
                        if (edtLogin.getText().toString().length() != 0){
                            if(getNetworkState() != null && getNetworkState().isConnected()) {
                                mHandler.sendEmptyMessage(0);
                            } else {
                                Toast.makeText(LoginActivity.this, "네트워크상태를 체크해주세요.", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(LoginActivity.this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    if (hanYoung){
                        //delete
                        if (i == 67){
                            delete();
                        }
                        //shift
                        else if (i == 59 || i == 60){
                            shiftStatus = true;
                        }
                        else {
                            hangul(HangulVersionArray.ReturnText(i,shiftStatus));
                        }
                    }
                    //hanYoung
                    if (i == 218){
                        if (hanYoung){
                            hanYoung = false;
                        }else{
                            hanYoung = true;
                        }
                    }

                }else if (keyEvent.getAction() == KeyEvent.ACTION_UP){
                    if (i == 59 || i == 60){
                        shiftStatus = false;
                    }
                }
                if (!hanYoung){
                    return false;
                }else{
                    return true;
                }
            }
        });

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

//        wifiManager.setWifiEnabled(false);

//        WIFIUtil wifiUtil = WIFIUtil.getInstance();
//        if ( wifiUtil.isConnectWIFI(this) ) {
//            // WIFI 사용 가능
//            Log.i(TAG,"사용가능");
//            // WIFI 연결
//            wifiUtil.setSSIDandWPA("playbench", "tladmlqh");
//            wifiUtil.connectWifi(this, InfWIFIConsts.WIFI_CONFIG_WPA2);
//
//            // WIFI 연결 해제
//            // 현재 연결되어있는 WIFI를 해제한다.
////            wifiUtil.removeWifiSpecialSSID(this);
//        }
//        else {
//            // WIFI 사용 불가
//            Log.i(TAG,"사용불가능");
//        }

    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkMultiplePermissions(int permissionCode, Context context) {

        String[] PERMISSIONS = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
        if (!hasPermissions(context, PERMISSIONS)) {
            ActivityCompat.requestPermissions((Activity) context, PERMISSIONS, permissionCode);
        } else {
            // Open your camera here.
        }
    }

    private boolean hasPermissions(Context context, String... permissions) {
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }else{
                }
            }
        }
        return true;
    }
    @SuppressLint("InlinedApi")
    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, 1234);
        }else{
            String str = Environment.getExternalStorageState();
            if (str.equals(Environment.MEDIA_MOUNTED)) {

                String dirPath = Environment.getExternalStorageDirectory().getAbsolutePath();
                File file = new File(dirPath);
                if (!file.exists()) { // 원하는 경로에 폴더가 있는지 확인
                    file.mkdirs();
                }
            } else {
                Toast.makeText(LoginActivity.this, "SD Card 인식 실패", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1 : {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    checkPermissions();
                } else {
                    Toast.makeText(LoginActivity.this, "권한요청을 거부했습니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    void FindViewById(){
        linearLogin = (LinearLayout)findViewById(R.id.linear_login);
        btnNext = (Button)findViewById(R.id.btn_login_next);
        edtLogin = (EditText) findViewById(R.id.edt_login);
        txtIpAddress = (TextView)findViewById(R.id.txt_login_ip_address);

        btnNext.setOnClickListener(this);
        linearLogin.setOnClickListener(this);

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

    public void hangul(String inputText) {

        try {
            this.hangulInput = new HangulInput(edtLogin.onCreateInputConnection(new EditorInfo()));
            for (int i=0; i<=inputText.length(); i++) {
                try {
                    hangulInput.ProcessHangul(convertStringToUnicode(inputText,i));
                }catch (Exception e){}
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void delete()
    {
//        try {
        hangulInput.HangulBs();
//        }catch (Exception e){

//        }
    }

    public int convertStringToUnicode(String text, int x)
    {
        char firstChar = text.charAt(x);
        int uniCode = firstChar;
        Log.d("HWI", "유니코드 : " + uniCode);
        return uniCode;
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

//                    Intent intent = new Intent(LoginActivity.this, InternetBinsoActivity.class);
//                    startActivity(intent);

                    if (JsonIsNullCheck(jsonObject,"FUNERAL_FLAG").equals("1")){
                        if (!(JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").contains("영정") || JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").contains("특수"))){
                            Intent intent = new Intent(LoginActivity.this, UpdateService.class);
                            startService(intent);
                        }

                        if (JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").contains("빈소")){
                            Intent intent = new Intent(LoginActivity.this, InternetBinsoActivity.class);
                            startActivity(intent);
                        }else if (JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").contains("입관")){
                            Intent intent = new Intent(LoginActivity.this, InternetEntryActivity.class);
                            startActivity(intent);
                        }else if (JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").contains("종합")){
                            Intent intent = new Intent(LoginActivity.this, InternetGeneralActivity.class);
                            startActivity(intent);
                        }else if (JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").contains("상가")){
                            Intent intent = new Intent(LoginActivity.this, InternetSangGaActivity.class);
                            startActivity(intent);
                        }else if (JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").contains("특수")){
                            Intent intent = new Intent(LoginActivity.this, SpecialActivity.class);
                            startActivity(intent);
                        }else if (JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").contains("영정")){
                            Intent intent = new Intent(LoginActivity.this, DeceasedImgActivity.class);
                            startActivity(intent);
                        }else if (JsonIsNullCheck(jsonObject,"CLASSIFICATION_NAME").length() == 0){
                            Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }else{
                        Intent intent = new Intent(LoginActivity.this, DisabledActivity.class);
                        startActivity(intent);
                    }


                }catch (JSONException e){

                }
            }else{
//                Toast.makeText(LoginActivity.this, "아이디가 존재하지않습니다.", Toast.LENGTH_SHORT).show();
            }
        }
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

            try{
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
                    new LoginNetWork().execute(edtLogin.getText().toString(),token);
                    mHandler.removeMessages(0);
                }
            }catch (NullPointerException e){
                token = FirebaseInstanceId.getInstance().getToken();
                mHandler.sendEmptyMessageDelayed(0,1000);
            }
        }
    };

    public void ipAddress(){
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();

                //네트워크 중에서 IP가 할당된 넘들에 대해서 뺑뺑이를 한 번 더 돕니다.
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {

                    InetAddress inetAddress = enumIpAddr.nextElement();

                    //네트워크에는 항상 Localhost 즉, 루프백(LoopBack)주소가 있으며, 우리가 원하는 것이 아닙니다.
                    //IP는 IPv6와 IPv4가 있습니다.
                    //IPv6의 형태 : fe80::64b9::c8dd:7003
                    //IPv4의 형태 : 123.234.123.123
                    //어떻게 나오는지는 찍어보세요.
                    if(inetAddress.isLoopbackAddress()) {
                        Log.i("IPAddress", intf.getDisplayName() + "(loopback) | " + inetAddress.getHostAddress());
                    }
                    else
                    {
                        Log.i("IPAddress", intf.getDisplayName() + " | " + inetAddress.getHostAddress());
                    }

                    //루프백이 아니고, IPv4가 맞다면 리턴~~~
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        txtIpAddress.setText(inetAddress.getHostAddress());
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
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
                Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
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
                Intent intent = new Intent(LoginActivity.this, ModeSelectActivity.class);
                startActivity(intent);
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login_next : {
//                Intent intent = new Intent(this, ModeSelectActivity.class);
//                startActivity(intent);

                if (edtLogin.getText().toString().length() != 0){
                    if(getNetworkState() != null && getNetworkState().isConnected()) {
                        mHandler.sendEmptyMessage(0);
//                        new LoginNetWork().execute(edtLogin.getText().toString(),"");
                    } else {
                        Toast.makeText(this, "네트워크상태를 체크해주세요.", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(this, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }

                break;
            }
            case R.id.linear_login : {
                inputMethodManager.hideSoftInputFromWindow(edtLogin.getWindowToken(), 0);
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

                wifiManager.setWifiEnabled(true);

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

                try {
                    Runtime.getRuntime().exec("am startservice -n com.google.wifisetup/.WifiSetupService -a WifiSetupService.Connect -e ssid " + format + " -e passphrase " + edtPassword.getText().toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String networkSSID = ssid;
                String networkPass = edtPassword.getText().toString();

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
        if (pref.getString("raspberryId","").length() != 0){
            edtLogin.setText(pref.getString("raspberryId",""));
        }
        mHandler1.sendEmptyMessage(0);
    }

    public Handler mHandler1 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (getNetworkState() != null && getNetworkState().isConnected()){
                if (pref.getString("raspberryId","").length() != 0){
                    mHandler.sendEmptyMessageDelayed(0,5000);
                    mHandler1.removeMessages(0);
                }else{
                    mHandler1.removeMessages(0);
                }
            }else{
                if (pref.getString("raspberryId","").length() != 0){
                    mHandler1.sendEmptyMessageDelayed(0,2000);
                }else{
                    mHandler1.removeMessages(0);
                }
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver( mUpdateReceiver);
        if (mHandler != null){
            mHandler.removeMessages(0);
        }

        if (mHandler1 != null){
            mHandler1.removeMessages(0);
        }

    }

    @Override
    public void onBackPressed() {

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
            }else if (message.equals("check")){
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
            }else{

            }
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
    protected void onDestroy() {
        super.onDestroy();
        if (mNetworkReceiver != null){
            unregisterNetworkChanges();
        }
    }

    private BroadcastReceiver mNetworkReceiver;

    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }
    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

}
