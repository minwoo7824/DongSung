package com.kmw.dongsung.Func;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.kmw.dongsung.R;

import java.util.ArrayList;

public class WaitingViewPagerAdapter extends PagerAdapter {

    LayoutInflater inflater;
    ArrayList<WaitingViewPagerItem> itemArrayList = new ArrayList<WaitingViewPagerItem>();
    Context context;

    public WaitingViewPagerAdapter(LayoutInflater inflater, Context context) {
        this.inflater = inflater;
        this.context = context;
    }

    @Override
    public int getCount() {
        return itemArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View)object);
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int i) {

        View view = null;

       view = inflater.inflate(R.layout.view_wait_file_item_layout,null);
       ImageView imgMain = (ImageView)view.findViewById(R.id.img_wait_main);

       Glide.with(context).load(itemArrayList.get(i).getFilePath()).into(imgMain);

        ((ViewPager) container).addView(view);

        return view;
    }

    public void addItem(String filePath) {
        WaitingViewPagerItem waitingItem = new WaitingViewPagerItem();
        waitingItem.setFilePath(filePath);
        itemArrayList.add(waitingItem);
    }
}
