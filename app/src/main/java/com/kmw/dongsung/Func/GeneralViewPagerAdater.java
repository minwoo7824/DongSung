package com.kmw.dongsung.Func;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.kmw.dongsung.R;

import java.util.ArrayList;

public class GeneralViewPagerAdater extends PagerAdapter {

    LayoutInflater inflater;
    ArrayList<GeneralViewPagerItem> itemArrayList = new ArrayList<GeneralViewPagerItem>();
    Context context;
    int division;
    int arrowFlag = 0;

    public GeneralViewPagerAdater(LayoutInflater inflater,Context context, int division, int arrowFlag) {
        this.inflater = inflater;
        this.context = context;
        this.division = division;
        this.arrowFlag = arrowFlag;
    }

    @Override
    public int getCount() {
        if (itemArrayList.size() < 9){
            return 1;
        }else if (itemArrayList.size() > 8 && itemArrayList.size() < 17){
            return 2;
        }else{
            return 3;
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View)object);
        ((ViewPager)container).removeView((View)object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {

        View view = null;

        if (division == 1){
            view = inflater.inflate(R.layout.binso_one_division_item,null);
            TextView txtTitle = (TextView)view.findViewById(R.id.txt_one_division_item_title);
            TextView txtDeceasedName = (TextView)view.findViewById(R.id.txt_one_division_item_deceased_name);
            TextView txtRelationship = (TextView)view.findViewById(R.id.txt_one_division_item_relationship);
            TextView txtCheckIn = (TextView)view.findViewById(R.id.txt_one_division_item_check_in);
            TextView txtCheckOut = (TextView)view.findViewById(R.id.txt_one_division_item_check_out);
            TextView txtLocation = (TextView)view.findViewById(R.id.txt_one_division_item_location);
            ImageView imgProfile = (ImageView)view.findViewById(R.id.img_one_division_item_deceased_profile);


//            Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(0)).into(imgProfile);



            txtTitle.setText(itemArrayList.get(i).getTitleArray().get(0));
            txtDeceasedName.setText(itemArrayList.get(i).getDeceasedArray().get(0));
            txtRelationship.setText(itemArrayList.get(i).getRelationshipArray().get(0));
            txtCheckIn.setText(itemArrayList.get(i).getCheckInArray().get(0));
            txtCheckOut.setText(itemArrayList.get(i).getCheckOutArray().get(0));
            txtLocation.setText(itemArrayList.get(i).getLocationArray().get(0));

        }else if (division == 2){
            view = inflater.inflate(R.layout.binso_two_division_item,null);

            TextView txtTitle1 = (TextView)view.findViewById(R.id.txt_tow_division_item_title1);
            TextView txtDeceasedName1 = (TextView)view.findViewById(R.id.txt_tow_division_item_deceased_name1);
            TextView txtRelationship1 = (TextView)view.findViewById(R.id.txt_two_division_item_relationship1);
            TextView txtCheckIn1 = (TextView)view.findViewById(R.id.txt_tow_division_item_check_in1);
            TextView txtCheckOut1 = (TextView)view.findViewById(R.id.txt_tow_division_item_check_out1);
            TextView txtLocation1 = (TextView)view.findViewById(R.id.txt_tow_division_item_location1);
            ImageView imgProfile1 = (ImageView)view.findViewById(R.id.img_tow_division_item_profile1);
//            ImageView imgArrow1 = (ImageView)view.findViewById(R.id.img_two_division_item_arrow1);

            TextView txtTitle2 = (TextView)view.findViewById(R.id.txt_tow_division_item_title2);
            TextView txtDeceasedName2 = (TextView)view.findViewById(R.id.txt_tow_division_item_deceased_name2);
            TextView txtRelationship2 = (TextView)view.findViewById(R.id.txt_tow_division_item_relationship2);
            TextView txtCheckIn2 = (TextView)view.findViewById(R.id.txt_tow_division_item_check_in2);
            TextView txtCheckOut2 = (TextView)view.findViewById(R.id.txt_tow_division_item_check_out2);
            TextView txtLocation2 = (TextView)view.findViewById(R.id.txt_tow_division_item_location2);
            ImageView imgProfile2 = (ImageView)view.findViewById(R.id.img_tow_division_item_profile2);
//            ImageView imgArrow2 = (ImageView)view.findViewById(R.id.img_two_division_item_arrow2);

//            Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(0)).into(imgProfile1);
//            Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(1)).into(imgProfile2);



            txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(0));
            txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(0));
            txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(0));
            txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(0));
            txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(0));
            txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(0));

            txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(1));
            txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(1));
            txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(1));
            txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(1));
            txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(1));
            txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(1));

        }else if (division < 5){
            view = inflater.inflate(R.layout.binso_four_division_item,null);
            LinearLayout linVisible = (LinearLayout)view.findViewById(R.id.linear_four_list_item_visible4);

            TextView txtTitle1 = (TextView)view.findViewById(R.id.txt_four_division_item_title1);
            TextView txtDeceasedName1 = (TextView)view.findViewById(R.id.txt_four_division_item_deceased_name1);
            TextView txtRelationship1 = (TextView)view.findViewById(R.id.txt_four_division_item_relationship1);
            TextView txtCheckIn1 = (TextView)view.findViewById(R.id.txt_four_division_item_check_in1);
            TextView txtCheckOut1 = (TextView)view.findViewById(R.id.txt_four_division_item_check_out1);
            TextView txtLocation1 = (TextView)view.findViewById(R.id.txt_four_division_item_location1);
            ImageView imgProfile1 = (ImageView)view.findViewById(R.id.img_four_division_item_profile1);


            TextView txtTitle2 = (TextView)view.findViewById(R.id.txt_four_division_item_title2);
            TextView txtDeceasedName2 = (TextView)view.findViewById(R.id.txt_four_division_item_deceased_name2);
            TextView txtRelationship2 = (TextView)view.findViewById(R.id.txt_four_division_item_relationship2);
            TextView txtCheckIn2 = (TextView)view.findViewById(R.id.txt_four_division_item_check_in2);
            TextView txtCheckOut2 = (TextView)view.findViewById(R.id.txt_four_division_item_check_out2);
            TextView txtLocation2 = (TextView)view.findViewById(R.id.txt_four_division_item_location2);
            ImageView imgProfile2 = (ImageView)view.findViewById(R.id.img_four_division_item_profile2);


            TextView txtTitle3 = (TextView)view.findViewById(R.id.txt_four_division_item_title3);
            TextView txtDeceasedName3 = (TextView)view.findViewById(R.id.txt_four_division_item_deceased_name3);
            TextView txtRelationship3 = (TextView)view.findViewById(R.id.txt_four_division_item_relationship3);
            TextView txtCheckIn3 = (TextView)view.findViewById(R.id.txt_four_division_item_check_in3);
            TextView txtCheckOut3 = (TextView)view.findViewById(R.id.txt_four_division_item_check_out3);
            TextView txtLocation3 = (TextView)view.findViewById(R.id.txt_four_division_item_location3);
            ImageView imgProfile3 = (ImageView)view.findViewById(R.id.img_four_division_item_profile3);


            TextView txtTitle4 = (TextView)view.findViewById(R.id.txt_four_division_item_title4);
            TextView txtDeceasedName4 = (TextView)view.findViewById(R.id.txt_four_division_item_deceased_name4);
            TextView txtRelationship4 = (TextView)view.findViewById(R.id.txt_four_division_item_relationship4);
            TextView txtCheckIn4 = (TextView)view.findViewById(R.id.txt_four_division_item_check_in4);
            TextView txtCheckOut4 = (TextView)view.findViewById(R.id.txt_four_division_item_check_out4);
            TextView txtLocation4 = (TextView)view.findViewById(R.id.txt_four_division_item_location4);
            ImageView imgProfile4 = (ImageView)view.findViewById(R.id.img_four_division_item_profile4);


//            Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(0)).into(imgProfile1);
//            Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(1)).into(imgProfile2);
//            Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(2)).into(imgProfile3);


            txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(0));
            txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(0));
            txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(0));
            txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(0));
            txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(0));
            txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(0));

            txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(1));
            txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(1));
            txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(1));
            txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(1));
            txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(1));
            txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(1));

            txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(2));
            txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(2));
            txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(2));
            txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(2));
            txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(2));
            txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(2));

            if (division == 4){
                txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(3));
                txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(3));
                txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(3));
                txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(3));
                txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(3));
                txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(3));
