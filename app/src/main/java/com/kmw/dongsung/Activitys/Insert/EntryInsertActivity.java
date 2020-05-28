package com.kmw.dongsung.Activitys.Insert;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

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
public class EntryInsertActivity extends Activity implements View.OnClickListener {

    Button btnBack,btnSave;
    Button btnRemove;
    EditText edtBinsoName,edtDeceasedName,edtAge,edtReligion,edtSpot,edtLocation;
    EditText edtRelationship;
    Spinner spnEntryYear,spnEntryMonth,spnEntryDay,spnEntryHour,spnEntryMinute;
    Spinner spnFinishYear,spnFinishMonth,spnFinishDay,spnFinishHour,spnFinishMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry_insert);

        FindViewById();

    }

    void FindViewById(){
        btnBack = (Button)findViewById(R.id.btn_entry_insert_back);
        btnSave = (Button)findViewById(R.id.btn_entry_insert_save);
        btnRemove = (Button)findViewById(R.id.btn_entry_insert_remove);
        edtBinsoName = (EditText)findViewById(R.id.edt_entry_insert_binso_name);
        edtDeceasedName = (EditText)findViewById(R.id.edt_entry_insert_deceased_name);
        edtAge = (EditText)findViewById(R.id.edt_entry_insert_deceased_age);
        edtReligion = (EditText)findViewById(R.id.edt_entry_insert_religion);
        edtSpot = (EditText)findViewById(R.id.edt_entry_insert_spot);
        edtLocation = (EditText)findViewById(R.id.edt_entry_insert_location);
        edtRelationship = (EditText)findViewById(R.id.edt_entry_insert_relationship);

        spnEntryYear = (Spinner)findViewById(R.id.spn_entry_insert_entry_year);
        spnEntryMonth = (Spinner)findViewById(R.id.spn_entry_insert_entry_month);
        spnEntryDay = (Spinner)findViewById(R.id.spn_entry_insert_entry_day);
        spnEntryHour = (Spinner)findViewById(R.id.spn_entry_insert_entry_hour);
        spnEntryMinute = (Spinner)findViewById(R.id.spn_entry_insert_entry_minute);

        spnFinishYear = (Spinner)findViewById(R.id.spn_entry_insert_finish_year);
        spnFinishMonth = (Spinner)findViewById(R.id.spn_entry_insert_finish_month);
        spnFinishDay = (Spinner)findViewById(R.id.spn_entry_insert_finish_day);
        spnFinishHour = (Spinner)findViewById(R.id.spn_entry_insert_finish_hour);
        spnFinishMinute = (Spinner)findViewById(R.id.spn_entry_insert_finish_minute);

        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_entry_insert_back : {
                onBackPressed();
                break;
            }
        }
    }
}
