package com.blogspot.thengnet.auto_silence;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.blogspot.thengnet.auto_silence.databinding.FragmentFirstBinding;

import java.util.Date;
import java.util.ArrayList;

public class FirstFragment extends Fragment {

    ArrayList<Schedule> schedules;
    SchedulesAdapter schedulesAdapter;

    private FragmentFirstBinding binding;

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        schedules = new ArrayList<>();
        schedules.add(new Schedule(true, "Exercise", "Daily jogging exercise.", null, null));
        schedules.add(new Schedule(false, "Exercise", "Pre-sleep exercise.", null, null));

        schedulesAdapter = new SchedulesAdapter(getContext(), 0, schedules);
    }

    @Override
    public View onCreateView (
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated (@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.listviewSchedules.setAdapter(schedulesAdapter);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
    }

    @Override
    public void onDestroyView () {
        super.onDestroyView();
        binding = null;
    }

}