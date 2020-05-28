package com.kmw.dongsung.Activitys;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.kmw.dongsung.BuildConfig;

import com.bumptech.glide.Glide;
import com.kmw.dongsung.Commons.CaptureUtil;
import com.kmw.dongsung.Commons.Https.HttpClient;
import com.kmw.dongsung.Commons.NetworkChangeReceiver;
import com.kmw.dongsung.Commons.ServerUrl.Server;
import com.kmw.dongsung.Commons.Utils;
import com.kmw.dongsung.Func.GeneralViewPagerAdater;
import com.kmw.dongsung.Func.InfiniteFunc.InfinitePagerAdapter;
import com.kmw.dongsung.Func.InfiniteFunc.InfiniteViewPager;
import com.kmw.dongsung.Func.InternetGeneralViewPagerAdapter;
import com.kmw.dongsung.Func.SangGaPagerAdapter;
import com.kmw.dongsung.Func.TestGeneralViewPagerAdapter;
import com.kmw.dongsung.R;
import com.kmw.dongsung.Views.FitTextView;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
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

import static android.content.Intent.makeRestartActivityTask;
import static com.kmw.dongsung.Commons.Utils.Collapse;
import static com.kmw.dongsung.Commons.Utils.Expand;
import static com.kmw.dongsung.Commons.Utils.JsonIntIsNullCheck;
import static com.kmw.dongsung.Commons.Utils.JsonIsNullCheck;

public class InternetGeneralActivity extends Activity implements View.OnClickListener {

    private String TAG = "InternetGeneralActivity";
    public static InfiniteViewPager viewPager;
    InfinitePagerAdapter infinitePagerAdapter;
    InternetGeneralViewPagerAdapter adater;
    TestGeneralViewPagerAdapter adapterTest;
    GeneralViewPagerAdater adater1;
    RelativeLayout relaTop;
    TextView txtBottom;
    FitTextView txtTopTime;
    ImageView imgLogo;
    String intentData = "";
    public static RelativeLayout relaCaptureParent;

    ArrayList<String>                   titleArray = new ArrayList<String>();
    ArrayList<String>                   deceasedArray = new ArrayList<String>();
    ArrayList<String>                   imgPathArray = new ArrayList<String>();
    ArrayList<String>                   relationshipArray = new ArrayList<String>();
    ArrayList<ArrayList<String>>        namesArray = new ArrayList<ArrayList<String>>();
    ArrayList<ArrayList<String>>        relationArray = new ArrayList<ArrayList<String>>();
    ArrayList<String>                   arrowNoArray = new ArrayList<String>();
    ArrayList<String>                   religionBgArray = new ArrayList<String>();
    ArrayList<Float>                    familyTextSizeList = new ArrayList<>();

    ArrayList<String>                   changeCheckInArray = new ArrayList<String>();
    ArrayList<String>                   changeCheckOutArray = new ArrayList<String>();
    ArrayList<String>                   changeLocationArray = new ArrayList<String>();

    public static final String TIME_SERVER = "pool.ntp.org";
    Calendar calendar;
    Timer timer;
    int position = 0;
    Handler handler;
    Runnable Update;
    String success = "";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat hangulDateFormat = new SimpleDateFormat("MM월 dd일 HH시 mm분 ");
    SimpleDateFormat timeDateFormat = new SimpleDateFormat("HH:mm");
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    //hover
    LinearLayout linearHover;
    LinearLayout linearHoverVisible;

    private Button btnLogout,btnReboot,btnWifi,btnAuto,btnManual;
    private LinearLayout linearPopupVisible,linearHoverGone;
    private LinearLayout linearWifiPopupVisible;
    private ImageView imgWifiClose;
    private TextView txtVersion;
    private TextView txtSystemUpdate;

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
    ArrayList<String> wifiSSIDArray = new ArrayList<String>();

    ArrayList<String> divideImgPathArray = new ArrayList<String>();
    ArrayList<String> divideArray = new ArrayList<String>();
    String divisionImgPath = "";
    Typeface type = null;
    TextView txtTitle;
    InputMethodManager inputMethodManager;
    EditText editTextToken;
    String deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor;