//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(3)).into(imgProfile4);
            }else{
                linVisible.setVisibility(View.GONE);
            }
        }else if (division < 7){
            view = inflater.inflate(R.layout.binso_six_division_item,null);
            LinearLayout linVisible = (LinearLayout)view.findViewById(R.id.linear_six_list_item_visible6);

            TextView txtTitle1 = (TextView)view.findViewById(R.id.txt_six_division_item_title1);
            TextView txtDeceasedName1 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name1);
            TextView txtRelationship1 = (TextView)view.findViewById(R.id.txt_six_division_item_relationship1);
            TextView txtCheckIn1 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in1);
            TextView txtCheckOut1 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out1);
            TextView txtLocation1 = (TextView)view.findViewById(R.id.txt_six_division_item_location1);
            ImageView imgProfile1 = (ImageView)view.findViewById(R.id.img_six_division_item_profile1);


            TextView txtTitle2 = (TextView)view.findViewById(R.id.txt_six_division_item_title2);
            TextView txtDeceasedName2 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name2);
            TextView txtRelationship2 = (TextView)view.findViewById(R.id.txt_six_division_item_relationship2);
            TextView txtCheckIn2 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in2);
            TextView txtCheckOut2 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out2);
            TextView txtLocation2 = (TextView)view.findViewById(R.id.txt_six_division_item_location2);
            ImageView imgProfile2 = (ImageView)view.findViewById(R.id.img_six_division_item_profile2);


            TextView txtTitle3 = (TextView)view.findViewById(R.id.txt_six_division_item_title3);
            TextView txtDeceasedName3 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name3);
            TextView txtRelationship3 = (TextView)view.findViewById(R.id.txt_six_division_item_relationship3);
            TextView txtCheckIn3 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in3);
            TextView txtCheckOut3 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out3);
            TextView txtLocation3 = (TextView)view.findViewById(R.id.txt_six_division_item_location3);
            ImageView imgProfile3 = (ImageView)view.findViewById(R.id.img_six_division_item_profile3);


            TextView txtTitle4 = (TextView)view.findViewById(R.id.txt_six_division_item_title4);
            TextView txtDeceasedName4 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name4);
            TextView txtRelationship4 = (TextView)view.findViewById(R.id.txt_six_division_item_relationship4);
            TextView txtCheckIn4 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in4);
            TextView txtCheckOut4 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out4);
            TextView txtLocation4 = (TextView)view.findViewById(R.id.txt_six_division_item_location4);
            ImageView imgProfile4 = (ImageView)view.findViewById(R.id.img_six_division_item_profile4);


            TextView txtTitle5 = (TextView)view.findViewById(R.id.txt_six_division_item_title5);
            TextView txtDeceasedName5 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name5);
            TextView txtRelationship5 = (TextView)view.findViewById(R.id.txt_six_division_item_relationship5);
            TextView txtCheckIn5 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in5);
            TextView txtCheckOut5 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out5);
            TextView txtLocation5 = (TextView)view.findViewById(R.id.txt_six_division_item_location5);
            ImageView imgProfile5 = (ImageView)view.findViewById(R.id.img_six_division_item_profile5);


            TextView txtTitle6 = (TextView)view.findViewById(R.id.txt_six_division_item_title6);
            TextView txtDeceasedName6 = (TextView)view.findViewById(R.id.txt_six_division_item_deceased_name6);
            TextView txtRelationship6 = (TextView)view.findViewById(R.id.txt_six_division_item_relationship6);
            TextView txtCheckIn6 = (TextView)view.findViewById(R.id.txt_six_division_item_check_in6);
            TextView txtCheckOut6 = (TextView)view.findViewById(R.id.txt_six_division_item_check_out6);
            TextView txtLocation6 = (TextView)view.findViewById(R.id.txt_six_division_item_location6);
            ImageView imgProfile6 = (ImageView)view.findViewById(R.id.img_six_division_item_profile6);


