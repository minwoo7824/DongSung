package com.kmw.dongsung.Func;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.util.Pools;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.kmw.dongsung.R;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class InternetGeneralViewPagerAdapter2 extends PagerAdapter {

    LayoutInflater inflater;
    ArrayList<InternetGeneralViewPagerItem> itemArrayList = new ArrayList<InternetGeneralViewPagerItem>();
    Context context;
    int division;
    int arrowFlag;
    int famailySize;
    int familyPosition;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String bottomText;
    String familyColor = "";

    public static ArrayList<TextView> txtRelationArray1 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtRelationArray2 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtRelationArray3 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtRelationArray4 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtRelationArray5 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtRelationArray6 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtRelationArray7 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtRelationArray8 = new ArrayList<TextView>();

    public static ArrayList<TextView> txtNamesArray1 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtNamesArray2 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtNamesArray3 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtNamesArray4 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtNamesArray5 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtNamesArray6 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtNamesArray7 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtNamesArray8 = new ArrayList<TextView>();

    public static ArrayList<TextView> txtTestArray1 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtTestArray2 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtTestArray3 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtTestArray4 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtTestArray5 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtTestArray6 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtTestArray7 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtTestArray8 = new ArrayList<TextView>();

    public static ArrayList<TextView> txtRelationNamesArray = new ArrayList<TextView>();
    public static ArrayList<View> viewArrayList = new ArrayList<View>();

    LinearLayout linearLayoutTest;

    public static TextView txtTitle1;
    public static TextView txtDeceasedName1;
    public static TextView txtRelationship1;
    LinearLayout linNamesParent1;
    GridLayout gridLayout;
    public static TextView txtCheckIn1;
    public static TextView txtCheckOut1;
    public static TextView txtLocation1;
    public static ImageView imgProfile1;
    public static ImageView imgArrow1;
    public static ImageView imgReligionBg1;

    public static TextView txtTitle2;
    public static TextView txtDeceasedName2;
    public static TextView txtRelationship2;
    LinearLayout linNamesParent2;
    public static TextView txtCheckIn2;
    public static TextView txtCheckOut2;
    public static TextView txtLocation2;
    public static ImageView imgProfile2;
    public static ImageView imgArrow2;
    public static ImageView imgReligionBg2;

    public static TextView txtTitle3;
    public static TextView txtDeceasedName3;
    public static TextView txtRelationship3;
    LinearLayout linNamesParent3;
    public static TextView txtCheckIn3;
    public static TextView txtCheckOut3;
    public static TextView txtLocation3;
    public static ImageView imgProfile3;
    public static ImageView imgArrow3;
    public static ImageView imgReligionBg3;

    public static TextView txtTitle4;
    public static TextView txtDeceasedName4;
    public static TextView txtRelationship4;
    LinearLayout linNamesParent4;
    public static TextView txtCheckIn4;
    public static TextView txtCheckOut4;
    public static TextView txtLocation4;
    public static ImageView imgProfile4;
    public static ImageView imgArrow4;
    public static ImageView imgReligionBg4;

    public static TextView txtTitle5;
    public static TextView txtDeceasedName5;
    public static TextView txtRelationship5;
    LinearLayout linNamesParent5;
    public static TextView txtCheckIn5;
    public static TextView txtCheckOut5;
    public static TextView txtLocation5;
    public static ImageView imgProfile5;
    public static ImageView imgArrow5;
    public static ImageView imgReligionBg5;

    public static TextView txtTitle6;
    public static TextView txtDeceasedName6;
    public static TextView txtRelationship6;
    LinearLayout linNamesParent6;
    public static TextView txtCheckIn6;
    public static TextView txtCheckOut6;
    public static TextView txtLocation6;
    public static ImageView imgProfile6;
    public static ImageView imgArrow6;
    public static ImageView imgReligionBg6;

    public static TextView txtTitle7;
    public static TextView txtDeceasedName7;
    public static TextView txtRelationship7;
    LinearLayout linNamesParent7;
    public static TextView txtCheckIn7;
    public static TextView txtCheckOut7;
    public static TextView txtLocation7;
    public static ImageView imgProfile7;
    public static ImageView imgArrow7;
    public static ImageView imgReligionBg7;

    public static TextView txtTitle8;
    public static TextView txtDeceasedName8;
    public static TextView txtRelationship8;
    LinearLayout linNamesParent8;
    public static TextView txtCheckIn8;
    public static TextView txtCheckOut8;
    public static TextView txtLocation8;
    public static ImageView imgProfile8;
    public static ImageView imgArrow8;
    public static ImageView imgReligionBg8;

    public static LinearLayout linearBg;
    public static LinearLayout linearTop;

    public static TextView txtEtc1,txtEtc2,txtEtc3,txtEtc4,txtEtc5,txtEtc6,txtEtc7,txtEtc8,txtEtc9,txtEtc10,txtEtc11,txtEtc12,txtEtc13,
            txtEtc14,txtEtc15,txtEtc16,txtEtc17,txtEtc18,txtEtc19,txtEtc20,txtEtc21,txtEtc22,txtEtc23,txtEtc24;

    public static ImageView imgVisible;
    public static boolean first = false;
    public static int height = 0;

    String divisionImg;
    Typeface typeface;

    String deceasedColor,roomNameColor,relationColor,checkInTitleColor,checkInColor,checkOutTitleColor,checkOutColor,locationTitleColor,locationColor;

    String statusPlateNo;

    public static float txtSize1 = 0;

    private Pools.SimplePool<View> viewSimplePool;

    int realCount = 0;

    float txtRealSize1 = 0;
    float txtRealSize2 = 0;

    public static int finalPosition1;

    public InternetGeneralViewPagerAdapter2(LayoutInflater inflater,Context context, int division, int arrowFlag, int famailySize,
                                           int familyPosition, SharedPreferences pref, String bottomText, String divisionImg,Typeface typeface,
                                           String deceasedColor,String roomNameColor,String relationColor,String checkInTitleColor,String checkInColor,String checkOutTitleColor,
                                           String checkOutColor,String locationTitleColor,String locationColor,String statusPlateNo) {
        this.inflater = inflater;
        this.context = context;
        this.division = division;
        this.arrowFlag = arrowFlag;
        this.famailySize = famailySize;
        this.familyPosition = familyPosition;
        this.pref = pref;
        this.bottomText = bottomText;
        this.divisionImg = divisionImg;
        this.typeface = typeface;
        this.deceasedColor = deceasedColor;
        this.roomNameColor = roomNameColor;
        this.relationColor = relationColor;
        this.checkInTitleColor = checkInTitleColor;
        this.checkInColor = checkInColor;
        this.checkOutTitleColor = checkOutTitleColor;
        this.checkOutColor = checkOutColor;
        this.locationTitleColor = locationTitleColor;
        this.locationColor = locationColor;
        this.statusPlateNo = statusPlateNo;
        editor = pref.edit();
        viewSimplePool = new Pools.SimplePool<View>(10);

        if (linNamesParent1 != null){
            Log.i(TAG,"remove remove");
            linNamesParent1.getViewTreeObserver().removeOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    @Override
    public int getCount() {
        return (int) Math.ceil(itemArrayList.size()/(double)division);
//        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    private View getPagerItemView() {
        // View 를 pool 에서 가져오고 없으면 inflate 한다.
        View view = viewSimplePool.acquire();
        if (view == null) {
            if (division == 1){
                view = LayoutInflater.from(context).inflate(R.layout.internet_general_one_division_item, null);
            }else if (division == 2){
                view = LayoutInflater.from(context).inflate(R.layout.internet_general_two_division_item, null);
            }else if (division == 4){
                view = LayoutInflater.from(context).inflate(R.layout.internet_general_four_division_item, null);
            }else if (division == 6){
                view = LayoutInflater.from(context).inflate(R.layout.internet_general_six_division_item, null);
            }else if (division == 8){
                view = LayoutInflater.from(context).inflate(R.layout.internet_general_eight_division_item, null);
            }
        }

        return view;
    }
    public void destroyItem(ViewGroup container, int position, Object object) {

        viewSimplePool.release((View)object);
        container.removeView((View)object);
//        ((ViewPager)container).removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int i) {

//        View view = getPagerItemView();
        View view = null;
        realCount = i;

//        i = i % (int) Math.ceil(itemArrayList.size()/(double)division);

//        if (view !=null){
//
//        }else{
//
//        }

        txtRelationArray1 = new ArrayList<TextView>();
        txtRelationArray2 = new ArrayList<TextView>();
        txtRelationArray3 = new ArrayList<TextView>();
        txtRelationArray4 = new ArrayList<TextView>();
        txtRelationArray5 = new ArrayList<TextView>();
        txtRelationArray6 = new ArrayList<TextView>();
        txtRelationArray7 = new ArrayList<TextView>();
        txtRelationArray8 = new ArrayList<TextView>();

        txtNamesArray1 = new ArrayList<TextView>();
        txtNamesArray2 = new ArrayList<TextView>();
        txtNamesArray3 = new ArrayList<TextView>();
        txtNamesArray4 = new ArrayList<TextView>();
        txtNamesArray5 = new ArrayList<TextView>();
        txtNamesArray6 = new ArrayList<TextView>();
        txtNamesArray7 = new ArrayList<TextView>();
        txtNamesArray8 = new ArrayList<TextView>();

        txtTestArray1 = new ArrayList<TextView>();
        txtTestArray2 = new ArrayList<TextView>();
        txtTestArray3 = new ArrayList<TextView>();
        txtTestArray4 = new ArrayList<TextView>();
        txtTestArray5 = new ArrayList<TextView>();
        txtTestArray6 = new ArrayList<TextView>();
        txtTestArray7 = new ArrayList<TextView>();
        txtTestArray8 = new ArrayList<TextView>();

        txtRelationNamesArray = new ArrayList<TextView>();

//        if (linearLayoutTest != null){
//            linearLayoutTest.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                @Override
//                public void onGlobalLayout() {
//                    linearLayoutTest.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                }
//            });
//        }

        if (division == 1){
            view = inflater.inflate(R.layout.internet_general_one_division_item,null);
            txtTitle1        =         (TextView)view.findViewById(R.id.txt_one_division_item_title);
            txtDeceasedName1 = (TextView)view.findViewById(R.id.txt_one_division_item_deceased_name);
            txtCheckIn1      = (TextView)view.findViewById(R.id.txt_one_division_item_check_in);
            txtCheckOut1     = (TextView)view.findViewById(R.id.txt_one_division_item_check_out);
            txtLocation1     = (TextView)view.findViewById(R.id.txt_one_division_item_location);
            imgProfile1      = (ImageView)view.findViewById(R.id.img_one_division_item_deceased_profile);
            imgArrow1        = (ImageView)view.findViewById(R.id.img_one_division_item_arrow);
            linNamesParent1 = (LinearLayout)view.findViewById(R.id.linear_one_division_item_relation_name_parent);
            txtEtc1         = (TextView)view.findViewById(R.id.txt_one_division_item_etc1);
            txtEtc2         = (TextView)view.findViewById(R.id.txt_one_division_item_etc2);
            txtEtc3         = (TextView)view.findViewById(R.id.txt_one_division_item_etc3);
            imgReligionBg1  = (ImageView)view.findViewById(R.id.img_one_division_item_religion_bg1);

            linearLayoutTest = linNamesParent1;


            txtTitle1.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName1.setTextColor(Color.parseColor(deceasedColor));
            txtEtc1.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc2.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc3.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn1.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut1.setTextColor(Color.parseColor(checkOutColor));
            txtLocation1.setTextColor(Color.parseColor(locationColor));

            final LinearLayout linearBg = (LinearLayout)view.findViewById(R.id.linear_general_one_division_bg);
            final LinearLayout linearTop = (LinearLayout)view.findViewById(R.id.linear_general_one_division_top);

            if (statusPlateNo.equals("43")){
                linearBg.setBackgroundResource(R.drawable.img_division_one_top_black_42);
            }else if (statusPlateNo.equals("50")){
                linearBg.setBackgroundResource(R.drawable.img_division_one_top_black_49);
            }else if (statusPlateNo.equals("56")){
                linearBg.setBackgroundResource(R.drawable.img_division_one_top_black_55);
            }else if (statusPlateNo.equals("62")){
                linearBg.setBackgroundResource(R.drawable.img_division_one_top_black_61);
            }
            if (bottomText.length() != 0){
                if (statusPlateNo.equals("43")){
                    linearBg.setBackgroundResource(R.drawable.img_division_one_bottom_black_42);
                }else if (statusPlateNo.equals("50")){
                    linearBg.setBackgroundResource(R.drawable.img_division_one_bottom_black_49);
                }else if (statusPlateNo.equals("56")){
                    linearBg.setBackgroundResource(R.drawable.img_division_one_bottom_black_55);
                }else if (statusPlateNo.equals("62")){
                    linearBg.setBackgroundResource(R.drawable.img_division_one_bottom_black_61);
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
                params.weight = 48;
                linearTop.setLayoutParams(params);
            }

            txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(i));
            txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(i));
            txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(i));
            txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(i));

            if (itemArrayList.get(i).getLocationArray().get(i).contains("\r\n")){
                txtLocation1.setMaxLines(2);
            }else{
                txtLocation1.setMaxLines(1);
            }

            if (itemArrayList.get(i).getLocationArray().get(i).length() != 0){
                txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(i));
            }else{
                txtLocation1.setText("미 정");
            }

            if (itemArrayList.get(i).getImgPathArray().get(i).length() > 0){
                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(i)).into(imgProfile1);
            }
            if (itemArrayList.get(i).getReligionArray().get(i).length() > 0){
                Glide.with(context).load(itemArrayList.get(i).getReligionArray().get(i)).into(imgReligionBg1);
            }

            String result = "";

            for (int j = 0; j < itemArrayList.get(i).getNamesArray().get(familyPosition).size(); j++){
                result += itemArrayList.get(i).getRelationArray().get(familyPosition).get(j) + "   " + itemArrayList.get(i).getNamesArray().get(familyPosition).get(j) + "\n";
            }

            int relationLenth10 = 0;
            int position1 = 0;

            for (int a = 0; a < itemArrayList.get(i).getNamesArray().get(i).size(); a++){
                if (relationLenth10 < itemArrayList.get(i).getRelationArray().get(i).get(a).length()){
                    if (itemArrayList.get(i).getRelationArray().get(i).get(a).contains(" ")){
                        relationLenth10 = itemArrayList.get(i).getRelationArray().get(i).get(a).length()-1;
                    }else{
                        relationLenth10 = itemArrayList.get(i).getRelationArray().get(i).get(a).length();
                    }
                    position1 = a;
                }
            }

            final int finalPosition1 = position1;

            for (int a = 0; a < itemArrayList.get(i).getNamesArray().get(i).size(); a++){
                ListMake(a,itemArrayList.get(i).getRelationArray().get(i).get(a),itemArrayList.get(i).getNamesArray().get(i).get(a), 70f,linNamesParent1,familyColor,1);
            }

            if (itemArrayList.get(i).getArrowNoArray().get((i)).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i)).equals("")){
                imgArrow1.setVisibility(View.GONE);
            }else{
                imgArrow1.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i))));
            }

            linNamesParent1.setVisibility(View.VISIBLE);

            linNamesParent1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    txtEtc1.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtCheckIn1.getTextSize());
                    txtEtc2.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtCheckIn1.getTextSize());
                    txtEtc3.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtCheckIn1.getTextSize());
                    txtCheckOut1.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtCheckIn1.getTextSize());
                    txtLocation1.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtCheckIn1.getTextSize());

                    if (txtTestArray1.size() == 0){
                        if (linNamesParent1.getChildCount() != 0){
                            int child1 = 0;

                            for (int j = 0; j < linNamesParent1.getChildCount(); j++){
                                child1 = child1 + linNamesParent1.getChildAt(j).getMeasuredHeight();
                            }

                            if (child1 >= (linNamesParent1.getHeight() - 40)) {

                                if (child1 != 0) {

                                    for (int i = 0; i < txtTestArray1.size(); i++) {
                                        txtTestArray1.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(0).getTextSize() - 1f);
                                    }

                                    if (txtTestArray1.size() > 0){
                                        txtTestArray1.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(1).getTextSize());
                                    }

                                    for (int j = 0; j < txtRelationArray1.size(); j++) {
                                        if (finalPosition1 < txtRelationArray1.size()) {
                                            final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                            if (j != finalPosition1) {
                                                txtRelationArray1.get(j).setLayoutParams(params11);
                                                txtRelationArray1.get(j).invalidate();
                                            }
                                        }
                                    }
                                }
                            }else {
                                for (int j = 0; j < txtRelationArray1.size(); j++) {
                                    if (finalPosition1 < txtRelationArray1.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition1) {
                                            txtRelationArray1.get(j).setLayoutParams(params11);
                                            txtRelationArray1.get(j).invalidate();
                                        }
                                    }
                                }
                                linNamesParent1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                        }else{
                            linNamesParent1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            if (arrowFlag != 1){
                imgArrow1.setVisibility(View.GONE);
            }

        }else if (division == 2){

            LinearLayout linearVisible2 = null;

            view = inflater.inflate(R.layout.internet_general_two_division_item,null);

            linearVisible2 = (LinearLayout)view.findViewById(R.id.linear_two_visible2);

            txtTitle1 = (TextView)view.findViewById(R.id.txt_tow_division_item_title1);
            txtDeceasedName1 = (TextView)view.findViewById(R.id.txt_tow_division_item_deceased_name1);
            txtRelationship1 = (TextView)view.findViewById(R.id.txt_two_division_item_relationship1);
            txtCheckIn1 = (TextView)view.findViewById(R.id.txt_tow_division_item_check_in1);
            txtCheckOut1 = (TextView)view.findViewById(R.id.txt_tow_division_item_check_out1);
            txtLocation1 = (TextView)view.findViewById(R.id.txt_tow_division_item_location1);
            imgProfile1 = (ImageView)view.findViewById(R.id.img_tow_division_item_profile1);
            imgArrow1 = (ImageView)view.findViewById(R.id.img_two_division_item_arrow1);
            linNamesParent1 = (LinearLayout)view.findViewById(R.id.linear_two_division_item_name_parent1);
            imgReligionBg1  = (ImageView)view.findViewById(R.id.img_two_division_item_religion_bg1);
            if (familyPosition == 0){
                linearLayoutTest = linNamesParent1;
            }

            txtTitle2 = (TextView)view.findViewById(R.id.txt_tow_division_item_title2);
            txtDeceasedName2 = (TextView)view.findViewById(R.id.txt_tow_division_item_deceased_name2);
            linNamesParent2 = (LinearLayout)view.findViewById(R.id.linear_two_division_item_name_parent2);
            txtCheckIn2 = (TextView)view.findViewById(R.id.txt_tow_division_item_check_in2);
            txtCheckOut2 = (TextView)view.findViewById(R.id.txt_tow_division_item_check_out2);
            txtLocation2 = (TextView)view.findViewById(R.id.txt_tow_division_item_location2);
            imgProfile2 = (ImageView)view.findViewById(R.id.img_tow_division_item_profile2);
            imgArrow2 = (ImageView)view.findViewById(R.id.img_two_division_item_arrow2);
            imgVisible = (ImageView)view.findViewById(R.id.img_two_division_item_img);
            imgReligionBg2  = (ImageView)view.findViewById(R.id.img_two_division_item_religion_bg2);
            if (familyPosition == 1){
                linearLayoutTest = linNamesParent2;
            }

            txtEtc1         = (TextView)view.findViewById(R.id.txt_two_division_item_etc1);
            txtEtc2         = (TextView)view.findViewById(R.id.txt_two_division_item_etc2);
            txtEtc3         = (TextView)view.findViewById(R.id.txt_two_division_item_etc3);
            txtEtc4         = (TextView)view.findViewById(R.id.txt_two_division_item_etc4);
            txtEtc5         = (TextView)view.findViewById(R.id.txt_two_division_item_etc5);
            txtEtc6         = (TextView)view.findViewById(R.id.txt_two_division_item_etc6);

            txtTitle1.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName1.setTextColor(Color.parseColor(deceasedColor));
            txtEtc1.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc2.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc3.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn1.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut1.setTextColor(Color.parseColor(checkOutColor));
            txtLocation1.setTextColor(Color.parseColor(locationColor));

            txtTitle2.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName2.setTextColor(Color.parseColor(deceasedColor));
            txtEtc4.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc5.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc6.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn2.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut2.setTextColor(Color.parseColor(checkOutColor));
            txtLocation2.setTextColor(Color.parseColor(locationColor));

            final LinearLayout linearBg = (LinearLayout)view.findViewById(R.id.linear_general_two_division_bg);
            final LinearLayout linearTop = (LinearLayout)view.findViewById(R.id.linear_general_two_division_top);

            if (statusPlateNo.equals("44")){
                linearBg.setBackgroundResource(R.drawable.img_division_two_top_black_42);
            }else if (statusPlateNo.equals("51")){
                linearBg.setBackgroundResource(R.drawable.img_division_two_top_black_49);
            }else if (statusPlateNo.equals("57")){
                linearBg.setBackgroundResource(R.drawable.img_division_two_top_black_55);
            }else if (statusPlateNo.equals("63")){
                linearBg.setBackgroundResource(R.drawable.img_division_two_top_black_61);
            }
            if (bottomText.length() != 0){
                if (statusPlateNo.equals("44")){
                    linearBg.setBackgroundResource(R.drawable.img_division_two_bottom_black_42);
                }else if (statusPlateNo.equals("51")){
                    linearBg.setBackgroundResource(R.drawable.img_division_two_bottom_black_49);
                }else if (statusPlateNo.equals("57")){
                    linearBg.setBackgroundResource(R.drawable.img_division_two_bottom_black_55);
                }else if (statusPlateNo.equals("63")){
                    linearBg.setBackgroundResource(R.drawable.img_division_two_bottom_black_61);
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
                params.weight = 47;
                linearTop.setLayoutParams(params);
            }

            if (divisionImg.length() != 0){
                Glide.with(context).load(divisionImg).into(imgVisible);
            }else{
                imgVisible.setBackgroundResource(R.drawable.img_two_visible);
            }

            txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(i * 2));
            txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(i * 2));
            txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(i * 2));
            txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(i * 2));

            if (itemArrayList.get(i).getLocationArray().get(i * 2).contains("\r\n")){
                txtLocation1.setMaxLines(2);
            }else{
                txtLocation1.setMaxLines(1);
            }

            if (itemArrayList.get(i).getLocationArray().get(i * 2).length() != 0){
                txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(i * 2));
            }else{
                txtLocation1.setText("미 정");
            }

            if (itemArrayList.get(i).getImgPathArray().get(i * 2).length() != 0){
                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(i * 2)).into(imgProfile1);
            }else{
                imgProfile1.setImageResource(R.drawable.img_no_profile);
            }


            if (itemArrayList.get(i).getReligionArray().get(i * 2).length() > 0){
                Glide.with(context).load(itemArrayList.get(i).getReligionArray().get(i * 2)).into(imgReligionBg1);
            }

            String result = "";

            for (int j = 0; j < itemArrayList.get(i).getNamesArray().get(familyPosition).size(); j++){
                result += itemArrayList.get(i).getRelationArray().get(familyPosition).get(j) + "   " + itemArrayList.get(i).getNamesArray().get(familyPosition).get(j) + "\n";
            }

            txtRelationship1.setText(result);

            int relationLenth10 = 0;
            int position1 = 0;

            for (int a = 0; a < itemArrayList.get(i).getNamesArray().get(i * 2).size(); a++){
                if (relationLenth10 < itemArrayList.get(i).getRelationArray().get(i * 2).get(a).length()){
                    if (itemArrayList.get(i).getRelationArray().get(i * 2).get(a).contains(" ")){
                        relationLenth10 = itemArrayList.get(i).getRelationArray().get(i * 2).get(a).length()-1;
                    }else{
                        relationLenth10 = itemArrayList.get(i).getRelationArray().get(i * 2).get(a).length();
                    }
                    position1 = a;
                }
            }

            int relationLenth2 = 0;
            int position2 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 2) + 1){
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 2) + 1).size(); a++){
                    if (relationLenth2 < itemArrayList.get(i).getRelationArray().get((i * 2) + 1).get(a).length()){
                        if (itemArrayList.get(i).getRelationArray().get((i * 2) + 1).get(a).contains(" ")){
                            relationLenth2 = itemArrayList.get(i).getRelationArray().get((i * 2) + 1).get(a).length() - 1;
                        }else{
                            relationLenth2 = itemArrayList.get(i).getRelationArray().get((i * 2) + 1).get(a).length();
                        }
                        position2 = a;
                    }
                }
            }

            final int finalPosition1 = position1;
            final int finalPosition2 = position2;

            for (int a = 0; a < itemArrayList.get(i).getNamesArray().get(i * 2).size(); a++){
                ListMake(a,itemArrayList.get(i).getRelationArray().get(i * 2).get(a),itemArrayList.get(i).getNamesArray().get(i * 2).get(a), 70f,linNamesParent1,familyColor,1);
            }

            if (itemArrayList.get(i).getArrowNoArray().get((i * 2)).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 2)).equals("")){
                imgArrow1.setVisibility(View.GONE);
            }else{
                imgArrow1.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 2))));
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 2) + 1){
                txtTitle2.setText(itemArrayList.get(i).getTitleArray().get((i * 2) + 1));
                txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get((i * 2) + 1));
