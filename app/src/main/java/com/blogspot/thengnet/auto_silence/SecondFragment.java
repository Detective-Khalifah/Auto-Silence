package com.blogspot.thengnet.auto_silence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blogspot.thengnet.auto_silence.databinding.FragmentSecondBinding;
import com.blogspot.thengnet.auto_silence.databinding.ScheduleBinding;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    private Bundle mScheduleParams;
    private ArrayList<String> mScheduleParamsList;

    // for testing
    private String[] currentSchedule;

    @Override
    public View onCreateView (
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        mScheduleParams = new Bundle();
        currentSchedule = mScheduleParams.getStringArray("selected-schedule");

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated (@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        // TEST
        ScheduleBinding tryout = DataBindingUtil.inflate(getLayoutInflater(), R.layout.schedule, (ViewGroup) view.getParent(), false);
        View fragmentRoot = binding.getRoot();
        ((ConstraintLayout) fragmentRoot).addView(tryout.getRoot());
        fragmentRoot.setPadding(16, 8, 16, 8);


        // DUMMY TEST-EDIT DATA
        SchedulesAdapter schedulesAdapter = new SchedulesAdapter(view.getContext(), 0,
                new ArrayList<Schedule>());
        tryout.setSchedule(new Schedule(false, "Edit it!",
                "Schedule edit mode.", "2021-07-02", "06:30",
                "2021-07-02", "15:00"));
        tryout.executePendingBindings();

        tryout.editStartDate.setOnClickListener(schedulesAdapter.new Dates(tryout.editStartDate, "date"));
        tryout.editEndDate.setOnClickListener(schedulesAdapter.new Dates(tryout.editStartDate, "date"));
        tryout.editStartDate.setOnClickListener(schedulesAdapter.new Dates(tryout.editStartDate, "date"));
        tryout.editStartTime.setOnClickListener(schedulesAdapter.new Dates(tryout.editStartDate, "date"));
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();
        binding = null;
    }

}