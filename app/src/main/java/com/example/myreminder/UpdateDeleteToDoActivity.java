package com.example.myreminder;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UpdateDeleteToDoActivity extends AppCompatActivity {

    ImageView btnBackToolbar;
    TextView toolbarTextView, dueDateTV;
    EditText titleET, descET;
    Button btnUpdate, btnDelete;
    DatabaseReference reference;
    ToDoAdapter toDoAdapter;
    ArrayList<ToDo> toDoArrayList = new ArrayList<ToDo>();
//    final String keyIdDoes = getIntent().getStringExtra("idDoes");

    ToDo td  = new ToDo();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_notes);

        toolbarTextView = findViewById(R.id.toolbarTitleTextView);
        btnBackToolbar = findViewById(R.id.toolbarBackImageView);
        titleET = findViewById(R.id.updateDeleteTitleDoesEditText);
        descET = findViewById(R.id.updateDeleteDescDoesEditText);
        dueDateTV = findViewById(R.id.updateDeleteDueDateTextView);
        btnUpdate = findViewById(R.id.updateButton);
        btnDelete = findViewById(R.id.deleteButton);

        toolbarTextView.setText(R.string.update_delete);

        btnBackToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        titleET.setText(getIntent().getStringExtra("titleDoes"));
        descET.setText(getIntent().getStringExtra("descDoes"));
        dueDateTV.setText(getIntent().getStringExtra("dueDateDoes"));

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reference = FirebaseDatabase.getInstance().getReference().child("MyReminder");

                getValue();

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

//                        reference.child("AddNewToDo" ).push().setValue(td);
//
//                        Intent a = new Intent(UpdateDeleteToDoActivity.this, DashboardActivity.class);
//                        startActivity(a);
//                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
    }

    private void getValue(){
        td.setTitleMR(titleET.getText().toString());
        td.setDescMR(descET.getText().toString());
        td.setDueDateMR(dueDateTV.getText().toString());
//        td.setIdMR(keyIdDoes);
    }
}
