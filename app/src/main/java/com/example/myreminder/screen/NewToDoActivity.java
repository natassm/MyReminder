package com.example.myreminder.screen;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myreminder.fragment.DatePickerFragment;
import com.example.myreminder.R;
import com.example.myreminder.ToDo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class NewToDoActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener,
        AdapterView.OnItemSelectedListener {

    private Button btnChooseDate, btnCreateNew, btnCancel;
    private ImageView btnBackToolbar;
    private TextView dateView, toolbarTextView, taskPriority;
    private EditText titleET, descET;
    private Spinner spinner;

    private DatePickerDialog dpg;
    Calendar calendar;

    private String sTitle, sDesc, sDueDate, sPriority;
    private int year, month, day;
    private DatabaseReference reference;

    ToDo td  = new ToDo();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);

        btnChooseDate = findViewById(R.id.datePickerButton);
        btnCreateNew = findViewById(R.id.createNewButton);
        btnCancel = findViewById(R.id.cancelButton);
        toolbarTextView = findViewById(R.id.toolbarTitleTextView);
        btnBackToolbar = findViewById(R.id.toolbarBackImageView);
        calendar = Calendar.getInstance();
        taskPriority = findViewById(R.id.taskPriorityTextView);
        dateView = findViewById(R.id.itemDueDateTextView);
        titleET = findViewById(R.id.newTitleDoesEditText);
        descET = findViewById(R.id.newDescDoesEditText);
        spinner = findViewById(R.id.spinner);

        toolbarTextView.setText(R.string.new_notes);

        categorySpinner();

        final DatePickerFragment newFrag = new DatePickerFragment();

        btnChooseDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newFrag.show(getSupportFragmentManager(), "btnChooseDate");
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(NewToDoActivity.this, DashboardActivity.class);
                a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(a);
                finish();
            }
        });

        btnBackToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnCreateNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sTitle = titleET.getText().toString();
                sDesc = descET.getText().toString();
                sDueDate = dateView.getText().toString();
                sPriority = taskPriority.getText().toString();

                reference = FirebaseDatabase.getInstance().getReference().child("MyReminder");

                getValue();

                switch (sPriority) {
                    case "Important":
                        reference.child("AddImportantToDo").push().setValue(td);
                        saveData();
                        break;
                    case "Urgent":
                        reference.child("AddUrgentToDo").push().setValue(td);
                        saveData();
                        break;
                    case "Medium":
                        reference.child("AddMediumToDo").push().setValue(td);
                        saveData();
                        break;
                    case "Low":
                        reference.child("AddLowToDo").push().setValue(td);
                        saveData();
                        break;
                }
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if (view.getTag().equals("btnChooseDate")) {
            dateView.setText(dayOfMonth + "/" + (monthOfYear+1) + "/" + year);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        taskPriority.setText(item);
        Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {}


    public void categorySpinner(){

        spinner.setOnItemSelectedListener(this);

        String[] priorityCategory = {"Urgent","Important","Medium","Low"};

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, priorityCategory);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

    }

    private void saveData(){
        titleET.setText("");
        descET.setText("");
        dateView.setText("");
        Toast.makeText(NewToDoActivity.this, "Data saved", Toast.LENGTH_SHORT).show();

        Intent a = new Intent(NewToDoActivity.this, DashboardActivity.class);
        a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(a);
        finish();
    }

    private void getValue(){
        td.setTitleMR(titleET.getText().toString());
        td.setDescMR(descET.getText().toString());
        td.setDueDateMR(dateView.getText().toString());
        td.setCategoryPriority(taskPriority.getText().toString());
    }
}