    public static ImageView linPopupTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_general);

        pref = getSharedPreferences("dongsung",MODE_PRIVATE);
        editor = pref.edit();

        FindViewById();

        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//        wifi = manager.getActiveNetworkInfo();

        if (wifi.isConnected()){
            wifiManager.setWifiEnabled(true);
        }else{
            wifiManager.setWifiEnabled(true);
        }

    }

    void FindViewById(){
        viewPager = (InfiniteViewPager)findViewById(R.id.view_pager_internet_general);
        relaTop = (RelativeLayout)findViewById(R.id.rela_one_division_item_top);
        txtBottom = (TextView)findViewById(R.id.txt_general_bottom_text);
        txtTopTime = (FitTextView) findViewById(R.id.txt_general_top_time);
        imgLogo = (ImageView)findViewById(R.id.img_general_top_funeral_logo);
        txtTitle = (TextView)findViewById(R.id.txt_general_top_title);
        relaCaptureParent = (RelativeLayout)findViewById(R.id.rela_general_capture_parent);

        linPopupTest = (ImageView)findViewById(R.id.lin_general_popup_test);

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
        txtSystemUpdate = (TextView)findViewById(R.id.txt_hover_system_update);

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
                        break;
                    }

                    case MotionEvent.ACTION_HOVER_EXIT : {
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

    public class GeneralListNetWork extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpClient.Builder http = new HttpClient.Builder("POST", Server.generalListUrl());

            http.addOrReplace("statusPlate","true"                                                              );
            http.addOrReplace("eventAliveFlag","true"                                                           );
            http.addOrReplace("order","EXPOSURE ASC,APPELLATION ASC,ENTRANCE_ROOM_DT"                           );
            http.addOrReplace("raspberryConnectionNo",pref.getString("raspberryConnectionNo","")    );
            //            http.addOrReplace("order","EXPOSURE,RASPBERRY_CONNECTION_NO");
//            http.addOrReplace("statusPlateStyleBasic","true");
            HttpClient post = http.create();
            post.request();
            String body = post.getBody();

            Log.i(TAG, "GeneralListNetWork check : " + body);

            return body;
        }

        @Override
        protected void onPostExecute(String s) {

            titleArray = new ArrayList<String>();
            deceasedArray = new ArrayList<String>();
            imgPathArray = new ArrayList<String>();
            relationshipArray = new ArrayList<String>();
            namesArray = new ArrayList<ArrayList<String>>();
            relationArray = new ArrayList<ArrayList<String>>();
            arrowNoArray = new ArrayList<String>();
            changeCheckInArray = new ArrayList<String>();
            changeCheckOutArray = new ArrayList<String>();
            changeLocationArray = new ArrayList<String>();
            familyTextSizeList = new ArrayList<>();

            boolean multiMode = false;

            Log.i(TAG,"rpiNo : " + pref.getString("raspberryConnectionNo",""));

            if (handler != null){
                handler.removeMessages(0);
            }

            for (int i = 0; i < 10; i++){
                editor.putInt("array"+i,0);
            }
            editor.apply();

            if (s.length() != 0){
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArrayList = jsonObject.getJSONArray("binsoList");

                    Log.i(TAG,"binsoList : " + jsonArrayList.length());

                    if (jsonArrayList.length() == 0){
                        Intent intent = new Intent(InternetGeneralActivity.this,WaitingActivity.class);
                        startActivity(intent);
                    }else {
                        final JSONObject jsonObjectStyle = jsonObject.getJSONObject("style");

                        Log.i(TAG,"style : " + jsonObjectStyle.toString());

                        editor.putString("textStyle",JsonIsNullCheck(jsonObjectStyle,"FONT_TYPE"));
                        editor.apply();

                        if (JsonIsNullCheck(jsonObjectStyle,"FONT_TYPE").length() == 0){
                            type = Typeface.createFromAsset(getAssets(),"noto_sans_bold.ttf");
                        }else if (JsonIsNullCheck(jsonObjectStyle,"FONT_TYPE").equals("Noto Sans KR")){
                            type = Typeface.createFromAsset(getAssets(),"noto_sans_bold.ttf");
                        }else if (JsonIsNullCheck(jsonObjectStyle,"FONT_TYPE").equals("nanumMyeongjoBold")){
                            type = Typeface.createFromAsset(getAssets(),"nanum_myeongjo_bold.ttf");
                        }else if (JsonIsNullCheck(jsonObjectStyle,"FONT_TYPE").equals("unBatang")){
                            type = Typeface.createFromAsset(getAssets(),"un_batang.ttf");
                        }else if (JsonIsNullCheck(jsonObjectStyle,"FONT_TYPE").equals("unDotum")){
                            type = Typeface.createFromAsset(getAssets(),"undotum.ttf");
                        }else if (JsonIsNullCheck(jsonObjectStyle,"FONT_TYPE").equals("unGraphic")){
                            type = Typeface.createFromAsset(getAssets(),"un_graphic.ttf");
                        }else if (JsonIsNullCheck(jsonObjectStyle,"FONT_TYPE").equals("unGungseo")){
                            type = Typeface.createFromAsset(getAssets(),"un_gungseo.ttf");
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"NAME").length()!=0){
                            deceasedColor = JsonIsNullCheck(jsonObjectStyle,"NAME");
                        }else{
                            deceasedColor = "#000000";
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"STATUS").length()!=0){
                            roomNameColor = JsonIsNullCheck(jsonObjectStyle,"STATUS");
                        }else{
                            roomNameColor = "#000000";
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"CHIEF_MOURNER").length()!=0){
                            relationColor = JsonIsNullCheck(jsonObjectStyle,"CHIEF_MOURNER");
                        }else{
                            relationColor = "#000000";
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"ER_START").length()!=0){
                            checkInTitleColor = JsonIsNullCheck(jsonObjectStyle,"ER_START");
                        }else{
                            checkInTitleColor = "#000000";
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"ER_START_TIME").length()!=0){
                            checkInColor = JsonIsNullCheck(jsonObjectStyle,"ER_START_TIME");
                        }else{
                            checkInColor = "#000000";
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"CARRING_START").length()!=0){
                            checkOutTitleColor = JsonIsNullCheck(jsonObjectStyle,"CARRING_START");
                        }else{
                            checkOutTitleColor = "#000000";
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"CARRING_START_TIME").length()!=0){
                            checkOutColor = JsonIsNullCheck(jsonObjectStyle,"CARRING_START_TIME");
                        }else{
                            checkOutColor = "#000000";
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"BURIAL_PLOT").length()!=0){
                            locationTitleColor = JsonIsNullCheck(jsonObjectStyle,"BURIAL_PLOT");
                        }else{
                            locationTitleColor = "#000000";
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"BURIAL_PLOT_NAME").length()!=0){
                            locationColor = JsonIsNullCheck(jsonObjectStyle,"BURIAL_PLOT_NAME");
                        }else{
                            locationColor = "#000000";
                        }

                        txtTopTime.setTypeface(type);
                        txtBottom.setTypeface(type);
                        txtTitle.setTypeface(type);

                        if (JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT").length() != 0){
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
                            params.weight = 50;
                            relaTop.setLayoutParams(params);
                            txtBottom.setText(JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"));

                            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams)imgLogo.getLayoutParams();
                            params1.bottomMargin = 2;
                            imgLogo.setLayoutParams(params1);
                        }else{
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
                            params.weight = 100;
                            relaTop.setLayoutParams(params);
                            txtBottom.setText("");

                            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams)imgLogo.getLayoutParams();
                            params1.bottomMargin = 4;
                            imgLogo.setLayoutParams(params1);
                        }

                        int familySize = 0;
                        int familyPosition = 0;

                        String[] multiList = new String[]{};

                        ArrayList<String> connectNoList = new ArrayList<>();

                        ArrayList<Integer> multiFamilyLength = new ArrayList<>();

                        for (int i = 0; i < jsonArrayList.length(); i++){

                            JSONObject object = jsonArrayList.getJSONObject(i);

                            JSONArray jsonArrayFamily = object.getJSONArray("eventFamilyInfo");
                            JSONObject jsonObjectInfo = object.getJSONObject("eventInfo");

                            if (!jsonObjectInfo.isNull("MULTI_NAME")){
                                multiMode = true;
                                multiList = jsonObjectInfo.getString("MULTI_LIST").split(",");

                                if (JsonIsNullCheck(jsonObjectInfo,"BINSO_LIST").contains(JsonIsNullCheck(jsonObjectInfo,"RASPBERRY_CONNECTION_NO")) && !(connectNoList.contains(JsonIsNullCheck(jsonObjectInfo,"RASPBERRY_CONNECTION_NO")))){
                                    connectNoList.add(JsonIsNullCheck(jsonObjectInfo,"RASPBERRY_CONNECTION_NO"));
                                    ArrayList<String> names = new ArrayList<String>();
                                    ArrayList<String> relations = new ArrayList<String>();

                                    for (int j = 0; j < jsonArrayFamily.length(); j++){
                                        multiFamilyLength.add(jsonArrayFamily.length());
                                        JSONObject familyObject = jsonArrayFamily.getJSONObject(j);
                                        names.add(JsonIsNullCheck(familyObject,"NAMES").replace("^#$%&PB$@!",","));
                                        relations.add(JsonIsNullCheck(familyObject,"RELATION"));
                                    }

                                    namesArray.add(names);
                                    relationArray.add(relations);

                                    titleArray.add(JsonIsNullCheck(jsonObjectInfo,"APPELLATION"));
                                    familyTextSizeList.add(0f);
//                        deceasedArray.add("故 "+JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + "(" + JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + ") " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION"));

                                    String gender = JsonIsNullCheck(jsonObjectInfo,"DM_GENDER");

                                    if (gender.equals("1")){
                                        gender = "남";
                                    }else if (gender.equals("2")){
                                        gender = "여";
                                    }

                                    if (JsonIsNullCheck(jsonObjectInfo,"DM_POSITION").length() != 0){
                                        if (JsonIsNullCheck(jsonObjectInfo,"DM_GENDER").length() != 0){
                                            if (JsonIsNullCheck(jsonObjectInfo,"DM_AGE").length() != 0){
                                                deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION") + " " + "(" + gender + ", "+ JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + "세)");
                                            }else{
                                                deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION") + " " + "(" + gender + ")");
                                            }
                                        }else{
                                            if (JsonIsNullCheck(jsonObjectInfo,"DM_AGE").length() != 0){
                                                deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION") + " " + "(" + JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + "세)");
                                            }else{
                                                deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION"));
                                            }
                                        }
                                    }else{
                                        if (JsonIsNullCheck(jsonObjectInfo,"DM_GENDER").length() != 0){
                                            if (JsonIsNullCheck(jsonObjectInfo,"DM_AGE").length() != 0){
                                                deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + "(" + gender + ", "+ JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + "세)");
                                            }else{
                                                deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + "(" + gender + ")");
                                            }
                                        }else{
                                            if (JsonIsNullCheck(jsonObjectInfo,"DM_AGE").length() != 0){
                                                deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + "(" + JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + "세)");
                                            }else{
                                                deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME"));
                                            }
                                        }
                                    }

                                    if (JsonIntIsNullCheck(jsonObjectInfo,"CARRYING_YN") == 0){
                                        changeCheckOutArray.add(" - ");
                                    }

                                    try {
                                        if (JsonIsNullCheck(jsonObjectInfo,"ENTRANCE_ROOM_NO").length() == 0){
                                            changeCheckInArray.add(" - ");
                                        }else{
                                            changeCheckInArray.add(hangulDateFormat.format(simpleDateFormat.parse(JsonIsNullCheck(jsonObjectInfo,"ENTRANCE_ROOM_START_DT").replace("T"," "))));
                                        }

                                        if (JsonIntIsNullCheck(jsonObjectInfo,"CARRYING_YN") == 1){
                                            changeCheckOutArray.add(hangulDateFormat.format(simpleDateFormat.parse(JsonIsNullCheck(jsonObjectInfo,"CARRYING_DT").replace("T"," "))));
                                        }else {
                                            changeCheckOutArray.add("");
                                        }
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    relationshipArray.add("");
                                    changeLocationArray.add(JsonIsNullCheck(jsonObjectInfo,"BURIAL_PLOT_NAME"));
                                    imgPathArray.add(JsonIsNullCheck(jsonObjectInfo,"DM_PHOTO"));
                                    arrowNoArray.add(JsonIsNullCheck(jsonObjectInfo,"ARROW_NO"));
                                    religionBgArray.add(JsonIsNullCheck(jsonObjectInfo,"STATUS_PLATE_BG"));
                                }
                            }else{
                                ArrayList<String> names = new ArrayList<String>();
                                ArrayList<String> relations = new ArrayList<String>();

                                for (int j = 0; j < jsonArrayFamily.length(); j++){
                                    JSONObject familyObject = jsonArrayFamily.getJSONObject(j);

                                    names.add(JsonIsNullCheck(familyObject,"NAMES").replace("^#$%&PB$@!",","));
                                    relations.add(JsonIsNullCheck(familyObject,"RELATION"));
                                }

                                if (familySize < jsonArrayFamily.length()){
                                    familySize = jsonArrayFamily.length();
                                    familyPosition = i;
                                }else{

                                }

                                namesArray.add(names);
                                relationArray.add(relations);

                                titleArray.add(JsonIsNullCheck(jsonObjectInfo,"APPELLATION"));
                                familyTextSizeList.add(0f);
//                        deceasedArray.add("故 "+JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + "(" + JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + ") " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION"));

                                String gender = JsonIsNullCheck(jsonObjectInfo,"DM_GENDER");

                                if (gender.equals("1")){
                                    gender = "남";
                                }else if (gender.equals("2")){
                                    gender = "여";
                                }

                                if (JsonIsNullCheck(jsonObjectInfo,"DM_POSITION").length() != 0){
                                    if (JsonIsNullCheck(jsonObjectInfo,"DM_GENDER").length() != 0){
                                        if (JsonIsNullCheck(jsonObjectInfo,"DM_AGE").length() != 0){
                                            deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION") + " " + "(" + gender + ", "+ JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + "세)");
                                        }else{
                                            deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION") + " " + "(" + gender + ")");
                                        }
                                    }else{
                                        if (JsonIsNullCheck(jsonObjectInfo,"DM_AGE").length() != 0){
                                            deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION") + " " + "(" + JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + "세)");
                                        }else{
                                            deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION"));
                                        }
                                    }
                                }else{
                                    if (JsonIsNullCheck(jsonObjectInfo,"DM_GENDER").length() != 0){
                                        if (JsonIsNullCheck(jsonObjectInfo,"DM_AGE").length() != 0){
                                            deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + "(" + gender + ", "+ JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + "세)");
                                        }else{
                                            deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + "(" + gender + ")");
                                        }
                                    }else{
                                        if (JsonIsNullCheck(jsonObjectInfo,"DM_AGE").length() != 0){
                                            deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + "(" + JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + "세)");
                                        }else{
                                            deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME"));
                                        }
                                    }
                                }

                                if (JsonIntIsNullCheck(jsonObjectInfo,"CARRYING_YN") == 0){
                                    changeCheckOutArray.add(" - ");
                                }

                                try {
                                    if (JsonIsNullCheck(jsonObjectInfo,"ENTRANCE_ROOM_NO").length() == 0){
                                        changeCheckInArray.add(" - ");
                                    }else{
                                        changeCheckInArray.add(hangulDateFormat.format(simpleDateFormat.parse(JsonIsNullCheck(jsonObjectInfo,"ENTRANCE_ROOM_START_DT").replace("T"," "))));
                                    }

                                    if (JsonIntIsNullCheck(jsonObjectInfo,"CARRYING_YN") == 1){
                                        changeCheckOutArray.add(hangulDateFormat.format(simpleDateFormat.parse(JsonIsNullCheck(jsonObjectInfo,"CARRYING_DT").replace("T"," "))));
                                    }else {
                                        changeCheckOutArray.add("");
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                relationshipArray.add("");
                                changeLocationArray.add(JsonIsNullCheck(jsonObjectInfo,"BURIAL_PLOT_NAME"));
                                imgPathArray.add(JsonIsNullCheck(jsonObjectInfo,"DM_PHOTO"));
                                arrowNoArray.add(JsonIsNullCheck(jsonObjectInfo,"ARROW_NO"));
                                religionBgArray.add(JsonIsNullCheck(jsonObjectInfo,"STATUS_PLATE_BG"));
                            }
                        }

                        if (JsonIntIsNullCheck(jsonObjectStyle,"DIVISION_MODE") == 1 || JsonIntIsNullCheck(jsonObjectStyle,"DIVISION_MODE") == 0 ){
                            if (titleArray.size() == 3){
                                for (int q = 0; q < divideArray.size(); q++){
                                    if (divideArray.get(q).contains("4")){
                                        divisionImgPath = divideImgPathArray.get(q);
                                    }
                                }
                            }else if (titleArray.size() == 5){
                                for (int q = 0; q < divideArray.size(); q++){
                                    if (divideArray.get(q).contains("6")){
                                        divisionImgPath = divideImgPathArray.get(q);
                                    }
                                }
                            }else if (titleArray.size() > 6){
                                for (int q = 0; q < divideArray.size(); q++){
                                    if (divideArray.get(q).contains("8")){
                                        divisionImgPath = divideImgPathArray.get(q);
                                    }
                                }
                            }
                        }else{
                            for (int q = 0; q < divideArray.size(); q++){
                                if (divideArray.get(q).contains(JsonIsNullCheck(jsonObjectStyle,"DIVISION"))){
                                    divisionImgPath = divideImgPathArray.get(q);
                                }
                            }
                        }

                        if (jsonArrayList.length() > 0){

                            if (multiMode){

                                Log.i(TAG,"multi mode");

                                int a = multiList.length; //2
                                int b = 0;

                                for (int i = 0; i < titleArray.size(); i++){
                                    for (int j = b; j < a; j++){
                                        editor.putInt("array"+j,pref.getInt("array"+j,0) + 1);
                                        //1
                                        editor.apply();

                                        if (j == (a-1)){
                                            b = 0;
                                            break;
                                        }else{
                                            b++;
                                            break;
                                        }
                                    }
                                }

                                int divisionMulti = 0;

                                String statePlateNo = "";

                                if (pref.getInt("array0",0) == 1){
                                    divisionMulti = 1;

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#000000";
                                    checkOutColor = "#000000";
                                    locationColor = "#000000";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "43";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "50";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "56";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "62";
                                    }

                                }else if (pref.getInt("array0",0) == 2){
                                    divisionMulti = 2;

                                    for (int q = 0; q < divideArray.size(); q++){
                                        if (divideArray.get(q).contains("2")){
                                            divisionImgPath = divideImgPathArray.get(q);
                                        }
                                    }

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#000000";
                                    checkOutColor = "#000000";
                                    locationColor = "#000000";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "44";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "51";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "57";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "63";
                                    }
                                }else if (pref.getInt("array0",0) < 5){

                                    divisionMulti = 4;

                                    for (int q = 0; q < divideArray.size(); q++){
                                        if (divideArray.get(q).contains("4")){
                                            divisionImgPath = divideImgPathArray.get(q);
                                        }
                                    }

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#000000";
                                    checkOutColor = "#000000";
                                    locationColor = "#000000";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "45";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "52";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "58";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "64";
                                    }
                                }else if (pref.getInt("array0",0) < 7){
                                    divisionMulti = 6;

                                    for (int q = 0; q < divideArray.size(); q++){
                                        if (divideArray.get(q).contains("6")){
                                            divisionImgPath = divideImgPathArray.get(q);
                                        }
                                    }

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#ffffff";
                                    checkOutColor = "#ffffff";
                                    locationColor = "#ffffff";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "46";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "53";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "59";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "65";
                                    }
                                }else {
                                    divisionMulti = 8;

                                    for (int q = 0; q < divideArray.size(); q++){
                                        if (divideArray.get(q).contains("8")){
                                            divisionImgPath = divideImgPathArray.get(q);
                                        }
                                    }

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#ffffff";
                                    checkOutColor = "#ffffff";
                                    locationColor = "#ffffff";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "47";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "54";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "60";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "66";
                                    }
                                }

                                ArrayList<String> titleTest = new ArrayList<>();
                                ArrayList<String> deceasedTest = new ArrayList<>();
                                ArrayList<String> relationShipTest = new ArrayList<>();
                                ArrayList<String> changeCheckInTest = new ArrayList<>();
                                ArrayList<String> changeCheckOutTest = new ArrayList<>();
                                ArrayList<String> changeLocationTest = new ArrayList<>();
                                ArrayList<String> imgPathTest = new ArrayList<>();
                                ArrayList<ArrayList<String>> namesTest = new ArrayList<>();
                                ArrayList<ArrayList<String>> relationTest = new ArrayList<>();
                                ArrayList<String> arrowNoTest = new ArrayList<>();
                                ArrayList<String> religionBgTest = new ArrayList<>();

                                for (int i = 0; i < multiList.length; i++){
                                    if (multiList[i].equals(pref.getString("raspberryConnectionNo",""))){
                                        if (i == 0){
                                            if (pref.getInt("array"+i,0) != 0){

                                                familySize = 0;
                                                familyPosition = 0;

                                                ArrayList<Integer> textLength = new ArrayList<>();
                                                int textHJHH = 0;

                                                for (int j = 0; j < pref.getInt("array"+i,0); j++){
                                                    titleTest.add(titleArray.get(j));
                                                    deceasedTest.add(deceasedArray.get(j));
                                                    relationShipTest.add(relationshipArray.get(j));
                                                    changeCheckInTest.add(changeCheckInArray.get(j));
                                                    changeCheckOutTest.add(changeCheckOutArray.get(j));
                                                    changeLocationTest.add(changeLocationArray.get(j));
                                                    imgPathTest.add(imgPathArray.get(j));
                                                    namesTest.add(namesArray.get(j));
                                                    relationTest.add(relationArray.get(j));
                                                    arrowNoTest.add(arrowNoArray.get(j));
                                                    religionBgTest.add(religionBgArray.get(j));

                                                    for (int x = 0; x < relationArray.get(j).size(); x++){
                                                        Log.i(TAG,"bbbb : " + relationArray.get(j).get(x));
                                                        textHJHH = textHJHH + relationArray.get(j).get(x).length();
                                                    }
                                                    textLength.add(textHJHH);
                                                    textHJHH = 0;
                                                }

                                                for (int x = 0; x < textLength.size(); x++){
                                                    if (familySize < textLength.get(x)){
                                                        familyPosition = x;
                                                        familySize = textLength.get(x);
                                                    }
                                                }

                                            }else{
                                                Intent intent = new Intent(InternetGeneralActivity.this,WaitingActivity.class);
                                                startActivity(intent);
                                            }
                                        }else {
                                            if (pref.getInt("array"+i,0) != 0){

                                                new GeneralListNetWork1().execute(multiList[0]);

                                                this.cancel(true);

                                                return;

                                            }else{
                                                Intent intent = new Intent(InternetGeneralActivity.this,WaitingActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    }
                                }

                                if (titleTest.size() != 0){

                                    adater = new InternetGeneralViewPagerAdapter(getLayoutInflater(),InternetGeneralActivity.this,
                                            divisionMulti,JsonIntIsNullCheck(jsonObjectStyle,"ARROW_FLAG"),
                                            familySize,familyPosition,pref,JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"),divisionImgPath,type
                                            ,deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor,statePlateNo);

//                                    for (int i = 0; i < titleTest.size(); i++){

                                    Log.i(TAG,"titleTest : " + titleTest.size());

                                    adater.addItem(titleTest,deceasedTest,relationShipTest,changeCheckInTest,changeCheckOutTest,changeLocationTest,imgPathTest,namesTest,relationTest,arrowNoTest,religionBgTest,familyTextSizeList);
//                                    }

                                    infinitePagerAdapter = new InfinitePagerAdapter(adater);
                                    viewPager.setAdapter(infinitePagerAdapter);

                                }else{
                                    Log.i(TAG,"titleTest : " + titleTest.size());
                                }
                            }else if (JsonIntIsNullCheck(jsonObjectStyle,"DIVISION_MODE") == 1 || JsonIntIsNullCheck(jsonObjectStyle,"DIVISION_MODE") == 0){
                                String statePlateNo = "";

                                deceasedColor = "#ffffff";
                                roomNameColor = "#ffffff";
                                relationColor = "#000000";
                                checkInTitleColor = "#ffffff";
                                checkOutTitleColor = "#ffffff";
                                locationTitleColor = "#ffffff";
                                checkInColor = "#000000";
                                checkOutColor = "#000000";
                                locationColor = "#000000";

                                if (titleArray.size() == 1){
                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "43";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "50";

                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "56";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "62";
                                    }

                                    adater = new InternetGeneralViewPagerAdapter(getLayoutInflater(),InternetGeneralActivity.this,
                                            1,JsonIntIsNullCheck(jsonObjectStyle,"ARROW_FLAG"),
                                            familySize,familyPosition,pref,JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"),divisionImgPath,type
                                            ,deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor,statePlateNo);

                                    adater.addItem(titleArray,deceasedArray,relationshipArray,changeCheckInArray,changeCheckOutArray,changeLocationArray,imgPathArray,namesArray,relationArray,arrowNoArray,religionBgArray,familyTextSizeList);

                                }else if (titleArray.size() == 2){

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#000000";
                                    checkOutColor = "#000000";
                                    locationColor = "#000000";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "44";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "51";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "57";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "63";
                                    }

                                    adater = new InternetGeneralViewPagerAdapter(getLayoutInflater(),InternetGeneralActivity.this,
                                            2,JsonIntIsNullCheck(jsonObjectStyle,"ARROW_FLAG"),
                                            familySize,familyPosition,pref,JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"),divisionImgPath,type
                                            ,deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor,statePlateNo);

                                    adater.addItem(titleArray,deceasedArray,relationshipArray,changeCheckInArray,changeCheckOutArray,changeLocationArray,imgPathArray,namesArray,relationArray,arrowNoArray,religionBgArray,familyTextSizeList);

                                }else if (titleArray.size() < 5){

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#000000";
                                    checkOutColor = "#000000";
                                    locationColor = "#000000";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "45";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "52";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "58";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "64";
                                    }

                                    adater = new InternetGeneralViewPagerAdapter(getLayoutInflater(),InternetGeneralActivity.this,
                                            4,JsonIntIsNullCheck(jsonObjectStyle,"ARROW_FLAG"),
                                            familySize,familyPosition,pref,JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"),divisionImgPath,type
                                            ,deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor,statePlateNo);

                                    adater.addItem(titleArray,deceasedArray,relationshipArray,changeCheckInArray,changeCheckOutArray,changeLocationArray,imgPathArray,namesArray,relationArray,arrowNoArray,religionBgArray,familyTextSizeList);

                                }else if (titleArray.size() < 7){

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#ffffff";
                                    checkOutColor = "#ffffff";
                                    locationColor = "#ffffff";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "46";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "53";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "59";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "65";
                                    }

                                    adater = new InternetGeneralViewPagerAdapter(getLayoutInflater(),InternetGeneralActivity.this,
                                            6,JsonIntIsNullCheck(jsonObjectStyle,"ARROW_FLAG"),
                                            familySize,familyPosition,pref,JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"),divisionImgPath,type
                                            ,deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor,statePlateNo);
