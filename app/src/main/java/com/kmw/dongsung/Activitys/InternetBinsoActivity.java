package com.kmw.dongsung.Activitys;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
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
import android.widget.RelativeLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.iid.FirebaseInstanceId;
import com.kmw.dongsung.BuildConfig;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideApp;
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou;
import com.kmw.dongsung.Commons.Https.HttpClient;
import com.kmw.dongsung.Commons.ServerUrl.Server;
import com.kmw.dongsung.Commons.UpdateService;
import com.kmw.dongsung.R;
import com.kmw.dongsung.Views.CustomTextSwitcher;
import com.kmw.dongsung.Views.FitTextView;

import org.apache.commons.net.ntp.NTPUDPClient;
import org.apache.commons.net.ntp.TimeInfo;
import org.apmem.tools.layouts.FlowLayout;
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

import static android.content.Intent.makeRestartActivityTask;
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
public class  InternetBinsoActivity extends Activity implements View.OnClickListener {

    private String TAG = "InternetBinsoActivity";
    LinearLayout linearParent;
    ImageView imgBg;
    FitTextView txtRoomName,txtDeceasedName,txtTime,txtCheckIn,txtCheckOut,txtLocation;
    TextView txtCheckInTitle,txtCheckOutTitle,txtLocationTitle;
    LinearLayout linearRelation,linearRelationName;
    ImageView imgProfile;
//    TextView txtTest;
    LinearLayout linearRelationVisible,linearHoverGone;
    ImageView imgBackImg;
    Typeface typeface;
    ArrayList<TextView> txtRelationArray;
    ArrayList<TextView> txtRelationNamesArray;

    public static final String TIME_SERVER = "pool.ntp.org";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    SimpleDateFormat hangulDateFormat = new SimpleDateFormat("MM월 dd일 HH시 mm분");
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("MM월 dd일");
    Calendar calendar;
    String success = "";

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    int screenMode = 100;

    public RequestManager mGlideRequestManager;
    public RequestManager mGlideRequestManager1;

    TextView txtFirst,txtSecond;
    ImageView imgFirst,imgSecond;

    //hover
    LinearLayout linearHover;
    LinearLayout linearHoverVisible;

    private Button btnLogout,btnReboot,btnWifi,btnAuto,btnManual;
    private LinearLayout linearPopupVisible;
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

    private WifiManager wifiManager;
    private List<ScanResult> scanDatas;
    NetworkInfo wifi;
    ConnectivityManager manager;
    ArrayList<String> wifiSSIDArray = new ArrayList<>();
    InputMethodManager inputMethodManager;
    EditText editTextToken;
    private TextView txtSystemUpdate;
    Activity activity = InternetBinsoActivity.this;

    ArrayList<Integer> commentUserFlagList = new ArrayList<>();
    ArrayList<String> commentNameList  = new ArrayList<>();
    ArrayList<String> commentCommentList = new ArrayList<>();

    ArrayList<Spanned> imgGatterTextList = new ArrayList<>();

    LinearLayout linSwitFirst,linSwitSecond;

    ViewSwitcher textSwitcher;

    int currentIndex = 0;
    int messageCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler());
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_internet_binso);
        pref = getSharedPreferences("dongsung",MODE_PRIVATE);
        editor = pref.edit();

        mGlideRequestManager = Glide.with(this);
        mGlideRequestManager1 = GlideApp.with(this);

