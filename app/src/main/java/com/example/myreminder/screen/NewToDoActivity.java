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

import com.example.myreminder.DatePickerFragment;
import com.example.myreminder.R;
import com.example.myreminder.ToDo;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Calendar;

public class NewToDoActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button btnChooseDate, btnCreateNew, btnCancel;
    ImageView btnBackToolbar;
    Calendar calendar;
    TextView dateView, toolbarTextView;
    DatePickerDialog dpg;
    EditText titleET, descET;
    String sTitle, sDesc, sDueDate, sID;
    int year, month, day;
    DatabaseReference reference;

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
        dateView = findViewById(R.id.itemDueDateTextView);
        titleET = findViewById(R.id.newTitleDoesEditText);
        descET = findViewById(R.id.newDescDoesEditText);

        toolbarTextView.setText(R.string.new_notes);

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

                reference = FirebaseDatabase.getInstance().getReference().child("MyReminder");

                getValue();

                if (titleET.equals("") || descET.equals("") || dateView.equals("")){
                    Toast.makeText(NewToDoActivity.this, "Please fill all the data",
                            Toast.LENGTH_LONG).show();
                }else{
                    reference.child("AddNewToDo").push().setValue(td);
                    titleET.setText("");
                    descET.setText("");
                    Toast.makeText(NewToDoActivity.this, "Data saved", Toast.LENGTH_SHORT).show();

                    Intent a = new Intent(NewToDoActivity.this, DashboardActivity.class);
                    a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(a);
                    finish();
                }
            }
        });
    }

    private void getValue(){
        td.setTitleMR(titleET.getText().toString());
        td.setDescMR(descET.getText().toString());
        td.setDueDateMR(dateView.getText().toString());
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if (view.getTag().equals("btnChooseDate")) {
            dateView.setText(monthOfYear + "/" + dayOfMonth + "/" + year);
        }
    }
}