//                txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get((i * 4) + 1));
                txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get((i * 2) + 1));
                txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get((i * 2) + 1));

                if (itemArrayList.get(i).getLocationArray().get((i * 2) + 1).contains("\r\n")){
                    txtLocation2.setMaxLines(2);
                }else{
                    txtLocation2.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 2) + 1).length() != 0){
                    txtLocation2.setText(itemArrayList.get(i).getLocationArray().get((i * 2) + 1));
                }else{
                    txtLocation2.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 2) + 1).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 2) + 1)).into(imgProfile2);
                }else{
                    imgProfile2.setImageResource(R.drawable.img_no_profile);
                }


                if (itemArrayList.get(i).getReligionArray().get((i * 2) + 1).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 2) + 1)).into(imgReligionBg2);
                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 2) + 1).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 2) + 1).get(a),itemArrayList.get(i).getNamesArray().get((i * 2) + 1).get(a),70f,linNamesParent2,familyColor,2);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 2) + 1).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 2) + 1).equals("")){
                    imgArrow2.setVisibility(View.GONE);
                }else{
                    imgArrow2.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 2) + 1)));
                }

            }else{
                linearVisible2.setVisibility(View.GONE);
            }

            txtRelationship1.setVisibility(View.GONE);
            linNamesParent1.setVisibility(View.VISIBLE);

            txtEtc1.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            txtEtc2.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            txtEtc3.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            txtCheckOut1.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            txtLocation1.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);

            txtEtc4.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            txtEtc5.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            txtEtc6.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            txtCheckOut2.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            txtLocation2.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);

            linNamesParent1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if (linNamesParent1.getChildCount() != 0){
                        int child1 = 0;

                        for (int j = 0; j < linNamesParent1.getChildCount(); j++){
                            child1 = child1 + linNamesParent1.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent1.getHeight() - 20)) {

                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray1.size(); i++) {
                                    txtTestArray1.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(0).getTextSize() - 1f);
                                }
                                txtTestArray1.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray1.size(); j++) {
                                    if (finalPosition1 < txtRelationArray1.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition1) {
                                        txtRelationArray1.get(j).setLayoutParams(params11);
                                        txtRelationArray1.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray1.size(); j++) {
                                if (finalPosition1 < txtRelationArray1.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray1.get(j).setLayoutParams(params11);
                                    txtRelationArray1.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            linNamesParent2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if (linNamesParent2.getChildCount() != 0){
                        int child1 = 0;

                        Log.i("tag","parent2 cnt : " + linNamesParent2.getChildCount());

                        for (int j = 0; j < linNamesParent2.getChildCount(); j++){
                            child1 = child1 + linNamesParent2.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent2.getHeight() - 20)) {
                            if (child1 != 0) {
                                for (int i = 0; i < txtTestArray2.size(); i++) {
                                    txtTestArray2.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(0).getTextSize() - 2f);
                                }

                                txtTestArray2.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray2.size(); j++) {
                                    if (finalPosition2 < txtRelationArray2.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition2) {
                                        txtRelationArray2.get(j).setLayoutParams(params11);
                                        txtRelationArray2.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray2.size(); j++) {
                                if (finalPosition2 < txtRelationArray2.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition2) {
                                    txtRelationArray2.get(j).setLayoutParams(params11);
                                    txtRelationArray2.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            if (arrowFlag != 1){
                imgArrow1.setVisibility(View.GONE);
                imgArrow2.setVisibility(View.GONE);
            }
        }else if (division == 4) {
            view = inflater.inflate(R.layout.internet_general_four_division_item, null);
            LinearLayout linVisible2 = (LinearLayout) view.findViewById(R.id.linear_four_list_item_visible2);
            LinearLayout linVisible3 = (LinearLayout) view.findViewById(R.id.linear_four_list_item_visible3);
            LinearLayout linVisible4 = (LinearLayout) view.findViewById(R.id.linear_four_list_item_visible4);

            txtTitle1 = (TextView) view.findViewById(R.id.txt_four_division_item_title1);
            txtDeceasedName1 = (TextView) view.findViewById(R.id.txt_four_division_item_deceased_name1);
            txtRelationship1 = (TextView) view.findViewById(R.id.txt_four_division_item_relationship1);
            txtCheckIn1 = (TextView) view.findViewById(R.id.txt_four_division_item_check_in1);
            txtCheckOut1 = (TextView) view.findViewById(R.id.txt_four_division_item_check_out1);
            txtLocation1 = (TextView) view.findViewById(R.id.txt_four_division_item_location1);
            imgProfile1 = (ImageView) view.findViewById(R.id.img_four_division_item_profile1);
            imgArrow1 = (ImageView) view.findViewById(R.id.img_four_division_item_arrow1);
            linNamesParent1 = (LinearLayout) view.findViewById(R.id.linear_four_division_item_name_parent1);
            imgReligionBg1  = (ImageView)view.findViewById(R.id.img_four_division_item_religion_bg1);
            if (familyPosition == 0) {
                linearLayoutTest = linNamesParent1;
            }

            txtTitle2 = (TextView) view.findViewById(R.id.txt_four_division_item_title2);
            txtDeceasedName2 = (TextView) view.findViewById(R.id.txt_four_division_item_deceased_name2);
            linNamesParent2 = (LinearLayout) view.findViewById(R.id.linear_four_division_item_name_parent2);
            txtCheckIn2 = (TextView) view.findViewById(R.id.txt_four_division_item_check_in2);
            txtCheckOut2 = (TextView) view.findViewById(R.id.txt_four_division_item_check_out2);
            txtLocation2 = (TextView) view.findViewById(R.id.txt_four_division_item_location2);
            imgProfile2 = (ImageView) view.findViewById(R.id.img_four_division_item_profile2);
            imgArrow2 = (ImageView) view.findViewById(R.id.img_four_division_item_arrow2);
            imgReligionBg2  = (ImageView)view.findViewById(R.id.img_four_division_item_religion_bg2);
            if (familyPosition == 1) {
                linearLayoutTest = linNamesParent2;
            }

            txtTitle3 = (TextView) view.findViewById(R.id.txt_four_division_item_title3);
            txtDeceasedName3 = (TextView) view.findViewById(R.id.txt_four_division_item_deceased_name3);
            linNamesParent3 = (LinearLayout) view.findViewById(R.id.linear_four_division_item_name_parent3);
            txtCheckIn3 = (TextView) view.findViewById(R.id.txt_four_division_item_check_in3);
            txtCheckOut3 = (TextView) view.findViewById(R.id.txt_four_division_item_check_out3);
            txtLocation3 = (TextView) view.findViewById(R.id.txt_four_division_item_location3);
            imgProfile3 = (ImageView) view.findViewById(R.id.img_four_division_item_profile3);
            imgArrow3 = (ImageView) view.findViewById(R.id.img_four_division_item_arrow3);
            imgReligionBg3  = (ImageView)view.findViewById(R.id.img_four_division_item_religion_bg3);
            if (familyPosition == 2) {
                linearLayoutTest = linNamesParent3;
            }

            txtTitle4 = (TextView) view.findViewById(R.id.txt_four_division_item_title4);
            txtDeceasedName4 = (TextView) view.findViewById(R.id.txt_four_division_item_deceased_name4);
            linNamesParent4 = (LinearLayout) view.findViewById(R.id.linear_four_division_item_name_parent4);
            txtCheckIn4 = (TextView) view.findViewById(R.id.txt_four_division_item_check_in4);
            txtCheckOut4 = (TextView) view.findViewById(R.id.txt_four_division_item_check_out4);
            txtLocation4 = (TextView) view.findViewById(R.id.txt_four_division_item_location4);
            imgProfile4 = (ImageView) view.findViewById(R.id.img_four_division_item_profile4);
            imgArrow4 = (ImageView) view.findViewById(R.id.img_four_division_item_arrow4);
            imgVisible = (ImageView) view.findViewById(R.id.img_four_division_item_visible);
            imgReligionBg4  = (ImageView)view.findViewById(R.id.img_four_division_item_religion_bg4);
            if (familyPosition == 3) {
                linearLayoutTest = linNamesParent4;
            }

            txtEtc1 = (TextView) view.findViewById(R.id.txt_four_division_item_etc1);
            txtEtc2 = (TextView) view.findViewById(R.id.txt_four_division_item_etc2);
            txtEtc3 = (TextView) view.findViewById(R.id.txt_four_division_item_etc3);
            txtEtc4 = (TextView) view.findViewById(R.id.txt_four_division_item_etc4);
            txtEtc5 = (TextView) view.findViewById(R.id.txt_four_division_item_etc5);
            txtEtc6 = (TextView) view.findViewById(R.id.txt_four_division_item_etc6);
            txtEtc7 = (TextView) view.findViewById(R.id.txt_four_division_item_etc7);
            txtEtc8 = (TextView) view.findViewById(R.id.txt_four_division_item_etc8);
            txtEtc9 = (TextView) view.findViewById(R.id.txt_four_division_item_etc9);
            txtEtc10 = (TextView) view.findViewById(R.id.txt_four_division_item_etc10);
            txtEtc11 = (TextView) view.findViewById(R.id.txt_four_division_item_etc11);
            txtEtc12 = (TextView) view.findViewById(R.id.txt_four_division_item_etc12);

            txtTitle1.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName1.setTextColor(Color.parseColor(deceasedColor));
            txtEtc1.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc2.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc3.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn1.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut1.setTextColor(Color.parseColor(checkOutColor));
            txtLocation1.setTextColor(Color.parseColor(locationColor));

            txtTitle2.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName2.setTextColor(Color.parseColor(deceasedColor));
            txtEtc4.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc5.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc6.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn2.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut2.setTextColor(Color.parseColor(checkOutColor));
            txtLocation2.setTextColor(Color.parseColor(locationColor));

            txtTitle3.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName3.setTextColor(Color.parseColor(deceasedColor));
            txtEtc7.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc8.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc9.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn3.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut3.setTextColor(Color.parseColor(checkOutColor));
            txtLocation3.setTextColor(Color.parseColor(locationColor));

            txtTitle4.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName4.setTextColor(Color.parseColor(deceasedColor));
            txtEtc10.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc11.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc12.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn4.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut4.setTextColor(Color.parseColor(checkOutColor));
            txtLocation4.setTextColor(Color.parseColor(locationColor));

            final LinearLayout linearBg = (LinearLayout) view.findViewById(R.id.linear_general_four_division_bg);
            final LinearLayout linearTop = (LinearLayout) view.findViewById(R.id.linear_general_four_division_top);

            if (statusPlateNo.equals("45")) {
                linearBg.setBackgroundResource(R.drawable.img_division_four_top_black_42);
            } else if (statusPlateNo.equals("52")) {
                linearBg.setBackgroundResource(R.drawable.img_division_four_top_black_49);
            } else if (statusPlateNo.equals("58")) {
                linearBg.setBackgroundResource(R.drawable.img_division_four_top_black_55);
            } else if (statusPlateNo.equals("64")) {
                linearBg.setBackgroundResource(R.drawable.img_division_four_top_black_61);
            }
            if (bottomText.length() != 0) {
                if (statusPlateNo.equals("45")) {
                    linearBg.setBackgroundResource(R.drawable.img_division_four_bottom_black_42);
                } else if (statusPlateNo.equals("52")) {
                    linearBg.setBackgroundResource(R.drawable.img_division_four_bottom_black_49);
                } else if (statusPlateNo.equals("58")) {
                    linearBg.setBackgroundResource(R.drawable.img_division_four_bottom_black_55);
                } else if (statusPlateNo.equals("64")) {
                    linearBg.setBackgroundResource(R.drawable.img_division_four_bottom_black_61);
                }
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
                params.weight = 50;
                linearTop.setLayoutParams(params);
            }

            txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(i * 4));
            txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(i * 4));
            txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(i * 4));
            txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(i * 4));

            if (itemArrayList.get(i).getLocationArray().get((i * 4)).contains("\r\n")) {
                txtLocation1.setMaxLines(2);
            } else {
                txtLocation1.setMaxLines(1);
            }

            if (itemArrayList.get(i).getLocationArray().get(i * 4).length() != 0) {
                txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(i * 4));
            } else {
                txtLocation1.setText("미 정");
            }

            if (itemArrayList.get(i).getImgPathArray().get(i * 4).length() != 0){
                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(i * 4)).into(imgProfile1);
            }else{
                imgProfile1.setImageResource(R.drawable.img_four_division_no_profile);
            }


            if (itemArrayList.get(i).getReligionArray().get(i * 4).length() > 0){
                Glide.with(context).load(itemArrayList.get(i).getReligionArray().get(i * 4)).into(imgReligionBg1);
            }

            String result = "";

            Log.i("tag", "divide img : " + divisionImg);
            if (divisionImg.length() != 0) {
                Glide.with(context).load(divisionImg).into(imgVisible);
            } else {
                imgVisible.setBackgroundResource(R.drawable.img_four_visible);
            }

            for (int j = 0; j < itemArrayList.get(i).getNamesArray().get(familyPosition).size(); j++) {
                result += itemArrayList.get(i).getRelationArray().get(familyPosition).get(j) + "   " + itemArrayList.get(i).getNamesArray().get(familyPosition).get(j) + "\n";
            }

            txtRelationship1.setText(result);

            Log.i("tag", "familyPosition : " + familyPosition);

            int relationLenth10 = 0;
            int position1 = 0;

            for (int a = 0; a < itemArrayList.get(i).getRelationArray().get(i * 4).size(); a++) {
                if (relationLenth10 < itemArrayList.get(i).getRelationArray().get(i * 4).get(a).length()) {
                    if (itemArrayList.get(i).getRelationArray().get(i * 4).get(a).contains(" ")) {
                        relationLenth10 = itemArrayList.get(i).getRelationArray().get(i * 4).get(a).length() - 1;
                    } else {
                        relationLenth10 = itemArrayList.get(i).getRelationArray().get(i * 4).get(a).length();
                    }
                    position1 = a;
                }
            }

            int relationLenth2 = 0;
            int position2 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 4) + 2) {
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 4) + 2).size(); a++) {
                    if (relationLenth2 < itemArrayList.get(i).getRelationArray().get((i * 4) + 2).get(a).length()) {
                        if (itemArrayList.get(i).getRelationArray().get((i * 4) + 2).get(a).contains(" ")) {
                            relationLenth2 = itemArrayList.get(i).getRelationArray().get((i * 4) + 2).get(a).length() - 1;
                        } else {
                            relationLenth2 = itemArrayList.get(i).getRelationArray().get((i * 4) + 2).get(a).length();
                        }
                        position2 = a;
                    }
                }
            }

            int relationLenth3 = 0;
            int position3 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 4) + 1) {
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 4) + 1).size(); a++) {
                    if (relationLenth3 < itemArrayList.get(i).getRelationArray().get((i * 4) + 1).get(a).length()) {
                        if (itemArrayList.get(i).getRelationArray().get((i * 4) + 1).get(a).contains(" ")) {
                            relationLenth3 = itemArrayList.get(i).getRelationArray().get((i * 4) + 1).get(a).length() - 1;
                        } else {
                            relationLenth3 = itemArrayList.get(i).getRelationArray().get((i * 4) + 1).get(a).length();
                        }
                        position3 = a;
                    }
                }
            }

            int relationLenth4 = 0;
            int position4 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 4) + 3) {
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 4) + 3).size(); a++) {
                    if (relationLenth4 < itemArrayList.get(i).getRelationArray().get((i * 4) + 3).get(a).length()) {
                        if (itemArrayList.get(i).getRelationArray().get((i * 4) + 3).get(a).contains(" ")) {
                            relationLenth4 = itemArrayList.get(i).getRelationArray().get((i * 4) + 3).get(a).length() - 1;
                        } else {
                            relationLenth4 = itemArrayList.get(i).getRelationArray().get((i * 4) + 3).get(a).length();
                        }
                        position4 = a;
                    }
                }
            } else {
                imgVisible.setVisibility(View.VISIBLE);
            }

            finalPosition1 = position1;
            final int finalPosition2 = position2;
            final int finalPosition3 = position3;
            final int finalPosition4 = position4;

            for (int a = 0; a < itemArrayList.get(i).getNamesArray().get(i * 4).size(); a++) {
                ListMake(a, itemArrayList.get(i).getRelationArray().get(i * 4).get(a), itemArrayList.get(i).getNamesArray().get(i * 4).get(a), 60f, linNamesParent1, familyColor, 1);
            }

            if (itemArrayList.get(i).getArrowNoArray().get((i * 4)).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 4)).equals("")) {
                imgArrow1.setVisibility(View.GONE);
            } else {
                imgArrow1.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 4))));
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 4) + 1) {
                txtTitle2.setText(itemArrayList.get(i).getTitleArray().get((i * 4) + 1));
                txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get((i * 4) + 1));
