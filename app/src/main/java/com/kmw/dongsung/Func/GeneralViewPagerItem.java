package com.kmw.dongsung.Func;

import java.util.ArrayList;

public class GeneralViewPagerItem {

    public GeneralViewPagerItem() {

    }

    ArrayList<String> titleArray = new ArrayList<>();
    ArrayList<String> deceasedArray = new ArrayList<>();
    ArrayList<String> imgPathArray = new ArrayList<>();
    ArrayList<String> relationshipArray = new ArrayList<>();
    ArrayList<String> checkInArray = new ArrayList<>();
    ArrayList<String> checkOutArray = new ArrayList<>();
    ArrayList<String> locationArray = new ArrayList<>();

    public ArrayList<String> getTitleArray() {
        return titleArray;
    }

    public void setTitleArray(ArrayList<String> titleArray) {
        this.titleArray = titleArray;
    }

    public ArrayList<String> getDeceasedArray() {
        return deceasedArray;
    }

    public void setDeceasedArray(ArrayList<String> deceasedArray) {
        this.deceasedArray = deceasedArray;
    }

    public ArrayList<String> getImgPathArray() {
        return imgPathArray;
    }

    public void setImgPathArray(ArrayList<String> imgPathArray) {
        this.imgPathArray = imgPathArray;
    }

    public ArrayList<String> getRelationshipArray() {
        return relationshipArray;
    }

    public void setRelationshipArray(ArrayList<String> relationshipArray) {
        this.relationshipArray = relationshipArray;
    }

    public ArrayList<String> getCheckInArray() {
        return checkInArray;
    }

    public void setCheckInArray(ArrayList<String> checkInArray) {
        this.checkInArray = checkInArray;
    }

    public ArrayList<String> getCheckOutArray() {
        return checkOutArray;
    }

    public void setCheckOutArray(ArrayList<String> checkOutArray) {
        this.checkOutArray = checkOutArray;
    }

    public ArrayList<String> getLocationArray() {
        return locationArray;
    }

    public void setLocationArray(ArrayList<String> locationArray) {
        this.locationArray = locationArray;
    }
}
