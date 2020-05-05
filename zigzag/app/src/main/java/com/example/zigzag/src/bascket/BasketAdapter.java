package com.example.zigzag.src.bascket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zigzag.R;
import com.example.zigzag.src.bascket.models.BasketResponse;
import com.example.zigzag.src.outer.content.models.ItemsResponse;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.ViewHolder> {

    private ArrayList<BasketResponse.BasketResult> mListProduct;
    private Context mContext;
    public OnItemClickListener mOnItemClickListener = null;

    public BasketAdapter(ArrayList<BasketResponse.BasketResult> mListProduct) {
        this.mListProduct = mListProduct;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, BasketResponse.BasketResult productVO);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    @NonNull
    @Override
    public BasketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bascket, parent, false);

        return new ViewHolder(convertView);

    }

    @Override
    public void onBindViewHolder(@NonNull BasketAdapter.ViewHolder holder, int position) {

        final BasketResponse.BasketResult productVO = mListProduct.get(position);


        String url1 = productVO.getImage().getImage_url1();
        //String url2=productVO.getImage().get(0).getImage_url2();

        System.out.println(url1);
        Glide.with(mContext)
                .load(url1)
                .thumbnail(0.5f)
                .into(holder.mImage);
        holder.mImage.setClipToOutline(true);
        holder.mImage.setScaleType(ImageView.ScaleType.FIT_XY);



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
        private ImageButton mBtnDelete;
        private TextView mProductName;
        private TextView mOrderNum;
        private TextView mPrice;
        private ImageView mImage;

        public ViewHolder(View convertView) {
            super(convertView);

            mLayoutProduct = (ConstraintLayout) convertView.findViewById(R.id.basketitem_ll_layout);
            //img_thumb = (ImageView) convertView.findViewById(R.id.img_thumb);
            mProductName = (TextView) convertView.findViewById(R.id.bascketitem_tv_name);
            mPrice = (TextView) convertView.findViewById(R.id.bascketitem_tv_price);
            mImage = (ImageView) convertView.findViewById(R.id.bascketitem_iv_image);
            mBtnDelete = (ImageButton) convertView.findViewById(R.id.bascketitem_ib_cut);


        }
    }
}
