package com.example.librarypnlib.ui.bill;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
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
import com.example.librarypnlib.adapter.PhieuMuonAdapter;
import com.example.librarypnlib.adapter.SpinerPmSAdapter;
import com.example.librarypnlib.adapter.SpinerPmTvAdapter;
import com.example.librarypnlib.database.DatabasePhieuMuon;
import com.example.librarypnlib.database.DatabaseSach;
import com.example.librarypnlib.database.DatabaseThanhVien;
import com.example.librarypnlib.model.PhieuMuonObject;
import com.example.librarypnlib.model.SachObject;
import com.example.librarypnlib.model.ThanhVienObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HoaDonFragment extends Fragment {
    public HoaDonFragment() {
    }
    public static HoaDonFragment newInstance() {
        HoaDonFragment fragment = new HoaDonFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_hoa_don, container, false);
    }
    @Override
    public void onResume() {
        super.onResume();
        LoadData();
    }
    private RecyclerView RCVPhieuMuon;
    private FloatingActionButton fabPM;
    private ArrayList<PhieuMuonObject> arrayList = new ArrayList<>();
    private PhieuMuonAdapter phieuMuonAdapter;
    private int maTV, maS, giathue;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RCVPhieuMuon = view.findViewById(R.id.RCV_PhieuMuon);
        fabPM = view.findViewById(R.id.fab_PM);
        phieuMuonAdapter = new PhieuMuonAdapter(getContext());
        LoadData();
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        RCVPhieuMuon.setLayoutManager(layoutManager);
        RCVPhieuMuon.setAdapter(phieuMuonAdapter);
        fabPM.setOnClickListener(view1 -> {
            Dialog dialog = new Dialog(getContext(), androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_phieu_muon);

            EditText edTenTT = dialog.findViewById(R.id.ed_TenTT);
            Spinner spTenTV = dialog.findViewById(R.id.sp_TenTV);
            Spinner spTenS = dialog.findViewById(R.id.sp_TenS);
            CheckBox ckbTrangThai = dialog.findViewById(R.id.ckb_trangThai);
            EditText edNgayMuon = dialog.findViewById(R.id.ed_NgayMuon);
            ImageView imgCancel = dialog.findViewById(R.id.img_cancel);
            ImageView imgSave = dialog.findViewById(R.id.img_save);
            ckbTrangThai.setEnabled(false);
            SpinerPmTvAdapter spinerPmTvAdapter = new SpinerPmTvAdapter(getDataTV(),dialog.getContext(),R.layout.item_spiner);
            spTenTV.setAdapter(spinerPmTvAdapter);
            spTenTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    maTV = getDataTV().get(i).getMaTV();
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            SpinerPmSAdapter spinerPmSAdapter = new SpinerPmSAdapter(getDataSach(),dialog.getContext(),R.layout.item_spiner);
            spTenS.setAdapter(spinerPmSAdapter);
            spTenS.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    maS = getDataSach().get(i).getMaSach();
                    giathue = getDataSach().get(i).getGiaThue();
                }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                }
            });
            imgCancel.setOnClickListener(view2 -> dialog.cancel());
            edNgayMuon.setText(dateFormat.format(new Date()));
            imgSave.setOnClickListener(view2 -> {
                if (edNgayMuon.getText().toString().trim().isEmpty()||edTenTT.getText().toString().trim().isEmpty()|| spinerPmTvAdapter.isEmpty()||spinerPmSAdapter.isEmpty()) {
                    Toast.makeText(getContext(), "Không để trống !", Toast.LENGTH_SHORT).show();
                }else {
                    DatabasePhieuMuon.getInstance(getContext()).phieuMuonDao().insert(new PhieuMuonObject(edTenTT.getText().toString(),
                            maTV, maS, giathue,1, dateFormat.format(new Date())));
                    LoadData();
                    Toast.makeText(getContext(), "Ghi chú thành công.", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            });
            dialog.show();
        });
    }
    public void LoadData() {
        arrayList = (ArrayList<PhieuMuonObject>) DatabasePhieuMuon.getInstance(getContext()).phieuMuonDao().getAllData();
        phieuMuonAdapter.SetData(arrayList);
    }
    public ArrayList<ThanhVienObject> getDataTV(){
        ArrayList<ThanhVienObject> list;
        list = (ArrayList<ThanhVienObject>) DatabaseThanhVien.getInstance(getContext()).thanhVienDao().getAllData();
        return list;
    }
    public ArrayList<SachObject> getDataSach(){
        ArrayList<SachObject> list;
        list = (ArrayList<SachObject>) DatabaseSach.getInstance(getContext()).sachDao().getAllData();
        return list;
    }
}