package com.example.librarypnlib.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarypnlib.R;
import com.example.librarypnlib.database.DatabasePhieuMuon;
import com.example.librarypnlib.database.DatabaseSach;
import com.example.librarypnlib.database.DatabaseThanhVien;
import com.example.librarypnlib.model.PhieuMuonObject;
import com.example.librarypnlib.model.SachObject;
import com.example.librarypnlib.model.ThanhVienObject;

import java.util.ArrayList;

public class PhieuMuonAdapter extends RecyclerView.Adapter<PhieuMuonAdapter.PhieuMuonViewHolder> {
    private Context context;
    private ArrayList<PhieuMuonObject> arrayList;
    private int maTV, maS, giathue;

    public PhieuMuonAdapter(Context context) {
        this.context = context;
    }

    public void SetData(ArrayList<PhieuMuonObject> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PhieuMuonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_phieu_muon,parent,false);
        return new PhieuMuonViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull PhieuMuonViewHolder holder, int position) {
        PhieuMuonObject phieuMuonObject = arrayList.get(position);
        ThanhVienObject thanhVienObject = DatabaseThanhVien.getInstance(context).thanhVienDao().getById(phieuMuonObject.getMaTV());
        SachObject sachObject = DatabaseSach.getInstance(context).sachDao().getById(phieuMuonObject.getMaSach());
        if(phieuMuonObject == null)
            return;
        holder.tvTenTV.setText(thanhVienObject.getHoTen());
        holder.tvTenS.setText(sachObject.getTenSach());
        holder.tvNgay.setText(phieuMuonObject.getNgay());
        if (phieuMuonObject.getTraSach() == 1){
            holder.tvTrangThai.setText("Chưa trả sách");
            holder.tvTrangThai.setTextColor(Color.RED);
        }else {
            holder.tvTrangThai.setText("Đã trả sách");
            holder.tvTrangThai.setTextColor(Color.GREEN);
        }
        holder.tvTenTV.setOnClickListener(view -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
            alertDialog.setTitle("Infomation");
            alertDialog.setMessage(phieuMuonObject.getMaPM()+"\n"+phieuMuonObject.getMaTT()+"\n"+phieuMuonObject.getTienThue()+"\n"+phieuMuonObject.getNgay());
            alertDialog.setNegativeButton("Ok", (dialogInterface, i) -> dialogInterface.cancel());
            alertDialog.show();
        });
        holder.itemPhieumuon.setOnLongClickListener(view -> {
            Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_phieu_muon);

            EditText edTenTT = dialog.findViewById(R.id.ed_TenTT);
            Spinner spTenTV = dialog.findViewById(R.id.sp_TenTV);
            Spinner spTenS = dialog.findViewById(R.id.sp_TenS);
            CheckBox ckbTrangThai = dialog.findViewById(R.id.ckb_trangThai);
            EditText edNgayMuon = dialog.findViewById(R.id.ed_NgayMuon);
            ImageView imgCancel = dialog.findViewById(R.id.img_cancel);
            ImageView imgSave = dialog.findViewById(R.id.img_save);
            imgCancel.setOnClickListener(view1 -> dialog.cancel());
            edTenTT.setText(phieuMuonObject.getMaTT());
            if (phieuMuonObject.getTraSach() == 0){
                ckbTrangThai.setChecked(true);
            }else {
                ckbTrangThai.setChecked(false);
            }
            edNgayMuon.setText(phieuMuonObject.getNgay());
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
            for (int i = 0; i < spinerPmTvAdapter.getCount(); i++) {
                if(phieuMuonObject.getMaTV()==getDataTV().get(i).getMaTV()) {
                    spTenTV.setSelection(i);
                    spTenTV.setSelected(true);
                }
            }
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
            for (int i = 0; i < spinerPmSAdapter.getCount(); i++) {
                if(phieuMuonObject.getMaSach()==getDataSach().get(i).getMaSach()) {
                    spTenS.setSelection(i);
                    spTenS.setSelected(true);
                }
            }
            imgSave.setOnClickListener(view1 -> {
                phieuMuonObject.setMaTT(edTenTT.getText().toString());
                phieuMuonObject.setMaTV(maTV);
                phieuMuonObject.setMaSach(maS);
                phieuMuonObject.setTienThue(giathue);
                if (ckbTrangThai.isChecked()){
                    phieuMuonObject.setTraSach(0);
                }else {
                    phieuMuonObject.setTraSach(1);
                }
                phieuMuonObject.setNgay(edNgayMuon.getText().toString());
                if (edTenTT.getText().toString().trim().isEmpty()||
                        edNgayMuon.getText().toString().trim().isEmpty()||
                        spinerPmTvAdapter.isEmpty()||spinerPmSAdapter.isEmpty()) {
                    Toast.makeText(context, "Không để trống !", Toast.LENGTH_SHORT).show();
                }else {
                    DatabasePhieuMuon.getInstance(context).phieuMuonDao().edit(phieuMuonObject);
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    LoadAllData();
                    dialog.dismiss();
                }
            });
            dialog.show();
            return false;
        });
        holder.imgDelete.setOnClickListener(view -> {
            AlertDialog.Builder dialog = new AlertDialog.Builder(context);
            dialog.setTitle("Xác nhận xóa !");
            dialog.setMessage("Chắn chắn muốn xóa ");
            dialog.setIcon(R.drawable.bill_icons);
            dialog.setPositiveButton("Ok", (dialogInterface, i) -> {
                DatabasePhieuMuon.getInstance(context).phieuMuonDao().delete(phieuMuonObject);
                LoadAllData();
                Toast.makeText(context, "Xóa thành công.", Toast.LENGTH_SHORT).show();
            });
            dialog.setNegativeButton("cancel", (dialogInterface, i) -> dialogInterface.cancel());
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
        });
    }

    public ArrayList<ThanhVienObject> getDataTV(){
        ArrayList<ThanhVienObject> list;
        list = (ArrayList<ThanhVienObject>) DatabaseThanhVien.getInstance(context).thanhVienDao().getAllData();
        return list;
    }
    public ArrayList<SachObject> getDataSach(){
        ArrayList<SachObject> list;
        list = (ArrayList<SachObject>) DatabaseSach.getInstance(context).sachDao().getAllData();
        return list;
    }
    private void LoadAllData(){
        arrayList = (ArrayList<PhieuMuonObject>) DatabasePhieuMuon.getInstance(context).phieuMuonDao().getAllData();
        SetData(arrayList);
    }
    @Override
    public int getItemCount() {
        if (arrayList!=null)
            return arrayList.size();
        return 0;
    }
    public class PhieuMuonViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenTV;
        private TextView tvTenS;
        private TextView tvNgay;
        private TextView tvTrangThai;
        private CardView itemPhieumuon;
        private ImageView imgDelete;
        public PhieuMuonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenTV = itemView.findViewById(R.id.tv_TenTV);
            tvNgay = itemView.findViewById(R.id.tv_Ngay);
            tvTrangThai = itemView.findViewById(R.id.tv_trangThai);
            tvTenS = itemView.findViewById(R.id.tv_TenS);
            itemPhieumuon = itemView.findViewById(R.id.item_phieumuon);
            imgDelete = itemView.findViewById(R.id.img_delete);
        }
    }
}
