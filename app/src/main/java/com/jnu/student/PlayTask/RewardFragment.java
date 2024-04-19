package com.jnu.student.PlayTask;

import static android.app.Activity.RESULT_OK;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jnu.student.DataBank;
import com.jnu.student.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RewardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RewardFragment extends Fragment {

    private int my_score;

    private ArrayList<Reward> rewardItems = new ArrayList<>();

    private ArrayList<Bill> billItems;

    TextView myScoreTextView;

    private Button addButton;

    ActivityResultLauncher<Intent> addRewardLauncher;

    private RewardFragment.RewardItemAdapter rewardItemAdapter;


    public RewardFragment() {
        // Required empty public constructor
    }


    public static RewardFragment newInstance() {
        RewardFragment fragment = new RewardFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_reward, container, false);
        RecyclerView rewardRecyclerView = rootView.findViewById(R.id.recyclerview_reward);
        rewardRecyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));


        billItems = new DataBank().LoadBillItems(requireActivity());
        my_score = new DataBank().LoadScore(requireActivity());

        myScoreTextView = rootView.findViewById(R.id.my_score);
        myScoreTextView.setText("My Score: " + my_score);

        rewardItems = new DataBank().LoadRewardItems(requireActivity());

        if(0 == rewardItems.size()) {
            rewardItems.add(new Reward("看电影", 10));
        }


        rewardItemAdapter = new RewardFragment.RewardItemAdapter(rewardItems);
        rewardRecyclerView.setAdapter(rewardItemAdapter);

        addButton = rootView.findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {

            Intent intent = new Intent(requireActivity(), NewRewardActivity.class);
            addRewardLauncher.launch(intent);
        });



        addRewardLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent data = result.getData();
                        String name = data.getStringExtra("name"); // 获取返回的数据
                        int score =  Integer.parseInt(data.getStringExtra("score")); // 获取返回的数据

                        rewardItems.add(new Reward(name, score));
                        rewardItemAdapter.notifyItemInserted(rewardItems.size());

                        new DataBank().SaveRewardItems(requireActivity(), rewardItems);


                    } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        // 处理取消操作
                    }
                }
        );



        return rootView;
    }


    public class  RewardItemAdapter extends RecyclerView.Adapter<RewardFragment.RewardItemAdapter.ViewHolder> {
        private ArrayList<Reward> rewardArrayList;

        public class ViewHolder extends RecyclerView.ViewHolder{
            private final TextView nameView;

            private final TextView scoreView;

            private final TextView finishView;

            private final CheckBox checkboxView;


            public ViewHolder(View taskItemView) {
                super(taskItemView);
                nameView = taskItemView.findViewById(R.id.reward_name);
                scoreView = taskItemView.findViewById(R.id.reward_score);
                finishView = taskItemView.findViewById(R.id.reward_times);
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

        public RewardItemAdapter(ArrayList<Reward> rewardItems){rewardArrayList = rewardItems;}

        @Override
        public RewardFragment.RewardItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.reward_item_row, viewGroup, false);

            return new RewardFragment.RewardItemAdapter.ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RewardFragment.RewardItemAdapter.ViewHolder viewHolder, final int position) {
            viewHolder.getnameView().setText(rewardArrayList.get(position).getName());
            viewHolder.getscoreView().setText("-"+rewardArrayList.get(position).getScore() + "分");
            viewHolder.getfinishView().setText(rewardArrayList.get(position).getFinishtime()+" / ∞");

            viewHolder.checkboxView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    boolean isChecked = ((CheckBox) view).isChecked();
                    if(isChecked){
                        int itemPosition = viewHolder.getAdapterPosition();
                        my_score -= rewardArrayList.get(itemPosition).getScore();
                        new DataBank().SaveScore(requireActivity(),my_score);
                        myScoreTextView.setText("My Score: " + my_score);

                        rewardArrayList.get(itemPosition).finish();

                        viewHolder.getfinishView().setText(
                                rewardArrayList.get(itemPosition).getFinishtime()+" / ∞"
                                        );

                        new DataBank().SaveRewardItems(requireActivity(), rewardItems);

                        billItems.add(new Bill(rewardArrayList.get(itemPosition)));
                        new DataBank().SaveBillItems(requireActivity(),billItems);
                    }

                }
            });

        }

        @Override
        public int getItemCount() {
            return rewardArrayList.size();
        }

    }



    }