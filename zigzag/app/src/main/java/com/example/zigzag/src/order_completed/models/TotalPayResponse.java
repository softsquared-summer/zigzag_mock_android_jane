package com.example.zigzag.src.order_completed.models;

import com.google.gson.annotations.SerializedName;

public class TotalPayResponse {

    public class TotalPayResult {

        @SerializedName("price")
        private String price;
        @SerializedName("ship")
        private String ship;
        @SerializedName("total")
        private String total;

        public String getPrice() {
            return price;
        }

        public String getShip() {
            return ship;
        }

        public String getTotal() {
            return total;
        }
    }
    @SerializedName("result")
    private TotalPayResult totalPayResult;

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


    public TotalPayResult getTotalPayResult() {
        return totalPayResult;
    }

    public boolean isIs_success() {
        return is_success;
    }


}
