package com.example.myreminder.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myreminder.helper.FirebaseDatabaseHelper;
import com.example.myreminder.R;
import com.example.myreminder.ToDo;
import com.example.myreminder.ToDoConfig;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private TextView categoryPrio;
    Button btnAddNew, btnSetAlarm, btnUrgent, btnImportant, btnMedium, btnLow;
    RecyclerView listRV;
    String uText = "Urgent", iText = "Important", mText = "Medium", lText = "Low";

    @Override
    public void onBackPressed() {
        this.finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnAddNew = findViewById(R.id.dashboardAddButton);
        btnUrgent = findViewById(R.id.urgentButton);
        btnImportant = findViewById(R.id.importantButton);
        btnMedium = findViewById(R.id.mediumButton);
        btnLow = findViewById(R.id.lowButton);

        categoryPrio = findViewById(R.id.categoryPrioTextView);

        btnAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(DashboardActivity.this, NewToDoActivity.class);
                startActivity(a);
            }
        });

        btnUrgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryPrio.setText(uText);
                Intent a = new Intent(DashboardActivity.this, CategoryActivity.class);
                a.putExtra("categoryTask", categoryPrio.getText().toString());
                startActivity(a);
            }
        });

        btnImportant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryPrio.setText(iText);
                Intent a = new Intent(DashboardActivity.this, CategoryActivity.class);
                a.putExtra("categoryTask", categoryPrio.getText().toString());
                startActivity(a);
            }
        });

        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryPrio.setText(mText);
                Intent a = new Intent(DashboardActivity.this, CategoryActivity.class);
                a.putExtra("categoryTask", categoryPrio.getText().toString());
                startActivity(a);
            }
        });

        btnLow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                categoryPrio.setText(lText);
                Intent a = new Intent(DashboardActivity.this, CategoryActivity.class);
                a.putExtra("categoryTask", categoryPrio.getText().toString());
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
            public void dataIsUpdate() {}

            @Override
            public void dataIsDelete() {}
        });
    }
}
