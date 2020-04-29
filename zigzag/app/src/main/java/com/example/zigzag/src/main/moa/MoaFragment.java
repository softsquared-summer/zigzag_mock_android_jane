package com.example.zigzag.src.main.moa;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zigzag.R;
import com.example.zigzag.src.outer.OuterActivity;

public class MoaFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private static final int Ad_PAGES = 100;
    private ViewPager mAdPager;
    private PagerAdapter mPagerAdapter;
    private String mParam1;
    private String mParam2;

    public MoaFragment() {
        // Required empty public constructor
    }


    public static MoaFragment newInstance(String param1, String param2) {
        MoaFragment fragment = new MoaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_moa, container, false);

        // Instantiate a ViewPager and a PagerAdapter.
        mAdPager = (ViewPager) view.findViewById(R.id.moa_vp_ad_top);
        //mAdPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mPagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager(),Ad_PAGES);

        mAdPager.setAdapter(mPagerAdapter);




        return view;
    }





}