//            Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(0)).into(imgProfile1);
//            Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(1)).into(imgProfile2);
//            Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(2)).into(imgProfile3);
//            Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(3)).into(imgProfile4);
//            Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(4)).into(imgProfile5);



            txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(0));
            txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(0));
            txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(0));
            txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(0));
            txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(0));
            txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(0));

            txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(1));
            txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(1));
            txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(1));
            txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(1));
            txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(1));
            txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(1));

            txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(2));
            txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(2));
            txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(2));
            txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(2));
            txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(2));
            txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(2));

            txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(3));
            txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(3));
            txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(3));
            txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(3));
            txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(3));
            txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(3));

            txtTitle5.setText(itemArrayList.get(i).getTitleArray().get(4));
            txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get(4));
            txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get(4));
            txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get(4));
            txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get(4));
            txtLocation5.setText(itemArrayList.get(i).getLocationArray().get(4));

            if (division == 6){
                txtTitle6.setText(itemArrayList.get(i).getTitleArray().get(5));
                txtDeceasedName6.setText(itemArrayList.get(i).getDeceasedArray().get(5));
                txtRelationship6.setText(itemArrayList.get(i).getRelationshipArray().get(5));
                txtCheckIn6.setText(itemArrayList.get(i).getCheckInArray().get(5));
                txtCheckOut6.setText(itemArrayList.get(i).getCheckOutArray().get(5));
                txtLocation6.setText(itemArrayList.get(i).getLocationArray().get(5));
//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(5)).into(imgProfile6);

            }else{
                linVisible.setVisibility(View.GONE);
            }
        }else if (division >= 7){
            view = inflater.inflate(R.layout.binso_eight_division_item,null);
            LinearLayout linVisible2 = (LinearLayout)view.findViewById(R.id.linear_eight_visible2);
            LinearLayout linVisible3 = (LinearLayout)view.findViewById(R.id.linear_eight_visible3);
            LinearLayout linVisible4 = (LinearLayout)view.findViewById(R.id.linear_eight_visible4);
            LinearLayout linVisible5 = (LinearLayout)view.findViewById(R.id.linear_eight_visible5);
            LinearLayout linVisible6 = (LinearLayout)view.findViewById(R.id.linear_eight_visible6);
            LinearLayout linVisible7 = (LinearLayout)view.findViewById(R.id.linear_eight_visible7);
            LinearLayout linVisible8 = (LinearLayout)view.findViewById(R.id.linear_eight_visible8);

            TextView txtTitle1 = (TextView)view.findViewById(R.id.txt_eight_division_item_title1);
            TextView txtDeceasedName1 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name1);
            TextView txtRelationship1 = (TextView)view.findViewById(R.id.txt_eight_division_item_relationship1);
            TextView txtCheckIn1 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in1);
            TextView txtCheckOut1 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out1);
            TextView txtLocation1 = (TextView)view.findViewById(R.id.txt_eight_division_item_location1);
            ImageView imgProfile1 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile1);


            TextView txtTitle2 = (TextView)view.findViewById(R.id.txt_eight_division_item_title2);
            TextView txtDeceasedName2 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name2);
            TextView txtRelationship2 = (TextView)view.findViewById(R.id.txt_eight_division_item_relationship2);
            TextView txtCheckIn2 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in2);
            TextView txtCheckOut2 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out2);
            TextView txtLocation2 = (TextView)view.findViewById(R.id.txt_eight_division_item_location2);
            ImageView imgProfile2 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile2);


            TextView txtTitle3 = (TextView)view.findViewById(R.id.txt_eight_division_item_title3);
            TextView txtDeceasedName3 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name3);
            TextView txtRelationship3 = (TextView)view.findViewById(R.id.txt_eight_division_item_relationship3);
            TextView txtCheckIn3 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in3);
            TextView txtCheckOut3 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out3);
            TextView txtLocation3 = (TextView)view.findViewById(R.id.txt_eight_division_item_location3);
            ImageView imgProfile3 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile3);


            TextView txtTitle4 = (TextView)view.findViewById(R.id.txt_eight_division_item_title4);
            TextView txtDeceasedName4 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name4);
            TextView txtRelationship4 = (TextView)view.findViewById(R.id.txt_eight_division_item_relationship4);
            TextView txtCheckIn4 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in4);
            TextView txtCheckOut4 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out4);
            TextView txtLocation4 = (TextView)view.findViewById(R.id.txt_eight_division_item_location4);
            ImageView imgProfile4 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile4);


            TextView txtTitle5 = (TextView)view.findViewById(R.id.txt_eight_division_item_title5);
            TextView txtDeceasedName5 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name5);
            TextView txtRelationship5 = (TextView)view.findViewById(R.id.txt_eight_division_item_relationship5);
            TextView txtCheckIn5 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in5);
            TextView txtCheckOut5 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out5);
            TextView txtLocation5 = (TextView)view.findViewById(R.id.txt_eight_division_item_location5);
            ImageView imgProfile5 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile5);


            TextView txtTitle6 = (TextView)view.findViewById(R.id.txt_eight_division_item_title6);
            TextView txtDeceasedName6 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name6);
            TextView txtRelationship6 = (TextView)view.findViewById(R.id.txt_eight_division_item_relationship6);
            TextView txtCheckIn6 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in6);
            TextView txtCheckOut6 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out6);
            TextView txtLocation6 = (TextView)view.findViewById(R.id.txt_eight_division_item_location6);
            ImageView imgProfile6 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile6);


            TextView txtTitle7 = (TextView)view.findViewById(R.id.txt_eight_division_item_title7);
            TextView txtDeceasedName7 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name7);
            TextView txtRelationship7 = (TextView)view.findViewById(R.id.txt_eight_division_item_relationship7);
            TextView txtCheckIn7 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in7);
            TextView txtCheckOut7 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out7);
            TextView txtLocation7 = (TextView)view.findViewById(R.id.txt_eight_division_item_location7);
            ImageView imgProfile7 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile7);


            TextView txtTitle8 = (TextView)view.findViewById(R.id.txt_eight_division_item_title8);
            TextView txtDeceasedName8 = (TextView)view.findViewById(R.id.txt_eight_division_item_deceased_name8);
            TextView txtRelationship8 = (TextView)view.findViewById(R.id.txt_eight_division_item_relationship8);
            TextView txtCheckIn8 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_in8);
            TextView txtCheckOut8 = (TextView)view.findViewById(R.id.txt_eight_division_item_check_out8);
            TextView txtLocation8 = (TextView)view.findViewById(R.id.txt_eight_division_item_location8);
            ImageView imgProfile8 = (ImageView)view.findViewById(R.id.img_eight_division_item_profile8);

            if (division == 7){
                txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(0));
                txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(0));
                txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(0));
                txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(0));
                txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(0));
                txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(0));

                txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(1));
                txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(1));
                txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(1));
                txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(1));
                txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(1));
                txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(1));

                txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(2));
                txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(2));
                txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(2));
                txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(2));
                txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(2));
                txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(2));

                txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(3));
                txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(3));
                txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(3));
                txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(3));
                txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(3));
                txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(3));

                txtTitle5.setText(itemArrayList.get(i).getTitleArray().get(4));
                txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get(4));
                txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get(4));
                txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get(4));
                txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get(4));
                txtLocation5.setText(itemArrayList.get(i).getLocationArray().get(4));

                txtTitle6.setText(itemArrayList.get(i).getTitleArray().get(5));
                txtDeceasedName6.setText(itemArrayList.get(i).getDeceasedArray().get(5));
                txtRelationship6.setText(itemArrayList.get(i).getRelationshipArray().get(5));
                txtCheckIn6.setText(itemArrayList.get(i).getCheckInArray().get(5));
                txtCheckOut6.setText(itemArrayList.get(i).getCheckOutArray().get(5));
                txtLocation6.setText(itemArrayList.get(i).getLocationArray().get(5));

                txtTitle7.setText(itemArrayList.get(i).getTitleArray().get(6));
                txtDeceasedName7.setText(itemArrayList.get(i).getDeceasedArray().get(6));
                txtRelationship7.setText(itemArrayList.get(i).getRelationshipArray().get(6));
                txtCheckIn7.setText(itemArrayList.get(i).getCheckInArray().get(6));
                txtCheckOut7.setText(itemArrayList.get(i).getCheckOutArray().get(6));
                txtLocation7.setText(itemArrayList.get(i).getLocationArray().get(6));