//                                    for (int i = 0; i < titleArray.size(); i++){
                                        adater.addItem(titleArray,deceasedArray,relationshipArray,changeCheckInArray,changeCheckOutArray,changeLocationArray,imgPathArray,namesArray,relationArray,arrowNoArray,religionBgArray,familyTextSizeList);
//                                    }
                                }else if (titleArray.size() >= 7){

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#ffffff";
                                    checkOutColor = "#ffffff";
                                    locationColor = "#ffffff";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "47";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "54";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "60";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "66";
                                    }

                                    adater = new InternetGeneralViewPagerAdapter(getLayoutInflater(),InternetGeneralActivity.this,
                                            8,JsonIntIsNullCheck(jsonObjectStyle,"ARROW_FLAG"),
                                            familySize,familyPosition,pref,JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"),divisionImgPath,type
                                            ,deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor,statePlateNo);
                                    for (int i = 0; i < titleArray.size(); i++){
                                        adater.addItem(titleArray,deceasedArray,relationshipArray,changeCheckInArray,changeCheckOutArray,changeLocationArray,imgPathArray,namesArray,relationArray,arrowNoArray,religionBgArray,familyTextSizeList);
                                    }
                                }

                                infinitePagerAdapter = new InfinitePagerAdapter(adater);
                                viewPager.setAdapter(infinitePagerAdapter);

                            }else{
//                                adater = new InternetGeneralViewPagerAdapter(getLayoutInflater(),InternetGeneralActivity.this,
//                                        JsonIntIsNullCheck(jsonObjectStyle,"DIVISION"),JsonIntIsNullCheck(jsonObjectStyle,"ARROW_FLAG"),
//                                        familySize,familyPosition,pref,JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"),divisionImgPath,type
//                                        ,deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor,JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO"));
//
//                                    adater.addItem(titleArray,deceasedArray,relationshipArray,changeCheckInArray,changeCheckOutArray,changeLocationArray,imgPathArray,namesArray,relationArray,arrowNoArray,religionBgArray);

                                adater = new InternetGeneralViewPagerAdapter(getLayoutInflater(),InternetGeneralActivity.this,
                                        JsonIntIsNullCheck(jsonObjectStyle,"DIVISION"),JsonIntIsNullCheck(jsonObjectStyle,"ARROW_FLAG"),
                                        familySize,familyPosition,pref,JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"),divisionImgPath,type
                                        ,deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor,JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO"));

                                for (int i = 0; i < titleArray.size(); i++){
                                    adater.addItem(titleArray,deceasedArray,relationshipArray,changeCheckInArray,changeCheckOutArray,changeLocationArray,imgPathArray,namesArray,relationArray,arrowNoArray,religionBgArray,familyTextSizeList);
                                }

                                infinitePagerAdapter = new InfinitePagerAdapter(adater);
                                viewPager.setAdapter(infinitePagerAdapter);
                            }

//                            infinitePagerAdapter = new InfinitePagerAdapter(adapterTest);
//                            viewPager.setAdapter(infinitePagerAdapter);

                            position = 0;

                            boolean finalMultiMode = multiMode;

                            handler = new Handler(){
                                public void handleMessage(Message msg) {
                                    if (finalMultiMode){

                                    }else{
                                        if (JsonIntIsNullCheck(jsonObjectStyle,"DIVISION_MODE") == 1 || JsonIntIsNullCheck(jsonObjectStyle,"DIVISION_MODE") == 0){
                                            if (titleArray.size() < 9){
                                                Log.i(TAG,"remove 1");
                                                handler.removeMessages(0);
                                            }else{
                                                Log.i(TAG,"handle 1");
                                                if (JsonIsNullCheck(jsonObjectStyle,"SLIDE_EFFECT").equals("0")){
                                                    viewPager.setCurrentItem(position++,false);
                                                }else{
                                                    viewPager.setCurrentItem(position++,true);
                                                }
                                                handler.sendEmptyMessageDelayed(0,(JsonIntIsNullCheck(jsonObjectStyle,"SLIDE_SEC") * 1000));
                                            }
                                        }else{
                                            if (JsonIntIsNullCheck(jsonObjectStyle,"DIVISION") < titleArray.size()){
                                                Log.i(TAG,"handle 2");
                                                Log.i(TAG,"position : " + position);
                                                if (position == 0){
                                                    linPopupTest.setVisibility(View.VISIBLE);
                                                }else{
                                                    linPopupTest.setVisibility(View.GONE);
                                                }
                                                if (JsonIsNullCheck(jsonObjectStyle,"SLIDE_EFFECT").equals("0")){
                                                    viewPager.setCurrentItem(position++,false);
                                                }else{
                                                    viewPager.setCurrentItem(position++,true);
                                                }

                                                handler.sendEmptyMessageDelayed(0,(JsonIntIsNullCheck(jsonObjectStyle,"SLIDE_SEC") * 1000));
                                            }else{
                                                Log.i(TAG,"remove 2");
                                                handler.removeMessages(0);
                                            }
                                        }
                                    }
                                }
                            };

                            if (!multiMode){
                                handler.sendEmptyMessage(0);
                            }

                            txtBottom.setFocusable(true);
                            txtBottom.requestFocus();
                            txtBottom.setFocusableInTouchMode(true);
                        }
                    }
                }catch (JSONException e){

                }
            }else{
                Intent intent = new Intent(InternetGeneralActivity.this,WaitingActivity.class);
                startActivity(intent);
            }
        }
    }

    public class GeneralListNetWork1 extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpClient.Builder http = new HttpClient.Builder("POST", Server.generalListUrl());

            http.addOrReplace("statusPlate","true");
            http.addOrReplace("eventAliveFlag","true");
