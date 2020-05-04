package com.example.zigzag.src.main.today;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zigzag.R;
import com.example.zigzag.src.main.today.interfaces.TodayActivityView;
import com.example.zigzag.src.main.today.models.ItemsResponse;
import com.example.zigzag.src.product.ProductActivity;

import java.util.ArrayList;


public class TodayFragment extends Fragment implements TodayContentAdapter.OnItemClickListener, TodayActivityView {


    private RecyclerView mRvContent;
    private TodayContentAdapter mTodayContentAdapter;
    private ArrayList<ItemsResponse.ItemsResult> mProductList=new ArrayList<ItemsResponse.ItemsResult>();

    private TodayService todayService;
    public TodayFragment() {
    }

    public static TodayFragment newInstance(String param1, String param2) {
        TodayFragment fragment = new TodayFragment();
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
        View view= inflater.inflate(R.layout.fragment_today, container, false);
        initView(view);

        //아이템 리스트 가져오기
        getItemList();

        return view;
    }

    void initView(View view) {
        todayService=new TodayService(this);
        mRvContent=(RecyclerView)view.findViewById(R.id.today_rv_list);
        RecyclerView.LayoutManager mLayoutManager=new GridLayoutManager(getContext(),3);

        mRvContent.setLayoutManager(mLayoutManager);
        mRvContent.addItemDecoration(new TodayFragment.ItemDecoration(getActivity()));
        mTodayContentAdapter=new TodayContentAdapter(mProductList,getContext());

        mTodayContentAdapter.setOnItemClickListener(this);
        mRvContent.setAdapter(mTodayContentAdapter);

    }
    //아이템 리스트 조회
    private void getItemList() {

        todayService.getItemList();

    }
    @Override
    public void onItemClick(View view, ItemsResponse.ItemsResult productVO) {
        System.out.println("아이템 클릭: "+productVO.getItem_name());

        Intent intent = new Intent(getContext(), ProductActivity.class);
        intent.putExtra("item_id", productVO.getItem_id());
        startActivity(intent);
    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void getItemsSuccess(boolean isSuccess, int code, String message, ArrayList<ItemsResponse.ItemsResult> itemsResult) {
        if (isSuccess) {
            if (code == 100) {
                System.out.println("오늘의 아이템 리스트 조회 성공");
                //activity.showCustomToast(message);
                mProductList.clear();
                mProductList.addAll(itemsResult);
                mTodayContentAdapter.notifyDataSetChanged();

                System.out.println("오늘의 아이템 리스트 조회 결과:"+mProductList.toString());
            }
        }
    }

    public class ItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private int outerMargin;

        public ItemDecoration(Activity mActivity) {

            spanCount = 2;
            spacing = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    12, mActivity.getResources().getDisplayMetrics());
            outerMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                    50, mActivity.getResources().getDisplayMetrics());

        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            int maxCount = parent.getAdapter().getItemCount();
            int position = parent.getChildAdapterPosition(view);
            int column = position % spanCount;
            int row = position / spanCount;
            int lastRow = (maxCount - 1) / spanCount;

            outRect.left = column * spacing / spanCount;
            outRect.right = spacing - (column + 1) * spacing / spanCount;
            outRect.top = spacing * 2;

            if (row == lastRow) {
                outRect.bottom = outerMargin;

            }

        }
    }
}
