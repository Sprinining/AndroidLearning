package com.example.deviceinfo.base;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deviceinfo.R;
import com.example.deviceinfo.adapter.MyRecyclerViewAdapter;
import com.example.deviceinfo.entity.Bean;

import java.util.ArrayList;
import java.util.List;

public class BlankFragment extends Fragment {

    private static final String TAG = "blankFragment";
    private View rootView;
    private List<Bean> data = new ArrayList<>();

    public static BlankFragment newInstance(List<Bean> data) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) data);
        fragment.setArguments(args);
        return fragment;
    }

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.data = getArguments().getParcelableArrayList("data");
        }
    }

    // TODO: 2021/8/26
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 第一次加载时获取父视图
        if (rootView == null) {
            rootView = inflater.inflate(R.layout.activity_main, container, false);
        }

        // 从父视图获取recyclerView，然后设置瀑布布局
        RecyclerView recyclerView = rootView.findViewById(R.id.rv);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, 1);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(data, getContext());
        recyclerView.setAdapter(myRecyclerViewAdapter);
        myRecyclerViewAdapter.setOnRecyclerItemClickListener(new MyRecyclerViewAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onRecyclerItemClick(int position) {
                Log.d(TAG, "onRecyclerItemClick: " + position);
            }
        });

        return rootView;
    }
}