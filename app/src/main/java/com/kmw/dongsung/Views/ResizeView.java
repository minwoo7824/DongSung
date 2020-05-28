package com.kmw.dongsung.Views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kmw.dongsung.R;

public class ResizeView extends LinearLayout {

    private String TAG = "ResizeView";

    public ResizeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ResizeView(Context context) {
        super(context);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        final int oldWidth = getMeasuredWidth() - getPaddingBottom() - getPaddingTop();

        final int oldHeight = getMeasuredHeight() - getPaddingLeft() - getPaddingRight();

        float size = 50f;

//        for (int i = 0; i < getChildCount(); i++){
//            View v = getChildAt(i);
//            if (v instanceof TextView){
//                for (int textViewHeight = Integer.MAX_VALUE; textViewHeight > oldHeight; size -= 0.1f) {
//                    ((TextView) v).setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
//                    ((TextView) v).measure(MeasureSpec.makeMeasureSpec(oldWidth, MeasureSpec.EXACTLY), MeasureSpec.UNSPECIFIED);
//                    textViewHeight = ((TextView) v).getMeasuredHeight();
//                    ((TextView) v).requestLayout();
//                }
//            }
//        }

        int viewHeight = 0;

//        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//
//                int height = 0;
//
//                for (int i = 0; i < getChildCount(); i++){
//                    if (i % 3 == 0){
//                        View v = getChildAt(i);
//                        height += v.getMeasuredHeight();
//                    }
//                }
//                Log.i(TAG,"childView : " + height);
//
//                if (height >= getMeasuredHeight()){
//                    for (int i = 0; i < getChildCount(); i++){
//                        View v = getChildAt(i);
//                        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,v.getMeasuredHeight()-10);
//                        v.setLayoutParams(params);
//                    }
//                }else{
//                    getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                }
//            }
//        });
    }
}
