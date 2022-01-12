package com.example.app05012022;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;


import com.example.app05012022.databinding.ActivityTabLayoutBinding;
import com.example.app05012022.ui.AppliedJobsFragment;
import com.example.app05012022.ui.OngoingProcessFragment;
import com.example.app05012022.ui.RecommenedJobFragment;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutActivity extends AppCompatActivity {
    private ActivityTabLayoutBinding binding;
    private TableLayout tableLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTabLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        VPadapter vPadapter = new VPadapter( getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        ViewPager viewPager = binding.viewpager;
        TabLayout tabs = binding.tablaoutView;
        vPadapter.AddFragment(new RecommenedJobFragment(),"Recommended");
        vPadapter.AddFragment(new AppliedJobsFragment(),"Applied");
        vPadapter.AddFragment(new OngoingProcessFragment(),"Ongoing");
        viewPager.setAdapter(vPadapter);
        tabs.setupWithViewPager(viewPager);


    }
}