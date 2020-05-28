package com.kmw.dongsung.Func;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Paint;
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
import com.kmw.dongsung.Views.PaintTextView;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class TestGeneralViewPagerAdapter extends PagerAdapter {

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

    public  ArrayList<TextView> txtRelationArray1 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtRelationArray2 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtRelationArray3 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtRelationArray4 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtRelationArray5 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtRelationArray6 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtRelationArray7 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtRelationArray8 = new ArrayList<TextView>();

    public  ArrayList<TextView> txtNamesArray1 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtNamesArray2 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtNamesArray3 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtNamesArray4 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtNamesArray5 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtNamesArray6 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtNamesArray7 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtNamesArray8 = new ArrayList<TextView>();

    public  ArrayList<TextView> txtTestArray1 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtTestArray2 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtTestArray3 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtTestArray4 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtTestArray5 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtTestArray6 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtTestArray7 = new ArrayList<TextView>();
    public static ArrayList<TextView> txtTestArray8 = new ArrayList<TextView>();

    public  ArrayList<TextView> txtRelationNamesArray = new ArrayList<TextView>();
    public static ArrayList<View> viewArrayList = new ArrayList<View>();

    LinearLayout linearLayoutTest;

    public  TextView txtTitle1;
    public  TextView txtDeceasedName1;
    public  TextView txtRelationship1;
    LinearLayout linNamesParent1;
    GridLayout gridLayout;
    public  TextView txtCheckIn1;
    public  TextView txtCheckOut1;
    public  TextView txtLocation1;
    public  ImageView imgProfile1;
    public  ImageView imgArrow1;
    public  ImageView imgReligionBg1;

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

    public TestGeneralViewPagerAdapter(LayoutInflater inflater,Context context, int division, int arrowFlag, int famailySize,
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
    }

    @Override
    public int getCount() {
        Log.i(TAG,"pager size : " + (int) Math.ceil(itemArrayList.size()/(double)division));
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

       if (division == 4) {
           ViewHolder viewHolder = null;
           if (view == null){
               viewHolder = new ViewHolder();
               view = inflater.inflate(R.layout.test_general_view_pager_list_item, null);
               LinearLayout linVisible2 = (LinearLayout) view.findViewById(R.id.linear_four_list_item_visible2);
               LinearLayout linVisible3 = (LinearLayout) view.findViewById(R.id.linear_four_list_item_visible3);
               LinearLayout linVisible4 = (LinearLayout) view.findViewById(R.id.linear_four_list_item_visible4);

               viewHolder.txtTitle2                       = (TextView) view.findViewById(R.id.txt_four_division_item_title2);
               viewHolder.txtDeceasedName2                 = (TextView) view.findViewById(R.id.txt_four_division_item_deceased_name2);
               viewHolder.linNamesParent2     = (LinearLayout) view.findViewById(R.id.linear_four_division_item_name_parent2);
               viewHolder.txtCheckIn2                  =       (TextView) view.findViewById(R.id.txt_four_division_item_check_in2);
               viewHolder.txtCheckOut2                    =      (TextView) view.findViewById(R.id.txt_four_division_item_check_out2);
               viewHolder.txtLocation2                    =      (TextView) view.findViewById(R.id.txt_four_division_item_location2);
               viewHolder.imgProfile2                     =       (ImageView) view.findViewById(R.id.img_four_division_item_profile2);
               viewHolder.imgArrow2                   =         (ImageView) view.findViewById(R.id.img_four_division_item_arrow2);
               viewHolder.imgReligionBg2               =   (ImageView)view.findViewById(R.id.img_four_division_item_religion_bg2);

               view.setTag(viewHolder);
           }else{
               viewHolder = (ViewHolder)view.getTag();
           }


            txtTitle1 = (TextView) view.findViewById(R.id.txt_four_division_item_title1);
            txtDeceasedName1 = (TextView) view.findViewById(R.id.txt_four_division_item_deceased_name1);
            txtCheckIn1 = (TextView) view.findViewById(R.id.txt_four_division_item_check_in1);
            txtCheckOut1 = (TextView) view.findViewById(R.id.txt_four_division_item_check_out1);
            txtLocation1 = (TextView) view.findViewById(R.id.txt_four_division_item_location1);
            imgProfile1 = (ImageView) view.findViewById(R.id.img_four_division_item_profile1);
            imgArrow1 = (ImageView) view.findViewById(R.id.img_four_division_item_arrow1);
           PaintTextView paintTextView = (PaintTextView) view.findViewById(R.id.text_test_test);
            linNamesParent1 = (LinearLayout) view.findViewById(R.id.linear_four_division_item_name_parent1);
            imgReligionBg1  = (ImageView)view.findViewById(R.id.img_four_division_item_religion_bg1);
            if (familyPosition == 0) {
                linearLayoutTest = linNamesParent1;
            }


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

           viewHolder.txtTitle2.setTextColor(Color.parseColor(roomNameColor));
           viewHolder.txtDeceasedName2.setTextColor(Color.parseColor(deceasedColor));
            txtEtc4.setTextColor(Color.parseColor(checkInTitleColor));
            txtEtc5.setTextColor(Color.parseColor(checkOutTitleColor));
            txtEtc6.setTextColor(Color.parseColor(locationTitleColor));
           viewHolder.txtCheckIn2.setTextColor(Color.parseColor(checkInColor));
           viewHolder.txtCheckOut2.setTextColor(Color.parseColor(checkOutColor));
           viewHolder.txtLocation2.setTextColor(Color.parseColor(locationColor));

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

            if (divisionImg.length() != 0) {
                Glide.with(context).load(divisionImg).into(imgVisible);
            } else {
                imgVisible.setBackgroundResource(R.drawable.img_four_visible);
            }

            for (int j = 0; j < itemArrayList.get(i).getNamesArray().get(familyPosition).size(); j++) {
                result += itemArrayList.get(i).getRelationArray().get(familyPosition).get(j) + "   " + itemArrayList.get(i).getNamesArray().get(familyPosition).get(j) + "\n";
            }


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

            float relationLenth2 = 0;
            int position2 = 0;

           Paint paint = new Paint();

            if (itemArrayList.get(i).getTitleArray().size() > (i * 4) + 2) {
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 4) + 2).size(); a++) {
                    if (relationLenth2 < paint.measureText(itemArrayList.get(i).getRelationArray().get((i * 4) + 2).get(a))) {
                        relationLenth2 = paint.measureText(itemArrayList.get(i).getRelationArray().get((i * 4) + 2).get(a));
                        position2 = a;
                    }
                }
            }

            float relationLenth3 = 0;
            int position3 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 4) + 1) {
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 4) + 1).size(); a++) {
                    if (relationLenth3 < paint.measureText(itemArrayList.get(i).getRelationArray().get((i * 4) + 1).get(a))) {
                        relationLenth3 = paint.measureText(itemArrayList.get(i).getRelationArray().get((i * 4) + 1).get(a));
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



            if (itemArrayList.get(i).getArrowNoArray().get((i * 4)).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 4)).equals("")) {
                imgArrow1.setVisibility(View.GONE);
            } else {
                imgArrow1.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 4))));
            }

           ArrayList<TextView> txtRelationArray2 = new ArrayList<>();
           ArrayList<TextView> txtTestArray2 = new ArrayList<>();
           ArrayList<TextView> txtNamesArray2 = new ArrayList<>();
           ArrayList<TextView> txtRelationNamesArray = new ArrayList<>();

            if (itemArrayList.get(i).getTitleArray().size() > (i * 4) + 1) {
                viewHolder.txtTitle2.setText(itemArrayList.get(i).getTitleArray().get((i * 4) + 1));
                viewHolder.txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get((i * 4) + 1));
