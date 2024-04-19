package com.jnu.student.PlayTask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.jnu.student.R;

public class NewRewardActivity extends AppCompatActivity {

    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_reward);

        Intent intent = getIntent();
        if (intent != null) {
            // 从Intent中获取传递的数据
            String name = intent.getStringExtra("name");
            if (null != name) {
                int score = intent.getIntExtra("score",5);
                position = intent.getIntExtra("position",-1);

                EditText editTaskName= findViewById(R.id.edit_reward_name);
                editTaskName.setText(name);
                EditText editTaskScore= findViewById(R.id.edit_reward_score);
                editTaskScore.setText(score);

            }
        }


        Button buttonOk= findViewById(R.id.button_item_reward_ok);
        buttonOk.setOnClickListener(v -> {
            Intent intent1 = new Intent();
            EditText editRewardName= findViewById(R.id.edit_reward_name);
            EditText editRewardScore= findViewById(R.id.edit_reward_score);

            intent1.putExtra("name", editRewardName.getText().toString());
            intent1.putExtra("score", editRewardScore.getText().toString());

            intent1.putExtra("position", position);
            setResult(Activity.RESULT_OK, intent1);
            NewRewardActivity.this.finish();
        });
    }
}