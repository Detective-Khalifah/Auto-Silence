package com.blogspot.thengnet.auto_silence;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.blogspot.thengnet.auto_silence.databinding.ScheduleBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

public class SchedulesAdapter extends ArrayAdapter<Schedule> {

    private final Context mAppContext;
    private LayoutInflater mInflater;

    protected ScheduleBinding binding;

    private static TimePickerDialog mTimePickerDialogFrag;
    private static DatePickerDialog mDatePickerDialogFrag;
    private TextInputEditText mActiveDateSelector, mActiveTimeSelector;

    private Schedule currentSchedule;
    private final ArrayList<Schedule> mSchedules;
    private final int mYear, mMonth, mDay, mHours, mMinutes;

    public SchedulesAdapter (@NonNull Context context, int resource, @NonNull ArrayList<Schedule> theSchedules) {
        super(context, resource, theSchedules);
        mAppContext = context;
        mSchedules = theSchedules;

        Calendar now = Calendar.getInstance(Locale.getDefault());
        mYear = now.get(Calendar.YEAR);
        mMonth = now.get(Calendar.MONTH);
        mDay = now.get(Calendar.DAY_OF_MONTH);
        mHours = now.get(Calendar.HOUR_OF_DAY);
        mMinutes = now.get(Calendar.MINUTE);
    }

    @NonNull
    @Override
    public View getView (int position, @NonNull View convertView, @NonNull ViewGroup parent) {

        if (mInflater == null)
            mInflater = ((Activity) mAppContext).getLayoutInflater();

        binding = DataBindingUtil.getBinding(convertView);

        if (binding == null)
            binding = DataBindingUtil.inflate(mInflater, R.layout.schedule, parent, false);

        currentSchedule = getItem(position);
        binding.setSchedule(currentSchedule);
        binding.executePendingBindings();

        binding.editStartDate.setOnClickListener(new Dates(binding.editStartDate, "date"));
        binding.editEndDate.setOnClickListener(new Dates(binding.editEndDate, "date"));
        binding.editStartTime.setOnClickListener(new Dates(binding.editStartTime, "time"));
        binding.editEndTime.setOnClickListener(new Dates(binding.editEndTime, "time"));

        return binding.getRoot();
    }

    protected class Dates implements DatePickerDialog.OnDateSetListener,
            TimePickerDialog.OnTimeSetListener, View.OnClickListener {

        final TextInputEditText clickedPicker;
        final String pickerType;

        protected Dates (TextInputEditText thePicker, String pickerType) {
            this.clickedPicker = thePicker;
            this.pickerType = pickerType;
        }

        @Override
        public void onClick (View view) {
            switch (pickerType) {
                case "date":
                    mActiveDateSelector = clickedPicker;
                    mDatePickerDialogFrag = new DatePickerDialog(mAppContext, this, mYear, mMonth, mDay);
                    mDatePickerDialogFrag.show();
                    break;
                case "time":
                    mActiveTimeSelector = clickedPicker;
                    mTimePickerDialogFrag = new TimePickerDialog(mAppContext, this, mHours, mMinutes, true);
                    mTimePickerDialogFrag.show();
                    break;
                default:
            }
        }

        @Override
        public void onDateSet (DatePicker datePicker, int year, int month, int day) {
            mActiveDateSelector.setText(year + "-" + month + "-" + day);
        }

        @Override
        public void onTimeSet (TimePicker timePicker, int hrs, int mins) {
            mActiveTimeSelector.setText(hrs + ":" + mins);
        }
    }
}