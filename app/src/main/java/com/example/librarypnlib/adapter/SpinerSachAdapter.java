package com.example.librarypnlib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.librarypnlib.R;
import com.example.librarypnlib.model.LoaiSachObject;

import java.util.ArrayList;

public class SpinerSachAdapter extends BaseAdapter {
    private ArrayList<LoaiSachObject> list;
    private LoaiSachObject loaiSachObject;
    private Context context;
    private int layout;

    public SpinerSachAdapter(ArrayList<LoaiSachObject> list, Context context, int layout) {
        this.list = list;
        this.context = context;
        this.layout = layout;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public LoaiSachObject getItem(int i) {
        loaiSachObject = list.get(i);
        return loaiSachObject;
    }
    @Override
    public long getItemId(int i) {
        loaiSachObject = list.get(i);
        return loaiSachObject.getMaLoai();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SpinerSachAdapter.SpinSachViewHolder holder;
        if (view == null){
            holder = new SpinerSachAdapter.SpinSachViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.tvSpTen = view.findViewById(R.id.tv_sp_Ten);
            view.setTag(holder);
        }else {
            holder = (SpinerSachAdapter.SpinSachViewHolder) view.getTag();
        }
        holder.tvSpTen.setText(list.get(i).getTenLoai());
        return view;
    }
    public  static class SpinSachViewHolder{
        private TextView tvSpTen;
    }
}
