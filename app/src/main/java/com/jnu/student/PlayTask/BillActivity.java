package com.jnu.student.PlayTask;

import static com.google.android.material.internal.ContextUtils.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jnu.student.DataBank;
import com.jnu.student.R;

import java.util.ArrayList;

public class BillActivity extends AppCompatActivity {
    private BillActivity.BillItemAdapter billItemAdapter;

    public BillActivity(){



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);


            ArrayList<Bill> billItems = new DataBank().LoadBillItems(this);

            billItemAdapter = new BillItemAdapter(billItems);

            RecyclerView recyclerView = findViewById(R.id.recyclerview_bill);

            LinearLayoutManager layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(billItemAdapter);




    }

    public class BillItemAdapter extends RecyclerView.Adapter<BillActivity.BillItemAdapter.ViewHolder>{

        private ArrayList<Bill> billArrayList;
        public class ViewHolder extends RecyclerView.ViewHolder{
            private final TextView nameView;

            private final TextView scoreView;

            private final TextView dateView;




            public ViewHolder(View billItemView) {
                super(billItemView);
                nameView = billItemView.findViewById(R.id.bill_name);
                scoreView = billItemView.findViewById(R.id.bill_score);
                dateView = billItemView.findViewById(R.id.bill_date);


            }

            public TextView getnameView() {
                return nameView;
            }

            public TextView getscoreView() {
                return scoreView;
            }

            public TextView getdateView() {
                return dateView;
            }

        }

        public BillItemAdapter(ArrayList<Bill> billItems){billArrayList = billItems;}
        @Override
        public BillActivity.BillItemAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.bill_item_row, viewGroup, false);

            return new ViewHolder(view);
        }


        @Override
        public void onBindViewHolder(BillActivity.BillItemAdapter.ViewHolder viewHolder, final int position) {
            viewHolder.getnameView().setText(billArrayList.get(position).getName());
            viewHolder.getscoreView().setText(billArrayList.get(position).getScore());
            viewHolder.getdateView().setText(billArrayList.get(position).getTime());
        }

            @Override
        public int getItemCount() {
            return billArrayList.size();
        }
    }
}