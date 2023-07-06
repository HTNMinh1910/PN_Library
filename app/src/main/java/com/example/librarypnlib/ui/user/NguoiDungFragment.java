package com.example.librarypnlib.ui.user;

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
import com.example.librarypnlib.adapter.ThuThuAdapter;
import com.example.librarypnlib.database.DatabaseThuThu;
import com.example.librarypnlib.model.ThuThuObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class NguoiDungFragment extends Fragment {
    public NguoiDungFragment() {
    }
    public static NguoiDungFragment newInstance() {
        NguoiDungFragment fragment = new NguoiDungFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nguoi_dung, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    private RecyclerView RCVThuThu;
    private FloatingActionButton fabTT;
    private ArrayList<ThuThuObject> thuThuObjectArrayList = new ArrayList<>();
    private ThuThuAdapter thuThuAdapter;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RCVThuThu = view.findViewById(R.id.RCV_ThuThu);
        fabTT = view.findViewById(R.id.fab_TT);
        thuThuAdapter = new ThuThuAdapter(getContext());
        LoadData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        RCVThuThu.setLayoutManager(layoutManager);
        RCVThuThu.setAdapter(thuThuAdapter);
        fabTT.setOnClickListener(view1 -> {
            Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_thu_thu);
             EditText edMaTT = dialog.findViewById(R.id.ed_MaTT);
             EditText edTenTT = dialog.findViewById(R.id.ed_TenTT);
             EditText edMatKhau = dialog.findViewById(R.id.ed_MatKhau);
            ImageView imgCancel = dialog.findViewById(R.id.img_cancel);
            ImageView imgSave = dialog.findViewById(R.id.img_save);
            imgCancel.setOnClickListener(view2 -> dialog.cancel());
            imgSave.setOnClickListener(view2 -> {
                if (edMaTT.getText().toString().isEmpty()||edTenTT.getText().toString().isEmpty()||edMatKhau.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Không để trống !", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseThuThu.getInstance(getContext()).thuThuDao().insert(new ThuThuObject(edMaTT.getText().toString(),edTenTT.getText().toString(),edMatKhau.getText().toString()));
                    LoadData();
                    Toast.makeText(getContext(), "Ghi chú thành công", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            });
            dialog.show();
        });
    }
    public void LoadData() {
        thuThuObjectArrayList = (ArrayList<ThuThuObject>) DatabaseThuThu.getInstance(getContext()).thuThuDao().getAllData();
        thuThuAdapter.SetData(thuThuObjectArrayList);
    }
}