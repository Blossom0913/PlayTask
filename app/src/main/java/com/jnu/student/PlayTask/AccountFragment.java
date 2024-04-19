package com.jnu.student.PlayTask;

import android.content.Intent;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jnu.student.MainActivity;
import com.jnu.student.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {



    public AccountFragment() {
        // Required empty public constructor
    }


    public static AccountFragment newInstance() {
        AccountFragment fragment = new AccountFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);





        // 将Canvas对象绘制到屏幕上

        Button billButton = rootView.findViewById(R.id.btn_bill);

        billButton.setOnClickListener(v->{
            Intent intent  = new Intent(requireActivity(),BillActivity.class);
            startActivity(intent);
        });
        return rootView;

    }
}