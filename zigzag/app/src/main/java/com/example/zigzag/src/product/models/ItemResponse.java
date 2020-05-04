package com.example.zigzag.src.product.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemResponse {


    public ItemResult getItemResult() {
        return itemResult;
    }

    public class ItemResult {
        @SerializedName("id")
        private int item_id;
        @SerializedName("mall_name")
        private String mall_name;

        @SerializedName("image")
        private ArrayList<ImageURL> image;
        @SerializedName("comment_num")
        private int comment_num;

        @SerializedName("is_heart")
        private String is_heart;
        @SerializedName("item_name")
        private String item_name;
        @SerializedName("discount")
        private String discount;
        @SerializedName("price")
        private String price;

        public int getComment_num() {
            return comment_num;
        }

        public ArrayList<ImageURL> getImage() {
            return image;
        }



        public int getItem_id() {
            return item_id;
        }

        public String getMall_name() {
            return mall_name;
        }

        public String getIs_heart() {
            return is_heart;
        }


        public String getItem_name() {
            return item_name;
        }

        public String getDiscount() {
            return discount;
        }

        public String getPrice() {
            return price;
        }


        public  class ImageURL {
            @SerializedName("image_url1")
            private String image_url1;
            @SerializedName("image_url2")
            private String image_url2;

            public String getImage_url1() {
                return image_url1;
            }

            public String getImage_url2() {
                return image_url2;
            }
        }
    }
    @SerializedName("result")
    private ItemResult itemResult;

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }



    public boolean isSuccess() {
        return isSuccess;
    }

}
