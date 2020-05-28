package com.kmw.dongsung.Func;

import android.app.ProgressDialog;
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
import com.kmw.dongsung.Activitys.InternetGeneralActivity;
import com.kmw.dongsung.Commons.CaptureUtil;
import com.kmw.dongsung.R;

import org.apmem.tools.layouts.FlowLayout;

import java.util.ArrayList;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.kmw.dongsung.Activitys.InternetGeneralActivity.linPopupTest;
import static com.kmw.dongsung.Activitys.InternetGeneralActivity.relaCaptureParent;
import static com.kmw.dongsung.Activitys.InternetGeneralActivity.viewPager;

public class InternetGeneralViewPagerAdapter extends PagerAdapter {

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

    public static boolean captureStatus = false;

    public InternetGeneralViewPagerAdapter(LayoutInflater inflater,Context context, int division, int arrowFlag, int famailySize,
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

        linPopupTest.setVisibility(View.VISIBLE);
        captureStatus = false;
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

        ArrayList<TextView> txtRelationArray1 = new ArrayList<TextView>();
        ArrayList<TextView> txtRelationArray2 = new ArrayList<TextView>();
        ArrayList<TextView> txtRelationArray3 = new ArrayList<TextView>();
        ArrayList<TextView> txtRelationArray4 = new ArrayList<TextView>();
        ArrayList<TextView> txtRelationArray5 = new ArrayList<TextView>();
        ArrayList<TextView> txtRelationArray6 = new ArrayList<TextView>();
        ArrayList<TextView> txtRelationArray7 = new ArrayList<TextView>();
        ArrayList<TextView> txtRelationArray8 = new ArrayList<TextView>();

        ArrayList<TextView> txtNamesArray1 = new ArrayList<TextView>();
        ArrayList<TextView> txtNamesArray2 = new ArrayList<TextView>();
        ArrayList<TextView>  txtNamesArray3 = new ArrayList<TextView>();
        ArrayList<TextView> txtNamesArray4 = new ArrayList<TextView>();
        ArrayList<TextView> txtNamesArray5 = new ArrayList<TextView>();
        ArrayList<TextView> txtNamesArray6 = new ArrayList<TextView>();
        ArrayList<TextView> txtNamesArray7 = new ArrayList<TextView>();
        ArrayList<TextView> txtNamesArray8 = new ArrayList<TextView>();

        ArrayList<TextView> txtTestArray1 = new ArrayList<TextView>();
        ArrayList<TextView> txtTestArray2 = new ArrayList<TextView>();
        ArrayList<TextView> txtTestArray3 = new ArrayList<TextView>();
        ArrayList<TextView> txtTestArray4 = new ArrayList<TextView>();
        ArrayList<TextView> txtTestArray5 = new ArrayList<TextView>();
        ArrayList<TextView> txtTestArray6 = new ArrayList<TextView>();
        ArrayList<TextView> txtTestArray7 = new ArrayList<TextView>();
        ArrayList<TextView> txtTestArray8 = new ArrayList<TextView>();

        ArrayList<FlowLayout> flowLayoutArrayList1 = new ArrayList<>();
        ArrayList<FlowLayout> flowLayoutArrayList2 = new ArrayList<>();
        ArrayList<FlowLayout> flowLayoutArrayList3 = new ArrayList<>();
        ArrayList<FlowLayout> flowLayoutArrayList4 = new ArrayList<>();
        ArrayList<FlowLayout> flowLayoutArrayList5 = new ArrayList<>();
        ArrayList<FlowLayout> flowLayoutArrayList6 = new ArrayList<>();
        ArrayList<FlowLayout> flowLayoutArrayList7 = new ArrayList<>();
        ArrayList<FlowLayout> flowLayoutArrayList8 = new ArrayList<>();

        ArrayList<TextView> txtRelationNamesArray = new ArrayList<TextView>();

        ViewHolder viewHolder = null;

        if (view == null){

            viewHolder = new ViewHolder();

            if (division == 1){
                view = inflater.inflate(R.layout.internet_general_one_division_item,null);
                viewHolder.txtTitle1        =         (TextView)view.findViewById(R.id.txt_one_division_item_title);
                viewHolder.txtDeceasedName1 = (TextView)view.findViewById(R.id.txt_one_division_item_deceased_name);
                viewHolder.txtCheckIn1      = (TextView)view.findViewById(R.id.txt_one_division_item_check_in);
                viewHolder.txtCheckOut1     = (TextView)view.findViewById(R.id.txt_one_division_item_check_out);
                viewHolder.txtLocation1     = (TextView)view.findViewById(R.id.txt_one_division_item_location);
                viewHolder.imgProfile1      = (ImageView)view.findViewById(R.id.img_one_division_item_deceased_profile);
                viewHolder.imgArrow1        = (ImageView)view.findViewById(R.id.img_one_division_item_arrow);
                viewHolder.linNamesParent1 = (LinearLayout)view.findViewById(R.id.linear_one_division_item_relation_name_parent);
                viewHolder.txtEtc1         = (TextView)view.findViewById(R.id.txt_one_division_item_etc1);
                viewHolder.txtEtc2         = (TextView)view.findViewById(R.id.txt_one_division_item_etc2);
                viewHolder.txtEtc3         = (TextView)view.findViewById(R.id.txt_one_division_item_etc3);
                viewHolder.imgReligionBg1  = (ImageView)view.findViewById(R.id.img_one_division_item_religion_bg1);
            }else if (division == 2){
                view = inflater.inflate(R.layout.internet_general_two_division_item,null);

                viewHolder.txtTitle1 = (TextView)view.findViewById(R.id.txt_tow_division_item_title1);
                viewHolder.txtDeceasedName1 = (TextView)view.findViewById(R.id.txt_tow_division_item_deceased_name1);
                viewHolder.txtCheckIn1 = (TextView)view.findViewById(R.id.txt_tow_division_item_check_in1);
                viewHolder.txtCheckOut1 = (TextView)view.findViewById(R.id.txt_tow_division_item_check_out1);
                viewHolder.txtLocation1 = (TextView)view.findViewById(R.id.txt_tow_division_item_location1);
                viewHolder.imgProfile1 = (ImageView)view.findViewById(R.id.img_tow_division_item_profile1);
                viewHolder.imgArrow1 = (ImageView)view.findViewById(R.id.img_two_division_item_arrow1);
                viewHolder.linNamesParent1 = (LinearLayout)view.findViewById(R.id.linear_two_division_item_name_parent1);
                viewHolder.imgReligionBg1  = (ImageView)view.findViewById(R.id.img_two_division_item_religion_bg1);


                viewHolder.txtTitle2 = (TextView)view.findViewById(R.id.txt_tow_division_item_title2);
                viewHolder.txtDeceasedName2 = (TextView)view.findViewById(R.id.txt_tow_division_item_deceased_name2);
                viewHolder.linNamesParent2 = (LinearLayout)view.findViewById(R.id.linear_two_division_item_name_parent2);
                viewHolder.txtCheckIn2 = (TextView)view.findViewById(R.id.txt_tow_division_item_check_in2);
                viewHolder.txtCheckOut2 = (TextView)view.findViewById(R.id.txt_tow_division_item_check_out2);
                viewHolder.txtLocation2 = (TextView)view.findViewById(R.id.txt_tow_division_item_location2);
                viewHolder.imgProfile2 = (ImageView)view.findViewById(R.id.img_tow_division_item_profile2);
                viewHolder.imgArrow2 = (ImageView)view.findViewById(R.id.img_two_division_item_arrow2);

                viewHolder.imgReligionBg2  = (ImageView)view.findViewById(R.id.img_two_division_item_religion_bg2);

                viewHolder.txtEtc1         = (TextView)view.findViewById(R.id.txt_two_division_item_etc1);
                viewHolder.txtEtc2         = (TextView)view.findViewById(R.id.txt_two_division_item_etc2);
                viewHolder.txtEtc3         = (TextView)view.findViewById(R.id.txt_two_division_item_etc3);
                viewHolder.txtEtc4         = (TextView)view.findViewById(R.id.txt_two_division_item_etc4);
                viewHolder.txtEtc5         = (TextView)view.findViewById(R.id.txt_two_division_item_etc5);
                viewHolder.txtEtc6         = (TextView)view.findViewById(R.id.txt_two_division_item_etc6);
            }else if (division == 4){
                view = inflater.inflate(R.layout.internet_general_four_division_item, null);
                viewHolder.txtTitle1 = (TextView) view.findViewById(R.id.txt_four_division_item_title1);
                viewHolder.txtDeceasedName1 = (TextView) view.findViewById(R.id.txt_four_division_item_deceased_name1);
                viewHolder.txtCheckIn1 = (TextView) view.findViewById(R.id.txt_four_division_item_check_in1);
                viewHolder.txtCheckOut1 = (TextView) view.findViewById(R.id.txt_four_division_item_check_out1);
                viewHolder.txtLocation1 = (TextView) view.findViewById(R.id.txt_four_division_item_location1);
                viewHolder.imgProfile1 = (ImageView) view.findViewById(R.id.img_four_division_item_profile1);
                viewHolder.imgArrow1 = (ImageView) view.findViewById(R.id.img_four_division_item_arrow1);
                viewHolder.linNamesParent1 = (LinearLayout) view.findViewById(R.id.linear_four_division_item_name_parent1);
                viewHolder.imgReligionBg1  = (ImageView)view.findViewById(R.id.img_four_division_item_religion_bg1);


                viewHolder.txtTitle2 = (TextView) view.findViewById(R.id.txt_four_division_item_title2);
                viewHolder.txtDeceasedName2 = (TextView) view.findViewById(R.id.txt_four_division_item_deceased_name2);
                viewHolder.linNamesParent2 = (LinearLayout) view.findViewById(R.id.linear_four_division_item_name_parent2);
                viewHolder.txtCheckIn2 = (TextView) view.findViewById(R.id.txt_four_division_item_check_in2);
                viewHolder.txtCheckOut2 = (TextView) view.findViewById(R.id.txt_four_division_item_check_out2);
                viewHolder.txtLocation2 = (TextView) view.findViewById(R.id.txt_four_division_item_location2);
                viewHolder.imgProfile2 = (ImageView) view.findViewById(R.id.img_four_division_item_profile2);
                viewHolder.imgArrow2 = (ImageView) view.findViewById(R.id.img_four_division_item_arrow2);
                viewHolder.imgReligionBg2  = (ImageView)view.findViewById(R.id.img_four_division_item_religion_bg2);


                viewHolder.txtTitle3 = (TextView) view.findViewById(R.id.txt_four_division_item_title3);
                viewHolder.txtDeceasedName3 = (TextView) view.findViewById(R.id.txt_four_division_item_deceased_name3);
                viewHolder.linNamesParent3 = (LinearLayout) view.findViewById(R.id.linear_four_division_item_name_parent3);
                viewHolder.txtCheckIn3 = (TextView) view.findViewById(R.id.txt_four_division_item_check_in3);
                viewHolder.txtCheckOut3 = (TextView) view.findViewById(R.id.txt_four_division_item_check_out3);
                viewHolder.txtLocation3 = (TextView) view.findViewById(R.id.txt_four_division_item_location3);
                viewHolder.imgProfile3 = (ImageView) view.findViewById(R.id.img_four_division_item_profile3);
                viewHolder.imgArrow3 = (ImageView) view.findViewById(R.id.img_four_division_item_arrow3);
                viewHolder.imgReligionBg3  = (ImageView)view.findViewById(R.id.img_four_division_item_religion_bg3);


                viewHolder.txtTitle4 = (TextView) view.findViewById(R.id.txt_four_division_item_title4);
                viewHolder.txtDeceasedName4 = (TextView) view.findViewById(R.id.txt_four_division_item_deceased_name4);
                viewHolder.linNamesParent4 = (LinearLayout) view.findViewById(R.id.linear_four_division_item_name_parent4);
                viewHolder.txtCheckIn4 = (TextView) view.findViewById(R.id.txt_four_division_item_check_in4);
                viewHolder.txtCheckOut4 = (TextView) view.findViewById(R.id.txt_four_division_item_check_out4);
                viewHolder.txtLocation4 = (TextView) view.findViewById(R.id.txt_four_division_item_location4);
                viewHolder.imgProfile4 = (ImageView) view.findViewById(R.id.img_four_division_item_profile4);
                viewHolder.imgArrow4 = (ImageView) view.findViewById(R.id.img_four_division_item_arrow4);

                viewHolder.imgReligionBg4  = (ImageView)view.findViewById(R.id.img_four_division_item_religion_bg4);


                viewHolder.txtEtc1 = (TextView) view.findViewById(R.id.txt_four_division_item_etc1);
                viewHolder.txtEtc2 = (TextView) view.findViewById(R.id.txt_four_division_item_etc2);
                viewHolder.txtEtc3 = (TextView) view.findViewById(R.id.txt_four_division_item_etc3);
                viewHolder.txtEtc4 = (TextView) view.findViewById(R.id.txt_four_division_item_etc4);
                viewHolder.txtEtc5 = (TextView) view.findViewById(R.id.txt_four_division_item_etc5);
                viewHolder.txtEtc6 = (TextView) view.findViewById(R.id.txt_four_division_item_etc6);
                viewHolder.txtEtc7 = (TextView) view.findViewById(R.id.txt_four_division_item_etc7);
                viewHolder.txtEtc8 = (TextView) view.findViewById(R.id.txt_four_division_item_etc8);
                viewHolder.txtEtc9 = (TextView) view.findViewById(R.id.txt_four_division_item_etc9);
                viewHolder.txtEtc10 = (TextView) view.findViewById(R.id.txt_four_division_item_etc10);
                viewHolder.txtEtc11 = (TextView) view.findViewById(R.id.txt_four_division_item_etc11);
                viewHolder.txtEtc12 = (TextView) view.findViewById(R.id.txt_four_division_item_etc12);
            }else if (division == 6){
                view = inflater.inflate(R.layout.internet_general_six_division_item,null);
                viewHolder.txtTitle1 = (TextView)view.findViewById(R.id.txt_six_division_item_title1);
                viewHolder.txtDeceasedName1 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name1);
                viewHolder.txtCheckIn1 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in1);
                viewHolder.txtCheckOut1 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out1);
                viewHolder.txtLocation1 = (TextView)view.findViewById(R.id.txt_six_division_item_location1);
                viewHolder. imgProfile1 = (ImageView)view.findViewById(R.id.img_six_division_item_profile1);
                viewHolder.imgArrow1 = (ImageView)view.findViewById(R.id.img_six_division_item_arrow1);
                viewHolder.linNamesParent1 = (LinearLayout) view.findViewById(R.id.linear_six_division_item_name_parent1);
                viewHolder.imgReligionBg1  = (ImageView)view.findViewById(R.id.img_six_division_item_religion_bg1);

