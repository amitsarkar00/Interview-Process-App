package com.example.app05012022.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app05012022.R;
import com.example.app05012022.databinding.FragmentAppliedJobsBinding;


public class AppliedJobsFragment extends Fragment {
    private FragmentAppliedJobsBinding binding;



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        binding = FragmentAppliedJobsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }

}