package com.example.myreminder.screen;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.myreminder.fragment.DatePickerFragment;
import com.example.myreminder.helper.FirebaseDatabaseHelper;
import com.example.myreminder.R;
import com.example.myreminder.ToDo;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class UpdateDeleteToDoActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener{

    ImageView btnBackToolbar;
    TextView toolbarTextView, dueDateTV, taskPriority;
    EditText titleET, descET;
    Button btnUpdate, btnDelete, btnChooseDate;
    int year, month, day;
    String key, sCatTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_notes);

        toolbarTextView = findViewById(R.id.toolbarTitleTextView);
        btnBackToolbar = findViewById(R.id.toolbarBackImageView);
        titleET = findViewById(R.id.updateDeleteTitleDoesEditText);
        descET = findViewById(R.id.updateDeleteDescDoesEditText);
        dueDateTV = findViewById(R.id.updateDeleteDueDateTextView);
        taskPriority = findViewById(R.id.textPriorityUD);
        btnChooseDate = findViewById(R.id.datePickerButton);
        btnUpdate = findViewById(R.id.updateButton);
        btnDelete = findViewById(R.id.deleteButton);
        btnBackToolbar = findViewById(R.id.toolbarBackImageView);

        toolbarTextView.setText(R.string.update_delete);

        btnBackToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnBackToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        final DatePickerFragment newFrag = new DatePickerFragment();

        btnChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFrag.show(getSupportFragmentManager(), "btnChooseDate");
            }
        });

        key = getIntent().getStringExtra("key");
        titleET.setText(getIntent().getStringExtra("titleDoes"));
        descET.setText(getIntent().getStringExtra("descDoes"));
        dueDateTV.setText(getIntent().getStringExtra("dateDoes"));
        taskPriority.setText(getIntent().getStringExtra("categoryDoes"));

        sCatTask = taskPriority.getText().toString();

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ToDo toDo = new ToDo();
                toDo.setTitleMR(titleET.getText().toString());
                toDo.setDescMR(descET.getText().toString());
                toDo.setDueDateMR(dueDateTV.getText().toString());
                toDo.setCategoryPriority(taskPriority.getText().toString());

                switch (sCatTask) {
                    case "Important":
                        new FirebaseDatabaseHelper().updateImportant(key, toDo, new FirebaseDatabaseHelper.DataStatus() {
                            @Override
                            public void dataIsLoad(ArrayList<ToDo> toDoArrayList, ArrayList<String> keys) {}

                            @Override
                            public void dataIsUpdate() { updatedData(); }

                            @Override
                            public void dataIsDelete() {}
                        });
                        break;
                    case "Urgent":
                        new FirebaseDatabaseHelper().updateUrgent(key, toDo, new FirebaseDatabaseHelper.DataStatus() {
                            @Override
                            public void dataIsLoad(ArrayList<ToDo> toDoArrayList, ArrayList<String> keys) {}

                            @Override
                            public void dataIsUpdate() { updatedData(); }

                            @Override
                            public void dataIsDelete() {}
                        });
                        break;
                    case "Medium":
                        new FirebaseDatabaseHelper().updateMedium(key, toDo, new FirebaseDatabaseHelper.DataStatus() {
                            @Override
                            public void dataIsLoad(ArrayList<ToDo> toDoArrayList, ArrayList<String> keys) {}

                            @Override
                            public void dataIsUpdate() { updatedData(); }

                            @Override
                            public void dataIsDelete() {}
                        });
                        break;
                    case "Low":
                        new FirebaseDatabaseHelper().updateLow(key, toDo, new FirebaseDatabaseHelper.DataStatus() {
                            @Override
                            public void dataIsLoad(ArrayList<ToDo> toDoArrayList, ArrayList<String> keys) {}

                            @Override
                            public void dataIsUpdate() { updatedData(); }

                            @Override
                            public void dataIsDelete() {}
                        });
                        break;
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (sCatTask) {
                    case "Important":
                        new FirebaseDatabaseHelper().deleteImportant(key, new FirebaseDatabaseHelper.DataStatus() {
                            @Override
                            public void dataIsLoad(ArrayList<ToDo> toDoArrayList, ArrayList<String> keys) {}

                            @Override
                            public void dataIsUpdate() {}

                            @Override
                            public void dataIsDelete() { deletedData(); }
                        });
                        break;
                    case "Urgent":
                        new FirebaseDatabaseHelper().deleteUrgent(key, new FirebaseDatabaseHelper.DataStatus() {
                            @Override
                            public void dataIsLoad(ArrayList<ToDo> toDoArrayList, ArrayList<String> keys) {}

                            @Override
                            public void dataIsUpdate() {}

                            @Override
                            public void dataIsDelete() { deletedData(); }
                        });
                        break;
                    case "Medium":
                        new FirebaseDatabaseHelper().deleteMedium(key, new FirebaseDatabaseHelper.DataStatus() {
                            @Override
                            public void dataIsLoad(ArrayList<ToDo> toDoArrayList, ArrayList<String> keys) {}

                            @Override
                            public void dataIsUpdate() {}

                            @Override
                            public void dataIsDelete() { deletedData(); }
                        });
                        break;
                    case "Low":
                        new FirebaseDatabaseHelper().deleteLow(key, new FirebaseDatabaseHelper.DataStatus() {
                            @Override
                            public void dataIsLoad(ArrayList<ToDo> toDoArrayList, ArrayList<String> keys) {}

                            @Override
                            public void dataIsUpdate() {}

                            @Override
                            public void dataIsDelete() { deletedData(); }
                        });
                        break;
                }
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
        if (datePicker.getTag().equals("btnChooseDate")) {
            dueDateTV.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
        }
    }

    private void updatedData(){
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

    private void deletedData(){
        Toast.makeText(UpdateDeleteToDoActivity.this,
                "Data Deleted", Toast.LENGTH_SHORT).show();
        Intent a = new Intent(UpdateDeleteToDoActivity.this,
                DashboardActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
        finish();
    }
}