//        FindViewById();

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
        if (screenMode == 1 || screenMode == 100){
            linearParent = (LinearLayout)findViewById(R.id.linear_internet_binso_parent);
            imgBg = (ImageView) linearParent.findViewById(R.id.img_one_division_item_bg);
            txtRoomName = (FitTextView)linearParent.findViewById(R.id.txt_one_division_item_title);
            txtDeceasedName = (FitTextView)linearParent.findViewById(R.id.txt_one_division_item_deceased_name);
            txtTime = (FitTextView)linearParent.findViewById(R.id.txt_one_division_item_time);
            linearRelation = (LinearLayout)linearParent.findViewById(R.id.linear_one_division_item_relation_parent);
            linearRelationName = (LinearLayout)linearParent.findViewById(R.id.linear_one_division_item_relation_name_parent);
            txtCheckIn = (FitTextView)linearParent.findViewById(R.id.txt_one_division_item_check_in);
            txtCheckOut = (FitTextView)linearParent.findViewById(R.id.txt_one_division_item_check_out);
            txtLocation = (FitTextView)linearParent.findViewById(R.id.txt_one_division_item_location);
            imgProfile = (ImageView)linearParent.findViewById(R.id.img_one_division_item_deceased_profile);
//            txtTest = (TextView)linearParent.findViewById(R.id.txt_test_test);
            linearRelationVisible = (LinearLayout)linearParent.findViewById(R.id.linear_one_division_item_relation_visible);
            imgBackImg = (ImageView)linearParent.findViewById(R.id.img_one_division_item_back_img);
            txtCheckInTitle = (TextView)linearParent.findViewById(R.id.txt_one_division_item_check_in_title);
            txtCheckOutTitle = (TextView)linearParent.findViewById(R.id.txt_one_division_item_check_out_title);
            txtLocationTitle = (TextView)linearParent.findViewById(R.id.txt_one_division_item_check_location_title);

            textSwitcher = (ViewSwitcher) linearParent.findViewById(R.id.txt_swit_parent);
            linSwitFirst = (LinearLayout) linearParent.findViewById(R.id.lin_swit_first);

            txtFirst = (TextView)linearParent.findViewById(R.id.txt_swit_first);
            txtSecond = (TextView)linearParent.findViewById(R.id.txt_swit_second);

            imgFirst = (ImageView)linearParent.findViewById(R.id.img_swit_first);
            imgSecond = (ImageView)linearParent.findViewById(R.id.img_swit_second);

            textSwitcher.setInAnimation(this,R.anim.slide_in_bottom);
            textSwitcher.setOutAnimation(this,R.anim.slide_out_top);

        }else {
            imgBg = (ImageView) findViewById(R.id.img_one_division_item_bg);
            txtRoomName = (FitTextView)findViewById(R.id.txt_one_division_item_title);
            txtDeceasedName = (FitTextView)findViewById(R.id.txt_one_division_item_deceased_name);
            linearRelationName = (LinearLayout)findViewById(R.id.linear_one_division_item_relation_name_parent);
            txtCheckIn = (FitTextView)findViewById(R.id.txt_one_division_item_check_in);
            txtCheckOut = (FitTextView)findViewById(R.id.txt_one_division_item_check_out);
            txtLocation = (FitTextView)findViewById(R.id.txt_one_division_item_location);
            imgProfile = (ImageView)findViewById(R.id.img_one_division_item_deceased_profile);
//            txtTest = (TextView)findViewById(R.id.txt_test_test);
            imgBackImg = (ImageView)findViewById(R.id.img_one_division_item_back_img);
            linearRelationVisible = (LinearLayout)findViewById(R.id.linear_one_division_item_relation_visible);

            txtCheckInTitle = (TextView)findViewById(R.id.txt_one_division_item_check_in_title);
            txtCheckOutTitle = (TextView)findViewById(R.id.txt_one_division_item_check_out_title);
            txtLocationTitle = (TextView)findViewById(R.id.txt_one_division_item_check_location_title);

            textSwitcher = (ViewSwitcher) findViewById(R.id.txt_swit_parent);
            linSwitFirst = (LinearLayout) findViewById(R.id.lin_swit_first);

            txtFirst = (TextView)findViewById(R.id.txt_swit_first);
            txtSecond = (TextView)findViewById(R.id.txt_swit_second);

            imgFirst = (ImageView)findViewById(R.id.img_swit_first);
            imgSecond = (ImageView)findViewById(R.id.img_swit_second);

            textSwitcher.setInAnimation(this,R.anim.slide_in_bottom);
            textSwitcher.setOutAnimation(this,R.anim.slide_out_top);

            RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.rela_binso_main);

            mainLayout.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    int w = mainLayout.getWidth();
                    int h = mainLayout.getHeight();

                    mainLayout.setRotation(90.0f);
                    mainLayout.setTranslationX((w - h) / 2);
                    mainLayout.setTranslationY((h - w) / 2);

                    ViewGroup.LayoutParams lp = (ViewGroup.LayoutParams) mainLayout.getLayoutParams();
                    lp.height = w;
                    lp.width = h;
                    mainLayout.requestLayout();

                    mainLayout.getViewTreeObserver().removeOnPreDrawListener(this);
                    return true;
                }
            });
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

