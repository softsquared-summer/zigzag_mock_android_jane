package com.example.zigzag.src.main.store.ranking.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RankResponse {


    public class RankResult {
        @SerializedName("mall_id")
        private int mall_id;
        @SerializedName("image_url")
        private String image_url;
        @SerializedName("is_favorite")
        private String is_favorite;
        @SerializedName("mall_name")
        private String mall_name;
        @SerializedName("mall_rank")
        private int mall_rank;
        @SerializedName("tags")
        private TagList tags;

        public TagList getTags() {
            return tags;
        }

        public int getMall_id() {
            return mall_id;
        }

        public String getImage_url() {
            return image_url;
        }

        public String getIs_favorite() {
            return is_favorite;
        }

        public String getMall_name() {
            return mall_name;
        }

        public int getMall_rank() {
            return mall_rank;
        }


        public class TagList {
            @SerializedName("tag_id")
            private String tag_id;
            @SerializedName("tag_name")
            private String tag_name;



            public String getTag_name() {
                return tag_name;
            }
        }
    }

    @SerializedName("result")
    private ArrayList<RankResult> rankResult;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("is_success")
    private boolean is_success;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


    public boolean isIs_success() {
        return is_success;
    }

    public ArrayList<RankResult> getRankResult() {
        return rankResult;
    }
}
