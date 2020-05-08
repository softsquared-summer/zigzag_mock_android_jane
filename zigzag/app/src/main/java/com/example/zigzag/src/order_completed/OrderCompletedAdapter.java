package com.example.zigzag.src.order_completed;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.zigzag.R;
import com.example.zigzag.src.order_completed.models.OrderResponse;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class OrderCompletedAdapter extends RecyclerView.Adapter<OrderCompletedAdapter.ViewHolder> {

    private ArrayList<OrderResponse.OrderResult> mListProduct;
    private Context mContext;
    public OnItemClickListener mOnItemClickListener = null;

    public OrderCompletedAdapter(ArrayList<OrderResponse.OrderResult> mListProduct, Context mContext) {
        this.mContext=mContext;
        this.mListProduct = mListProduct;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, OrderResponse.OrderResult productVO);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    @NonNull
    @Override
    public OrderCompletedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);

        return new ViewHolder(convertView);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderCompletedAdapter.ViewHolder holder, int position) {

        final OrderResponse.OrderResult productVO = mListProduct.get(position);


        //String url1 = productVO.getImage().getImage_url1();
        //String url2=productVO.getImage().get(0).getImage_url2();

       // System.out.println(url1);
//        Glide.with(mContext)
//                .load(url1)
//                .thumbnail(0.5f)
//                .into(holder.mImage);
//        holder.mImage.setClipToOutline(true);
//        holder.mImage.setScaleType(ImageView.ScaleType.FIT_XY);

        //이미지 둥글게게
        //이미지 둥글게게
        holder.mImage1.setImageResource(R.drawable.default_image);
        holder.mImage2.setImageResource(R.drawable.default_image2);

        int offtime = new Random().nextInt(200) + 800;
        holder.viewFlipper.setFlipInterval(offtime);
        holder.viewFlipper.startFlipping();

        holder.mWait.setVisibility(View.VISIBLE);


        //이미지 둥글게
        GradientDrawable drawable=
                (GradientDrawable) mContext.getDrawable(R.drawable.round_shape_transparent);
        holder.mImage1.setBackground(drawable);
        holder.mImage1.setClipToOutline(true);
        holder.mImage2.setBackground(drawable);
        holder.mImage2.setClipToOutline(true);


//        holder.mProductName.setText(productVO.getItem_name());
        holder.mPrice.setText(productVO.getOrderItems().getPrice());
        holder.mProductName.setText(productVO.getOrderItems().getItemName());

        DecimalFormat formatter=new DecimalFormat("###,###");
        String mSumPrice=formatter.format(productVO.getOrderItems().getShip());
        holder.mShip.setText(mSumPrice+"원");
        holder.mLayoutProduct.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, productVO);

            }

        });

        String color=productVO.getOrderItems().getColor();
        String size=productVO.getOrderItems().getSize();
        holder.mOption.setText(color+"/"+size);

    }


    @Override
    public int getItemCount() {
        return mListProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout mLayoutProduct;
        private ImageButton mBtnDelete;
        private TextView mProductName,mAmount;
        private TextView mOrderNum;
        private TextView mPrice,mShip;
        private ImageView mImage1,mWait;
        private ImageView mImage2;
        private ViewFlipper viewFlipper;
        private TextView mOption;

        public ViewHolder(View convertView) {
            super(convertView);

            mLayoutProduct = (ConstraintLayout) convertView.findViewById(R.id.orderitem_ll_layout);
            //img_thumb = (ImageView) convertView.findViewById(R.id.img_thumb);
            mProductName = (TextView) convertView.findViewById(R.id.orderitem_tv_name);
            mPrice = (TextView) convertView.findViewById(R.id.orderitem_tv_price);
            mImage1 = (ImageView) convertView.findViewById(R.id.product_iv1);
            mImage2 = (ImageView) convertView.findViewById(R.id.product_iv2);
            viewFlipper = (ViewFlipper)convertView.findViewById(R.id.orderitem_iv_image);
            mOption = (TextView) convertView.findViewById(R.id.orderitem_tv_option);
            mAmount = (TextView) convertView.findViewById(R.id.orderitem_tv_amount);
            mShip = (TextView) convertView.findViewById(R.id.orderitem_tv_delivery_price);
            mWait = (ImageView) convertView.findViewById(R.id.order_iv_wait);


        }
    }
}