                viewHolder.txtTitle2 = (TextView)view.findViewById(R.id.txt_six_division_item_title2);
                viewHolder.txtDeceasedName2 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name2);
                viewHolder.linNamesParent2 = (LinearLayout)view.findViewById(R.id.linear_six_division_item_name_parent2);
                viewHolder.txtCheckIn2 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in2);
                viewHolder.txtCheckOut2 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out2);
                viewHolder.txtLocation2 = (TextView)view.findViewById(R.id.txt_six_division_item_location2);
                viewHolder.imgProfile2 = (ImageView)view.findViewById(R.id.img_six_division_item_profile2);
                viewHolder.imgArrow2 = (ImageView)view.findViewById(R.id.img_six_division_item_arrow2);
                viewHolder.imgReligionBg2  = (ImageView)view.findViewById(R.id.img_six_division_item_religion_bg2);

                viewHolder.txtTitle3 = (TextView)view.findViewById(R.id.txt_six_division_item_title3);
                viewHolder.txtDeceasedName3 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name3);
                viewHolder.linNamesParent3 = (LinearLayout)view.findViewById(R.id.linear_six_division_item_name_parent3);
                viewHolder.txtCheckIn3 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in3);
                viewHolder.txtCheckOut3 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out3);
                viewHolder.txtLocation3 = (TextView)view.findViewById(R.id.txt_six_division_item_location3);
                viewHolder.imgProfile3 = (ImageView)view.findViewById(R.id.img_six_division_item_profile3);
                viewHolder.imgArrow3 = (ImageView)view.findViewById(R.id.img_six_division_item_arrow3);
                viewHolder.imgReligionBg3  = (ImageView)view.findViewById(R.id.img_six_division_item_religion_bg3);

                viewHolder.txtTitle4 = (TextView)view.findViewById(R.id.txt_six_division_item_title4);
                viewHolder.txtDeceasedName4 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name4);
                viewHolder.linNamesParent4 = (LinearLayout)view.findViewById(R.id.linear_six_division_item_name_parent4);
                viewHolder.txtCheckIn4 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in4);
                viewHolder.txtCheckOut4 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out4);
                viewHolder.txtLocation4 = (TextView)view.findViewById(R.id.txt_six_division_item_location4);
                viewHolder.imgProfile4 = (ImageView)view.findViewById(R.id.img_six_division_item_profile4);
                viewHolder.imgArrow4 = (ImageView)view.findViewById(R.id.img_six_division_item_arrow4);
                viewHolder.imgReligionBg4  = (ImageView)view.findViewById(R.id.img_six_division_item_religion_bg4);

                viewHolder.txtTitle5 = (TextView)view.findViewById(R.id.txt_six_division_item_title5);
                viewHolder.txtDeceasedName5 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name5);
                viewHolder.linNamesParent5 = (LinearLayout)view.findViewById(R.id.linear_six_division_item_name_parent5);
                viewHolder.txtCheckIn5 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in5);
                viewHolder.txtCheckOut5 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out5);
                viewHolder.txtLocation5 = (TextView)view.findViewById(R.id.txt_six_division_item_location5);
                viewHolder.imgProfile5 = (ImageView)view.findViewById(R.id.img_six_division_item_profile5);
                viewHolder.imgArrow5 = (ImageView)view.findViewById(R.id.img_six_division_item_arrow5);
                viewHolder.imgReligionBg5  = (ImageView)view.findViewById(R.id.img_six_division_item_religion_bg5);

                viewHolder.txtTitle6 = (TextView)view.findViewById(R.id.txt_six_division_item_title6);
                viewHolder.txtDeceasedName6 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name6);
                viewHolder.linNamesParent6 = (LinearLayout)view.findViewById(R.id.linear_six_division_item_name_parent6);
                viewHolder.txtCheckIn6 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in6);
                viewHolder.txtCheckOut6 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out6);
                viewHolder.txtLocation6 = (TextView)view.findViewById(R.id.txt_six_division_item_location6);
                viewHolder.imgProfile6 = (ImageView)view.findViewById(R.id.img_six_division_item_profile6);
                viewHolder.imgArrow6 = (ImageView)view.findViewById(R.id.img_six_division_item_arrow6);

                viewHolder.imgReligionBg6  = (ImageView)view.findViewById(R.id.img_six_division_item_religion_bg6);

                viewHolder.txtEtc1         = (TextView)view.findViewById(R.id.txt_six_division_item_etc1);
                viewHolder.txtEtc2         = (TextView)view.findViewById(R.id.txt_six_division_item_etc2);
                viewHolder.txtEtc3         = (TextView)view.findViewById(R.id.txt_six_division_item_etc3);
                viewHolder.txtEtc4         = (TextView)view.findViewById(R.id.txt_six_division_item_etc4);
                viewHolder.txtEtc5         = (TextView)view.findViewById(R.id.txt_six_division_item_etc5);
                viewHolder.txtEtc6         = (TextView)view.findViewById(R.id.txt_six_division_item_etc6);
                viewHolder.txtEtc7         = (TextView)view.findViewById(R.id.txt_six_division_item_etc7);
                viewHolder.txtEtc8         = (TextView)view.findViewById(R.id.txt_six_division_item_etc8);
                viewHolder.txtEtc9         = (TextView)view.findViewById(R.id.txt_six_division_item_etc9);
                viewHolder.txtEtc10         = (TextView)view.findViewById(R.id.txt_six_division_item_etc10);
                viewHolder.txtEtc11         = (TextView)view.findViewById(R.id.txt_six_division_item_etc11);
                viewHolder.txtEtc12         = (TextView)view.findViewById(R.id.txt_six_division_item_etc12);
                viewHolder.txtEtc13        = (TextView)view.findViewById(R.id.txt_six_division_item_etc13);
                viewHolder.txtEtc14         = (TextView)view.findViewById(R.id.txt_six_division_item_etc14);
                viewHolder.txtEtc15         = (TextView)view.findViewById(R.id.txt_six_division_item_etc15);
                viewHolder.txtEtc16         = (TextView)view.findViewById(R.id.txt_six_division_item_etc16);
                viewHolder.txtEtc17         = (TextView)view.findViewById(R.id.txt_six_division_item_etc17);
                viewHolder.txtEtc18         = (TextView)view.findViewById(R.id.txt_six_division_item_etc18);
            }else if (division == 8){
                view = inflater.inflate(R.layout.internet_general_eight_division_item,null);

                viewHolder.txtTitle1 = (TextView)view.findViewById(R.id.txt_eight_division_item_title1);
                viewHolder.txtDeceasedName1 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name1);
                viewHolder.txtCheckIn1 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in1);
                viewHolder.txtCheckOut1 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out1);
                viewHolder.txtLocation1 = (TextView)view.findViewById(R.id.txt_eight_division_item_location1);
                viewHolder.imgProfile1 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile1);
                viewHolder.imgArrow1 = (ImageView)view.findViewById(R.id.img_eight_division_item_arrow1);
                viewHolder.linNamesParent1 = (LinearLayout)view.findViewById(R.id.linear_eight_division_item_name_parent1);
                viewHolder.imgReligionBg1  = (ImageView)view.findViewById(R.id.img_eight_division_item_religion_bg1);

                viewHolder.txtTitle2 = (TextView)view.findViewById(R.id.txt_eight_division_item_title2);
                viewHolder.txtDeceasedName2 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name2);
                viewHolder.linNamesParent2 = (LinearLayout)view.findViewById(R.id.linear_eight_division_item_name_parent2);
                viewHolder.txtCheckIn2 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in2);
                viewHolder.txtCheckOut2 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out2);
                viewHolder.txtLocation2 = (TextView)view.findViewById(R.id.txt_eight_division_item_location2);
                viewHolder.imgProfile2 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile2);
                viewHolder.imgArrow2 = (ImageView)view.findViewById(R.id.img_eight_division_item_arrow2);
                viewHolder.imgReligionBg2  = (ImageView)view.findViewById(R.id.img_eight_division_item_religion_bg2);

                viewHolder.txtTitle3 = (TextView)view.findViewById(R.id.txt_eight_division_item_title3);
                viewHolder.txtDeceasedName3 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name3);
                viewHolder.linNamesParent3 = (LinearLayout)view.findViewById(R.id.linear_eight_division_item_name_parent3);
                viewHolder.txtCheckIn3 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in3);
                viewHolder.txtCheckOut3 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out3);
                viewHolder.txtLocation3 = (TextView)view.findViewById(R.id.txt_eight_division_item_location3);
                viewHolder.imgProfile3 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile3);
                viewHolder.imgArrow3 = (ImageView)view.findViewById(R.id.img_eight_division_item_arrow3);
                viewHolder.imgReligionBg3  = (ImageView)view.findViewById(R.id.img_eight_division_item_religion_bg3);

                viewHolder.txtTitle4 = (TextView)view.findViewById(R.id.txt_eight_division_item_title4);
                viewHolder.txtDeceasedName4 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name4);
                viewHolder.linNamesParent4 = (LinearLayout)view.findViewById(R.id.linear_eight_division_item_name_parent4);
                viewHolder.txtCheckIn4 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in4);
                viewHolder.txtCheckOut4 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out4);
                viewHolder.txtLocation4 = (TextView)view.findViewById(R.id.txt_eight_division_item_location4);
                viewHolder.imgProfile4 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile4);
                viewHolder. imgArrow4 = (ImageView)view.findViewById(R.id.img_eight_division_item_arrow4);
                viewHolder.imgReligionBg4  = (ImageView)view.findViewById(R.id.img_eight_division_item_religion_bg4);

                viewHolder.txtTitle5 = (TextView)view.findViewById(R.id.txt_eight_division_item_title5);
                viewHolder.txtDeceasedName5 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name5);
                viewHolder.linNamesParent5 = (LinearLayout)view.findViewById(R.id.linear_eight_division_item_name_parent5);
                viewHolder.txtCheckIn5 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in5);
                viewHolder.txtCheckOut5 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out5);
                viewHolder.txtLocation5 = (TextView)view.findViewById(R.id.txt_eight_division_item_location5);
                viewHolder.imgProfile5 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile5);
                viewHolder.imgArrow5 = (ImageView)view.findViewById(R.id.img_eight_division_item_arrow5);
                viewHolder.imgReligionBg5  = (ImageView)view.findViewById(R.id.img_eight_division_item_religion_bg5);

                viewHolder.txtTitle6 = (TextView)view.findViewById(R.id.txt_eight_division_item_title6);
                viewHolder. txtDeceasedName6 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name6);
                viewHolder. linNamesParent6 = (LinearLayout)view.findViewById(R.id.linear_eight_division_item_name_parent6);
                viewHolder. txtCheckIn6 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in6);
                viewHolder. txtCheckOut6 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out6);
                viewHolder. txtLocation6 = (TextView)view.findViewById(R.id.txt_eight_division_item_location6);
                viewHolder. imgProfile6 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile6);
                viewHolder. imgArrow6 = (ImageView)view.findViewById(R.id.img_eight_division_item_arrow6);
                viewHolder.imgReligionBg6  = (ImageView)view.findViewById(R.id.img_eight_division_item_religion_bg6);

                viewHolder. txtTitle7 = (TextView)view.findViewById(R.id.txt_eight_division_item_title7);
                viewHolder.txtDeceasedName7 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name7);
                viewHolder. linNamesParent7 = (LinearLayout)view.findViewById(R.id.linear_eight_division_item_name_parent7);
                viewHolder.txtCheckIn7 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in7);
                viewHolder.txtCheckOut7 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out7);
                viewHolder.txtLocation7 = (TextView)view.findViewById(R.id.txt_eight_division_item_location7);
                viewHolder.imgProfile7 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile7);
                viewHolder.imgArrow7 = (ImageView)view.findViewById(R.id.img_eight_division_item_arrow7);
                viewHolder.imgReligionBg7  = (ImageView)view.findViewById(R.id.img_eight_division_item_religion_bg7);

                viewHolder.txtTitle8 = (TextView)view.findViewById(R.id.txt_eight_division_item_title8);
                viewHolder.txtDeceasedName8 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name8);
                viewHolder.linNamesParent8 = (LinearLayout)view.findViewById(R.id.linear_eight_division_item_name_parent8);
                viewHolder.txtCheckIn8 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in8);
                viewHolder.txtCheckOut8 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out8);
                viewHolder.txtLocation8 = (TextView)view.findViewById(R.id.txt_eight_division_item_location8);
                viewHolder.imgProfile8 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile8);
                viewHolder.imgArrow8 = (ImageView)view.findViewById(R.id.img_eight_division_item_arrow8);

                viewHolder.imgReligionBg8  = (ImageView)view.findViewById(R.id.img_eight_division_item_religion_bg8);

                viewHolder.txtEtc1          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc1);
                viewHolder.txtEtc2          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc2);
                viewHolder.txtEtc3          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc3);
                viewHolder.txtEtc4          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc4);
                viewHolder.txtEtc5          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc5);
                viewHolder.txtEtc6          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc6);
                viewHolder.txtEtc7          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc7);
                viewHolder.txtEtc8          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc8);
                viewHolder.txtEtc9          = (TextView)view.findViewById(R.id.txt_eight_division_item_etc9);
                viewHolder.txtEtc10         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc10);
                viewHolder.txtEtc11         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc11);
                viewHolder.txtEtc12         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc12);
                viewHolder.txtEtc13         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc13);
                viewHolder.txtEtc14         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc14);
                viewHolder.txtEtc15         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc15);
                viewHolder.txtEtc16         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc16);
                viewHolder.txtEtc17         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc17);
                viewHolder.txtEtc18         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc18);
                viewHolder.txtEtc19         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc19);
                viewHolder.txtEtc20         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc20);
                viewHolder.txtEtc21         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc21);
                viewHolder. txtEtc22         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc22);
                viewHolder.txtEtc23         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc23);
                viewHolder.txtEtc24         = (TextView)view.findViewById(R.id.txt_eight_division_item_etc24);
            }

            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)view.getTag();
        }


        if (division == 1){

            viewHolder.txtTitle1.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName1.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc1.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc2.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc3.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder.txtCheckIn1.setTextColor(Color.parseColor(checkInColor));
            viewHolder.txtCheckOut1.setTextColor(Color.parseColor(checkOutColor));
            viewHolder.txtLocation1.setTextColor(Color.parseColor(locationColor));

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

            viewHolder.txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(i));
            viewHolder.txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(i));
            viewHolder.txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(i));
            viewHolder.txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(i));

            if (itemArrayList.get(i).getLocationArray().get(i).contains("\r\n")){
                viewHolder.txtLocation1.setMaxLines(2);
            }else{
                viewHolder.txtLocation1.setMaxLines(1);
            }

            if (itemArrayList.get(i).getLocationArray().get(i).length() != 0){
                viewHolder.txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(i));
            }else{
                viewHolder.txtLocation1.setText("미 정");
            }

            if (itemArrayList.get(i).getImgPathArray().get(i).length() > 0){
                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(i)).into(viewHolder.imgProfile1);
            }
//            if (itemArrayList.get(i).getReligionArray().get(i).length() > 0){
//                Glide.with(context).load(itemArrayList.get(i).getReligionArray().get(i)).into(viewHolder.imgReligionBg1);
//            }



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
                ListMake(a,itemArrayList.get(i).getRelationArray().get(i).get(a),itemArrayList.get(i).getNamesArray().get(i).get(a), 60f,viewHolder.linNamesParent1,familyColor,1,txtRelationArray1,txtTestArray1,txtRelationNamesArray,txtNamesArray1);
            }

            if (itemArrayList.get(i).getArrowNoArray().get((i)).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i)).equals("")){
                viewHolder.imgArrow1.setVisibility(View.GONE);
            }else{
                viewHolder.imgArrow1.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i))));
            }

            viewHolder.linNamesParent1.setVisibility(View.VISIBLE);

            ViewHolder finalViewHolder = viewHolder;


            viewHolder.linNamesParent1.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    finalViewHolder.txtEtc1.setTextSize(TypedValue.COMPLEX_UNIT_PX, finalViewHolder.txtCheckIn1.getTextSize());
                    finalViewHolder.txtEtc2.setTextSize(TypedValue.COMPLEX_UNIT_PX, finalViewHolder.txtCheckIn1.getTextSize());
                    finalViewHolder.txtEtc3.setTextSize(TypedValue.COMPLEX_UNIT_PX, finalViewHolder.txtCheckIn1.getTextSize());
                    finalViewHolder.txtCheckOut1.setTextSize(TypedValue.COMPLEX_UNIT_PX, finalViewHolder.txtCheckIn1.getTextSize());
                    finalViewHolder.txtLocation1.setTextSize(TypedValue.COMPLEX_UNIT_PX, finalViewHolder.txtCheckIn1.getTextSize());

                    if (finalViewHolder.linNamesParent1.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder.linNamesParent1.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder.linNamesParent1.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder.linNamesParent1.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray1.size(); i++) {
                                    txtTestArray1.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray1.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i), txtTestArray1.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray1.get(finalPosition1).setLayoutParams(params1);
                                txtRelationArray1.get(finalPosition1).invalidate();

                                for (int j = 0; j < txtRelationArray1.size(); j++) {
                                    if (finalPosition1 < txtRelationArray1.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition1) {
                                            txtRelationArray1.get(j).setWidth(txtRelationArray1.get(finalPosition1).getWidth());
                                            txtRelationArray1.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray1.size(); j++) {
                                if (finalPosition1 < txtRelationArray1.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray1.get(j).setLayoutParams(params11);
                                    txtRelationArray1.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition1 == 0){
                                if (txtRelationArray1.size()>1){
                                    txtRelationArray1.get(0).setWidth(txtRelationArray1.get(1).getWidth());
                                    txtRelationArray1.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray1.size()>1){
                                    txtRelationArray1.get(finalPosition1).setWidth(txtRelationArray1.get(0).getWidth());
                                    txtRelationArray1.get(finalPosition1).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder.linNamesParent1.getChildCount(); j++){
                                child2 = child2 + finalViewHolder.linNamesParent1.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder.linNamesParent1.getHeight())) {

                            }else{

                                if (finalPosition1 == 0){
                                    if (txtRelationArray1.size()>1){
                                        txtRelationArray1.get(0).setWidth(txtRelationArray1.get(1).getWidth());
                                        txtRelationArray1.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray1.size()>1){
                                        txtRelationArray1.get(finalPosition1).setWidth(txtRelationArray1.get(0).getWidth());
                                        txtRelationArray1.get(finalPosition1).invalidate();
                                    }
                                }

                                if (itemArrayList.get(i).getTitleArray().size() < 2){
                                    linPopupTest.setVisibility(View.GONE);
                                }

                                finalViewHolder. linNamesParent1.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder.linNamesParent1.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            if (arrowFlag != 1){
                viewHolder.imgArrow1.setVisibility(View.GONE);
            }

        }else if (division == 2){

            LinearLayout linearVisible2 = null;

            linearVisible2 = (LinearLayout)view.findViewById(R.id.linear_two_visible2);
            imgVisible = (ImageView)view.findViewById(R.id.img_two_division_item_img);

            viewHolder.txtTitle1.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName1.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc1.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc2.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc3.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder.txtCheckIn1.setTextColor(Color.parseColor(checkInColor));
            viewHolder.txtCheckOut1.setTextColor(Color.parseColor(checkOutColor));
            viewHolder.txtLocation1.setTextColor(Color.parseColor(locationColor));

            viewHolder.txtTitle2.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName2.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc4.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc5.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc6.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder.txtCheckIn2.setTextColor(Color.parseColor(checkInColor));
            viewHolder.txtCheckOut2.setTextColor(Color.parseColor(checkOutColor));
            viewHolder.txtLocation2.setTextColor(Color.parseColor(locationColor));

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

            viewHolder.txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(i * 2));
            viewHolder.txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(i * 2));
            viewHolder.txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(i * 2));
            viewHolder.txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(i * 2));

            if (itemArrayList.get(i).getLocationArray().get(i * 2).contains("\r\n")){
                viewHolder.txtLocation1.setMaxLines(2);
            }else{
                viewHolder.txtLocation1.setMaxLines(1);
            }

            if (itemArrayList.get(i).getLocationArray().get(i * 2).length() != 0){
                viewHolder.txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(i * 2));
            }else{
                viewHolder.txtLocation1.setText("미 정");
            }

            if (itemArrayList.get(i).getImgPathArray().get(i * 2).length() != 0){
                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(i * 2)).into(viewHolder.imgProfile1);
            }else{
                viewHolder.imgProfile1.setImageResource(R.drawable.img_no_profile);
            }