//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(0)).into(imgProfile1);
//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(1)).into(imgProfile2);
//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(2)).into(imgProfile3);
//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(3)).into(imgProfile4);
//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(4)).into(imgProfile5);
//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(5)).into(imgProfile6);
//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(6)).into(imgProfile7);

                linVisible8.setVisibility(View.GONE);

            }else if (division == 8){
                txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(0));
                txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(0));
                txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(0));
                txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(0));
                txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(0));
                txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(0));

                txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(1));
                txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(1));
                txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(1));
                txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(1));
                txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(1));
                txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(1));

                txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(2));
                txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(2));
                txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(2));
                txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(2));
                txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(2));
                txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(2));

                txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(3));
                txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(3));
                txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(3));
                txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(3));
                txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(3));
                txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(3));

                txtTitle5.setText(itemArrayList.get(i).getTitleArray().get(4));
                txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get(4));
                txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get(4));
                txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get(4));
                txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get(4));
                txtLocation5.setText(itemArrayList.get(i).getLocationArray().get(4));

                txtTitle6.setText(itemArrayList.get(i).getTitleArray().get(5));
                txtDeceasedName6.setText(itemArrayList.get(i).getDeceasedArray().get(5));
                txtRelationship6.setText(itemArrayList.get(i).getRelationshipArray().get(5));
                txtCheckIn6.setText(itemArrayList.get(i).getCheckInArray().get(5));
                txtCheckOut6.setText(itemArrayList.get(i).getCheckOutArray().get(5));
                txtLocation6.setText(itemArrayList.get(i).getLocationArray().get(5));

                txtTitle7.setText(itemArrayList.get(i).getTitleArray().get(6));
                txtDeceasedName7.setText(itemArrayList.get(i).getDeceasedArray().get(6));
                txtRelationship7.setText(itemArrayList.get(i).getRelationshipArray().get(6));
                txtCheckIn7.setText(itemArrayList.get(i).getCheckInArray().get(6));
                txtCheckOut7.setText(itemArrayList.get(i).getCheckOutArray().get(6));
                txtLocation7.setText(itemArrayList.get(i).getLocationArray().get(6));

                txtTitle8.setText(itemArrayList.get(i).getTitleArray().get(7));
                txtDeceasedName8.setText(itemArrayList.get(i).getDeceasedArray().get(7));
                txtRelationship8.setText(itemArrayList.get(i).getRelationshipArray().get(7));
                txtCheckIn8.setText(itemArrayList.get(i).getCheckInArray().get(7));
                txtCheckOut8.setText(itemArrayList.get(i).getCheckOutArray().get(7));
                txtLocation8.setText(itemArrayList.get(i).getLocationArray().get(7));

//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(0)).into(imgProfile1);
//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(1)).into(imgProfile2);
//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(2)).into(imgProfile3);
//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(3)).into(imgProfile4);
//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(4)).into(imgProfile5);
//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(5)).into(imgProfile6);
//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(6)).into(imgProfile7);
//                Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(7)).into(imgProfile8);
            }else if (division > 8 && division <17){
                if (i == 0){
                    txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(0));
                    txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(0));
                    txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(0));
                    txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(0));
                    txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(0));
                    txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(0));

                    txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(1));
                    txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(1));
                    txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(1));
                    txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(1));
                    txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(1));
                    txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(1));

                    txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(2));
                    txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(2));
                    txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(2));
                    txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(2));
                    txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(2));
                    txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(2));

                    txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(3));
                    txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(3));
                    txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(3));
                    txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(3));
                    txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(3));
                    txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(3));

                    txtTitle5.setText(itemArrayList.get(i).getTitleArray().get(4));
                    txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get(4));
                    txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get(4));
                    txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get(4));
                    txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get(4));
                    txtLocation5.setText(itemArrayList.get(i).getLocationArray().get(4));

                    txtTitle6.setText(itemArrayList.get(i).getTitleArray().get(5));
                    txtDeceasedName6.setText(itemArrayList.get(i).getDeceasedArray().get(5));
                    txtRelationship6.setText(itemArrayList.get(i).getRelationshipArray().get(5));
                    txtCheckIn6.setText(itemArrayList.get(i).getCheckInArray().get(5));
                    txtCheckOut6.setText(itemArrayList.get(i).getCheckOutArray().get(5));
                    txtLocation6.setText(itemArrayList.get(i).getLocationArray().get(5));

                    txtTitle7.setText(itemArrayList.get(i).getTitleArray().get(6));
                    txtDeceasedName7.setText(itemArrayList.get(i).getDeceasedArray().get(6));
                    txtRelationship7.setText(itemArrayList.get(i).getRelationshipArray().get(6));
                    txtCheckIn7.setText(itemArrayList.get(i).getCheckInArray().get(6));
                    txtCheckOut7.setText(itemArrayList.get(i).getCheckOutArray().get(6));
                    txtLocation7.setText(itemArrayList.get(i).getLocationArray().get(6));

                    txtTitle8.setText(itemArrayList.get(i).getTitleArray().get(7));
                    txtDeceasedName8.setText(itemArrayList.get(i).getDeceasedArray().get(7));
                    txtRelationship8.setText(itemArrayList.get(i).getRelationshipArray().get(7));
                    txtCheckIn8.setText(itemArrayList.get(i).getCheckInArray().get(7));
                    txtCheckOut8.setText(itemArrayList.get(i).getCheckOutArray().get(7));
                    txtLocation8.setText(itemArrayList.get(i).getLocationArray().get(7));

//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(0)).into(imgProfile1);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(1)).into(imgProfile2);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(2)).into(imgProfile3);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(3)).into(imgProfile4);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(4)).into(imgProfile5);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(5)).into(imgProfile6);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(6)).into(imgProfile7);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(7)).into(imgProfile8);
                }
                if (division - 8 == 1){
                    if (i == 1){
                        txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(8));
                        txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(8));
                        txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(8));
                        txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(8));
                        txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(8));
                        txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(8));
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(8)).into(imgProfile1);

                        linVisible2.setVisibility(View.GONE);
                        linVisible3.setVisibility(View.GONE);
                        linVisible4.setVisibility(View.GONE);
                        linVisible5.setVisibility(View.GONE);
                        linVisible6.setVisibility(View.GONE);
                        linVisible7.setVisibility(View.GONE);
                        linVisible8.setVisibility(View.GONE);
                    }
                }else if (division - 8 == 2){
                    if (i == 1){
                        txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(8));
                        txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(8));
                        txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(8));
                        txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(8));
                        txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(8));
                        txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(8));

                        txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(9));
                        txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(9));
                        txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(9));
                        txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(9));
                        txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(9));
                        txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(9));

//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(8)).into(imgProfile1);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(9)).into(imgProfile2);

                        linVisible3.setVisibility(View.GONE);
                        linVisible4.setVisibility(View.GONE);
                        linVisible5.setVisibility(View.GONE);
                        linVisible6.setVisibility(View.GONE);
                        linVisible7.setVisibility(View.GONE);
                        linVisible8.setVisibility(View.GONE);
                    }
                }else if (division - 8 == 3){
                    if (i == 1){
                        txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(8));
                        txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(8));
                        txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(8));
                        txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(8));
                        txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(8));
                        txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(8));

                        txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(9));
                        txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(9));
                        txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(9));
                        txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(9));
                        txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(9));
                        txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(9));

                        txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(10));
                        txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(10));
                        txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(10));
                        txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(10));
                        txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(10));
                        txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(10));