//                txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get((i * 4) + 1));
                txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get((i * 4) + 1));
                txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get((i * 4) + 1));

                if (itemArrayList.get(i).getLocationArray().get((i * 4) + 1).contains("\r\n")) {
                    txtLocation2.setMaxLines(2);
                } else {
                    txtLocation2.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 4) + 1).length() != 0) {
                    txtLocation2.setText(itemArrayList.get(i).getLocationArray().get((i * 4) + 1));
                } else {
                    txtLocation2.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 4) + 1).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 4) + 1)).into(imgProfile2);
                }else{
                    imgProfile2.setImageResource(R.drawable.img_four_division_no_profile);
                }

                if (itemArrayList.get(i).getReligionArray().get((i * 4) + 1).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 4) + 1)).into(imgReligionBg2);
                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 4) + 1).size(); a++) {
                    Log.i("tag", "a : " + a);
                    ListMake(a, itemArrayList.get(i).getRelationArray().get((i * 4) + 1).get(a), itemArrayList.get(i).getNamesArray().get((i * 4) + 1).get(a), 60f, linNamesParent2, familyColor, 2);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 4) + 1).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 4) + 1).equals("")) {
                    imgArrow2.setVisibility(View.GONE);
                } else {
                    imgArrow2.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 4) + 1)));
                }

            } else {
                linVisible2.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 4) + 2) {
                Log.i("tag", "testTest" + " " + itemArrayList.get(i).getTitleArray().get((i * 4) + 2));
                txtTitle3.setText(itemArrayList.get(i).getTitleArray().get((i * 4) + 2));
                txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get((i * 4) + 2));
//                txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get((i * 4) + 1));
                txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get((i * 4) + 2));
                txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get((i * 4) + 2));

                if (itemArrayList.get(i).getLocationArray().get((i * 4) + 2).contains("\r\n")) {
                    txtLocation3.setMaxLines(2);
                } else {
                    txtLocation3.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 4) + 2).length() != 0) {
                    txtLocation3.setText(itemArrayList.get(i).getLocationArray().get((i * 4) + 2));
                } else {
                    txtLocation3.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 4) + 2).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 4) + 2)).into(imgProfile3);
                }else{
                    imgProfile3.setImageResource(R.drawable.img_four_division_no_profile);
                }

                if (itemArrayList.get(i).getReligionArray().get((i * 4) + 2).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 4) + 2)).into(imgReligionBg3);
                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 4) + 2).size(); a++) {
                    ListMake(a, itemArrayList.get(i).getRelationArray().get((i * 4) + 2).get(a), itemArrayList.get(i).getNamesArray().get((i * 4) + 2).get(a), 60f, linNamesParent3, familyColor, 3);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 4) + 2).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 4) + 2).equals("")) {
                    imgArrow3.setVisibility(View.GONE);
                } else {
                    imgArrow3.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 4) + 2)));
                }
            } else {
                linVisible3.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 4) + 3) {
                txtTitle4.setText(itemArrayList.get(i).getTitleArray().get((i * 4) + 3));
                txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get((i * 4) + 3));
