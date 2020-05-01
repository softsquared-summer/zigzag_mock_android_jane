package com.example.zigzag.src.outer.content;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zigzag.R;
import com.example.zigzag.src.main.MainActivity;
import com.example.zigzag.src.main.store.ContentsPagerAdapter;
import com.example.zigzag.src.outer.OuterActivity;
import com.example.zigzag.src.product.Product;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class ContentFragment extends Fragment implements ContentAdapter.OnItemClickListener {
    private int mCategoryNum;
    private String mBigCategory;
    private ArrayList<Product> mProductList;

    private RecyclerView mRvContent;
    private ContentAdapter mContentAdapter;

    public ContentFragment() {
    }


    public static ContentFragment newInstance(String param1, String param2) {
        ContentFragment fragment = new ContentFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    public ContentFragment(int categoryNum, String bigCategory) {
        this.mCategoryNum=categoryNum;
        this.mBigCategory=bigCategory;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_content, container, false);
        mProductList=new ArrayList<>();
        mProductList.add(new Product(0,"소피아",null,"튤립 스커트",2,"Y","N","60%","50,000"));
        mProductList.add(new Product(0,"소피아",null,"튤립 스커트",2,"Y","N","60%","50,000"));
        mProductList.add(new Product(0,"소피아",null,"튤립 스커트",2,"Y","N","60%","50,000"));
        mProductList.add(new Product(0,"소피아",null,"튤립 스커트",2,"Y","N","60%","50,000"));
        mProductList.add(new Product(0,"소피아",null,"튤립 스커트",2,"Y","N","60%","50,000"));
        mProductList.add(new Product(0,"소피아",null,"튤립 스커트",2,"Y","N","60%","50,000"));
        mProductList.add(new Product(0,"소피아",null,"튤립 스커트",2,"Y","N","60%","50,000"));
        mProductList.add(new Product(0,"소피아",null,"튤립 스커트",2,"Y","N","60%","50,000"));
        mProductList.add(new Product(0,"소피아",null,"튤립 스커트",2,"Y","N","60%","50,000"));


        initView(view);

        return view;
    }

    void initView(View view){
        mRvContent=(RecyclerView)view.findViewById(R.id.content_rv_list);
        RecyclerView.LayoutManager mLayoutManager=new GridLayoutManager(getContext(),3);

        mRvContent.setLayoutManager(mLayoutManager);
        mContentAdapter=new ContentAdapter(mProductList,getContext());

        mContentAdapter.setOnItemClickListener(this);
        mRvContent.setAdapter(mContentAdapter);

    }


    @Override
    public void onItemClick(View view, Product productVO) {
        System.out.println("아이템 클릭: "+productVO.getmItemName());
    }
}