//            if (itemArrayList.get(i).getReligionArray().get(i * 2).length() > 0){
//                Glide.with(context).load(itemArrayList.get(i).getReligionArray().get(i * 2)).into(viewHolder.imgReligionBg1);
//            }

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
                ListMake(a,itemArrayList.get(i).getRelationArray().get(i * 2).get(a),itemArrayList.get(i).getNamesArray().get(i * 2).get(a), 50f,viewHolder.linNamesParent1,familyColor,1,txtRelationArray1,txtTestArray1,txtRelationNamesArray,txtNamesArray1);
            }

            if (itemArrayList.get(i).getArrowNoArray().get((i * 2)).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 2)).equals("")){
                viewHolder.imgArrow1.setVisibility(View.GONE);
            }else{
                viewHolder.imgArrow1.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 2))));
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 2) + 1){
                viewHolder.txtTitle2.setText(itemArrayList.get(i).getTitleArray().get((i * 2) + 1));
                viewHolder.txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get((i * 2) + 1));
//                txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get((i * 4) + 1));
                viewHolder.txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get((i * 2) + 1));
                viewHolder.txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get((i * 2) + 1));

                if (itemArrayList.get(i).getLocationArray().get((i * 2) + 1).contains("\r\n")){
                    viewHolder.txtLocation2.setMaxLines(2);
                }else{
                    viewHolder.txtLocation2.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 2) + 1).length() != 0){
                    viewHolder.txtLocation2.setText(itemArrayList.get(i).getLocationArray().get((i * 2) + 1));
                }else{
                    viewHolder.txtLocation2.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 2) + 1).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 2) + 1)).into(viewHolder.imgProfile2);
                }else{
                    viewHolder.imgProfile2.setImageResource(R.drawable.img_no_profile);
                }

//                if (itemArrayList.get(i).getReligionArray().get((i * 2) + 1).length() > 0){
//                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 2) + 1)).into(viewHolder.imgReligionBg2);
//                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 2) + 1).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 2) + 1).get(a),itemArrayList.get(i).getNamesArray().get((i * 2) + 1).get(a),50f,viewHolder.linNamesParent2,familyColor,2,txtRelationArray2,txtTestArray2,txtRelationNamesArray,txtNamesArray2);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 2) + 1).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 2) + 1).equals("")){
                    viewHolder.imgArrow2.setVisibility(View.GONE);
                }else{
                    viewHolder.imgArrow2.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 2) + 1)));
                }

            }else{
                linearVisible2.setVisibility(View.GONE);
            }

            viewHolder.txtEtc1.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            viewHolder.txtEtc2.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            viewHolder. txtEtc3.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            viewHolder.txtCheckOut1.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            viewHolder.txtLocation1.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);

            viewHolder.txtEtc4.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            viewHolder.txtEtc5.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            viewHolder.txtEtc6.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            viewHolder.txtCheckOut2.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);
            viewHolder.txtLocation2.setTextSize(TypedValue.COMPLEX_UNIT_PX,40);

            ViewHolder finalViewHolder1 = viewHolder;

            Log.i(TAG,"titleSize : " + itemArrayList.get(i).getTitleArray().size() + " position : " + familyPosition);

            viewHolder.linNamesParent1.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent1.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent1.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent1.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent1.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray1.size(); i++) {
                                    txtTestArray1.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray1.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 2), txtTestArray1.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray1.get(finalPosition1).setLayoutParams(params1);
                                txtRelationArray1.get(finalPosition1).invalidate();

                                for (int j = 0; j < txtRelationArray1.size(); j++) {
                                    if (finalPosition1 < txtRelationArray1.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition1) {
                                            txtRelationArray1.get(j).setWidth(txtRelationArray1.get(finalPosition1).getWidth());
                                            txtRelationArray1.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray1.size(); j++) {
                                if (finalPosition1 < txtRelationArray1.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray1.get(j).setLayoutParams(params11);
                                    txtRelationArray1.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition1 == 0){
                                if (txtRelationArray1.size()>1){
                                    txtRelationArray1.get(0).setWidth(txtRelationArray1.get(1).getWidth());
                                    txtRelationArray1.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray1.size()>1){
                                    txtRelationArray1.get(finalPosition1).setWidth(txtRelationArray1.get(0).getWidth());
                                    txtRelationArray1.get(finalPosition1).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent1.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent1.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent1.getHeight())) {

                            }else{

                                if (finalPosition1 == 0){
                                    if (txtRelationArray1.size()>1){
                                        txtRelationArray1.get(0).setWidth(txtRelationArray1.get(1).getWidth());
                                        txtRelationArray1.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray1.size()>1){
                                        txtRelationArray1.get(finalPosition1).setWidth(txtRelationArray1.get(0).getWidth());
                                        txtRelationArray1.get(finalPosition1).invalidate();
                                    }
                                }
                                if (familyPosition == 0){
                                    if (itemArrayList.get(i).getTitleArray().size() < 3){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1. linNamesParent1.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent1.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            viewHolder.linNamesParent2.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent2.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent2.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent2.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent2.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray2.size(); i++) {
                                    txtTestArray2.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray2.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 2) + 1, txtTestArray2.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray2.get(finalPosition2).setLayoutParams(params1);
                                txtRelationArray2.get(finalPosition2).invalidate();

                                for (int j = 0; j < txtRelationArray2.size(); j++) {
                                    if (finalPosition2 < txtRelationArray2.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition2) {
                                            txtRelationArray2.get(j).setWidth(txtRelationArray2.get(finalPosition2).getWidth());
                                            txtRelationArray2.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray2.size(); j++) {
                                if (finalPosition2 < txtRelationArray2.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray2.get(j).setLayoutParams(params11);
                                    txtRelationArray2.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition2 == 0){
                                if (txtRelationArray2.size()>1){
                                    txtRelationArray2.get(0).setWidth(txtRelationArray2.get(1).getWidth());
                                    txtRelationArray2.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray2.size()>1){
                                    txtRelationArray2.get(finalPosition2).setWidth(txtRelationArray2.get(0).getWidth());
                                    txtRelationArray2.get(finalPosition2).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent2.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent2.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent2.getHeight())) {

                            }else{

                                if (finalPosition2 == 0){
                                    if (txtRelationArray2.size()>1){
                                        txtRelationArray2.get(0).setWidth(txtRelationArray2.get(1).getWidth());
                                        txtRelationArray2.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray2.size()>1){
                                        txtRelationArray2.get(finalPosition2).setWidth(txtRelationArray2.get(0).getWidth());
                                        txtRelationArray2.get(finalPosition2).invalidate();
                                    }
                                }
                                if (familyPosition == 1){
                                    if (itemArrayList.get(i).getTitleArray().size() < 3){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1. linNamesParent2.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent2.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            if (arrowFlag != 1){
                viewHolder.imgArrow1.setVisibility(View.GONE);
                viewHolder.imgArrow2.setVisibility(View.GONE);
            }
        }else if (division == 4) {

            LinearLayout linVisible2 = (LinearLayout) view.findViewById(R.id.linear_four_list_item_visible2);
            LinearLayout linVisible3 = (LinearLayout) view.findViewById(R.id.linear_four_list_item_visible3);
            LinearLayout linVisible4 = (LinearLayout) view.findViewById(R.id.linear_four_list_item_visible4);

            imgVisible = (ImageView) view.findViewById(R.id.img_four_division_item_visible);

            viewHolder.txtTitle1.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName1.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc1.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc2.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc3.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder.txtCheckIn1.setTextColor(Color.parseColor(checkInColor));
            viewHolder.txtCheckOut1.setTextColor(Color.parseColor(checkOutColor));
            viewHolder.txtLocation1.setTextColor(Color.parseColor(locationColor));

            viewHolder.txtTitle2.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName2.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc4.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc5.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc6.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder.txtCheckIn2.setTextColor(Color.parseColor(checkInColor));
            viewHolder.txtCheckOut2.setTextColor(Color.parseColor(checkOutColor));
            viewHolder.txtLocation2.setTextColor(Color.parseColor(locationColor));

            viewHolder.txtTitle3.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName3.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc7.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc8.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc9.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder.txtCheckIn3.setTextColor(Color.parseColor(checkInColor));
            viewHolder.txtCheckOut3.setTextColor(Color.parseColor(checkOutColor));
            viewHolder.txtLocation3.setTextColor(Color.parseColor(locationColor));

            viewHolder.txtTitle4.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName4.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc10.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc11.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc12.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder.txtCheckIn4.setTextColor(Color.parseColor(checkInColor));
            viewHolder.txtCheckOut4.setTextColor(Color.parseColor(checkOutColor));
            viewHolder.txtLocation4.setTextColor(Color.parseColor(locationColor));

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

            viewHolder.txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(i * 4));
            viewHolder.txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(i * 4));
            viewHolder.txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(i * 4));
            viewHolder.txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(i * 4));

            if (itemArrayList.get(i).getLocationArray().get((i * 4)).contains("\r\n")) {
                viewHolder.txtLocation1.setMaxLines(2);
            } else {
                viewHolder.txtLocation1.setMaxLines(1);
            }

            if (itemArrayList.get(i).getLocationArray().get(i * 4).length() != 0) {
                viewHolder.txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(i * 4));
            } else {
                viewHolder.txtLocation1.setText("미 정");
            }

            if (itemArrayList.get(i).getImgPathArray().get(i * 4).length() != 0){
                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(i * 4)).into(viewHolder.imgProfile1);
            }else{
//                viewHolder.imgProfile1.setImageResource(R.drawable.img_four_division_no_profile);
            }


//            if (itemArrayList.get(i).getReligionArray().get(i * 4).length() > 0){
//                Glide.with(context).load(itemArrayList.get(i).getReligionArray().get(i * 4)).into(viewHolder.imgReligionBg1);
//            }

            if (divisionImg.length() != 0) {
                Glide.with(context).load(divisionImg).into(imgVisible);
            } else {
                imgVisible.setBackgroundResource(R.drawable.img_four_visible);
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

            int relationLenth3 = 0;
            int position3 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 4) + 2) {
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 4) + 2).size(); a++) {
                    if (relationLenth3 < itemArrayList.get(i).getRelationArray().get((i * 4) + 2).get(a).length()) {
                        if (itemArrayList.get(i).getRelationArray().get((i * 4) + 2).get(a).contains(" ")) {
                            relationLenth3 = itemArrayList.get(i).getRelationArray().get((i * 4) + 2).get(a).length() - 1;
                        } else {
                            relationLenth3 = itemArrayList.get(i).getRelationArray().get((i * 4) + 2).get(a).length();
                        }
                        position3 = a;
                    }
                }
            }

            int relationLenth2 = 0;
            int position2 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 4) + 1) {
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 4) + 1).size(); a++) {
                    if (relationLenth2 < itemArrayList.get(i).getRelationArray().get((i * 4) + 1).get(a).length()) {
                        if (itemArrayList.get(i).getRelationArray().get((i * 4) + 1).get(a).contains(" ")) {
                            relationLenth2 = itemArrayList.get(i).getRelationArray().get((i * 4) + 1).get(a).length() - 1;
                        } else {
                            relationLenth2 = itemArrayList.get(i).getRelationArray().get((i * 4) + 1).get(a).length();
                        }
                        position2 = a;
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

            final int finalPosition1 = position1;
            final int finalPosition2 = position2;
            final int finalPosition3 = position3;
            final int finalPosition4 = position4;

            for (int a = 0; a < itemArrayList.get(i).getNamesArray().get(i * 4).size(); a++) {
                ListMake(a, itemArrayList.get(i).getRelationArray().get(i * 4).get(a), itemArrayList.get(i).getNamesArray().get(i * 4).get(a), 50f, viewHolder.linNamesParent1, familyColor, 1,txtRelationArray1,txtTestArray1,txtRelationNamesArray,txtNamesArray1);
            }

            if (itemArrayList.get(i).getArrowNoArray().get((i * 4)).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 4)).equals("")) {
                viewHolder.imgArrow1.setVisibility(View.GONE);
            } else {
                viewHolder.imgArrow1.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 4))));
            }

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
//                    viewHolder.imgProfile2.setImageResource(R.drawable.img_four_division_no_profile);
                }

//                if (itemArrayList.get(i).getReligionArray().get((i * 4) + 1).length() > 0){
//                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 4) + 1)).into(viewHolder.imgReligionBg2);
//                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 4) + 1).size(); a++) {
                    ListMake(a, itemArrayList.get(i).getRelationArray().get((i * 4) + 1).get(a), itemArrayList.get(i).getNamesArray().get((i * 4) + 1).get(a), 50f, viewHolder.linNamesParent2, familyColor, 2,txtRelationArray2,txtTestArray2,txtRelationNamesArray,txtNamesArray2);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 4) + 1).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 4) + 1).equals("")) {
                    viewHolder.imgArrow2.setVisibility(View.GONE);
                } else {
                    viewHolder.imgArrow2.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 4) + 1)));
                }

            } else {
                linVisible2.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 4) + 2) {
                viewHolder.txtTitle3.setText(itemArrayList.get(i).getTitleArray().get((i * 4) + 2));
                viewHolder.txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get((i * 4) + 2));
//                txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get((i * 4) + 1));
                viewHolder.txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get((i * 4) + 2));
                viewHolder.txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get((i * 4) + 2));

                if (itemArrayList.get(i).getLocationArray().get((i * 4) + 2).contains("\r\n")) {
                    viewHolder.txtLocation3.setMaxLines(2);
                } else {
                    viewHolder.txtLocation3.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 4) + 2).length() != 0) {
                    viewHolder.txtLocation3.setText(itemArrayList.get(i).getLocationArray().get((i * 4) + 2));
                } else {
                    viewHolder.txtLocation3.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 4) + 2).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 4) + 2)).into(viewHolder.imgProfile3);
                }else{
//                    viewHolder.imgProfile3.setImageResource(R.drawable.img_four_division_no_profile);
                }

//                if (itemArrayList.get(i).getReligionArray().get((i * 4) + 2).length() > 0){
//                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 4) + 2)).into(viewHolder.imgReligionBg3);
//                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 4) + 2).size(); a++) {
                    ListMake(a, itemArrayList.get(i).getRelationArray().get((i * 4) + 2).get(a), itemArrayList.get(i).getNamesArray().get((i * 4) + 2).get(a), 50f, viewHolder.linNamesParent3, familyColor, 3,txtRelationArray3,txtTestArray3,txtRelationNamesArray,txtNamesArray3);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 4) + 2).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 4) + 2).equals("")) {
                    viewHolder.imgArrow3.setVisibility(View.GONE);
                } else {
                    viewHolder.imgArrow3.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 4) + 2)));
                }
            } else {
                linVisible3.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 4) + 3) {
                viewHolder.txtTitle4.setText(itemArrayList.get(i).getTitleArray().get((i * 4) + 3));
                viewHolder.txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get((i * 4) + 3));
//                txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get((i * 4) + 3));
                viewHolder.txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get((i * 4) + 3));
                viewHolder.txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get((i * 4) + 3));

                if (itemArrayList.get(i).getLocationArray().get((i * 4) + 3).contains("\r\n")) {
                    viewHolder.txtLocation4.setMaxLines(2);
                } else {
                    viewHolder.txtLocation4.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 4) + 3).length() != 0) {
                    viewHolder.txtLocation4.setText(itemArrayList.get(i).getLocationArray().get((i * 4) + 3));
                } else {
                    viewHolder.txtLocation4.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 4) + 3).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 4) + 3)).into(viewHolder.imgProfile4);
                }else{
//                    viewHolder.imgProfile4.setImageResource(R.drawable.img_four_division_no_profile);
                }

