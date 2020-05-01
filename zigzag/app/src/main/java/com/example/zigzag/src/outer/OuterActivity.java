package com.example.zigzag.src.outer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.zigzag.R;
import com.google.android.material.tabs.TabLayout;

public class OuterActivity extends AppCompatActivity {
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private TextView mTextTop;
    private ContentsPagerAdapter mContentPagerAdapter;
    private String mWhatBigCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outer);

        initView();

    }

    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.outer_tl_tab);

        System.out.println("아우터 탭 메뉴 생성");

        mWhatBigCategory = getIntent().getStringExtra("category");
        mViewPager = (ViewPager) findViewById(R.id.outer_vp_list);
        mTextTop=findViewById(R.id.outer_tv_title);

        //탭레이아웃 메뉴 추가
        makeTab(mWhatBigCategory, mTabLayout);

        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        mContentPagerAdapter = new ContentsPagerAdapter(mWhatBigCategory,getSupportFragmentManager(), mTabLayout.getTabCount());

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

    private void makeTab(String mWhatBigCategory, TabLayout mTabLayout) {
        switch (mWhatBigCategory) {
            case "outer":
                mTextTop.setText("아우터");
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("전체")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("가디건")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("자켓")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("코트")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("점퍼")));
                break;
            case "top":
                mTextTop.setText("상의");
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("전체")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("티셔츠")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("블라우스")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("셔츠/남방")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("니트")));
                break;
            case "onepiece":
                mTextTop.setText("원피스");
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("전체")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("미니")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("미디")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("롱")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("투피스")));
                break;
            case "pants":
                mTextTop.setText("바지");
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("전체")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("일자")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("슬랙스")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("반바지")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("와이드")));

                break;
            case "skirt":
                mTextTop.setText("스커트");
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("전체")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("미니스커트")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("미디스커트")));
                mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("롱스커트")));

                break;
            default:
                break;

        }
    }

    private View createTabView(String tabName) {
        View tabView = LayoutInflater.from(this).inflate(R.layout.custom_tab, null);
        TextView txt_name = (TextView) tabView.findViewById(R.id.txt_name);
        txt_name.setTextColor(getResources().getColor(R.color.rankNumColor));
        txt_name.setText(tabName);

        return tabView;
    }
}
