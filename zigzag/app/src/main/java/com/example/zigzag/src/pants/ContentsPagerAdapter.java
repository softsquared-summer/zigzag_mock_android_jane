package com.example.zigzag.src.pants;

import com.example.zigzag.src.pants.content.ContentFragment;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ContentsPagerAdapter extends FragmentStatePagerAdapter {
    private  int mPageCount;

    public ContentsPagerAdapter(FragmentManager fm, int pageCount) {
        super(fm);
        this.mPageCount=pageCount;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {

            case 0:
                System.out.println("전체 탭");
                ContentFragment contentFragment=new ContentFragment(0);
                System.out.println("전체 탭");
                return contentFragment;
            case 1:
                contentFragment=new ContentFragment(1);
                return contentFragment;

            case 2:
                contentFragment=new ContentFragment(2);
                return contentFragment;
            case 3:
                contentFragment=new ContentFragment(3);
                return contentFragment;
            case 4:
                contentFragment=new ContentFragment(4);
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
