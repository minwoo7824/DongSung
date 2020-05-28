package com.kmw.dongsung.Activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.kmw.dongsung.Activitys.Insert.BinsoInsertActivity;
import com.kmw.dongsung.Activitys.Insert.DeceasedInsertActivity;
import com.kmw.dongsung.Activitys.Insert.EntryInsertActivity;
import com.kmw.dongsung.Activitys.Insert.GeneralInsertActivity;
import com.kmw.dongsung.R;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

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
public class ModeSelectActivity extends Activity implements View.OnClickListener {

    private String TAG = "ModeSelectActivity";
    Button btnBack,btnBinso,btnDeceased,btnGeneral,btnEntry;
    Button btnBinsoDone,btnDeceasedDone,btnGeneralDone,btnEntryDone;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_select);

//        registerReceiver(mUsbDeviceReceiver, new IntentFilter(UsbManager.ACTION_USB_DEVICE_ATTACHED));
//        registerReceiver(mUsbDeviceReceiver, new IntentFilter(UsbManager.ACTION_USB_DEVICE_DETACHED));

        FindViewById();

        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
        while(deviceIterator.hasNext()){
            UsbDevice device = deviceIterator.next();
            //your code
            Log.i(TAG,"device : " + device.getDeviceName());
        }

        //content://com.android.mtp.documents/root

//        String path = "content://media/SAMSUNG_Android Phone";
//
//        Log.i(TAG,"path : " + path);
//
//        File directory = new File(path);
//
//        if (directory.exists()){
//            Log.i(TAG,"yes");
//            File[] files = directory.listFiles();
//
//            Log.i(TAG,"size : " + files.length);
//        }else {
//            Log.i(TAG,"no");
//        }
    }

    private final BroadcastReceiver mUsbDeviceReceiver =
            new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    String action = intent.getAction();
                    if (UsbManager.ACTION_USB_DEVICE_ATTACHED.equals(action)) {
//                        pickImage();
                        UsbManager manager = (UsbManager) getSystemService(Context.USB_SERVICE);
                        HashMap<String, UsbDevice> deviceList = manager.getDeviceList();
                        Iterator<UsbDevice> deviceIterator = deviceList.values().iterator();
                        while(deviceIterator.hasNext()){
                            UsbDevice device = deviceIterator.next();
                            //your code
                            Log.i(TAG,"device : " + device.getDeviceName());
                        }

                    }else if (UsbManager.ACTION_USB_DEVICE_DETACHED.equals(action)) {
//                        try {
//                            Runtime.getRuntime().exec("input keyevent 4");
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
                        Log.i(TAG,"2");
                    }
                }
            };

    void FindViewById(){
        btnBack = (Button)findViewById(R.id.btn_mode_select_back);
        btnBinso = (Button)findViewById(R.id.btn_mode_select_binso);
        btnDeceased = (Button)findViewById(R.id.btn_mode_select_deceased);
        btnGeneral = (Button)findViewById(R.id.btn_mode_select_general);
        btnEntry = (Button)findViewById(R.id.btn_mode_select_entry);
        btnBinsoDone = (Button)findViewById(R.id.btn_mode_select_binso_done);
        btnDeceasedDone = (Button)findViewById(R.id.btn_mode_select_deceased_done);
        btnGeneralDone = (Button)findViewById(R.id.btn_mode_select_general_done);
        btnEntryDone = (Button)findViewById(R.id.btn_mode_select_entry_done);

        btnBack.setOnClickListener(this);
        btnBinso.setOnClickListener(this);
        btnDeceased.setOnClickListener(this);
        btnGeneral.setOnClickListener(this);
        btnEntry.setOnClickListener(this);
        btnBinsoDone.setOnClickListener(this);
        btnDeceasedDone.setOnClickListener(this);
        btnGeneralDone.setOnClickListener(this);
        btnEntryDone.setOnClickListener(this);
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT).setType("image/*");
        try {
            startActivity(intent);
            Log.i(TAG,"1");
        } catch (ActivityNotFoundException e) {
            Log.i(TAG,"3");
            Toast.makeText(this, "No image source available", Toast.LENGTH_SHORT).show();
        }
        Log.i(TAG,"2");
    }

    @Override
    public void onClick(View v) {

        Intent intent;

        switch (v.getId()){
            case R.id.btn_mode_select_back : {
                onBackPressed();
                break;
            }
            case R.id.btn_mode_select_binso : {
                Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show();
                intent = new Intent(this, BinsoInsertActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_mode_select_deceased : {
                Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show();
                intent = new Intent(this, DeceasedInsertActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_mode_select_general : {
                Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show();
                intent = new Intent(this, GeneralInsertActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_mode_select_entry : {
                Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show();
                intent = new Intent(this, EntryInsertActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_mode_select_binso_done : {
                Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show();
                intent = new Intent(this, BinsoActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_mode_select_deceased_done : {
                Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show();
                intent = new Intent(this, TestActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_mode_select_general_done : {
                Toast.makeText(this, "Loading...", Toast.LENGTH_LONG).show();
                intent = new Intent(this, GeneralActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_mode_select_entry_done : {

                break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unregisterReceiver(mUsbDeviceReceiver);
    }
}
