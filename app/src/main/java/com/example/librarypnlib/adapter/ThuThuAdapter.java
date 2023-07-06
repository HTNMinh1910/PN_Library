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
import com.example.librarypnlib.database.DatabaseThuThu;
import com.example.librarypnlib.model.ThuThuObject;

import java.util.ArrayList;

public class ThuThuAdapter extends RecyclerView.Adapter<ThuThuAdapter.ThuThuViewHolder> {
    private Context context;
    private ArrayList<ThuThuObject> arrayList;

    public ThuThuAdapter(Context context) {
        this.context = context;
    }

    public void SetData(ArrayList<ThuThuObject> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ThuThuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_thu_thu,parent,false);
        return new ThuThuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThuThuViewHolder holder, int position) {
        ThuThuObject thuThuObject = arrayList.get(position);
        if(thuThuObject==null)
            return;
        holder.tvTenTT.setText(thuThuObject.getHoTen());
        holder.tvTaiKhoan.setText(thuThuObject.getMaTT());
        holder.itemThuthu.setOnLongClickListener(view -> {
            Dialog dialog = new Dialog(context, androidx.appcompat.R.style.Theme_AppCompat_Light_Dialog_Alert);
            dialog.setContentView(R.layout.dialog_thu_thu);

            EditText edMaTT = dialog.findViewById(R.id.ed_MaTT);
            EditText edTenTT = dialog.findViewById(R.id.ed_TenTT);
            EditText edMatKhau = dialog.findViewById(R.id.ed_MatKhau);
            ImageView imgCancel = dialog.findViewById(R.id.img_cancel);
            ImageView imgSave = dialog.findViewById(R.id.img_save);
            edMaTT.setText(thuThuObject.getMaTT());
            edTenTT.setText(thuThuObject.getHoTen());
            edMatKhau.setText(thuThuObject.getMatKhau());
            imgCancel.setOnClickListener(view1 -> dialog.cancel());

            imgSave.setOnClickListener(view1 -> {
                thuThuObject.setMaTT(edMaTT.getText().toString());
                thuThuObject.setHoTen(edTenTT.getText().toString());
                thuThuObject.setMatKhau(edMatKhau.getText().toString());
                if (edMaTT.getText().toString().trim().isEmpty()||
                        edTenTT.getText().toString().trim().isEmpty()||
                        edMatKhau.getText().toString().trim().isEmpty()) {
                    Toast.makeText(context, "Không để trống !", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseThuThu.getInstance(context).thuThuDao().edit(thuThuObject);
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
            dialog.setIcon(R.drawable.programmer);
            dialog.setMessage("Chắn chắn muốn xóa"+thuThuObject.getHoTen());
            dialog.setPositiveButton("Ok", (dialogInterface, i) -> {
                DatabaseThuThu.getInstance(context).thuThuDao().delete(thuThuObject);
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

    public class ThuThuViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenTT;
        private TextView tvTaiKhoan;
        private CardView itemThuthu;
        private final ImageView imgDelete;
        public ThuThuViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenTT = itemView.findViewById(R.id.tv_TenTT);
            tvTaiKhoan = itemView.findViewById(R.id.tv_taiKhoan);
            itemThuthu = itemView.findViewById(R.id.item_thuthu);
            imgDelete = itemView.findViewById(R.id.img_delete);
        }
    }
    private void LoadAllData(){
        arrayList = (ArrayList<ThuThuObject>) DatabaseThuThu.getInstance(context).thuThuDao().getAllData();
        SetData(arrayList);
    }
}
