package com.example.zigzag.src.main.moa;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.zigzag.R;
import com.example.zigzag.src.bascket.BascketActivity;
import com.example.zigzag.src.outer.OuterActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class MoaFragment extends Fragment implements View.OnClickListener {


    private static final int Ad_PAGES = 100;
    private ViewPager mAdPager;
    private ImageButton mBtnBascket;
    private ImageView mBtnOuter,mBtnTop,mBtnOnepiece,mBtnPants,mBtnSkirt;

    private Timer mTimer;

    private AdPagerAdapter mAdPagerAdapter;
    private ArrayList<Integer> images=new ArrayList<>();
    private int NUM_PAGERS;
    final long DELAY_MS = 3000;
    final long PERIOD_MS = 3000;

    private int mPageNum=0;
    private TabLayout mAdTabLayout;

    public MoaFragment() {
        // Required empty public constructor
    }


    public static MoaFragment newInstance(String param1, String param2) {
        MoaFragment fragment = new MoaFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_moa, container, false);

        initView(view);

        //이미지 둥글게
        //setRoundImage(view);

        //mPagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager(),Ad_PAGES);
        //mAdPager.setAdapter(mPagerAdapter);



        return view;
    }

    private void setRoundImage(View view) {
        //이미지 둥글게
        //GradientDrawable drawable=
        //        (GradientDrawable) getContext().getDrawable(R.drawable.round_shape_transparent);
        //holder.mImage.setBackground(drawable);
        //holder.mImage.setClipToOutline(true);
    }


    void initView(View view){

        //광고이미지
        images.add(R.drawable.ad_top1);
        images.add(R.drawable.ad_top2);
        images.add(R.drawable.ad_top3);
        images.add(R.drawable.ad_top4);
        images.add(R.drawable.ad_top5);

        NUM_PAGERS= images.size();

        mAdPager = (ViewPager) view.findViewById(R.id.moa_vp_ad_top);
        mAdPagerAdapter = new AdPagerAdapter(getContext(), images);
        mAdPager.setAdapter(mAdPagerAdapter);

        //mAdTabLayout = view.findViewById(R.id.moa_ad_selector);
        //mAdTabLayout.setupWithViewPager(mAdPager, true);

        mBtnBascket=view.findViewById(R.id.moa_ib_top1);
        mBtnOuter=view.findViewById(R.id.moa_iv_category_outer);
        mBtnTop=view.findViewById(R.id.moa_iv_category_top);
        mBtnOnepiece=view.findViewById(R.id.moa_iv_category_onepiece);
        mBtnPants=view.findViewById(R.id.moa_iv_category_pants);
        mBtnSkirt=view.findViewById(R.id.moa_iv_category_skirt);

        mBtnBascket.setOnClickListener(this);
        mBtnOuter.setOnClickListener(this);
        mBtnTop.setOnClickListener(this);
        mBtnOnepiece.setOnClickListener(this);
        mBtnPants.setOnClickListener(this);
        mBtnSkirt.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.moa_iv_category_outer:
                Intent intent = new Intent(getActivity(), OuterActivity.class);
                intent.putExtra("category","아우터");
                startActivity(intent);
                break;
            case R.id.moa_iv_category_top:
                intent = new Intent(getActivity(), OuterActivity.class);
                intent.putExtra("category","상의");
                startActivity(intent);
                break;
            case R.id.moa_iv_category_onepiece:
                intent = new Intent(getActivity(), OuterActivity.class);
                intent.putExtra("category","원피스/세트");
                startActivity(intent);
                break;
            case R.id.moa_iv_category_pants:
                intent = new Intent(getActivity(), OuterActivity.class);
                intent.putExtra("category","바지");
                startActivity(intent);
                break;
            case R.id.moa_iv_category_skirt:
                intent = new Intent(getActivity(), OuterActivity.class);
                intent.putExtra("category","스커트");
                startActivity(intent);
                break;
            case R.id.moa_ib_top1:
                //FragmentTransaction transaction= getChildFragmentManager().beginTransaction();;

                intent = new Intent(getActivity(), BascketActivity.class);
                startActivity(intent);

                //transaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_right,R.anim.exit_to_left);

                break;

            default:
                break;

        }
    }


    @Override
    public void onResume() {
        super.onResume();

        //Adapter 세팅 후 타이머 실행
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            @Override
            public void run() {
                mPageNum = mAdPager.getCurrentItem();
                int nextPage = mPageNum + 1;

                if(nextPage >= NUM_PAGERS){
                    nextPage = 0;
                }
                mAdPager.setCurrentItem(nextPage,true);
                mPageNum = nextPage;
            }
        };

        mTimer = new Timer();
        mTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        },DELAY_MS,PERIOD_MS);
    }

    @Override
    public void onPause() {
        super.onPause();
        //다른 액티비티나 프래그먼트 실행시 타이머 제거
        if(mTimer != null){
            mTimer.cancel();
            mTimer = null;
        }
    }

}