//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(8)).into(imgProfile1);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(9)).into(imgProfile2);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(10)).into(imgProfile3);

                        linVisible4.setVisibility(View.GONE);
                        linVisible5.setVisibility(View.GONE);
                        linVisible6.setVisibility(View.GONE);
                        linVisible7.setVisibility(View.GONE);
                        linVisible8.setVisibility(View.GONE);
                    }
                }else if (division - 8 == 4){
                    if (i == 1){
                        txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(8));
                        txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(8));
                        txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(8));
                        txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(8));
                        txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(8));
                        txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(8));

                        txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(9));
                        txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(9));
                        txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(9));
                        txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(9));
                        txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(9));
                        txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(9));

                        txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(10));
                        txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(10));
                        txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(10));
                        txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(10));
                        txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(10));
                        txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(10));

                        txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(11));
                        txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(11));
                        txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(11));
                        txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(11));
                        txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(11));
                        txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(11));

//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(8)).into(imgProfile1);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(9)).into(imgProfile2);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(10)).into(imgProfile3);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(11)).into(imgProfile4);

                        linVisible5.setVisibility(View.GONE);
                        linVisible6.setVisibility(View.GONE);
                        linVisible7.setVisibility(View.GONE);
                        linVisible8.setVisibility(View.GONE);
                    }
                }else if (division - 8 == 5){
                    if (i == 1){
                        txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(8));
                        txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(8));
                        txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(8));
                        txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(8));
                        txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(8));
                        txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(8));

                        txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(9));
                        txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(9));
                        txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(9));
                        txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(9));
                        txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(9));
                        txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(9));

                        txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(10));
                        txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(10));
                        txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(10));
                        txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(10));
                        txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(10));
                        txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(10));

                        txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(11));
                        txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(11));
                        txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(11));
                        txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(11));
                        txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(11));
                        txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(11));

                        txtTitle5.setText(itemArrayList.get(i).getTitleArray().get(12));
                        txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get(12));
                        txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get(12));
                        txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get(12));
                        txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get(12));
                        txtLocation5.setText(itemArrayList.get(i).getLocationArray().get(12));

//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(8)).into(imgProfile1);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(9)).into(imgProfile2);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(10)).into(imgProfile3);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(11)).into(imgProfile4);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(12)).into(imgProfile5);

                        linVisible6.setVisibility(View.GONE);
                        linVisible7.setVisibility(View.GONE);
                        linVisible8.setVisibility(View.GONE);
                    }
                }else if (division - 8 == 6){
                    if (i == 1){
                        txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(8));
                        txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(8));
                        txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(8));
                        txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(8));
                        txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(8));
                        txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(8));

                        txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(9));
                        txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(9));
                        txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(9));
                        txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(9));
                        txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(9));
                        txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(9));

                        txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(10));
                        txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(10));
                        txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(10));
                        txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(10));
                        txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(10));
                        txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(10));

                        txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(11));
                        txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(11));
                        txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(11));
                        txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(11));
                        txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(11));
                        txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(11));

                        txtTitle5.setText(itemArrayList.get(i).getTitleArray().get(12));
                        txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get(12));
                        txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get(12));
                        txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get(12));
                        txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get(12));
                        txtLocation5.setText(itemArrayList.get(i).getLocationArray().get(12));

                        txtTitle6.setText(itemArrayList.get(i).getTitleArray().get(13));
                        txtDeceasedName6.setText(itemArrayList.get(i).getDeceasedArray().get(13));
                        txtRelationship6.setText(itemArrayList.get(i).getRelationshipArray().get(13));
                        txtCheckIn6.setText(itemArrayList.get(i).getCheckInArray().get(13));
                        txtCheckOut6.setText(itemArrayList.get(i).getCheckOutArray().get(13));
                        txtLocation6.setText(itemArrayList.get(i).getLocationArray().get(13));

//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(8)).into(imgProfile1);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(9)).into(imgProfile2);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(10)).into(imgProfile3);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(11)).into(imgProfile4);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(12)).into(imgProfile5);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(13)).into(imgProfile6);

                        linVisible7.setVisibility(View.GONE);
                        linVisible8.setVisibility(View.GONE);
                    }
                }else if (division - 8 == 7){
                    if (i == 1){
                        txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(8));
                        txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(8));
                        txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(8));
                        txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(8));
                        txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(8));
                        txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(8));

                        txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(9));
                        txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(9));
                        txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(9));
                        txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(9));
                        txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(9));
                        txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(9));

                        txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(10));
                        txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(10));
                        txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(10));
                        txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(10));
                        txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(10));
                        txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(10));

                        txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(11));
                        txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(11));
                        txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(111));
                        txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(11));
                        txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(11));
                        txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(11));

                        txtTitle5.setText(itemArrayList.get(i).getTitleArray().get(12));
                        txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get(12));
                        txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get(12));
                        txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get(12));
                        txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get(12));
                        txtLocation5.setText(itemArrayList.get(i).getLocationArray().get(12));

                        txtTitle6.setText(itemArrayList.get(i).getTitleArray().get(13));
                        txtDeceasedName6.setText(itemArrayList.get(i).getDeceasedArray().get(13));
                        txtRelationship6.setText(itemArrayList.get(i).getRelationshipArray().get(13));
                        txtCheckIn6.setText(itemArrayList.get(i).getCheckInArray().get(13));
                        txtCheckOut6.setText(itemArrayList.get(i).getCheckOutArray().get(13));
                        txtLocation6.setText(itemArrayList.get(i).getLocationArray().get(13));

                        txtTitle7.setText(itemArrayList.get(i).getTitleArray().get(14));
                        txtDeceasedName7.setText(itemArrayList.get(i).getDeceasedArray().get(14));
                        txtRelationship7.setText(itemArrayList.get(i).getRelationshipArray().get(14));
                        txtCheckIn7.setText(itemArrayList.get(i).getCheckInArray().get(14));
                        txtCheckOut7.setText(itemArrayList.get(i).getCheckOutArray().get(14));
                        txtLocation7.setText(itemArrayList.get(i).getLocationArray().get(14));

