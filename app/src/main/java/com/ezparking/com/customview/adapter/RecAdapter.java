package com.ezparking.com.customview.adapter;

import android.provider.ContactsContract;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ezparking.com.customview.R;

import java.util.List;

/**
 * Created by zyh
 */

public class RecAdapter<T> extends RecyclerView.Adapter {

   private final int V_TYPE_GROUP_ITEM = 0;
   private final int V_TYPE_CHILD_ITEM = 1;
   private List<T> datas;

    public RecAdapter(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(V_TYPE_GROUP_ITEM == viewType){
           View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_group_layout,parent,false);
           return new Group_ViewHolder(itemView);
        }else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_child_layout,parent,false);
            return new Child_ViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if(V_TYPE_GROUP_ITEM == getItemViewType(position)){
            Group_ViewHolder viewHolder = (Group_ViewHolder) holder;
            viewHolder.ctv.setText("第"+ position /10+ "组");
        }else if(V_TYPE_CHILD_ITEM == getItemViewType(position)){
            Child_ViewHolder viewHolder = (Child_ViewHolder) holder;
            viewHolder.ctv.setText("第 "+ position +" 个item");
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class Child_ViewHolder extends RecyclerView.ViewHolder{
        TextView  ctv;
        public Child_ViewHolder(View itemView) {
            super(itemView);
            ctv = itemView.findViewById(R.id.tv_item_child);
        }
    }
    class Group_ViewHolder extends RecyclerView.ViewHolder{
          TextView ctv;
        public Group_ViewHolder(View itemView) {
            super(itemView);
            ctv = itemView.findViewById(R.id.tv_item_group);
        }
    }
}
