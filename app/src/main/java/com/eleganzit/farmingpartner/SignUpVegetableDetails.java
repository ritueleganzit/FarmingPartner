package com.eleganzit.farmingpartner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.eleganzit.farmingpartner.databinding.ActivitySignUpVegetableDetailsBinding;
import com.eleganzit.farmingpartner.model.ExcercisePojo;


public class SignUpVegetableDetails extends AppCompatActivity implements View.OnDragListener {


    ActivitySignUpVegetableDetailsBinding signUpVegetableDetailsBinding;


    //SignUpVegetableDetailsBinding signUpVegetableDetailsBinding;

    public ObservableArrayList<ExcercisePojo> exerciseList = new ObservableArrayList<>();
    ;
    public ObservableArrayList<ExcercisePojo> exerciseSelectedList = new ObservableArrayList<>();
    public ExcercisePojo exerciseToMove;
    private int newContactPosition = -1;
    public static boolean isFromExercise = false;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpVegetableDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up_vegetable_details);
        progressDialog = new ProgressDialog(SignUpVegetableDetails.this);
        progressDialog.setMessage("Please Wait");
        progressDialog.setCancelable(false);
        progressDialog.setCanceledOnTouchOutside(false);
        // loadData();
        //signUpVegetableDetailsBinding.setActivitySignUpVegetableDetailsBinding(this);
        signUpVegetableDetailsBinding.rcvSelectedExercise.setOnDragListener(this);

        signUpVegetableDetailsBinding.rcvChooseExercise.setOnDragListener(new MyDragInsideRcvListener());
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.scale_3dp);
        signUpVegetableDetailsBinding.rcvSelectedExercise.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

    }

    @Override
    public boolean onDrag(View view, DragEvent dragEvent) {
        View selectedView = (View) dragEvent.getLocalState();
        RecyclerView rcvSelected = (RecyclerView) view;
        int currentPosition = 0;
        try {
            currentPosition = signUpVegetableDetailsBinding.rcvChooseExercise.getChildAdapterPosition(selectedView);

            // Ensure the position is valid.
            if (currentPosition != -1) {
                exerciseToMove = exerciseList.get(currentPosition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        switch (dragEvent.getAction()) {
            case DragEvent.ACTION_DRAG_LOCATION:

                View onTopOf = rcvSelected.findChildViewUnder(dragEvent.getX(), dragEvent.getY());
                newContactPosition = rcvSelected.getChildAdapterPosition(onTopOf);

                break;
            case DragEvent.ACTION_DRAG_ENTERED:

                Toast.makeText(this, "selected currentPosition : " + currentPosition, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "selected newContactPosition : " + newContactPosition, Toast.LENGTH_SHORT).show();


                if (exerciseList.size() == 0) {
                    signUpVegetableDetailsBinding.noVeg.setVisibility(View.VISIBLE);
                } else {
                    signUpVegetableDetailsBinding.noVeg.setVisibility(View.GONE);
                }
                break;
            case DragEvent.ACTION_DRAG_EXITED:

                if (exerciseList.size() == 0) {
                    signUpVegetableDetailsBinding.noVeg.setVisibility(View.VISIBLE);
                } else {
                    signUpVegetableDetailsBinding.noVeg.setVisibility(View.GONE);
                }
                break;
            case DragEvent.ACTION_DROP:
                //when Item is dropped off to recyclerview.

                if (currentPosition > 6) {
                    if (isFromExercise) {
                        exerciseSelectedList.add(exerciseToMove);
                        exerciseList.remove(exerciseToMove);
                        if (exerciseList.size() == 0) {
                            signUpVegetableDetailsBinding.noVeg.setVisibility(View.VISIBLE);
                        } else {
                            signUpVegetableDetailsBinding.noVeg.setVisibility(View.GONE);
                        }
                        signUpVegetableDetailsBinding.rcvChooseExercise.getAdapter().notifyItemRemoved(currentPosition);
                        signUpVegetableDetailsBinding.executePendingBindings();
                    }
                } else {
                    Toast.makeText(SignUpVegetableDetails.this, "These are required vegetables", Toast.LENGTH_SHORT).show();
                }
                //This is to hide/add the container!
                ViewGroup owner = (ViewGroup) (view.getParent());
                if (owner != null) {
                    //owner.removeView(selectedView);
                    //owner.addView(selectedView);

                    try {
                        LinearLayout rlContainer = (LinearLayout) view;
                        rlContainer.addView(selectedView);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    //selectedView.setVisibility(View.VISIBLE);
                }

                break;

            case DragEvent.ACTION_DRAG_ENDED:
                // Reset the visibility for the Contact item's view.
                // This is done to reset the state in instances where the drag action didn't do anything.
                selectedView.setVisibility(View.VISIBLE);

                // Boundary condition, scroll to top is moving list item to position 0.
                if (newContactPosition != -1) {
                    rcvSelected.scrollToPosition(newContactPosition);
                    newContactPosition = -1;
                } else {
                    rcvSelected.scrollToPosition(0);
                }
                if (exerciseList.size() == 0) {
                    signUpVegetableDetailsBinding.noVeg.setVisibility(View.VISIBLE);
                } else {
                    signUpVegetableDetailsBinding.noVeg.setVisibility(View.GONE);
                }
            default:
                break;
        }
        return true;
    }

    class MyDragInsideRcvListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            RecyclerView rcv = (RecyclerView) v;
            int currentPosition = 0;
            View selectedView = (View) event.getLocalState();
            try {
                currentPosition = rcv.getChildAdapterPosition(selectedView);
                // Ensure the position is valid.
                if (currentPosition != -1) {
                    exerciseToMove = exerciseSelectedList.get(currentPosition);
                    //exerciseSelectedList.get(currentPosition);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_LOCATION:
                    View onTopOf = rcv.findChildViewUnder(event.getX(), event.getY());
                    newContactPosition = rcv.getChildAdapterPosition(onTopOf);

                    //Flag for our own understanding!
                    //isFromExercise = true;

                    //This is for internal dragging (inside recyclerview only).  VVIP!
                    // Ensure the new position is valid.

                    //If you wanted to swap the items in recyclerview only.
                    //It requires bit changes.
                    if (newContactPosition != -1) {
                        LinearLayoutManager layoutManager = (LinearLayoutManager) rcv.getLayoutManager();
                        int firstVisiblePosition = layoutManager.findFirstCompletelyVisibleItemPosition();
                        int lastVisiblePosition = layoutManager.findLastVisibleItemPosition();

                        // Scroll up or down one if we are over the first or last visible list item.
                        if (newContactPosition >= lastVisiblePosition)
                            layoutManager.scrollToPositionWithOffset(firstVisiblePosition + 1, 0);
                        else if (newContactPosition <= firstVisiblePosition)
                            layoutManager.scrollToPositionWithOffset(firstVisiblePosition - 1, 0);

                        // Update the location of the Contact
                        exerciseList.remove(exerciseToMove);
                        exerciseList.add(newContactPosition, exerciseToMove);
                        rcv.getAdapter().notifyDataSetChanged();
                    }
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    // Reset the visibility for the Contact item's view.
                    // This is done to reset the state in instances where the drag action didn't do anything.


                    Toast.makeText(SignUpVegetableDetails.this, "choose currentPosition : " + currentPosition, Toast.LENGTH_SHORT).show();
                    Toast.makeText(SignUpVegetableDetails.this, "choose newContactPosition : " + newContactPosition, Toast.LENGTH_SHORT).show();


                    selectedView.setVisibility(View.VISIBLE);
                    // Boundary condition, scroll to top is moving list item to position 0.
                    if (newContactPosition != -1) {
                        rcv.scrollToPosition(newContactPosition);
                        newContactPosition = -1;
                    } else {
                        rcv.scrollToPosition(0);
                    }
                    break;
                case DragEvent.ACTION_DROP:
                    if (currentPosition > 6) {
                        //Toast.makeText(MainActivity.this, "can drag", Toast.LENGTH_SHORT).show();
                        if (!isFromExercise) {
                            //THIS IS FOR WHEN WE TAKE ITEM OF OTHER LIST AND DROP IN THIS LIST.
                            exerciseList.add(exerciseToMove);
                            exerciseSelectedList.remove(exerciseToMove);
                            signUpVegetableDetailsBinding.rcvChooseExercise.getAdapter().notifyItemInserted(currentPosition);
                            signUpVegetableDetailsBinding.executePendingBindings();
                        }
                    } else {
                        if (!isFromExercise) {
                            Toast.makeText(SignUpVegetableDetails.this, "These are required vegetables", Toast.LENGTH_SHORT).show();
                        }
                    }

                    break;

                default:
                    break;
            }
            return true;
            //}
        }

        public void loadData() {
            exerciseList.add(new ExcercisePojo("1", "Vegetable " + 1, "https://c.ndtvimg.com/2018-09/4a6d49go_vegetables_625x300_26_September_18.jpg", "", ""));
            exerciseList.add(new ExcercisePojo("2", "Vegetable " + 2, "https://images-prod.healthline.com/hlcmsresource/images/topic_centers/Food-Nutrition/high-protein-veggies/388x210_potatoes.jpg", "", ""));
            exerciseList.add(new ExcercisePojo("3", "Vegetable " + 3, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHC45t_GzF-5OXLFJoFqt21pVu2fn53z-yi4tJm3Q1i0-ozOZP", "", ""));
            exerciseList.add(new ExcercisePojo("4", "Vegetable " + 4, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQB-4buwvMxmDdc3QlyYvQkR06V_9Ya9fegwGahfMIBFxv4amFLwg", "", ""));
            exerciseList.add(new ExcercisePojo("5", "Vegetable " + 5, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXbpZ09GUV14WRhViNQNLSiZc6qJVV8Ju-ohjFrtdqgOYTJMisyg", "", ""));
            exerciseList.add(new ExcercisePojo("6", "Vegetable " + 1, "https://c.ndtvimg.com/2018-09/4a6d49go_vegetables_625x300_26_September_18.jpg", "", ""));
            exerciseList.add(new ExcercisePojo("7", "Vegetable " + 2, "https://images-prod.healthline.com/hlcmsresource/images/topic_centers/Food-Nutrition/high-protein-veggies/388x210_potatoes.jpg", "", ""));
            exerciseList.add(new ExcercisePojo("8", "Vegetable " + 6, "https://c.ndtvimg.com/2018-09/4a6d49go_vegetables_625x300_26_September_18.jpg", "", ""));
            exerciseList.add(new ExcercisePojo("9", "Vegetable " + 7, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXbpZ09GUV14WRhViNQNLSiZc6qJVV8Ju-ohjFrtdqgOYTJMisyg", "", ""));
            exerciseList.add(new ExcercisePojo("10", "Vegetable " + 8, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQB-4buwvMxmDdc3QlyYvQkR06V_9Ya9fegwGahfMIBFxv4amFLwg", "", ""));
            exerciseList.add(new ExcercisePojo("22", "Vegetable " + 9, "https://images-prod.healthline.com/hlcmsresource/images/topic_centers/Food-Nutrition/high-protein-veggies/388x210_potatoes.jpg", "", ""));
            exerciseList.add(new ExcercisePojo("10", "Vegetable " + 10, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHC45t_GzF-5OXLFJoFqt21pVu2fn53z-yi4tJm3Q1i0-ozOZP", "", ""));

            exerciseSelectedList.add(new ExcercisePojo("1", "Vegetable " + 1, "https://c.ndtvimg.com/2018-09/4a6d49go_vegetables_625x300_26_September_18.jpg", "", ""));
            exerciseSelectedList.add(new ExcercisePojo("2", "Vegetable " + 2, "https://images-prod.healthline.com/hlcmsresource/images/topic_centers/Food-Nutrition/high-protein-veggies/388x210_potatoes.jpg", "", ""));
            exerciseSelectedList.add(new ExcercisePojo("3", "Vegetable " + 3, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHC45t_GzF-5OXLFJoFqt21pVu2fn53z-yi4tJm3Q1i0-ozOZP", "", ""));
            exerciseSelectedList.add(new ExcercisePojo("4", "Vegetable " + 4, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQB-4buwvMxmDdc3QlyYvQkR06V_9Ya9fegwGahfMIBFxv4amFLwg", "", ""));
            exerciseSelectedList.add(new ExcercisePojo("5", "Vegetable " + 5, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXbpZ09GUV14WRhViNQNLSiZc6qJVV8Ju-ohjFrtdqgOYTJMisyg", "", ""));
            exerciseSelectedList.add(new ExcercisePojo("6", "Vegetable " + 6, "https://c.ndtvimg.com/2018-09/4a6d49go_vegetables_625x300_26_September_18.jpg", "", ""));
            exerciseSelectedList.add(new ExcercisePojo("7", "Vegetable " + 7, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXbpZ09GUV14WRhViNQNLSiZc6qJVV8Ju-ohjFrtdqgOYTJMisyg", "", ""));
        }
    }
}