//                if (itemArrayList.get(i).getReligionArray().get((i * 4) + 3).length() > 0){
//                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 4) + 3)).into(viewHolder.imgReligionBg4);
//                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 4) + 3).size(); a++) {
                    ListMake(a, itemArrayList.get(i).getRelationArray().get((i * 4) + 3).get(a), itemArrayList.get(i).getNamesArray().get((i * 4) + 3).get(a), 50f, viewHolder.linNamesParent4, familyColor, 4,txtRelationArray4,txtTestArray4,txtRelationNamesArray,txtNamesArray4);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 4) + 3).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 4) + 3).equals("")) {
                    viewHolder.imgArrow4.setVisibility(View.GONE);
                } else {
                    viewHolder.imgArrow4.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 4) + 3)));
                }
            } else {
                linVisible4.setVisibility(View.GONE);
            }

            final int[] aaaa = {0};

            Log.i(TAG,"titleSize : " + itemArrayList.get(i).getTitleArray().size() + " position : " + familyPosition);

            ViewHolder finalViewHolder1 = viewHolder;

            viewHolder.linNamesParent1.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent1.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent1.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent1.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent1.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray1.size(); i++) {
                                    txtTestArray1.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray1.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 4), txtTestArray1.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray1.get(finalPosition1).setLayoutParams(params1);
                                txtRelationArray1.get(finalPosition1).invalidate();

                                for (int j = 0; j < txtRelationArray1.size(); j++) {
                                    if (finalPosition1 < txtRelationArray1.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition1) {
                                            txtRelationArray1.get(j).setWidth(txtRelationArray1.get(finalPosition1).getWidth());
                                            txtRelationArray1.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray1.size(); j++) {
                                if (finalPosition1 < txtRelationArray1.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                        txtRelationArray1.get(j).setLayoutParams(params11);
                                        txtRelationArray1.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition1 == 0){
                                if (txtRelationArray1.size()>1){
                                    txtRelationArray1.get(0).setWidth(txtRelationArray1.get(1).getWidth());
                                    txtRelationArray1.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray1.size()>1){
                                    txtRelationArray1.get(finalPosition1).setWidth(txtRelationArray1.get(0).getWidth());
                                    txtRelationArray1.get(finalPosition1).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent1.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent1.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent1.getHeight())) {

                            }else{

                                if (finalPosition1 == 0){
                                    if (txtRelationArray1.size()>1){
                                        txtRelationArray1.get(0).setWidth(txtRelationArray1.get(1).getWidth());
                                        txtRelationArray1.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray1.size()>1){
                                        txtRelationArray1.get(finalPosition1).setWidth(txtRelationArray1.get(0).getWidth());
                                        txtRelationArray1.get(finalPosition1).invalidate();
                                    }
                                }
                                if (familyPosition == 0){
                                    if (itemArrayList.get(i).getTitleArray().size() < 5){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1. linNamesParent1.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent1.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            viewHolder.linNamesParent2.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent2.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent2.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent2.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent2.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray2.size(); i++) {
                                    txtTestArray2.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray2.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 4) + 1, txtTestArray2.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray2.get(finalPosition2).setLayoutParams(params1);
                                txtRelationArray2.get(finalPosition2).invalidate();

                                for (int j = 0; j < txtRelationArray2.size(); j++) {
                                    if (finalPosition2 < txtRelationArray2.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition2) {
                                            txtRelationArray2.get(j).setWidth(txtRelationArray2.get(finalPosition2).getWidth());
                                            txtRelationArray2.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray2.size(); j++) {
                                if (finalPosition2 < txtRelationArray2.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray2.get(j).setLayoutParams(params11);
//                                    }
                                }
                            }

                            if (finalPosition2 == 0){
                                if (txtRelationArray2.size()>1){
                                    txtRelationArray2.get(0).setWidth(txtRelationArray2.get(1).getWidth());
                                }
                            }else{
                                if (txtRelationArray2.size()>1){
                                    txtRelationArray2.get(finalPosition2).setWidth(txtRelationArray2.get(0).getWidth());
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent2.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent2.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent2.getHeight())) {

                            }else{

                                if (finalPosition2 == 0){
                                    if (txtRelationArray2.size()>1){
                                        txtRelationArray2.get(0).setWidth(txtRelationArray2.get(1).getWidth());
                                        txtRelationArray2.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray2.size()>1){
                                        txtRelationArray2.get(finalPosition2).setWidth(txtRelationArray2.get(0).getWidth());
                                        txtRelationArray2.get(finalPosition2).invalidate();
                                    }
                                }
                                if (familyPosition == 1){
                                    if (itemArrayList.get(i).getTitleArray().size() < 5){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1. linNamesParent2.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent2.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            viewHolder.linNamesParent3.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent3.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent3.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent3.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent3.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray3.size(); i++) {
                                    txtTestArray3.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray3.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray3.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray3.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 4) + 2, txtTestArray3.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray3.get(finalPosition3).setLayoutParams(params1);
                                txtRelationArray3.get(finalPosition3).invalidate();

                                for (int j = 0; j < txtRelationArray3.size(); j++) {
                                    if (finalPosition3 < txtRelationArray3.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray3.get(finalPosition3).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition3) {
                                            txtRelationArray3.get(j).setWidth(txtRelationArray3.get(finalPosition3).getWidth());
                                            txtRelationArray3.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray3.size(); j++) {
                                if (finalPosition3 < txtRelationArray3.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray3.get(finalPosition3).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray3.get(j).setLayoutParams(params11);
                                    txtRelationArray3.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition3 == 0){
                                if (txtRelationArray3.size()>1){
                                    txtRelationArray3.get(0).setWidth(txtRelationArray3.get(1).getWidth());
                                    txtRelationArray3.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray3.size()>1){
                                    txtRelationArray3.get(finalPosition3).setWidth(txtRelationArray3.get(0).getWidth());
                                    txtRelationArray3.get(finalPosition3).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent3.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent3.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent3.getHeight())) {

                            }else{

                                if (finalPosition3 == 0){
                                    if (txtRelationArray3.size()>1){
                                        txtRelationArray3.get(0).setWidth(txtRelationArray3.get(1).getWidth());
                                        txtRelationArray3.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray3.size()>1){
                                        txtRelationArray3.get(finalPosition3).setWidth(txtRelationArray3.get(0).getWidth());
                                        txtRelationArray3.get(finalPosition3).invalidate();
                                    }
                                }
                                if (familyPosition == 2){
                                    if (itemArrayList.get(i).getTitleArray().size() < 5){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1. linNamesParent3.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent3.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            viewHolder.linNamesParent4.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent4.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent4.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent4.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent4.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray4.size(); i++) {
                                    txtTestArray4.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray4.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray4.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray4.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 4) + 3, txtTestArray4.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray4.get(finalPosition4).setLayoutParams(params1);
                                txtRelationArray4.get(finalPosition4).invalidate();

                                for (int j = 0; j < txtRelationArray4.size(); j++) {
                                    if (finalPosition4 < txtRelationArray4.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray4.get(finalPosition4).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition4) {
                                            txtRelationArray4.get(j).setWidth(txtRelationArray4.get(finalPosition4).getWidth());
                                            txtRelationArray4.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray4.size(); j++) {
                                if (finalPosition4 < txtRelationArray4.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray4.get(finalPosition4).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray4.get(j).setLayoutParams(params11);
                                    txtRelationArray4.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition4 == 0){
                                if (txtRelationArray4.size()>1){
                                    txtRelationArray4.get(0).setWidth(txtRelationArray4.get(1).getWidth());
                                    txtRelationArray4.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray4.size()>1){
                                    txtRelationArray4.get(finalPosition4).setWidth(txtRelationArray4.get(0).getWidth());
                                    txtRelationArray4.get(finalPosition4).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent4.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent4.getChildAt(j).getHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent4.getHeight())) {

                            }else{
                                if (finalPosition4 == 0){
                                    if (txtRelationArray4.size()>1){
                                        txtRelationArray4.get(0).setWidth(txtRelationArray4.get(1).getWidth());
                                        txtRelationArray4.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray4.size()>1){
                                        txtRelationArray4.get(finalPosition4).setWidth(txtRelationArray4.get(0).getWidth());
                                        txtRelationArray4.get(finalPosition4).invalidate();
                                    }
                                }

                                if (familyPosition == 3){
                                    if (itemArrayList.get(i).getTitleArray().size() < 5){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1. linNamesParent4.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent4.getViewTreeObserver().removeOnPreDrawListener(this);
                    }
                    return true;
                }
            });

            if (arrowFlag != 1){
                viewHolder.imgArrow1.setVisibility(View.GONE);
                viewHolder.imgArrow2.setVisibility(View.GONE);
                viewHolder.imgArrow3.setVisibility(View.GONE);
                viewHolder.imgArrow4.setVisibility(View.GONE);
            }

        }else if (division == 6) {

            LinearLayout linVisible2 = (LinearLayout) view.findViewById(R.id.linear_six_list_item_visible2);
            LinearLayout linVisible3 = (LinearLayout) view.findViewById(R.id.linear_six_list_item_visible3);
            LinearLayout linVisible4 = (LinearLayout) view.findViewById(R.id.linear_six_list_item_visible4);
            LinearLayout linVisible5 = (LinearLayout) view.findViewById(R.id.linear_six_list_item_visible5);
            LinearLayout linVisible6 = (LinearLayout) view.findViewById(R.id.linear_six_list_item_visible6);

            imgVisible = (ImageView) view.findViewById(R.id.img_six_division_item_visible);


            viewHolder.txtTitle1.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName1.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc1.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc2.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc3.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder.txtCheckIn1.setTextColor(Color.parseColor(checkInColor));
            viewHolder.txtCheckOut1.setTextColor(Color.parseColor(checkOutColor));
            viewHolder.txtLocation1.setTextColor(Color.parseColor(locationColor));

            viewHolder.txtTitle2.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName2.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc4.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc5.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc6.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder.txtCheckIn2.setTextColor(Color.parseColor(checkInColor));
            viewHolder.txtCheckOut2.setTextColor(Color.parseColor(checkOutColor));
            viewHolder.txtLocation2.setTextColor(Color.parseColor(locationColor));

            viewHolder.txtTitle3.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName3.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc7.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc8.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc9.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder.txtCheckIn3.setTextColor(Color.parseColor(checkInColor));
            viewHolder.txtCheckOut3.setTextColor(Color.parseColor(checkOutColor));
            viewHolder.txtLocation3.setTextColor(Color.parseColor(locationColor));

            viewHolder.txtTitle4.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName4.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc10.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc11.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc12.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder.txtCheckIn4.setTextColor(Color.parseColor(checkInColor));
            viewHolder.txtCheckOut4.setTextColor(Color.parseColor(checkOutColor));
            viewHolder.txtLocation4.setTextColor(Color.parseColor(locationColor));

            viewHolder.txtTitle5.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName5.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc13.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc14.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc15.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder.txtCheckIn5.setTextColor(Color.parseColor(checkInColor));
            viewHolder.txtCheckOut5.setTextColor(Color.parseColor(checkOutColor));
            viewHolder.txtLocation5.setTextColor(Color.parseColor(locationColor));

            viewHolder.txtTitle6.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName6.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc16.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc17.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc18.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder.txtCheckIn6.setTextColor(Color.parseColor(checkInColor));
            viewHolder.txtCheckOut6.setTextColor(Color.parseColor(checkOutColor));
            viewHolder.txtLocation6.setTextColor(Color.parseColor(locationColor));

            viewHolder.linearBg = (LinearLayout) view.findViewById(R.id.linear_general_six_division_bg);
            viewHolder.linearTop = (LinearLayout) view.findViewById(R.id.linear_general_six_division_top);

            if (statusPlateNo.equals("46")) {
                viewHolder.linearBg.setBackgroundResource(R.drawable.img_division_six_top_black_42);
            } else if (statusPlateNo.equals("53")) {
                viewHolder.linearBg.setBackgroundResource(R.drawable.img_division_six_top_black_49);
            } else if (statusPlateNo.equals("59")) {
                viewHolder.linearBg.setBackgroundResource(R.drawable.img_division_six_top_black_55);
            } else if (statusPlateNo.equals("65")) {
                viewHolder.linearBg.setBackgroundResource(R.drawable.img_division_six_top_black_61);
            }
            if (bottomText.length() != 0) {
                if (statusPlateNo.equals("46")) {
                    viewHolder.linearBg.setBackgroundResource(R.drawable.img_division_six_bottom_black_42);
                } else if (statusPlateNo.equals("53")) {
                    viewHolder.linearBg.setBackgroundResource(R.drawable.img_division_six_bottom_black_49);
                } else if (statusPlateNo.equals("59")) {
                    viewHolder.linearBg.setBackgroundResource(R.drawable.img_division_six_bottom_black_55);
                } else if (statusPlateNo.equals("65")) {
                    viewHolder.linearBg.setBackgroundResource(R.drawable.img_division_six_bottom_black_61);
                }
            }

            if (bottomText.length() != 0) {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
                params.weight = 51;
                viewHolder.linearTop.setLayoutParams(params);
            } else {
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
                params.weight = 99;
                viewHolder.linearTop.setLayoutParams(params);
            }

            viewHolder.txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(i * 6));
            viewHolder.txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(i * 6));
            viewHolder.txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(i * 6));
            viewHolder.txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(i * 6));

            if (itemArrayList.get(i).getLocationArray().get((i * 6)).contains("\r\n")) {
                viewHolder.txtLocation1.setMaxLines(2);
            } else {
                viewHolder.txtLocation1.setMaxLines(1);
            }

            if (itemArrayList.get(i).getLocationArray().get((i * 6)).length() != 0) {
                viewHolder.txtLocation1.setText(itemArrayList.get(i).getLocationArray().get((i * 6)));
            } else {
                viewHolder.txtLocation1.setText("미 정");
            }

            if (itemArrayList.get(i).getImgPathArray().get(i * 6).length() != 0) {
                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(i * 6)).into(viewHolder.imgProfile1);
            } else {
//                viewHolder.imgProfile1.setImageResource(R.drawable.img_six_division_no_profile);
            }

//            if (itemArrayList.get(i).getReligionArray().get(i * 6).length() > 0) {
//                Glide.with(context).load(itemArrayList.get(i).getReligionArray().get(i * 6)).into(viewHolder.imgReligionBg1);
//            }

            if (divisionImg.length() != 0) {
                Glide.with(context).load(divisionImg).into(imgVisible);
            } else {
                imgVisible.setBackgroundResource(R.drawable.img_six_visible);
            }

            int relationLenth10 = 0;
            int position1 = 0;

//            gridLayout.setScrollContainer(false);
//            gridLayout.setRowCount(10);
//            gridLayout.setColumnCount(1);
//            gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);
//            gridLayout.setRowOrderPreserved(false);
//            gridLayout.setUseDefaultMargins(true);

            for (int a = 0; a < itemArrayList.get(i).getNamesArray().get(i * 6).size(); a++) {
                if (relationLenth10 < itemArrayList.get(i).getRelationArray().get(i * 6).get(a).length()) {
                    if (itemArrayList.get(i).getRelationArray().get(i * 6).get(a).contains(" ")) {
                        relationLenth10 = itemArrayList.get(i).getRelationArray().get(i * 6).get(a).length() - 1;
                    } else {
                        relationLenth10 = itemArrayList.get(i).getRelationArray().get(i * 6).get(a).length();
                    }
                    position1 = a;
                }
            }

            int relationLenth2 = 0;
            int position2 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 1) {
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 1).size(); a++) {
                    if (relationLenth2 < itemArrayList.get(i).getRelationArray().get((i * 6) + 1).get(a).length()) {
                        if (itemArrayList.get(i).getRelationArray().get((i * 6) + 1).get(a).contains(" ")) {
                            relationLenth2 = itemArrayList.get(i).getRelationArray().get((i * 6) + 1).get(a).length() - 1;
                        } else {
                            relationLenth2 = itemArrayList.get(i).getRelationArray().get((i * 6) + 1).get(a).length();
                        }
                        position2 = a;
                    }
                }
            }

            int relationLenth3 = 0;
            int position3 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 2) {
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 2).size(); a++) {
                    if (relationLenth3 < itemArrayList.get(i).getRelationArray().get((i * 6) + 2).get(a).length()) {
                        if (itemArrayList.get(i).getRelationArray().get((i * 6) + 2).get(a).contains(" ")) {
                            relationLenth3 = itemArrayList.get(i).getRelationArray().get((i * 6) + 2).get(a).length() - 1;
                        } else {
                            relationLenth3 = itemArrayList.get(i).getRelationArray().get((i * 6) + 2).get(a).length();
                        }
                        position3 = a;
                    }
                }
            }

            int relationLenth4 = 0;
            int position4 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 3) {
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 3).size(); a++) {
                    if (relationLenth4 < itemArrayList.get(i).getRelationArray().get((i * 6) + 3).get(a).length()) {
                        if (itemArrayList.get(i).getRelationArray().get((i * 6) + 3).get(a).contains(" ")) {
                            relationLenth4 = itemArrayList.get(i).getRelationArray().get((i * 6) + 3).get(a).length() - 1;
                        } else {
                            relationLenth4 = itemArrayList.get(i).getRelationArray().get((i * 6) + 3).get(a).length();
                        }
                        position4 = a;
                    }
                }
            }

            int relationLenth5 = 0;
            int position5 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 4) {
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 4).size(); a++) {
                    if (relationLenth5 < itemArrayList.get(i).getRelationArray().get((i * 6) + 4).get(a).length()) {
                        if (itemArrayList.get(i).getRelationArray().get((i * 6) + 4).get(a).contains(" ")) {
                            relationLenth5 = itemArrayList.get(i).getRelationArray().get((i * 6) + 4).get(a).length() - 1;
                        } else {
                            relationLenth5 = itemArrayList.get(i).getRelationArray().get((i * 6) + 4).get(a).length();
                        }
                        position5 = a;
                    }
                }
            }

            int relationLenth6 = 0;
            int position6 = 0;

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 5) {
                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 5).size(); a++) {
                    if (relationLenth6 < itemArrayList.get(i).getRelationArray().get((i * 6) + 5).get(a).length()) {
                        if (itemArrayList.get(i).getRelationArray().get((i * 6) + 5).get(a).contains(" ")) {
                            relationLenth6 = itemArrayList.get(i).getRelationArray().get((i * 6) + 5).get(a).length() - 1;
                        } else {
                            relationLenth6 = itemArrayList.get(i).getRelationArray().get((i * 6) + 5).get(a).length();
                        }
                        position6 = a;
                    }
                }
            } else {
                imgVisible.setVisibility(View.VISIBLE);
            }

            final int finalPosition1 = position1;
            final int finalPosition2 = position2;
            final int finalPosition3 = position3;
            final int finalPosition4 = position4;
            final int finalPosition5 = position5;
            final int finalPosition6 = position6;

            for (int a = 0; a < itemArrayList.get(i).getNamesArray().get(i * 6).size(); a++) {
                ListMake(a, itemArrayList.get(i).getRelationArray().get(i * 6).get(a), itemArrayList.get(i).getNamesArray().get(i * 6).get(a), 30f, viewHolder.linNamesParent1, familyColor, 1,txtRelationArray1,txtTestArray1,txtRelationNamesArray,txtNamesArray1,flowLayoutArrayList1);
            }

            if (itemArrayList.get(i).getArrowNoArray().get((i * 6)).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 6)).equals("")) {
                viewHolder.imgArrow1.setVisibility(View.GONE);
            } else {
                viewHolder.imgArrow1.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 6))));
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 1) {
                viewHolder.txtTitle2.setText(itemArrayList.get(i).getTitleArray().get((i * 6) + 1));
                viewHolder.txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get((i * 6) + 1));
