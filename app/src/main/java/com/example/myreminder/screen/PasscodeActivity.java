package com.example.myreminder.screen;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myreminder.R;
import com.hanks.passcodeview.PasscodeView;

public class PasscodeActivity extends AppCompatActivity {

    PasscodeView passcodeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pass_code);

        passcodeView = findViewById(R.id.passCode);
        passcodeView.setPasscodeLength(4).setLocalPasscode("1234")
                .setListener(new PasscodeView.PasscodeViewListener() {
            @Override
            public void onFail() {
                Toast.makeText(getApplicationContext(), "Password is Wrong",
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String number) {
                Intent i = new Intent(PasscodeActivity.this, DashboardActivity.class);
                startActivity(i);
            }
        });
    }
}
