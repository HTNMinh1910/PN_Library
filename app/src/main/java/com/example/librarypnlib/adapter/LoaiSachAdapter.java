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
import com.example.librarypnlib.database.DatabaseLoaiSach;
import com.example.librarypnlib.model.LoaiSachObject;

import java.util.ArrayList;

public class LoaiSachAdapter extends RecyclerView.Adapter<LoaiSachAdapter.LoaiSachViewHolder> {
    private Context context;
    private ArrayList<LoaiSachObject> arrayList;
    public LoaiSachAdapter(Context context) {
        this.context = context;
    }

    public void SetData(ArrayList<LoaiSachObject> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public LoaiSachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_loai_sach,parent,false);
        return new LoaiSachViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull LoaiSachViewHolder holder, int position) {
        LoaiSachObject loaiSachObject = arrayList.get(position);
        if(loaiSachObject==null)
            return;
        holder.tvTenLS.setText(loaiSachObject.getTenLoai());
        holder.itemLoaisach.setOnLongClickListener(view -> {
            Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_loai_sach);
            EditText edTenLS = dialog.findViewById(R.id.ed_TenLS);
            ImageView imgCancel = dialog.findViewById(R.id.img_cancel);
            ImageView imgSave = dialog.findViewById(R.id.img_save);
            edTenLS.setText(loaiSachObject.getTenLoai());
            imgCancel.setOnClickListener(view1 -> dialog.cancel());
            imgSave.setOnClickListener(view1 -> {
                loaiSachObject.setTenLoai(edTenLS.getText().toString());
                if (edTenLS.getText().toString().trim().isEmpty()) {
                    Toast.makeText(context, "Không để trống !", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseLoaiSach.getInstance(context).LoaiSachDao().edit(loaiSachObject);
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
            dialog.setIcon(R.drawable.books_caterory_icons);
            dialog.setMessage("Chắn chắn muốn xóa"+loaiSachObject.getTenLoai());
            dialog.setPositiveButton("Ok", (dialogInterface, i) -> {
                DatabaseLoaiSach.getInstance(context).LoaiSachDao().delete(loaiSachObject);
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
    public class LoaiSachViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenLS;
        private CardView itemLoaisach;
        private ImageView imgDelete;
        public LoaiSachViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenLS = itemView.findViewById(R.id.tv_TenLS);
            itemLoaisach = itemView.findViewById(R.id.item_loaisach);
            imgDelete = itemView.findViewById(R.id.img_delete);
        }
    }
    private void LoadAllData(){
        arrayList = (ArrayList<LoaiSachObject>) DatabaseLoaiSach.getInstance(context).LoaiSachDao().getAllData();
        SetData(arrayList);
    }
}
