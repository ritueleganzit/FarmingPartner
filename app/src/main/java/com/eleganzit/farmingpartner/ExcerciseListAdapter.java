package com.eleganzit.farmingpartner;

import android.app.Dialog;
import android.content.ClipData;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import com.eleganzit.farmingpartner.databinding.LayoutChooseExerciseItemBinding;
import com.eleganzit.farmingpartner.model.ExcercisePojo;



public class ExcerciseListAdapter extends RecyclerView.Adapter<ExcerciseListAdapter.MyViewHolder> {
    private ObservableList<ExcercisePojo> exerciseObservableList;
    private Context context;
    private RecyclerView recyclerExercise;
    private int layoutId;
    private int TYPE_MAIN = 0;
    private int TYPE_LAST = 1;
    public int pos=0;
    public boolean isClickable = false;

    public ExcerciseListAdapter(RecyclerView recyclerExercise, ObservableArrayList<ExcercisePojo> exerciseObservableList, int layoutId) {
        this.exerciseObservableList = exerciseObservableList;
        this.recyclerExercise = recyclerExercise;
        this.layoutId = layoutId;
    }

    @Override
    public int getItemViewType(int position) {
        if (recyclerExercise.getId() == R.id.rcv_selected_exercise) {
            if (layoutId==R.layout.layout_selected_exercise_item && position == exerciseObservableList.size()) {
                return TYPE_LAST;
            } else {
                return TYPE_MAIN;
            }
        }
if (recyclerExercise.getId()==R.id.rcv_choose_exercise) {
            Log.d("sdddddd", "chooseselected"+recyclerExercise.getId());
        }

        return TYPE_MAIN;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (recyclerExercise.getId() == R.id.rcv_selected_exercise) {
            if (viewType == TYPE_MAIN) {
                View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
                context = parent.getContext();
                return new ProjectHolder(v);
            }
            if (viewType == TYPE_LAST) {
                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.last_row_layout, parent, false);
                context = parent.getContext();
                return new LastViewHolder(v);
            }
        }
        if (recyclerExercise.getId() == R.id.rcv_choose_exercise) {
        }
        if (recyclerExercise.getId() == R.id.rcv_choose_exercise) {
        }
        if (viewType == TYPE_MAIN) {
            View v = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            context = parent.getContext();
            return new ProjectHolder(v);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        if (exerciseObservableList == null) {
            return 1;
        }
        if (recyclerExercise.getId() == R.id.rcv_selected_exercise) {
            return exerciseObservableList.size() + 1;
        }
        return exerciseObservableList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public class ProjectHolder extends MyViewHolder {
        public ViewDataBinding chooseExerciseItemBinding;

        public ProjectHolder(View itemView) {
            super(itemView);
            chooseExerciseItemBinding = DataBindingUtil.bind(itemView);
        }
    }

    public class LastViewHolder extends MyViewHolder {
        public LastViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    public boolean onLongClick(View view) {

if(pos>6)
        {
            Toast.makeText(context, "can drag", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "cannot drag", Toast.LENGTH_SHORT).show();
        }

        ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        view.startDrag(data, shadowBuilder, view, 0);
        view.setVisibility(View.INVISIBLE);
        if (layoutId == R.layout.layout_choose_exercise_item) {
            SignUpVegetableDetails.isFromExercise = true;
        } else {
            SignUpVegetableDetails.isFromExercise = false;
        }
        return true;
    }

    public void onListItemClick(View view) {
        Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show();

    }

    public ExcercisePojo getItem(int position) {
        return exerciseObservableList.get(position);
    }


}
