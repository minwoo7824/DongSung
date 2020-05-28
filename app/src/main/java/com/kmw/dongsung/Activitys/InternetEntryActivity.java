package com.kmw.dongsung.Activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
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
import android.widget.VideoView;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.firebase.iid.FirebaseInstanceId;
import com.kmw.dongsung.BuildConfig;
import com.kmw.dongsung.Commons.Https.HttpClient;
import com.kmw.dongsung.Commons.ServerUrl.Server;
import com.kmw.dongsung.Commons.Utils;
import com.kmw.dongsung.Func.InfiniteFunc.InfinitePagerAdapter;
import com.kmw.dongsung.Func.InfiniteFunc.InfiniteViewPager;
import com.kmw.dongsung.Func.SangGaPagerAdapter;
import com.kmw.dongsung.Func.WaitingViewPagerAdapter;
import com.kmw.dongsung.R;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apmem.tools.layouts.FlowLayout;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.URL;
import java.net.URLConnection;
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

public class InternetEntryActivity extends Activity implements View.OnClickListener {

    private String TAG = "InternetEntryActivity";
    LinearLayout linearVisible;
    TextView txtName;
    ImageView imgProfile;
    TextView txtRelationTest,txtCheckOut,txtLocation;
    LinearLayout linearRelationParent;
    LinearLayout linearRelationVisible;
    TextView txtEtc1,txtEtc2,txtEtc3;
    ArrayList<TextView> txtRelationArray;
    ArrayList<TextView> txtRelationNamesArray;
    Typeface typeface = null;
    File outputFile;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    public RequestManager mGlideRequestManager;

    ArrayList<String> roomStartDt = new ArrayList<>();
    ArrayList<String> roomEndDt = new ArrayList<>();

    public static final String TIME_SERVER = "pool.ntp.org";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat hangulDateFormat = new SimpleDateFormat("MM월 dd일 HH시 mm분");
    SimpleDateFormat timeDateFormat = new SimpleDateFormat("HH시 mm분");

    SimpleDateFormat rebootDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    SimpleDateFormat reboottimeDateFormat = new SimpleDateFormat("HH시 mm분");
    Calendar calendar;

    boolean screenCheck = false;
    String success = "";
    Date dateCurrent = null;
    public static int rebootCheck = 0;

    int eventCnt = 0;

    InfiniteViewPager viewPager;
    InfinitePagerAdapter infinitePagerAdapter;
    VideoView videoView;

    // 0 - 미사용, 1 - 사진, 2 - 동영상
    int screenType = 0;
    int statusPlateNo = 0;
    int slideSec = 0;

    ArrayList<String> imagePathList;
    WaitingViewPagerAdapter adapter;

    int position = 0;
    Handler handler;
    Runnable Update;
    String VIDEO_URL = "";

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
        setContentView(R.layout.activity_internet_entry);

        pref = getSharedPreferences("dongsung",MODE_PRIVATE);
        editor = pref.edit();

        mGlideRequestManager = Glide.with(this);

        typeface = Typeface.createFromAsset(getAssets(),"un_batang.ttf");

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

        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.i(TAG,"what : " + what + " extra : " + extra);
//
                String downloadsPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();

                String fileName = "test.mp4";

                File outputFile = new File(downloadsPath);

                File list[] = outputFile.listFiles();

                for (int i = 0; i < list.length; i++){
                    Log.i(TAG,"aaaa : " + list[i]);
                    list[i].delete();
                }

                new DownloadFilesTask(InternetEntryActivity.this).execute(VIDEO_URL);

