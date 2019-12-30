package com.bawei.day1230.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei.day1230.R;
import com.bawei.day1230.model.entity.ProductEntity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * ProjectName: Day1230
 * PackageName: com.bawei.day1230.view
 * ClassName:   MyRecyclerAdapter
 * Description: Java类的作用
 * Author: Lazy
 * CreateDate: 2019/12/30_14:09
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.VH> {

    private final Context context;
    private final List<ProductEntity.ResultBean> result;


    public MyRecyclerAdapter(Context context, List<ProductEntity.ResultBean> result) {

        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        VH vh = new VH(View.inflate(context, R.layout.item, null));
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        ProductEntity.ResultBean list = result.get(position);
        Glide.with(context)
                .load(list.getMasterPic())
                .transform(new RoundedCorners(12))
                .into(holder.ivShow);

        holder.tvName.setText(list.getCommodityName());
        holder.tvPrice.setText("￥:" + list.getPrice());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (addOnItemClickListener != null) {
                    addOnItemClickListener.clickListener(list,position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    class VH extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_show)
        ImageView ivShow;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        public VH(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }





    private AddOnItemClickListener addOnItemClickListener;


    public void setAddOnItemClickListener(AddOnItemClickListener addOnItemClickListener) {
        this.addOnItemClickListener = addOnItemClickListener;
    }

    public interface AddOnItemClickListener{
        void clickListener(ProductEntity.ResultBean list,int position);
    }



}
