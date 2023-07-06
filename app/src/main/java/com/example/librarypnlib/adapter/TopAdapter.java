package com.example.librarypnlib.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.librarypnlib.R;
import com.example.librarypnlib.database.DatabaseSach;
import com.example.librarypnlib.model.SachObject;
import com.example.librarypnlib.model.TopObject;

import java.util.ArrayList;

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.TopViewHolder> {
    private final Context context;
    private ArrayList<TopObject> arrayList;
    private TopObject topObject;
    private SachObject sachObject;
    public TopAdapter(Context context) {
        this.context = context;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void SetData(ArrayList<TopObject> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public TopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_top,parent,false);
        return new TopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TopViewHolder holder, int position) {
        topObject = arrayList.get(position);
        sachObject = DatabaseSach.getInstance(context).sachDao().getById(topObject.getMaSach());
        if (topObject == null)
            return;
        holder.tvTenS.setText(sachObject.getTenSach());
        holder.tvSoLuong.setText(topObject.getSoLuong());
    }
    @Override
    public int getItemCount() {
        if (arrayList!=null)
            return arrayList.size();
        return 0;
    }
    public static class TopViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTenS;
        private TextView tvSoLuong;
        public TopViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenS = itemView.findViewById(R.id.tv_TenS);
            tvSoLuong = itemView.findViewById(R.id.tv_SoLuong);
        }
    }
}