                return true;
            }
        });
    }

    void FindViewById(){
        linearVisible = (LinearLayout)findViewById(R.id.linear_entry_visible);
        txtName = (TextView)findViewById(R.id.txt_entry_name);
        imgProfile = (ImageView)findViewById(R.id.img_entry_profile);
        txtRelationTest = (TextView)findViewById(R.id.txt_entry_relation_test);
        linearRelationParent = (LinearLayout)findViewById(R.id.linear_entry_relation_parent);
        linearRelationVisible = (LinearLayout)findViewById(R.id.linear_entry_relation_visible);
        txtCheckOut = (TextView)findViewById(R.id.txt_entry_check_out);
        txtLocation = (TextView)findViewById(R.id.txt_entry_location);
        txtEtc1 = (TextView)findViewById(R.id.txt_entry_etc1);
        txtEtc2 = (TextView)findViewById(R.id.txt_entry_etc2);
        txtEtc3 = (TextView)findViewById(R.id.txt_entry_etc3);

        viewPager = (InfiniteViewPager)findViewById(R.id.view_pager_wait_slide);
        videoView = (VideoView)findViewById(R.id.video_wait_play);

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

        txtRelationTest.setTypeface(typeface);
        txtName.setTypeface(typeface);
        txtCheckOut.setTypeface(typeface);
        txtLocation.setTypeface(typeface);
        txtEtc1.setTypeface(typeface);
        txtEtc2.setTypeface(typeface);
        txtEtc3.setTypeface(typeface);

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

    void ListMake(int position, String relation, String name, float textSize){

        if (position != 0){
            TextView linear = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,15);
            linear.setLayoutParams(params);

            linearRelationParent.addView(linear);
        }

        View listView = new View(this);
        listView = getLayoutInflater().inflate(R.layout.view_family_list_item,null);
        TextView txtRelation = listView.findViewById(R.id.txt_family_relation_sort_item_relation);
//        TextView txtName = listView.findViewById(R.id.txt_family_relation_sort_item_name);
        FlowLayout flowLayout = listView.findViewById(R.id.flow_family_relation_sort_item_name_parent);
        TextView txtComma = listView.findViewById(R.id.txt_family_relation_sort_item_comma);

        txtRelation.setTypeface(typeface);
        txtComma.setTypeface(typeface);

        txtRelation.setGravity(Gravity.CENTER_HORIZONTAL);
        txtRelationArray.add(txtRelation);
        txtRelationNamesArray.add(txtRelation);
        txtRelationNamesArray.add(txtComma);

        txtComma.setTextColor(getResources().getColor(R.color.colorWhite));
        txtRelation.setTextColor(getResources().getColor(R.color.colorWhite));

        String[] strings = name.split(",");

        for (int i = 0; i < strings.length; i++){
            TextView txtName = new TextView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            txtName.setLayoutParams(layoutParams);

            txtName.setIncludeFontPadding(false);
            txtName.setTypeface(typeface);
            txtName.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);

            txtName.setTextColor(getResources().getColor(R.color.colorWhite));

            flowLayout.addView(txtName);

            if (i != (strings.length-1)) {
                if (strings[i].equals("  ")){
                    txtName.setText("김, ");
                    txtName.setVisibility(View.INVISIBLE);
                }else if (strings[i].equals("    ")){
                    txtName.setText("김민, ");
                    txtName.setVisibility(View.INVISIBLE);
                }else if (strings[i].equals("      ")){
                    txtName.setText("김민우, ");
                    txtName.setVisibility(View.INVISIBLE);
                }else if (strings[i].equals("        ")){
                    txtName.setText("김민우우, ");
                    txtName.setVisibility(View.INVISIBLE);
                }else{
                    if (strings[i + 1].equals("  ")){
                        txtName.setText(strings[i] + "  ");
                    }else{
                        txtName.setText(strings[i] + ", ");
                    }
                }
            }else{
                txtName.setText(strings[i]);
            }

            txtRelationNamesArray.add(txtName);
        }

        txtRelation.setText(relation);

        txtRelation.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        txtComma.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        linearRelationParent.addView(listView);
    }

    public class BinsoNetWork extends AsyncTask<String, String, String> {

        String eventNo = "";

        public BinsoNetWork(String eventNo) {
            this.eventNo = eventNo;
        }

        @Override
        protected String doInBackground(String... strings) {

            HttpClient.Builder http = new HttpClient.Builder("POST", Server.binsoDetailUrl());

            http.addOrReplace("order","EXPOSURE ASC, APPELLATION ASC");
            http.addOrReplace("toDayEntranceRoom","true");
            http.addOrReplace("eventNo",eventNo);
            http.addOrReplace("eventAliveFlag","true");
            http.addOrReplace("funeralNo",pref.getString("funeralNo",""));
            http.addOrReplace("entranceRoomNo",pref.getString("raspberryConnectionNo",""));

            HttpClient post = http.create();
            post.request();
            String body = post.getBody();

            Log.i(TAG, "BinsoNetWork check : " + body);

            return body;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.length() != 0){

                linearVisible.setVisibility(View.VISIBLE);
                linearRelationParent.removeAllViews();

                if (screenType == 1){
                    videoView.setVisibility(View.GONE);
                }else if (screenType == 2){
                    if (handler != null){
                        handler.removeMessages(0);
                    }
                    viewPager.setVisibility(View.GONE);
                }else{

                }

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArrayInfo = jsonObject.getJSONArray("eventInfo");
                    final JSONArray jsonArrayFamily = jsonObject.getJSONArray("eventFamilyInfo");

                    if (jsonArrayInfo.length() != 0){
                        JSONObject object = jsonArrayInfo.getJSONObject(0);

                        String gender = JsonIsNullCheck(object,"DM_GENDER");

                        if (gender.equals("1")){
                            gender = "남";
                        }else if (gender.equals("2")){
                            gender = "여";
                        }

                        if (JsonIsNullCheck(object,"DM_POSITION").length() != 0){
                            if (JsonIsNullCheck(object,"DM_GENDER").length() != 0){
                                if (JsonIsNullCheck(object,"DM_AGE").length() != 0){
                                    txtName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + JsonIsNullCheck(object,"DM_POSITION") + " " + "(" + gender + ", "+ JsonIsNullCheck(object,"DM_AGE") + "세)");
                                }else{
                                    txtName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + JsonIsNullCheck(object,"DM_POSITION") + " " + "(" + gender + ", "+ JsonIsNullCheck(object,"DM_AGE") + "세)");
                                }
                            }else{
                                if (JsonIsNullCheck(object,"DM_AGE").length() != 0){
                                    txtName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + JsonIsNullCheck(object,"DM_POSITION") + " " + "(" + JsonIsNullCheck(object,"DM_AGE") + "세)");
                                }else{
                                    txtName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + JsonIsNullCheck(object,"DM_POSITION"));
                                }
                            }
                        }else{
                            if (JsonIsNullCheck(object,"DM_GENDER").length() != 0){
                                if (JsonIsNullCheck(object,"DM_AGE").length() != 0){
                                    txtName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + "(" + gender + ", "+ JsonIsNullCheck(object,"DM_AGE") + "세)");
                                }else{
                                    txtName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + "(" + gender + ")");
                                }
                            }else{
                                if (JsonIsNullCheck(object,"DM_AGE").length() != 0){
                                    txtName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + "(" + JsonIsNullCheck(object,"DM_AGE") + "세)");
                                }else{
                                    txtName.setText("故 " + JsonIsNullCheck(object,"DM_NAME"));
                                }
                            }
                        }

                        Log.i(TAG,"txtName : " + txtName.getText());

                        if (JsonIntIsNullCheck(object,"CARRYING_YN") == 0){
                            txtCheckOut.setText("미 정");
                        }else {
                            txtCheckOut.setText(hangulDateFormat.format(simpleDateFormat.parse(JsonIsNullCheck(object,"CARRYING_DT").replace("T"," "))));
                        }

                        if (JsonIsNullCheck(object,"BURIAL_PLOT_NAME").contains("\r\n")){
                            txtLocation.setMaxLines(2);
                        }else{
                            txtLocation.setMaxLines(1);
                        }

                        if (JsonIsNullCheck(object,"BURIAL_PLOT_NAME").length() != 0){
                            txtLocation.setText(JsonIsNullCheck(object,"BURIAL_PLOT_NAME"));
                        }else{
                            txtLocation.setText("미  정");
                        }

                        mGlideRequestManager.load(JsonIsNullCheck(object,"DM_PHOTO")).into(imgProfile);

                        int relationLength = 0;
                        String relatest = "";
                        int position = 0;
                        txtRelationArray = new ArrayList<>();
                        txtRelationNamesArray = new ArrayList<>();

                        if (jsonArrayFamily.length() != 0){
                            for (int i = 0; i < jsonArrayFamily.length(); i++){
                                JSONObject objectFamily = jsonArrayFamily.getJSONObject(i);
                                if (relationLength < JsonIsNullCheck(objectFamily,"RELATION").length()){
                                    if (JsonIsNullCheck(objectFamily,"RELATION").contains(" ")){
                                        relationLength = JsonIsNullCheck(objectFamily,"RELATION").length()-1;
                                    }else{
                                        relationLength = JsonIsNullCheck(objectFamily,"RELATION").length();
                                    }
                                    position = i;
                                }
                                relatest += JsonIsNullCheck(objectFamily,"RELATION") + " : " + JsonIsNullCheck(objectFamily,"NAMES").replace("^#$%&PB$@!",",") + "\n";
                            }

                            txtRelationTest.setText(relatest);

                            Log.i(TAG,"size : " + txtRelationTest.getTextSize());

                            linearRelationVisible.setVisibility(View.VISIBLE);
                            txtRelationTest.setVisibility(View.GONE);

                            for (int i = 0; i < jsonArrayFamily.length(); i++){
                                JSONObject objectFamily = jsonArrayFamily.getJSONObject(i);
                                ListMake(i,JsonIsNullCheck(objectFamily,"RELATION"),JsonIsNullCheck(objectFamily,"NAMES").replace("^#$%&PB$@!",","), 60f);
                            }

                            final int finalPosition = position;

                            linearRelationParent.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                                @Override
                                public boolean onPreDraw() {

                                    if (linearRelationParent.getChildCount() != 0) {

                                        int child1 = 0;

                                        for (int j = 0; j < linearRelationParent.getChildCount(); j++) {
                                            child1 = child1 + linearRelationParent.getChildAt(j).getMeasuredHeight();
                                        }

                                        if (child1 >= (linearRelationParent.getHeight())) {
                                            if (child1 != 0) {

                                                for (int i = 0; i < txtRelationNamesArray.size(); i++) {
                                                    txtRelationNamesArray.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtRelationNamesArray.get(0).getTextSize() - 0.9f);
                                                }
                                                txtRelationNamesArray.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtRelationNamesArray.get(1).getTextSize());

                                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                                txtRelationArray.get(finalPosition).setLayoutParams(params1);
                                                txtRelationArray.get(finalPosition).invalidate();

                                                for (int j = 0; j < txtRelationArray.size(); j++) {
                                                    if (finalPosition < txtRelationArray.size()) {
                                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray.get(finalPosition).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                                        if (j != finalPosition) {
                                                            txtRelationArray.get(j).setWidth(txtRelationArray.get(finalPosition).getWidth());
                                                            txtRelationArray.get(j).invalidate();
                                                        }
                                                    }
                                                }
                                            }
                                        } else {

                                            for (int j = 0; j < txtRelationArray.size(); j++) {
                                                if (finalPosition < txtRelationArray.size()) {
                                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray.get(finalPosition).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                                    txtRelationArray.get(j).setLayoutParams(params11);
                                                    txtRelationArray.get(j).invalidate();
//                                    }
                                                }
                                            }

                                            if (finalPosition == 0){
                                                if (txtRelationArray.size()>1){
                                                    txtRelationArray.get(0).setWidth(txtRelationArray.get(1).getWidth());
                                                    txtRelationArray.get(0).invalidate();
                                                }
                                            }else{
                                                if (txtRelationArray.size()>1){
                                                    txtRelationArray.get(finalPosition).setWidth(txtRelationArray.get(0).getWidth());
                                                    txtRelationArray.get(finalPosition).invalidate();
                                                }
                                            }

                                            int child2 = 0;

                                            for (int j = 0; j < linearRelationParent.getChildCount(); j++){
                                                child2 = child2 + linearRelationParent.getChildAt(j).getMeasuredHeight();
                                            }

                                            if (child2 >= (linearRelationParent.getHeight())) {

                                            }else{

                                                if (finalPosition == 0){
                                                    if (txtRelationArray.size()>1){
                                                        txtRelationArray.get(0).setWidth(txtRelationArray.get(1).getWidth());
                                                        txtRelationArray.get(0).invalidate();
                                                    }
                                                }else{
                                                    if (txtRelationArray.size()>1){
                                                        txtRelationArray.get(finalPosition).setWidth(txtRelationArray.get(0).getWidth());
                                                        txtRelationArray.get(finalPosition).invalidate();
                                                    }
                                                }
                                                linearRelationParent.getViewTreeObserver().removeOnPreDrawListener(this);
                                            }
                                        }
                                    } else {
                                        linearRelationParent.getViewTreeObserver().removeOnPreDrawListener(this);
                                    }
                                    return true;
                                }
                            });
                        }
                        screenCheck = true;
                    }else{

                    }
                }catch (JSONException e){

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class BinsoListNetWork extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpClient.Builder http = new HttpClient.Builder("POST", Server.binsoListUrl());

            http.addOrReplace("order","ENTRANCE_ROOM_START_DT ASC");
            http.addOrReplace("toDayEntranceRoom","true");
            http.addOrReplace("eventAliveFlag","true");
            http.addOrReplace("funeralNo",pref.getString("funeralNo",""));
            http.addOrReplace("entranceRoomNo",pref.getString("raspberryConnectionNo",""));

            HttpClient post = http.create();
            post.request();
            String body = post.getBody();

            Log.i(TAG, "BinsoListNetWork check : " + body);

            return body;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.length() != 0){

                screenCheck = true;

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArray = jsonObject.getJSONArray("list");

                    Log.i(TAG,"total : " + jsonObject.getString("total"));

//                    if (jsonArray.length() != 0){
//                        linearVisible.setVisibility(View.GONE);
//                        new WaitingOneNetWork().execute(pref.getString("funeralNo",""));
//                    }

                    roomStartDt = new ArrayList<>();
                    roomEndDt = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject object = jsonArray.getJSONObject(i);

                        Log.i(TAG,"aaaa : " + object.toString());

                        Date dateEnd = simpleDateFormat.parse(JsonIsNullCheck(object,"ENTRANCE_ROOM_END_DT").replace("T"," "));

                        if (dateCurrent.before(dateEnd)){
                            eventCnt++;
                            roomStartDt.add(JsonIsNullCheck(object,"ENTRANCE_ROOM_START_DT").replace("T"," "));
                            roomEndDt.add(JsonIsNullCheck(object,"ENTRANCE_ROOM_END_DT").replace("T"," "));

                            if(dateCurrent.after(simpleDateFormat.parse(JsonIsNullCheck(object,"ENTRANCE_ROOM_START_DT").replace("T"," "))) && dateCurrent.before(dateEnd)){
                                new BinsoNetWork(JsonIsNullCheck(object,"EVENT_NO")).execute();
                                break;
                            }else if (dateCurrent.getTime() == simpleDateFormat.parse(JsonIsNullCheck(object,"ENTRANCE_ROOM_START_DT").replace("T"," ")).getTime() && dateCurrent.before(dateEnd)){
                                new BinsoNetWork(JsonIsNullCheck(object,"EVENT_NO")).execute();
                                break;
                            }
                        }else if (dateCurrent.getTime() == dateEnd.getTime()){

                        }
                    }

                    if (roomStartDt.size() > 0){
                        if (dateCurrent.after(simpleDateFormat.parse(roomStartDt.get(0))) && dateCurrent.before(simpleDateFormat.parse(roomEndDt.get(0)))){
                            viewPager.setVisibility(View.GONE);
                            videoView.setVisibility(View.GONE);
                            linearVisible.setVisibility(View.VISIBLE);
                        }else {
                            linearVisible.setVisibility(View.GONE);
                            new WaitingOneNetWork().execute(pref.getString("funeralNo",""));
                        }
                    }else{
                        linearVisible.setVisibility(View.GONE);
                        new WaitingOneNetWork().execute(pref.getString("funeralNo",""));
                    }

                }catch (JSONException e){

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }else{
                linearVisible.setVisibility(View.GONE);
                new WaitingOneNetWork().execute(pref.getString("funeralNo",""));
            }
        }
    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {

                Date date = new Date(System.currentTimeMillis());
                calendar = Calendar.getInstance();
                calendar.setTime(date);
                calendar.add(Calendar.HOUR,9);
                dateCurrent = calendar.getTime();

                Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(date);
            calendar1.add(Calendar.HOUR,9);
            calendar1.add(Calendar.MINUTE,-1);
            Date date1 = calendar1.getTime();

                if (!screenCheck){
                    linearVisible.setVisibility(View.GONE);
                    new BinsoListNetWork().execute();
                }else{
                    if (roomEndDt.size() > 0){
                        Log.i(TAG,"roomEndDt : " + roomEndDt.get(0));

                        if (simpleDateFormat.format(dateCurrent).equals(roomEndDt.get(0))){
                            Log.i(TAG,"현재행사 끝");
                            linearVisible.setVisibility(View.GONE);
                            new BinsoListNetWork().execute();
                        }else if (simpleDateFormat.format(dateCurrent).equals(roomStartDt.get(0))){
                            linearVisible.setVisibility(View.GONE);
                            new BinsoListNetWork().execute();
                        }
                    }
                }
            mHandler.sendEmptyMessageDelayed(0,1000 * 60);
        }
    };

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
                Intent intent = new Intent(InternetEntryActivity.this, LoginActivity.class);
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
                Intent intent = new Intent(InternetEntryActivity.this, ModeSelectActivity.class);
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

    public class WaitingOneNetWork extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpClient.Builder http = new HttpClient.Builder("POST", Server.waitingOneUrl());

            http.addOrReplace("funeralNo",strings[0]);
            http.addOrReplace("classification","90");

            HttpClient post = http.create();
            post.request();
            String body = post.getBody();

            Log.i(TAG, "WaitingOneNetWork check : " + body);

            return body;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.length() != 0){

                try {
                    JSONArray jsonArray = new JSONArray(s);

                    imagePathList = new ArrayList<>();
                    adapter = new WaitingViewPagerAdapter(getLayoutInflater(),InternetEntryActivity.this);

//                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(0);

                        screenType = JsonIntIsNullCheck(jsonObject,"SCREEN_TYPE");
                        statusPlateNo = JsonIntIsNullCheck(jsonObject,"STATUS_PLATE_NO");
                        slideSec = JsonIntIsNullCheck(jsonObject,"SLIDE_SEC");

                        if (JsonIntIsNullCheck(jsonObject,"SCREEN_TYPE") == 1){
                            viewPager.setVisibility(View.VISIBLE);
                            videoView.setVisibility(View.GONE);
                        }else if (JsonIntIsNullCheck(jsonObject,"SCREEN_TYPE") == 2){
                            viewPager.setVisibility(View.GONE);
                            videoView.setVisibility(View.VISIBLE);
                        }else{

                        }
                        new WaitingTwoNetWork().execute(String.valueOf(statusPlateNo));
//                    }

                }catch (JSONException e){

                }
            }
        }
    }

    public class WaitingTwoNetWork extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpClient.Builder http = new HttpClient.Builder("POST", Server.waitingTwoUrl());

            http.addOrReplace("statusPlateNo",strings[0]);

            HttpClient post = http.create();
            post.request();
            String body = post.getBody();

            Log.i(TAG, "WaitingTwoNetWork check : " + body);

            return body;
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.length() != 0){

                try {
                    JSONArray jsonArray = new JSONArray(s);

                    JSONObject jsonObject = jsonArray.getJSONObject(0); //0 - hd 1 - fhd

                    if (screenType == 1){
                        imagePathList.add(JsonIsNullCheck(jsonObject,"FILE"));
                        adapter.addItem(JsonIsNullCheck(jsonObject,"FILE"));
                    }else if (screenType == 2){
                        if (JsonIntIsNullCheck(jsonObject,"PRIORITY") == 0){
                            VIDEO_URL = JsonIsNullCheck(jsonObject,"FILE");
                            if (VIDEO_URL.length() == 0){
                                Toast.makeText(InternetEntryActivity.this, "동영상 형식이 잘못되었습니다.", Toast.LENGTH_SHORT).show();
                            }else{
                                if (pref.getString("videoFile","").equals(VIDEO_URL)){
                                    String downloadsPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()+"/test.mp4";
                                    videoView.setVideoPath(downloadsPath);
                                    videoView.start();

                                    videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                                        @Override
                                        public void onPrepared(MediaPlayer mp) {
                                            mp.setLooping(true);
                                        }
                                    });
                                }else{
                                    Log.i(TAG,"videoFile : " + pref.getString("videoFile",""));
                                    new DownloadFilesTask(InternetEntryActivity.this).execute(VIDEO_URL);
                                }
                            }
                        }else{
                            videoView.setVisibility(View.GONE);
                        }
                    }

                    if (screenType == 1){
                        infinitePagerAdapter = new InfinitePagerAdapter(adapter);

                        viewPager.setAdapter(infinitePagerAdapter);

                        handler = new Handler(){
                            @Override
                            public void handleMessage(Message msg) {
                                viewPager.setCurrentItem(position++,true);
                                handler.sendEmptyMessageDelayed(0,(slideSec * 1000));
                            }
                        };

                        handler.sendEmptyMessage(0);
                    }

                }catch (JSONException e){

                }
            }
        }
    }

    private void cleanupVideo(){
        if(videoView.getVisibility() == View.VISIBLE){
            videoView.stopPlayback();
            videoView.clearAnimation();
            videoView.suspend(); // clears media player
            videoView.setVideoURI(null);
            videoView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        linearVisible.setVisibility(View.GONE);
        if (handler != null){
            handler.removeMessages(0);
        }
        LocalBroadcastManager.getInstance(this).registerReceiver( mUpdateReceiver, new IntentFilter("update"));
        mHandler.sendEmptyMessage(0);

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (handler != null){
            handler.removeMessages(0);
        }
        LocalBroadcastManager.getInstance(this).unregisterReceiver( mUpdateReceiver);
        if (mHandler != null){
            mHandler.removeMessages(0);
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

            if (message.equals("update") && status.equals("입관")){

                cleanupVideo();

                if (handler != null){
                    handler.removeMessages(0);
                }

                linearVisible.setVisibility(View.GONE);

                new BinsoListNetWork().execute();

            }else if (message.equals("system")){
                txtSystemUpdate.setVisibility(View.VISIBLE);
            }else if (message.equals("check") && status.equals("입관")){
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

    private class DownloadFilesTask extends AsyncTask<String, String, String> {

        private Context context;
        String videoToDownload;
        File path= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
//        private PowerManager.WakeLock mWakeLock;

        public DownloadFilesTask(Context context) {
            this.context = context;
        }

        //파일 다운로드를 시작하기 전에 프로그레스바를 화면에 보여줍니다.
        @Override
        protected void onPreExecute() { //2
            super.onPreExecute();

            //사용자가 다운로드 중 파워 버튼을 누르더라도 CPU가 잠들지 않도록 해서
            //다시 파워버튼 누르면 그동안 다운로드가 진행되고 있게 됩니다.
//            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
//            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
//            mWakeLock.acquire();
        }

        //파일 다운로드를 진행합니다.
        @Override
        protected String doInBackground(String... string_url) { //3
            int count;
            long FileSize = -1;
            InputStream input = null;
            OutputStream output = null;
            URLConnection connection = null;

            try {

                videoToDownload = string_url[0];

                URL url = new URL(string_url[0]);
                connection = url.openConnection();

                connection.setRequestProperty("Accept-Encoding", "identity");

                connection.connect();

                //파일 크기를 가져옴
                FileSize = connection.getContentLength();

                Log.i(TAG,"size : " + FileSize);

                //URL 주소로부터 파일다운로드하기 위한 input stream
                input = new BufferedInputStream(url.openStream(),8192);


                outputFile= new File(path, "test.mp4"); //파일명까지 포함함 경로의 File 객체 생성

                // SD카드에 저장하기 위한 Output stream
                output = new FileOutputStream(outputFile);

                byte data[] = new byte[1024];
                int readByte;
                long downloadedSize = 0;
                while ((count = input.read(data)) != -1) {
                    //사용자가 BACK 버튼 누르면 취소가능
                    if (isCancelled()) {
                        input.close();
                        return "";
                    }

                    downloadedSize += count;

                    int same = 0;

//                    if (FileSize > 0) {
//                        float per = ((float)downloadedSize/FileSize) * 100;
//                        String str = "Downloaded " + downloadedSize + "KB / " + FileSize + "KB (" + (int)per + "%)";
//                        Log.i(TAG,"test :  "+ str);
//                        publishProgress("" + (int) ((downloadedSize * 100) / FileSize), str);
//
//                    }

                    //파일에 데이터를 기록합니다.
                    output.write(data, 0, count);
                }
                // Flush output
                output.flush();

                // Close streams
                output.close();
                input.close();

                editor.putString("videoFile",videoToDownload);
                editor.apply();

            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
            }finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {

                }
//                mWakeLock.release();
            }

            String downloadsPath = path + "/test.mp4";

            return downloadsPath;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            videoView.setVideoPath(s);
            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                    mp.start();
                }
            });
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
