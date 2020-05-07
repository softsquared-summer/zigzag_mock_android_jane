package com.example.zigzag.src.order;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zigzag.R;
import com.example.zigzag.src.order.models.OrderItem;
import com.example.zigzag.src.order.models.OrderResponse;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {

    private ArrayList<OrderItem> mListProduct;
    private Context mContext;
    public OnItemClickListener mOnItemClickListener = null;

    public OrderAdapter(ArrayList<OrderItem> mListProduct, Context mContext) {
        this.mContext=mContext;
        this.mListProduct = mListProduct;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, OrderItem productVO);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);

        return new ViewHolder(convertView);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {

        final OrderItem productVO = mListProduct.get(position);


        String url1 = productVO.getImage().getImage_url1();
        //String url2=productVO.getImage().get(0).getImage_url2();

        System.out.println(url1);
//        Glide.with(mContext)
//                .load(url1)
//                .thumbnail(0.5f)
//                .into(holder.mImage);
//        holder.mImage.setClipToOutline(true);
//        holder.mImage.setScaleType(ImageView.ScaleType.FIT_XY);

        //이미지 둥글게게
        holder.mImage.setImageResource(R.drawable.default_image);

        GradientDrawable drawable=
                (GradientDrawable) mContext.getDrawable(R.drawable.round_shape_transparent);
        holder.mImage.setBackground(drawable);
        holder.mImage.setClipToOutline(true);




//        holder.mProductName.setText(productVO.getItem_name());
        holder.mPrice.setText(productVO.getPrice());
        holder.mLayoutProduct.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                mOnItemClickListener.onItemClick(v, productVO);

            }

        });

        holder.mOption.setText(productVO.getColor()+"/"+productVO.getSize());

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
        private ImageView mImage;
        private TextView mOption;

        public ViewHolder(View convertView) {
            super(convertView);

            mLayoutProduct = (ConstraintLayout) convertView.findViewById(R.id.orderitem_ll_layout);
            //img_thumb = (ImageView) convertView.findViewById(R.id.img_thumb);
            mProductName = (TextView) convertView.findViewById(R.id.orderitem_tv_name);
            mPrice = (TextView) convertView.findViewById(R.id.orderitem_tv_price);
            mImage = (ImageView) convertView.findViewById(R.id.orderitem_iv_image);
            mOption = (TextView) convertView.findViewById(R.id.orderitem_tv_option);
            mAmount = (TextView) convertView.findViewById(R.id.orderitem_tv_amount);
            mShip = (TextView) convertView.findViewById(R.id.orderitem_tv_delivery_price);


        }
    }
}