//    void CommentListMake(int flag, String name, String comment){
//        View listView = new View(this);
//        listView = getLayoutInflater().inflate(R.layout.view_obituary_list_item,null);
//        TextView txtComment = (TextView)listView.findViewById(R.id.txt_obituary_list_item_comment);
//
//        if (flag == 1){
//            imgIcon.setImageResource(R.drawable.ic_obituary_ribon);
//        }else{
//            imgIcon.setImageResource(R.drawable.ic_obituary_flower);
//        }
//
//        txtComment.setText(comment);
//
//        linReadListParent.addView(listView);
//    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            Date date = new Date(System.currentTimeMillis());
            calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.HOUR,9);

            if (screenMode == 1 || screenMode == 100){
                if (txtTime != null){
                    txtTime.setText(simpleDateFormat1.format(calendar.getTime()));
                }
            }
            // 메세지를 처리하고 또다시 핸들러에 메세지 전달 (1000ms 지연)
            mHandler.sendEmptyMessageDelayed(0,1000 * 60);
        }
    };

    void ListMake(int position, String relation, String name, float textSize, String textColor){

        if (position != 0){
            TextView linear = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,15);
            linear.setLayoutParams(params);

            linearRelationName.addView(linear);
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

        if (textColor.length() !=0){
            txtComma.setTextColor(Color.parseColor(textColor));
            txtRelation.setTextColor(Color.parseColor(textColor));
        }else{
            txtComma.setTextColor(getResources().getColor(R.color.colorBlack));
            txtRelation.setTextColor(getResources().getColor(R.color.colorBlack));
        }

        String[] strings = name.split(",");

        for (int i = 0; i < strings.length; i++){
            TextView txtName = new TextView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            txtName.setLayoutParams(layoutParams);

            txtName.setIncludeFontPadding(false);
            txtName.setTypeface(typeface);
            txtName.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);

            if (textColor.length() !=0){
                txtName.setTextColor(Color.parseColor(textColor));
            }else{
                txtName.setTextColor(getResources().getColor(R.color.colorBlack));
            }
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
        linearRelationName.addView(listView);
    }

    public class EarlyNetWork extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpClient.Builder http = new HttpClient.Builder("POST", Server.binsoDetailUrl());

            http.addOrReplace("order","EXPOSURE ASC, APPELLATION ASC, ENTRANCE_ROOM_DT");
            http.addOrReplace("statusPlate","true");
            http.addOrReplace("eventAliveFlag","true");
            http.addOrReplace("funeralNo",pref.getString("funeralNo",""));
            http.addOrReplace("raspberryConnectionNo",pref.getString("raspberryConnectionNo",""));

            HttpClient post = http.create();
            post.request();
            String body = post.getBody();

            Log.i(TAG, "EarlyNetWork check : " + body);

            return body;
        }

        @Override
        protected void onPostExecute(String s) {

            if (s.length() != 0){
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArrayInfo = jsonObject.getJSONArray("eventInfo");

                    if (jsonArrayInfo.length() == 0){
                        Intent intent = new Intent(InternetBinsoActivity.this,WaitingActivity.class);
                        startActivity(intent);
                    }else{
                        JSONObject jsonArrayStyle = null;

                        if (jsonObject.isNull("raspberryStatusPlate")){
                            setContentView(R.layout.activity_internet_binso);
                            screenMode = 1;
                            FindViewById();

                            typeface = Typeface.createFromAsset(getAssets(),"noto_sans_bold.ttf");

                        }else{
                            jsonArrayStyle = jsonObject.getJSONObject("raspberryStatusPlate");

                            if (!jsonArrayStyle.isNull("SCREEN_MODE")){
                                if (screenMode != JsonIntIsNullCheck(jsonArrayStyle,"SCREEN_MODE") || screenMode == 100){
                                    if (JsonIsNullCheck(jsonArrayStyle,"SCREEN_MODE").equals("1")){
                                        setContentView(R.layout.activity_internet_binso);
                                        screenMode = JsonIntIsNullCheck(jsonArrayStyle,"SCREEN_MODE");
                                        FindViewById();
                                    }else{
                                        setContentView(R.layout.activity_internet_binso_selo);
                                        screenMode = JsonIntIsNullCheck(jsonArrayStyle,"SCREEN_MODE");
                                        FindViewById();
                                    }
                                }

                                if (JsonIsNullCheck(jsonArrayStyle,"FONT_TYPE").length() == 0){
                                    typeface = Typeface.createFromAsset(getAssets(),"noto_sans_bold.ttf");
                                }else if (JsonIsNullCheck(jsonArrayStyle,"FONT_TYPE").equals("Noto Sans KR")){
                                    typeface = Typeface.createFromAsset(getAssets(),"noto_sans_bold.ttf");
                                }else if (JsonIsNullCheck(jsonArrayStyle,"FONT_TYPE").equals("nanumMyeongjoBold")){
                                    typeface = Typeface.createFromAsset(getAssets(),"nanum_myeongjo_bold.ttf");
                                }else if (JsonIsNullCheck(jsonArrayStyle,"FONT_TYPE").equals("unBatang")){
                                    typeface = Typeface.createFromAsset(getAssets(),"un_batang.ttf");
                                }else if (JsonIsNullCheck(jsonArrayStyle,"FONT_TYPE").equals("unDotum")){
                                    typeface = Typeface.createFromAsset(getAssets(),"undotum.ttf");
                                }else if (JsonIsNullCheck(jsonArrayStyle,"FONT_TYPE").equals("unGraphic")){
                                    typeface = Typeface.createFromAsset(getAssets(),"un_graphic.ttf");
                                }else if (JsonIsNullCheck(jsonArrayStyle,"FONT_TYPE").equals("unGungseo")){
                                    typeface = Typeface.createFromAsset(getAssets(),"un_gungseo.ttf");
                                }

                                editor.putString("textStyle",JsonIsNullCheck(jsonArrayStyle,"FONT_TYPE"));
                                editor.apply();
                            }
                        }

                        txtRoomName.setTypeface(typeface);
                        txtDeceasedName.setTypeface(typeface);
                        if (screenMode == 1 || screenMode == 100){
                            txtTime.setTypeface(typeface);
                            txtTime.invalidate();
                        }
                        txtCheckIn.setTypeface(typeface);
                        txtCheckOut.setTypeface(typeface);
                        txtLocation.setTypeface(typeface);
                        txtCheckInTitle.setTypeface(typeface);
                        txtCheckOutTitle.setTypeface(typeface);
                        txtLocationTitle.setTypeface(typeface);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                new BinsoNetWork().execute();
                            }
                        },1000);

                    }
                }catch (JSONException e){

                }
            }else{
                Intent intent = new Intent(InternetBinsoActivity.this,WaitingActivity.class);
                startActivity(intent);
            }
        }
    }

    public class BinsoNetWork extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {

            HttpClient.Builder http = new HttpClient.Builder("POST", Server.binsoDetailUrl());

            http.addOrReplace("order","EXPOSURE ASC, APPELLATION ASC, ENTRANCE_ROOM_DT"                      );
            http.addOrReplace("eventAliveFlag","true"                                                        );
            http.addOrReplace("statusPlate","true"                                                            );
            http.addOrReplace("funeralNo",pref.getString("funeralNo","")                        );
            http.addOrReplace("raspberryConnectionNo",pref.getString("raspberryConnectionNo",""));


            HttpClient post = http.create();
            post.request();
            String body = post.getBody();

            Log.i(TAG, "BinsoNetWork check : " + body);

            return body;
        }

        @Override
        protected void onPostExecute(String s) {

                try {
                    JSONObject jsonObject = new JSONObject(s);
                    JSONArray jsonArrayInfo = jsonObject.getJSONArray("eventInfo");

                    if (jsonArrayInfo.length() == 0){
                        Intent intent = new Intent(InternetBinsoActivity.this,WaitingActivity.class);
                        startActivity(intent);
                    }

                    JSONArray jsonArrayFamily = jsonObject.getJSONArray("eventFamilyInfo");
                    String cheefTextColor = "";

                    if (jsonObject.isNull("raspberryStatusPlate")){
                        txtDeceasedName.setTextColor(Color.parseColor("#ffffff"));
                        txtRoomName.setTextColor(Color.parseColor("#ffffff"));
                        txtCheckInTitle.setTextColor(Color.parseColor("#ffffff"));
                        txtCheckIn.setTextColor(Color.parseColor("#000000"));
                        txtCheckOutTitle.setTextColor(Color.parseColor("#ffffff"));
                        txtCheckOut.setTextColor(Color.parseColor("#000000"));
                        txtLocationTitle.setTextColor(Color.parseColor("#ffffff"));
                        txtLocation.setTextColor(Color.parseColor("#000000"));
                        cheefTextColor = "#000000";
//                        imgBg.setImageResource(R.drawable.img_binso_test_bg);
                        Uri uri = Uri.parse("http://211.251.237.150:7080/dsfiles/style/style_sample_01_horizontal.svg");
                        mGlideRequestManager1.load(uri).into(imgBg);
                    }else{
                        JSONObject jsonArrayStyle = jsonObject.getJSONObject("raspberryStatusPlate");

                        if (JsonIsNullCheck(jsonArrayStyle,"NAME").length()!=0){
                            txtDeceasedName.setTextColor(Color.parseColor(JsonIsNullCheck(jsonArrayStyle,"NAME")));
                        }else{
                            txtDeceasedName.setTextColor(Color.parseColor("#000000"));
                        }

                        if (JsonIsNullCheck(jsonArrayStyle,"STATUS").length()!=0){
                            txtRoomName.setTextColor(Color.parseColor(JsonIsNullCheck(jsonArrayStyle,"STATUS")));
                        }else{
                            txtRoomName.setTextColor(Color.parseColor("#000000"));
                        }

                        if (JsonIsNullCheck(jsonArrayStyle,"ER_START").length()!=0){
                            txtCheckInTitle.setTextColor(Color.parseColor(JsonIsNullCheck(jsonArrayStyle,"ER_START")));
                        }else{
                            txtCheckInTitle.setTextColor(Color.parseColor("#000000"));
                        }

                        if (JsonIsNullCheck(jsonArrayStyle,"ER_START_TIME").length()!=0){
                            txtCheckIn.setTextColor(Color.parseColor(JsonIsNullCheck(jsonArrayStyle,"ER_START_TIME")));
                        }else{
                            txtCheckIn.setTextColor(Color.parseColor("#000000"));
                        }

                        if (JsonIsNullCheck(jsonArrayStyle,"CARRING_START").length()!=0){
                            txtCheckOutTitle.setTextColor(Color.parseColor(JsonIsNullCheck(jsonArrayStyle,"CARRING_START")));
                        }else{
                            txtCheckOutTitle.setTextColor(Color.parseColor("#000000"));
                        }

                        if (JsonIsNullCheck(jsonArrayStyle,"CARRING_START_TIME").length()!=0){
                            txtCheckOut.setTextColor(Color.parseColor(JsonIsNullCheck(jsonArrayStyle,"CARRING_START_TIME")));
                        }else{
                            txtCheckOut.setTextColor(Color.parseColor("#000000"));
                        }

                        if (JsonIsNullCheck(jsonArrayStyle,"BURIAL_PLOT").length()!=0){
                            txtLocationTitle.setTextColor(Color.parseColor(JsonIsNullCheck(jsonArrayStyle,"BURIAL_PLOT")));
                        }else{
                            txtLocationTitle.setTextColor(Color.parseColor("#000000"));
                        }

                        if (JsonIsNullCheck(jsonArrayStyle,"BURIAL_PLOT_NAME").length()!=0){
                            txtLocation.setTextColor(Color.parseColor(JsonIsNullCheck(jsonArrayStyle,"BURIAL_PLOT_NAME")));
                        }else{
                            txtLocation.setTextColor(Color.parseColor("#000000"));
                        }

                        if (JsonIsNullCheck(jsonArrayStyle,"CHIEF_MOURNER").length()!=0){
                            cheefTextColor = JsonIsNullCheck(jsonArrayStyle,"CHIEF_MOURNER");
                        }else{
                            cheefTextColor = "#000000";
                        }

                        Uri uri = null;

                        if (JsonIsNullCheck(jsonArrayStyle,"STATUS_PLATE_STYLE_FILE").length() > 0){
                            uri = Uri.parse(JsonIsNullCheck(jsonArrayStyle,"STATUS_PLATE_STYLE_FILE"));
//                            GlideApp.with(InternetBinsoActivity.this).load(uri).into(imgBg);
                            mGlideRequestManager1.load(uri).into(imgBg);
//                            GlideToVectorYou.justLoadImage(InternetBinsoActivity.this,uri,imgBg);
                        }
                    }

                    Uri uri = null;

                    if (jsonArrayInfo.length() != 0){
                        JSONObject object = jsonArrayInfo.getJSONObject(0);

//                        if (simpleDateFormat.parse(JsonIsNullCheck(object,"ENTRANCE_ROOM_DT").replace("T"," ")).after(simpleDateFormat.parse(simpleDateFormat.format(new Date(System.currentTimeMillis()))))){
//                            Intent intent = new Intent(InternetBinsoActivity.this,WaitingActivity.class);
//                            startActivity(intent);
//                        }else{
                            if (JsonIsNullCheck(object,"STATUS_PLATE_BG").length() > 0){
                                uri = Uri.parse(JsonIsNullCheck(object,"STATUS_PLATE_BG"));
                                if (JsonIsNullCheck(object,"STATUS_PLATE_BG").contains("svg")){
//                                    GlideApp.with(InternetBinsoActivity.this).load(uri).into(imgBackImg);
                                    mGlideRequestManager1.load(uri).into(imgBackImg);
//                                GlideToVectorYou.justLoadImage(InternetBinsoActivity.this,uri,imgBackImg);
                                }else{
                                    mGlideRequestManager.load(JsonIsNullCheck(object,"STATUS_PLATE_BG")).into(imgBackImg);
                                }
                            }

                            String gender = JsonIsNullCheck(object,"DM_GENDER");

                            if (gender.equals("1")){
                                gender = "남";
                            }else if (gender.equals("2")){
                                gender = "여";
                            }

                            txtRoomName.setText(JsonIsNullCheck(object,"APPELLATION"));

                            if (JsonIsNullCheck(object,"DM_POSITION").length() != 0){
                                if (JsonIsNullCheck(object,"DM_GENDER").length() != 0){
                                    if (JsonIsNullCheck(object,"DM_AGE").length() != 0){
                                        if (screenMode == 1 || screenMode == 100){
                                            txtDeceasedName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + JsonIsNullCheck(object,"DM_POSITION") + " " + "(" + gender + ", "+ JsonIsNullCheck(object,"DM_AGE") + "세)");
                                        }else{
                                            txtDeceasedName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + JsonIsNullCheck(object,"DM_POSITION") + "\n" + "(" + gender + ", "+ JsonIsNullCheck(object,"DM_AGE") + "세)");
                                        }
                                    }else{
                                        if (screenMode == 1 || screenMode == 100){
                                            txtDeceasedName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + JsonIsNullCheck(object,"DM_POSITION") + " " + "(" + gender + ", "+ JsonIsNullCheck(object,"DM_AGE") + "세)");
                                        }else{
                                            txtDeceasedName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + JsonIsNullCheck(object,"DM_POSITION") + "\n" + "(" + gender + ")");
                                        }
                                    }
                                }else{
                                    if (JsonIsNullCheck(object,"DM_AGE").length() != 0){
                                        if (screenMode == 1 || screenMode == 100){
                                            txtDeceasedName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + JsonIsNullCheck(object,"DM_POSITION") + " " + "(" + JsonIsNullCheck(object,"DM_AGE") + "세)");
                                        }else{
                                            txtDeceasedName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + JsonIsNullCheck(object,"DM_POSITION") + "\n" + "(" + JsonIsNullCheck(object,"DM_AGE") + "세)");
                                        }
                                    }else{
                                        if (screenMode == 1 || screenMode == 100){
                                            txtDeceasedName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + JsonIsNullCheck(object,"DM_POSITION"));
                                        }else{
                                            txtDeceasedName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + JsonIsNullCheck(object,"DM_POSITION"));
                                        }
                                    }
                                }
                            }else{
                                if (JsonIsNullCheck(object,"DM_GENDER").length() != 0){
                                    if (JsonIsNullCheck(object,"DM_AGE").length() != 0){
                                        if (screenMode == 1 || screenMode == 100){
                                            txtDeceasedName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + "(" + gender + ", "+ JsonIsNullCheck(object,"DM_AGE") + "세)");
                                        }else{
                                            txtDeceasedName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + "\n" + "(" + gender + ", "+ JsonIsNullCheck(object,"DM_AGE") + "세)");
                                        }
                                    }else{
                                        if (screenMode == 1 || screenMode == 100){
                                            txtDeceasedName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + "(" + gender + ")");
                                        }else{
                                            txtDeceasedName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + "\n" + "(" + gender + ")");
                                        }
                                    }
                                }else{
                                    if (JsonIsNullCheck(object,"DM_AGE").length() != 0){
                                        if (screenMode == 1 || screenMode == 100){
                                            txtDeceasedName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + " " + "(" + JsonIsNullCheck(object,"DM_AGE") + "세)");
                                        }else{
                                            txtDeceasedName.setText("故 " + JsonIsNullCheck(object,"DM_NAME") + "\n" + "(" + JsonIsNullCheck(object,"DM_AGE") + "세)");
                                        }
                                    }else{
                                        if (screenMode == 1 || screenMode == 100){
                                            txtDeceasedName.setText("故 " + JsonIsNullCheck(object,"DM_NAME"));
                                        }else{
                                            txtDeceasedName.setText("故 " + JsonIsNullCheck(object,"DM_NAME"));
                                        }
                                    }
                                }
                            }

                            if (JsonIsNullCheck(object,"ENTRANCE_ROOM_NO").length() == 0){
                                txtCheckIn.setText("-");
                            }else{
                                txtCheckIn.setText(hangulDateFormat.format(simpleDateFormat.parse(JsonIsNullCheck(object,"ENTRANCE_ROOM_START_DT").replace("T"," "))));
                            }


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

                            linearRelationName.removeAllViews();

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
                                    relatest += JsonIsNullCheck(objectFamily,"RELATION") + " : " + JsonIsNullCheck(objectFamily,"NAMES") + "\n";
                                }

