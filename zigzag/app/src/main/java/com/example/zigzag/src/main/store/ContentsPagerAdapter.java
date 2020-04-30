package com.example.zigzag.src.main.store;

import com.example.zigzag.src.main.store.favorite.FavoriteFragment;
import com.example.zigzag.src.main.store.ranking.RankingFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ContentsPagerAdapter extends FragmentStatePagerAdapter {
    private  int mPageCount;

    public ContentsPagerAdapter(FragmentManager fm,int behavior,int pageCount) {
        super(fm,behavior);
        this.mPageCount=pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                RankingFragment rankingFragment = new RankingFragment();
                return rankingFragment;
            case 1:
                FavoriteFragment favoriteFragment = new FavoriteFragment();
                return favoriteFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mPageCount;
    }
}
