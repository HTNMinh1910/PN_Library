package com.example.librarypnlib.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarypnlib.R;
import com.example.librarypnlib.database.DatabaseLoaiSach;
import com.example.librarypnlib.database.DatabaseSach;
import com.example.librarypnlib.model.LoaiSachObject;
import com.example.librarypnlib.model.SachObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.SachViewHolder> {
    private Context context;
    private ArrayList<SachObject> arrayList;
    private int maLoaiSach;
    SpinerSachAdapter spinerSachAdapter;
    Spinner spSach;
    public SachAdapter(Context context) {
        this.context = context;
    }

    public void SetData(ArrayList<SachObject> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_sach,parent,false);
        return new SachViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, int position) {
        SachObject sachObject = arrayList.get(position);
        LoaiSachObject loaiSachObject = DatabaseLoaiSach.getInstance(context).LoaiSachDao().getById(sachObject.getMaLoai());
        if(sachObject == null)
            return;

        holder.tvTenS.setText(sachObject.getTenSach());
        holder.tvGia.setText(new DecimalFormat("###,###,###").format(sachObject.getGiaThue()));
        holder.tvTenLS.setText(loaiSachObject.getTenLoai());
        holder.itemSach.setOnLongClickListener(view -> {
            Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Base_Theme_AppCompat_Light_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_sach);
            EditText edTenS = dialog.findViewById(R.id.ed_TenS);
            EditText edGia = dialog.findViewById(R.id.ed_Gia);
            spSach = dialog.findViewById(R.id.sp_Sach);
            ImageView imgCancel = dialog.findViewById(R.id.img_cancel);
            ImageView imgSave = dialog.findViewById(R.id.img_save);
            edTenS.setText(sachObject.getTenSach());
            edGia.setText(new DecimalFormat("#########").format(sachObject.getGiaThue()));
            spinerSachAdapter = new SpinerSachAdapter(getDataLSach(),dialog.getContext(),R.layout.item_spiner);
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
            for (int i = 0; i < spinerSachAdapter.getCount(); i++) {
                if(sachObject.getMaLoai()==getDataLSach().get(i).getMaLoai()) {
                    spSach.setSelection(i);
                    spSach.setSelected(true);
                }
            }
            imgCancel.setOnClickListener(view1 -> dialog.cancel());
            imgSave.setOnClickListener(view1 -> {
                sachObject.setTenSach(edTenS.getText().toString());
                sachObject.setGiaThue(Integer.parseInt(edGia.getText().toString()));
                sachObject.setMaLoai(maLoaiSach);
                if (edTenS.getText().toString().trim().isEmpty()||
                        edGia.getText().toString().trim().isEmpty()||
                        spinerSachAdapter.isEmpty()) {
                    Toast.makeText(context, "Không để trống !", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseSach.getInstance(context).sachDao().edit(sachObject);
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
            dialog.setIcon(R.drawable.book_icon);
            dialog.setMessage("Chắn chắn muốn xóa "+sachObject.getTenSach());
            dialog.setPositiveButton("Ok", (dialogInterface, i) -> {
                DatabaseSach.getInstance(context).sachDao().delete(sachObject);
                LoadAllData();
                Toast.makeText(context, "Xóa thành công.", Toast.LENGTH_SHORT).show();
            });
            dialog.setNegativeButton("cancel", (dialogInterface, i) -> dialogInterface.cancel());
            AlertDialog alertDialog = dialog.create();
            alertDialog.show();
        });
    }
    @Override
    public int getItemCount() {
        if (arrayList!=null)
            return arrayList.size();
        return 0;
    }
    public class SachViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenS;
        private TextView tvGia;
        private TextView tvTenLS;
        private CardView itemSach;
        private ImageView imgDelete;
        public SachViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenS = itemView.findViewById(R.id.tv_TenS);
            tvGia = itemView.findViewById(R.id.tv_Gia);
            tvTenLS = itemView.findViewById(R.id.tv_TenLS);
            itemSach = itemView.findViewById(R.id.item_sach);
            imgDelete = itemView.findViewById(R.id.img_delete);
        }
    }
    public ArrayList<LoaiSachObject> getDataLSach(){
        ArrayList<LoaiSachObject> list;
        list = (ArrayList<LoaiSachObject>) DatabaseLoaiSach.getInstance(context).LoaiSachDao().getAllData();
        return list;
    }
    private void LoadAllData(){
        arrayList = (ArrayList<SachObject>) DatabaseSach.getInstance(context).sachDao().getAllData();
        SetData(arrayList);
    }
}