//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(8)).into(imgProfile1);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(9)).into(imgProfile2);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(10)).into(imgProfile3);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(11)).into(imgProfile4);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(12)).into(imgProfile5);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(13)).into(imgProfile6);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(14)).into(imgProfile7);

                        linVisible8.setVisibility(View.GONE);
                    }
                }else if (division - 8 == 8){
                    if (i == 1){
                        txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(8));
                        txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(8));
                        txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(8));
                        txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(8));
                        txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(8));
                        txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(8));

                        txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(9));
                        txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(9));
                        txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(9));
                        txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(9));
                        txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(9));
                        txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(9));

                        txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(10));
                        txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(10));
                        txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(10));
                        txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(10));
                        txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(10));
                        txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(10));

                        txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(11));
                        txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(11));
                        txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(11));
                        txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(11));
                        txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(11));
                        txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(11));

                        txtTitle5.setText(itemArrayList.get(i).getTitleArray().get(12));
                        txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get(12));
                        txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get(12));
                        txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get(12));
                        txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get(12));
                        txtLocation5.setText(itemArrayList.get(i).getLocationArray().get(12));

                        txtTitle6.setText(itemArrayList.get(i).getTitleArray().get(13));
                        txtDeceasedName6.setText(itemArrayList.get(i).getDeceasedArray().get(13));
                        txtRelationship6.setText(itemArrayList.get(i).getRelationshipArray().get(13));
                        txtCheckIn6.setText(itemArrayList.get(i).getCheckInArray().get(13));
                        txtCheckOut6.setText(itemArrayList.get(i).getCheckOutArray().get(13));
                        txtLocation6.setText(itemArrayList.get(i).getLocationArray().get(13));

                        txtTitle7.setText(itemArrayList.get(i).getTitleArray().get(14));
                        txtDeceasedName7.setText(itemArrayList.get(i).getDeceasedArray().get(14));
                        txtRelationship7.setText(itemArrayList.get(i).getRelationshipArray().get(14));
                        txtCheckIn7.setText(itemArrayList.get(i).getCheckInArray().get(14));
                        txtCheckOut7.setText(itemArrayList.get(i).getCheckOutArray().get(14));
                        txtLocation7.setText(itemArrayList.get(i).getLocationArray().get(14));

                        txtTitle8.setText(itemArrayList.get(i).getTitleArray().get(15));
                        txtDeceasedName8.setText(itemArrayList.get(i).getDeceasedArray().get(15));
                        txtRelationship8.setText(itemArrayList.get(i).getRelationshipArray().get(15));
                        txtCheckIn8.setText(itemArrayList.get(i).getCheckInArray().get(15));
                        txtCheckOut8.setText(itemArrayList.get(i).getCheckOutArray().get(15));
                        txtLocation8.setText(itemArrayList.get(i).getLocationArray().get(15));

//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(8)).into(imgProfile1);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(9)).into(imgProfile2);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(10)).into(imgProfile3);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(11)).into(imgProfile4);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(12)).into(imgProfile5);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(13)).into(imgProfile6);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(14)).into(imgProfile7);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(15)).into(imgProfile8);
                    }
                }
            }else if (division > 16 && division < 25){
                if (i == 0){
                    txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(0));
                    txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(0));
                    txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(0));
                    txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(0));
                    txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(0));
                    txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(0));

                    txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(1));
                    txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(1));
                    txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(1));
                    txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(1));
                    txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(1));
                    txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(1));

                    txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(2));
                    txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(2));
                    txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(2));
                    txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(2));
                    txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(2));
                    txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(2));

                    txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(3));
                    txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(3));
                    txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(3));
                    txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(3));
                    txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(3));
                    txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(3));

                    txtTitle5.setText(itemArrayList.get(i).getTitleArray().get(4));
                    txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get(4));
                    txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get(4));
                    txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get(4));
                    txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get(4));
                    txtLocation5.setText(itemArrayList.get(i).getLocationArray().get(4));

                    txtTitle6.setText(itemArrayList.get(i).getTitleArray().get(5));
                    txtDeceasedName6.setText(itemArrayList.get(i).getDeceasedArray().get(5));
                    txtRelationship6.setText(itemArrayList.get(i).getRelationshipArray().get(5));
                    txtCheckIn6.setText(itemArrayList.get(i).getCheckInArray().get(5));
                    txtCheckOut6.setText(itemArrayList.get(i).getCheckOutArray().get(5));
                    txtLocation6.setText(itemArrayList.get(i).getLocationArray().get(5));

                    txtTitle7.setText(itemArrayList.get(i).getTitleArray().get(6));
                    txtDeceasedName7.setText(itemArrayList.get(i).getDeceasedArray().get(6));
                    txtRelationship7.setText(itemArrayList.get(i).getRelationshipArray().get(6));
                    txtCheckIn7.setText(itemArrayList.get(i).getCheckInArray().get(6));
                    txtCheckOut7.setText(itemArrayList.get(i).getCheckOutArray().get(6));
                    txtLocation7.setText(itemArrayList.get(i).getLocationArray().get(6));

                    txtTitle8.setText(itemArrayList.get(i).getTitleArray().get(7));
                    txtDeceasedName8.setText(itemArrayList.get(i).getDeceasedArray().get(7));
                    txtRelationship8.setText(itemArrayList.get(i).getRelationshipArray().get(7));
                    txtCheckIn8.setText(itemArrayList.get(i).getCheckInArray().get(7));
                    txtCheckOut8.setText(itemArrayList.get(i).getCheckOutArray().get(7));
                    txtLocation8.setText(itemArrayList.get(i).getLocationArray().get(7));

//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(0)).into(imgProfile1);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(1)).into(imgProfile2);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(2)).into(imgProfile3);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(3)).into(imgProfile4);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(4)).into(imgProfile5);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(5)).into(imgProfile6);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(6)).into(imgProfile7);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(7)).into(imgProfile8);

                }else if (i == 1){
                    txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(8));
                    txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(8));
                    txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(8));
                    txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(8));
                    txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(8));
                    txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(8));

                    txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(9));
                    txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(9));
                    txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(9));
                    txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(9));
                    txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(9));
                    txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(9));

                    txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(10));
                    txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(10));
                    txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(10));
                    txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(10));
                    txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(10));
                    txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(10));

                    txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(11));
                    txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(11));
                    txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(11));
                    txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(11));
                    txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(11));
                    txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(11));

                    txtTitle5.setText(itemArrayList.get(i).getTitleArray().get(12));
                    txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get(12));
                    txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get(12));
                    txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get(12));
                    txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get(12));
                    txtLocation5.setText(itemArrayList.get(i).getLocationArray().get(12));

                    txtTitle6.setText(itemArrayList.get(i).getTitleArray().get(13));
                    txtDeceasedName6.setText(itemArrayList.get(i).getDeceasedArray().get(13));
                    txtRelationship6.setText(itemArrayList.get(i).getRelationshipArray().get(13));
                    txtCheckIn6.setText(itemArrayList.get(i).getCheckInArray().get(13));
                    txtCheckOut6.setText(itemArrayList.get(i).getCheckOutArray().get(13));
                    txtLocation6.setText(itemArrayList.get(i).getLocationArray().get(13));

                    txtTitle7.setText(itemArrayList.get(i).getTitleArray().get(14));
                    txtDeceasedName7.setText(itemArrayList.get(i).getDeceasedArray().get(14));
                    txtRelationship7.setText(itemArrayList.get(i).getRelationshipArray().get(14));
                    txtCheckIn7.setText(itemArrayList.get(i).getCheckInArray().get(14));
                    txtCheckOut7.setText(itemArrayList.get(i).getCheckOutArray().get(14));
                    txtLocation7.setText(itemArrayList.get(i).getLocationArray().get(14));

                    txtTitle8.setText(itemArrayList.get(i).getTitleArray().get(15));
                    txtDeceasedName8.setText(itemArrayList.get(i).getDeceasedArray().get(15));
                    txtRelationship8.setText(itemArrayList.get(i).getRelationshipArray().get(15));
                    txtCheckIn8.setText(itemArrayList.get(i).getCheckInArray().get(15));
                    txtCheckOut8.setText(itemArrayList.get(i).getCheckOutArray().get(15));
                    txtLocation8.setText(itemArrayList.get(i).getLocationArray().get(15));

