package com.example.zigzag.src.bascket.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BasketResponse {


    public class BasketResult {

        @SerializedName("num")
        private int num;
        @SerializedName("list")
        private ArrayList<BasketItem> list;

        public int getNum() {
            return num;
        }

        public ArrayList<BasketItem> getList() {
            return list;
        }

        public class BasketItem {
            @SerializedName("item_id")
            private int item_id;
            @SerializedName("mall_name")
            private String mall_name;
            @SerializedName("item_name")
            private String item_name;
            @SerializedName("image")
            private ImageList image;
            @SerializedName("size")
            private String size;
            @SerializedName("color")
            private String color;
            @SerializedName("num")
            private int num;
            @SerializedName("price")
            private String price;
            @SerializedName("ship")
            private int ship;

            public int getItem_id() {
                return item_id;
            }

            public String getMall_name() {
                return mall_name;
            }

            public String getItem_name() {
                return item_name;
            }

            public ImageList getImage() {
                return image;
            }

            public String getSize() {
                return size;
            }

            public String getColor() {
                return color;
            }

            public int getNum() {
                return num;
            }

            public String getPrice() {
                return price;
            }

            public int getShip() {
                return ship;
            }

            public class ImageList {
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
    }
    @SerializedName("result")
    private ArrayList<BasketResult> basketsResult;

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


    public ArrayList<BasketResult> getBasketsResult() {
        return basketsResult;
    }

    public boolean isIs_success() {
        return is_success;
    }


}
