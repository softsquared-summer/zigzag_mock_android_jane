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
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.zigzag.R;
import com.example.zigzag.src.bascket.BascketActivity;
import com.example.zigzag.src.onepiece.OnepieceActivity;
import com.example.zigzag.src.outer.OuterActivity;
import com.example.zigzag.src.pants.PantsActivity;
import com.example.zigzag.src.skirt.SkirtActivity;
import com.example.zigzag.src.top.TopActivity;

public class MoaFragment extends Fragment implements View.OnClickListener {


    private static final int Ad_PAGES = 100;
    private ViewPager mAdPager;
    private PagerAdapter mPagerAdapter;
    private String mParam1;
    private String mParam2;
    private ImageButton mBtnBascket;
    private ImageView mBtnOuter,mBtnTop,mBtnOnepiece,mBtnPants,mBtnSkirt;

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

        mPagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager(),Ad_PAGES);
        mAdPager.setAdapter(mPagerAdapter);



        return view;
    }


    void initView(View view){
        mBtnBascket=view.findViewById(R.id.moa_ib_top1);
        mBtnOuter=view.findViewById(R.id.moa_iv_category_outer);
        mBtnTop=view.findViewById(R.id.moa_iv_category_top);
        mBtnOnepiece=view.findViewById(R.id.moa_iv_category_onepiece);
        mBtnPants=view.findViewById(R.id.moa_iv_category_pants);
        mBtnSkirt=view.findViewById(R.id.moa_iv_category_skirt);
        mAdPager = (ViewPager) view.findViewById(R.id.moa_vp_ad_top);

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
                startActivity(intent);
                break;
            case R.id.moa_iv_category_top:
                intent = new Intent(getActivity(), TopActivity.class);
                startActivity(intent);
                break;
            case R.id.moa_iv_category_onepiece:
                intent = new Intent(getActivity(), OnepieceActivity.class);
                startActivity(intent);
                break;
            case R.id.moa_iv_category_pants:
                intent = new Intent(getActivity(), PantsActivity.class);
                startActivity(intent);
                break;
            case R.id.moa_iv_category_skirt:
                intent = new Intent(getActivity(), SkirtActivity.class);
                startActivity(intent);
                break;
            case R.id.moa_ib_top1:
                intent = new Intent(getActivity(), BascketActivity.class);
                startActivity(intent);
                break;

            default:
                break;

        }
    }
}