//            http.addOrReplace("order","EXPOSURE,RASPBERRY_CONNECTION_NO");
            http.addOrReplace("order","EXPOSURE ASC,APPELLATION ASC,ENTRANCE_ROOM_DT");
//            http.addOrReplace("statusPlateStyleBasic","true");
            http.addOrReplace("raspberryConnectionNo",strings[0]);

            HttpClient post = http.create();
            post.request();
            String body = post.getBody();

            Log.i(TAG, "GeneralListNetWork check : " + body);

            return body;
        }

        @Override
        protected void onPostExecute(String s) {

            titleArray = new ArrayList<String>();
            deceasedArray = new ArrayList<String>();
            imgPathArray = new ArrayList<String>();
            relationshipArray = new ArrayList<String>();
            namesArray = new ArrayList<ArrayList<String>>();
            relationArray = new ArrayList<ArrayList<String>>();
            arrowNoArray = new ArrayList<String>();
            changeCheckInArray = new ArrayList<String>();
            changeCheckOutArray = new ArrayList<String>();
            changeLocationArray = new ArrayList<String>();
            familyTextSizeList = new ArrayList<>();

            boolean multiMode = false;

            Log.i(TAG,"rpiNo : " + pref.getString("raspberryConnectionNo",""));

            if (handler != null){
                handler.removeMessages(0);
            }

            if (s.length() != 0){
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArrayList = jsonObject.getJSONArray("binsoList");

                    Log.i(TAG,"binsoList : " + jsonArrayList.length());

                    if (jsonArrayList.length() == 0){
                        Intent intent = new Intent(InternetGeneralActivity.this,WaitingActivity.class);
                        startActivity(intent);
                    }else {
                        final JSONObject jsonObjectStyle = jsonObject.getJSONObject("style");

                        editor.putString("textStyle",JsonIsNullCheck(jsonObjectStyle,"FONT_TYPE"));
                        editor.apply();

                        if (JsonIsNullCheck(jsonObjectStyle,"FONT_TYPE").length() == 0){
                            type = Typeface.createFromAsset(getAssets(),"noto_sans_bold.ttf");
                        }else if (JsonIsNullCheck(jsonObjectStyle,"FONT_TYPE").equals("Noto Sans KR")){
                            type = Typeface.createFromAsset(getAssets(),"noto_sans_bold.ttf");
                        }else if (JsonIsNullCheck(jsonObjectStyle,"FONT_TYPE").equals("nanumMyeongjoBold")){
                            type = Typeface.createFromAsset(getAssets(),"nanum_myeongjo_bold.ttf");
                        }else if (JsonIsNullCheck(jsonObjectStyle,"FONT_TYPE").equals("unBatang")){
                            type = Typeface.createFromAsset(getAssets(),"un_batang.ttf");
                        }else if (JsonIsNullCheck(jsonObjectStyle,"FONT_TYPE").equals("unDotum")){
                            type = Typeface.createFromAsset(getAssets(),"undotum.ttf");
                        }else if (JsonIsNullCheck(jsonObjectStyle,"FONT_TYPE").equals("unGraphic")){
                            type = Typeface.createFromAsset(getAssets(),"un_graphic.ttf");
                        }else if (JsonIsNullCheck(jsonObjectStyle,"FONT_TYPE").equals("unGungseo")){
                            type = Typeface.createFromAsset(getAssets(),"un_gungseo.ttf");
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"NAME").length()!=0){
                            deceasedColor = JsonIsNullCheck(jsonObjectStyle,"NAME");
                        }else{
                            deceasedColor = "#000000";
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"STATUS").length()!=0){
                            roomNameColor = JsonIsNullCheck(jsonObjectStyle,"STATUS");
                        }else{
                            roomNameColor = "#000000";
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"CHIEF_MOURNER").length()!=0){
                            relationColor = JsonIsNullCheck(jsonObjectStyle,"CHIEF_MOURNER");
                        }else{
                            relationColor = "#000000";
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"ER_START").length()!=0){
                            checkInTitleColor = JsonIsNullCheck(jsonObjectStyle,"ER_START");
                        }else{
                            checkInTitleColor = "#000000";
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"ER_START_TIME").length()!=0){
                            checkInColor = JsonIsNullCheck(jsonObjectStyle,"ER_START_TIME");
                        }else{
                            checkInColor = "#000000";
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"CARRING_START").length()!=0){
                            checkOutTitleColor = JsonIsNullCheck(jsonObjectStyle,"CARRING_START");
                        }else{
                            checkOutTitleColor = "#000000";
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"CARRING_START_TIME").length()!=0){
                            checkOutColor = JsonIsNullCheck(jsonObjectStyle,"CARRING_START_TIME");
                        }else{
                            checkOutColor = "#000000";
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"BURIAL_PLOT").length()!=0){
                            locationTitleColor = JsonIsNullCheck(jsonObjectStyle,"BURIAL_PLOT");
                        }else{
                            locationTitleColor = "#000000";
                        }

                        if (JsonIsNullCheck(jsonObjectStyle,"BURIAL_PLOT_NAME").length()!=0){
                            locationColor = JsonIsNullCheck(jsonObjectStyle,"BURIAL_PLOT_NAME");
                        }else{
                            locationColor = "#000000";
                        }

                        txtTopTime.setTypeface(type);
                        txtBottom.setTypeface(type);
                        txtTitle.setTypeface(type);

                        if (JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT").length() != 0){
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
                            params.weight = 50;
                            relaTop.setLayoutParams(params);
                            txtBottom.setText(JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"));

                            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams)imgLogo.getLayoutParams();
                            params1.bottomMargin = 2;
                            imgLogo.setLayoutParams(params1);
                        }else{
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
                            params.weight = 100;
                            relaTop.setLayoutParams(params);
                            txtBottom.setText("");

                            RelativeLayout.LayoutParams params1 = (RelativeLayout.LayoutParams)imgLogo.getLayoutParams();
                            params1.bottomMargin = 4;
                            imgLogo.setLayoutParams(params1);
                        }

                        int familySize = 0;
                        int familyPosition = 0;

                        String[] multiList = new String[]{};
                        ArrayList<String> connectNoList = new ArrayList<>();
                        ArrayList<Integer> multiFamilyLength = new ArrayList<>();

                        for (int i = 0; i < jsonArrayList.length(); i++){
                            JSONObject object = jsonArrayList.getJSONObject(i);

                            Log.i(TAG,"binsoList : " + object.toString());

                            JSONArray jsonArrayFamily = object.getJSONArray("eventFamilyInfo");
                            JSONObject jsonObjectInfo = object.getJSONObject("eventInfo");

                            if (!jsonObjectInfo.isNull("MULTI_NAME")){
                                multiMode = true;
                                multiList = jsonObjectInfo.getString("MULTI_LIST").split(",");

                                if (JsonIsNullCheck(jsonObjectInfo,"BINSO_LIST").contains(JsonIsNullCheck(jsonObjectInfo,"RASPBERRY_CONNECTION_NO")) && !(connectNoList.contains(JsonIsNullCheck(jsonObjectInfo,"RASPBERRY_CONNECTION_NO")))){
                                    connectNoList.add(JsonIsNullCheck(jsonObjectInfo,"RASPBERRY_CONNECTION_NO"));
                                    ArrayList<String> names = new ArrayList<String>();
                                    ArrayList<String> relations = new ArrayList<String>();

                                    for (int j = 0; j < jsonArrayFamily.length(); j++){
                                        JSONObject familyObject = jsonArrayFamily.getJSONObject(j);
                                        multiFamilyLength.add(jsonArrayFamily.length());
                                        Log.i(TAG,"aaaaa : " + jsonArrayFamily.get(j) + " i : " + j);
                                        names.add(JsonIsNullCheck(familyObject,"NAMES").replace("^#$%&PB$@!",","));
                                        relations.add(JsonIsNullCheck(familyObject,"RELATION"));
                                    }

                                    namesArray.add(names);
                                    relationArray.add(relations);

                                    titleArray.add(JsonIsNullCheck(jsonObjectInfo,"APPELLATION"));
                                    familyTextSizeList.add(0f);
//                        deceasedArray.add("故 "+JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + "(" + JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + ") " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION"));

                                    String gender = JsonIsNullCheck(jsonObjectInfo,"DM_GENDER");

                                    if (gender.equals("1")){
                                        gender = "남";
                                    }else if (gender.equals("2")){
                                        gender = "여";
                                    }

                                    if (JsonIsNullCheck(jsonObjectInfo,"DM_POSITION").length() != 0){
                                        if (JsonIsNullCheck(jsonObjectInfo,"DM_GENDER").length() != 0){
                                            if (JsonIsNullCheck(jsonObjectInfo,"DM_AGE").length() != 0){
                                                deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION") + " " + "(" + gender + ", "+ JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + "세)");
                                            }else{
                                                deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION") + " " + "(" + gender + ")");
                                            }
                                        }else{
                                            if (JsonIsNullCheck(jsonObjectInfo,"DM_AGE").length() != 0){
                                                deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION") + " " + "(" + JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + "세)");
                                            }else{
                                                deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION"));
                                            }
                                        }
                                    }else{
                                        if (JsonIsNullCheck(jsonObjectInfo,"DM_GENDER").length() != 0){
                                            if (JsonIsNullCheck(jsonObjectInfo,"DM_AGE").length() != 0){
                                                deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + "(" + gender + ", "+ JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + "세)");
                                            }else{
                                                deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + "(" + gender + ")");
                                            }
                                        }else{
                                            if (JsonIsNullCheck(jsonObjectInfo,"DM_AGE").length() != 0){
                                                deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + "(" + JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + "세)");
                                            }else{
                                                deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME"));
                                            }
                                        }
                                    }

                                    if (JsonIntIsNullCheck(jsonObjectInfo,"CARRYING_YN") == 0){
                                        changeCheckOutArray.add(" - ");
                                    }

                                    try {
                                        if (JsonIsNullCheck(jsonObjectInfo,"ENTRANCE_ROOM_NO").length() == 0){
                                            changeCheckInArray.add(" - ");
                                        }else{
                                            changeCheckInArray.add(hangulDateFormat.format(simpleDateFormat.parse(JsonIsNullCheck(jsonObjectInfo,"ENTRANCE_ROOM_START_DT").replace("T"," "))));
                                        }

                                        if (JsonIntIsNullCheck(jsonObjectInfo,"CARRYING_YN") == 1){
                                            changeCheckOutArray.add(hangulDateFormat.format(simpleDateFormat.parse(JsonIsNullCheck(jsonObjectInfo,"CARRYING_DT").replace("T"," "))));
                                        }else {
                                            changeCheckOutArray.add("");
                                        }
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }

                                    relationshipArray.add("");
                                    changeLocationArray.add(JsonIsNullCheck(jsonObjectInfo,"BURIAL_PLOT_NAME"));
                                    imgPathArray.add(JsonIsNullCheck(jsonObjectInfo,"DM_PHOTO"));
                                    arrowNoArray.add(JsonIsNullCheck(jsonObjectInfo,"ARROW_NO"));
                                    religionBgArray.add(JsonIsNullCheck(jsonObjectInfo,"STATUS_PLATE_BG"));
                                }
                            }else{
                                ArrayList<String> names = new ArrayList<String>();
                                ArrayList<String> relations = new ArrayList<String>();

                                for (int j = 0; j < jsonArrayFamily.length(); j++){
                                    JSONObject familyObject = jsonArrayFamily.getJSONObject(j);

                                    names.add(JsonIsNullCheck(familyObject,"NAMES").replace("^#$%&PB$@!",","));
                                    relations.add(JsonIsNullCheck(familyObject,"RELATION"));
                                }

                                if (familySize < jsonArrayFamily.length()){
                                    familySize = jsonArrayFamily.length();
                                    familyPosition = i;
                                }else{

                                }

                                namesArray.add(names);
                                relationArray.add(relations);

                                titleArray.add(JsonIsNullCheck(jsonObjectInfo,"APPELLATION"));
                                familyTextSizeList.add(0f);
//                        deceasedArray.add("故 "+JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + "(" + JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + ") " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION"));

                                String gender = JsonIsNullCheck(jsonObjectInfo,"DM_GENDER");

                                if (gender.equals("1")){
                                    gender = "남";
                                }else if (gender.equals("2")){
                                    gender = "여";
                                }

                                if (JsonIsNullCheck(jsonObjectInfo,"DM_POSITION").length() != 0){
                                    if (JsonIsNullCheck(jsonObjectInfo,"DM_GENDER").length() != 0){
                                        if (JsonIsNullCheck(jsonObjectInfo,"DM_AGE").length() != 0){
                                            deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION") + " " + "(" + gender + ", "+ JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + "세)");
                                        }else{
                                            deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION") + " " + "(" + gender + ")");
                                        }
                                    }else{
                                        if (JsonIsNullCheck(jsonObjectInfo,"DM_AGE").length() != 0){
                                            deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION") + " " + "(" + JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + "세)");
                                        }else{
                                            deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + JsonIsNullCheck(jsonObjectInfo,"DM_POSITION"));
                                        }
                                    }
                                }else{
                                    if (JsonIsNullCheck(jsonObjectInfo,"DM_GENDER").length() != 0){
                                        if (JsonIsNullCheck(jsonObjectInfo,"DM_AGE").length() != 0){
                                            deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + "(" + gender + ", "+ JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + "세)");
                                        }else{
                                            deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + "(" + gender + ")");
                                        }
                                    }else{
                                        if (JsonIsNullCheck(jsonObjectInfo,"DM_AGE").length() != 0){
                                            deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME") + " " + "(" + JsonIsNullCheck(jsonObjectInfo,"DM_AGE") + "세)");
                                        }else{
                                            deceasedArray.add("故 " + JsonIsNullCheck(jsonObjectInfo,"DM_NAME"));
                                        }
                                    }
                                }

                                if (JsonIntIsNullCheck(jsonObjectInfo,"CARRYING_YN") == 0){
                                    changeCheckOutArray.add(" - ");
                                }

                                try {
                                    if (JsonIsNullCheck(jsonObjectInfo,"ENTRANCE_ROOM_NO").length() == 0){
                                        changeCheckInArray.add(" - ");
                                    }else{
                                        changeCheckInArray.add(hangulDateFormat.format(simpleDateFormat.parse(JsonIsNullCheck(jsonObjectInfo,"ENTRANCE_ROOM_START_DT").replace("T"," "))));
                                    }

                                    if (JsonIntIsNullCheck(jsonObjectInfo,"CARRYING_YN") == 1){
                                        changeCheckOutArray.add(hangulDateFormat.format(simpleDateFormat.parse(JsonIsNullCheck(jsonObjectInfo,"CARRYING_DT").replace("T"," "))));
                                    }else {
                                        changeCheckOutArray.add("");
                                    }
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }

                                relationshipArray.add("");
                                changeLocationArray.add(JsonIsNullCheck(jsonObjectInfo,"BURIAL_PLOT_NAME"));
                                imgPathArray.add(JsonIsNullCheck(jsonObjectInfo,"DM_PHOTO"));
                                arrowNoArray.add(JsonIsNullCheck(jsonObjectInfo,"ARROW_NO"));
                                religionBgArray.add(JsonIsNullCheck(jsonObjectInfo,"STATUS_PLATE_BG"));
                            }
                        }

                        if (JsonIntIsNullCheck(jsonObjectStyle,"DIVISION_MODE") == 1 || JsonIntIsNullCheck(jsonObjectStyle,"DIVISION_MODE") == 0 ){
                            if (titleArray.size() == 3){
                                for (int q = 0; q < divideArray.size(); q++){
                                    if (divideArray.get(q).contains("4")){
                                        divisionImgPath = divideImgPathArray.get(q);
                                    }
                                }
                            }else if (titleArray.size() == 5){
                                for (int q = 0; q < divideArray.size(); q++){
                                    if (divideArray.get(q).contains("6")){
                                        divisionImgPath = divideImgPathArray.get(q);
                                    }
                                }
                            }else if (titleArray.size() > 6){
                                for (int q = 0; q < divideArray.size(); q++){
                                    if (divideArray.get(q).contains("8")){
                                        divisionImgPath = divideImgPathArray.get(q);
                                    }
                                }
                            }
                        }else{
                            for (int q = 0; q < divideArray.size(); q++){
                                if (divideArray.get(q).contains(JsonIsNullCheck(jsonObjectStyle,"DIVISION"))){
                                    divisionImgPath = divideImgPathArray.get(q);
                                }
                            }
                        }

                        if (jsonArrayList.length() > 0){

                            if (multiMode){

                                int divisionMulti = 0;

                                String statePlateNo = "";

                                if (pref.getInt("array0",0) == 1){
                                    divisionMulti = 1;

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#000000";
                                    checkOutColor = "#000000";
                                    locationColor = "#000000";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "43";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "50";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "56";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "62";
                                    }

                                }else if (pref.getInt("array0",0) == 2){
                                    divisionMulti = 2;

                                    for (int q = 0; q < divideArray.size(); q++){
                                        if (divideArray.get(q).contains("2")){
                                            divisionImgPath = divideImgPathArray.get(q);
                                        }
                                    }

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#000000";
                                    checkOutColor = "#000000";
                                    locationColor = "#000000";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "44";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "51";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "57";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "63";
                                    }
                                }else if (pref.getInt("array0",0) < 5){
                                    divisionMulti = 4;

                                    for (int q = 0; q < divideArray.size(); q++){
                                        if (divideArray.get(q).contains("4")){
                                            divisionImgPath = divideImgPathArray.get(q);
                                        }
                                    }

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#000000";
                                    checkOutColor = "#000000";
                                    locationColor = "#000000";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "45";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "52";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "58";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "64";
                                    }
                                }else if (pref.getInt("array0",0) < 7){
                                    divisionMulti = 6;

                                    for (int q = 0; q < divideArray.size(); q++){
                                        if (divideArray.get(q).contains("6")){
                                            divisionImgPath = divideImgPathArray.get(q);
                                        }
                                    }

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#ffffff";
                                    checkOutColor = "#ffffff";
                                    locationColor = "#ffffff";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "46";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "53";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "59";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "65";
                                    }
                                }else {
                                    divisionMulti = 8;

                                    for (int q = 0; q < divideArray.size(); q++){
                                        if (divideArray.get(q).contains("8")){
                                            divisionImgPath = divideImgPathArray.get(q);
                                        }
                                    }

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#ffffff";
                                    checkOutColor = "#ffffff";
                                    locationColor = "#ffffff";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "47";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "54";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "60";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "66";
                                    }
                                }

                                ArrayList<String> titleTest = new ArrayList<>();
                                ArrayList<String> deceasedTest = new ArrayList<>();
                                ArrayList<String> relationShipTest = new ArrayList<>();
                                ArrayList<String> changeCheckInTest = new ArrayList<>();
                                ArrayList<String> changeCheckOutTest = new ArrayList<>();
                                ArrayList<String> changeLocationTest = new ArrayList<>();
                                ArrayList<String> imgPathTest = new ArrayList<>();
                                ArrayList<ArrayList<String>> namesTest = new ArrayList<>();
                                ArrayList<ArrayList<String>> relationTest = new ArrayList<>();
                                ArrayList<String> arrowNoTest = new ArrayList<>();
                                ArrayList<String> religionBgTest = new ArrayList<>();

                                for (int i = 0; i < multiList.length; i++){
                                    if (multiList[i].equals(pref.getString("raspberryConnectionNo",""))){
                                        if (i == 0){
                                            if (pref.getInt("array"+i,0) != 0){
                                                for (int j = 0; j < pref.getInt("array"+i,0); j++){
                                                    titleTest.add(titleArray.get(j));
                                                    deceasedTest.add(deceasedArray.get(j));
                                                    relationShipTest.add(relationshipArray.get(j));
                                                    changeCheckInTest.add(changeCheckInArray.get(j));
                                                    changeCheckOutTest.add(changeCheckOutArray.get(j));
                                                    changeLocationTest.add(changeLocationArray.get(j));
                                                    imgPathTest.add(imgPathArray.get(j));
                                                    namesTest.add(namesArray.get(j));
                                                    relationTest.add(relationArray.get(j));
                                                    arrowNoTest.add(arrowNoArray.get(j));
                                                    religionBgTest.add(religionBgArray.get(j));
                                                }
                                            }else{
                                                Intent intent = new Intent(InternetGeneralActivity.this,WaitingActivity.class);
                                                startActivity(intent);
                                            }

                                        }else {
                                            if (pref.getInt("array"+i,0) != 0){

                                                familySize = 0;
                                                familyPosition = 0;

                                                ArrayList<Integer> textLength = new ArrayList<>();
                                                int textHJHH = 0;

                                                for (int j = pref.getInt("array"+(i-1),0); j < (pref.getInt("array"+(i-1),0) + pref.getInt("array"+i,0)); j++){
                                                    titleTest.add(titleArray.get(j));
                                                    deceasedTest.add(deceasedArray.get(j));
                                                    relationShipTest.add(relationshipArray.get(j));
                                                    changeCheckInTest.add(changeCheckInArray.get(j));
                                                    changeCheckOutTest.add(changeCheckOutArray.get(j));
                                                    changeLocationTest.add(changeLocationArray.get(j));
                                                    imgPathTest.add(imgPathArray.get(j));
                                                    namesTest.add(namesArray.get(j));
                                                    relationTest.add(relationArray.get(j));
                                                    arrowNoTest.add(arrowNoArray.get(j));
                                                    religionBgTest.add(religionBgArray.get(j));

                                                    for (int x = 0; x < relationArray.get(j).size(); x++){
                                                        Log.i(TAG,"bbbb : " + relationArray.get(j).get(x));
                                                        textHJHH = textHJHH + relationArray.get(j).get(x).length();
                                                    }
                                                    textLength.add(textHJHH);
                                                    textHJHH = 0;
                                                }

                                                for (int x = 0; x < textLength.size(); x++){
                                                    if (familySize < textLength.get(x)){
                                                        familyPosition = x;
                                                        familySize = textLength.get(x);
                                                    }
                                                }

                                            }else{
                                                Intent intent = new Intent(InternetGeneralActivity.this,WaitingActivity.class);
                                                startActivity(intent);
                                            }
                                        }
                                    }
                                }

                                if (titleTest.size() != 0){
                                    adater = new InternetGeneralViewPagerAdapter(getLayoutInflater(),InternetGeneralActivity.this,
                                            divisionMulti,JsonIntIsNullCheck(jsonObjectStyle,"ARROW_FLAG"),
                                            familySize,familyPosition,pref,JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"),divisionImgPath,type
                                            ,deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor,statePlateNo);

//                                    for (int i = 0; i < titleTest.size(); i++){
                                        adater.addItem(titleTest,deceasedTest,relationShipTest,changeCheckInTest,changeCheckOutTest,changeLocationTest,imgPathTest,namesTest,relationTest,arrowNoTest,religionBgTest,familyTextSizeList);
//                                    }
                                    infinitePagerAdapter = new InfinitePagerAdapter(adater);
                                    viewPager.setAdapter(infinitePagerAdapter);
                                }
                            }else if (JsonIntIsNullCheck(jsonObjectStyle,"DIVISION_MODE") == 1 || JsonIntIsNullCheck(jsonObjectStyle,"DIVISION_MODE") == 0){
                                String statePlateNo = "";

                                if (titleArray.size() == 1){

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#000000";
                                    checkOutColor = "#000000";
                                    locationColor = "#000000";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "43";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "50";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "56";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "62";
                                    }

                                    adater = new InternetGeneralViewPagerAdapter(getLayoutInflater(),InternetGeneralActivity.this,
                                            1,JsonIntIsNullCheck(jsonObjectStyle,"ARROW_FLAG"),
                                            familySize,familyPosition,pref,JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"),divisionImgPath,type
                                            ,deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor,statePlateNo);

                                    adater.addItem(titleArray,deceasedArray,relationshipArray,changeCheckInArray,changeCheckOutArray,changeLocationArray,imgPathArray,namesArray,relationArray,arrowNoArray,religionBgArray,familyTextSizeList);

                                }else if (titleArray.size() == 2){

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#000000";
                                    checkOutColor = "#000000";
                                    locationColor = "#000000";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "44";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "51";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "57";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "63";
                                    }

                                    adater = new InternetGeneralViewPagerAdapter(getLayoutInflater(),InternetGeneralActivity.this,
                                            2,JsonIntIsNullCheck(jsonObjectStyle,"ARROW_FLAG"),
                                            familySize,familyPosition,pref,JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"),divisionImgPath,type
                                            ,deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor,statePlateNo);

                                    adater.addItem(titleArray,deceasedArray,relationshipArray,changeCheckInArray,changeCheckOutArray,changeLocationArray,imgPathArray,namesArray,relationArray,arrowNoArray,religionBgArray,familyTextSizeList);

                                }else if (titleArray.size() < 5){

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#000000";
                                    checkOutColor = "#000000";
                                    locationColor = "#000000";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "45";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "52";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "58";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "64";
                                    }

                                    adater = new InternetGeneralViewPagerAdapter(getLayoutInflater(),InternetGeneralActivity.this,
                                            4,JsonIntIsNullCheck(jsonObjectStyle,"ARROW_FLAG"),
                                            familySize,familyPosition,pref,JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"),divisionImgPath,type
                                            ,deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor,statePlateNo);

                                    adater.addItem(titleArray,deceasedArray,relationshipArray,changeCheckInArray,changeCheckOutArray,changeLocationArray,imgPathArray,namesArray,relationArray,arrowNoArray,religionBgArray,familyTextSizeList);

                                    Log.i(TAG,"insert");

                                }else if (titleArray.size() < 7){

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#ffffff";
                                    checkOutColor = "#ffffff";
                                    locationColor = "#ffffff";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "46";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "53";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "59";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "65";
                                    }

                                    adater = new InternetGeneralViewPagerAdapter(getLayoutInflater(),InternetGeneralActivity.this,
                                            6,JsonIntIsNullCheck(jsonObjectStyle,"ARROW_FLAG"),
                                            familySize,familyPosition,pref,JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"),divisionImgPath,type
                                            ,deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor,statePlateNo);