//                txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get((i * 4) + 1));
                viewHolder.txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get((i * 4) + 1));
                viewHolder.txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get((i * 4) + 1));

                if (itemArrayList.get(i).getLocationArray().get((i * 4) + 1).contains("\r\n")) {
                    viewHolder.txtLocation2.setMaxLines(2);
                } else {
                    viewHolder.txtLocation2.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 4) + 1).length() != 0) {
                    viewHolder.txtLocation2.setText(itemArrayList.get(i).getLocationArray().get((i * 4) + 1));
                } else {
                    viewHolder.txtLocation2.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 4) + 1).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 4) + 1)).into(viewHolder.imgProfile2);
                }else{
                    viewHolder.imgProfile2.setImageResource(R.drawable.img_four_division_no_profile);
                }

                if (itemArrayList.get(i).getReligionArray().get((i * 4) + 1).length() > 0){
                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 4) + 1)).into(viewHolder.imgReligionBg2);
                }





                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 4) + 1).size(); a++) {

//                    ListMake(a, itemArrayList.get(i).getRelationArray().get((i * 4) + 1).get(a), itemArrayList.get(i).getNamesArray().get((i * 4) + 1).get(a), 60f, linNamesParent2, familyColor, 2);

                    if (division == 1){
                        if (a != 0){
                            TextView linear = new TextView(context);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,10);
                            linear.setLayoutParams(params);

                            linNamesParent2.addView(linear);
                        }
                    }else if (division == 2){
                        if (a != 0){
                            TextView linear = new TextView(context);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,10);
                            linear.setLayoutParams(params);

                            viewHolder.linNamesParent2.addView(linear);
                        }
                    }else if (division == 4){
                        if (a != 0){
                            TextView linear = new TextView(context);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,8);
                            linear.setLayoutParams(params);

                            viewHolder.linNamesParent2.addView(linear);
                        }
                    }else if (division == 6){
                        if (a != 0){
                            TextView linear = new TextView(context);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,4);
                            linear.setLayoutParams(params);

                            viewHolder.linNamesParent2.addView(linear);
                        }
                    }else if (division == 8){
                        if (a != 0){
                            TextView linear = new TextView(context);
                            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,4);
                            linear.setLayoutParams(params);

                            viewHolder.linNamesParent2.addView(linear);
                        }
                    }

                    View listView = new View(context);
                    listView = inflater.inflate(R.layout.view_family_list_item,null);
                    TextView txtRelation = listView.findViewById(R.id.txt_family_relation_sort_item_relation);
                    FlowLayout flowLayout = listView.findViewById(R.id.flow_family_relation_sort_item_name_parent);
                    TextView txtComma = listView.findViewById(R.id.txt_family_relation_sort_item_comma);

                    txtRelation.setGravity(Gravity.CENTER_HORIZONTAL);

                    txtRelationArray2.add(txtRelation);
                    txtTestArray2.add(txtRelation);
                    txtTestArray2.add(txtComma);

                    txtRelationNamesArray.add(txtRelation);
                    txtRelationNamesArray.add(txtComma);

                    txtRelation.setTypeface(typeface);
                    txtComma.setTypeface(typeface);
                    txtRelation.setTextColor(Color.parseColor(relationColor));
                    txtComma.setTextColor(Color.parseColor(relationColor));

                    String[] strings = itemArrayList.get(i).getNamesArray().get((i * 4) + 1).get(a).split(",");

                    for (int x = 0; x < strings.length; x++){
                        TextView txtName = new TextView(context);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                        txtName.setLayoutParams(layoutParams);


                        txtName.setIncludeFontPadding(false);
                        txtName.setTypeface(typeface);

                        if (itemArrayList.get(i).getFamilyTextSizeArray().get((i * 4) + 1) == 0f){
                            txtName.setTextSize(TypedValue.COMPLEX_UNIT_PX,60f);
                        }else{
                            txtName.setTextSize(TypedValue.COMPLEX_UNIT_PX,itemArrayList.get(i).getFamilyTextSizeArray().get((i * 4) + 1));
                        }

                        txtName.setTextColor(Color.parseColor(relationColor));

                        txtRelationNamesArray.add(txtName);
                        txtTestArray2.add(txtName);
                        flowLayout.addView(txtName);

                        if (x != (strings.length-1)) {
                            txtName.setText(strings[x] + ", ");
                        }else{
                            txtName.setText(strings[x]);
                        }

                        txtNamesArray2.add(txtName);

                    }

                    txtNamesArray2.add(txtComma);

                    txtRelation.setText(itemArrayList.get(i).getRelationArray().get((i * 4) + 1).get(a));

                    if (itemArrayList.get(i).getFamilyTextSizeArray().get((i * 4) + 1) == 0f){
                        txtRelation.setTextSize(TypedValue.COMPLEX_UNIT_PX,60);
                        txtComma.setTextSize(TypedValue.COMPLEX_UNIT_PX,60);
                    }else{
                        txtRelation.setTextSize(TypedValue.COMPLEX_UNIT_PX,itemArrayList.get(i).getFamilyTextSizeArray().get((i * 4) + 1));
                        txtComma.setTextSize(TypedValue.COMPLEX_UNIT_PX,itemArrayList.get(i).getFamilyTextSizeArray().get((i * 4) + 1));
                    }

                    viewHolder.linNamesParent2.addView(listView);
                }



                if (itemArrayList.get(i).getArrowNoArray().get((i * 4) + 1).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 4) + 1).equals("")) {
                    viewHolder.imgArrow2.setVisibility(View.GONE);
                } else {
                    viewHolder.imgArrow2.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 4) + 1)));
                }

            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 4) + 2) {

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
            }

            if (arrowFlag != 1){
                imgArrow1.setVisibility(View.GONE);
                viewHolder.imgArrow2.setVisibility(View.GONE);
                imgArrow3.setVisibility(View.GONE);
                imgArrow4.setVisibility(View.GONE);
            }

