package com.example.zigzag.src.outer.content;

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
import com.example.zigzag.src.outer.content.interfaces.ContentActivityView;
import com.example.zigzag.src.outer.content.models.ItemsResponse;
import com.example.zigzag.src.product.ProductActivity;

import java.util.ArrayList;

public class ContentFragment extends Fragment implements ContentActivityView,ContentAdapter.OnItemClickListener{
    private int mCategoryNum;
    private String mBigCategory;
    private ArrayList<ItemsResponse.ItemsResult> mProductList=new ArrayList<ItemsResponse.ItemsResult>();

    private RecyclerView mRvContent;
    private ContentAdapter mContentAdapter;

    private ContentService mContentService;

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
        mContentService=new ContentService(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_content, container, false);
        mProductList=new ArrayList<ItemsResponse.ItemsResult>();

        //api데이터 불러오기

        initView(view);

        tryGetItemList();


        return view;
    }


    //아이템 리스트 조회
    private void tryGetItemList() {

        mContentService.getItemList();

    }

    void initView(View view){
        mRvContent=(RecyclerView)view.findViewById(R.id.content_rv_list);
        RecyclerView.LayoutManager mLayoutManager=new GridLayoutManager(getContext(),3);

        mRvContent.setLayoutManager(mLayoutManager);
        mRvContent.addItemDecoration(new ItemDecoration(getActivity()));
        mContentAdapter=new ContentAdapter(mProductList,getContext());


        mContentAdapter.setOnItemClickListener(this);
        mRvContent.setAdapter(mContentAdapter);

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
    public void getItemsSuccess(boolean isSuccess, int code, String message, ArrayList<ItemsResponse.ItemsResult> resultProducts) {
        if (isSuccess) {
            if (code == 100) {
                System.out.println("아이템 리스트 조회 성공");
                //activity.showCustomToast(message);
                mProductList.clear();
                mProductList.addAll(resultProducts);
                mContentAdapter.notifyDataSetChanged();

                System.out.println("아이템 리스트 조회 결과:"+mProductList.toString());
            }
        }

    }


    public class ItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private int outerMargin;

        public ItemDecoration(Activity mActivity) {

            spanCount = 3;
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
