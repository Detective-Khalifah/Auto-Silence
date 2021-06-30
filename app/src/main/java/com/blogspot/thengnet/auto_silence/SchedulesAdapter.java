package com.blogspot.thengnet.auto_silence;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.blogspot.thengnet.auto_silence.databinding.ScheduleBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SchedulesAdapter extends ArrayAdapter<Schedule> {

    protected ScheduleBinding binding;
    LayoutInflater mInflater;
    Context mAppContext;

    public SchedulesAdapter (@NonNull Context context, int resource, @NonNull ArrayList<Schedule> schedules) {
        super(context, resource, schedules);
        mAppContext = context;
    }

    @NonNull
    @Override
    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (mInflater == null)
            mInflater = ((Activity) mAppContext).getLayoutInflater();

        if (binding == null)
            binding = ScheduleBinding.inflate(mInflater, parent, false);

        return binding.getRoot();
    }

}
