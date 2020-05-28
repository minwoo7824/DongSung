package com.kmw.dongsung.Activitys.Insert;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kmw.dongsung.Commons.HangulInput;
import com.kmw.dongsung.Commons.HangulVersionArray;
import com.kmw.dongsung.R;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
public class GeneralInsertActivity extends Activity implements View.OnClickListener , View.OnFocusChangeListener {

    private String TAG = "GeneralInsertActivity";
    Button btnBack,btnSave,btnListInsert;
    LinearLayout linearListParent;
    ArrayList<EditText> titleArray = new ArrayList<>();
    ArrayList<EditText> deceasedArray = new ArrayList<>();
    ArrayList<EditText> relationshipArray = new ArrayList<>();
    ArrayList<EditText> locationArray = new ArrayList<>();
    ArrayList<EditText> ageArray = new ArrayList<>();
    ArrayList<EditText> religionArray = new ArrayList<>();
    ArrayList<EditText> spotArray = new ArrayList<>();

    ArrayList<String> titleStringArray = new ArrayList<>();
    ArrayList<String> deceasedStringArray = new ArrayList<>();
    ArrayList<String> relationshipStringArray = new ArrayList<>();
    ArrayList<String> checkInStringArray = new ArrayList<>();
    ArrayList<String> checkOutStringArray = new ArrayList<>();
    ArrayList<String> locationStringArray = new ArrayList<>();
    ArrayList<String> ageStringArray = new ArrayList<>();
    ArrayList<String> religionStringArray = new ArrayList<>();
    ArrayList<String> spotStringArray = new ArrayList<>();

    ArrayList<Spinner> yearArrayEntry = new ArrayList<>();
    ArrayList<Spinner> monthArrayEntry = new ArrayList<>();
    ArrayList<Spinner> dayArrayEntry = new ArrayList<>();
    ArrayList<Spinner> hourArrayEntry = new ArrayList<>();
    ArrayList<Spinner> minuteArrayEntry = new ArrayList<>();

    ArrayList<Spinner> yearArrayFinish = new ArrayList<>();
    ArrayList<Spinner> monthArrayFinish = new ArrayList<>();
    ArrayList<Spinner> dayArrayFinish = new ArrayList<>();
    ArrayList<Spinner> hourArrayFinish = new ArrayList<>();
    ArrayList<Spinner> minuteArrayFinish = new ArrayList<>();

    HangulInput hangulInput;
    boolean shiftStatus = false;
    boolean hanYoung = false;

    SharedPreferences pref;
    SharedPreferences.Editor editor;

    String entry="",finish="";
    String entry1="",finish1="";

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM월 dd일 HH:mm");
    SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyyMMddHHmm");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_insert);

        pref = getSharedPreferences("dongsung",MODE_PRIVATE);
        editor = pref.edit();

        FindViewById();

