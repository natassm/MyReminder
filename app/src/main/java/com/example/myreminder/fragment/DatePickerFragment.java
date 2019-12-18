package com.example.myreminder.fragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;


public class DatePickerFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day_of_month = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), STYLE_NORMAL,
                (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day_of_month);
        dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        dialog.getDatePicker().setTag(getTag());
        return dialog;
    }
}
