package com.kmw.dongsung.Activitys.Insert;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.kmw.dongsung.R;

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
public class DeceasedInsertActivity extends Activity {

    Button btnBack,btnSave;
    ImageView imgProfile;
    Button btnProfileInsert,btnProfileRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deceased_insert);

        FindViewById();
    }

    void FindViewById(){
        btnBack = (Button)findViewById(R.id.btn_deceased_insert_back);
        btnSave = (Button)findViewById(R.id.btn_deceased_insert_save);
        imgProfile = (ImageView)findViewById(R.id.img_deceased_insert_profile);
        btnProfileInsert = (Button)findViewById(R.id.btn_deceased_insert_profile_save);
        btnProfileRemove = (Button)findViewById(R.id.btn_deceased_insert_profile_remove);
    }
}
