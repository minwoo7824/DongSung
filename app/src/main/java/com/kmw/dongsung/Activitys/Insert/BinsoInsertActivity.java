package com.kmw.dongsung.Activitys.Insert;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.kmw.dongsung.Commons.HangulInput;
import com.kmw.dongsung.Commons.HangulVersionArray;
import com.kmw.dongsung.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.security.AccessController.getContext;

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
public class BinsoInsertActivity extends Activity implements View.OnClickListener, View.OnFocusChangeListener {

    private String TAG = "BinsoInsertActivity";
    Button btnBack,btnSave;
    ImageView imgProfile;
    Button btnProfileInsert,btnProfileRemove;
    EditText edtBinsoName,edtDeceasedName,edtAge,edtReligion,edtSpot,edtLocation;
    EditText edtRelationship;
    Spinner spnEntryYear,spnEntryMonth,spnEntryDay,spnEntryHour,spnEntryMinute;
    Spinner spnFinishYear,spnFinishMonth,spnFinishDay,spnFinishHour,spnFinishMinute;

    HangulInput hangulInput;
    boolean shiftStatus = false;
    boolean hanYoung = false;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    String entry="",finish="";

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM월 dd일 HH:mm");
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMddHHmm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binso_insert);

        pref = getSharedPreferences("dongsung",MODE_PRIVATE);
        editor = pref.edit();

        FindViewById();

        spinnerAdapterSetting();

    }

    void FindViewById(){
        btnBack = (Button)findViewById(R.id.btn_binso_insert_back);
        btnSave = (Button)findViewById(R.id.btn_binso_insert_save);
        imgProfile = (ImageView)findViewById(R.id.img_binso_insert_profile);
        btnProfileInsert = (Button)findViewById(R.id.btn_binso_insert_profile_save);
        btnProfileRemove = (Button)findViewById(R.id.btn_binso_insert_profile_remove);

        edtBinsoName = (EditText)findViewById(R.id.edt_binso_insert_binso_name);
        edtDeceasedName = (EditText)findViewById(R.id.edt_binso_insert_deceased_name);
        edtAge = (EditText)findViewById(R.id.edt_binso_insert_deceased_age);
        edtReligion = (EditText)findViewById(R.id.edt_binso_insert_religion);
        edtSpot = (EditText)findViewById(R.id.edt_binso_insert_spot);
        edtLocation = (EditText)findViewById(R.id.edt_binso_insert_location);
        edtRelationship = (EditText)findViewById(R.id.edt_binso_insert_relationship);

        spnEntryYear = (Spinner)findViewById(R.id.spn_binso_insert_entry_year);
        spnEntryMonth = (Spinner)findViewById(R.id.spn_binso_insert_entry_month);
        spnEntryDay = (Spinner)findViewById(R.id.spn_binso_insert_entry_day);
        spnEntryHour = (Spinner)findViewById(R.id.spn_binso_insert_entry_hour);
        spnEntryMinute = (Spinner)findViewById(R.id.spn_binso_insert_entry_minute);

        spnFinishYear = (Spinner)findViewById(R.id.spn_binso_insert_finish_year);
        spnFinishMonth = (Spinner)findViewById(R.id.spn_binso_insert_finish_month);
        spnFinishDay = (Spinner)findViewById(R.id.spn_binso_insert_finish_day);
        spnFinishHour = (Spinner)findViewById(R.id.spn_binso_insert_finish_hour);
        spnFinishMinute = (Spinner)findViewById(R.id.spn_binso_insert_finish_minute);

        btnBack.setOnClickListener(this);
        btnSave.setOnClickListener(this);

        edtBinsoName.setOnFocusChangeListener(this);
        edtDeceasedName.setOnFocusChangeListener(this);
        edtAge.setOnFocusChangeListener(this);
        edtReligion.setOnFocusChangeListener(this);
        edtSpot.setOnFocusChangeListener(this);
        edtLocation.setOnFocusChangeListener(this);
        edtRelationship.setOnFocusChangeListener(this);

        edtBinsoName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    Log.i(TAG,"i : " + i + " keyEvent : " + keyEvent.getKeyCode());
                    if (hanYoung){
                        //delete
                        if (i == 67){
                            delete();
                        }
                        //shift
                        else if (i == 59 || i == 60){
                            shiftStatus = true;
                        }else {
                            hangul(HangulVersionArray.ReturnText(i,shiftStatus),edtBinsoName);
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

        edtDeceasedName.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    Log.i(TAG,"i : " + i + " keyEvent : " + keyEvent.getKeyCode());
                    if (hanYoung){
                        //delete
                        if (i == 67){
                            delete();
                        }
                        //shift
                        else if (i == 59 || i == 60){
                            shiftStatus = true;
                        }else {
                            hangul(HangulVersionArray.ReturnText(i,shiftStatus),edtDeceasedName);
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

        edtAge.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    Log.i(TAG,"i : " + i + " keyEvent : " + keyEvent.getKeyCode());
                    if (hanYoung){
                        //delete
                        if (i == 67){
                            delete();
                        }
                        //shift
                        else if (i == 59 || i == 60){
                            shiftStatus = true;
                        }else {
                            hangul(HangulVersionArray.ReturnText(i,shiftStatus),edtAge);
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

        edtReligion.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    Log.i(TAG,"i : " + i + " keyEvent : " + keyEvent.getKeyCode());
                    if (hanYoung){
                        //delete
                        if (i == 67){
                            delete();
                        }
                        //shift
                        else if (i == 59 || i == 60){
                            shiftStatus = true;
                        }else {
                            hangul(HangulVersionArray.ReturnText(i,shiftStatus),edtReligion);
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

        edtSpot.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    Log.i(TAG,"i : " + i + " keyEvent : " + keyEvent.getKeyCode());
                    if (hanYoung){
                        //delete
                        if (i == 67){
                            delete();
                        }
                        //shift
                        else if (i == 59 || i == 60){
                            shiftStatus = true;
                        }else {
                            hangul(HangulVersionArray.ReturnText(i,shiftStatus),edtSpot);
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

        edtLocation.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    Log.i(TAG,"i : " + i + " keyEvent : " + keyEvent.getKeyCode());
                    if (hanYoung){
                        //delete
                        if (i == 67){
                            delete();
                        }
                        //shift
                        else if (i == 59 || i == 60){
                            shiftStatus = true;
                        }else {
                            hangul(HangulVersionArray.ReturnText(i,shiftStatus),edtLocation);
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

        edtRelationship.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                    Log.i(TAG,"i : " + i + " keyEvent : " + keyEvent.getKeyCode());
                    if (hanYoung){
                        //delete
                        if (i == 67){
                            delete();
                        }
                        //shift
                        else if (i == 59 || i == 60){
                            shiftStatus = true;
                        }else {
                            hangul(HangulVersionArray.ReturnText(i,shiftStatus),edtRelationship);
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
    }

    void spinnerAdapterSetting(){
        entry = spnEntryYear.getSelectedItem().toString() + spnEntryMonth.getSelectedItem().toString() + spnEntryDay.getSelectedItem().toString() + spnEntryHour.getSelectedItem().toString() + spnEntryMinute.getSelectedItem().toString();
        finish = spnFinishYear.getSelectedItem().toString()+ spnFinishMonth.getSelectedItem().toString() + spnFinishDay.getSelectedItem().toString() + spnFinishHour.getSelectedItem().toString()+ spnFinishMinute.getSelectedItem().toString();
    }

    public void hangul(String inputText,EditText editText) {

        try {
            this.hangulInput = new HangulInput(editText.onCreateInputConnection(new EditorInfo()));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_binso_insert_back : {
                onBackPressed();
                break;
            }
            case R.id.btn_binso_insert_save : {

                spinnerAdapterSetting();

                editor.putString("binso_title",edtBinsoName.getText().toString());
                editor.putString("binso_deceased_name",edtDeceasedName.getText().toString());
                editor.putString("binso_age",edtAge.getText().toString());
                editor.putString("binso_religion",edtReligion.getText().toString());
                editor.putString("binso_spot",edtSpot.getText().toString());
                editor.putString("binso_location",edtLocation.getText().toString());
                editor.putString("binso_relationship",edtRelationship.getText().toString());
                editor.putString("binso_check_in",entry);
                editor.putString("binso_check_out",finish);
                editor.apply();
                onBackPressed();
                break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        edtBinsoName.setText(pref.getString("binso_title",""));
        edtDeceasedName.setText(pref.getString("binso_deceased_name",""));
        edtAge.setText(pref.getString("binso_age",""));
        edtReligion.setText(pref.getString("binso_religion",""));
        edtSpot.setText(pref.getString("binso_spot",""));
        edtLocation.setText(pref.getString("binso_location",""));
        edtRelationship.setText(pref.getString("binso_relationship",""));

        entry = pref.getString("binso_check_in","");
        finish = pref.getString("binso_check_out","");

        String[] yearArray = getResources().getStringArray(R.array.yearSpinnerArray);
        String[] monthArray = getResources().getStringArray(R.array.monthSpinnerArray);
        String[] dayArray = getResources().getStringArray(R.array.daySpinnerArray);
        String[] hourArray = getResources().getStringArray(R.array.hourSpinnerArray);
        String[] minuteArray = getResources().getStringArray(R.array.minuteSpinnerArray);

        if (entry.length() > 0 && finish.length() > 0){
            for (int i = 0; i < yearArray.length; i++){
                if (yearArray[i].equals(entry.substring(0,5))){
                    spnEntryYear.setSelection(i);
                }
                if (yearArray[i].equals(finish.substring(0,5))){
                    spnFinishYear.setSelection(i);
                }
            }
            for (int i = 0; i < monthArray.length; i++){
                if (monthArray[i].equals(entry.substring(5,8))){
                    spnEntryMonth.setSelection(i);
                }
                if (monthArray[i].equals(finish.substring(5,8))){
                    spnFinishMonth.setSelection(i);
                }
            }
            for (int i = 0; i < dayArray.length; i++){
                if (dayArray[i].equals(entry.substring(8,11))){
                    spnEntryDay.setSelection(i);
                }
                if (dayArray[i].equals(finish.substring(8,11))){
                    spnFinishDay.setSelection(i);
                }
            }
            for (int i = 0; i < hourArray.length; i++){
                if (hourArray[i].equals(entry.substring(11,14))){
                    spnEntryHour.setSelection(i);
                }
                if (hourArray[i].equals(finish.substring(11,14))){
                    spnFinishHour.setSelection(i);
                }
            }
            for (int i = 0; i < minuteArray.length; i++){
                if (minuteArray[i].equals(entry.substring(14,17))){
                    spnEntryMinute.setSelection(i);
                }
                if (minuteArray[i].equals(finish.substring(14,17))){
                    spnFinishMinute.setSelection(i);
                }
            }
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.edt_binso_insert_binso_name : {
                if (hasFocus){
                    try{
                        this.hangulInput.ResetCompo();
                    }catch (Exception e){

                    }
                }
                break;
            }
            case R.id.edt_binso_insert_deceased_name : {
                if (hasFocus){
                    try{
                        this.hangulInput.ResetCompo();
                    }catch (Exception e){

                    }
                }
                break;
            }
            case R.id.edt_binso_insert_deceased_age : {
                if (hasFocus){
                    try{
                        this.hangulInput.ResetCompo();
                    }catch (Exception e){

                    }
                }
                break;
            }
            case R.id.edt_binso_insert_religion : {
                if (hasFocus){
                    try{
                        this.hangulInput.ResetCompo();
                    }catch (Exception e){

                    }
                }
                break;
            }
            case R.id.edt_binso_insert_spot : {
                if (hasFocus){
                    try{
                        this.hangulInput.ResetCompo();
                    }catch (Exception e){

                    }
                }
                break;
            }
            case R.id.edt_binso_insert_location : {
                if (hasFocus){
                    try{
                        this.hangulInput.ResetCompo();
                    }catch (Exception e){

                    }
                }
                break;
            }
            case R.id.edt_binso_insert_relationship: {
                if (hasFocus){
                    try{
                        this.hangulInput.ResetCompo();
                    }catch (Exception e){

                    }
                }
                break;
            }
        }
    }
}
