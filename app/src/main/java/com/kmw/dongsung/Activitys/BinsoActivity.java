package com.kmw.dongsung.Activitys;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kmw.dongsung.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class BinsoActivity extends Activity {

    SharedPreferences pref;
    LinearLayout linearParent;
    TextView txtTitle,txtDeceasedName,txtRelationship,txtCheckIn,txtCheckOut,txtLocation;
    ImageView imgProfile;
    String entry ="",finish="";
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM월 dd일 HH:mm");
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMddHHmm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binso);

        pref = getSharedPreferences("dongsung",MODE_PRIVATE);

        FindViewById();

    }

    void FindViewById(){
        linearParent = (LinearLayout)findViewById(R.id.linear_binso_parent);
        txtTitle = (TextView)linearParent.findViewById(R.id.txt_one_division_item_title);
        txtDeceasedName = (TextView)linearParent.findViewById(R.id.txt_one_division_item_deceased_name);
        txtRelationship = (TextView)linearParent.findViewById(R.id.txt_one_division_item_relationship);
        txtCheckIn = (TextView)linearParent.findViewById(R.id.txt_one_division_item_check_in);
        txtCheckOut = (TextView)linearParent.findViewById(R.id.txt_one_division_item_check_out);
        txtLocation = (TextView)linearParent.findViewById(R.id.txt_one_division_item_location);
        imgProfile = (ImageView)linearParent.findViewById(R.id.img_one_division_item_deceased_profile);

        txtTitle.setText(pref.getString("binso_title",""));
        txtDeceasedName.setText("故 " + pref.getString("binso_deceased_name",""));
        txtRelationship.setText(pref.getString("binso_relationship",""));
        txtCheckIn.setText(pref.getString("binso_check_in",""));

        entry = pref.getString("binso_check_in","").replace("년","").replace("월","").replace("일","").replace("시","").replace("분","");
        finish = pref.getString("binso_check_out","").replace("년","").replace("월","").replace("일","").replace("시","").replace("분","");

        try {
            Date entryDate = simpleDateFormat1.parse(entry);
            Date finishDate = simpleDateFormat1.parse(finish);

            txtCheckOut.setText(simpleDateFormat.format(entryDate));
            txtLocation.setText(simpleDateFormat.format(finishDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