//                txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get((i * 4) + 3));
                txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get((i * 4) + 3));
                txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get((i * 4) + 3));

                if (itemArrayList.get(i).getLocationArray().get((i * 4) + 3).contains("\r\n")) {
                    txtLocation4.setMaxLines(2);
                } else {
                    txtLocation4.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 4) + 3).length() != 0) {
                    txtLocation4.setText(itemArrayList.get(i).getLocationArray().get((i * 4) + 3));
                } else {
                    txtLocation4.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 4) + 3).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 4) + 3)).into(imgProfile4);
                }else{
                    imgProfile4.setImageResource(R.drawable.img_four_division_no_profile);
                }

                if (itemArrayList.get(i).getReligionArray().get((i * 4) + 3).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 4) + 3)).into(imgReligionBg4);
                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 4) + 3).size(); a++) {
                    ListMake(a, itemArrayList.get(i).getRelationArray().get((i * 4) + 3).get(a), itemArrayList.get(i).getNamesArray().get((i * 4) + 3).get(a), 60f, linNamesParent4, familyColor, 4);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 4) + 3).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 4) + 3).equals("")) {
                    imgArrow4.setVisibility(View.GONE);
                } else {
                    imgArrow4.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 4) + 3)));
                }
            } else {
                linVisible4.setVisibility(View.GONE);
            }

            linNamesParent1.setVisibility(View.VISIBLE);
            txtRelationship1.setVisibility(View.GONE);

            linNamesParent1.getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);

            linNamesParent2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if (linNamesParent2.getChildCount() != 0){
                        int child1 = 0;

                        Log.i("tag","parent2 cnt : " + linNamesParent2.getChildCount());

                        for (int j = 0; j < linNamesParent2.getChildCount(); j++){
                            child1 = child1 + linNamesParent2.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent2.getHeight() - 20)) {
                            if (child1 != 0) {
                                for (int i = 0; i < txtTestArray2.size(); i++) {
                                    txtTestArray2.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(0).getTextSize() - 1f);
                                }
                                txtTestArray2.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray2.size(); j++) {
                                    if (finalPosition2 < txtRelationArray2.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition2) {
                                        txtRelationArray2.get(j).setLayoutParams(params11);
                                        txtRelationArray2.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray2.size(); j++) {
                                if (finalPosition2 < txtRelationArray2.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition2) {
                                    txtRelationArray2.get(j).setLayoutParams(params11);
                                    txtRelationArray2.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }

                }
            });

            linNamesParent3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (linNamesParent3.getChildCount() != 0){
                        int child1 = 0;

                        for (int j = 0; j < linNamesParent3.getChildCount(); j++){
                            child1 = child1 + linNamesParent3.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent3.getHeight() - 20)) {

                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray3.size(); i++) {
                                    txtTestArray3.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray3.get(0).getTextSize() - 1f);
                                }
                                txtTestArray3.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray3.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray3.size(); j++) {
                                    if (finalPosition3 < txtRelationArray3.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray3.get(finalPosition3).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition3) {
                                        txtRelationArray3.get(j).setLayoutParams(params11);
                                        txtRelationArray3.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray3.size(); j++) {
                                if (finalPosition3 < txtRelationArray3.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray3.get(finalPosition3).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition3) {
                                    txtRelationArray3.get(j).setLayoutParams(params11);
                                    txtRelationArray3.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            linNamesParent4.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if (linNamesParent4.getChildCount() != 0){
                        int child1 = 0;

                        for (int j = 0; j < linNamesParent4.getChildCount(); j++){
                            child1 = child1 + linNamesParent4.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent4.getHeight() - 20)) {
                            if (child1 != 0) {
                                for (int i = 0; i < txtTestArray4.size(); i++) {
                                    txtTestArray4.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray4.get(0).getTextSize() - 1f);
                                }
                                txtTestArray4.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray4.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray4.size(); j++) {
                                    if (finalPosition4 < txtRelationArray4.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray4.get(finalPosition4).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition4) {
                                        txtRelationArray4.get(j).setLayoutParams(params11);
                                        txtRelationArray4.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray4.size(); j++) {
                                if (finalPosition4 < txtRelationArray4.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray4.get(finalPosition4).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition4) {
                                    txtRelationArray4.get(j).setLayoutParams(params11);
                                    txtRelationArray4.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent4.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent4.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            if (arrowFlag != 1){
                imgArrow1.setVisibility(View.GONE);
                imgArrow2.setVisibility(View.GONE);
                imgArrow3.setVisibility(View.GONE);
                imgArrow4.setVisibility(View.GONE);
            }

        }else if (division == 6){
            view = inflater.inflate(R.layout.internet_general_six_division_item,null);
            LinearLayout linVisible2 = (LinearLayout)view.findViewById(R.id.linear_six_list_item_visible2);
            LinearLayout linVisible3 = (LinearLayout)view.findViewById(R.id.linear_six_list_item_visible3);
            LinearLayout linVisible4 = (LinearLayout)view.findViewById(R.id.linear_six_list_item_visible4);
            LinearLayout linVisible5 = (LinearLayout)view.findViewById(R.id.linear_six_list_item_visible5);
            LinearLayout linVisible6 = (LinearLayout)view.findViewById(R.id.linear_six_list_item_visible6);

            txtTitle1 = (TextView)view.findViewById(R.id.txt_six_division_item_title1);
            txtDeceasedName1 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name1);
            txtRelationship1 = (TextView)view.findViewById(R.id.txt_six_division_item_relationship1);
            txtCheckIn1 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in1);
            txtCheckOut1 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out1);
            txtLocation1 = (TextView)view.findViewById(R.id.txt_six_division_item_location1);
            imgProfile1 = (ImageView)view.findViewById(R.id.img_six_division_item_profile1);
            imgArrow1 = (ImageView)view.findViewById(R.id.img_six_division_item_arrow1);
            gridLayout = (GridLayout)view.findViewById(R.id.linear_six_division_item_name_parent11);
            linNamesParent1 = (LinearLayout) view.findViewById(R.id.linear_six_division_item_name_parent1);
            imgReligionBg1  = (ImageView)view.findViewById(R.id.img_six_division_item_religion_bg1);
            if (familyPosition == 0){
                linearLayoutTest = linNamesParent1;
            }


            txtTitle2 = (TextView)view.findViewById(R.id.txt_six_division_item_title2);
            txtDeceasedName2 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name2);
            linNamesParent2 = (LinearLayout)view.findViewById(R.id.linear_six_division_item_name_parent2);
            txtCheckIn2 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in2);
            txtCheckOut2 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out2);
            txtLocation2 = (TextView)view.findViewById(R.id.txt_six_division_item_location2);
            imgProfile2 = (ImageView)view.findViewById(R.id.img_six_division_item_profile2);
            imgArrow2 = (ImageView)view.findViewById(R.id.img_six_division_item_arrow2);
            imgReligionBg2  = (ImageView)view.findViewById(R.id.img_six_division_item_religion_bg2);
            if (familyPosition == 1){
                linearLayoutTest = linNamesParent2;
            }

            txtTitle3 = (TextView)view.findViewById(R.id.txt_six_division_item_title3);
            txtDeceasedName3 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name3);
            linNamesParent3 = (LinearLayout)view.findViewById(R.id.linear_six_division_item_name_parent3);
            txtCheckIn3 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in3);
            txtCheckOut3 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out3);
            txtLocation3 = (TextView)view.findViewById(R.id.txt_six_division_item_location3);
            imgProfile3 = (ImageView)view.findViewById(R.id.img_six_division_item_profile3);
            imgArrow3 = (ImageView)view.findViewById(R.id.img_six_division_item_arrow3);
            imgReligionBg3  = (ImageView)view.findViewById(R.id.img_six_division_item_religion_bg3);
            if (familyPosition == 2){
                linearLayoutTest = linNamesParent3;
            }

            txtTitle4 = (TextView)view.findViewById(R.id.txt_six_division_item_title4);
            txtDeceasedName4 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name4);
            linNamesParent4 = (LinearLayout)view.findViewById(R.id.linear_six_division_item_name_parent4);
            txtCheckIn4 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in4);
            txtCheckOut4 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out4);
            txtLocation4 = (TextView)view.findViewById(R.id.txt_six_division_item_location4);
            imgProfile4 = (ImageView)view.findViewById(R.id.img_six_division_item_profile4);
            imgArrow4 = (ImageView)view.findViewById(R.id.img_six_division_item_arrow4);
            imgReligionBg4  = (ImageView)view.findViewById(R.id.img_six_division_item_religion_bg4);
            if (familyPosition == 3){
                linearLayoutTest = linNamesParent4;
            }

            txtTitle5 = (TextView)view.findViewById(R.id.txt_six_division_item_title5);
            txtDeceasedName5 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name5);
            linNamesParent5 = (LinearLayout)view.findViewById(R.id.linear_six_division_item_name_parent5);
            txtCheckIn5 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in5);
            txtCheckOut5 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out5);
            txtLocation5 = (TextView)view.findViewById(R.id.txt_six_division_item_location5);
            imgProfile5 = (ImageView)view.findViewById(R.id.img_six_division_item_profile5);
            imgArrow5 = (ImageView)view.findViewById(R.id.img_six_division_item_arrow5);
            imgReligionBg5  = (ImageView)view.findViewById(R.id.img_six_division_item_religion_bg5);
            if (familyPosition == 4){
                linearLayoutTest = linNamesParent5;
            }

            txtTitle6 = (TextView)view.findViewById(R.id.txt_six_division_item_title6);
            txtDeceasedName6 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name6);
            linNamesParent6 = (LinearLayout)view.findViewById(R.id.linear_six_division_item_name_parent6);
            txtCheckIn6 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in6);
            txtCheckOut6 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out6);
            txtLocation6 = (TextView)view.findViewById(R.id.txt_six_division_item_location6);
            imgProfile6 = (ImageView)view.findViewById(R.id.img_six_division_item_profile6);
            imgArrow6 = (ImageView)view.findViewById(R.id.img_six_division_item_arrow6);
            imgVisible = (ImageView)view.findViewById(R.id.img_six_division_item_visible);
            imgReligionBg6  = (ImageView)view.findViewById(R.id.img_six_division_item_religion_bg6);
            if (familyPosition == 5){
                linearLayoutTest = linNamesParent6;
            }

            txtEtc1         = (TextView)view.findViewById(R.id.txt_six_division_item_etc1);
            txtEtc2         = (TextView)view.findViewById(R.id.txt_six_division_item_etc2);
            txtEtc3         = (TextView)view.findViewById(R.id.txt_six_division_item_etc3);
            txtEtc4         = (TextView)view.findViewById(R.id.txt_six_division_item_etc4);
            txtEtc5         = (TextView)view.findViewById(R.id.txt_six_division_item_etc5);
            txtEtc6         = (TextView)view.findViewById(R.id.txt_six_division_item_etc6);
            txtEtc7         = (TextView)view.findViewById(R.id.txt_six_division_item_etc7);
            txtEtc8         = (TextView)view.findViewById(R.id.txt_six_division_item_etc8);
            txtEtc9         = (TextView)view.findViewById(R.id.txt_six_division_item_etc9);
            txtEtc10         = (TextView)view.findViewById(R.id.txt_six_division_item_etc10);
            txtEtc11         = (TextView)view.findViewById(R.id.txt_six_division_item_etc11);
            txtEtc12         = (TextView)view.findViewById(R.id.txt_six_division_item_etc12);
            txtEtc13        = (TextView)view.findViewById(R.id.txt_six_division_item_etc13);
            txtEtc14         = (TextView)view.findViewById(R.id.txt_six_division_item_etc14);
            txtEtc15         = (TextView)view.findViewById(R.id.txt_six_division_item_etc15);
            txtEtc16         = (TextView)view.findViewById(R.id.txt_six_division_item_etc16);
            txtEtc17         = (TextView)view.findViewById(R.id.txt_six_division_item_etc17);
            txtEtc18         = (TextView)view.findViewById(R.id.txt_six_division_item_etc18);

            txtTitle1.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName1.setTextColor(Color.parseColor(deceasedColor));
            txtEtc1.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc2.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc3.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn1.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut1.setTextColor(Color.parseColor(checkOutColor));
            txtLocation1.setTextColor(Color.parseColor(locationColor));

            txtTitle2.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName2.setTextColor(Color.parseColor(deceasedColor));
            txtEtc4.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc5.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc6.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn2.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut2.setTextColor(Color.parseColor(checkOutColor));
            txtLocation2.setTextColor(Color.parseColor(locationColor));

            txtTitle3.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName3.setTextColor(Color.parseColor(deceasedColor));
            txtEtc7.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc8.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc9.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn3.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut3.setTextColor(Color.parseColor(checkOutColor));
            txtLocation3.setTextColor(Color.parseColor(locationColor));

            txtTitle4.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName4.setTextColor(Color.parseColor(deceasedColor));
            txtEtc10.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc11.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc12.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn4.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut4.setTextColor(Color.parseColor(checkOutColor));
            txtLocation4.setTextColor(Color.parseColor(locationColor));

            txtTitle5.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName5.setTextColor(Color.parseColor(deceasedColor));
            txtEtc13.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc14.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc15.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn5.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut5.setTextColor(Color.parseColor(checkOutColor));
            txtLocation5.setTextColor(Color.parseColor(locationColor));

            txtTitle6.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName6.setTextColor(Color.parseColor(deceasedColor));
            txtEtc16.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc17.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc18.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn6.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut6.setTextColor(Color.parseColor(checkOutColor));
            txtLocation6.setTextColor(Color.parseColor(locationColor));

            linearBg = (LinearLayout)view.findViewById(R.id.linear_general_six_division_bg);
            linearTop = (LinearLayout)view.findViewById(R.id.linear_general_six_division_top);

            if (statusPlateNo.equals("46")){
                linearBg.setBackgroundResource(R.drawable.img_division_six_top_black_42);
            }else if (statusPlateNo.equals("53")){
                linearBg.setBackgroundResource(R.drawable.img_division_six_top_black_49);
            }else if (statusPlateNo.equals("59")){
                linearBg.setBackgroundResource(R.drawable.img_division_six_top_black_55);
            }else if (statusPlateNo.equals("65")){
                linearBg.setBackgroundResource(R.drawable.img_division_six_top_black_61);
            }
            if (bottomText.length() != 0){
                if (statusPlateNo.equals("46")){
                    linearBg.setBackgroundResource(R.drawable.img_division_six_bottom_black_42);
                }else if (statusPlateNo.equals("53")){
                    linearBg.setBackgroundResource(R.drawable.img_division_six_bottom_black_49);
                }else if (statusPlateNo.equals("59")){
                    linearBg.setBackgroundResource(R.drawable.img_division_six_bottom_black_55);
                }else if (statusPlateNo.equals("65")){
                    linearBg.setBackgroundResource(R.drawable.img_division_six_bottom_black_61);
                }
            }

            if (bottomText.length() != 0){
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
                params.weight = 51;
                linearTop.setLayoutParams(params);
            }else{
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
                params.weight = 99;
                linearTop.setLayoutParams(params);
            }

            txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(i * 6));
            txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(i * 6));
            txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(i * 6));
            txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(i * 6));

            if (itemArrayList.get(i).getLocationArray().get((i * 6)).contains("\r\n")){
                txtLocation1.setMaxLines(2);
            }else{
                txtLocation1.setMaxLines(1);
            }

            if (itemArrayList.get(i).getLocationArray().get((i * 6)).length() != 0){
                txtLocation1.setText(itemArrayList.get(i).getLocationArray().get((i * 6)));
            }else{
                txtLocation1.setText("미 정");
            }

            if (itemArrayList.get(i).getImgPathArray().get(i * 6).length() != 0){
                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(i * 6)).into(imgProfile1);
            }else{
                imgProfile1.setImageResource(R.drawable.img_six_division_no_profile);
            }

            if (itemArrayList.get(i).getReligionArray().get(i * 6).length() > 0){
                Glide.with(context).load(itemArrayList.get(i).getReligionArray().get(i * 6)).into(imgReligionBg1);
            }

            String result = "";

            for (int j = 0; j < itemArrayList.get(i).getNamesArray().get(familyPosition).size(); j++){
                result += itemArrayList.get(i).getRelationArray().get(familyPosition).get(j) + "   " + itemArrayList.get(i).getNamesArray().get(familyPosition).get(j) + "\n";
            }

            txtRelationship1.setText(result);

            if (divisionImg.length() != 0){
                Glide.with(context).load(divisionImg).into(imgVisible);
            }else{
                imgVisible.setBackgroundResource(R.drawable.img_six_visible);
            }

            Log.i("tag","familyPosition : " + familyPosition);

            int relationLenth10 = 0;
            int position1 = 0;