//           for (int a = 0; a < itemArrayList.get(i).getNamesArray().get(i * 4).size(); a++) {
//               ListMake(a, itemArrayList.get(i).getRelationArray().get(i * 4).get(a), itemArrayList.get(i).getNamesArray().get(i * 4).get(a), 60f, linTestTest, familyColor, 1);
//           }

//           String text = "";
//
//           AutoResizeTextView fitTextView = new AutoResizeTextView(context);
//
//           for (int x = 0; x < itemArrayList.get(i).getNamesArray().get(i * 4).size(); x++){
//               text = text + itemArrayList.get(i).getNamesArray().get(i * 4).get(x) + "\n";
//           }
//
//           fitTextView.setTextSize(80f);
//           fitTextView.setText(text);
//
//           LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
//
//           params.weight = 1;
//
//           fitTextView.setLayoutParams(params);
//
//           linTestTest.addView(fitTextView);

           String text = "";

           for (int x = 0; x < itemArrayList.get(i).getNamesArray().get(i * 4).size(); x++){

               text = text + itemArrayList.get(i).getRelationArray().get(i * 4).get(x) + "  :  " + itemArrayList.get(i).getNamesArray().get(i * 4).get(x) + "\n";

           }



           paintTextView.setText(text);

//           linTestTest.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//               @Override
//               public void onGlobalLayout() {
//                   ArrayList<Float> floatArrayList = new ArrayList<>();
//                   for (int i = 0; i < linTestTest.getChildCount(); i++){
//                       Log.i(TAG,"size : " + ((TextView)linTestTest.getChildAt(i)).getTextSize());
//                       floatArrayList.add(((TextView)linTestTest.getChildAt(i)).getTextSize());
//                   }
//
//                   float max = Collections.max(floatArrayList);
//
//                   LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
//
//                   params.weight = 1.9f;
//
//                   for (int i = 0; i < linTestTest.getChildCount(); i++){
//
//                       if (((TextView)linTestTest.getChildAt(i)).getTextSize() < max){
//                           ((TextView)linTestTest.getChildAt(i)).setLayoutParams(params);
//
//                           ((TextView)linTestTest.getChildAt(i)).setTextSize(TypedValue.COMPLEX_UNIT_PX,max);
//                       }
//
//                   }
//
//                   linTestTest.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//               }
//           });

           ViewHolder finalViewHolder = viewHolder;

           final int[] aaaa = {0};

           viewHolder.linNamesParent2.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
               @Override
               public boolean onPreDraw() {

                   if (finalViewHolder.linNamesParent2.getChildCount() != 0) {

                       int child1 = 0;

                       Log.i("tag", "parent2 cnt : " + finalViewHolder.linNamesParent2.getChildCount());

                       for (int j = 0; j < finalViewHolder.linNamesParent2.getChildCount(); j++) {
                           child1 = child1 + finalViewHolder.linNamesParent2.getChildAt(j).getMeasuredHeight();
                       }


                       if (child1 >= (finalViewHolder.linNamesParent2.getHeight() - 20)) {
                           if (child1 != 0) {
                               for (int i = 0; i < txtTestArray2.size(); i++) {
                                   txtTestArray2.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(0).getTextSize() - 2f);
                               }
                               txtTestArray2.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(1).getTextSize());

                               itemArrayList.get(i).getFamilyTextSizeArray().set((i * 4) + 1, txtTestArray2.get(1).getTextSize());

                               Log.i("PAGER", "TEXT INSERT");
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
                       } else {
                           for (int j = 0; j < txtRelationArray2.size(); j++) {
                               if (finalPosition2 < txtRelationArray2.size()) {
                                   final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition2) {
                                   txtRelationArray2.get(j).setLayoutParams(params11);
                                   txtRelationArray2.get(j).invalidate();
//                                    }
                               }
                           }

                           finalViewHolder.linNamesParent2.getViewTreeObserver().removeOnPreDrawListener(this);

                       }
                   }


                   Log.i(TAG,"들어옴 preDraw");

                   return true;
               }
           });