//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(8)).into(imgProfile1);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(9)).into(imgProfile2);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(10)).into(imgProfile3);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(11)).into(imgProfile4);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(12)).into(imgProfile5);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(13)).into(imgProfile6);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(14)).into(imgProfile7);
//                    Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(15)).into(imgProfile8);
                }else {
                    if (division - 16 == 1){
                        txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(16));
                        txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(16));
                        txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(16));
                        txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(16));
                        txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(16));
                        txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(16));
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(16)).into(imgProfile1);

                        linVisible2.setVisibility(View.GONE);
                        linVisible3.setVisibility(View.GONE);
                        linVisible4.setVisibility(View.GONE);
                        linVisible5.setVisibility(View.GONE);
                        linVisible6.setVisibility(View.GONE);
                        linVisible7.setVisibility(View.GONE);
                        linVisible8.setVisibility(View.GONE);

                    }else if (division - 16 == 2){
                        txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(16));
                        txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(16));
                        txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(16));
                        txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(16));
                        txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(16));
                        txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(16));

                        txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(17));
                        txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(17));
                        txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(17));
                        txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(17));
                        txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(17));
                        txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(17));

//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(16)).into(imgProfile1);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(17)).into(imgProfile2);

                        linVisible3.setVisibility(View.GONE);
                        linVisible4.setVisibility(View.GONE);
                        linVisible5.setVisibility(View.GONE);
                        linVisible6.setVisibility(View.GONE);
                        linVisible7.setVisibility(View.GONE);
                        linVisible8.setVisibility(View.GONE);
                    }else if (division - 16 == 3){
                        txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(16));
                        txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(16));
                        txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(16));
                        txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(16));
                        txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(16));
                        txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(16));

                        txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(17));
                        txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(17));
                        txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(17));
                        txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(17));
                        txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(17));
                        txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(17));

                        txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(18));
                        txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(18));
                        txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(18));
                        txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(18));
                        txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(18));
                        txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(18));

//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(16)).into(imgProfile1);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(17)).into(imgProfile2);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(18)).into(imgProfile3);

                        linVisible4.setVisibility(View.GONE);
                        linVisible5.setVisibility(View.GONE);
                        linVisible6.setVisibility(View.GONE);
                        linVisible7.setVisibility(View.GONE);
                        linVisible8.setVisibility(View.GONE);
                    }else if (division - 16 == 4){
                        txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(16));
                        txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(16));
                        txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(16));
                        txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(16));
                        txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(16));
                        txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(16));

                        txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(17));
                        txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(17));
                        txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(17));
                        txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(17));
                        txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(17));
                        txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(17));

                        txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(18));
                        txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(18));
                        txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(18));
                        txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(18));
                        txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(18));
                        txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(18));

                        txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(19));
                        txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(19));
                        txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(19));
                        txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(19));
                        txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(19));
                        txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(19));

//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(16)).into(imgProfile1);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(17)).into(imgProfile2);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(18)).into(imgProfile3);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(19)).into(imgProfile4);

                        linVisible5.setVisibility(View.GONE);
                        linVisible6.setVisibility(View.GONE);
                        linVisible7.setVisibility(View.GONE);
                        linVisible8.setVisibility(View.GONE);
                    }else if (division - 16 == 5){
                        txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(16));
                        txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(16));
                        txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(16));
                        txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(16));
                        txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(16));
                        txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(16));

                        txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(17));
                        txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(17));
                        txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(17));
                        txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(17));
                        txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(17));
                        txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(17));

                        txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(18));
                        txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(18));
                        txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(18));
                        txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(18));
                        txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(18));
                        txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(18));

                        txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(19));
                        txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(19));
                        txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(19));
                        txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(19));
                        txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(19));
                        txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(19));

                        txtTitle5.setText(itemArrayList.get(i).getTitleArray().get(20));
                        txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get(20));
                        txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get(20));
                        txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get(20));
                        txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get(20));
                        txtLocation5.setText(itemArrayList.get(i).getLocationArray().get(20));

//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(16)).into(imgProfile1);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(17)).into(imgProfile2);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(18)).into(imgProfile3);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(19)).into(imgProfile4);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(20)).into(imgProfile5);

                        linVisible6.setVisibility(View.GONE);
                        linVisible7.setVisibility(View.GONE);
                        linVisible8.setVisibility(View.GONE);
                    }else if (division - 16 == 6){
                        txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(16));
                        txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(16));
                        txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(16));
                        txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(16));
                        txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(16));
                        txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(16));

                        txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(17));
                        txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(17));
                        txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(17));
                        txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(17));
                        txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(17));
                        txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(17));

                        txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(18));
                        txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(18));
                        txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(18));
                        txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(18));
                        txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(18));
                        txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(18));

                        txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(19));
                        txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(19));
                        txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(19));
                        txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(19));
                        txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(19));
                        txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(19));

                        txtTitle5.setText(itemArrayList.get(i).getTitleArray().get(20));
                        txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get(20));
                        txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get(20));
                        txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get(20));
                        txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get(20));
                        txtLocation5.setText(itemArrayList.get(i).getLocationArray().get(20));

                        txtTitle6.setText(itemArrayList.get(i).getTitleArray().get(21));
                        txtDeceasedName6.setText(itemArrayList.get(i).getDeceasedArray().get(21));
                        txtRelationship6.setText(itemArrayList.get(i).getRelationshipArray().get(21));
                        txtCheckIn6.setText(itemArrayList.get(i).getCheckInArray().get(21));
                        txtCheckOut6.setText(itemArrayList.get(i).getCheckOutArray().get(21));
                        txtLocation6.setText(itemArrayList.get(i).getLocationArray().get(21));