        if (pref.getString("general_title","").length() != 0){
            Gson gson = new Gson();
            String title = pref.getString("general_title","");
            String deceasedName = pref.getString("general_deceased_name","");
            String relationship = pref.getString("general_relationship","");
            String checkIn = pref.getString("general_checkIn","");
            String checkOut = pref.getString("general_checkOut","");
            String location = pref.getString("general_location","");
            String spot = pref.getString("general_spot","");
            String age = pref.getString("general_age","");
            String religion = pref.getString("general_religion","");
            Type type = new TypeToken<ArrayList<String>>(){}.getType();

            titleStringArray = gson.fromJson(title,type);
            deceasedStringArray = gson.fromJson(deceasedName,type);
            relationshipStringArray = gson.fromJson(relationship,type);
            checkInStringArray = gson.fromJson(checkIn,type);
            checkOutStringArray = gson.fromJson(checkOut,type);
            locationStringArray = gson.fromJson(location,type);
            spotStringArray = gson.fromJson(spot,type);
            ageStringArray = gson.fromJson(age,type);
            religionStringArray = gson.fromJson(religion,type);

            for (int i = 0; i < titleStringArray.size(); i++){
                ListMake(titleStringArray.get(i),deceasedStringArray.get(i),ageStringArray.get(i),
                        religionStringArray.get(i),spotStringArray.get(i),locationStringArray.get(i),
                        relationshipStringArray.get(i),checkInStringArray.get(i),checkOutStringArray.get(i));
            }
        }
    }

    void FindViewById(){
        btnBack = (Button)findViewById(R.id.btn_general_insert_back);
        btnSave = (Button)findViewById(R.id.btn_general_insert_save);
        btnListInsert = (Button)findViewById(R.id.btn_general_insert_list_insert);
        linearListParent = (LinearLayout)findViewById(R.id.linear_general_insert_list_parent);

        btnBack.setOnClickListener(this);
        btnListInsert.setOnClickListener(this);
        btnSave.setOnClickListener(this);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            case R.id.edt_general_binso_insert_list_item_binso_name : {
                if (hasFocus){
                    try{
                        this.hangulInput.ResetCompo();
                    }catch (Exception e){

                    }
                }
                break;
            }
            case R.id.edt_general_binso_insert_list_item_deceased_name : {
                if (hasFocus){
                    try{
                        this.hangulInput.ResetCompo();
                    }catch (Exception e){

                    }
                }
                break;
            }
            case R.id.edt_general_binso_insert_list_item_deceased_age : {
                if (hasFocus){
                    try{
                        this.hangulInput.ResetCompo();
                    }catch (Exception e){

                    }
                }
                break;
            }
            case R.id.edt_general_binso_insert_list_item_religion : {
                if (hasFocus){
                    try{
                        this.hangulInput.ResetCompo();
                    }catch (Exception e){

                    }
                }
                break;
            }
            case R.id.edt_general_binso_insert_list_item_spot : {
                if (hasFocus){
                    try{
                        this.hangulInput.ResetCompo();
                    }catch (Exception e){

                    }
                }
                break;
            }
            case R.id.edt_general_binso_insert_list_item_location : {
                if (hasFocus){
                    try{
                        this.hangulInput.ResetCompo();
                    }catch (Exception e){

                    }
                }
                break;
            }
            case R.id.edt_general_binso_insert_list_item_relationship: {
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

    void ListMake(String title, String deceasedName, String age, String religion, String spot, String location, String relationship,String checkIn,String checkOut){
        final View listView = getLayoutInflater().inflate(R.layout.general_binso_insert_list_item_layout,null);
        Button btnRemove = (Button)listView.findViewById(R.id.btn_general_binso_insert_list_item_binso_remove);

        final EditText edtTitle = (EditText)listView.findViewById(R.id.edt_general_binso_insert_list_item_binso_name);
        final EditText edtDeceasedName = (EditText)listView.findViewById(R.id.edt_general_binso_insert_list_item_deceased_name);
        final EditText edtAge = (EditText)listView.findViewById(R.id.edt_general_binso_insert_list_item_deceased_age);
        final EditText edtReligion = (EditText)listView.findViewById(R.id.edt_general_binso_insert_list_item_religion);
        final EditText edtSpot = (EditText)listView.findViewById(R.id.edt_general_binso_insert_list_item_spot);
        final EditText edtLocation = (EditText)listView.findViewById(R.id.edt_general_binso_insert_list_item_location);
        final EditText edtRelationship = (EditText)listView.findViewById(R.id.edt_general_binso_insert_list_item_relationship);

        final Spinner spnFirstYear = (Spinner)listView.findViewById(R.id.spn_general_binso_insert_list_item_entry_year);
        final Spinner spnFirstMonth = (Spinner)listView.findViewById(R.id.spn_general_binso_insert_list_item_entry_month);
        final Spinner spnFirstDay = (Spinner)listView.findViewById(R.id.spn_general_binso_insert_list_item_entry_day);
        final Spinner spnFirstHour = (Spinner)listView.findViewById(R.id.spn_general_binso_insert_list_item_entry_hour);
        final Spinner spnFirstMinute = (Spinner)listView.findViewById(R.id.spn_general_binso_insert_list_item_entry_minute);

        final Spinner spnSecondYear = (Spinner)listView.findViewById(R.id.spn_general_binso_insert_list_item_finish_year);
        final Spinner spnSecondMonth = (Spinner)listView.findViewById(R.id.spn_general_binso_insert_list_item_finish_month);
        final Spinner spnSecondDay = (Spinner)listView.findViewById(R.id.spn_general_binso_insert_list_item_finish_day);
        final Spinner spnSecondHour = (Spinner)listView.findViewById(R.id.spn_general_binso_insert_list_item_finish_hour);
        final Spinner spnSecondMinute = (Spinner)listView.findViewById(R.id.spn_general_binso_insert_list_item_finish_minute);

        linearListParent.addView(listView);

        titleArray.add(edtTitle);
        deceasedArray.add(edtDeceasedName);
        locationArray.add(edtLocation);
        relationshipArray.add(edtRelationship);
        ageArray.add(edtAge);
        religionArray.add(edtReligion);
        spotArray.add(edtSpot);

        yearArrayEntry.add(spnFirstYear);
        monthArrayEntry.add(spnFirstMonth);
        dayArrayEntry.add(spnFirstDay);
        hourArrayEntry.add(spnFirstHour);
        minuteArrayEntry.add(spnFirstMinute);

        yearArrayFinish.add(spnSecondYear);
        monthArrayFinish.add(spnSecondMonth);
        dayArrayFinish.add(spnSecondDay);
        hourArrayFinish.add(spnSecondHour);
        minuteArrayFinish.add(spnSecondMinute);

        edtTitle.setOnFocusChangeListener(this);
        edtDeceasedName.setOnFocusChangeListener(this);
        edtAge.setOnFocusChangeListener(this);
        edtReligion.setOnFocusChangeListener(this);
        edtSpot.setOnFocusChangeListener(this);
        edtLocation.setOnFocusChangeListener(this);
        edtRelationship.setOnFocusChangeListener(this);

        edtTitle.setText(title);
        edtDeceasedName.setText(deceasedName);
        edtAge.setText(age);
        edtReligion.setText(religion);
        edtSpot.setText(spot);
        edtLocation.setText(location);
        edtRelationship.setText(relationship);

        String[] yearArray = getResources().getStringArray(R.array.yearSpinnerArray);
        String[] monthArray = getResources().getStringArray(R.array.monthSpinnerArray);
        String[] dayArray = getResources().getStringArray(R.array.daySpinnerArray);
        String[] hourArray = getResources().getStringArray(R.array.hourSpinnerArray);
        String[] minuteArray = getResources().getStringArray(R.array.minuteSpinnerArray);

        if (checkIn.length() > 0 && checkOut.length() > 0){
            for (int i = 0; i < yearArray.length; i++){
                if (yearArray[i].equals(checkIn.substring(0,5))){
                    spnFirstYear.setSelection(i);
                }
                if (yearArray[i].equals(checkOut.substring(0,5))){
                    spnSecondYear.setSelection(i);
                }
            }
            for (int i = 0; i < monthArray.length; i++){
                if (monthArray[i].equals(checkIn.substring(5,8))){
                    spnFirstMonth.setSelection(i);
                }
                if (monthArray[i].equals(checkOut.substring(5,8))){
                    spnSecondMonth.setSelection(i);
                }
            }
            for (int i = 0; i < dayArray.length; i++){
                if (dayArray[i].equals(checkIn.substring(8,11))){
                    spnFirstDay.setSelection(i);
                }
                if (dayArray[i].equals(checkOut.substring(8,11))){
                    spnSecondDay.setSelection(i);
                }
            }
            for (int i = 0; i < hourArray.length; i++){
                if (hourArray[i].equals(checkIn.substring(11,14))){
                    spnFirstHour.setSelection(i);
                }
                if (hourArray[i].equals(checkOut.substring(11,14))){
                    spnSecondHour.setSelection(i);
                }
            }
            for (int i = 0; i < minuteArray.length; i++){
                if (minuteArray[i].equals(checkIn.substring(14,17))){
                    spnFirstMinute.setSelection(i);
                }
                if (minuteArray[i].equals(checkOut.substring(14,17))){
                    spnSecondMinute.setSelection(i);
                }
            }
        }

        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                titleArray.remove(edtTitle);
                deceasedArray.remove(edtDeceasedName);
                locationArray.remove(edtLocation);
                relationshipArray.remove(edtRelationship);
                ageArray.remove(edtAge);
                religionArray.remove(edtReligion);
                spotArray.remove(edtSpot);

                yearArrayEntry.remove(spnFirstYear);
                monthArrayEntry.remove(spnFirstMonth);
                dayArrayEntry.remove(spnFirstDay);
                hourArrayEntry.remove(spnFirstHour);
                minuteArrayEntry.remove(spnFirstMinute);

                yearArrayFinish.remove(spnSecondYear);
                monthArrayFinish.remove(spnSecondMonth);
                dayArrayFinish.remove(spnSecondDay);
                hourArrayFinish.remove(spnSecondHour);
                minuteArrayFinish.remove(spnSecondMinute);
                linearListParent.removeView(listView);
            }
        });

        edtTitle.setOnKeyListener(new View.OnKeyListener() {
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
                            hangul(HangulVersionArray.ReturnText(i,shiftStatus),edtTitle);
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
            case R.id.btn_general_insert_back : {
                onBackPressed();
                break;
            }
            case R.id.btn_general_insert_list_insert : {
                ListMake("","","","","","","","","");
                break;
            }
            case R.id.btn_general_insert_save : {
                Gson gson = new Gson();

                titleStringArray = new ArrayList<>();
                deceasedStringArray = new ArrayList<>();
                ageStringArray = new ArrayList<>();
                religionStringArray = new ArrayList<>();
                spotStringArray = new ArrayList<>();
                locationStringArray = new ArrayList<>();
                relationshipStringArray = new ArrayList<>();
                checkInStringArray = new ArrayList<>();
                checkOutStringArray = new ArrayList<>();

                Log.i(TAG,"year : " + yearArrayEntry.get(0).getSelectedItem().toString());

                for (int i = 0; i < titleArray.size(); i++){
                    titleStringArray.add(titleArray.get(i).getText().toString());
                    deceasedStringArray.add(deceasedArray.get(i).getText().toString());
                    ageStringArray.add(ageArray.get(i).getText().toString());
                    religionStringArray.add(religionArray.get(i).getText().toString());
                    spotStringArray.add(spotArray.get(i).getText().toString());
                    locationStringArray.add(locationArray.get(i).getText().toString());
                    relationshipStringArray.add(relationshipArray.get(i).getText().toString());
                    checkInStringArray.add(yearArrayEntry.get(i).getSelectedItem().toString() +
                            monthArrayEntry.get(i).getSelectedItem().toString() +
                            dayArrayEntry.get(i).getSelectedItem().toString() +
                            hourArrayEntry.get(i).getSelectedItem().toString() +
                            minuteArrayEntry.get(i).getSelectedItem().toString());
                    checkOutStringArray.add(yearArrayFinish.get(i).getSelectedItem().toString() +
                            monthArrayFinish.get(i).getSelectedItem().toString() +
                            dayArrayFinish.get(i).getSelectedItem().toString() +
                            hourArrayFinish.get(i).getSelectedItem().toString() +
                            minuteArrayFinish.get(i).getSelectedItem().toString());
                }

                String title = gson.toJson(titleStringArray);
                String deceasedName = gson.toJson(deceasedStringArray);
                String age = gson.toJson(ageStringArray);
                String religion = gson.toJson(religionStringArray);
                String spot = gson.toJson(spotStringArray);
                String location = gson.toJson(locationStringArray);
                String relationship = gson.toJson(relationshipStringArray);
                String checkIn = gson.toJson(checkInStringArray);
                String checkOut = gson.toJson(checkOutStringArray);

                editor.putString("general_title",title);
                editor.putString("general_deceased_name",deceasedName);
                editor.putString("general_age",age);
                editor.putString("general_religion",religion);
                editor.putString("general_spot",spot);
                editor.putString("general_location",location);
                editor.putString("general_relationship",relationship);
                editor.putString("general_checkIn",checkIn);
                editor.putString("general_checkOut",checkOut);
                editor.apply();

                onBackPressed();
                break;
            }
        }
    }


}
