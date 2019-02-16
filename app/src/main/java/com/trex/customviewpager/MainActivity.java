package com.trex.customviewpager;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.trex.lib.ThumbIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Integer> mImgs;
    ViewPager mVpMain;
    ThumbIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        def();
        setup();
    }

    private void def() {
        mVpMain = findViewById(R.id.vpMain);
        mIndicator = findViewById(R.id.indicator);
        mImgs = getMockImgs();
    }

    private List<Integer> getMockImgs() {
        List<Integer> tmp = new ArrayList<>();
        tmp.add(R.drawable.a);
        tmp.add(R.drawable.b);
        tmp.add(R.drawable.c);
        tmp.add(R.drawable.d);
        tmp.add(R.drawable.e);
        return tmp;
    }

    private void setup() {
        VpAdapter adp = new VpAdapter();
        mVpMain.setAdapter(adp);
        mIndicator.setupWithViewPager(mVpMain, mImgs, 70);
    }

    class VpAdapter extends PagerAdapter {
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            View v = getLayoutInflater().inflate(R.layout.thumb_item, container, false);
            ImageView imgSlider = v.findViewById(R.id.imgSlider);
            imgSlider.setImageResource(mImgs.get(position));
            container.addView(v);
            return v;
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
            return mImgs.size();
        }
    }
}