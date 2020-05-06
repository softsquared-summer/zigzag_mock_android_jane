package com.example.zigzag.src.main.store.ranking;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zigzag.R;
import com.example.zigzag.src.bascket.BasketAdapter;
import com.example.zigzag.src.main.store.ranking.interfaces.RankActivityView;
import com.example.zigzag.src.main.store.ranking.models.RankResponse;

import java.util.ArrayList;


public class RankingFragment extends Fragment implements RankActivityView {

    private ArrayList<RankResponse.RankResult> mRankList=new ArrayList<>();
    private RankAdapter mRankAdapter;
    private RecyclerView mRvRank;
    private RankingService rankService;

    public RankingFragment() {
    }

    public static RankingFragment newInstance(String param1, String param2) {
        RankingFragment fragment = new RankingFragment();
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

        View view= inflater.inflate(R.layout.fragment_ranking, container, false);

        initView(view);

        //랭킹 조회 통신 시작
        getStoreRank();
        return view;
    }

    private void getStoreRank() {
        rankService.getStoreRank();
    }

    private void initView(View view) {
        rankService=new RankingService(this);
        mRvRank=view.findViewById(R.id.rank_rv_storelist);

        //mRvRank.addItemDecoration(new DividerItemDecoration(getContext(), R.drawable.rank_divider));

        DividerItemDecoration dividerItemDecoration =
                new DividerItemDecoration(getContext(),
                        new LinearLayoutManager(getContext()).getOrientation());
        dividerItemDecoration.setDrawable(getResources().getDrawable(R.drawable.rank_dividor));
        mRvRank.addItemDecoration(dividerItemDecoration);
        mRvRank.addItemDecoration(new DividerItemDecoration(getContext(), 1));
        mRvRank.setLayoutManager(new LinearLayoutManager(getContext())) ;
        mRankAdapter=new RankAdapter(mRankList);
        mRvRank.setAdapter(mRankAdapter);

    }

    @Override
    public void validateSuccess(String text) {

    }

    @Override
    public void validateFailure(String message) {

    }

    @Override
    public void validateRank(boolean isSuccess, int code, String message,
                             ArrayList<RankResponse.RankResult> rankResult) {
        if (isSuccess) {
            if (code == 100) {
                System.out.println("스토어 랭킹 리스트 조회 성공");
                mRankList.clear();

                mRankList.addAll(rankResult);

                mRankAdapter.notifyDataSetChanged();

                System.out.println("스토어 리스트 조회 결과:"+mRankList.toString());
            }
        }
    }
}
