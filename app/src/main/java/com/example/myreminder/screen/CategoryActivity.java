package com.example.myreminder.screen;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myreminder.R;
import com.example.myreminder.ToDo;
import com.example.myreminder.ToDoConfig;
import com.example.myreminder.helper.FirebaseDatabaseHelper;

import java.util.ArrayList;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView catRV;
    TextView catTV, toolBarTextView;
    String sCat;
    ImageView btnBackToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_task);

        catRV = findViewById(R.id.categoryRecyclerView);
        catTV = findViewById(R.id.categoryReminderTextView);
        toolBarTextView = findViewById(R.id.toolbarTitleTextView);
        btnBackToolbar = findViewById(R.id.toolbarBackImageView);

        btnBackToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        catTV.setText(getIntent().getStringExtra("categoryTask"));

        sCat = catTV.getText().toString();

        switch (sCat) {
            case "Urgent":
                toolBarTextView.setText(R.string.urgent_task);
                new FirebaseDatabaseHelper().readUrgent(new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void dataIsLoad(ArrayList<ToDo> toDoArrayList, ArrayList<String> keys) {
                        new ToDoConfig().setConfig(catRV, CategoryActivity.this, toDoArrayList, keys);
                    }

                    @Override
                    public void dataIsUpdate() {}

                    @Override
                    public void dataIsDelete() {}
                });
                break;
            case "Important":
                toolBarTextView.setText(R.string.important_task);
                new FirebaseDatabaseHelper().readImportant(new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void dataIsLoad(ArrayList<ToDo> toDoArrayList, ArrayList<String> keys) {
                        new ToDoConfig().setConfig(catRV, CategoryActivity.this, toDoArrayList, keys);
                    }

                    @Override
                    public void dataIsUpdate() {}

                    @Override
                    public void dataIsDelete() {}
                });
                break;
            case "Medium":
                toolBarTextView.setText(R.string.medium_task);
                new FirebaseDatabaseHelper().readMedium(new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void dataIsLoad(ArrayList<ToDo> toDoArrayList, ArrayList<String> keys) {
                        new ToDoConfig().setConfig(catRV, CategoryActivity.this, toDoArrayList, keys);
                    }

                    @Override
                    public void dataIsUpdate() {}

                    @Override
                    public void dataIsDelete() {}
                });
                break;
            case "Low":
                toolBarTextView.setText(R.string.low_task);
                new FirebaseDatabaseHelper().readLow(new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void dataIsLoad(ArrayList<ToDo> toDoArrayList, ArrayList<String> keys) {
                        new ToDoConfig().setConfig(catRV, CategoryActivity.this, toDoArrayList, keys);
                    }

                    @Override
                    public void dataIsUpdate() {}

                    @Override
                    public void dataIsDelete() {}
                });
                break;
        }
    }
}