//            gridLayout.setScrollContainer(false);
//            gridLayout.setRowCount(10);
//            gridLayout.setColumnCount(1);
//            gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
//            gridLayout.setRowOrderPreserved(false);
//            gridLayout.setUseDefaultMargins(true);

            for (int a = 0; a < itemArrayList.get(i).getNamesArray().get(i * 6).size(); a++){
                if (relationLenth10 < itemArrayList.get(i).getRelationArray().get(i * 6).get(a).length()){
                    if (itemArrayList.get(i).getRelationArray().get(i * 6).get(a).contains(" ")){
                        relationLenth10 = itemArrayList.get(i).getRelationArray().get(i * 6).get(a).length()-1;
                    }else{
                        relationLenth10 = itemArrayList.get(i).getRelationArray().get(i * 6).get(a).length();
                    }
                    position1 = a;
                }
            }

            int relationLenth2 = 0;
            int position2 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 1){
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 1).size(); a++){
                    if (relationLenth2 < itemArrayList.get(i).getRelationArray().get((i * 6) + 1).get(a).length()){
                        if (itemArrayList.get(i).getRelationArray().get((i * 6) + 1).get(a).contains(" ")){
                            relationLenth2 = itemArrayList.get(i).getRelationArray().get((i * 6) + 1).get(a).length() - 1;
                        }else{
                            relationLenth2 = itemArrayList.get(i).getRelationArray().get((i * 6) + 1).get(a).length();
                        }
                        position2 = a;
                    }
                }
            }

            int relationLenth3 = 0;
            int position3 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 2){
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 2).size(); a++){
                    if (relationLenth3 < itemArrayList.get(i).getRelationArray().get((i * 6) + 2).get(a).length()){
                        if (itemArrayList.get(i).getRelationArray().get((i * 6) + 2).get(a).contains(" ")){
                            relationLenth3 = itemArrayList.get(i).getRelationArray().get((i * 6) + 2).get(a).length()-1;
                        }else{
                            relationLenth3 = itemArrayList.get(i).getRelationArray().get((i * 6) + 2).get(a).length();
                        }
                        position3 = a;
                    }
                }
            }

            int relationLenth4 = 0;
            int position4 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 3){
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 3).size(); a++){
                    if (relationLenth4 < itemArrayList.get(i).getRelationArray().get((i * 6) + 3).get(a).length()){
                        if (itemArrayList.get(i).getRelationArray().get((i * 6) + 3).get(a).contains(" ")){
                            relationLenth4 = itemArrayList.get(i).getRelationArray().get((i * 6) + 3).get(a).length()-1;
                        }else{
                            relationLenth4 = itemArrayList.get(i).getRelationArray().get((i * 6) + 3).get(a).length();
                        }
                        position4 = a;
                    }
                }
            }

            int relationLenth5 = 0;
            int position5 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 4){
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 4).size(); a++){
                    if (relationLenth5 < itemArrayList.get(i).getRelationArray().get((i * 6) + 4).get(a).length()){
                        if (itemArrayList.get(i).getRelationArray().get((i * 6) + 4).get(a).contains(" ")){
                            relationLenth5 = itemArrayList.get(i).getRelationArray().get((i * 6) + 4).get(a).length()-1;
                        }else{
                            relationLenth5 = itemArrayList.get(i).getRelationArray().get((i * 6) + 4).get(a).length();
                        }
                        position5 = a;
                    }
                }
            }

            int relationLenth6 = 0;
            int position6 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 5){
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 5).size(); a++){
                    if (relationLenth6 < itemArrayList.get(i).getRelationArray().get((i * 6) + 5).get(a).length()){
                        if (itemArrayList.get(i).getRelationArray().get((i * 6) + 5).get(a).contains(" ")){
                            relationLenth6 = itemArrayList.get(i).getRelationArray().get((i * 6) + 5).get(a).length()-1;
                        }else{
                            relationLenth6 = itemArrayList.get(i).getRelationArray().get((i * 6) + 5).get(a).length();
                        }
                        position6 = a;
                    }
                }
            }else{
                imgVisible.setVisibility(View.VISIBLE);
            }

            final int finalPosition1 = position1;
            final int finalPosition2 = position2;
            final int finalPosition3 = position3;
            final int finalPosition4 = position4;
            final int finalPosition5 = position5;
            final int finalPosition6 = position6;

            for (int a = 0; a < itemArrayList.get(i).getNamesArray().get(i * 6).size(); a++){
                ListMake(a,itemArrayList.get(i).getRelationArray().get(i * 6).get(a),itemArrayList.get(i).getNamesArray().get(i * 6).get(a), 60f,linNamesParent1,familyColor,1);
            }

            if (itemArrayList.get(i).getArrowNoArray().get((i * 6)).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 6)).equals("")){
                imgArrow1.setVisibility(View.GONE);
            }else{
                imgArrow1.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 6))));
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 1){
                txtTitle2.setText(itemArrayList.get(i).getTitleArray().get((i * 6) + 1));
                txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get((i * 6) + 1));
//                txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get((i * 6) + 1));
                txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get((i * 6) + 1));
                txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get((i * 6) + 1));

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 1).contains("\r\n")){
                    txtLocation2.setMaxLines(2);
                }else{
                    txtLocation2.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 1).length() != 0){
                    txtLocation2.setText(itemArrayList.get(i).getLocationArray().get((i * 6) + 1));
                }else{
                    txtLocation2.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 6) + 1).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 6) + 1)).into(imgProfile2);
                }else{
                    imgProfile2.setImageResource(R.drawable.img_six_division_no_profile);
                }

                if (itemArrayList.get(i).getReligionArray().get((i * 6) + 1).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 6) + 1)).into(imgReligionBg2);
                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 1).size(); a++){
                    Log.i("tag","a : " + a);
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 6) + 1).get(a),itemArrayList.get(i).getNamesArray().get((i * 6) + 1).get(a),50f,linNamesParent2,familyColor,2);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 6) + 1).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 6) + 1).equals("")){
                    imgArrow2.setVisibility(View.GONE);
                }else{
                    imgArrow2.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 6) + 1)));
                }

            }else{
                linVisible2.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 2){
                Log.i("tag","testTest" + " " + itemArrayList.get(i).getTitleArray().get((i * 6) + 2));
                txtTitle3.setText(itemArrayList.get(i).getTitleArray().get((i * 6) + 2));
                txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get((i * 6) + 2));
//                txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get((i * 6) + 2));
                txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get((i * 6) + 2));
                txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get((i * 6) + 2));

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 2).contains("\r\n")){
                    txtLocation3.setMaxLines(2);
                }else{
                    txtLocation3.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 2).length() != 0){
                    txtLocation3.setText(itemArrayList.get(i).getLocationArray().get((i * 6) + 2));
                }else{
                    txtLocation3.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 6) + 2).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 6) + 2)).into(imgProfile3);
                }else{
                    imgProfile3.setImageResource(R.drawable.img_six_division_no_profile);
                }

                if (itemArrayList.get(i).getReligionArray().get((i * 6) + 2).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 6) + 2)).into(imgReligionBg3);
                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 2).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 6) + 2).get(a),itemArrayList.get(i).getNamesArray().get((i * 6) + 2).get(a),50f,linNamesParent3,familyColor,3);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 6) + 2).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 6) + 2).equals("")){
                    imgArrow3.setVisibility(View.GONE);
                }else{
                    imgArrow3.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 6) + 2)));
                }
            }else{
                linVisible3.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 3){
                txtTitle4.setText(itemArrayList.get(i).getTitleArray().get((i * 6) + 3));
                txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get((i * 6) + 3));
//                txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get((i * 6) + 3));
                txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get((i * 6) + 3));
                txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get((i * 6) + 3));

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 3).contains("\r\n")){
                    txtLocation4.setMaxLines(2);
                }else{
                    txtLocation4.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 3).length() != 0){
                    txtLocation4.setText(itemArrayList.get(i).getLocationArray().get((i * 6) + 3));
                }else{
                    txtLocation4.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 6) + 3).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 6) + 3)).into(imgProfile4);
                }else{
                    imgProfile4.setImageResource(R.drawable.img_six_division_no_profile);
                }

                if (itemArrayList.get(i).getReligionArray().get((i * 6) + 3).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 6) + 3)).into(imgReligionBg4);
                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 3).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 6) + 3).get(a),itemArrayList.get(i).getNamesArray().get((i * 6) + 3).get(a),50f,linNamesParent4,familyColor,4);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 6) + 3).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 6) + 3).equals("")){
                    imgArrow4.setVisibility(View.GONE);
                }else{
                    imgArrow4.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 6) + 3)));
                }
            }else{
                linVisible4.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 4){
                txtTitle5.setText(itemArrayList.get(i).getTitleArray().get((i * 6) + 4));
                txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get((i * 6) + 4));
//                txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get((i * 6) + 4));
                txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get((i * 6) + 4));
                txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get((i * 6) + 4));

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 4).contains("\r\n")){
                    txtLocation5.setMaxLines(2);
                }else{
                    txtLocation5.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 4).length() != 0){
                    txtLocation5.setText(itemArrayList.get(i).getLocationArray().get((i * 6) + 4));
                }else{
                    txtLocation5.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 6) + 4).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 6) + 4)).into(imgProfile5);
                }else{
                    imgProfile5.setImageResource(R.drawable.img_six_division_no_profile);
                }

                if (itemArrayList.get(i).getReligionArray().get((i * 6) + 4).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 6) + 4)).into(imgReligionBg5);
                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 4).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 6) + 4).get(a),itemArrayList.get(i).getNamesArray().get((i * 6) + 4).get(a),50f,linNamesParent5,familyColor,5);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 6) + 4).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 6) + 4).equals("")){
                    imgArrow5.setVisibility(View.GONE);
                }else{
                    imgArrow5.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 6) + 4)));
                }
            }else{
                linVisible5.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 5){
                txtTitle6.setText(itemArrayList.get(i).getTitleArray().get((i * 6) + 5));
                txtDeceasedName6.setText(itemArrayList.get(i).getDeceasedArray().get((i * 6) + 5));
//                txtRelationship6.setText(itemArrayList.get(i).getRelationshipArray().get((i * 6) + 5));
                txtCheckIn6.setText(itemArrayList.get(i).getCheckInArray().get((i * 6) + 5));
                txtCheckOut6.setText(itemArrayList.get(i).getCheckOutArray().get((i * 6) + 5));

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 5).contains("\r\n")){
                    txtLocation6.setMaxLines(2);
                }else{
                    txtLocation6.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 5).length() != 0){
                    txtLocation6.setText(itemArrayList.get(i).getLocationArray().get((i * 6) + 5));
                }else{
                    txtLocation6.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 6) + 5).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 6) + 5)).into(imgProfile6);
                }else{
                    imgProfile6.setImageResource(R.drawable.img_six_division_no_profile);
                }

                if (itemArrayList.get(i).getReligionArray().get((i * 6) + 5).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 6) + 5)).into(imgReligionBg6);
                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 5).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 6) + 5).get(a),itemArrayList.get(i).getNamesArray().get((i * 6) + 5).get(a),50f,linNamesParent6,familyColor,6);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 6) + 5).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 6) + 5).equals("")){
                    imgArrow6.setVisibility(View.GONE);
                }else{
                    imgArrow6.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 6) + 5)));
                }
            }else{
                linVisible6.setVisibility(View.GONE);
            }

            linNamesParent1.setVisibility(View.VISIBLE);