//                txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get((i * 6) + 1));
                viewHolder.txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get((i * 6) + 1));
                viewHolder.txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get((i * 6) + 1));

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 1).contains("\r\n")) {
                    viewHolder.txtLocation2.setMaxLines(2);
                } else {
                    viewHolder.txtLocation2.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 1).length() != 0) {
                    viewHolder.txtLocation2.setText(itemArrayList.get(i).getLocationArray().get((i * 6) + 1));
                } else {
                    viewHolder.txtLocation2.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 6) + 1).length() != 0) {
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 6) + 1)).into(viewHolder.imgProfile2);
                } else {
//                    viewHolder.imgProfile2.setImageResource(R.drawable.img_six_division_no_profile);
                }

//                if (itemArrayList.get(i).getReligionArray().get((i * 6) + 1).length() > 0) {
//                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 6) + 1)).into(viewHolder.imgReligionBg2);
//                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 1).size(); a++) {
                    ListMake(a, itemArrayList.get(i).getRelationArray().get((i * 6) + 1).get(a), itemArrayList.get(i).getNamesArray().get((i * 6) + 1).get(a), 30f, viewHolder.linNamesParent2, familyColor, 2,txtRelationArray2,txtTestArray2,txtRelationNamesArray,txtNamesArray2,flowLayoutArrayList1);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 6) + 1).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 6) + 1).equals("")) {
                    viewHolder.imgArrow2.setVisibility(View.GONE);
                } else {
                    viewHolder.imgArrow2.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 6) + 1)));
                }

            } else {
                linVisible2.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 2) {
                viewHolder.txtTitle3.setText(itemArrayList.get(i).getTitleArray().get((i * 6) + 2));
                viewHolder.txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get((i * 6) + 2));
//                txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get((i * 6) + 2));
                viewHolder.txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get((i * 6) + 2));
                viewHolder.txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get((i * 6) + 2));

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 2).contains("\r\n")) {
                    viewHolder.txtLocation3.setMaxLines(2);
                } else {
                    viewHolder.txtLocation3.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 2).length() != 0) {
                    viewHolder.txtLocation3.setText(itemArrayList.get(i).getLocationArray().get((i * 6) + 2));
                } else {
                    viewHolder.txtLocation3.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 6) + 2).length() != 0) {
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 6) + 2)).into(viewHolder.imgProfile3);
                } else {
//                    viewHolder.imgProfile3.setImageResource(R.drawable.img_six_division_no_profile);
                }

//                if (itemArrayList.get(i).getReligionArray().get((i * 6) + 2).length() > 0) {
//                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 6) + 2)).into(viewHolder.imgReligionBg3);
//                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 2).size(); a++) {
                    ListMake(a, itemArrayList.get(i).getRelationArray().get((i * 6) + 2).get(a), itemArrayList.get(i).getNamesArray().get((i * 6) + 2).get(a), 30f, viewHolder.linNamesParent3, familyColor, 3,txtRelationArray3,txtTestArray3,txtRelationNamesArray,txtNamesArray3,flowLayoutArrayList1);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 6) + 2).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 6) + 2).equals("")) {
                    viewHolder.imgArrow3.setVisibility(View.GONE);
                } else {
                    viewHolder.imgArrow3.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 6) + 2)));
                }
            } else {
                linVisible3.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 3) {
                viewHolder.txtTitle4.setText(itemArrayList.get(i).getTitleArray().get((i * 6) + 3));
                viewHolder.txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get((i * 6) + 3));
//                txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get((i * 6) + 3));
                viewHolder.txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get((i * 6) + 3));
                viewHolder.txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get((i * 6) + 3));

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 3).contains("\r\n")) {
                    viewHolder.txtLocation4.setMaxLines(2);
                } else {
                    viewHolder.txtLocation4.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 3).length() != 0) {
                    viewHolder.txtLocation4.setText(itemArrayList.get(i).getLocationArray().get((i * 6) + 3));
                } else {
                    viewHolder.txtLocation4.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 6) + 3).length() != 0) {
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 6) + 3)).into(viewHolder.imgProfile4);
                } else {
//                    viewHolder.imgProfile4.setImageResource(R.drawable.img_six_division_no_profile);
                }

//                if (itemArrayList.get(i).getReligionArray().get((i * 6) + 3).length() > 0) {
//                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 6) + 3)).into(viewHolder.imgReligionBg4);
//                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 3).size(); a++) {
                    ListMake(a, itemArrayList.get(i).getRelationArray().get((i * 6) + 3).get(a), itemArrayList.get(i).getNamesArray().get((i * 6) + 3).get(a), 30f, viewHolder.linNamesParent4, familyColor, 4,txtRelationArray4,txtTestArray4,txtRelationNamesArray,txtNamesArray4,flowLayoutArrayList1);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 6) + 3).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 6) + 3).equals("")) {
                    viewHolder.imgArrow4.setVisibility(View.GONE);
                } else {
                    viewHolder.imgArrow4.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 6) + 3)));
                }
            } else {
                linVisible4.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 4) {
                viewHolder.txtTitle5.setText(itemArrayList.get(i).getTitleArray().get((i * 6) + 4));
                viewHolder.txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get((i * 6) + 4));
//                txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get((i * 6) + 4));
                viewHolder.txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get((i * 6) + 4));
                viewHolder.txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get((i * 6) + 4));

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 4).contains("\r\n")) {
                    viewHolder.txtLocation5.setMaxLines(2);
                } else {
                    viewHolder.txtLocation5.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 4).length() != 0) {
                    viewHolder.txtLocation5.setText(itemArrayList.get(i).getLocationArray().get((i * 6) + 4));
                } else {
                    viewHolder.txtLocation5.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 6) + 4).length() != 0) {
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 6) + 4)).into(viewHolder.imgProfile5);
                } else {
//                    viewHolder.imgProfile5.setImageResource(R.drawable.img_six_division_no_profile);
                }

//                if (itemArrayList.get(i).getReligionArray().get((i * 6) + 4).length() > 0) {
//                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 6) + 4)).into(viewHolder.imgReligionBg5);
//                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 4).size(); a++) {
                    ListMake(a, itemArrayList.get(i).getRelationArray().get((i * 6) + 4).get(a), itemArrayList.get(i).getNamesArray().get((i * 6) + 4).get(a), 30f, viewHolder.linNamesParent5, familyColor, 5,txtRelationArray5,txtTestArray5,txtRelationNamesArray,txtNamesArray5,flowLayoutArrayList1);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 6) + 4).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 6) + 4).equals("")) {
                    viewHolder.imgArrow5.setVisibility(View.GONE);
                } else {
                    viewHolder.imgArrow5.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 6) + 4)));
                }
            } else {
                linVisible5.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 6) + 5) {
                viewHolder.txtTitle6.setText(itemArrayList.get(i).getTitleArray().get((i * 6) + 5));
                viewHolder.txtDeceasedName6.setText(itemArrayList.get(i).getDeceasedArray().get((i * 6) + 5));
//                txtRelationship6.setText(itemArrayList.get(i).getRelationshipArray().get((i * 6) + 5));
                viewHolder.txtCheckIn6.setText(itemArrayList.get(i).getCheckInArray().get((i * 6) + 5));
                viewHolder.txtCheckOut6.setText(itemArrayList.get(i).getCheckOutArray().get((i * 6) + 5));

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 5).contains("\r\n")) {
                    viewHolder.txtLocation6.setMaxLines(2);
                } else {
                    viewHolder.txtLocation6.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 6) + 5).length() != 0) {
                    viewHolder.txtLocation6.setText(itemArrayList.get(i).getLocationArray().get((i * 6) + 5));
                } else {
                    viewHolder.txtLocation6.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 6) + 5).length() != 0) {
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 6) + 5)).into(viewHolder.imgProfile6);
                } else {
//                    viewHolder.imgProfile6.setImageResource(R.drawable.img_six_division_no_profile);
                }

//                if (itemArrayList.get(i).getReligionArray().get((i * 6) + 5).length() > 0) {
//                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 6) + 5)).into(viewHolder.imgReligionBg6);
//                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 6) + 5).size(); a++) {
                    ListMake(a, itemArrayList.get(i).getRelationArray().get((i * 6) + 5).get(a), itemArrayList.get(i).getNamesArray().get((i * 6) + 5).get(a), 30f, viewHolder.linNamesParent6, familyColor, 6,txtRelationArray6,txtTestArray6,txtRelationNamesArray,txtNamesArray6,flowLayoutArrayList1);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 6) + 5).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 6) + 5).equals("")) {
                    viewHolder.imgArrow6.setVisibility(View.GONE);
                } else {
                    viewHolder.imgArrow6.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 6) + 5)));
                }
            } else {
                linVisible6.setVisibility(View.GONE);
            }

            viewHolder.linNamesParent1.setVisibility(View.VISIBLE);
