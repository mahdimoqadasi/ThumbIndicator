package com.trex.lib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.TypedValue;

import java.util.ArrayList;
import java.util.List;

public class ThumbIndicator extends ViewPager {
    private ArrayList<String> mUrls;
    private List<Integer> mResources;

    public ThumbIndicator(@NonNull Context context) {
        super(context);
    }

    public ThumbIndicator(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void setupWithViewPager(ViewPager mMainViewPager, ArrayList<String> urls, float thumbSizeInDp) {
        this.mUrls = urls;
        prepare(mMainViewPager, thumbSizeInDp);
    }

    public void setupWithViewPager(ViewPager mMainViewPager, List<Integer> drawableResources, float thumbSizeInDp) {
        this.mResources = drawableResources;
        prepare(mMainViewPager, thumbSizeInDp);
    }

    void prepare(final ViewPager vp, final float size) {
        OnThumbClickListener callBack = new OnThumbClickListener() {
            @Override
            public void onThumbClick(int pos) {
                vp.setCurrentItem(pos);
                setCurrentItem(pos);
            }
        };
        IndicatorAdapter mAdp;
        if (mUrls != null)
            mAdp = new IndicatorAdapter(mUrls, callBack);
        else
            mAdp = new IndicatorAdapter(mResources, callBack);
        setAdapter(mAdp);
        setPageMargin(dpToPixel(4));
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                setCurrentItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                vp.setCurrentItem(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });
        setClipToPadding(false);
        int padding = (getResources().getDisplayMetrics().widthPixels / 2) - (dpToPixel(size) / 2);
        setPadding(padding, 0, padding, 0);
    }

    private int dpToPixel(float dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    public interface OnThumbClickListener {
        void onThumbClick(int pos);
    }
}