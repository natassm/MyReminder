package com.example.myreminder.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myreminder.FirebaseDatabaseHelper;
import com.example.myreminder.R;
import com.example.myreminder.ToDo;
import com.example.myreminder.ToDoConfig;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    Button btnAddNew;
    RecyclerView listRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnAddNew = findViewById(R.id.dashboardAddButton);
        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(DashboardActivity.this, NewToDoActivity.class);
                startActivity(a);
            }
        });

        listRV = findViewById(R.id.dashboardListRecyclerView);

        new FirebaseDatabaseHelper().readToDo(new FirebaseDatabaseHelper.DataStatus() {
            @Override
            public void dataIsLoad(ArrayList<ToDo> toDoArrayList, ArrayList<String> keys) {
                new ToDoConfig().setConfig(listRV, DashboardActivity.this, toDoArrayList, keys);
            }

            @Override
            public void dataIsUpdate() {

            }

            @Override
            public void dataIsDelete() {

            }
        });
    }
}