//            gridLayout.setVisibility(View.VISIBLE);
            txtRelationship1.setVisibility(View.GONE);

            txtEtc1.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc2.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc3.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtLocation1.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtCheckOut1.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc4.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc5.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc6.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtLocation2.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtCheckOut2.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc7.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc8.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc9.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtLocation3.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtCheckOut3.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc10.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc11.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc12.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtLocation4.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtCheckOut4.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc13.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc14.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc15.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtLocation5.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtCheckOut5.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc16.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc17.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtEtc18.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtLocation6.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            txtCheckOut6.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);

            linNamesParent1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if (linNamesParent1.getChildCount() != 0){
                        int child1 = 0;

                        for (int j = 0; j < linNamesParent1.getChildCount(); j++){
                            child1 = child1 + linNamesParent1.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent1.getHeight() - 20)) {

                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray1.size(); i++) {
                                    txtTestArray1.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(0).getTextSize() - 1f);
                                }
                                txtTestArray1.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray1.size(); j++) {
                                    if (finalPosition1 < txtRelationArray1.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition1) {
                                        txtRelationArray1.get(j).setLayoutParams(params11);
                                        txtRelationArray1.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray1.size(); j++) {
                                if (finalPosition1 < txtRelationArray1.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray1.get(j).setLayoutParams(params11);
                                    txtRelationArray1.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            linNamesParent2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if (linNamesParent2.getChildCount() != 0){
                        int child1 = 0;

                        Log.i("tag","parent2 cnt : " + linNamesParent2.getChildCount());

                        for (int j = 0; j < linNamesParent2.getChildCount(); j++){
                            child1 = child1 + linNamesParent2.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent2.getHeight() - 20)) {
                            if (child1 != 0) {
                                for (int i = 0; i < txtTestArray2.size(); i++) {
                                    txtTestArray2.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(0).getTextSize() - 1f);
                                }
                                txtTestArray2.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray2.size(); j++) {
                                    if (finalPosition2 < txtRelationArray2.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition2) {
                                        txtRelationArray2.get(j).setLayoutParams(params11);
                                        txtRelationArray2.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray2.size(); j++) {
                                if (finalPosition2 < txtRelationArray2.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition2) {
                                    txtRelationArray2.get(j).setLayoutParams(params11);
                                    txtRelationArray2.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }

                }
            });

            linNamesParent3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (linNamesParent3.getChildCount() != 0){
                        int child1 = 0;

                        for (int j = 0; j < linNamesParent3.getChildCount(); j++){
                            child1 = child1 + linNamesParent3.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent3.getHeight() - 20)) {

                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray3.size(); i++) {
                                    txtTestArray3.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray3.get(0).getTextSize() - 1f);
                                }
                                txtTestArray3.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray3.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray3.size(); j++) {
                                    if (finalPosition3 < txtRelationArray3.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray3.get(finalPosition3).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition3) {
                                        txtRelationArray3.get(j).setLayoutParams(params11);
                                        txtRelationArray3.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray3.size(); j++) {
                                if (finalPosition3 < txtRelationArray3.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray3.get(finalPosition3).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition3) {
                                    txtRelationArray3.get(j).setLayoutParams(params11);
                                    txtRelationArray3.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            linNamesParent4.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if (linNamesParent4.getChildCount() != 0){
                        int child1 = 0;

                        for (int j = 0; j < linNamesParent4.getChildCount(); j++){
                            child1 = child1 + linNamesParent4.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent4.getHeight() - 20)) {
                            if (child1 != 0) {
                                for (int i = 0; i < txtTestArray4.size(); i++) {
                                    txtTestArray4.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray4.get(0).getTextSize() - 1f);
                                }
                                txtTestArray4.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray4.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray4.size(); j++) {
                                    if (finalPosition4 < txtRelationArray4.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray4.get(finalPosition4).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition4) {
                                        txtRelationArray4.get(j).setLayoutParams(params11);
                                        txtRelationArray4.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray4.size(); j++) {
                                if (finalPosition4 < txtRelationArray4.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray4.get(finalPosition4).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition4) {
                                    txtRelationArray4.get(j).setLayoutParams(params11);
                                    txtRelationArray4.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent4.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent4.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            linNamesParent5.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (linNamesParent5.getChildCount() != 0){
                        int child1 = 0;

                        for (int j = 0; j < linNamesParent5.getChildCount(); j++){
                            child1 = child1 + linNamesParent5.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent5.getHeight() - 20)) {

                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray5.size(); i++) {
                                    txtTestArray5.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray5.get(0).getTextSize() - 1f);
                                }
                                txtTestArray5.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray5.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray5.size(); j++) {
                                    if (finalPosition5 < txtRelationArray5.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray5.get(finalPosition5).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition5) {
                                        txtRelationArray5.get(j).setLayoutParams(params11);
                                        txtRelationArray5.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray5.size(); j++) {
                                if (finalPosition5 < txtRelationArray5.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray5.get(finalPosition5).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition5) {
                                    txtRelationArray5.get(j).setLayoutParams(params11);
                                    txtRelationArray5.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent5.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent5.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            linNamesParent6.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if (linNamesParent6.getChildCount() != 0){
                        int child1 = 0;

                        for (int j = 0; j < linNamesParent6.getChildCount(); j++){
                            child1 = child1 + linNamesParent6.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent6.getHeight() - 20)) {
                            if (child1 != 0) {
                                for (int i = 0; i < txtTestArray6.size(); i++) {
                                    txtTestArray6.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray6.get(0).getTextSize() - 1f);
                                }
                                txtTestArray6.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray6.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray6.size(); j++) {
                                    if (finalPosition6 < txtRelationArray6.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray6.get(finalPosition6).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition6) {
                                        txtRelationArray6.get(j).setLayoutParams(params11);
                                        txtRelationArray6.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray6.size(); j++) {
                                if (finalPosition6 < txtRelationArray6.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray6.get(finalPosition6).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition6) {
                                    txtRelationArray6.get(j).setLayoutParams(params11);
                                    txtRelationArray6.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent6.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent6.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            if (arrowFlag != 1){
                imgArrow1.setVisibility(View.GONE);
                imgArrow2.setVisibility(View.GONE);
                imgArrow3.setVisibility(View.GONE);
                imgArrow4.setVisibility(View.GONE);
                imgArrow5.setVisibility(View.GONE);
                imgArrow6.setVisibility(View.GONE);
            }
        }else if (division == 8){

            view = inflater.inflate(R.layout.internet_general_eight_division_item,null);
            final LinearLayout linVisible2 = (LinearLayout)view.findViewById(R.id.linear_eight_visible2);
            final LinearLayout linVisible3 = (LinearLayout)view.findViewById(R.id.linear_eight_visible3);
            final LinearLayout linVisible4 = (LinearLayout)view.findViewById(R.id.linear_eight_visible4);
            final LinearLayout linVisible5 = (LinearLayout)view.findViewById(R.id.linear_eight_visible5);
            final LinearLayout linVisible6 = (LinearLayout)view.findViewById(R.id.linear_eight_visible6);
            final LinearLayout linVisible7 = (LinearLayout)view.findViewById(R.id.linear_eight_visible7);
            final LinearLayout linVisible8 = (LinearLayout)view.findViewById(R.id.linear_eight_visible8);

            txtTitle1 = (TextView)view.findViewById(R.id.txt_eight_division_item_title1);
            txtDeceasedName1 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name1);
            txtRelationship1 = (TextView)view.findViewById(R.id.txt_eight_division_item_relationship1);
            txtCheckIn1 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in1);
            txtCheckOut1 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out1);
            txtLocation1 = (TextView)view.findViewById(R.id.txt_eight_division_item_location1);
            imgProfile1 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile1);
            imgArrow1 = (ImageView)view.findViewById(R.id.img_eight_division_item_arrow1);
            linNamesParent1 = (LinearLayout)view.findViewById(R.id.linear_eight_division_item_name_parent1);
            imgReligionBg1  = (ImageView)view.findViewById(R.id.img_eight_division_item_religion_bg1);
            if (familyPosition == 0){
                linearLayoutTest = linNamesParent1;
            }


            txtTitle2 = (TextView)view.findViewById(R.id.txt_eight_division_item_title2);
            txtDeceasedName2 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name2);
            linNamesParent2 = (LinearLayout)view.findViewById(R.id.linear_eight_division_item_name_parent2);
            txtCheckIn2 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in2);
            txtCheckOut2 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out2);
            txtLocation2 = (TextView)view.findViewById(R.id.txt_eight_division_item_location2);
            imgProfile2 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile2);
            imgArrow2 = (ImageView)view.findViewById(R.id.img_eight_division_item_arrow2);
            imgReligionBg2  = (ImageView)view.findViewById(R.id.img_eight_division_item_religion_bg2);
            if (familyPosition == 1){
                linearLayoutTest = linNamesParent2;
            }

            txtTitle3 = (TextView)view.findViewById(R.id.txt_eight_division_item_title3);
            txtDeceasedName3 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name3);
            linNamesParent3 = (LinearLayout)view.findViewById(R.id.linear_eight_division_item_name_parent3);
            txtCheckIn3 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in3);
            txtCheckOut3 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out3);
            txtLocation3 = (TextView)view.findViewById(R.id.txt_eight_division_item_location3);
            imgProfile3 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile3);
            imgArrow3 = (ImageView)view.findViewById(R.id.img_eight_division_item_arrow3);
            imgReligionBg3  = (ImageView)view.findViewById(R.id.img_eight_division_item_religion_bg3);
            if (familyPosition == 2){
                linearLayoutTest = linNamesParent3;
            }

            txtTitle4 = (TextView)view.findViewById(R.id.txt_eight_division_item_title4);
            txtDeceasedName4 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name4);
            linNamesParent4 = (LinearLayout)view.findViewById(R.id.linear_eight_division_item_name_parent4);
            txtCheckIn4 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in4);
            txtCheckOut4 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out4);
            txtLocation4 = (TextView)view.findViewById(R.id.txt_eight_division_item_location4);
            imgProfile4 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile4);
            imgArrow4 = (ImageView)view.findViewById(R.id.img_eight_division_item_arrow4);
            imgReligionBg4  = (ImageView)view.findViewById(R.id.img_eight_division_item_religion_bg4);
            if (familyPosition == 3){
                linearLayoutTest = linNamesParent4;
            }

            txtTitle5 = (TextView)view.findViewById(R.id.txt_eight_division_item_title5);
            txtDeceasedName5 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name5);
            linNamesParent5 = (LinearLayout)view.findViewById(R.id.linear_eight_division_item_name_parent5);
            txtCheckIn5 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in5);
            txtCheckOut5 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out5);
            txtLocation5 = (TextView)view.findViewById(R.id.txt_eight_division_item_location5);
            imgProfile5 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile5);
            imgArrow5 = (ImageView)view.findViewById(R.id.img_eight_division_item_arrow5);
            imgReligionBg5  = (ImageView)view.findViewById(R.id.img_eight_division_item_religion_bg5);
            if (familyPosition == 4){
                linearLayoutTest = linNamesParent5;
            }

            txtTitle6 = (TextView)view.findViewById(R.id.txt_eight_division_item_title6);
            txtDeceasedName6 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name6);
            linNamesParent6 = (LinearLayout)view.findViewById(R.id.linear_eight_division_item_name_parent6);
            txtCheckIn6 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in6);
            txtCheckOut6 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out6);
            txtLocation6 = (TextView)view.findViewById(R.id.txt_eight_division_item_location6);
            imgProfile6 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile6);
            imgArrow6 = (ImageView)view.findViewById(R.id.img_eight_division_item_arrow6);
            imgReligionBg6  = (ImageView)view.findViewById(R.id.img_eight_division_item_religion_bg6);
            if (familyPosition == 5){
                linearLayoutTest = linNamesParent6;
            }

            txtTitle7 = (TextView)view.findViewById(R.id.txt_eight_division_item_title7);
            txtDeceasedName7 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name7);
            linNamesParent7 = (LinearLayout)view.findViewById(R.id.linear_eight_division_item_name_parent7);
            txtCheckIn7 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in7);
            txtCheckOut7 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out7);
            txtLocation7 = (TextView)view.findViewById(R.id.txt_eight_division_item_location7);
            imgProfile7 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile7);
            imgArrow7 = (ImageView)view.findViewById(R.id.img_eight_division_item_arrow7);
            imgReligionBg7  = (ImageView)view.findViewById(R.id.img_eight_division_item_religion_bg7);
            if (familyPosition == 6){
                linearLayoutTest = linNamesParent7;
            }

            txtTitle8 = (TextView)view.findViewById(R.id.txt_eight_division_item_title8);
            txtDeceasedName8 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name8);
            linNamesParent8 = (LinearLayout)view.findViewById(R.id.linear_eight_division_item_name_parent8);
            txtCheckIn8 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in8);
            txtCheckOut8 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out8);
            txtLocation8 = (TextView)view.findViewById(R.id.txt_eight_division_item_location8);
            imgProfile8 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile8);
            imgArrow8 = (ImageView)view.findViewById(R.id.img_eight_division_item_arrow8);
            imgVisible = (ImageView)view.findViewById(R.id.img_eight_division_item_visible);
            imgReligionBg8  = (ImageView)view.findViewById(R.id.img_eight_division_item_religion_bg8);
            if (familyPosition == 7){
                linearLayoutTest = linNamesParent8;
            }

            txtEtc1          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc1);
            txtEtc2          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc2);
            txtEtc3          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc3);
            txtEtc4          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc4);
            txtEtc5          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc5);
            txtEtc6          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc6);
            txtEtc7          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc7);
            txtEtc8          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc8);
            txtEtc9          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc9);
            txtEtc10         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc10);
            txtEtc11         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc11);
            txtEtc12         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc12);
            txtEtc13         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc13);
            txtEtc14         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc14);
            txtEtc15         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc15);
            txtEtc16         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc16);
            txtEtc17         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc17);
            txtEtc18         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc18);
            txtEtc19         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc19);
            txtEtc20         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc20);
            txtEtc21         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc21);
            txtEtc22         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc22);
            txtEtc23         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc23);
            txtEtc24         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc24);

            txtTitle1.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName1.setTextColor(Color.parseColor(deceasedColor));
            txtEtc1.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc2.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc3.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn1.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut1.setTextColor(Color.parseColor(checkOutColor));
            txtLocation1.setTextColor(Color.parseColor(locationColor));

            txtTitle2.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName2.setTextColor(Color.parseColor(deceasedColor));
            txtEtc4.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc5.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc6.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn2.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut2.setTextColor(Color.parseColor(checkOutColor));
            txtLocation2.setTextColor(Color.parseColor(locationColor));

            txtTitle3.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName3.setTextColor(Color.parseColor(deceasedColor));
            txtEtc7.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc8.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc9.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn3.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut3.setTextColor(Color.parseColor(checkOutColor));
            txtLocation3.setTextColor(Color.parseColor(locationColor));

            txtTitle4.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName4.setTextColor(Color.parseColor(deceasedColor));
            txtEtc10.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc11.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc12.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn4.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut4.setTextColor(Color.parseColor(checkOutColor));
            txtLocation4.setTextColor(Color.parseColor(locationColor));

            txtTitle5.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName5.setTextColor(Color.parseColor(deceasedColor));
            txtEtc13.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc14.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc15.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn5.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut5.setTextColor(Color.parseColor(checkOutColor));
            txtLocation5.setTextColor(Color.parseColor(locationColor));

            txtTitle6.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName6.setTextColor(Color.parseColor(deceasedColor));
            txtEtc16.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc17.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc18.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn6.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut6.setTextColor(Color.parseColor(checkOutColor));
            txtLocation6.setTextColor(Color.parseColor(locationColor));

            txtTitle7.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName7.setTextColor(Color.parseColor(deceasedColor));
            txtEtc19.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc20.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc21.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn7.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut7.setTextColor(Color.parseColor(checkOutColor));
            txtLocation7.setTextColor(Color.parseColor(locationColor));

            txtTitle8.setTextColor(Color.parseColor(roomNameColor));
            txtDeceasedName8.setTextColor(Color.parseColor(deceasedColor));
            txtEtc22.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc23.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc24.setTextColor(Color.parseColor(locationTitleColor));
            txtCheckIn8.setTextColor(Color.parseColor(checkInColor));
            txtCheckOut8.setTextColor(Color.parseColor(checkOutColor));
            txtLocation8.setTextColor(Color.parseColor(locationColor));

            final LinearLayout linearBg = (LinearLayout)view.findViewById(R.id.linear_general_eight_division_bg);
            final LinearLayout linearTop = (LinearLayout)view.findViewById(R.id.linear_general_eight_division_top);

            if (statusPlateNo.equals("47")){
                linearBg.setBackgroundResource(R.drawable.img_division_eight_top_black_42);
            }else if (statusPlateNo.equals("54")){
                linearBg.setBackgroundResource(R.drawable.img_division_eight_top_black_49);
            }else if (statusPlateNo.equals("60")){
                linearBg.setBackgroundResource(R.drawable.img_division_eight_top_black_55);
            }else if (statusPlateNo.equals("66")){
                linearBg.setBackgroundResource(R.drawable.img_division_eight_top_black_61);
            }
            if (bottomText.length() != 0){
                if (statusPlateNo.equals("47")){
                    linearBg.setBackgroundResource(R.drawable.img_division_eight_bottom_black_42);
                }else if (statusPlateNo.equals("54")){
                    linearBg.setBackgroundResource(R.drawable.img_division_eight_bottom_black_49);
                }else if (statusPlateNo.equals("60")){
                    linearBg.setBackgroundResource(R.drawable.img_division_eight_bottom_black_55);
                }else if (statusPlateNo.equals("66")){
                    linearBg.setBackgroundResource(R.drawable.img_division_eight_bottom_black_61);
                }
            }

            if (bottomText.length() != 0){
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
                params.weight = 50;
                linearTop.setLayoutParams(params);
            }

            txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(i * 8));
            txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(i * 8));
            txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(i * 8));
            txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(i * 8));

            if (itemArrayList.get(i).getLocationArray().get((i * 8)).contains("\r\n")){
                txtLocation1.setMaxLines(2);
            }else{
                txtLocation1.setMaxLines(1);
            }

            if (itemArrayList.get(i).getLocationArray().get((i * 8)).length() != 0){
                txtLocation1.setText(itemArrayList.get(i).getLocationArray().get((i * 8)));
            }else{
                txtLocation1.setText("미 정");
            }

            if (itemArrayList.get(i).getImgPathArray().get(i * 8).length() != 0){
                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(i * 8)).into(imgProfile1);
            }else{
                imgProfile1.setImageResource(R.drawable.img_eight_division_no_profile);
            }

            if (itemArrayList.get(i).getReligionArray().get(i * 8).length() > 0){
                Glide.with(context).load(itemArrayList.get(i).getReligionArray().get(i * 8)).into(imgReligionBg1);
            }

            if (divisionImg.length() != 0){
                Glide.with(context).load(divisionImg).into(imgVisible);
            }else{
                imgVisible.setBackgroundResource(R.drawable.img_eight_visible);
            }

            String result = "";

            for (int j = 0; j < itemArrayList.get(i).getNamesArray().get(familyPosition).size(); j++){
                result += itemArrayList.get(i).getRelationArray().get(familyPosition).get(j) + "   " + itemArrayList.get(i).getNamesArray().get(familyPosition).get(j) + "\n";
            }

            txtRelationship1.setText(result);

            Log.i("tag","familyPosition : " + familyPosition);

            int relationLenth10 = 0;
            int position1 = 0;

            for (int a = 0; a < itemArrayList.get(i).getNamesArray().get(i * 8).size(); a++){
                if (relationLenth10 < itemArrayList.get(i).getRelationArray().get(i * 8).get(a).length()){
                    if (itemArrayList.get(i).getRelationArray().get(i * 8).get(a).contains(" ")){
                        relationLenth10 = itemArrayList.get(i).getRelationArray().get(i * 8).get(a).length()-1;
                    }else{
                        relationLenth10 = itemArrayList.get(i).getRelationArray().get(i * 8).get(a).length();
                    }
                    position1 = a;
                }
            }

            int relationLenth2 = 0;
            int position2 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 1){
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 1).size(); a++){
                    if (relationLenth2 < itemArrayList.get(i).getRelationArray().get((i * 8) + 1).get(a).length()){
                        if (itemArrayList.get(i).getRelationArray().get((i * 8) + 1).get(a).contains(" ")){
                            relationLenth2 = itemArrayList.get(i).getRelationArray().get((i * 8) + 1).get(a).length() - 1;
                        }else{
                            relationLenth2 = itemArrayList.get(i).getRelationArray().get((i * 8) + 1).get(a).length();
                        }
                        position2 = a;
                    }
                }
            }

            int relationLenth3 = 0;
            int position3 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 2){
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 2).size(); a++){
                    if (relationLenth3 < itemArrayList.get(i).getRelationArray().get((i * 8) + 2).get(a).length()){
                        if (itemArrayList.get(i).getRelationArray().get((i * 8) + 2).get(a).contains(" ")){
                            relationLenth3 = itemArrayList.get(i).getRelationArray().get((i * 8) + 2).get(a).length()-1;
                        }else{
                            relationLenth3 = itemArrayList.get(i).getRelationArray().get((i * 8) + 2).get(a).length();
                        }
                        position3 = a;
                    }
                }
            }

            int relationLenth4 = 0;
            int position4 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 3){
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 3).size(); a++){
                    if (relationLenth4 < itemArrayList.get(i).getRelationArray().get((i * 8) + 3).get(a).length()){
                        if (itemArrayList.get(i).getRelationArray().get((i * 8) + 3).get(a).contains(" ")){
                            relationLenth4 = itemArrayList.get(i).getRelationArray().get((i * 8) + 3).get(a).length()-1;
                        }else{
                            relationLenth4 = itemArrayList.get(i).getRelationArray().get((i * 8) + 3).get(a).length();
                        }
                        position4 = a;
                    }
                }
            }

            int relationLenth5 = 0;
            int position5 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 4){
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 4).size(); a++){
                    if (relationLenth5 < itemArrayList.get(i).getRelationArray().get((i * 8) + 4).get(a).length()){
                        if (itemArrayList.get(i).getRelationArray().get((i * 8) + 4).get(a).contains(" ")){
                            relationLenth5 = itemArrayList.get(i).getRelationArray().get((i * 8) + 4).get(a).length()-1;
                        }else{
                            relationLenth5 = itemArrayList.get(i).getRelationArray().get((i * 8) + 4).get(a).length();
                        }
                        position5 = a;
                    }
                }
            }

            int relationLenth6 = 0;
            int position6 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 5){
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 5).size(); a++){
                    if (relationLenth6 < itemArrayList.get(i).getRelationArray().get((i * 8) + 5).get(a).length()){
                        if (itemArrayList.get(i).getRelationArray().get((i * 8) + 5).get(a).contains(" ")){
                            relationLenth6 = itemArrayList.get(i).getRelationArray().get((i * 8) + 5).get(a).length()-1;
                        }else{
                            relationLenth6 = itemArrayList.get(i).getRelationArray().get((i * 8) + 5).get(a).length();
                        }
                        position6 = a;
                    }
                }
            }

            int relationLenth7 = 0;
            int position7 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 6){
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 6).size(); a++){
                    if (relationLenth7 < itemArrayList.get(i).getRelationArray().get((i * 8) + 6).get(a).length()){
                        if (itemArrayList.get(i).getRelationArray().get((i * 8) + 6).get(a).contains(" ")){
                            relationLenth7 = itemArrayList.get(i).getRelationArray().get((i * 8) + 6).get(a).length()-1;
                        }else{
                            relationLenth7 = itemArrayList.get(i).getRelationArray().get((i * 8) + 6).get(a).length();
                        }
                        position7 = a;
                    }
                }
            }

            int relationLenth8 = 0;
            int position8 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 7){
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 7).size(); a++){
                    if (relationLenth8 < itemArrayList.get(i).getRelationArray().get((i * 8) + 7).get(a).length()){
                        if (itemArrayList.get(i).getRelationArray().get((i * 8) + 7).get(a).contains(" ")){
                            relationLenth8 = itemArrayList.get(i).getRelationArray().get((i * 8) + 7).get(a).length()-1;
                        }else{
                            relationLenth8 = itemArrayList.get(i).getRelationArray().get((i * 8) + 7).get(a).length();
                        }
                        position8 = a;
                    }
                }
            }

            final int finalPosition1 = position1;
            final int finalPosition2 = position2;
            final int finalPosition3 = position3;
            final int finalPosition4 = position4;
            final int finalPosition5 = position5;
            final int finalPosition6 = position6;
            final int finalPosition7 = position7;
            final int finalPosition8 = position8;

            for (int a = 0; a < itemArrayList.get(i).getNamesArray().get(i * 8).size(); a++){
                ListMake(a,itemArrayList.get(i).getRelationArray().get(i * 8).get(a),itemArrayList.get(i).getNamesArray().get(i * 8).get(a), 40f,linNamesParent1,familyColor,1);
            }

            if (itemArrayList.get(i).getArrowNoArray().get((i * 8)).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 8)).equals("")){
                imgArrow1.setVisibility(View.GONE);
            }else{
                imgArrow1.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 8))));
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 1){
                txtTitle2.setText(itemArrayList.get(i).getTitleArray().get((i * 8) + 1));
                txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get((i * 8) + 1));