//           if (itemArrayList.get(i).getTitleArray().size() > (i * 4) + 1) {
//               if (itemArrayList.get(i).getFamilyTextSizeArray().get((i * 4) + 1) == 0f) {
//                   Log.i("PAGER", "family Text Sizr : " + itemArrayList.get(i).getFamilyTextSizeArray().get((i * 4) + 1));
//                   viewHolder.linNamesParent2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//                       @Override
//                       public void onGlobalLayout() {
//
//                           Log.i("PAGER", "aaaaa : " + aaaa[0]++);
//
//                           if (aaaa[0] > 30) {
//                               finalViewHolder.linNamesParent2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                           }
//
//                           if (finalViewHolder.linNamesParent2.getChildCount() != 0) {
//                               int child1 = 0;
//
//                               Log.i("tag", "parent2 cnt : " + finalViewHolder.linNamesParent2.getChildCount());
//
//                               for (int j = 0; j < finalViewHolder.linNamesParent2.getChildCount(); j++) {
//                                   child1 = child1 + finalViewHolder.linNamesParent2.getChildAt(j).getMeasuredHeight();
//                               }
//
//
//                               if (child1 >= (finalViewHolder.linNamesParent2.getHeight() - 20)) {
//                                   if (child1 != 0) {
//                                       for (int i = 0; i < txtTestArray2.size(); i++) {
//                                           txtTestArray2.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(0).getTextSize() - 2f);
//                                       }
//                                       txtTestArray2.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(1).getTextSize());
//
//                                       itemArrayList.get(i).getFamilyTextSizeArray().set((i * 4) + 1, txtTestArray2.get(1).getTextSize());
//
//                                       Log.i("PAGER", "TEXT INSERT");
//                                       for (int j = 0; j < txtRelationArray2.size(); j++) {
//                                           if (finalPosition2 < txtRelationArray2.size()) {
//                                               final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
////                                        if (j != finalPosition2) {
//                                               txtRelationArray2.get(j).setLayoutParams(params11);
//                                               txtRelationArray2.get(j).invalidate();
////                                        }
//                                           }
//                                       }
//                                   }
//                               } else {
//                                   for (int j = 0; j < txtRelationArray2.size(); j++) {
//                                       if (finalPosition2 < txtRelationArray2.size()) {
//                                           final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getMeasuredWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
////                                    if (j != finalPosition2) {
//                                           txtRelationArray2.get(j).setLayoutParams(params11);
//                                           txtRelationArray2.get(j).invalidate();
////                                    }
//                                       }
//                                   }
//                                   finalViewHolder.linNamesParent2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                               }
//                           } else {
//                               finalViewHolder.linNamesParent2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                           }
//                       }
//                   });
//               } else {
//                   for (int z = 0; z < txtTestArray2.size(); z++) {
//                       txtTestArray2.get(z).setTextSize(TypedValue.COMPLEX_UNIT_PX, itemArrayList.get(i).getFamilyTextSizeArray().get((i * 4) + 1));
//                   }
//               }
//           }

//
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
                        ArrayList<String> religionArray,
                        ArrayList<Float> familyTextSizeArray){

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
        generalViewPagerItem.setFamilyTextSizeArray(familyTextSizeArray);

        itemArrayList.add(generalViewPagerItem);

    }

    private class ViewHolder{
        public TextView txtTitle2;
        public TextView txtDeceasedName2;
        public LinearLayout linNamesParent2;
        public TextView txtCheckIn2;
        public TextView txtCheckOut2;
        public TextView txtLocation2;
        public ImageView imgProfile2;
        public ImageView imgArrow2;
        public ImageView imgReligionBg2;
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
}
