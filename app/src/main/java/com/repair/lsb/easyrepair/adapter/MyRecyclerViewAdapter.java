package com.repair.lsb.easyrepair.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.repair.lsb.easyrepair.R;
import com.repair.lsb.easyrepair.model.Merchant;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter{
    private Context context;
    private ArrayList<Merchant> data;

    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        public  void onItemClick(View view,int position);
    }

    class MerItemViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,type;
        RatingBar score;
        private MerItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.merImage);
            name = itemView.findViewById(R.id.merName);
            type = itemView.findViewById(R.id.merType);
            score = itemView.findViewById(R.id.score);
        }
    }
    public MyRecyclerViewAdapter(Context context, ArrayList<Merchant> data) {
    this.context = context;
    this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.mer_item,viewGroup,false);
        return new MerItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
       Merchant merchant = data.get(i);

       if(onItemClickListener!=null){
           viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   int position = viewHolder.getLayoutPosition();
                  onItemClickListener.onItemClick(viewHolder.itemView,position);
               }
           });
       }

       if(viewHolder instanceof MerItemViewHolder){
           MerItemViewHolder merItemViewHolder = (MerItemViewHolder)viewHolder;
           Glide.with(context).load(merchant.getImage()).apply(new RequestOptions().centerCrop()).into(merItemViewHolder.imageView);
           merItemViewHolder.name.setText(merchant.getName());
           merItemViewHolder.type.setText(merchant.getType());
           merItemViewHolder.score.setRating(merchant.getScore());
       }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
