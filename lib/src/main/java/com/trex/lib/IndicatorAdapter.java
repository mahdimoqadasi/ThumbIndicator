package com.trex.lib;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

class IndicatorAdapter extends PagerAdapter {
    private ThumbIndicator.OnThumbClickListener currentSetter;
    private ArrayList<String> mUrls;
    private List<Integer> mResources;

    IndicatorAdapter(ArrayList<String> urls, ThumbIndicator.OnThumbClickListener currentSetter) {
        this.currentSetter = currentSetter;
        this.mUrls = urls;
    }

    IndicatorAdapter(List<Integer> resources, ThumbIndicator.OnThumbClickListener currentSetter) {
        this.currentSetter = currentSetter;
        this.mResources = resources;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View v = LayoutInflater.from(container.getContext()).inflate(R.layout.thumb_item, container, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentSetter.onThumbClick(position);
            }
        });
        ImageView imgSlider = v.findViewById(R.id.imgSlider);
        if (mUrls != null) {
            Glide.with(container.getContext()).load(mUrls.get(position)).into(imgSlider);
        } else
            imgSlider.setImageResource(mResources.get(position));
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
        return mUrls != null ? mUrls.size() : mResources.size();
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (mUrls != null) {
            if (mUrls.indexOf(object) == -1)
                return POSITION_NONE;
            else
                return super.getItemPosition(object);
        } else {
            if (mResources.indexOf(object) == -1)
                return POSITION_NONE;
            else
                return super.getItemPosition(object);
        }
    }
}