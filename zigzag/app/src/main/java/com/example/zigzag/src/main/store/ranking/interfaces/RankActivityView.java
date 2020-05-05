package com.example.zigzag.src.main.store.ranking.interfaces;


import com.example.zigzag.src.main.models.SignInResponse;
import com.example.zigzag.src.main.store.ranking.models.RankResponse;

import java.util.ArrayList;

public interface RankActivityView {

    void validateSuccess(String text);

    void validateFailure(String message);

    //로그인 성공 시, reuslt 자체를 넘긴다.
    void validateRank(boolean isSuccess, int code, String message, ArrayList<RankResponse.RankResult> rankResult);

}
