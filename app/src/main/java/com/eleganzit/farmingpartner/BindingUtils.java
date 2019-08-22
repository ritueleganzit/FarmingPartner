package com.eleganzit.farmingpartner;



import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.eleganzit.farmingpartner.model.ExcercisePojo;

/**
 * Created by Sumeet on 16-07-2017.
 */

public class BindingUtils {

    @BindingAdapter({"bind:exerciseChooseItems", "bind:layoutId"})
    public static void bindExerciseList(RecyclerView view, ObservableArrayList<ExcercisePojo> list, int layoutId) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false);
        view.setLayoutManager(layoutManager);
        view.setAdapter(new ExcerciseListAdapter(view, list, layoutId));
    }


    @BindingAdapter({"bind:exerciseHorizontalItems", "bind:layoutId"})
    public static void bindHorizontalList(RecyclerView view, ObservableArrayList<ExcercisePojo> list, int layoutId) {

        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 3);
        view.setLayoutManager(layoutManager);
        view.setAdapter(new ExcerciseListAdapter(view, list, layoutId));
    }

}
