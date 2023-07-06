package com.example.librarypnlib.ui.statistics;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarypnlib.R;
import com.example.librarypnlib.adapter.TopAdapter;
import com.example.librarypnlib.database.DatabasePhieuMuon;
import com.example.librarypnlib.model.TopObject;

import java.util.ArrayList;

public class TopFragment extends Fragment {

    public TopFragment() {
    }

    public static TopFragment newInstance() {
        TopFragment fragment = new TopFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    private RecyclerView RCVTop;
    private ArrayList<TopObject> arrayList = new ArrayList<>();
    private TopAdapter topAdapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RCVTop = (RecyclerView) view.findViewById(R.id.RCV_Top);
        topAdapter = new TopAdapter(getContext());
        LoadData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        RCVTop.setLayoutManager(layoutManager);
        RCVTop.setAdapter(topAdapter);
    }
    public void LoadData() {
       arrayList = (ArrayList<TopObject>) DatabasePhieuMuon.getInstance(getContext()).phieuMuonDao().getTopData();
        topAdapter.SetData(arrayList);
    }
}