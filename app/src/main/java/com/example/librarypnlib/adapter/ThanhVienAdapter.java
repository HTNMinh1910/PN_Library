package com.example.librarypnlib.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarypnlib.R;
import com.example.librarypnlib.database.DatabaseThanhVien;
import com.example.librarypnlib.model.ThanhVienObject;

import java.util.ArrayList;

public class ThanhVienAdapter extends RecyclerView.Adapter<ThanhVienAdapter.ThanhVienViewHolder> {
    private Context context;
    private ArrayList<ThanhVienObject> arrayList;

    public ThanhVienAdapter(Context context) {
        this.context = context;
    }

    public void SetData(ArrayList<ThanhVienObject> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ThanhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_thanh_vien,parent,false);
        return new ThanhVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienViewHolder holder, int position) {
        ThanhVienObject thanhVienObject = arrayList.get(position);
        if(thanhVienObject==null)
            return;
        holder.tvTenTV.setText(thanhVienObject.getHoTen());
        holder.tvNamSinh.setText(thanhVienObject.getNamSinh());
        holder.itemThanhvien.setOnLongClickListener(view -> {
            Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_thanh_vien);

            EditText edTenTV = dialog.findViewById(R.id.ed_TenTV);
            EditText edNamSinh = dialog.findViewById(R.id.ed_NamSinh);
            ImageView imgCancel = dialog.findViewById(R.id.img_cancel);
            ImageView imgSave = dialog.findViewById(R.id.img_save);
            edTenTV.setText(thanhVienObject.getHoTen());
            edNamSinh.setText(thanhVienObject.getNamSinh());
            imgCancel.setOnClickListener(view1 -> dialog.cancel());

            imgSave.setOnClickListener(view1 -> {
                thanhVienObject.setHoTen(edTenTV.getText().toString());
                thanhVienObject.setNamSinh(edNamSinh.getText().toString());
                if (edTenTV.getText().toString().trim().isEmpty()||
                        edNamSinh.getText().toString().trim().isEmpty()) {
                    Toast.makeText(context, "Không để trống !", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseThanhVien.getInstance(context).thanhVienDao().edit(thanhVienObject);
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
            dialog.setTitle("Xác nhận xóa");
            dialog.setIcon(R.drawable.member_icons);
            dialog.setMessage("Chắn chắn muốn xóa"+thanhVienObject.getHoTen());
            dialog.setPositiveButton("Ok", (dialogInterface, i) -> {
                DatabaseThanhVien.getInstance(context).thanhVienDao().delete(thanhVienObject);
                LoadAllData();
                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
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
    public class ThanhVienViewHolder extends RecyclerView.ViewHolder {
        private CardView itemThanhvien;
        private TextView tvTenTV;
        private TextView tvNamSinh;
        private ImageView imgDelete;
        public ThanhVienViewHolder(@NonNull View itemView) {
            super(itemView);

            itemThanhvien = itemView.findViewById(R.id.item_thanhvien);
            tvTenTV = itemView.findViewById(R.id.tv_TenTV);
            tvNamSinh = itemView.findViewById(R.id.tv_NamSinh);
            imgDelete = itemView.findViewById(R.id.img_delete);
        }
    }
    private void LoadAllData(){
        arrayList = (ArrayList<ThanhVienObject>) DatabaseThanhVien.getInstance(context).thanhVienDao().getAllData();
        SetData(arrayList);
    }
}
