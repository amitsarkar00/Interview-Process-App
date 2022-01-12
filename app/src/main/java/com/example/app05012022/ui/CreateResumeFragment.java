package com.example.app05012022.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.app05012022.Login;
import com.example.app05012022.R;
import com.example.app05012022.databinding.FragmentCreateResumeBinding;
import com.example.app05012022.databinding.FragmentInterviewBinding;

public class CreateResumeFragment extends Fragment {

    private FragmentCreateResumeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCreateResumeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), Login.class);
//                startActivity(intent);
                Toast.makeText(getContext(), "Resume are creating Please wait", Toast.LENGTH_SHORT).show();
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}