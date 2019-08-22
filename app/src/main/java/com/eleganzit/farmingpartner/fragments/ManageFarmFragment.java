package com.eleganzit.farmingpartner.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eleganzit.farmingpartner.HomeActivity;
import com.eleganzit.farmingpartner.R;
import com.eleganzit.farmingpartner.adapter.ManageMyFarmAdapter;
import com.eleganzit.farmingpartner.model.FarmSlotsData;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageFarmFragment extends Fragment {
    RecyclerView rc_farms;
ArrayList<FarmSlotsData> arrayList=new ArrayList();
    public ManageFarmFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manage_farm, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_farms=view.findViewById(R.id.rc_farms);
        rc_farms.setAdapter(new ManageMyFarmAdapter(arrayList,getActivity(),""));

        HomeActivity.home_title.setText("Manage Farm");

    }
}
