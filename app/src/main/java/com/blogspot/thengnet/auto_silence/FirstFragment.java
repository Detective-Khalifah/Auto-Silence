package com.blogspot.thengnet.auto_silence;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.blogspot.thengnet.auto_silence.databinding.FragmentFirstBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment {

    ArrayList<Schedule> schedules;
    SchedulesAdapter schedulesAdapter;

    private FragmentFirstBinding binding;

    private Bundle mSelectedScheduleParams;
    private ArrayList<String> mSelectedScheduleParamsList;

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        schedules = new ArrayList<>();

        Schedule testSched0 = new Schedule(true, "Islamic University of Madinah, Malaysia", "Lecture 0", "2017-12-01", "09:00", "2021-12-10", "10:00");
        Schedule testSched1 = new Schedule(true, "KASU", "Lecture 0", "2021-07-02", "08:00", "2021-07-30", "09:00");
        Schedule testSched2 = new Schedule(true, "Exercise", "Daily jogging exercise.", "2020-05-01", "06:00", "2020-05-30", "06:30");
        Schedule testSched3 = new Schedule(false, "Exercise", "Pre-sleep exercise.", "2021-02-20", "21:45", "2021-04-30", "22:00");
        schedules.add(testSched0);
        schedules.add(testSched1);
        schedules.add(testSched2);
        schedules.add(testSched3);
    }

    @Override
    public View onCreateView (
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        return binding.getRoot();

    }

    public void onViewCreated (@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        schedulesAdapter = new SchedulesAdapter(view.getContext(), 0, schedules);
        binding.listviewSchedules.setAdapter(schedulesAdapter);
        binding.listviewSchedules.setEmptyView(binding.textviewEmpty);

        mSelectedScheduleParams = new Bundle();

        binding.listviewSchedules.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick (AdapterView<?> adapterView, View view, int position, long id) {
                /**
                 * Dummy data to send to {@link SecondFragment}
                 * TODO: set up a database to contain the data, and use id to fetch data from it
                 * instead of passing the data from this {@link FirstFragment}
                 */
                mSelectedScheduleParamsList.addAll(Collections.singleton(schedules.toString()));
//                mSelectedScheduleParamsList.add(binding.listviewSchedules.getChildAt(position).toString()); // id of the selected CardView

                Schedule selectedSchedule = schedules.get(position);
                mSelectedScheduleParams.putStringArray("selected-schedule", new String[]{
                                selectedSchedule.getTitle(), selectedSchedule.getDescription(),
                                selectedSchedule.getStartDate(), selectedSchedule.getStartTime(),
                                selectedSchedule.getEndDate(), selectedSchedule.getEndTime()
                        }
                );
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Log.v(this.getClass().getName(), "List item " + position + " clicked.");
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, mSelectedScheduleParams);
            }
        });

    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();
        binding = null;
    }

}