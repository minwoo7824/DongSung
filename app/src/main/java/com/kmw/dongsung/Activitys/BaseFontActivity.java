package com.kmw.dongsung.Activitys;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kmw.dongsung.R;

/**
 * Skeleton of an Android Things activity.
 * <p>
 * Android Things peripheral APIs are accessible through the class
 * PeripheralManagerService. For example, the snippet below will open a GPIO pin and
 * set it to HIGH:
 *
 * <pre>{@code
 * PeripheralManagerService service = new PeripheralManagerService();
 * mLedGpio = service.openGpio("BCM6");
 * mLedGpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
 * mLedGpio.setValue(true);
 * }</pre>
 * <p>
 * For more complex peripherals, look for an existing user-space driver, or implement one if none
 * is available.
 *
 * @see <a href="https://github.com/androidthings/contrib-drivers#readme">https://github.com/androidthings/contrib-drivers#readme</a>
 */
public class BaseFontActivity extends Activity {

    public Typeface typeface = null;
    public int fontMode = 0;

    @Override
    public void setContentView(int savedInstanceState) {
        super.setContentView(savedInstanceState);

        if (typeface == null){
            if (fontMode == 0){

            }else if (fontMode == 1){
                typeface = Typeface.createFromAsset(getAssets(),"noto_sans_bold.otf");
            }else{
                typeface = Typeface.createFromAsset(getAssets(),"noto_sans_bold.otf");
            }
        }

        setGlobalFont(getWindow().getDecorView());
    }

    private void setGlobalFont(View view){
        if (view != null){
            if (view instanceof ViewGroup){
                ViewGroup viewGroup = (ViewGroup)view;

                int cnt = viewGroup.getChildCount();

                for (int i = 0; i < cnt; i++){
                    View v = viewGroup.getChildAt(i);

                    if (v instanceof TextView){
                        ((TextView) v).setTypeface(typeface);
                        ((TextView) v).setIncludeFontPadding(false);
                    }
                    setGlobalFont(v);
                }
            }
        }
    }
}
