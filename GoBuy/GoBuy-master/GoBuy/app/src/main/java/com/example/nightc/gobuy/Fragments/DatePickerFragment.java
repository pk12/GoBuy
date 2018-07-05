package com.example.nightc.gobuy.Fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.nightc.gobuy.R;

import org.joda.time.LocalDate;

import java.util.Calendar;

/**
 * Created by Oppai on 7/19/2017.
 */

//MAY BE DELETED SINCE WE SELECT THE DATE VIEW ON THE NEXT PAGE
public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
    private LocalDate DateWanted;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        DateWanted = new LocalDate(year,month,dayOfMonth);
        TextView textView = (TextView) getActivity().findViewById(R.id.DateText);
        textView.setText(DateWanted.toString());
    }





}
