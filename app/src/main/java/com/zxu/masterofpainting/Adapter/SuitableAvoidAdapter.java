package com.zxu.masterofpainting.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zxu.masterofpainting.Constants;
import com.zxu.masterofpainting.R;
import com.zxu.masterofpainting.bean.SuitableAvoidItem;

import java.util.ArrayList;
import java.util.List;

public class SuitableAvoidAdapter extends RecyclerView.Adapter<SuitableAvoidAdapter.ViewHolder> {
    private List<SuitableAvoidItem> mSuitableAvoidItemList;

    public SuitableAvoidAdapter(List<SuitableAvoidItem> mSuitableAvoidItemList) {
        this.mSuitableAvoidItemList = mSuitableAvoidItemList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_suitable_avoid, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SuitableAvoidItem suitableAvoidItem = mSuitableAvoidItemList.get(position);
        holder.collocationDrawView.setImageURI("http://site.meishij.net/r/184/180/4420184/s4420184_148098411711646.jpg");
        holder.collocationName.setText(Constants.ingredientsName+" + "+suitableAvoidItem.getCollocationName());
        holder.collocationEfficacy.setText(suitableAvoidItem.getCollocationEfficacy());
    }

    @Override
    public int getItemCount() {
        return mSuitableAvoidItemList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView collocationDrawView;
        TextView collocationName;
        TextView collocationEfficacy;

        public ViewHolder(View itemView) {
            super(itemView);
            collocationDrawView = (SimpleDraweeView) itemView.findViewById(R.id.collocation_image_view);
            collocationName = (TextView) itemView.findViewById(R.id.collocation_name);
            collocationEfficacy = (TextView) itemView.findViewById(R.id.collocation_efficacy);
        }
    }
}
