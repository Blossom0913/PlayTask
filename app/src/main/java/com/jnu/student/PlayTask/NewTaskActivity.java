package com.jnu.student.PlayTask;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jnu.student.R;


public class NewTaskActivity extends AppCompatActivity {
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        Intent intent = getIntent();
        if (intent != null) {
            // 从Intent中获取传递的数据
            String name = intent.getStringExtra("name");
            if (null != name) {
                int score = intent.getIntExtra("score",5);
                int times = intent.getIntExtra("times",10);
                position = intent.getIntExtra("position",-1);

                EditText editTaskName= findViewById(R.id.edit_task_name);
                editTaskName.setText(name);
                EditText editTaskScore= findViewById(R.id.edit_task_score);
                editTaskScore.setText(score);
                EditText editTaskTimes = findViewById(R.id.edit_task_times);
                editTaskTimes.setText(times);

            }
        }


        Button buttonOk= findViewById(R.id.button_item_task_ok);
        buttonOk.setOnClickListener(v -> {
            Intent intent1 = new Intent();
            EditText editTaskName= findViewById(R.id.edit_task_name);
            EditText editTaskScore= findViewById(R.id.edit_task_score);
            EditText editTaskTimes = findViewById(R.id.edit_task_times);
            intent1.putExtra("name", editTaskName.getText().toString());
            intent1.putExtra("score", editTaskScore.getText().toString());
            intent1.putExtra("times",editTaskTimes.getText().toString());
            intent1.putExtra("position", position);
            setResult(Activity.RESULT_OK, intent1);
            NewTaskActivity.this.finish();
        });
    }
}
