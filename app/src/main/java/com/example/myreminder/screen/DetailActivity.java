package com.example.myreminder.screen;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.myreminder.MyAlarm;
import com.example.myreminder.R;
import com.example.myreminder.fragment.TimePickerFragment;

import java.text.DateFormat;
import java.util.Calendar;

public class DetailActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private TextView toolbarTextView, dueDateTV, titleTV, descTV, timeView;
    private Button btnChooseTime, btnSetAlarm, btnCancel;
    ImageView btnBackToolbar;
    String key;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_task);

        btnChooseTime = findViewById(R.id.timePickerButton);
        btnSetAlarm = findViewById(R.id.detailSetAlarmButton);
        btnCancel = findViewById(R.id.detailCancelButton);
        titleTV = findViewById(R.id.detailTitleTextView);
        descTV = findViewById(R.id.detailDescTextView);
        dueDateTV = findViewById(R.id.detailDateTextView);
        timeView = findViewById(R.id.itemDueTimeTextView);
        toolbarTextView = findViewById(R.id.toolbarTitleTextView);

        toolbarTextView.setText(R.string.set_alarm);

        key = getIntent().getStringExtra("key");
        titleTV.setText(getIntent().getStringExtra("titleDoes"));
        descTV.setText(getIntent().getStringExtra("descDoes"));
        dueDateTV.setText(getIntent().getStringExtra("dateDoes"));

        btnChooseTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(DetailActivity.this, DashboardActivity.class);
                a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(a);

                Toast.makeText(DetailActivity.this, "Alarm set!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(DetailActivity.this, DashboardActivity.class);
                a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(a);
                finish();
            }
        });
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hourOfDay);
        c.set(Calendar.MINUTE, minute);
        c.set(Calendar.SECOND, 0);

        updateTimeText(c);
        startAlarm(c);
    }

    private void updateTimeText(Calendar c) {
        String timeText = "Alarm set for : ";
        timeText += DateFormat.getTimeInstance(DateFormat.SHORT).format(c.getTime());

        timeView.setText(timeText);
    }

    private void startAlarm(Calendar c) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(this, MyAlarm.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1, intent, 0);

        if (c.before(Calendar.getInstance())) {
            c.add(Calendar.DATE, 1);
        }

        alarmManager.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pendingIntent);
    }
}
