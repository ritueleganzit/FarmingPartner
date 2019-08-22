package com.eleganzit.farmingpartner.adapter;


import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eleganzit.farmingpartner.R;
import com.eleganzit.farmingpartner.model.FarmSlotsData;


import java.util.ArrayList;

public class ManageMyFarmAdapter extends RecyclerView.Adapter<ManageMyFarmAdapter.MyViewHolder> {

    ArrayList<FarmSlotsData> arrayList;
    Context context;
    String farm_id;

    public ManageMyFarmAdapter(ArrayList<FarmSlotsData> arrayList, Context context, String farm_id) {
        this.arrayList = arrayList;
        this.context = context;
        this.farm_id = farm_id;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.manage_my_farm_layout,viewGroup,false);

        MyViewHolder myViewHolder=new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {



        public MyViewHolder(@NonNull View itemView) {

            super(itemView);



        }
    }

}