//            gridLayout.setVisibility(View.VISIBLE);

            viewHolder.txtEtc1.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc2.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc3.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtLocation1.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtCheckOut1.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc4.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc5.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc6.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtLocation2.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtCheckOut2.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc7.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc8.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc9.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtLocation3.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtCheckOut3.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc10.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc11.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc12.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtLocation4.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtCheckOut4.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc13.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc14.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc15.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtLocation5.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtCheckOut5.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc16.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc17.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtEtc18.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtLocation6.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);
            viewHolder.txtCheckOut6.setTextSize(TypedValue.COMPLEX_UNIT_PX, 25);

            ViewHolder finalViewHolder1 = viewHolder;

            viewHolder.linNamesParent1.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent1.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent1.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent1.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent1.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray1.size(); i++) {
                                    txtTestArray1.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray1.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 6), txtTestArray1.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray1.get(finalPosition1).setLayoutParams(params1);
                                txtRelationArray1.get(finalPosition1).invalidate();

                                for (int j = 0; j < txtRelationArray1.size(); j++) {
                                    if (finalPosition1 < txtRelationArray1.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition1) {
                                            txtRelationArray1.get(j).setWidth(txtRelationArray1.get(finalPosition1).getWidth());
                                            txtRelationArray1.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray1.size(); j++) {
                                if (finalPosition1 < txtRelationArray1.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray1.get(j).setLayoutParams(params11);
                                    txtRelationArray1.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition1 == 0){
                                if (txtRelationArray1.size()>1){
                                    txtRelationArray1.get(0).setWidth(txtRelationArray1.get(1).getWidth());
                                    txtRelationArray1.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray1.size()>1){
                                    txtRelationArray1.get(finalPosition1).setWidth(txtRelationArray1.get(0).getWidth());
                                    txtRelationArray1.get(finalPosition1).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent1.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent1.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent1.getHeight())) {

                            }else{

                                if (finalPosition1 == 0){
                                    if (txtRelationArray1.size()>1){
                                        txtRelationArray1.get(0).setWidth(txtRelationArray1.get(1).getWidth());
                                        txtRelationArray1.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray1.size()>1){
                                        txtRelationArray1.get(finalPosition1).setWidth(txtRelationArray1.get(0).getWidth());
                                        txtRelationArray1.get(finalPosition1).invalidate();
                                    }
                                }

                                if (familyPosition == 0){
                                    if (itemArrayList.get(i).getTitleArray().size() < 7){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1. linNamesParent1.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent1.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            viewHolder.linNamesParent2.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent2.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent2.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent2.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent2.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray2.size(); i++) {
                                    txtTestArray2.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray2.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 6) + 1, txtTestArray2.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray2.get(finalPosition2).setLayoutParams(params1);
                                txtRelationArray2.get(finalPosition2).invalidate();

                                for (int j = 0; j < txtRelationArray2.size(); j++) {
                                    if (finalPosition2 < txtRelationArray2.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition2) {
                                            txtRelationArray2.get(j).setWidth(txtRelationArray2.get(finalPosition2).getWidth());
                                            txtRelationArray2.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray2.size(); j++) {
                                if (finalPosition2 < txtRelationArray2.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray2.get(j).setLayoutParams(params11);
                                    txtRelationArray2.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition2 == 0){
                                if (txtRelationArray2.size()>1){
                                    txtRelationArray2.get(0).setWidth(txtRelationArray2.get(1).getWidth());
                                    txtRelationArray2.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray2.size()>1){
                                    txtRelationArray2.get(finalPosition2).setWidth(txtRelationArray2.get(0).getWidth());
                                    txtRelationArray2.get(finalPosition2).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent2.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent2.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent2.getHeight())) {

                            }else{

                                if (finalPosition2 == 0){
                                    if (txtRelationArray2.size()>1){
                                        txtRelationArray2.get(0).setWidth(txtRelationArray2.get(1).getWidth());
                                        txtRelationArray2.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray2.size()>1){
                                        txtRelationArray2.get(finalPosition2).setWidth(txtRelationArray2.get(0).getWidth());
                                        txtRelationArray2.get(finalPosition2).invalidate();
                                    }
                                }

                                if (familyPosition == 1){
                                    if (itemArrayList.get(i).getTitleArray().size() < 7){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1. linNamesParent2.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent2.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            viewHolder.linNamesParent3.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent3.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent3.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent3.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent3.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray3.size(); i++) {
                                    txtTestArray3.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray3.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray3.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray3.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 6) + 2, txtTestArray3.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray3.get(finalPosition3).setLayoutParams(params1);
                                txtRelationArray3.get(finalPosition3).invalidate();

                                for (int j = 0; j < txtRelationArray3.size(); j++) {
                                    if (finalPosition3 < txtRelationArray3.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray3.get(finalPosition3).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition3) {
                                            txtRelationArray3.get(j).setWidth(txtRelationArray3.get(finalPosition3).getWidth());
                                            txtRelationArray3.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray3.size(); j++) {
                                if (finalPosition3 < txtRelationArray3.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray3.get(finalPosition3).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray3.get(j).setLayoutParams(params11);
                                    txtRelationArray3.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition3 == 0){
                                if (txtRelationArray3.size()>1){
                                    txtRelationArray3.get(0).setWidth(txtRelationArray3.get(1).getWidth());
                                    txtRelationArray3.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray3.size()>1){
                                    txtRelationArray3.get(finalPosition3).setWidth(txtRelationArray3.get(0).getWidth());
                                    txtRelationArray3.get(finalPosition3).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent3.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent3.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent3.getHeight())) {

                            }else{

                                if (finalPosition3 == 0){
                                    if (txtRelationArray3.size()>1){
                                        txtRelationArray3.get(0).setWidth(txtRelationArray3.get(1).getWidth());
                                        txtRelationArray3.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray3.size()>1){
                                        txtRelationArray3.get(finalPosition3).setWidth(txtRelationArray3.get(0).getWidth());
                                        txtRelationArray3.get(finalPosition3).invalidate();
                                    }
                                }

                                if (familyPosition == 2){
                                    if (itemArrayList.get(i).getTitleArray().size() < 7){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1. linNamesParent3.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent3.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            viewHolder.linNamesParent4.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent4.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent4.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent4.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent4.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray4.size(); i++) {
                                    txtTestArray4.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray4.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray4.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray4.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 6) + 3, txtTestArray4.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray4.get(finalPosition4).setLayoutParams(params1);
                                txtRelationArray4.get(finalPosition4).invalidate();

                                for (int j = 0; j < txtRelationArray4.size(); j++) {
                                    if (finalPosition4 < txtRelationArray4.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray4.get(finalPosition4).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition4) {
                                            txtRelationArray4.get(j).setWidth(txtRelationArray4.get(finalPosition4).getWidth());
                                            txtRelationArray4.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray4.size(); j++) {
                                if (finalPosition4 < txtRelationArray4.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray4.get(finalPosition4).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray4.get(j).setLayoutParams(params11);
                                    txtRelationArray4.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition4 == 0){
                                if (txtRelationArray4.size()>1){
                                    txtRelationArray4.get(0).setWidth(txtRelationArray4.get(1).getWidth());
                                    txtRelationArray4.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray4.size()>1){
                                    txtRelationArray4.get(finalPosition4).setWidth(txtRelationArray4.get(0).getWidth());
                                    txtRelationArray4.get(finalPosition4).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent4.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent4.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent4.getHeight())) {

                            }else{

                                if (finalPosition4 == 0){
                                    if (txtRelationArray4.size()>1){
                                        txtRelationArray4.get(0).setWidth(txtRelationArray4.get(1).getWidth());
                                        txtRelationArray4.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray4.size()>1){
                                        txtRelationArray4.get(finalPosition4).setWidth(txtRelationArray4.get(0).getWidth());
                                        txtRelationArray4.get(finalPosition4).invalidate();
                                    }
                                }

                                if (familyPosition == 3){
                                    if (itemArrayList.get(i).getTitleArray().size() < 7){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1. linNamesParent4.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent4.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            viewHolder.linNamesParent5.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent5.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent5.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent5.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent5.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray5.size(); i++) {
                                    txtTestArray5.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray5.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray5.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray5.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 6) + 4, txtTestArray5.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray5.get(finalPosition5).setLayoutParams(params1);
                                txtRelationArray5.get(finalPosition5).invalidate();

                                for (int j = 0; j < txtRelationArray5.size(); j++) {
                                    if (finalPosition5 < txtRelationArray5.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray5.get(finalPosition5).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition5) {
                                            txtRelationArray5.get(j).setWidth(txtRelationArray5.get(finalPosition5).getWidth());
                                            txtRelationArray5.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray5.size(); j++) {
                                if (finalPosition5 < txtRelationArray5.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray5.get(finalPosition5).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray5.get(j).setLayoutParams(params11);
                                    txtRelationArray5.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition5 == 0){
                                if (txtRelationArray5.size()>1){
                                    txtRelationArray5.get(0).setWidth(txtRelationArray5.get(1).getWidth());
                                    txtRelationArray5.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray5.size()>1){
                                    txtRelationArray5.get(finalPosition5).setWidth(txtRelationArray5.get(0).getWidth());
                                    txtRelationArray5.get(finalPosition5).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent5.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent5.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent5.getHeight())) {

                            }else{

                                if (finalPosition5 == 0){
                                    if (txtRelationArray5.size()>1){
                                        txtRelationArray5.get(0).setWidth(txtRelationArray5.get(1).getWidth());
                                        txtRelationArray5.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray5.size()>1){
                                        txtRelationArray5.get(finalPosition5).setWidth(txtRelationArray5.get(0).getWidth());
                                        txtRelationArray5.get(finalPosition5).invalidate();
                                    }
                                }

                                if (familyPosition == 4){
                                    if (itemArrayList.get(i).getTitleArray().size() < 7){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1. linNamesParent5.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent5.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            viewHolder.linNamesParent6.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent6.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent6.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent6.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent6.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray6.size(); i++) {
                                    txtTestArray6.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray6.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray6.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray6.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 6) + 5, txtTestArray6.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray6.get(finalPosition6).setLayoutParams(params1);
                                txtRelationArray6.get(finalPosition6).invalidate();

                                for (int j = 0; j < txtRelationArray6.size(); j++) {
                                    if (finalPosition6 < txtRelationArray6.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray6.get(finalPosition6).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition6) {
                                            txtRelationArray6.get(j).setWidth(txtRelationArray6.get(finalPosition6).getWidth());
                                            txtRelationArray6.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray6.size(); j++) {
                                if (finalPosition6 < txtRelationArray6.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray6.get(finalPosition6).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray6.get(j).setLayoutParams(params11);
                                    txtRelationArray6.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition6 == 0){
                                if (txtRelationArray6.size()>1){
                                    txtRelationArray6.get(0).setWidth(txtRelationArray6.get(1).getWidth());
                                    txtRelationArray6.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray6.size()>1){
                                    txtRelationArray6.get(finalPosition6).setWidth(txtRelationArray6.get(0).getWidth());
                                    txtRelationArray6.get(finalPosition6).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent6.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent6.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent6.getHeight())) {

                            }else{

                                if (finalPosition6 == 0){
                                    if (txtRelationArray6.size()>1){
                                        txtRelationArray6.get(0).setWidth(txtRelationArray6.get(1).getWidth());
                                        txtRelationArray6.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray6.size()>1){
                                        txtRelationArray6.get(finalPosition6).setWidth(txtRelationArray6.get(0).getWidth());
                                        txtRelationArray6.get(finalPosition6).invalidate();
                                    }
                                }

                                if (familyPosition == 5){
                                    if (itemArrayList.get(i).getTitleArray().size() < 7){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1. linNamesParent6.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent6.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            if (arrowFlag != 1){
                viewHolder.imgArrow1.setVisibility(View.GONE);
                viewHolder.imgArrow2.setVisibility(View.GONE);
                viewHolder.imgArrow3.setVisibility(View.GONE);
                viewHolder. imgArrow4.setVisibility(View.GONE);
                viewHolder. imgArrow5.setVisibility(View.GONE);
                viewHolder. imgArrow6.setVisibility(View.GONE);
            }
        }else if (division == 8){


            final LinearLayout linVisible2 = (LinearLayout)view.findViewById(R.id.linear_eight_visible2);
            final LinearLayout linVisible3 = (LinearLayout)view.findViewById(R.id.linear_eight_visible3);
            final LinearLayout linVisible4 = (LinearLayout)view.findViewById(R.id.linear_eight_visible4);
            final LinearLayout linVisible5 = (LinearLayout)view.findViewById(R.id.linear_eight_visible5);
            final LinearLayout linVisible6 = (LinearLayout)view.findViewById(R.id.linear_eight_visible6);
            final LinearLayout linVisible7 = (LinearLayout)view.findViewById(R.id.linear_eight_visible7);
            final LinearLayout linVisible8 = (LinearLayout)view.findViewById(R.id.linear_eight_visible8);

             imgVisible = (ImageView)view.findViewById(R.id.img_eight_division_item_visible);


            viewHolder. txtTitle1.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName1.setTextColor(Color.parseColor(deceasedColor));
            viewHolder. txtEtc1.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc2.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc3.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder. txtCheckIn1.setTextColor(Color.parseColor(checkInColor));
            viewHolder. txtCheckOut1.setTextColor(Color.parseColor(checkOutColor));
            viewHolder. txtLocation1.setTextColor(Color.parseColor(locationColor));

            viewHolder.txtTitle2.setTextColor(Color.parseColor(roomNameColor));
            viewHolder. txtDeceasedName2.setTextColor(Color.parseColor(deceasedColor));
            viewHolder. txtEtc4.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder. txtEtc5.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder. txtEtc6.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder.  txtCheckIn2.setTextColor(Color.parseColor(checkInColor));
            viewHolder. txtCheckOut2.setTextColor(Color.parseColor(checkOutColor));
            viewHolder. txtLocation2.setTextColor(Color.parseColor(locationColor));

            viewHolder.txtTitle3.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName3.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc7.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc8.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc9.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder. txtCheckIn3.setTextColor(Color.parseColor(checkInColor));
            viewHolder.txtCheckOut3.setTextColor(Color.parseColor(checkOutColor));
            viewHolder. txtLocation3.setTextColor(Color.parseColor(locationColor));

            viewHolder.txtTitle4.setTextColor(Color.parseColor(roomNameColor));
            viewHolder. txtDeceasedName4.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc10.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder. txtEtc11.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder. txtEtc12.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder. txtCheckIn4.setTextColor(Color.parseColor(checkInColor));
            viewHolder. txtCheckOut4.setTextColor(Color.parseColor(checkOutColor));
            viewHolder.txtLocation4.setTextColor(Color.parseColor(locationColor));

            viewHolder. txtTitle5.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName5.setTextColor(Color.parseColor(deceasedColor));
            viewHolder. txtEtc13.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder. txtEtc14.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.  txtEtc15.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder. txtCheckIn5.setTextColor(Color.parseColor(checkInColor));
            viewHolder. txtCheckOut5.setTextColor(Color.parseColor(checkOutColor));
            viewHolder. txtLocation5.setTextColor(Color.parseColor(locationColor));

            viewHolder.txtTitle6.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName6.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc16.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder. txtEtc17.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc18.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder. txtCheckIn6.setTextColor(Color.parseColor(checkInColor));
            viewHolder.txtCheckOut6.setTextColor(Color.parseColor(checkOutColor));
            viewHolder. txtLocation6.setTextColor(Color.parseColor(locationColor));

            viewHolder.txtTitle7.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName7.setTextColor(Color.parseColor(deceasedColor));
            viewHolder. txtEtc19.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc20.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder.txtEtc21.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder.txtCheckIn7.setTextColor(Color.parseColor(checkInColor));
            viewHolder. txtCheckOut7.setTextColor(Color.parseColor(checkOutColor));
            viewHolder. txtLocation7.setTextColor(Color.parseColor(locationColor));

            viewHolder.txtTitle8.setTextColor(Color.parseColor(roomNameColor));
            viewHolder.txtDeceasedName8.setTextColor(Color.parseColor(deceasedColor));
            viewHolder.txtEtc22.setTextColor(Color.parseColor(checkInTitleColor));
            viewHolder.txtEtc23.setTextColor(Color.parseColor(checkOutTitleColor));
            viewHolder. txtEtc24.setTextColor(Color.parseColor(locationTitleColor));
            viewHolder.txtCheckIn8.setTextColor(Color.parseColor(checkInColor));
            viewHolder.txtCheckOut8.setTextColor(Color.parseColor(checkOutColor));
            viewHolder.txtLocation8.setTextColor(Color.parseColor(locationColor));

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

            viewHolder.txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(i * 8));
            viewHolder.txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(i * 8));
            viewHolder. txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(i * 8));
            viewHolder.txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(i * 8));

            if (itemArrayList.get(i).getLocationArray().get((i * 8)).contains("\r\n")){
                viewHolder. txtLocation1.setMaxLines(2);
            }else{
                viewHolder. txtLocation1.setMaxLines(1);
            }

            if (itemArrayList.get(i).getLocationArray().get((i * 8)).length() != 0){
                viewHolder. txtLocation1.setText(itemArrayList.get(i).getLocationArray().get((i * 8)));
            }else{
                viewHolder. txtLocation1.setText("미 정");
            }

            if (itemArrayList.get(i).getImgPathArray().get(i * 8).length() != 0){
                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(i * 8)).into(viewHolder.imgProfile1);
            }else{
//                viewHolder.imgProfile1.setImageResource(R.drawable.img_eight_division_no_profile);
            }

//            if (itemArrayList.get(i).getReligionArray().get(i * 8).length() > 0){
//                Glide.with(context).load(itemArrayList.get(i).getReligionArray().get(i * 8)).into(viewHolder.imgReligionBg1);
//            }

            if (divisionImg.length() != 0){
                Glide.with(context).load(divisionImg).into(imgVisible);
            }else{
                imgVisible.setBackgroundResource(R.drawable.img_eight_visible);
            }

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
                    ListMake(a,itemArrayList.get(i).getRelationArray().get(i * 8).get(a),itemArrayList.get(i).getNamesArray().get(i * 8).get(a), 30f,viewHolder.linNamesParent1,familyColor,1,txtRelationArray1,txtTestArray1,txtRelationNamesArray,txtNamesArray1);
            }

            if (itemArrayList.get(i).getArrowNoArray().get((i * 8)).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 8)).equals("")){
                viewHolder. imgArrow1.setVisibility(View.GONE);
            }else{
                viewHolder. imgArrow1.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 8))));
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 1){
                viewHolder.txtTitle2.setText(itemArrayList.get(i).getTitleArray().get((i * 8) + 1));
                viewHolder. txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get((i * 8) + 1));
//                txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get((i * 8) + 1));
                viewHolder. txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get((i * 8) + 1));
                viewHolder. txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get((i * 8) + 1));

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 1).contains("\r\n")){
                    viewHolder. txtLocation2.setMaxLines(2);
                }else{
                    viewHolder. txtLocation2.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 1).length() != 0){
                    viewHolder.txtLocation2.setText(itemArrayList.get(i).getLocationArray().get((i * 8) + 1));
                }else{
                    viewHolder.txtLocation2.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 8) + 1).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 8) + 1)).into(viewHolder.imgProfile2);
                }else{
//                    viewHolder.imgProfile2.setImageResource(R.drawable.img_eight_division_no_profile);
                }

//                if (itemArrayList.get(i).getReligionArray().get((i * 8) + 1).length() > 0){
//                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 8) + 1)).into(viewHolder.imgReligionBg2);
//                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 1).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 8) + 1).get(a),itemArrayList.get(i).getNamesArray().get((i * 8) + 1).get(a),30f,viewHolder.linNamesParent2,familyColor,2,txtRelationArray2,txtTestArray2,txtRelationNamesArray,txtNamesArray2);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 8) + 1).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 8) + 1).equals("")){
                    viewHolder.imgArrow2.setVisibility(View.GONE);
                }else{
                    viewHolder. imgArrow2.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 8) + 1)));
                }

            }else{
                linVisible2.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 2){
                viewHolder. txtTitle3.setText(itemArrayList.get(i).getTitleArray().get((i * 8) + 2));
                viewHolder. txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get((i * 8) + 2));
//                txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get((i * 8) + 2));
                viewHolder. txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get((i * 8) + 2));
                viewHolder.txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get((i * 8) + 2));

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 2).contains("\r\n")){
                    viewHolder.txtLocation3.setMaxLines(2);
                }else{
                    viewHolder.txtLocation3.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 2).length() != 0){
                    viewHolder. txtLocation3.setText(itemArrayList.get(i).getLocationArray().get((i * 8) + 2));
                }else{
                    viewHolder.txtLocation3.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 8) + 2).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 8) + 2)).into(viewHolder.imgProfile3);
                }else{
//                    viewHolder.imgProfile3.setImageResource(R.drawable.img_eight_division_no_profile);
                }

//                if (itemArrayList.get(i).getReligionArray().get((i * 8) + 2).length() > 0){
//                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 8) + 2)).into(viewHolder.imgReligionBg3);
//                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 2).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 8) + 2).get(a),itemArrayList.get(i).getNamesArray().get((i * 8) + 2).get(a),30f,viewHolder.linNamesParent3,familyColor,3,txtRelationArray3,txtTestArray3,txtRelationNamesArray,txtNamesArray3);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 8) + 2).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 8) + 2).equals("")){
                    viewHolder. imgArrow3.setVisibility(View.GONE);
                }else{
                    viewHolder. imgArrow3.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 8) + 2)));
                }
            }else{
                linVisible3.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 3){
                viewHolder.txtTitle4.setText(itemArrayList.get(i).getTitleArray().get((i * 8) + 3));
                viewHolder.txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get((i * 8) + 3));
//                txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get((i * 8) + 3));
                viewHolder.txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get((i * 8) + 3));
                viewHolder.txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get((i * 8) + 3));

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 3).contains("\r\n")){
                    viewHolder.txtLocation4.setMaxLines(2);
                }else{
                    viewHolder.txtLocation4.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 3).length() != 0){
                    viewHolder.txtLocation4.setText(itemArrayList.get(i).getLocationArray().get((i * 8) + 3));
                }else{
                    viewHolder.txtLocation4.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 8) + 3).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 8) + 3)).into(viewHolder.imgProfile4);
                }else{
//                    viewHolder.imgProfile4.setImageResource(R.drawable.img_eight_division_no_profile);
                }


//                if (itemArrayList.get(i).getReligionArray().get((i * 8) + 3).length() > 0){
//                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 8) + 3)).into(viewHolder.imgReligionBg4);
//                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 3).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 8) + 3).get(a),itemArrayList.get(i).getNamesArray().get((i * 8) + 3).get(a),30f,viewHolder.linNamesParent4,familyColor,4,txtRelationArray4,txtTestArray4,txtRelationNamesArray,txtNamesArray4);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 8) + 3).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 8) + 3).equals("")){
                    viewHolder.imgArrow4.setVisibility(View.GONE);
                }else{
                    viewHolder.imgArrow4.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 8) + 3)));
                }
            }else{
                linVisible4.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 4){
                viewHolder.txtTitle5.setText(itemArrayList.get(i).getTitleArray().get((i * 8) + 4));
                viewHolder.txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get((i * 8) + 4));
