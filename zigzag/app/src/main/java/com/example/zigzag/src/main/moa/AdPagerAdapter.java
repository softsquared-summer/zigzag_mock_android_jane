package com.example.zigzag.src.main.moa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.zigzag.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class AdPagerAdapter extends PagerAdapter {
    private Context context = null;
    private ArrayList<Integer> images=new ArrayList<>();

    public AdPagerAdapter(Context context, ArrayList<Integer> images){
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        //poition 값을 받아 주어진 위치에 페이지 생성
        View view = null;

        if(context != null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.banner_ad, container, false);

            ImageView imageView = view.findViewById(R.id.banner_iv_image);
            imageView.setImageResource(images.get(position));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        }

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }
}