//                            txtTest.setText(relatest);

                                linearRelationVisible.setVisibility(View.VISIBLE);
//                            txtTest.setVisibility(View.GONE);

                                for (int i = 0; i < jsonArrayFamily.length(); i++){
                                    JSONObject objectFamily = jsonArrayFamily.getJSONObject(i);
                                    ListMake(i,JsonIsNullCheck(objectFamily,"RELATION"),JsonIsNullCheck(objectFamily,"NAMES").replace("^#$%&PB$@!",","), 70f,cheefTextColor);
                                }

                                final int finalPosition = position;

                                linearRelationName.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                                    @Override
                                    public boolean onPreDraw() {

                                        txtLocation.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtCheckIn.getTextSize());
                                        txtLocationTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtCheckIn.getTextSize());

                                        if (linearRelationName.getChildCount() != 0) {

                                            int child1 = 0;

                                            for (int j = 0; j < linearRelationName.getChildCount(); j++) {
                                                child1 = child1 + linearRelationName.getChildAt(j).getMeasuredHeight();
                                            }

                                            if (child1 >= (linearRelationName.getHeight())) {
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

                                                for (int j = 0; j < linearRelationName.getChildCount(); j++){
                                                    child2 = child2 + linearRelationName.getChildAt(j).getMeasuredHeight();
                                                }

                                                if (child2 >= (linearRelationName.getHeight())) {

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

                                                    linearRelationName.getViewTreeObserver().removeOnPreDrawListener(this);
                                                }
                                            }
                                        } else {
                                            linearRelationName.getViewTreeObserver().removeOnPreDrawListener(this);
                                        }

                                        return true;
                                    }
                                });
                            }
