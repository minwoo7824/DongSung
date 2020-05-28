package com.kmw.dongsung.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

public class CustomLinearLayout extends LinearLayout {

    private String TAG = "CustomLinearLayout";

    public CustomLinearLayout(Context context) {
        super(context);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setWeightSum(float weightSum) {
        super.setWeightSum(weightSum);
        init();
    }

    void init(){

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int height = 0;

                for (int i = 0; i < getChildCount(); i++){
                    height = height + getChildAt(i).getHeight();
                }
                getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }
}
