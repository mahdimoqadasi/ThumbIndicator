package com.trex.thumbindicator;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
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
        mUrls = getMockImgs();
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

    private void setup() {
        VpAdapter adp = new VpAdapter();
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