//                        }
                    }else{

                    }

                    if (mHandler1 != null){
                        mHandler1.removeMessages(0);
                    }

                    new SelectObituaryListNetWork().execute(jsonArrayInfo.getJSONObject(0).getString("EVENT_NO"));

                }catch (JSONException e){

                } catch (ParseException e) {
                    e.printStackTrace();
                }
        }
    }

    public class SelectObituaryListNetWork extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            HttpClient.Builder http = new HttpClient.Builder("POST", Server.selectObituaryList());

            http.addOrReplace("eventNo",strings[0]);

            HttpClient post = http.create();
            post.request();
            String body = post.getBody();

            Log.i(TAG, "SelectObituaryListNetWork check : " + body);

            return body;
        }

        @Override
        protected void onPostExecute(String s) {

            commentUserFlagList = new ArrayList<>();
            commentNameList = new ArrayList<>();
            commentCommentList = new ArrayList<>();
            imgGatterTextList = new ArrayList<>();

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("list");

                messageCount = jsonArray.length();

                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject object = jsonArray.getJSONObject(i);
                    commentUserFlagList.add(JsonIntIsNullCheck(object,"USER_FLAG"));
                    commentNameList.add(JsonIsNullCheck(object,"NAME"));
                    commentCommentList.add(JsonIsNullCheck(object,"COMMENT_NO"));
                }

                if (commentCommentList.size() > 0) {
                    if (commentCommentList.size() == 1){
                        if (commentUserFlagList.get(0) == 1){
                            imgFirst.setImageResource(R.drawable.ic_obituary_ribon);
                            txtFirst.setTextColor(Color.parseColor("#ffffff"));
                        }else{
                            imgFirst.setImageResource(R.drawable.ic_obituary_flower);
                            txtFirst.setTextColor(Color.parseColor("#f8cf4b"));
                        }
                        txtFirst.setText("'"+commentCommentList.get(currentIndex)+"'" + " " + commentNameList.get(currentIndex) + " 배상");
                        txtFirst.setTypeface(typeface);
                    }else{
                        mHandler1.sendEmptyMessage(0);
                    }
                }
            } catch (JSONException e) {

            }
        }
    }

    public Spanned imageGetter(int flag, String comment, String name){
        Html.ImageGetter imageGetter = new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String source) {

                if ( flag == 1){
                    Drawable drawable = getResources().getDrawable( R.drawable.ic_obituary_ribon);
                    drawable.setBounds( 0, 0, (int) (drawable.getIntrinsicWidth()/2.5), (int) (drawable.getIntrinsicHeight()/2.5));
                    return drawable;
                }else{
                    Drawable drawable = getResources().getDrawable( R.drawable.ic_obituary_flower);
                    drawable.setBounds( 0, 0, (int) (drawable.getIntrinsicWidth()/2.5), (int) (drawable.getIntrinsicHeight()/2.5));
                    return drawable;
                }
            }
        };

        Spanned htmlText = Html.fromHtml( "<font color='black'>조 문</font> <img src=\"icon\" width=25 height=25>" + "<font color='yellow'>"+comment+"</font> ", imageGetter, null);

        return htmlText;
    }

    public View CommentListMake(int idx, int flag, String name, String comment){
        View listView = new View(this);
        listView = getLayoutInflater().inflate(R.layout.view_obituary_list_item,null);

//        if (flag == 1){
//            imgIcon.setImageResource(R.drawable.ic_obituary_ribon);
//        }else{
//            imgIcon.setImageResource(R.drawable.ic_obituary_flower);
//        }

        return listView;
    }

    public Handler mHandler1 = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            mHandler1.sendEmptyMessageDelayed(0,3000);

            if (currentIndex == (messageCount -1)) {
                currentIndex = 0;
            }else{
                currentIndex++;
            }
            // If index reaches maximum reset it

            if (currentIndex % 2 == 0){
                if (commentUserFlagList.get(currentIndex) == 1){
                    imgFirst.setImageResource(R.drawable.ic_obituary_ribon);
                    txtFirst.setTextColor(Color.parseColor("#ffffff"));
                }else{
                    imgFirst.setImageResource(R.drawable.ic_obituary_flower);
                    txtFirst.setTextColor(Color.parseColor("#f8cf4b"));
                }
                txtFirst.setText("'"+commentCommentList.get(currentIndex)+"'" + " " + commentNameList.get(currentIndex) + " 배상");
                txtFirst.setTypeface(typeface);
            }else{
                if (commentUserFlagList.get(currentIndex) == 1){
                    imgSecond.setImageResource(R.drawable.ic_obituary_ribon);
                    txtSecond.setTextColor(Color.parseColor("#ffffff"));
                }else{
                    imgSecond.setImageResource(R.drawable.ic_obituary_flower);
                    txtSecond.setTextColor(Color.parseColor("#f8cf4b"));
                }
                txtSecond.setText("'"+commentCommentList.get(currentIndex)+"'" + " " + commentNameList.get(currentIndex) + " 배상");
                txtSecond.setTypeface(typeface);
            }

//            textSwitcher.setText(imgGatterTextList.get(currentIndex));
            textSwitcher.showNext();

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
                Intent intent = new Intent(InternetBinsoActivity.this, LoginActivity.class);
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
                Intent intent = new Intent(InternetBinsoActivity.this, ModeSelectActivity.class);
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
        new EarlyNetWork().execute();
        mHandler.sendEmptyMessage(0);
    }

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
    protected void onDestroy() {
        super.onDestroy();
    }

    String intentData = "";

    private BroadcastReceiver mUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub // Get extra data included in the Intent
            String message = intent.getStringExtra("message");
            String status = intent.getStringExtra("status");
            String data = intent.getStringExtra("data");

            if (message.equals("update") && status.equals("빈소")){
                if (data.length() != 0){
                    if (data.contains(pref.getString("raspberryConnectionNo",""))){
                        new EarlyNetWork().execute();
                    }
                }else{
                    new EarlyNetWork().execute();
                }
            }else if (message.equals("check") && status.equals("빈소")){
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
            } else if (message.equals("system")){
                txtSystemUpdate.setVisibility(View.VISIBLE);
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