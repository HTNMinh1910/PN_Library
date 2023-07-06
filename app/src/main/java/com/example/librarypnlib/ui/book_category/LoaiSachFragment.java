package com.example.librarypnlib.ui.book_category;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarypnlib.R;
import com.example.librarypnlib.adapter.LoaiSachAdapter;
import com.example.librarypnlib.database.DatabaseLoaiSach;
import com.example.librarypnlib.model.LoaiSachObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LoaiSachFragment extends Fragment {

    private RecyclerView RCVLoaiSach;
    private ArrayList<LoaiSachObject> sachObjectArrayList = new ArrayList<>();
    private LoaiSachAdapter loaiSachAdapter;
    private FloatingActionButton fabLS;
    public LoaiSachFragment() {
    }
    public static LoaiSachFragment newInstance() {
        LoaiSachFragment fragment = new LoaiSachFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_loai_sach, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RCVLoaiSach = view.findViewById(R.id.RCV_LoaiSach);
        fabLS = view.findViewById(R.id.fab_LS);
        loaiSachAdapter = new LoaiSachAdapter(getContext());
        LoadData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        RCVLoaiSach.setLayoutManager(layoutManager);
        RCVLoaiSach.setAdapter(loaiSachAdapter);
        fabLS.setOnClickListener(view1 -> {
            Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_loai_sach);
            EditText edTenLS = dialog.findViewById(R.id.ed_TenLS);
            ImageView imgCancel = dialog.findViewById(R.id.img_cancel);
            ImageView imgSave = dialog.findViewById(R.id.img_save);
            imgCancel.setOnClickListener(view2 -> dialog.cancel());
            imgSave.setOnClickListener(view2 -> {
                if (edTenLS.getText().toString().trim().isEmpty()) {
                    Toast.makeText(getContext(), "Không để trống !", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseLoaiSach.getInstance(getContext()).LoaiSachDao().insert(new LoaiSachObject(edTenLS.getText().toString()));
                    LoadData();
                    Toast.makeText(getContext(), "Ghi chú thành công", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            });
            dialog.show();
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        LoadData();
    }
    public void LoadData() {
        sachObjectArrayList = (ArrayList<LoaiSachObject>) DatabaseLoaiSach.getInstance(getContext()).LoaiSachDao().getAllData();
        loaiSachAdapter.SetData(sachObjectArrayList);
    }
}