//                                    for (int i = 0; i < titleArray.size(); i++){
                                    adater.addItem(titleArray,deceasedArray,relationshipArray,changeCheckInArray,changeCheckOutArray,changeLocationArray,imgPathArray,namesArray,relationArray,arrowNoArray,religionBgArray,familyTextSizeList);
//                                    }
                                }else if (titleArray.size() >= 7){

                                    deceasedColor = "#ffffff";
                                    roomNameColor = "#ffffff";
                                    relationColor = "#000000";
                                    checkInTitleColor = "#ffffff";
                                    checkOutTitleColor = "#ffffff";
                                    locationTitleColor = "#ffffff";
                                    checkInColor = "#ffffff";
                                    checkOutColor = "#ffffff";
                                    locationColor = "#ffffff";

                                    if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("42") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("43") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("44") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("45") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("46") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("47")){
                                        statePlateNo = "47";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("49") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("50") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("51") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("52") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("53") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("54")){
                                        statePlateNo = "54";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("55") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("56") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("57") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("58") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("59") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("60")){
                                        statePlateNo = "60";
                                    }else if (JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("61") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("62") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("63") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("64") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("65") ||
                                            JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO").equals("66")){
                                        statePlateNo = "66";
                                    }

                                    adater = new InternetGeneralViewPagerAdapter(getLayoutInflater(),InternetGeneralActivity.this,
                                            8,JsonIntIsNullCheck(jsonObjectStyle,"ARROW_FLAG"),
                                            familySize,familyPosition,pref,JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"),divisionImgPath,type
                                            ,deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor,statePlateNo);
                                    for (int i = 0; i < titleArray.size(); i++){
                                        adater.addItem(titleArray,deceasedArray,relationshipArray,changeCheckInArray,changeCheckOutArray,changeLocationArray,imgPathArray,namesArray,relationArray,arrowNoArray,religionBgArray,familyTextSizeList);
                                    }
                                }

                                infinitePagerAdapter = new InfinitePagerAdapter(adater);
                                viewPager.setAdapter(infinitePagerAdapter);

                            }else{
//                                adater = new InternetGeneralViewPagerAdapter(getLayoutInflater(),InternetGeneralActivity.this,
//                                        JsonIntIsNullCheck(jsonObjectStyle,"DIVISION"),JsonIntIsNullCheck(jsonObjectStyle,"ARROW_FLAG"),
//                                        familySize,familyPosition,pref,JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"),divisionImgPath,type
//                                        ,deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor,JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO"));
//
//                                    adater.addItem(titleArray,deceasedArray,relationshipArray,changeCheckInArray,changeCheckOutArray,changeLocationArray,imgPathArray,namesArray,relationArray,arrowNoArray,religionBgArray);

                                adater = new InternetGeneralViewPagerAdapter(getLayoutInflater(),InternetGeneralActivity.this,
                                        JsonIntIsNullCheck(jsonObjectStyle,"DIVISION"),JsonIntIsNullCheck(jsonObjectStyle,"ARROW_FLAG"),
                                        familySize,familyPosition,pref,JsonIsNullCheck(jsonObjectStyle,"BOTTOM_TEXT"),divisionImgPath,type
                                        ,deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor,JsonIsNullCheck(jsonObjectStyle,"STATUS_PLATE_STYLE_NO"));

                                for (int i = 0; i < titleArray.size(); i++){
                                    adater.addItem(titleArray,deceasedArray,relationshipArray,changeCheckInArray,changeCheckOutArray,changeLocationArray,imgPathArray,namesArray,relationArray,arrowNoArray,religionBgArray,familyTextSizeList);
                                }

                                infinitePagerAdapter = new InfinitePagerAdapter(adater);
                                viewPager.setAdapter(infinitePagerAdapter);
                            }

                            position = 0;

                            boolean finalMultiMode = multiMode;

                            handler = new Handler(){
                                public void handleMessage(Message msg) {
                                    if (finalMultiMode){
                                        handler.removeMessages(0);
                                    }else{
                                        if (JsonIntIsNullCheck(jsonObjectStyle,"DIVISION_MODE") == 1 || JsonIntIsNullCheck(jsonObjectStyle,"DIVISION_MODE") == 0){
                                            if (titleArray.size() < 9){
                                                Log.i(TAG,"remove 1");
//                                            viewPager.setCurrentItem(position++,false);
//                                            if (position == 5){
                                                handler.removeMessages(0);
//                                            }

                                            }else{
                                                Log.i(TAG,"handle 1");
                                                if (JsonIsNullCheck(jsonObjectStyle,"SLIDE_EFFECT").equals("0")){
                                                    viewPager.setCurrentItem(position++,false);
                                                }else{
                                                    viewPager.setCurrentItem(position++,true);
                                                }
                                                handler.sendEmptyMessageDelayed(0,(JsonIntIsNullCheck(jsonObjectStyle,"SLIDE_SEC") * 1000));
                                            }
                                        }else{
                                            if (JsonIntIsNullCheck(jsonObjectStyle,"DIVISION") < titleArray.size()){
                                                Log.i(TAG,"handle 2");
                                                if (JsonIsNullCheck(jsonObjectStyle,"SLIDE_EFFECT").equals("0")){
                                                    viewPager.setCurrentItem(position++,false);
                                                }else{
                                                    viewPager.setCurrentItem(position++,true);
                                                }
                                                handler.sendEmptyMessageDelayed(0,(JsonIntIsNullCheck(jsonObjectStyle,"SLIDE_SEC") * 1000));
                                            }else{
                                                Log.i(TAG,"remove 2");
                                                handler.removeMessages(0);
                                            }
                                        }
                                    }
                                }
                            };

                            handler.sendEmptyMessage(0);

                            txtBottom.setFocusable(true);
                            txtBottom.requestFocus();
                            txtBottom.setFocusableInTouchMode(true);
                        }
                    }
                }catch (JSONException e){

                }
            }else{
                Intent intent = new Intent(InternetGeneralActivity.this,WaitingActivity.class);
                startActivity(intent);
            }
        }
    }

    public class FuneralInfoNetWork extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpClient.Builder http = new HttpClient.Builder("POST", Server.funeralInfoUrl());
            http.addOrReplace("funeralNo",pref.getString("funeralNo",""));

            HttpClient post = http.create();
            post.request();
            String body = post.getBody();

            Log.i(TAG, "FuneralInfoNetWork check : " + body);

            return body;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONObject jsonObjectInfo = jsonObject.getJSONObject("infoList");

                if (JsonIsNullCheck(jsonObjectInfo,"LOGO_IMG").length() > 0){
                    Glide.with(InternetGeneralActivity.this).load(JsonIsNullCheck(jsonObjectInfo,"LOGO_IMG")).into(imgLogo);
                }
            }catch (JSONException e){

            }
        }
    }

    public class DivisionImgNetWork extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpClient.Builder http = new HttpClient.Builder("POST", Server.divisionImgUrl());
            http.addOrReplace("funeralNo",pref.getString("funeralNo",""));

            HttpClient post = http.create();
            post.request();
            String body = post.getBody();

            Log.i(TAG, "DivisionImgNetWork check : " + body);

            return body;
        }

        @Override
        protected void onPostExecute(String s) {
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("divideImg");

                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    divideArray.add(JsonIsNullCheck(object,"DIVIDE"));
                    divideImgPathArray.add(JsonIsNullCheck(object,"PATH"));
                }
            }catch (JSONException e){

            }
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
                Intent intent = new Intent(InternetGeneralActivity.this, LoginActivity.class);
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
                Intent intent = new Intent(InternetGeneralActivity.this, ModeSelectActivity.class);
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

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Date date = new Date(System.currentTimeMillis());
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR,9);

            if (txtTopTime != null){
                txtTopTime.setText(hangulDateFormat.format(calendar.getTime()));
                txtTopTime.setTypeface(type);
                txtTopTime.invalidate();
            }

