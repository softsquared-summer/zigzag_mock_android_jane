package com.example.zigzag.src.outer.content;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zigzag.R;
import com.example.zigzag.src.product.Product;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder>{

    private ArrayList<Product> mListProduct;
    private Context mContext;
    public OnItemClickListener mOnItemClickListener = null;

    public ContentAdapter(ArrayList<Product> mListProduct, Context mContext) {
        this.mListProduct = mListProduct;
        this.mContext = mContext;
    }


    public interface OnItemClickListener {
        void onItemClick(View view, Product productVO);
    }



    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    @NonNull
    @Override
    public ContentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(mContext).inflate(R.layout.item_product, parent, false);

        return new ViewHolder(convertView);

    }

    @Override
    public void onBindViewHolder(@NonNull ContentAdapter.ViewHolder holder, int position) {
        final Product productVO = mListProduct.get(position);
//
//        Glide.with(mContext)
//                .load(albumVO.getThumb())
//                .thumbnail(0.5f)
//                .into(holder.img_thumb);



        if(productVO.getIsFreeShip().equals("Y")){
            holder.mFreeShip.setVisibility(View.VISIBLE);
        }
        if(productVO.getIsHeart().equals("Y")){
            holder.mZzim.setImageResource(R.drawable.product_zzim_yes);
        }else{
            holder.mZzim.setImageResource(R.drawable.product_zzim_no);
        }
        holder.mStoreName.setText(productVO.getmMallName());
        holder.mProductName.setText(productVO.getmItemName());
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
        private  ImageView mZzim;
        private  ImageView mFreeShip;

        public ViewHolder(View convertView) {
            super(convertView);

            mLayoutProduct = (ConstraintLayout) convertView.findViewById(R.id.product_ll_layout);
            //img_thumb = (ImageView) convertView.findViewById(R.id.img_thumb);
            mStoreName = (TextView) convertView.findViewById(R.id.product_tv_storename);
            mProductName = (TextView) convertView.findViewById(R.id.product_tv_productname);
            mFreeShip=(ImageView)convertView.findViewById(R.id.product_iv_freedelivery);
            mZzim=(ImageView)convertView.findViewById(R.id.product_iv_zzim);

        }
    }
}
