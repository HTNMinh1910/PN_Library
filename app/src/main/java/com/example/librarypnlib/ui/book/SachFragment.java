package com.example.librarypnlib.ui.book;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarypnlib.R;
import com.example.librarypnlib.adapter.SachAdapter;
import com.example.librarypnlib.adapter.SpinerSachAdapter;
import com.example.librarypnlib.database.DatabaseLoaiSach;
import com.example.librarypnlib.database.DatabaseSach;
import com.example.librarypnlib.model.LoaiSachObject;
import com.example.librarypnlib.model.SachObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class SachFragment extends Fragment {

    public SachFragment() {
    }
    public static SachFragment newInstance(String param1, String param2) {
        SachFragment fragment = new SachFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sach, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        LoadData();
    }
    private RecyclerView RCVSach;
    private FloatingActionButton fabS;
    private ArrayList<SachObject> sachObjectArrayList = new ArrayList<>();
    private final LoaiSachObject loaiSachObject = new LoaiSachObject();
    private SachAdapter sachAdapter;
    private int maLoaiSach;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RCVSach = view.findViewById(R.id.RCV_Sach);
        fabS = view.findViewById(R.id.fab_S);
        sachAdapter = new SachAdapter(getContext());
        LoadData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        RCVSach.setLayoutManager(layoutManager);
        RCVSach.setAdapter(sachAdapter);
        fabS.setOnClickListener(view1 -> {
            Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_sach);
            EditText edTenS = dialog.findViewById(R.id.ed_TenS);
            EditText edGia = dialog.findViewById(R.id.ed_Gia);
            Spinner spSach = dialog.findViewById(R.id.sp_Sach);
            ImageView imgCancel = dialog.findViewById(R.id.img_cancel);
            ImageView imgSave = dialog.findViewById(R.id.img_save);
            SpinerSachAdapter spinerSachAdapter = new SpinerSachAdapter(getDataLSach(),dialog.getContext(),R.layout.item_spiner);
            spSach.setAdapter(spinerSachAdapter);
            spSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    maLoaiSach =  getDataLSach().get(i).getMaLoai();
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
            imgCancel.setOnClickListener(view2 -> dialog.cancel());
            imgSave.setOnClickListener(view2 -> {
                if (edTenS.getText().toString().trim().isEmpty()||edGia.getText().toString().trim().isEmpty()|| spinerSachAdapter.isEmpty()) {
                    Toast.makeText(getContext(), "Không để trống !", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseSach.getInstance(getContext()).sachDao().insert(new SachObject(edTenS.getText().toString(),
                            Integer.parseInt(edGia.getText().toString()), maLoaiSach));
                    LoadData();
                    Toast.makeText(getContext(), "Ghi chú thành công.", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            });
            dialog.show();
        });
    }
    public void LoadData() {
        sachObjectArrayList = (ArrayList<SachObject>) DatabaseSach.getInstance(getContext()).sachDao().getAllData();
        sachAdapter.SetData(sachObjectArrayList);
    }
    public ArrayList<LoaiSachObject> getDataLSach(){
        ArrayList<LoaiSachObject> list;
        list = (ArrayList<LoaiSachObject>) DatabaseLoaiSach.getInstance(getContext()).LoaiSachDao().getAllData();
        return list;
    }
}