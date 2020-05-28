package com.kmw.dongsung.Func;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

//import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide;
import com.kmw.dongsung.R;

import java.util.ArrayList;

public class SangGaPagerAdapter extends PagerAdapter {

    private String TAG = "SangGaPagerAdapter";
    LayoutInflater inflater;
    ArrayList<SangGaPagerItem> itemArrayList = new ArrayList<SangGaPagerItem>();
    Context context;
    int mode;
    int test;

    public SangGaPagerAdapter(LayoutInflater inflater,Context context,int mode,int test) {
        this.inflater = inflater;
        this.context = context;
        this.mode = mode;
        this.test = test;
    }

    @Override
    public int getCount() {
        return test;
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
    public Object instantiateItem(ViewGroup container, int position) {

        View view = inflater.inflate(R.layout.layout_sanga_view_pager,null);

        LinearLayout linearPage = (LinearLayout)view.findViewById(R.id.linear_sanga_view_pager);

        if (itemArrayList.get(0).getTitleList().size() > 6){
            for (int i = 0 + (position * 6); i < itemArrayList.get(0).getImgPathList().size(); i++){
                View listView = new View(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
                params.weight = 136;
                listView.setLayoutParams(params);

                if (mode == 1){
                    listView = inflater.inflate(R.layout.view_sang_ga_list_item_first_design,null);
                    ImageView imgProfile = (ImageView)listView.findViewById(R.id.img_sanga_list_item_first_design_profile);
                    TextView txtTitle = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_title);
                    TextView txtDmName = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_dm_name);
                    TextView txtGender = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_gender);
                    TextView txtAge = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_age);
                    TextView txtReligion = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_religion);
                    TextView txtPosition = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_position);
                    TextView txtInsertTime = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_insert_time);
                    TextView txtCmName = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_cm_name);
                    TextView txtCmPhone = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_cm_phone);
                    TextView txtCheckIn = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_check_in);
                    TextView txtCheckOut = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_check_out);
                    TextView txtLocation = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_location);
                    TextView txtBuilding = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_building);
                    TextView txtTotal = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_total);

                    if (itemArrayList.get(0).getImgPathList().get(i).length() != 0){
                        Glide.with(context).load(itemArrayList.get(0).getImgPathList().get(i)).into(imgProfile);
                    }

                    txtTitle.setText(itemArrayList.get(0).getTitleList().get(i));
                    txtDmName.setText(itemArrayList.get(0).getDmNameList().get(i));
                    if (itemArrayList.get(0).getGenderList().get(i).equals("1")){
                        txtGender.setText("남");
                    }else if (itemArrayList.get(0).getGenderList().get(i).equals("2")){
                        txtGender.setText("여");
                    }
                    txtAge.setText(itemArrayList.get(0).getAgeList().get(i));
                    txtReligion.setText(itemArrayList.get(0).getReligionList().get(i));
                    txtPosition.setText(itemArrayList.get(0).getPositionList().get(i));
                    txtInsertTime.setText(itemArrayList.get(0).getInsertTimeList().get(i));
                    txtCmName.setText(itemArrayList.get(0).getCmNameList().get(i));
                    txtCheckIn.setText(itemArrayList.get(0).getCheckInList().get(i));
                    txtCheckOut.setText(itemArrayList.get(0).getCheckOutList().get(i));

                    if (itemArrayList.get(0).getLocationList().get(i).contains("\r\n")){
                        txtLocation.setMaxLines(2);
                    }else{
                        txtLocation.setMaxLines(1);
                    }

                    txtLocation.setText(itemArrayList.get(0).getLocationList().get(i));
                    txtBuilding.setText(itemArrayList.get(0).getBuildingList().get(i));
                    txtTotal.setText(itemArrayList.get(0).getTotalList().get(i));

                    if (itemArrayList.get(0).getCmPhoneList().get(i).length() == 11){
                        String phonePattern = "(\\d{3})(\\d{3,4})(\\d{4})";
                        txtCmPhone.setText(itemArrayList.get(0).getCmPhoneList().get(i).replaceAll(phonePattern,"$1-$2-$3"));
                    }else if (itemArrayList.get(0).getCmPhoneList().get(i).length() == 10 || itemArrayList.get(0).getCmPhoneList().get(i).length() == 9){
                        String phonePattern = "(\\d{2})(\\d{3,4})(\\d{4})";
                        txtCmPhone.setText(itemArrayList.get(0).getCmPhoneList().get(i).replaceAll(phonePattern,"$1-$2-$3"));
                    }else if (itemArrayList.get(0).getCmPhoneList().get(i).length() == 17 || itemArrayList.get(0).getCmPhoneList().get(i).length() == 8){
                        String phonePattern = "(\\d{4})(\\d{3,4})";
                        txtCmPhone.setText(itemArrayList.get(0).getCmPhoneList().get(i).replaceAll(phonePattern,"$1-$2"));
                    }
                }else{
                    listView = inflater.inflate(R.layout.view_sang_ga_list_item_second_design,null);
                    ImageView imgProfile = (ImageView)listView.findViewById(R.id.img_sanga_list_item_first_design_profile);
                    TextView txtTitle = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_title);
                    TextView txtDmName = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_dm_name);
                    TextView txtGender = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_gender);
                    TextView txtAge = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_age);
                    TextView txtReligion = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_religion);
                    TextView txtPosition = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_position);
                    TextView txtInsertTime = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_insert_time);
                    TextView txtCmName = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_cm_name);
                    TextView txtCmPhone = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_cm_phone);
                    TextView txtCheckIn = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_check_in);
                    TextView txtCheckOut = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_check_out);
                    TextView txtLocation = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_location);
                    TextView txtBuilding = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_building);

                    if (itemArrayList.get(0).getImgPathList().get(i).length() != 0){
                        Glide.with(context).load(itemArrayList.get(0).getImgPathList().get(i)).into(imgProfile);
                    }

                    txtTitle.setText(itemArrayList.get(0).getTitleList().get(i));
                    txtDmName.setText(itemArrayList.get(0).getDmNameList().get(i));
                    if (itemArrayList.get(0).getGenderList().get(i).equals("1")){
                        txtGender.setText("남");
                    }else if (itemArrayList.get(0).getGenderList().get(i).equals("2")){
                        txtGender.setText("여");
                    }
                    txtAge.setText(itemArrayList.get(0).getAgeList().get(i));
                    txtReligion.setText(itemArrayList.get(0).getReligionList().get(i));
                    txtPosition.setText(itemArrayList.get(0).getPositionList().get(i));
                    txtInsertTime.setText(itemArrayList.get(0).getInsertTimeList().get(i));
                    txtCmName.setText(itemArrayList.get(0).getCmNameList().get(i));
                    txtCheckIn.setText(itemArrayList.get(0).getCheckInList().get(i));
                    txtCheckOut.setText(itemArrayList.get(0).getCheckOutList().get(i));

                    if (itemArrayList.get(0).getLocationList().get(i).contains("\r\n")){
                        txtLocation.setMaxLines(2);
                    }else{
                        txtLocation.setMaxLines(1);
                    }

                    txtLocation.setText(itemArrayList.get(0).getLocationList().get(i));
                    txtBuilding.setText(itemArrayList.get(0).getBuildingList().get(i));

                    if (itemArrayList.get(0).getCmPhoneList().get(i).length() == 11){
                        String phonePattern = "(\\d{3})(\\d{3,4})(\\d{4})";
                        txtCmPhone.setText(itemArrayList.get(0).getCmPhoneList().get(i).replaceAll(phonePattern,"$1-$2-$3"));
                    }else if (itemArrayList.get(0).getCmPhoneList().get(i).length() == 10 || itemArrayList.get(0).getCmPhoneList().get(i).length() == 9){
                        String phonePattern = "(\\d{2})(\\d{3,4})(\\d{4})";
                        txtCmPhone.setText(itemArrayList.get(0).getCmPhoneList().get(i).replaceAll(phonePattern,"$1-$2-$3"));
                    }else if (itemArrayList.get(0).getCmPhoneList().get(i).length() == 17 || itemArrayList.get(0).getCmPhoneList().get(i).length() == 8){
                        String phonePattern = "(\\d{4})(\\d{3,4})";
                        txtCmPhone.setText(itemArrayList.get(0).getCmPhoneList().get(i).replaceAll(phonePattern,"$1-$2"));
                    }
                }
                if (linearPage.getChildCount() != 6){
                    linearPage.addView(listView);
                }
            }
        } else{
            for (int i = 0 + (position * 6); i < itemArrayList.get(0).getImgPathList().size(); i++){
                View listView = new View(context);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0);
                params.weight = 136;
                listView.setLayoutParams(params);

                if (mode == 1){
                    listView = inflater.inflate(R.layout.view_sang_ga_list_item_first_design,null);
                    ImageView imgProfile = (ImageView)listView.findViewById(R.id.img_sanga_list_item_first_design_profile);
                    TextView txtTitle = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_title);
                    TextView txtDmName = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_dm_name);
                    TextView txtGender = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_gender);
                    TextView txtAge = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_age);
                    TextView txtReligion = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_religion);
                    TextView txtPosition = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_position);
                    TextView txtInsertTime = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_insert_time);
                    TextView txtCmName = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_cm_name);
                    TextView txtCmPhone = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_cm_phone);
                    TextView txtCheckIn = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_check_in);
                    TextView txtCheckOut = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_check_out);
                    TextView txtLocation = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_location);
                    TextView txtBuilding = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_building);
                    TextView txtTotal = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_total);

                    if (itemArrayList.get(position).getImgPathList().get(i).length() != 0){
                        Glide.with(context).load(itemArrayList.get(position).getImgPathList().get(i)).into(imgProfile);
                    }

                    txtTitle.setText(itemArrayList.get(position).getTitleList().get(i));
                    txtDmName.setText(itemArrayList.get(position).getDmNameList().get(i));
                    if (itemArrayList.get(position).getGenderList().get(i).equals("1")){
                        txtGender.setText("남");
                    }else if (itemArrayList.get(position).getGenderList().get(i).equals("2")){
                        txtGender.setText("여");
                    }
                    txtAge.setText(itemArrayList.get(position).getAgeList().get(i));
                    txtReligion.setText(itemArrayList.get(position).getReligionList().get(i));
                    txtPosition.setText(itemArrayList.get(position).getPositionList().get(i));
                    txtInsertTime.setText(itemArrayList.get(position).getInsertTimeList().get(i));
                    txtCmName.setText(itemArrayList.get(position).getCmNameList().get(i));
                    txtCheckIn.setText(itemArrayList.get(position).getCheckInList().get(i));
                    txtCheckOut.setText(itemArrayList.get(position).getCheckOutList().get(i));

                    if (itemArrayList.get(position).getLocationList().get(i).contains("\r\n")){
                        txtLocation.setMaxLines(2);
                    }else{
                        txtLocation.setMaxLines(1);
                    }

                    txtLocation.setText(itemArrayList.get(position).getLocationList().get(i));
                    txtBuilding.setText(itemArrayList.get(position).getBuildingList().get(i));
                    txtTotal.setText(itemArrayList.get(position).getTotalList().get(i));

                    if (itemArrayList.get(position).getCmPhoneList().get(i).length() == 11){
                        String phonePattern = "(\\d{3})(\\d{3,4})(\\d{4})";
                        txtCmPhone.setText(itemArrayList.get(position).getCmPhoneList().get(i).replaceAll(phonePattern,"$1-$2-$3"));
                    }else if (itemArrayList.get(position).getCmPhoneList().get(i).length() == 10 || itemArrayList.get(position).getCmPhoneList().get(i).length() == 9){
                        String phonePattern = "(\\d{2})(\\d{3,4})(\\d{4})";
                        txtCmPhone.setText(itemArrayList.get(position).getCmPhoneList().get(i).replaceAll(phonePattern,"$1-$2-$3"));
                    }else if (itemArrayList.get(position).getCmPhoneList().get(i).length() == 17 || itemArrayList.get(position).getCmPhoneList().get(i).length() == 8){
                        String phonePattern = "(\\d{4})(\\d{3,4})";
                        txtCmPhone.setText(itemArrayList.get(position).getCmPhoneList().get(i).replaceAll(phonePattern,"$1-$2"));
                    }
                }else{
                    listView = inflater.inflate(R.layout.view_sang_ga_list_item_second_design,null);
                    ImageView imgProfile = (ImageView)listView.findViewById(R.id.img_sanga_list_item_first_design_profile);
                    TextView txtTitle = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_title);
                    TextView txtDmName = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_dm_name);
                    TextView txtGender = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_gender);
                    TextView txtAge = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_age);
                    TextView txtReligion = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_religion);
                    TextView txtPosition = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_position);
                    TextView txtInsertTime = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_insert_time);
                    TextView txtCmName = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_cm_name);
                    TextView txtCmPhone = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_cm_phone);
                    TextView txtCheckIn = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_check_in);
                    TextView txtCheckOut = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_check_out);
                    TextView txtLocation = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_location);
                    TextView txtBuilding = (TextView)listView.findViewById(R.id.txt_sanga_list_item_first_design_building);

                    if (itemArrayList.get(0).getImgPathList().get(i).length() != 0){
                        Glide.with(context).load(itemArrayList.get(0).getImgPathList().get(i)).into(imgProfile);
                    }

                    txtTitle.setText(itemArrayList.get(0).getTitleList().get(i));
                    txtDmName.setText(itemArrayList.get(0).getDmNameList().get(i));
                    if (itemArrayList.get(0).getGenderList().get(i).equals("1")){
                        txtGender.setText("남");
                    }else if (itemArrayList.get(0).getGenderList().get(i).equals("2")){
                        txtGender.setText("여");
                    }
                    txtAge.setText(itemArrayList.get(0).getAgeList().get(i));
                    txtReligion.setText(itemArrayList.get(0).getReligionList().get(i));
                    txtPosition.setText(itemArrayList.get(0).getPositionList().get(i));
                    txtInsertTime.setText(itemArrayList.get(0).getInsertTimeList().get(i));
                    txtCmName.setText(itemArrayList.get(0).getCmNameList().get(i));
                    txtCheckIn.setText(itemArrayList.get(0).getCheckInList().get(i));
                    txtCheckOut.setText(itemArrayList.get(0).getCheckOutList().get(i));

                    if (itemArrayList.get(0).getLocationList().get(i).contains("\r\n")){
                        txtLocation.setMaxLines(2);
                    }else{
                        txtLocation.setMaxLines(1);
                    }

                    txtLocation.setText(itemArrayList.get(0).getLocationList().get(i));
                    txtBuilding.setText(itemArrayList.get(0).getBuildingList().get(i));

                    if (itemArrayList.get(0).getCmPhoneList().get(i).length() == 11){
                        String phonePattern = "(\\d{3})(\\d{3,4})(\\d{4})";
                        txtCmPhone.setText(itemArrayList.get(0).getCmPhoneList().get(i).replaceAll(phonePattern,"$1-$2-$3"));
                    }else if (itemArrayList.get(0).getCmPhoneList().get(i).length() == 10 || itemArrayList.get(0).getCmPhoneList().get(i).length() == 9){
                        String phonePattern = "(\\d{2})(\\d{3,4})(\\d{4})";
                        txtCmPhone.setText(itemArrayList.get(0).getCmPhoneList().get(i).replaceAll(phonePattern,"$1-$2-$3"));
                    }else if (itemArrayList.get(0).getCmPhoneList().get(i).length() == 17 || itemArrayList.get(0).getCmPhoneList().get(i).length() == 8){
                        String phonePattern = "(\\d{4})(\\d{3,4})";
                        txtCmPhone.setText(itemArrayList.get(0).getCmPhoneList().get(i).replaceAll(phonePattern,"$1-$2"));
                    }
                }
                linearPage.addView(listView);
            }
        }

        ((ViewPager)container).addView(view);

        return view;
    }

    public void addItem(ArrayList<String> imgPathList,
                        ArrayList<String> titleList,
                        ArrayList<String> dmNameList,
                        ArrayList<String> genderList,
                        ArrayList<String> ageList,
                        ArrayList<String> religionList,
                        ArrayList<String> positionList,
                        ArrayList<String> insertTimeList,
                        ArrayList<String> checkInList,
                        ArrayList<String> checkOutList,
                        ArrayList<String> locationList,
                        ArrayList<String> buildingList,
                        ArrayList<String> floorList,
                        ArrayList<String> totalList,
                        ArrayList<String> cmNameList,
                        ArrayList<String> cmPhoneList   ){
        SangGaPagerItem sangGaPagerItem = new SangGaPagerItem();

        sangGaPagerItem.setImgPathList(imgPathList);
        sangGaPagerItem.setTitleList(titleList);
        sangGaPagerItem.setDmNameList(dmNameList);
        sangGaPagerItem.setGenderList(genderList);
        sangGaPagerItem.setAgeList(ageList);
        sangGaPagerItem.setReligionList(religionList);
        sangGaPagerItem.setPositionList(positionList);
        sangGaPagerItem.setInsertTimeList(insertTimeList);
        sangGaPagerItem.setCheckInList(checkInList);
        sangGaPagerItem.setCheckOutList(checkOutList);
        sangGaPagerItem.setLocationList(locationList);
        sangGaPagerItem.setBuildingList(buildingList);
        sangGaPagerItem.setFloorList(floorList);
        sangGaPagerItem.setTotalList(totalList);
        sangGaPagerItem.setCmNameList(cmNameList);
        sangGaPagerItem.setCmPhoneList(cmPhoneList);

        itemArrayList.add(sangGaPagerItem);
    }
}
