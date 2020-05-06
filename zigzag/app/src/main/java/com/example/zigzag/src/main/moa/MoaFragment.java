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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.zigzag.R;
import com.example.zigzag.src.bascket.BascketActivity;
import com.example.zigzag.src.outer.OuterActivity;


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

        //이미지 둥글게
        //setRoundImage(view);

        mPagerAdapter = new ScreenSlidePagerAdapter(getActivity().getSupportFragmentManager(),Ad_PAGES);
        mAdPager.setAdapter(mPagerAdapter);



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
}
