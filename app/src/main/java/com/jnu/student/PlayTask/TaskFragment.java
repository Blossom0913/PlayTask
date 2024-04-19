package com.jnu.student.PlayTask;

import static android.app.Activity.RESULT_OK;
import static android.content.Intent.getIntent;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jnu.student.DataBank;
import com.jnu.student.R;
import com.jnu.student.ShopItem;
import com.jnu.student.ShopItemDetailsActivity;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskFragment extends Fragment {

    ActivityResultLauncher<Intent> addTaskLauncher;
    ActivityResultLauncher<Intent> updateTaskLauncher;

    ArrayList<Bill> billItems;

    TextView  myScoreTextView;

    private static int my_score = 40;

    private TaskItemAdapter  taskItemAdapter;

    private ArrayList<Task> taskItems = new ArrayList<>();

    private Button addButton;



    public TaskFragment() {
        // Required empty public constructor
    }


    public static TaskFragment newInstance() {
        TaskFragment fragment = new TaskFragment();
        Bundle args = new Bundle();
        args.putInt("my_score", my_score);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            my_score = getArguments().getInt("my_score");

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_task, container, false);
        RecyclerView taskRecyclerView = rootView.findViewById(R.id.recyclerview_task);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));

        registerForContextMenu(taskRecyclerView);

        billItems = new DataBank().LoadBillItems(requireActivity());


        my_score = new DataBank().LoadScore(requireActivity());



        myScoreTextView = rootView.findViewById(R.id.my_score);
        myScoreTextView.setText("My Score: " + my_score);




        //CheckBox checkBox = rootView.findViewById(R.id.checkBox_item);


        taskItems = new DataBank().LoadTaskItems(requireActivity());


        if(0 == taskItems.size()) {
            taskItems.add(new Task("写作业", 10, 10));
        }

        addButton = rootView.findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {

            Intent intent = new Intent(requireActivity(), NewTaskActivity.class);
            addTaskLauncher.launch(intent);







        });

        taskItemAdapter = new TaskFragment.TaskItemAdapter(taskItems);
        taskRecyclerView.setAdapter(taskItemAdapter);


        registerForContextMenu(taskRecyclerView);

        addTaskLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        String name = data.getStringExtra("name"); // 获取返回的数据
                        int score =  Integer.parseInt(data.getStringExtra("score")); // 获取返回的数据
                        int times =  Integer.parseInt(data.getStringExtra("times"));
                        taskItems.add(new Task(name, score, times));
                        taskItemAdapter.notifyItemInserted(taskItems.size());

                        new DataBank().SaveTaskItems(requireActivity(), taskItems);


                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        // 处理取消操作
                    }
                }
        );

        updateTaskLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        int position = data.getIntExtra("position",0);
                        String name = data.getStringExtra("name"); // 获取返回的数据
                        int score = Integer.parseInt(data.getStringExtra("score")); // 获取返回的数据
                        int times =  Integer.parseInt(data.getStringExtra("times"));


                        Task task = taskItems.get(position);
                        task.setScore(score);
                        task.setTotalTimes(times);
                        task.setName(name);
                        taskItemAdapter.notifyItemChanged(position);

                        new DataBank().SaveTaskItems(requireActivity(), taskItems);


                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        // 处理取消操作
                    }
                }
        );

        return rootView;
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:

                Intent intent = new Intent(requireActivity(), ShopItemDetailsActivity.class);
                addTaskLauncher.launch(intent);
                break;
            case 1:
                AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
                builder.setTitle("Delete Data");
                builder.setMessage("Are you sure you want to delete this data?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        taskItems.remove(item.getOrder());
                        taskItemAdapter.notifyItemRemoved(item.getOrder());

                        new DataBank().SaveTaskItems(requireActivity(), taskItems);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();
                break;
            case 2:
                Intent intentUpdate = new Intent(requireActivity(), ShopItemDetailsActivity.class);
                Task taskItem= taskItems.get(item.getOrder());
                intentUpdate.putExtra("name",taskItem.getName());
                intentUpdate.putExtra("score",taskItem.getScore());
                intentUpdate.putExtra("times",taskItem.getTotalTimes());
                intentUpdate.putExtra("position",item.getOrder());
                updateTaskLauncher.launch(intentUpdate);
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }



    public class  TaskItemAdapter extends RecyclerView.Adapter<TaskItemAdapter.ViewHolder> {
        private ArrayList<Task> taskArrayList;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {


            private final TextView nameView;

            private final TextView scoreView;

            private final TextView finishView;

            private final CheckBox checkboxView;

            @Override
            public void onCreateContextMenu(ContextMenu menu, View v,
                                            ContextMenu.ContextMenuInfo menuInfo) {
                menu.setHeaderTitle("具体操作");
                menu.add(0, 0, this.getAdapterPosition(), "添加" + this.getAdapterPosition());
                menu.add(0, 1, this.getAdapterPosition(), "删除" + this.getAdapterPosition());
                menu.add(0, 2, this.getAdapterPosition(), "修改" + this.getAdapterPosition());
            }

            public ViewHolder(View taskItemView) {
                super(taskItemView);
                nameView = taskItemView.findViewById(R.id.task_name);
                scoreView = taskItemView.findViewById(R.id.task_score);
                finishView = taskItemView.findViewById(R.id.task_times);
                checkboxView = taskItemView.findViewById(R.id.checkBox_item);

            }

            public TextView getnameView() {
                return nameView;
            }

            public TextView getscoreView() {
                return scoreView;
            }

            public TextView getfinishView() {
                return finishView;
            }

        }

        public TaskItemAdapter(ArrayList<Task> taskItems){taskArrayList = taskItems;}


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.task_item_row, viewGroup, false);

            return new ViewHolder(view);
        }



        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            viewHolder.getnameView().setText(taskArrayList.get(position).getName());
            viewHolder.getscoreView().setText("+"+taskArrayList.get(position).getScore() + "分");
            viewHolder.getfinishView().setText(taskArrayList.get(position).getFinishtime()+" / "+taskArrayList.get(position).getTotalTimes());

            viewHolder.checkboxView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    boolean isChecked = ((CheckBox) view).isChecked();
                    if(isChecked){
                        int itemPosition = viewHolder.getAdapterPosition();
                        my_score += taskArrayList.get(itemPosition).getScore();
                        new DataBank().SaveScore(requireActivity(),my_score);
                        myScoreTextView.setText("My Score: " + my_score);
                        if(taskArrayList.get(itemPosition).finish()){
                            taskArrayList.remove(itemPosition);
                        }
                        viewHolder.getfinishView().setText(
                                taskArrayList.get(itemPosition).getFinishtime()+" / "+
                                    taskArrayList.get(itemPosition).getTotalTimes());

                        new DataBank().SaveTaskItems(requireActivity(), taskItems);

                        billItems.add(new Bill(taskArrayList.get(itemPosition)));
                        new DataBank().SaveBillItems(requireActivity(),billItems);
                    }

                }
            });

        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return taskArrayList.size();
        }
    }
}