//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(16)).into(imgProfile1);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(17)).into(imgProfile2);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(18)).into(imgProfile3);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(19)).into(imgProfile4);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(20)).into(imgProfile5);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(21)).into(imgProfile6);

                        linVisible7.setVisibility(View.GONE);
                        linVisible8.setVisibility(View.GONE);
                    }else if (division - 16 == 7){
                        txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(16));
                        txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(16));
                        txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(16));
                        txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(16));
                        txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(16));
                        txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(16));

                        txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(17));
                        txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(17));
                        txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(17));
                        txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(17));
                        txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(17));
                        txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(17));

                        txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(18));
                        txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(18));
                        txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(18));
                        txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(18));
                        txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(18));
                        txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(18));

                        txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(19));
                        txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(19));
                        txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(19));
                        txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(19));
                        txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(19));
                        txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(19));

                        txtTitle5.setText(itemArrayList.get(i).getTitleArray().get(20));
                        txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get(20));
                        txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get(20));
                        txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get(20));
                        txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get(20));
                        txtLocation5.setText(itemArrayList.get(i).getLocationArray().get(20));

                        txtTitle6.setText(itemArrayList.get(i).getTitleArray().get(21));
                        txtDeceasedName6.setText(itemArrayList.get(i).getDeceasedArray().get(21));
                        txtRelationship6.setText(itemArrayList.get(i).getRelationshipArray().get(21));
                        txtCheckIn6.setText(itemArrayList.get(i).getCheckInArray().get(21));
                        txtCheckOut6.setText(itemArrayList.get(i).getCheckOutArray().get(21));
                        txtLocation6.setText(itemArrayList.get(i).getLocationArray().get(21));

                        txtTitle7.setText(itemArrayList.get(i).getTitleArray().get(22));
                        txtDeceasedName7.setText(itemArrayList.get(i).getDeceasedArray().get(22));
                        txtRelationship7.setText(itemArrayList.get(i).getRelationshipArray().get(22));
                        txtCheckIn7.setText(itemArrayList.get(i).getCheckInArray().get(22));
                        txtCheckOut7.setText(itemArrayList.get(i).getCheckOutArray().get(22));
                        txtLocation7.setText(itemArrayList.get(i).getLocationArray().get(22));

//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(16)).into(imgProfile1);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(17)).into(imgProfile2);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(18)).into(imgProfile3);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(19)).into(imgProfile4);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(20)).into(imgProfile5);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(21)).into(imgProfile6);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(22)).into(imgProfile7);

                        linVisible8.setVisibility(View.GONE);
                    }else if (division - 16 == 8){
                        txtTitle1.setText(itemArrayList.get(i).getTitleArray().get(16));
                        txtDeceasedName1.setText(itemArrayList.get(i).getDeceasedArray().get(16));
                        txtRelationship1.setText(itemArrayList.get(i).getRelationshipArray().get(16));
                        txtCheckIn1.setText(itemArrayList.get(i).getCheckInArray().get(16));
                        txtCheckOut1.setText(itemArrayList.get(i).getCheckOutArray().get(16));
                        txtLocation1.setText(itemArrayList.get(i).getLocationArray().get(16));

                        txtTitle2.setText(itemArrayList.get(i).getTitleArray().get(17));
                        txtDeceasedName2.setText(itemArrayList.get(i).getDeceasedArray().get(17));
                        txtRelationship2.setText(itemArrayList.get(i).getRelationshipArray().get(17));
                        txtCheckIn2.setText(itemArrayList.get(i).getCheckInArray().get(17));
                        txtCheckOut2.setText(itemArrayList.get(i).getCheckOutArray().get(17));
                        txtLocation2.setText(itemArrayList.get(i).getLocationArray().get(17));

                        txtTitle3.setText(itemArrayList.get(i).getTitleArray().get(18));
                        txtDeceasedName3.setText(itemArrayList.get(i).getDeceasedArray().get(18));
                        txtRelationship3.setText(itemArrayList.get(i).getRelationshipArray().get(18));
                        txtCheckIn3.setText(itemArrayList.get(i).getCheckInArray().get(18));
                        txtCheckOut3.setText(itemArrayList.get(i).getCheckOutArray().get(18));
                        txtLocation3.setText(itemArrayList.get(i).getLocationArray().get(18));

                        txtTitle4.setText(itemArrayList.get(i).getTitleArray().get(19));
                        txtDeceasedName4.setText(itemArrayList.get(i).getDeceasedArray().get(19));
                        txtRelationship4.setText(itemArrayList.get(i).getRelationshipArray().get(19));
                        txtCheckIn4.setText(itemArrayList.get(i).getCheckInArray().get(19));
                        txtCheckOut4.setText(itemArrayList.get(i).getCheckOutArray().get(19));
                        txtLocation4.setText(itemArrayList.get(i).getLocationArray().get(19));

                        txtTitle5.setText(itemArrayList.get(i).getTitleArray().get(20));
                        txtDeceasedName5.setText(itemArrayList.get(i).getDeceasedArray().get(20));
                        txtRelationship5.setText(itemArrayList.get(i).getRelationshipArray().get(20));
                        txtCheckIn5.setText(itemArrayList.get(i).getCheckInArray().get(20));
                        txtCheckOut5.setText(itemArrayList.get(i).getCheckOutArray().get(20));
                        txtLocation5.setText(itemArrayList.get(i).getLocationArray().get(20));

                        txtTitle6.setText(itemArrayList.get(i).getTitleArray().get(21));
                        txtDeceasedName6.setText(itemArrayList.get(i).getDeceasedArray().get(21));
                        txtRelationship6.setText(itemArrayList.get(i).getRelationshipArray().get(21));
                        txtCheckIn6.setText(itemArrayList.get(i).getCheckInArray().get(21));
                        txtCheckOut6.setText(itemArrayList.get(i).getCheckOutArray().get(21));
                        txtLocation6.setText(itemArrayList.get(i).getLocationArray().get(21));

                        txtTitle7.setText(itemArrayList.get(i).getTitleArray().get(22));
                        txtDeceasedName7.setText(itemArrayList.get(i).getDeceasedArray().get(22));
                        txtRelationship7.setText(itemArrayList.get(i).getRelationshipArray().get(22));
                        txtCheckIn7.setText(itemArrayList.get(i).getCheckInArray().get(22));
                        txtCheckOut7.setText(itemArrayList.get(i).getCheckOutArray().get(22));
                        txtLocation7.setText(itemArrayList.get(i).getLocationArray().get(22));

                        txtTitle8.setText(itemArrayList.get(i).getTitleArray().get(23));
                        txtDeceasedName8.setText(itemArrayList.get(i).getDeceasedArray().get(23));
                        txtRelationship8.setText(itemArrayList.get(i).getRelationshipArray().get(23));
                        txtCheckIn8.setText(itemArrayList.get(i).getCheckInArray().get(23));
                        txtCheckOut8.setText(itemArrayList.get(i).getCheckOutArray().get(23));
                        txtLocation8.setText(itemArrayList.get(i).getLocationArray().get(23));

//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(16)).into(imgProfile1);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(17)).into(imgProfile2);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(18)).into(imgProfile3);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(19)).into(imgProfile4);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(20)).into(imgProfile5);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(21)).into(imgProfile6);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(22)).into(imgProfile7);
//                        Glide.with(context).load(itemArrayList.get(i).getImgPathArray().get(23)).into(imgProfile8);
                    }
                }
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
                        ArrayList<String> imgPathArray){

        GeneralViewPagerItem generalViewPagerItem = new GeneralViewPagerItem();

        generalViewPagerItem.setTitleArray(titleArray);
        generalViewPagerItem.setDeceasedArray(deceasedArray);
        generalViewPagerItem.setCheckInArray(checkInArray);
        generalViewPagerItem.setCheckOutArray(checkOutArray);
        generalViewPagerItem.setRelationshipArray(relationshipArray);
        generalViewPagerItem.setLocationArray(locationArray);
        generalViewPagerItem.setImgPathArray(imgPathArray);

        itemArrayList.add(generalViewPagerItem);

    }
}