//            for (int i = 0; i < changeCheckOutArray.size(); i++){
//                if (changeCheckOutArray.get(i).equals(txtTopTime.getText().toString())){
//                    new GeneralListNetWork().execute();
//                }
//            }
            mHandler.sendEmptyMessageDelayed(0,1000 * 60);
        }
    };

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

                WifiConfiguration conf = new WifiConfiguration();
                conf.SSID = "\"" + networkSSID + "\"";

                if (bssid.contains("WEP")){
                    conf.wepKeys[0] = "\"" + networkPass + "\"";
                    conf.wepTxKeyIndex = 0;
                    conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                    conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
                }else if (bssid.contains("WPA")){
                    conf.SSID = String.format("\"%s\"", ssid);
                    conf.preSharedKey = "\""+ networkPass +"\"";
                }else {
                    conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
                }

                WifiManager wifiManager = (WifiManager)getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                wifiManager.addNetwork(conf);

                List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
                for( WifiConfiguration i : list ) {
                    if(i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {
                        wifiManager.disconnect();
                        wifiManager.enableNetwork(i.networkId, true);
                        wifiManager.reconnect();

                        break;
                    }
                }

//                WifiConfiguration wifiConfig = new WifiConfiguration();
//                wifiConfig.SSID = String.format("\"%s\"", ssid);
//                wifiConfig.allowedAuthAlgorithms.set(WifiConfiguration.AuthAlgorithm.OPEN);
//                wifiConfig.allowedProtocols.set(WifiConfiguration.Protocol.RSN);
//                wifiConfig.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
//                wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
//                wifiConfig.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
//                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
//                wifiConfig.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
//                wifiConfig.preSharedKey = "\"".concat(edtPassword.getText().toString()).concat("\"");
//
//                wifiManager.addNetwork(wifiConfig);
//                int netId = wifiManager.addNetwork(wifiConfig);
//
//                List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
//                for( WifiConfiguration i : list ) {
//                    if(i.SSID != null && i.SSID.equals("\"" + ssid + "\"")) {
//                        wifiManager.disconnect();
//                        wifiManager.enableNetwork(i.networkId, true);
//                        wifiManager.reconnect();
//                        break;
//                    }
//                }
            }
        });

        linearParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (wifi.isConnected()){
                }else{
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
                    if (wifiSSIDArray.contains(scanDatas.get(i).SSID)){

                    }else{
                        WifiListItem(scanDatas.get(i).SSID,scanDatas.get(i).level,scanDatas.get(i).capabilities);
                        wifiSSIDArray.add(scanDatas.get(i).SSID);
                    }
                }
            }else if(intent.getAction().equals(WifiManager.NETWORK_STATE_CHANGED_ACTION)) {
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

        if (handler != null){
            handler.removeMessages(0);
        }
        if (mHandler != null){
            mHandler.removeMessages(0);
        }

        new DivisionImgNetWork().execute();
        new FuneralInfoNetWork().execute();
        new GeneralListNetWork().execute();

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private BroadcastReceiver mUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub // Get extra data included in the Intent
            String message = intent.getStringExtra("message");
            String status = intent.getStringExtra("status");
            String data = intent.getStringExtra("data");

            if (message.equals("update") && status.equals("종합")){
                if (handler != null){
                    handler.removeMessages(0);
                }

                CaptureUtil.captureView(relaCaptureParent);

                if (adater != null){
                    adater.captureStatus = false;
                }
                String mPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath();
                File folder = new File(mPath);
                if(folder.exists()) {
                    Log.i(TAG,"존재");
                    Bitmap bitmap = BitmapFactory.decodeFile(mPath + "/test1.jpg");
                    linPopupTest.setVisibility(View.VISIBLE);
                    linPopupTest.setImageBitmap(bitmap);
                    linPopupTest.requestLayout();
//                    imgLogo.setImageBitmap(bitmap);
                }else{
                    Log.i(TAG,"존재안함");
                }

                new DivisionImgNetWork().execute();
                new FuneralInfoNetWork().execute();
                new GeneralListNetWork().execute();
            }else if (message.equals("check") && status.equals("종합")){
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
            }else if (message.equals("check") && status.equals("connect")){
                intentData = data;
                autorize();
            }else if (message.equals("system")){
                txtSystemUpdate.setVisibility(View.VISIBLE);
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

    public class MyJavascriptInterface {

        @JavascriptInterface
        public void getHtml(String html) { //위 자바스크립트가 호출되면 여기로 html이 반환됨
            System.out.println(html);
            new ConnectNetWork().execute(getIpAddress(),html,intentData);
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
