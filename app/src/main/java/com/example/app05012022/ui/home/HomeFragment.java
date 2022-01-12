package com.example.app05012022.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.app05012022.R;
import com.example.app05012022.databinding.ActivityMapsBinding;
import com.example.app05012022.databinding.ActivityProfileBinding;
import com.example.app05012022.databinding.ActivityTabLayoutBinding;
import com.example.app05012022.databinding.FragmentCameraBinding;
import com.example.app05012022.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    private ActivityTabLayoutBinding tBinding;
    private ActivityMapsBinding mapsBinding;
    private FragmentCameraBinding cameraBinding;
    private ActivityProfileBinding profileBinding;



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        tBinding = ActivityTabLayoutBinding.inflate(inflater, container, false);
        mapsBinding = ActivityMapsBinding.inflate(inflater, container, false);
        cameraBinding = FragmentCameraBinding.inflate(inflater,container,false);
        profileBinding = ActivityProfileBinding.inflate(inflater,container,false);
//        View root = binding.getRoot();
//        View root = mapsBinding.getRoot();
        View root = profileBinding.getRoot();



//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}