//                txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get((i * 8) + 4));
                viewHolder. txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get((i * 8) + 4));
                viewHolder.txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get((i * 8) + 4));

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 4).contains("\r\n")){
                    viewHolder.txtLocation5.setMaxLines(2);
                }else{
                    viewHolder.txtLocation5.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 4).length() != 0){
                    viewHolder.txtLocation5.setText(itemArrayList.get(i).getLocationArray().get((i * 8) + 4));
                }else{
                    viewHolder.txtLocation5.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 8) + 4).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 8) + 4)).into(viewHolder.imgProfile5);
                }else{
//                    viewHolder.imgProfile5.setImageResource(R.drawable.img_eight_division_no_profile);
                }


//                if (itemArrayList.get(i).getReligionArray().get((i * 8) + 4).length() > 0){
//                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 8) + 4)).into(viewHolder.imgReligionBg5);
//                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 4).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 8) + 4).get(a),itemArrayList.get(i).getNamesArray().get((i * 8) + 4).get(a),30f,viewHolder.linNamesParent5,familyColor,5,txtRelationArray5,txtTestArray5,txtRelationNamesArray,txtNamesArray5);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 8) + 4).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 8) + 4).equals("")){
                    viewHolder.imgArrow5.setVisibility(View.GONE);
                }else{
                    viewHolder.imgArrow5.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 8) + 4)));
                }
            }else{
                linVisible5.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 5){
                viewHolder.txtTitle6.setText(itemArrayList.get(i).getTitleArray().get((i * 8) + 5));
                viewHolder. txtDeceasedName6.setText(itemArrayList.get(i).getDeceasedArray().get((i * 8) + 5));
//                txtRelationship6.setText(itemArrayList.get(i).getRelationshipArray().get((i * 8) + 5));
                viewHolder.txtCheckIn6.setText(itemArrayList.get(i).getCheckInArray().get((i * 8) + 5));
                viewHolder.txtCheckOut6.setText(itemArrayList.get(i).getCheckOutArray().get((i * 8) + 5));

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 5).contains("\r\n")){
                    viewHolder.txtLocation6.setMaxLines(2);
                }else{
                    viewHolder.txtLocation6.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 5).length() != 0){
                    viewHolder.txtLocation6.setText(itemArrayList.get(i).getLocationArray().get((i * 8) + 5));
                }else{
                    viewHolder.txtLocation6.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 8) + 5).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 8) + 5)).into(viewHolder.imgProfile6);
                }else{
//                    viewHolder.imgProfile6.setImageResource(R.drawable.img_eight_division_no_profile);
                }


//                if (itemArrayList.get(i).getReligionArray().get((i * 8) + 5).length() > 0){
//                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 8) + 5)).into(viewHolder.imgReligionBg6);
//                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 5).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 8) + 5).get(a),itemArrayList.get(i).getNamesArray().get((i * 8) + 5).get(a),30f,viewHolder.linNamesParent6,familyColor,6,txtRelationArray6,txtTestArray6,txtRelationNamesArray,txtNamesArray6);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 8) + 5).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 8) + 5).equals("")){
                    viewHolder.imgArrow6.setVisibility(View.GONE);
                }else{
                    viewHolder.imgArrow6.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 8) + 5)));
                }
            }else{
                linVisible6.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 6){
                viewHolder.txtTitle7.setText(itemArrayList.get(i).getTitleArray().get((i * 8) + 6));
                viewHolder.txtDeceasedName7.setText(itemArrayList.get(i).getDeceasedArray().get((i * 8) + 6));
//                txtRelationship7.setText(itemArrayList.get(i).getRelationshipArray().get((i * 8) + 6));
                viewHolder.txtCheckIn7.setText(itemArrayList.get(i).getCheckInArray().get((i * 8) + 6));
                viewHolder.txtCheckOut7.setText(itemArrayList.get(i).getCheckOutArray().get((i * 8) + 6));

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 6).contains("\r\n")){
                    viewHolder.txtLocation7.setMaxLines(2);
                }else{
                    viewHolder.txtLocation7.setMaxLines(1);
                }

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 6).length() != 0){
                    viewHolder.txtLocation7.setText(itemArrayList.get(i).getLocationArray().get((i * 8) + 6));
                }else{
                    viewHolder. txtLocation7.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 8) + 6).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 8) + 6)).into(viewHolder.imgProfile7);
                }else{
//                    viewHolder.imgProfile7.setImageResource(R.drawable.img_eight_division_no_profile);
                }


//                if (itemArrayList.get(i).getReligionArray().get((i * 8) + 6).length() > 0){
//                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 8) + 6)).into(viewHolder.imgReligionBg7);
//                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 6).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 8) + 6).get(a),itemArrayList.get(i).getNamesArray().get((i * 8) + 6).get(a),30f,viewHolder.linNamesParent7,familyColor,7,txtRelationArray7,txtTestArray7,txtRelationNamesArray,txtNamesArray7);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 8) + 6).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 8) + 6).equals("")){
                    viewHolder.imgArrow7.setVisibility(View.GONE);
                }else{
                    viewHolder.imgArrow7.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 8) + 6)));
                }
            }else{
                linVisible7.setVisibility(View.GONE);
            }

            if (itemArrayList.get(i).getTitleArray().size() > (i * 8) + 7){
                viewHolder.txtTitle8.setText(itemArrayList.get(i).getTitleArray().get((i * 8) + 7));
                viewHolder.txtDeceasedName8.setText(itemArrayList.get(i).getDeceasedArray().get((i * 8) + 7));
//                txtRelationship8.setText(itemArrayList.get(i).getRelationshipArray().get((i * 8) + 7));
                viewHolder.txtCheckIn8.setText(itemArrayList.get(i).getCheckInArray().get((i * 8) + 7));
                viewHolder.txtCheckOut8.setText(itemArrayList.get(i).getCheckOutArray().get((i * 8) + 7));

                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 7).contains("\r\n")){
                    viewHolder.txtLocation8.setMaxLines(2);
                }else{
                    viewHolder.txtLocation8.setMaxLines(1);
                }
                if (itemArrayList.get(i).getLocationArray().get((i * 8) + 7).length() != 0){
                    viewHolder.txtLocation8.setText(itemArrayList.get(i).getLocationArray().get((i * 8) + 7));
                }else{
                    viewHolder.txtLocation8.setText("미 정");
                }

                if (itemArrayList.get(i).getImgPathArray().get((i * 8) + 7).length() != 0){
                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get((i * 8) + 7)).into(viewHolder.imgProfile8);
                }else{
//                    viewHolder.imgProfile8.setImageResource(R.drawable.img_eight_division_no_profile);
                }

