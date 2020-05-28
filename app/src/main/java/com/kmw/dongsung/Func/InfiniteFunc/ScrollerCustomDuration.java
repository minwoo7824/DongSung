package com.kmw.dongsung.Func.InfiniteFunc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

public class ScrollerCustomDuration extends Scroller {



    private double mScrollFactor = 1;
    public static int sec = 1;


    public ScrollerCustomDuration(Context context) {

        super(context);

    }



    public ScrollerCustomDuration(Context context, Interpolator interpolator) {

        super(context, interpolator);

    }



    @SuppressLint("NewApi")

    public ScrollerCustomDuration(Context context, Interpolator interpolator, boolean flywheel) {

        super(context, interpolator, flywheel);

    }



    /**

     * Set the factor by which the duration will change

     */
    public void setScrollDurationFactor(double scrollFactor) {
        mScrollFactor = scrollFactor;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        duration = 800;
        super.startScroll(startX, startY, dx, dy, (int) (duration * mScrollFactor));
    }
}
