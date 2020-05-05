package com.example.zigzag.src.main.store;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.zigzag.R;
import com.example.zigzag.src.bascket.BascketActivity;
import com.example.zigzag.src.main.MainActivity;
import com.google.android.material.tabs.TabLayout;

public class StoreFragment extends Fragment implements View.OnClickListener {


    MainActivity activity;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private ContentsPagerAdapter mContentPagerAdapter;


    private ImageButton mBtnBascket;

    public StoreFragment() {
        // Required empty public constructor
    }


    public static StoreFragment newInstance(String param1, String param2) {
        StoreFragment fragment = new StoreFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_store, container, false);

        initView(view);
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("랭킹")));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(createTabView("즐겨찾기")));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        mViewPager = (ViewPager) view.findViewById(R.id.store_vp_body);
        mContentPagerAdapter = new ContentsPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mTabLayout.getTabCount());
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


        return view;
    }

    void initView(View view){
        mBtnBascket=view.findViewById(R.id.store_ib_top3);
        mTabLayout = (TabLayout) view.findViewById(R.id.store_tl_tab);

        mBtnBascket.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.store_ib_top3:
                Intent intent = new Intent(getActivity(), BascketActivity.class);
                startActivity(intent);
                break;
            default:
                break;

        }
    }
    private View createTabView(String tabName){
        View tabView = LayoutInflater.from(activity).inflate(R.layout.custom_tab, null);
        TextView txt_name = (TextView) tabView.findViewById(R.id.txt_name);
        txt_name.setText(tabName);

        return tabView;
    }
}
