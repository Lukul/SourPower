package com.example.sourpower.ui.dailyspec;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.sourpower.R;

public class DailySpecFragment extends Fragment {

    private DailySpecViewModel dailyspecViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dailyspecViewModel =
                ViewModelProviders.of(this).get(DailySpecViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dailyspec, container, false);
        final TextView textView = root.findViewById(R.id.text_dailyspec);
        dailyspecViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
