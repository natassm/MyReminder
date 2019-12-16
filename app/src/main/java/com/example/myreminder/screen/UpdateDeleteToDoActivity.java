package com.example.myreminder.screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myreminder.FirebaseDatabaseHelper;
import com.example.myreminder.R;
import com.example.myreminder.ToDo;

import java.util.ArrayList;

public class UpdateDeleteToDoActivity extends AppCompatActivity {

    ImageView btnBackToolbar;
    TextView toolbarTextView, dueDateTV;
    EditText titleET, descET;
    Button btnUpdate, btnDelete;
    String key, title, desc, dueDate;

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

        key = getIntent().getStringExtra("key");
        title = getIntent().getStringExtra("titleDoes");
        desc = getIntent().getStringExtra("descDoes");
        dueDate = getIntent().getStringExtra("dateDoes");

        titleET.setText(title);
        descET.setText(desc);
        dueDateTV.setText(dueDate);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToDo toDo = new ToDo();
                toDo.setTitleMR(titleET.getText().toString());
                toDo.setDescMR(descET.getText().toString());
                toDo.setDueDateMR(dueDateTV.getText().toString());

                new FirebaseDatabaseHelper().updateToDo(key, toDo,
                        new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void dataIsLoad(ArrayList<ToDo> toDoArrayList, ArrayList<String> keys) {}

                    @Override
                    public void dataIsUpdate() {
                        Toast.makeText(UpdateDeleteToDoActivity.this,
                                "Data Updated", Toast.LENGTH_SHORT).show();

                        titleET.setText("");
                        descET.setText("");
                        dueDateTV.setText(R.string.choose_date);

                        Intent a = new Intent(UpdateDeleteToDoActivity.this,
                                DashboardActivity.class);
                        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(a);
                        finish();

                    }

                    @Override
                    public void dataIsDelete() {}
                });
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new FirebaseDatabaseHelper().deleteToDo(key, new FirebaseDatabaseHelper.DataStatus() {
                    @Override
                    public void dataIsLoad(ArrayList<ToDo> toDoArrayList, ArrayList<String> keys) {}

                    @Override
                    public void dataIsUpdate() {}

                    @Override
                    public void dataIsDelete() {
                        Toast.makeText(UpdateDeleteToDoActivity.this,
                                "Data Deleted", Toast.LENGTH_SHORT).show();
                        Intent a = new Intent(UpdateDeleteToDoActivity.this,
                                DashboardActivity.class);
                        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(a);
                        finish();
                    }
                });
            }
        });
    }
}
