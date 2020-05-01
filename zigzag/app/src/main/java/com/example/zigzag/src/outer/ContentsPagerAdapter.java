package com.example.zigzag.src.outer;

import com.example.zigzag.src.main.store.favorite.FavoriteFragment;
import com.example.zigzag.src.main.store.ranking.RankingFragment;
import com.example.zigzag.src.outer.content.ContentFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ContentsPagerAdapter extends FragmentStatePagerAdapter {
    private  int mPageCount;
    private String mBigCategory;

    public ContentsPagerAdapter(String bigCategory,FragmentManager fm, int pageCount) {
        super(fm);
        this.mPageCount=pageCount;
        this.mBigCategory=bigCategory;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                System.out.println("전체 탭");
                ContentFragment contentFragment=new ContentFragment(0,mBigCategory);
                System.out.println("전체 탭");
                return contentFragment;
            case 1:
                contentFragment=new ContentFragment(1,mBigCategory);
                return contentFragment;

            case 2:
                contentFragment=new ContentFragment(2,mBigCategory);
                return contentFragment;
            case 3:
                contentFragment=new ContentFragment(3,mBigCategory);
                return contentFragment;
            case 4:
                contentFragment=new ContentFragment(4,mBigCategory);
                return contentFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mPageCount;
    }
}
