package com.kmw.dongsung.Activitys;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kmw.dongsung.Func.GeneralViewPagerAdater;
import com.kmw.dongsung.Func.InfiniteFunc.InfinitePagerAdapter;
import com.kmw.dongsung.Func.InfiniteFunc.InfiniteViewPager;
import com.kmw.dongsung.R;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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

public class GeneralActivity extends Activity {

    String TAG = "GeneralActivity";
    InfiniteViewPager viewPager;
    InfinitePagerAdapter infinitePagerAdapter;
    GeneralViewPagerAdater adater;
    ArrayList<String> titleArray = new ArrayList<>();
    ArrayList<String> changeTitleArray = new ArrayList<>();
    ArrayList<String> deceasedArray = new ArrayList<>();
    ArrayList<String> imgPathArray = new ArrayList<>();
    ArrayList<String> relationshipArray = new ArrayList<>();
    ArrayList<String> checkInArray = new ArrayList<>();
    ArrayList<String> checkOutArray = new ArrayList<>();
    ArrayList<String> locationArray = new ArrayList<>();
    ArrayList<String> changeCheckInArray = new ArrayList<>();
    ArrayList<String> changeCheckOutArray = new ArrayList<>();
    ArrayList<String> changeLocationArray = new ArrayList<>();

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Timer timer;
    int position = 0;
    Handler handler;
    Runnable Update;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM월 dd일 HH:mm");
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMddHHmm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        pref = getSharedPreferences("dongsung",MODE_PRIVATE);

        FindViewById();

        Gson gson = new Gson();
        String title = pref.getString("general_title","");
        String deceasedName = pref.getString("general_deceased_name","");
        String relationship = pref.getString("general_relationship","");
        String checkIn = pref.getString("general_checkIn","");
        String checkOut = pref.getString("general_checkOut","");
        String location = pref.getString("general_location","");
        Type type = new TypeToken<ArrayList<String>>(){}.getType();

        titleArray = gson.fromJson(title,type);
        deceasedArray = gson.fromJson(deceasedName,type);
        relationshipArray = gson.fromJson(relationship,type);
        checkInArray = gson.fromJson(checkIn,type);
        checkOutArray = gson.fromJson(checkOut,type);
        locationArray = gson.fromJson(location,type);

        for (int i = 0; i < checkInArray.size(); i++){
            changeTitleArray.add("故 " + titleArray.get(i));
            try {
                Date entryDate = simpleDateFormat1.parse(checkInArray.get(i).replace("년","").replace("월","").replace("일","").replace("시","").replace("분",""));
                Date finishDate = simpleDateFormat1.parse(checkOutArray.get(i).replace("년","").replace("월","").replace("일","").replace("시","").replace("분",""));

                if (checkInArray.size() > 7){
                    changeCheckInArray.add("입 관 "+simpleDateFormat.format(entryDate));
                    changeCheckOutArray.add("발 인 "+simpleDateFormat.format(finishDate));
                }else{
                    changeCheckInArray.add(simpleDateFormat.format(entryDate));
                    changeCheckOutArray.add(simpleDateFormat.format(finishDate));
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (checkInArray.size() > 7){
                changeLocationArray.add("장 지 "+locationArray.get(i));
            }else{
                changeLocationArray.add(locationArray.get(i));
            }

        }


        adater = new GeneralViewPagerAdater(getLayoutInflater(),this,titleArray.size(),0);

        for (int i = 0; i < titleArray.size(); i++){
            adater.addItem(titleArray,deceasedArray,relationshipArray,changeCheckInArray,changeCheckOutArray,changeLocationArray,imgPathArray);
        }

        infinitePagerAdapter = new InfinitePagerAdapter(adater);

        viewPager.setAdapter(infinitePagerAdapter);

        handler = new Handler();
        Update = new Runnable() {
            @Override
            public void run() {
                viewPager.setCurrentItem(position++,true);
            }
        };

        timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (titleArray.size() > 8){
                    handler.post(Update);
                }
            }
        }, 5000, 5000);
    }

    void FindViewById(){
        viewPager = (InfiniteViewPager)findViewById(R.id.view_pager_general);
    }
}
