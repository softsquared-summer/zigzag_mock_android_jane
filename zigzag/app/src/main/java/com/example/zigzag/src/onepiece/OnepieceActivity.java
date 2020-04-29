package com.example.zigzag.src.onepiece;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.zigzag.R;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class OnepieceActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ContentsPagerAdapter mContentPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onepiece);

        initView();

    }

    private void initView(){
        mTabLayout = (TabLayout) findViewById(R.id.onepiece_tl_tab);

        System.out.println("원피스 탭 메뉴 생성");
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("전체")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("미니")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("미디")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("롱")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("투피스")));

        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mViewPager = (ViewPager) findViewById(R.id.onepiece_vp_list);

        mContentPagerAdapter = new ContentsPagerAdapter(getSupportFragmentManager(), mTabLayout.getTabCount());

        mViewPager.setAdapter(mContentPagerAdapter);

        mViewPager.addOnPageChangeListener(
                new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));



        //페이지가 변경될 때 알려주는 리스너너
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            //탭이 선택되었을 때, 호출되는 메서드
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
            //탭이 해제되었을 때, 호출되는 메서드
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            //탭이 다시 선택되었을 때, 호출되는 메서드
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }

    private View createTabView(String tabName){
        View tabView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        TextView txt_name = (TextView) tabView.findViewById(R.id.txt_name);
        txt_name.setTextColor(getResources().getColor(R.color.rankNumColor));
        txt_name.setText(tabName);

        return tabView;
    }
}
