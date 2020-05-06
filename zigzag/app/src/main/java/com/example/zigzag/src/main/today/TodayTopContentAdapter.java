package com.example.zigzag.src.main.today;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zigzag.R;
import com.example.zigzag.src.main.today.models.ItemsResponse;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class TodayTopContentAdapter extends RecyclerView.Adapter<TodayTopContentAdapter.ViewHolder> {

    private ArrayList<ItemsResponse.ItemsResult> mListProduct;
    private Context mContext;
    public OnItemClickListener mOnItemClickListener = null;
    private String mCategory;


    public TodayTopContentAdapter(ArrayList<ItemsResponse.ItemsResult> mListProduct, Context mContext) {
        this.mListProduct = mListProduct;

        this.mContext = mContext;
        this.mCategory = mCategory;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, ItemsResponse.ItemsResult productVO);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    @NonNull
    @Override
    public TodayTopContentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.item_product3, parent, false);

        return new ViewHolder(convertView);

    }

    @Override
    public void onBindViewHolder(@NonNull TodayTopContentAdapter.ViewHolder holder, int position) {

        final ItemsResponse.ItemsResult productVO = mListProduct.get(position);


        String url1 = productVO.getImage().getImage_url1();
        //String url2=productVO.getImage().get(0).getImage_url2();
        holder.mImage.setImageResource(R.drawable.default_image);

        //이미지 둥글게
        GradientDrawable drawable=
                (GradientDrawable) mContext.getDrawable(R.drawable.round_shape_transparent);
        holder.mImage.setBackground(drawable);
        holder.mImage.setClipToOutline(true);



//        System.out.println(url1);
//        Glide.with(mContext)
//                .load(url1)
//                .thumbnail(0.5f)
//                .into(holder.mImage);
//        holder.mImage.setClipToOutline(true);
//        holder.mImage.setScaleType(ImageView.ScaleType.FIT_XY);


        if (productVO.getIs_free_ship().equals("Y")) {
            holder.mFreeShip.setVisibility(View.VISIBLE);
        }
        if (productVO.getIs_heart().equals("Y")) {
            holder.mZzim.setImageResource(R.drawable.product_zzim_yes);
        } else {
            holder.mZzim.setImageResource(R.drawable.product_zzim_no);
        }
        holder.mStoreName.setText(productVO.getMall_name());
        holder.mProductName.setText(productVO.getItem_name());
        holder.mPrice.setText(productVO.getPrice());
        holder.mLayoutProduct.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                mOnItemClickListener.onItemClick(v, productVO);

            }

        });
    }


    @Override
    public int getItemCount() {
        return mListProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ConstraintLayout mLayoutProduct;
        private ImageView mImageUrl;
        private TextView mStoreName;
        private TextView mProductName;
        private ImageView mZzim;
        private ImageView mFreeShip;
        private TextView mPrice;
        private ImageView mImage;

        public ViewHolder(View convertView) {
            super(convertView);

            mLayoutProduct = (ConstraintLayout) convertView.findViewById(R.id.product_ll_layout);
            //img_thumb = (ImageView) convertView.findViewById(R.id.img_thumb);
            mStoreName = (TextView) convertView.findViewById(R.id.product_tv_storename);
            mProductName = (TextView) convertView.findViewById(R.id.product_tv_productname);
            mFreeShip = (ImageView) convertView.findViewById(R.id.product_iv_freedelivery);
            mZzim = (ImageView) convertView.findViewById(R.id.product_iv_zzim);
            mPrice = (TextView) convertView.findViewById(R.id.product_tv_price);
            mImage = (ImageView) convertView.findViewById(R.id.product_iv_image);

        }
    }
}