//                if (itemArrayList.get(i).getReligionArray().get((i * 8) + 7).length() > 0){
//                    Glide.with(context).load(itemArrayList.get(i).getReligionArray().get((i * 8) + 7)).into(viewHolder.imgReligionBg8);
//                }

                for (int a = 0; a < itemArrayList.get(i).getNamesArray().get((i * 8) + 7).size(); a++){
                    ListMake(a,itemArrayList.get(i).getRelationArray().get((i * 8) + 7).get(a),itemArrayList.get(i).getNamesArray().get((i * 8) + 7).get(a),30f,viewHolder.linNamesParent8,familyColor,8,txtRelationArray8,txtTestArray8,txtRelationNamesArray,txtNamesArray8);
                }

                if (itemArrayList.get(i).getArrowNoArray().get((i * 8) + 7).equals("0") || itemArrayList.get(i).getArrowNoArray().get((i * 8) + 7).equals("")){
                    viewHolder.imgArrow8.setVisibility(View.GONE);
                }else{
                    viewHolder.imgArrow8.setImageResource(arrowNoSelect(itemArrayList.get(i).getArrowNoArray().get((i * 8) + 7)));
                }
            }else{
                linVisible8.setVisibility(View.GONE);
                imgVisible.setVisibility(View.VISIBLE);
            }

            viewHolder. txtEtc1.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder.txtEtc2.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder. txtEtc3.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder.txtLocation1.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder.txtCheckOut1.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder. txtEtc4.setTextSize(TypedValue.COMPLEX_UNIT_PX,viewHolder. txtCheckIn1.getTextSize());
            viewHolder. txtEtc5.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder.txtEtc6.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder. txtLocation2.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder.txtCheckOut2.setTextSize(TypedValue.COMPLEX_UNIT_PX,viewHolder. txtCheckIn1.getTextSize());
            viewHolder.txtEtc7.setTextSize(TypedValue.COMPLEX_UNIT_PX,viewHolder. txtCheckIn1.getTextSize());
            viewHolder. txtEtc8.setTextSize(TypedValue.COMPLEX_UNIT_PX,viewHolder. txtCheckIn1.getTextSize());
            viewHolder. txtEtc9.setTextSize(TypedValue.COMPLEX_UNIT_PX,viewHolder. txtCheckIn1.getTextSize());
            viewHolder. txtLocation3.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder. txtCheckOut3.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder. txtEtc10.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder. txtEtc11.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder.txtEtc12.setTextSize(TypedValue.COMPLEX_UNIT_PX,viewHolder. txtCheckIn1.getTextSize());
            viewHolder. txtLocation4.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder. txtCheckOut4.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder. txtEtc13.setTextSize(TypedValue.COMPLEX_UNIT_PX,viewHolder. txtCheckIn1.getTextSize());
            viewHolder. txtEtc14.setTextSize(TypedValue.COMPLEX_UNIT_PX,viewHolder. txtCheckIn1.getTextSize());
            viewHolder. txtEtc15.setTextSize(TypedValue.COMPLEX_UNIT_PX,viewHolder.txtCheckIn1.getTextSize());
            viewHolder. txtLocation5.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder.  txtCheckOut5.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder.txtEtc16.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder. txtEtc17.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder. txtEtc18.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder. txtLocation6.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder. txtCheckOut6.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder.txtEtc19.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder.txtEtc20.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder.txtEtc21.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder.txtLocation7.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder.txtCheckOut7.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder. txtEtc22.setTextSize(TypedValue.COMPLEX_UNIT_PX,viewHolder. txtCheckIn1.getTextSize());
            viewHolder. txtEtc23.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());
            viewHolder.txtEtc24.setTextSize(TypedValue.COMPLEX_UNIT_PX,viewHolder. txtCheckIn1.getTextSize());
            viewHolder.txtLocation8.setTextSize(TypedValue.COMPLEX_UNIT_PX,viewHolder. txtCheckIn1.getTextSize());
            viewHolder. txtCheckOut8.setTextSize(TypedValue.COMPLEX_UNIT_PX, viewHolder.txtCheckIn1.getTextSize());

            ViewHolder finalViewHolder1 = viewHolder;

            viewHolder.linNamesParent1.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent1.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent1.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent1.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent1.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray1.size(); i++) {
                                    txtTestArray1.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray1.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray1.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 8), txtTestArray1.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray1.get(finalPosition1).setLayoutParams(params1);
                                txtRelationArray1.get(finalPosition1).invalidate();

                                for (int j = 0; j < txtRelationArray1.size(); j++) {
                                    if (finalPosition1 < txtRelationArray1.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition1) {
                                            txtRelationArray1.get(j).setWidth(txtRelationArray1.get(finalPosition1).getWidth());
                                            txtRelationArray1.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray1.size(); j++) {
                                if (finalPosition1 < txtRelationArray1.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray1.get(finalPosition1).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray1.get(j).setLayoutParams(params11);
                                    txtRelationArray1.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition1 == 0){
                                if (txtRelationArray1.size()>1){
                                    txtRelationArray1.get(0).setWidth(txtRelationArray1.get(1).getWidth());
                                    txtRelationArray1.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray1.size()>1){
                                    txtRelationArray1.get(finalPosition1).setWidth(txtRelationArray1.get(0).getWidth());
                                    txtRelationArray1.get(finalPosition1).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent1.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent1.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent1.getHeight())) {

                            }else{

                                if (finalPosition1 == 0){
                                    if (txtRelationArray1.size()>1){
                                        txtRelationArray1.get(0).setWidth(txtRelationArray1.get(1).getWidth());
                                        txtRelationArray1.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray1.size()>1){
                                        txtRelationArray1.get(finalPosition1).setWidth(txtRelationArray1.get(0).getWidth());
                                        txtRelationArray1.get(finalPosition1).invalidate();
                                    }
                                }

                                if (familyPosition == 0){
                                    if (itemArrayList.get(i).getTitleArray().size() < 9){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }

                                finalViewHolder1. linNamesParent1.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent1.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            viewHolder.linNamesParent2.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent2.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent2.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent2.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent2.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray2.size(); i++) {
                                    txtTestArray2.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray2.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray2.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 8) + 1, txtTestArray2.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray2.get(finalPosition2).setLayoutParams(params1);
                                txtRelationArray2.get(finalPosition2).invalidate();

                                for (int j = 0; j < txtRelationArray2.size(); j++) {
                                    if (finalPosition2 < txtRelationArray2.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition2) {
                                            txtRelationArray2.get(j).setWidth(txtRelationArray2.get(finalPosition2).getWidth());
                                            txtRelationArray2.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray2.size(); j++) {
                                if (finalPosition2 < txtRelationArray2.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray2.get(finalPosition2).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray2.get(j).setLayoutParams(params11);
                                    txtRelationArray2.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition2 == 0){
                                if (txtRelationArray2.size()>1){
                                    txtRelationArray2.get(0).setWidth(txtRelationArray2.get(1).getWidth());
                                    txtRelationArray2.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray2.size()>1){
                                    txtRelationArray2.get(finalPosition2).setWidth(txtRelationArray2.get(0).getWidth());
                                    txtRelationArray2.get(finalPosition2).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent2.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent2.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent2.getHeight())) {

                            }else{

                                if (finalPosition2 == 0){
                                    if (txtRelationArray2.size()>1){
                                        txtRelationArray2.get(0).setWidth(txtRelationArray2.get(1).getWidth());
                                        txtRelationArray2.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray2.size()>1){
                                        txtRelationArray2.get(finalPosition2).setWidth(txtRelationArray2.get(0).getWidth());
                                        txtRelationArray2.get(finalPosition2).invalidate();
                                    }
                                }

                                if (familyPosition == 1){
                                    if (itemArrayList.get(i).getTitleArray().size() < 9){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }

                                finalViewHolder1. linNamesParent2.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent2.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            viewHolder.linNamesParent3.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent3.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent3.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent3.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent3.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray3.size(); i++) {
                                    txtTestArray3.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray3.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray3.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray3.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 8) + 2, txtTestArray3.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray3.get(finalPosition3).setLayoutParams(params1);
                                txtRelationArray3.get(finalPosition3).invalidate();

                                for (int j = 0; j < txtRelationArray3.size(); j++) {
                                    if (finalPosition3 < txtRelationArray3.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray3.get(finalPosition3).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition3) {
                                            txtRelationArray3.get(j).setWidth(txtRelationArray3.get(finalPosition3).getWidth());
                                            txtRelationArray3.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray3.size(); j++) {
                                if (finalPosition3 < txtRelationArray3.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray3.get(finalPosition3).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray3.get(j).setLayoutParams(params11);
                                    txtRelationArray3.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition3 == 0){
                                if (txtRelationArray3.size()>1){
                                    txtRelationArray3.get(0).setWidth(txtRelationArray3.get(1).getWidth());
                                    txtRelationArray3.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray3.size()>1){
                                    txtRelationArray3.get(finalPosition3).setWidth(txtRelationArray3.get(0).getWidth());
                                    txtRelationArray3.get(finalPosition3).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent3.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent3.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent3.getHeight())) {

                            }else{

                                if (finalPosition3 == 0){
                                    if (txtRelationArray3.size()>1){
                                        txtRelationArray3.get(0).setWidth(txtRelationArray3.get(1).getWidth());
                                        txtRelationArray3.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray3.size()>1){
                                        txtRelationArray3.get(finalPosition3).setWidth(txtRelationArray3.get(0).getWidth());
                                        txtRelationArray3.get(finalPosition3).invalidate();
                                    }
                                }

                                if (familyPosition == 2){
                                    if (itemArrayList.get(i).getTitleArray().size() < 9){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1. linNamesParent3.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent3.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            viewHolder.linNamesParent4.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent4.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent4.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent4.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent4.getHeight())) {
                            if (child1 != 0) {
                                for (int i = 0; i < txtTestArray4.size(); i++) {
                                    txtTestArray4.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray4.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray4.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray4.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 8) + 3, txtTestArray4.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray4.get(finalPosition4).setLayoutParams(params1);
                                txtRelationArray4.get(finalPosition4).invalidate();

                                for (int j = 0; j < txtRelationArray4.size(); j++) {
                                    if (finalPosition4 < txtRelationArray4.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray4.get(finalPosition4).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition4) {
                                            txtRelationArray4.get(j).setWidth(txtRelationArray4.get(finalPosition4).getWidth());
                                            txtRelationArray4.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray4.size(); j++) {
                                if (finalPosition4 < txtRelationArray4.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray4.get(finalPosition4).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray4.get(j).setLayoutParams(params11);
                                    txtRelationArray4.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition4 == 0){
                                if (txtRelationArray4.size()>1){
                                    txtRelationArray4.get(0).setWidth(txtRelationArray4.get(1).getWidth());
                                    txtRelationArray4.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray4.size()>1){
                                    txtRelationArray4.get(finalPosition4).setWidth(txtRelationArray4.get(0).getWidth());
                                    txtRelationArray4.get(finalPosition4).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent4.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent4.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent4.getHeight())) {

                            }else{

                                if (finalPosition4 == 0){
                                    if (txtRelationArray4.size()>1){
                                        txtRelationArray4.get(0).setWidth(txtRelationArray4.get(1).getWidth());
                                        txtRelationArray4.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray4.size()>1){
                                        txtRelationArray4.get(finalPosition4).setWidth(txtRelationArray4.get(0).getWidth());
                                        txtRelationArray4.get(finalPosition4).invalidate();
                                    }
                                }

                                if (familyPosition == 3){
                                    if (itemArrayList.get(i).getTitleArray().size() < 9){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }

                                finalViewHolder1. linNamesParent4.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent4.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            viewHolder.linNamesParent5.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent5.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent5.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent5.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent5.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray5.size(); i++) {
                                    txtTestArray5.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray5.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray5.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray5.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 8) + 4, txtTestArray5.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray5.get(finalPosition5).setLayoutParams(params1);
                                txtRelationArray5.get(finalPosition5).invalidate();

                                for (int j = 0; j < txtRelationArray5.size(); j++) {
                                    if (finalPosition5 < txtRelationArray5.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray5.get(finalPosition5).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition5) {
                                            txtRelationArray5.get(j).setWidth(txtRelationArray5.get(finalPosition5).getWidth());
                                            txtRelationArray5.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray5.size(); j++) {
                                if (finalPosition5 < txtRelationArray5.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray5.get(finalPosition5).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray5.get(j).setLayoutParams(params11);
                                    txtRelationArray5.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition5 == 0){
                                if (txtRelationArray5.size()>1){
                                    txtRelationArray5.get(0).setWidth(txtRelationArray5.get(1).getWidth());
                                    txtRelationArray5.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray5.size()>1){
                                    txtRelationArray5.get(finalPosition5).setWidth(txtRelationArray5.get(0).getWidth());
                                    txtRelationArray5.get(finalPosition5).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent5.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent5.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent5.getHeight())) {

                            }else{

                                if (finalPosition5 == 0){
                                    if (txtRelationArray5.size()>1){
                                        txtRelationArray5.get(0).setWidth(txtRelationArray5.get(1).getWidth());
                                        txtRelationArray5.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray5.size()>1){
                                        txtRelationArray5.get(finalPosition5).setWidth(txtRelationArray5.get(0).getWidth());
                                        txtRelationArray5.get(finalPosition5).invalidate();
                                    }
                                }

                                if (familyPosition == 4){
                                    if (itemArrayList.get(i).getTitleArray().size() < 9){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1. linNamesParent5.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent5.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            viewHolder.linNamesParent6.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent6.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent6.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent6.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent6.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray6.size(); i++) {
                                    txtTestArray6.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray6.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray6.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray6.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 8) + 5, txtTestArray6.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray6.get(finalPosition6).setLayoutParams(params1);
                                txtRelationArray6.get(finalPosition6).invalidate();

                                for (int j = 0; j < txtRelationArray6.size(); j++) {
                                    if (finalPosition6 < txtRelationArray6.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray6.get(finalPosition6).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition6) {
                                            txtRelationArray6.get(j).setWidth(txtRelationArray6.get(finalPosition6).getWidth());
                                            txtRelationArray6.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray6.size(); j++) {
                                if (finalPosition6 < txtRelationArray6.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray6.get(finalPosition6).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray6.get(j).setLayoutParams(params11);
                                    txtRelationArray6.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition6 == 0){
                                if (txtRelationArray6.size()>1){
                                    txtRelationArray6.get(0).setWidth(txtRelationArray6.get(1).getWidth());
                                    txtRelationArray6.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray6.size()>1){
                                    txtRelationArray6.get(finalPosition6).setWidth(txtRelationArray6.get(0).getWidth());
                                    txtRelationArray6.get(finalPosition6).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent6.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent6.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent6.getHeight())) {

                            }else{

                                if (finalPosition6 == 0){
                                    if (txtRelationArray6.size()>1){
                                        txtRelationArray6.get(0).setWidth(txtRelationArray6.get(1).getWidth());
                                        txtRelationArray6.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray6.size()>1){
                                        txtRelationArray6.get(finalPosition6).setWidth(txtRelationArray6.get(0).getWidth());
                                        txtRelationArray6.get(finalPosition6).invalidate();
                                    }
                                }

                                if (familyPosition == 5){
                                    if (itemArrayList.get(i).getTitleArray().size() < 9){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1. linNamesParent6.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent6.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            viewHolder.linNamesParent7.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent7.getChildCount() != 0) {
                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent7.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent7.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent7.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray7.size(); i++) {
                                    txtTestArray7.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray7.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray7.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray7.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 8) + 6, txtTestArray7.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray7.get(finalPosition7).setLayoutParams(params1);
                                txtRelationArray7.get(finalPosition7).invalidate();

                                for (int j = 0; j < txtRelationArray7.size(); j++) {
                                    if (finalPosition7 < txtRelationArray7.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray7.get(finalPosition7).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition7) {
                                            txtRelationArray7.get(j).setWidth(txtRelationArray7.get(finalPosition7).getWidth());
                                            txtRelationArray7.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray7.size(); j++) {
                                if (finalPosition7 < txtRelationArray7.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray7.get(finalPosition7).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray7.get(j).setLayoutParams(params11);
                                    txtRelationArray7.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition7 == 0){
                                if (txtRelationArray7.size()>1){
                                    txtRelationArray7.get(0).setWidth(txtRelationArray7.get(1).getWidth());
                                    txtRelationArray7.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray7.size()>1){
                                    txtRelationArray7.get(finalPosition7).setWidth(txtRelationArray7.get(0).getWidth());
                                    txtRelationArray7.get(finalPosition7).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent7.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent7.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent7.getHeight())) {

                            }else{

                                if (finalPosition7 == 0){
                                    if (txtRelationArray7.size()>1){
                                        txtRelationArray7.get(0).setWidth(txtRelationArray7.get(1).getWidth());
                                        txtRelationArray7.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray7.size()>1){
                                        txtRelationArray7.get(finalPosition7).setWidth(txtRelationArray7.get(0).getWidth());
                                        txtRelationArray7.get(finalPosition7).invalidate();
                                    }
                                }
                                if (familyPosition == 6){
                                    if (itemArrayList.get(i).getTitleArray().size() < 9){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1. linNamesParent7.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent7.getViewTreeObserver().removeOnPreDrawListener(this);
                    }

                    return true;
                }
            });

            viewHolder.linNamesParent8.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                @Override
                public boolean onPreDraw() {

                    if (finalViewHolder1.linNamesParent8.getChildCount() != 0) {

                        int child1 = 0;

                        for (int j = 0; j < finalViewHolder1.linNamesParent8.getChildCount(); j++) {
                            child1 = child1 + finalViewHolder1.linNamesParent8.getChildAt(j).getMeasuredHeight();
                        }

                        if (child1 >= (finalViewHolder1.linNamesParent8.getHeight())) {
                            if (child1 != 0) {

                                for (int i = 0; i < txtTestArray8.size(); i++) {
                                    txtTestArray8.get(i).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray8.get(0).getTextSize() - 0.9f);
                                }
                                txtTestArray8.get(0).setTextSize(TypedValue.COMPLEX_UNIT_PX, txtTestArray8.get(1).getTextSize());

                                itemArrayList.get(i).getFamilyTextSizeArray().set((i * 8) + 7, txtTestArray8.get(1).getTextSize());

                                final LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                                txtRelationArray8.get(finalPosition8).setLayoutParams(params1);
                                txtRelationArray8.get(finalPosition8).invalidate();

                                for (int j = 0; j < txtRelationArray8.size(); j++) {
                                    if (finalPosition8 < txtRelationArray8.size()) {
                                        final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray8.get(finalPosition8).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
                                        if (j != finalPosition8) {
                                            txtRelationArray8.get(j).setWidth(txtRelationArray8.get(finalPosition8).getWidth());
                                            txtRelationArray8.get(j).invalidate();
                                        }
                                    }
                                }
                            }
                        } else {

                            for (int j = 0; j < txtRelationArray8.size(); j++) {
                                if (finalPosition8 < txtRelationArray8.size()) {
                                    final LinearLayout.LayoutParams params11 = new LinearLayout.LayoutParams(txtRelationArray8.get(finalPosition8).getWidth(), ViewGroup.LayoutParams.MATCH_PARENT);
//                                    if (j != finalPosition1) {
                                    txtRelationArray8.get(j).setLayoutParams(params11);
                                    txtRelationArray8.get(j).invalidate();
//                                    }
                                }
                            }

                            if (finalPosition8 == 0){
                                if (txtRelationArray8.size()>1){
                                    txtRelationArray8.get(0).setWidth(txtRelationArray8.get(1).getWidth());
                                    txtRelationArray8.get(0).invalidate();
                                }
                            }else{
                                if (txtRelationArray8.size()>1){
                                    txtRelationArray8.get(finalPosition8).setWidth(txtRelationArray8.get(0).getWidth());
                                    txtRelationArray8.get(finalPosition8).invalidate();
                                }
                            }

                            int child2 = 0;

                            for (int j = 0; j < finalViewHolder1.linNamesParent8.getChildCount(); j++){
                                child2 = child2 + finalViewHolder1.linNamesParent8.getChildAt(j).getMeasuredHeight();
                            }

                            if (child2 >= (finalViewHolder1.linNamesParent8.getHeight())) {

                            }else{

                                if (finalPosition8 == 0){
                                    if (txtRelationArray8.size()>1){
                                        txtRelationArray8.get(0).setWidth(txtRelationArray8.get(1).getWidth());
                                        txtRelationArray8.get(0).invalidate();
                                    }
                                }else{
                                    if (txtRelationArray8.size()>1){
                                        txtRelationArray8.get(finalPosition8).setWidth(txtRelationArray8.get(0).getWidth());
                                        txtRelationArray8.get(finalPosition8).invalidate();
                                    }
                                }

                                if (familyPosition == 7){
                                    if (itemArrayList.get(i).getTitleArray().size() < 9){
                                        linPopupTest.setVisibility(View.GONE);
                                    }
                                }
                                finalViewHolder1.linNamesParent8.getViewTreeObserver().removeOnPreDrawListener(this);
                            }
                        }
                    } else {
                        finalViewHolder1.linNamesParent8.getViewTreeObserver().removeOnPreDrawListener(this);
                    }
                    return true;
                }
            });

            if (arrowFlag != 1){
                viewHolder.imgArrow1.setVisibility(View.GONE);
                viewHolder. imgArrow2.setVisibility(View.GONE);
                viewHolder.imgArrow3.setVisibility(View.GONE);
                viewHolder. imgArrow4.setVisibility(View.GONE);
                viewHolder. imgArrow5.setVisibility(View.GONE);
                viewHolder.imgArrow6.setVisibility(View.GONE);
                viewHolder.imgArrow7.setVisibility(View.GONE);
                viewHolder.imgArrow8.setVisibility(View.GONE);
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
                        ArrayList<String> religionArray,
    ArrayList<Float> familyTestSizeArray){

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
        generalViewPagerItem.setFamilyTextSizeArray(familyTestSizeArray);

        itemArrayList.add(generalViewPagerItem);

    }

    void ListMake(int a,String relation, String name, float textSize,LinearLayout layout, String textColor, int position,ArrayList<TextView> txtRelationArray1,ArrayList<TextView> txtTestArray1,ArrayList<TextView> txtRelationNamesArray,ArrayList<TextView> txtNamesArray1){

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

        txtRelationArray1.add(txtRelation);
        txtTestArray1.add(txtRelation);
        txtTestArray1.add(txtComma);

//        if (pref.getString("raspberryId","").equals("j1")){
//            txtRelation.setAlpha(0f);
//            txtComma.setAlpha(0f);
//        }

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

            txtTestArray1.add(txtName);

//            if (pref.getString("raspberryId","").equals("j1")){
//                txtName.setAlpha(0f);
//            }

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

            txtNamesArray1.add(txtName);

        }

        txtNamesArray1.add(txtComma);

        txtRelation.setText(relation);

        txtRelation.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);
        txtComma.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);

        layout.addView(listView);

    }

    void ListMake(int a,String relation, String name, float textSize,LinearLayout layout, String textColor, int position,ArrayList<TextView> txtRelationArray1,ArrayList<TextView> txtTestArray1,ArrayList<TextView> txtRelationNamesArray,ArrayList<TextView> txtNamesArray1,ArrayList<FlowLayout> flowLayouts){

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
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,3);
                linear.setLayoutParams(params);

                layout.addView(linear);
            }
        }else if (division == 8){
            if (a != 0){
                TextView linear = new TextView(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,3);
                linear.setLayoutParams(params);

                layout.addView(linear);
            }
        }

        View listView = new View(context);
        listView = inflater.inflate(R.layout.view_family_list_item,null);
        TextView txtRelation = listView.findViewById(R.id.txt_family_relation_sort_item_relation);
        FlowLayout flowLayout = listView.findViewById(R.id.flow_family_relation_sort_item_name_parent);
        TextView txtComma = listView.findViewById(R.id.txt_family_relation_sort_item_comma);

        flowLayouts.add(flowLayout);

        txtRelation.setGravity(Gravity.CENTER_HORIZONTAL);

        txtRelationArray1.add(txtRelation);
        txtTestArray1.add(txtRelation);
        txtTestArray1.add(txtComma);

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

            txtTestArray1.add(txtName);

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

            txtNamesArray1.add(txtName);

        }

        txtNamesArray1.add(txtComma);

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

    private class ViewHolder{
        public TextView txtTitle1;
        public TextView txtDeceasedName1;
        public LinearLayout linNamesParent1;
        public TextView txtCheckIn1;
        public TextView txtCheckOut1;
        public TextView txtLocation1;
        public ImageView imgProfile1;
        public ImageView imgArrow1;
        public ImageView imgReligionBg1;

        public TextView txtTitle2;
        public TextView txtDeceasedName2;
        public LinearLayout linNamesParent2;
        public TextView txtCheckIn2;
        public TextView txtCheckOut2;
        public TextView txtLocation2;
        public ImageView imgProfile2;
        public ImageView imgArrow2;
        public ImageView imgReligionBg2;

        public TextView txtTitle3;
        public TextView txtDeceasedName3;
        public LinearLayout linNamesParent3;
        public TextView txtCheckIn3;
        public TextView txtCheckOut3;
        public TextView txtLocation3;
        public ImageView imgProfile3;
        public ImageView imgArrow3;
        public ImageView imgReligionBg3;

        public TextView txtTitle4;
        public TextView txtDeceasedName4;
        public LinearLayout linNamesParent4;
        public TextView txtCheckIn4;
        public TextView txtCheckOut4;
        public TextView txtLocation4;
        public ImageView imgProfile4;
        public ImageView imgArrow4;
        public ImageView imgReligionBg4;

        public TextView txtTitle5;
        public TextView txtDeceasedName5;
        public LinearLayout linNamesParent5;
        public TextView txtCheckIn5;
        public TextView txtCheckOut5;
        public TextView txtLocation5;
        public ImageView imgProfile5;
        public ImageView imgArrow5;
        public ImageView imgReligionBg5;

        public TextView txtTitle6;
        public TextView txtDeceasedName6;
        public LinearLayout linNamesParent6;
        public TextView txtCheckIn6;
        public TextView txtCheckOut6;
        public TextView txtLocation6;
        public ImageView imgProfile6;
        public ImageView imgArrow6;
        public ImageView imgReligionBg6;

        public TextView txtTitle7;
        public TextView txtDeceasedName7;
        public LinearLayout linNamesParent7;
        public TextView txtCheckIn7;
        public TextView txtCheckOut7;
        public TextView txtLocation7;
        public ImageView imgProfile7;
        public ImageView imgArrow7;
        public ImageView imgReligionBg7;

        public TextView txtTitle8;
        public TextView txtDeceasedName8;
        public LinearLayout linNamesParent8;
        public TextView txtCheckIn8;
        public TextView txtCheckOut8;
        public TextView txtLocation8;
        public ImageView imgProfile8;
        public ImageView imgArrow8;
        public ImageView imgReligionBg8;

        public LinearLayout linearBg;
        public LinearLayout linearTop;

        public TextView txtEtc1,txtEtc2,txtEtc3,txtEtc4,txtEtc5,txtEtc6,txtEtc7,txtEtc8,txtEtc9,txtEtc10,txtEtc11,txtEtc12,txtEtc13,
                txtEtc14,txtEtc15,txtEtc16,txtEtc17,txtEtc18,txtEtc19,txtEtc20,txtEtc21,txtEtc22,txtEtc23,txtEtc24;
    }
}