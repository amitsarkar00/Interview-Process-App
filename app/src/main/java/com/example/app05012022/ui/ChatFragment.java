package com.example.app05012022.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.app05012022.R;
import com.example.app05012022.databinding.FragmentChatBinding;
import com.example.app05012022.databinding.FragmentHomeBinding;


public class ChatFragment extends Fragment {
    private FragmentChatBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentChatBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
}