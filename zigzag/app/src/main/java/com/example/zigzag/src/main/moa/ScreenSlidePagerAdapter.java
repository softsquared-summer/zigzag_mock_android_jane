package com.example.zigzag.src.main.moa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zigzag.src.main.store.favorite.FavoriteFragment;
import com.example.zigzag.src.main.store.ranking.RankingFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private  int mPageCount;


    public ScreenSlidePagerAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        this.mPageCount=pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        position%=5;
        switch (position) {


            case 0:
                Ad1fragment ad1Fragment = new Ad1fragment();
                return ad1Fragment;
            case 1:
                Ad2Fragment ad2Fragment = new Ad2Fragment();
                return ad2Fragment;
            case 2:
                Ad3Fragment ad3Fragment = new Ad3Fragment();
                return ad3Fragment;
            case 3:
                Ad4Fragment ad4Fragment = new Ad4Fragment();
                return ad4Fragment;
            case 4:
                Ad5Fragment ad5Fragment = new Ad5Fragment();
                return ad5Fragment;

            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return mPageCount;
    }
}