//                txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get((i * 8) + 1));
                txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get((i * 8) + 1));
                txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get((i * 8) + 1));

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 1).contains("\r\n")){
                    txtLocation2.setMaxLines(2);
                }else{
                    txtLocation2.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 1).length() != 0){
                    txtLocation2.setText(itemArrayList.get(i).getLocationArray().get((i * 8) + 1));
                }else{
                    txtLocation2.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 8) + 1).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 8) + 1)).into(imgProfile2);
                }else{
                    imgProfile2.setImageResource(R.drawable.img_eight_division_no_profile);
                }

                if (itemArrayList.get(i).getReligionArray().get((i * 8) + 1).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 8) + 1)).into(imgReligionBg2);
                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 1).size(); a++){
                    Log.i("tag","a : " + a);
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 8) + 1).get(a),itemArrayList.get(i).getNamesArray().get((i * 8) + 1).get(a),40f,linNamesParent2,familyColor,2);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 8) + 1).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 8) + 1).equals("")){
                    imgArrow2.setVisibility(View.GONE);
                }else{
                    imgArrow2.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 8) + 1)));
                }

            }else{
                linVisible2.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 2){
                txtTitle3.setText(itemArrayList.get(i).getTitleArray().get((i * 8) + 2));
                txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get((i * 8) + 2));
//                txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get((i * 8) + 2));
                txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get((i * 8) + 2));
                txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get((i * 8) + 2));

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 2).contains("\r\n")){
                    txtLocation3.setMaxLines(2);
                }else{
                    txtLocation3.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 2).length() != 0){
                    txtLocation3.setText(itemArrayList.get(i).getLocationArray().get((i * 8) + 2));
                }else{
                    txtLocation3.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 8) + 2).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 8) + 2)).into(imgProfile3);
                }else{
                    imgProfile3.setImageResource(R.drawable.img_eight_division_no_profile);
                }

                if (itemArrayList.get(i).getReligionArray().get((i * 8) + 2).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 8) + 2)).into(imgReligionBg3);
                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 2).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 8) + 2).get(a),itemArrayList.get(i).getNamesArray().get((i * 8) + 2).get(a),40f,linNamesParent3,familyColor,3);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 8) + 2).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 8) + 2).equals("")){
                    imgArrow3.setVisibility(View.GONE);
                }else{
                    imgArrow3.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 8) + 2)));
                }
            }else{
                linVisible3.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 3){
                txtTitle4.setText(itemArrayList.get(i).getTitleArray().get((i * 8) + 3));
                txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get((i * 8) + 3));
//                txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get((i * 8) + 3));
                txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get((i * 8) + 3));
                txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get((i * 8) + 3));

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 3).contains("\r\n")){
                    txtLocation4.setMaxLines(2);
                }else{
                    txtLocation4.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 3).length() != 0){
                    txtLocation4.setText(itemArrayList.get(i).getLocationArray().get((i * 8) + 3));
                }else{
                    txtLocation4.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 8) + 3).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 8) + 3)).into(imgProfile4);
                }else{
                    imgProfile4.setImageResource(R.drawable.img_eight_division_no_profile);
                }


                if (itemArrayList.get(i).getReligionArray().get((i * 8) + 3).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 8) + 3)).into(imgReligionBg4);
                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 3).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 8) + 3).get(a),itemArrayList.get(i).getNamesArray().get((i * 8) + 3).get(a),40f,linNamesParent4,familyColor,4);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 8) + 3).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 8) + 3).equals("")){
                    imgArrow4.setVisibility(View.GONE);
                }else{
                    imgArrow4.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 8) + 3)));
                }
            }else{
                linVisible4.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 4){
                txtTitle5.setText(itemArrayList.get(i).getTitleArray().get((i * 8) + 4));
                txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get((i * 8) + 4));
