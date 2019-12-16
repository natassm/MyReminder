package com.example.myreminder;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    Button btnAddNew;
    RecyclerView listRV;

    DatabaseReference reference;
    ToDoAdapter toDoAdapter;
    ArrayList<ToDo> toDoArrayList = new ArrayList<ToDo>();

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

        toDoAdapter = new ToDoAdapter(this, toDoArrayList);
        listRV = findViewById(R.id.dashboardListRecyclerView);
        listRV.setLayoutManager(new LinearLayoutManager(this));
        listRV.setItemAnimator(new DefaultItemAnimator());
        listRV.setAdapter(toDoAdapter);

        reference = FirebaseDatabase.getInstance().getReference().child("MyReminder");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                toDoArrayList.clear();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    for (DataSnapshot dataSnapshot2: dataSnapshot1.getChildren()){
                        ToDo p = dataSnapshot2.getValue(ToDo.class);
                        toDoArrayList.add(p);
                    }
                }
                toDoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
