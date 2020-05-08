package com.example.zigzag.src.order_completed.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OrderResponse {


    public class OrderResult {

        @SerializedName("date")
        private String date;
        @SerializedName("completion_id")
        private int completionId;
        @SerializedName("list")
        private OrderItem orderItems;

        public String getDate() {
            return date;
        }

        public int getCompletionId() {
            return completionId;
        }

        public OrderItem getOrderItems() {
            return orderItems;
        }
    }
    @SerializedName("result")
    private ArrayList<OrderResult> ordersResult;

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

    public ArrayList<OrderResult> getOrdersResult() {
        return ordersResult;
    }

    public boolean isIs_success() {
        return is_success;
    }


}