//                txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get((i * 8) + 4));
                txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get((i * 8) + 4));
                txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get((i * 8) + 4));

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 4).contains("\r\n")){
                    txtLocation5.setMaxLines(2);
                }else{
                    txtLocation5.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 4).length() != 0){
                    txtLocation5.setText(itemArrayList.get(i).getLocationArray().get((i * 8) + 4));
                }else{
                    txtLocation5.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 8) + 4).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 8) + 4)).into(imgProfile5);
                }else{
                    imgProfile5.setImageResource(R.drawable.img_eight_division_no_profile);
                }


                if (itemArrayList.get(i).getReligionArray().get((i * 8) + 4).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 8) + 4)).into(imgReligionBg5);
                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 4).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 8) + 4).get(a),itemArrayList.get(i).getNamesArray().get((i * 8) + 4).get(a),40f,linNamesParent5,familyColor,5);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 8) + 4).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 8) + 4).equals("")){
                    imgArrow5.setVisibility(View.GONE);
                }else{
                    imgArrow5.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 8) + 4)));
                }
            }else{
                linVisible5.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 5){
                txtTitle6.setText(itemArrayList.get(i).getTitleArray().get((i * 8) + 5));
                txtDeceasedName6.setText(itemArrayList.get(i).getDeceasedArray().get((i * 8) + 5));
//                txtRelationship6.setText(itemArrayList.get(i).getRelationshipArray().get((i * 8) + 5));
                txtCheckIn6.setText(itemArrayList.get(i).getCheckInArray().get((i * 8) + 5));
                txtCheckOut6.setText(itemArrayList.get(i).getCheckOutArray().get((i * 8) + 5));

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 5).contains("\r\n")){
                    txtLocation6.setMaxLines(2);
                }else{
                    txtLocation6.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 5).length() != 0){
                    txtLocation6.setText(itemArrayList.get(i).getLocationArray().get((i * 8) + 5));
                }else{
                    txtLocation6.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 8) + 5).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 8) + 5)).into(imgProfile6);
                }else{
                    imgProfile6.setImageResource(R.drawable.img_eight_division_no_profile);
                }


                if (itemArrayList.get(i).getReligionArray().get((i * 8) + 5).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 8) + 5)).into(imgReligionBg6);
                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 5).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 8) + 5).get(a),itemArrayList.get(i).getNamesArray().get((i * 8) + 5).get(a),40f,linNamesParent6,familyColor,6);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 8) + 5).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 8) + 5).equals("")){
                    imgArrow6.setVisibility(View.GONE);
                }else{
                    imgArrow6.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 8) + 5)));
                }
            }else{
                linVisible6.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 6){
                txtTitle7.setText(itemArrayList.get(i).getTitleArray().get((i * 8) + 6));
                txtDeceasedName7.setText(itemArrayList.get(i).getDeceasedArray().get((i * 8) + 6));
//                txtRelationship7.setText(itemArrayList.get(i).getRelationshipArray().get((i * 8) + 6));
                txtCheckIn7.setText(itemArrayList.get(i).getCheckInArray().get((i * 8) + 6));
                txtCheckOut7.setText(itemArrayList.get(i).getCheckOutArray().get((i * 8) + 6));

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 6).contains("\r\n")){
                    txtLocation7.setMaxLines(2);
                }else{
                    txtLocation7.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 6).length() != 0){
                    txtLocation7.setText(itemArrayList.get(i).getLocationArray().get((i * 8) + 6));
                }else{
                    txtLocation7.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 8) + 6).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 8) + 6)).into(imgProfile7);
                }else{
                    imgProfile7.setImageResource(R.drawable.img_eight_division_no_profile);
                }


                if (itemArrayList.get(i).getReligionArray().get((i * 8) + 6).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 8) + 6)).into(imgReligionBg7);
                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 6).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 8) + 6).get(a),itemArrayList.get(i).getNamesArray().get((i * 8) + 6).get(a),40f,linNamesParent7,familyColor,7);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 8) + 6).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 8) + 6).equals("")){
                    imgArrow7.setVisibility(View.GONE);
                }else{
                    imgArrow7.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 8) + 6)));
                }
            }else{
                linVisible7.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 7){
                txtTitle8.setText(itemArrayList.get(i).getTitleArray().get((i * 8) + 7));
                txtDeceasedName8.setText(itemArrayList.get(i).getDeceasedArray().get((i * 8) + 7));
//                txtRelationship8.setText(itemArrayList.get(i).getRelationshipArray().get((i * 8) + 7));
                txtCheckIn8.setText(itemArrayList.get(i).getCheckInArray().get((i * 8) + 7));
                txtCheckOut8.setText(itemArrayList.get(i).getCheckOutArray().get((i * 8) + 7));

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 7).contains("\r\n")){
                    txtLocation8.setMaxLines(2);
                }else{
                    txtLocation8.setMaxLines(1);
                }
                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 7).length() != 0){
                    txtLocation8.setText(itemArrayList.get(i).getLocationArray().get((i * 8) + 7));
                }else{
                    txtLocation8.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 8) + 7).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 8) + 7)).into(imgProfile8);
                }else{
                    imgProfile8.setImageResource(R.drawable.img_eight_division_no_profile);
                }

                if (itemArrayList.get(i).getReligionArray().get((i * 8) + 7).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 8) + 7)).into(imgReligionBg8);
                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 7).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 8) + 7).get(a),itemArrayList.get(i).getNamesArray().get((i * 8) + 7).get(a),40f,linNamesParent8,familyColor,8);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 8) + 7).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 8) + 7).equals("")){
                    imgArrow8.setVisibility(View.GONE);
                }else{
                    imgArrow8.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 8) + 7)));
                }
            }else{
                linVisible8.setVisibility(View.GONE);
                imgVisible.setVisibility(View.VISIBLE);
            }

            txtRelationship1.setVisibility(View.GONE);
            linNamesParent1.setVisibility(View.VISIBLE);

            txtEtc1.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc2.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc3.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtLocation1.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtCheckOut1.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc4.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc5.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc6.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtLocation2.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtCheckOut2.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc7.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc8.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc9.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtLocation3.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtCheckOut3.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc10.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc11.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc12.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtLocation4.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtCheckOut4.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc13.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc14.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc15.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtLocation5.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtCheckOut5.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc16.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc17.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc18.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtLocation6.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtCheckOut6.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc19.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc20.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc21.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtLocation7.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtCheckOut7.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc22.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc23.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtEtc24.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtLocation8.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());
            txtCheckOut8.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtCheckIn1.getTextSize());

            linNamesParent1.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if (linNamesParent1.getChildCount() != 0){
                        int child1 = 0;

                        for (int j = 0; j < linNamesParent1.getChildCount(); j++){
                            child1 = child1 + linNamesParent1.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent1.getHeight() - 20)) {

                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray1.size(); i++) {
                                    txtTestArray1.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(0).getTextSize() - 1f);
                                }
                                txtTestArray1.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray1.size(); j++) {
                                    if (finalPosition1 < txtRelationArray1.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition1) {
                                        txtRelationArray1.get(j).setLayoutParams(params11);
                                        txtRelationArray1.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray1.size(); j++) {
                                if (finalPosition1 < txtRelationArray1.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray1.get(j).setLayoutParams(params11);
                                    txtRelationArray1.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            linNamesParent2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if (linNamesParent2.getChildCount() != 0){
                        int child1 = 0;

                        Log.i("tag","parent2 cnt : " + linNamesParent2.getChildCount());

                        for (int j = 0; j < linNamesParent2.getChildCount(); j++){
                            child1 = child1 + linNamesParent2.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent2.getHeight() - 20)) {
                            if (child1 != 0) {
                                for (int i = 0; i < txtTestArray2.size(); i++) {
                                    txtTestArray2.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(0).getTextSize() - 1f);
                                }
                                txtTestArray2.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray2.size(); j++) {
                                    if (finalPosition2 < txtRelationArray2.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition2) {
                                        txtRelationArray2.get(j).setLayoutParams(params11);
                                        txtRelationArray2.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray2.size(); j++) {
                                if (finalPosition2 < txtRelationArray2.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition2) {
                                    txtRelationArray2.get(j).setLayoutParams(params11);
                                    txtRelationArray2.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }

                }
            });

            linNamesParent3.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (linNamesParent3.getChildCount() != 0){
                        int child1 = 0;

                        for (int j = 0; j < linNamesParent3.getChildCount(); j++){
                            child1 = child1 + linNamesParent3.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent3.getHeight() - 20)) {

                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray3.size(); i++) {
                                    txtTestArray3.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray3.get(0).getTextSize() - 1f);
                                }
                                txtTestArray3.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray3.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray3.size(); j++) {
                                    if (finalPosition3 < txtRelationArray3.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray3.get(finalPosition3).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition3) {
                                        txtRelationArray3.get(j).setLayoutParams(params11);
                                        txtRelationArray3.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray3.size(); j++) {
                                if (finalPosition3 < txtRelationArray3.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray3.get(finalPosition3).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition3) {
                                    txtRelationArray3.get(j).setLayoutParams(params11);
                                    txtRelationArray3.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent3.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            linNamesParent4.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if (linNamesParent4.getChildCount() != 0){
                        int child1 = 0;

                        for (int j = 0; j < linNamesParent4.getChildCount(); j++){
                            child1 = child1 + linNamesParent4.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent4.getHeight() - 20)) {
                            if (child1 != 0) {
                                for (int i = 0; i < txtTestArray4.size(); i++) {
                                    txtTestArray4.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray4.get(0).getTextSize() - 1f);
                                }
                                txtTestArray4.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray4.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray4.size(); j++) {
                                    if (finalPosition4 < txtRelationArray4.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray4.get(finalPosition4).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition4) {
                                        txtRelationArray4.get(j).setLayoutParams(params11);
                                        txtRelationArray4.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray4.size(); j++) {
                                if (finalPosition4 < txtRelationArray4.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray4.get(finalPosition4).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition4) {
                                    txtRelationArray4.get(j).setLayoutParams(params11);
                                    txtRelationArray4.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent4.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent4.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            linNamesParent5.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (linNamesParent5.getChildCount() != 0){
                        int child1 = 0;

                        for (int j = 0; j < linNamesParent5.getChildCount(); j++){
                            child1 = child1 + linNamesParent5.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent5.getHeight() - 20)) {

                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray5.size(); i++) {
                                    txtTestArray5.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray5.get(0).getTextSize() - 1f);
                                }
                                txtTestArray5.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray5.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray5.size(); j++) {
                                    if (finalPosition5 < txtRelationArray5.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray5.get(finalPosition5).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition5) {
                                        txtRelationArray5.get(j).setLayoutParams(params11);
                                        txtRelationArray5.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray5.size(); j++) {
                                if (finalPosition5 < txtRelationArray5.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray5.get(finalPosition5).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition5) {
                                    txtRelationArray5.get(j).setLayoutParams(params11);
                                    txtRelationArray5.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent5.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent5.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            linNamesParent6.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if (linNamesParent6.getChildCount() != 0){
                        int child1 = 0;

                        for (int j = 0; j < linNamesParent6.getChildCount(); j++){
                            child1 = child1 + linNamesParent6.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent6.getHeight() - 20)) {
                            if (child1 != 0) {
                                for (int i = 0; i < txtTestArray6.size(); i++) {
                                    txtTestArray6.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray6.get(0).getTextSize() - 1f);
                                }
                                txtTestArray6.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray6.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray6.size(); j++) {
                                    if (finalPosition6 < txtRelationArray6.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray6.get(finalPosition6).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition6) {
                                        txtRelationArray6.get(j).setLayoutParams(params11);
                                        txtRelationArray6.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray6.size(); j++) {
                                if (finalPosition6 < txtRelationArray6.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray6.get(finalPosition6).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition6) {
                                    txtRelationArray6.get(j).setLayoutParams(params11);
                                    txtRelationArray6.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent6.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent6.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            linNamesParent7.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    if (linNamesParent7.getChildCount() != 0){
                        int child1 = 0;

                        for (int j = 0; j < linNamesParent7.getChildCount(); j++){
                            child1 = child1 + linNamesParent7.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent7.getHeight() - 20)) {

                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray7.size(); i++) {
                                    txtTestArray7.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray7.get(0).getTextSize() - 1f);
                                }
                                txtTestArray7.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray7.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray7.size(); j++) {
                                    if (finalPosition7 < txtRelationArray7.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray7.get(finalPosition7).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition7) {
                                        txtRelationArray7.get(j).setLayoutParams(params11);
                                        txtRelationArray7.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray7.size(); j++) {
                                if (finalPosition7 < txtRelationArray7.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray7.get(finalPosition7).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition7) {
                                    txtRelationArray7.get(j).setLayoutParams(params11);
                                    txtRelationArray7.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent7.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent7.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            linNamesParent8.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {

                    if (linNamesParent8.getChildCount() != 0){
                        int child1 = 0;

                        for (int j = 0; j < linNamesParent8.getChildCount(); j++){
                            child1 = child1 + linNamesParent8.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (linNamesParent8.getHeight() - 20)) {
                            if (child1 != 0) {
                                for (int i = 0; i < txtTestArray8.size(); i++) {
                                    txtTestArray8.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray8.get(0).getTextSize() - 1f);
                                }
                                txtTestArray8.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray8.get(1).getTextSize());

                                for (int j = 0; j < txtRelationArray8.size(); j++) {
                                    if (finalPosition8 < txtRelationArray8.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray8.get(finalPosition8).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition8) {
                                        txtRelationArray8.get(j).setLayoutParams(params11);
                                        txtRelationArray8.get(j).invalidate();
//                                        }
                                    }
                                }
                            }
                        }else {
                            for (int j = 0; j < txtRelationArray8.size(); j++) {
                                if (finalPosition8 < txtRelationArray8.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray8.get(finalPosition8).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition8) {
                                    txtRelationArray8.get(j).setLayoutParams(params11);
                                    txtRelationArray8.get(j).invalidate();
//                                    }
                                }
                            }
                            linNamesParent8.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    }else{
                        linNamesParent8.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            });

            if (arrowFlag != 1){
                imgArrow1.setVisibility(View.GONE);
                imgArrow2.setVisibility(View.GONE);
                imgArrow3.setVisibility(View.GONE);
                imgArrow4.setVisibility(View.GONE);
                imgArrow5.setVisibility(View.GONE);
                imgArrow6.setVisibility(View.GONE);
                imgArrow7.setVisibility(View.GONE);
                imgArrow8.setVisibility(View.GONE);
            }
        }

        ((ViewPager)container).addView(view);

        return view;
    }

    public void addItem(ArrayList<String> titleArray ,
                        ArrayList<String> deceasedArray ,
                        ArrayList<String> relationshipArray ,
                        ArrayList<String> checkInArray ,
                        ArrayList<String> checkOutArray ,
                        ArrayList<String> locationArray ,
                        ArrayList<String> imgPathArray,
                        ArrayList<ArrayList<String>> namesArray,
                        ArrayList<ArrayList<String>> relationArray,
                        ArrayList<String> arrowNoArray,
                        ArrayList<String> religionArray){

        InternetGeneralViewPagerItem generalViewPagerItem = new InternetGeneralViewPagerItem();

        generalViewPagerItem.setTitleArray(titleArray);
        generalViewPagerItem.setDeceasedArray(deceasedArray);
        generalViewPagerItem.setCheckInArray(checkInArray);
        generalViewPagerItem.setCheckOutArray(checkOutArray);
        generalViewPagerItem.setRelationshipArray(relationshipArray);
        generalViewPagerItem.setLocationArray(locationArray);
        generalViewPagerItem.setImgPathArray(imgPathArray);
        generalViewPagerItem.setNamesArray(namesArray);
        generalViewPagerItem.setRelationArray(relationArray);
        generalViewPagerItem.setArrowNoArray(arrowNoArray);
        generalViewPagerItem.setReligionArray(religionArray);

        itemArrayList.add(generalViewPagerItem);

    }

    void ListMake(int a,String relation, String name, float textSize,LinearLayout layout, String textColor, int position){

        if (division == 1){
            if (a != 0){
                TextView linear = new TextView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,10);
                linear.setLayoutParams(params);

                layout.addView(linear);
            }
        }else if (division == 2){
            if (a != 0){
                TextView linear = new TextView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,10);
                linear.setLayoutParams(params);

                layout.addView(linear);
            }
        }else if (division == 4){
            if (a != 0){
                TextView linear = new TextView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,8);
                linear.setLayoutParams(params);

                layout.addView(linear);
            }
        }else if (division == 6){
            if (a != 0){
                TextView linear = new TextView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,4);
                linear.setLayoutParams(params);

                layout.addView(linear);
            }
        }else if (division == 8){
            if (a != 0){
                TextView linear = new TextView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,4);
                linear.setLayoutParams(params);

                layout.addView(linear);
            }
        }

        View listView = new View(context);
        listView = inflater.inflate(R.layout.view_family_list_item,null);
        TextView txtRelation = listView.findViewById(R.id.txt_family_relation_sort_item_relation);
        FlowLayout flowLayout = listView.findViewById(R.id.flow_family_relation_sort_item_name_parent);
        TextView txtComma = listView.findViewById(R.id.txt_family_relation_sort_item_comma);

        txtRelation.setGravity(Gravity.CENTER_HORIZONTAL);

        Log.i("tag","position : " + position);

        if (position == 1){
            txtRelationArray1.add(txtRelation);
            txtTestArray1.add(txtRelation);
            txtTestArray1.add(txtComma);
        }else if (position == 2){
            txtRelationArray2.add(txtRelation);
            txtTestArray2.add(txtRelation);
            txtTestArray2.add(txtComma);
        }else if (position == 3){
            txtRelationArray3.add(txtRelation);
            txtTestArray3.add(txtRelation);
            txtTestArray3.add(txtComma);
        }else if (position == 4){
            txtRelationArray4.add(txtRelation);
            txtTestArray4.add(txtRelation);
            txtTestArray4.add(txtComma);
        }else if (position == 5){
            txtRelationArray5.add(txtRelation);
            txtTestArray5.add(txtRelation);
            txtTestArray5.add(txtComma);
        }else if (position == 6){
            txtRelationArray6.add(txtRelation);
            txtTestArray6.add(txtRelation);
            txtTestArray6.add(txtComma);
        }else if (position == 7){
            txtRelationArray7.add(txtRelation);
            txtTestArray7.add(txtRelation);
            txtTestArray7.add(txtComma);
        }else if (position == 8){
            txtRelationArray8.add(txtRelation);
            txtTestArray8.add(txtRelation);
            txtTestArray8.add(txtComma);
        }
        txtRelationNamesArray.add(txtRelation);
        txtRelationNamesArray.add(txtComma);

        txtRelation.setTypeface(typeface);
        txtComma.setTypeface(typeface);
        txtRelation.setTextColor(Color.parseColor(relationColor));
        txtComma.setTextColor(Color.parseColor(relationColor));

        String[] strings = name.split(",");

        for (int i = 0; i < strings.length; i++){
            TextView txtName = new TextView(context);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            txtName.setLayoutParams(layoutParams);


            txtName.setIncludeFontPadding(false);
            txtName.setTypeface(typeface);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                txtName.setAutoSizeTextTypeUniformWithConfiguration((int)textSize,100,2,TextView.AUTO_SIZE_TEXT_TYPE_UNIFORM);
//            }
            txtName.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
            txtName.setTextColor(Color.parseColor(relationColor));

            txtRelationNamesArray.add(txtName);
            if (position == 1){
                txtTestArray1.add(txtName);
            }else if (position == 2){
                txtTestArray2.add(txtName);
            }else if (position == 3){
                txtTestArray3.add(txtName);
            }else if (position == 4){
                txtTestArray4.add(txtName);
            }else if (position == 5){
                txtTestArray5.add(txtName);
            }else if (position == 6){
                txtTestArray6.add(txtName);
            }else if (position == 7){
                txtTestArray7.add(txtName);
            }else if (position == 8){
                txtTestArray8.add(txtName);
            }

            flowLayout.addView(txtName);

            if (i != (strings.length-1)) {
                txtName.setText(strings[i] + ", ");
            }else{
                txtName.setText(strings[i]);
            }

            if (position == 1){
                txtNamesArray1.add(txtName);
            }else if (position == 2){
                txtNamesArray2.add(txtName);
            }else if (position == 3){
                txtNamesArray3.add(txtName);
            }else if (position == 4){
                txtNamesArray4.add(txtName);
            }else if (position == 5){
                txtNamesArray5.add(txtName);
            }else if (position == 6){
                txtNamesArray6.add(txtName);
            }else if (position == 7){
                txtNamesArray7.add(txtName);
            }else if (position == 8){
                txtNamesArray8.add(txtName);
            }
        }

        if (position == 1){
            txtNamesArray1.add(txtComma);
        }else if (position == 2){
            txtNamesArray2.add(txtComma);
        }else if (position == 3){
            txtNamesArray3.add(txtComma);
        }else if (position == 4){
            txtNamesArray4.add(txtComma);
        }else if (position == 5){
            txtNamesArray5.add(txtComma);
        }else if (position == 6){
            txtNamesArray6.add(txtComma);
        }else if (position == 7){
            txtNamesArray7.add(txtComma);
        }else if (position == 8){
            txtNamesArray8.add(txtComma);
        }

        txtRelation.setText(relation);

        txtRelation.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        txtComma.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);

        layout.addView(listView);

    }

    public int arrowNoSelect(String arrowNo){
        int a = 0;
        if (arrowNo.equals("0")){
            a = 0;
        }else if (arrowNo.equals("1")){
            a = R.drawable.arrow1;
        }else if (arrowNo.equals("2")){
            a = R.drawable.arrow2;
        }else if (arrowNo.equals("3")){
            a =  R.drawable.arrow3;
        }else if (arrowNo.equals("4")){
            a =  R.drawable.arrow4;
        }else if (arrowNo.equals("5")){
            a =  R.drawable.arrow5;
        }else if (arrowNo.equals("6")){
            a =  R.drawable.arrow6;
        }else if (arrowNo.equals("7")){
            a =  R.drawable.arrow7;
        }else if (arrowNo.equals("8")){
            a =  R.drawable.arrow8;
        }else if (arrowNo.equals("9")){
            a =  R.drawable.arrow9;
        }else if (arrowNo.equals("10")){
            a =  R.drawable.arrow10;
        }else if (arrowNo.equals("11")){
            a =  R.drawable.arrow11;
        }else if (arrowNo.equals("12")){
            a =  R.drawable.arrow12;
        }
        return a;
    }

    ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
        @Override
        public void onGlobalLayout() {
            txtEtc1.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());
            txtEtc2.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());
            txtEtc3.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());
            txtLocation1.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());

            txtEtc4.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());
            txtEtc5.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());
            txtEtc6.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());
            txtLocation2.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());

            txtEtc7.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());
            txtEtc8.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());
            txtEtc9.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());
            txtLocation3.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());

            txtEtc10.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());
            txtEtc11.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());
            txtEtc12.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());
            txtLocation4.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());

            txtCheckOut1.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());
            txtCheckOut2.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());
            txtCheckOut3.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());
            txtCheckOut4.setTextSize(TypedValue.COMPLEX_UNIT_PX,txtTitle1.getTextSize());

            if (linNamesParent1.getChildCount() != 0){
                int child1 = 0;

                for (int j = 0; j < linNamesParent1.getChildCount(); j++){
                    child1 = child1 + linNamesParent1.getChildAt(j).getMeasuredHeight();
                }

                if (child1 >= (linNamesParent1.getHeight() - 20)) {

                    if (child1 != 0) {

                        for (int i = 0; i < txtTestArray1.size(); i++) {
                            txtTestArray1.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(0).getTextSize() - 1f);
                        }
                        txtTestArray1.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(1).getTextSize());

                        for (int j = 0; j < txtRelationArray1.size(); j++) {
                            if (finalPosition1 < txtRelationArray1.size()) {
                                final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                        if (j != finalPosition1) {
                                txtRelationArray1.get(j).setLayoutParams(params11);
                                txtRelationArray1.get(j).invalidate();
//                                        }
                            }
                        }
                    }
                }else {
                    for (int j = 0; j < txtRelationArray1.size(); j++) {
                        if (finalPosition1 < txtRelationArray1.size()) {
                            final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                            txtRelationArray1.get(j).setLayoutParams(params11);
                            txtRelationArray1.get(j).invalidate();
//                                    }
                        }
                    }
                    linNamesParent1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }else{
                linNamesParent1.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        }
    };
}
