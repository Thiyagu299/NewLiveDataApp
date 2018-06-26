package com.example.android.livedataapp;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.livedataapp.databinding.DataAdapterViewBinding;

import java.util.List;


public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.ViewHolder> {
    Context context;
    List<UserData> userlist;

    public DataListAdapter(Context context, List<UserData> userlist) {
        this.context=context;
        this.userlist=userlist;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DataAdapterViewBinding dataAdapterViewBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.data_adapter_view,parent,false);

        return new ViewHolder(dataAdapterViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UserData userData=userlist.get(position);
        holder.dataAdapterViewBinding.setDataValue(userData);

    }

    @Override
    public int getItemCount() {
        return userlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        DataAdapterViewBinding dataAdapterViewBinding;

        public ViewHolder(DataAdapterViewBinding dataAdapterViewBinding) {
            super(dataAdapterViewBinding.getRoot());
            this.dataAdapterViewBinding=dataAdapterViewBinding;
        }
    }
}
