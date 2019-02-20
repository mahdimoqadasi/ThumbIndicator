package com.trex.thumbindicator;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.trex.lib.ThumbIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> mUrls;
    ViewPager mVpMain;
    ThumbIndicator mIndicator;
    private int indexToDelete = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        def();
        setup();
    }

    public void deleteImageAfterScroll(View v) {
        if (indexToDelete != -1) return;
        int position = mVpMain.getCurrentItem();
        if ((position >= 0) && (position < mUrls.size()) && (mUrls.size() > 1)) {
            if (position == 0) {
                mVpMain.setCurrentItem(1, true);
            } else {
                mVpMain.setCurrentItem(position - 1, true);
            }
            indexToDelete = position;
        }

    }

    private void def() {
        mVpMain = findViewById(R.id.vpMain);
        mIndicator = findViewById(R.id.indicator);
        mUrls = getMockImgs();
        mVpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (indexToDelete != -1 && i == ViewPager.SCROLL_STATE_IDLE) {
                    mUrls.remove(indexToDelete);
                    adp.notifyDataSetChanged();
                    mIndicator.notifyDataSetChanged();
                    if (indexToDelete == 0) {
                        mVpMain.setCurrentItem(indexToDelete, false);
                        mIndicator.setCurrentItem(indexToDelete, false);
                    }
                    indexToDelete = -1;
                }
            }
        });
    }


    private List<String> getMockImgs() {
        List<String> tmp = new ArrayList<>();
        tmp.add("http://api.moneyar.com/APIs/images/23599258923.jpg");
        tmp.add("http://api.moneyar.com/APIs/images/23599524312.jpg");
        tmp.add("http://api.moneyar.com/APIs/images/23599660421.jpg");
        tmp.add("http://api.moneyar.com/APIs/images/23599697411.jpg");
        tmp.add("http://api.moneyar.com/APIs/images/23599792108.jpg");
        tmp.add("http://api.moneyar.com/APIs/images/23599993964.jpg");
        return tmp;
    }

    VpAdapter adp;

    private void setup() {
        adp = new VpAdapter();
        mVpMain.setAdapter(adp);
        mIndicator.setupWithViewPager(mVpMain, (ArrayList<String>) mUrls, 70);
    }

    class VpAdapter extends PagerAdapter {
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            View v = getLayoutInflater().inflate(R.layout.thumb_item, container, false);
            ImageView imgSlider = v.findViewById(R.id.imgSlider);
            Glide.with(container.getContext()).load(mUrls.get(position)).into(imgSlider);
            container.addView(v);
            return v;
        }

        @Override
        public int getItemPosition(@NonNull Object object) {
            if (mUrls.indexOf(object) == -1)
                return POSITION_NONE;
            else
                return super.getItemPosition(object);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
            return view == o;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return mUrls.size();
        }
    }
}