package com.example.librarypnlib.ui.member;

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
import com.example.librarypnlib.adapter.ThanhVienAdapter;
import com.example.librarypnlib.database.DatabaseThanhVien;
import com.example.librarypnlib.model.ThanhVienObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ThanhVienFragment extends Fragment {
    private RecyclerView RCVThanhVien;
    private FloatingActionButton fabTV;
    private ArrayList<ThanhVienObject> thanhVienObjectArrayList = new ArrayList<>();
    private ThanhVienAdapter thanhVienAdapter;
    public ThanhVienFragment() {
    }
    public static ThanhVienFragment newInstance() {
        ThanhVienFragment fragment = new ThanhVienFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_thanh_vien, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        LoadData();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RCVThanhVien = view.findViewById(R.id.RCV_ThanhVien);
        fabTV = view.findViewById(R.id.fab_TV);
        thanhVienAdapter = new ThanhVienAdapter(getContext());
        LoadData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        RCVThanhVien.setLayoutManager(layoutManager);
        RCVThanhVien.setAdapter(thanhVienAdapter);
        fabTV.setOnClickListener(view1 -> {
            Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_thanh_vien);
            EditText edTenTV = dialog.findViewById(R.id.ed_TenTV);
            EditText edNamSinh = dialog.findViewById(R.id.ed_NamSinh);
            ImageView imgCancel = dialog.findViewById(R.id.img_cancel);
            ImageView imgSave = dialog.findViewById(R.id.img_save);
            imgCancel.setOnClickListener(view2 -> dialog.cancel());
            imgSave.setOnClickListener(view2 -> {
                if (edTenTV.getText().toString().isEmpty()||edNamSinh.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Không để trống !", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseThanhVien.getInstance(getContext()).thanhVienDao().insert(new ThanhVienObject(edTenTV.getText().toString(),edNamSinh.getText().toString()));
                    LoadData();
                    Toast.makeText(getContext(), "Ghi chú thành công", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            });
            dialog.show();
        });
    }
    public void LoadData() {
        thanhVienObjectArrayList = (ArrayList<ThanhVienObject>) DatabaseThanhVien.getInstance(getContext()).thanhVienDao().getAllData();
        thanhVienAdapter.SetData(thanhVienObjectArrayList);
    }
}