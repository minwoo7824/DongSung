package com.kmw.dongsung.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class CustomFitLinearLayout extends LinearLayout {

    public CustomFitLinearLayout(Context context) {
        super(context);
    }

    public CustomFitLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomFitLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void test(){
        int height = 0;
        for (int i = 0; i < getChildCount(); i++){
            height += getChildAt(i).getMeasuredHeight();
        }

        if (height > getMeasuredHeight()){

        }
    }
}
