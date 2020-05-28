package com.kmw.dongsung.Views;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class CustomTextSwitcher extends ViewSwitcher {
    public CustomTextSwitcher(Context context) {
        super(context);
    }

    public CustomTextSwitcher(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (!(child instanceof View)) {
            throw new IllegalArgumentException(
                    "TextSwitcher children must be instances of TextView");
        }
        super.addView(child, index, params);
    }

    /**
     * Sets the text of the next view and switches to the next view. This can
     * be used to animate the old text out and animate the next text in.
     *
     * @param text the new text to display
     */
    public void setText(CharSequence text) {
        final TextView t = (TextView) getNextView();
        t.setText(text);
        showNext();
    }

    public void addLin(View lin){
        View l = (View) getNextView();
        l = lin;
        showNext();
    }

    /**
     * Sets the text of the text view that is currently showing.  This does
     * not perform the animations.
     *
     * @param text the new text to display
     */
    public void setCurrentText(CharSequence text) {
        ((TextView)getCurrentView()).setText(text);
    }

    @Override
    public CharSequence getAccessibilityClassName() {
        return TextSwitcher.class.getName();
    }
}
