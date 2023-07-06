package com.example.librarypnlib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.librarypnlib.R;
import com.example.librarypnlib.model.ThanhVienObject;

import java.util.ArrayList;

public class SpinerPmTvAdapter extends BaseAdapter {
    private ArrayList<ThanhVienObject> list;
    private ThanhVienObject ThanhVienObject;
    private Context context;
    private int layout;

    public SpinerPmTvAdapter(ArrayList<ThanhVienObject> list, Context context, int layout) {
        this.list = list;
        this.context = context;
        this.layout = layout;
    }
    @Override
    public int getCount() {
        return list.size();
    }
    @Override
    public Object getItem(int i) {
        ThanhVienObject = list.get(i);
        return ThanhVienObject;
    }
    @Override
    public long getItemId(int i) {
        ThanhVienObject = list.get(i);
        return ThanhVienObject.getMaTV();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SpinerPmTvAdapter.SpinSachViewHolder holder;
        if (view == null){
            holder = new SpinerPmTvAdapter.SpinSachViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.tvSpTen = view.findViewById(R.id.tv_sp_Ten);
            view.setTag(holder);
        }else {
            holder = (SpinerPmTvAdapter.SpinSachViewHolder) view.getTag();
        }
        holder.tvSpTen.setText(list.get(i).getHoTen());
        return view;
    }
    public  static class SpinSachViewHolder{
        private TextView tvSpTen;
    }
}
