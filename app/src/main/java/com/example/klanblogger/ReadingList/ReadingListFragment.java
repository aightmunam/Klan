package com.example.klanblogger.ReadingList;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.klanblogger.R;
import com.google.android.material.tabs.TabLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReadingListFragment extends Fragment {

    TabLayout rl_tab_layout;
    ViewPager rl_viewPager;
    ReadingListAdapter rl_adapter;

    public ReadingListFragment() {
        // Required empty public constructor
    }
    private void setupViewPager(ViewPager viewPager) {
        ReadingListAdapter adapter = new ReadingListAdapter(getChildFragmentManager());
        adapter.addFragment(new SavedFragment(), "Saved");
        adapter.addFragment(new ArchivedFragment(), "Archived");
        adapter.addFragment(new RecentlyViewed(), "Recently Viewed");
        viewPager.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater,container,savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_reading_list, container, false);
        rl_viewPager = view.findViewById(R.id.reading_list_pager);
        setupViewPager(rl_viewPager);
        rl_tab_layout = view.findViewById(R.id.reading_list_tab_layout);
        rl_tab_layout.setupWithViewPager(rl_viewPager);
        return view;
    }

    @Override

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


    }

}
