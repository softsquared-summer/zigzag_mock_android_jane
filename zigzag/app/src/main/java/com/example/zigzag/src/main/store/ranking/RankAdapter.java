package com.example.zigzag.src.main.store.ranking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.zigzag.R;
import com.example.zigzag.src.bascket.models.BasketResponse;
import com.example.zigzag.src.main.store.ranking.models.RankResponse;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {

    private ArrayList<RankResponse.RankResult> mListProduct;
    private Context mContext;
    public OnItemClickListener mOnItemClickListener = null;

    public RankAdapter(ArrayList<RankResponse.RankResult> mListProduct) {
        this.mListProduct = mListProduct;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, RankResponse.RankResult productVO);
    }


    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }


    @NonNull
    @Override
    public RankAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rank, parent, false);

        return new ViewHolder(convertView);

    }

    @Override
    public void onBindViewHolder(@NonNull RankAdapter.ViewHolder holder, int position) {

        final RankResponse.RankResult productVO = mListProduct.get(position);



        String url1 = productVO.getImage_url();
        //String url2=productVO.getImage().get(0).getImage_url2();

        holder.mRankNum.setText(position+1+"");
        holder.mImage.setImageResource(R.drawable.default_image);

        if(productVO.getTags().size()>0){
            //String tagtmp=holder.mTag.getText().toString();
            holder.mTag.setText("10대/20대 " +productVO.getTags().get(0).getTag_name());

        }else{
            holder.mTag.setText("10대/20대");
            holder.mTag.setTextColor(Color.parseColor("#8F9DF8"));
        }

        //이미지 둥글게
        holder.mImage.setBackground(new ShapeDrawable(new OvalShape()));
        holder.mImage.setClipToOutline(true);

        System.out.println(url1);
//        Glide.with(mContext)
//                .load(url1)
//                .thumbnail(0.5f)
//                .into(holder.mImage);
//        holder.mImage.setClipToOutline(true);
//        holder.mImage.setScaleType(ImageView.ScaleType.FIT_XY);


        holder.mStoreName.setText(productVO.getMall_name());
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
        private TextView mStoreName,mRankNum,mTag;
        private ImageView mImage;


        public ViewHolder(View convertView) {
            super(convertView);

            mLayoutProduct = (ConstraintLayout) convertView.findViewById(R.id.rankitem_cl_layout);
            //img_thumb = (ImageView) convertView.findViewById(R.id.img_thumb);
            mStoreName = (TextView) convertView.findViewById(R.id.rankitem_tv_name);
            mRankNum = (TextView) convertView.findViewById(R.id.rankitem_tv_num);
            mTag = (TextView) convertView.findViewById(R.id.rankitem_tv_tag);
            mImage = (ImageView) convertView.findViewById(R.id.rankitem_iv_img);


        }
    }
}
