package com.example.librarypnlib.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.librarypnlib.R;
import com.example.librarypnlib.model.SachObject;

import java.util.ArrayList;

public class SpinerPmSAdapter extends BaseAdapter {
    private ArrayList<SachObject> list;
    private SachObject sachObject;
    private Context context;
    private int layout;

    public SpinerPmSAdapter(ArrayList<SachObject> list, Context context, int layout) {
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
        sachObject = list.get(i);
        return sachObject;
    }
    @Override
    public long getItemId(int i) {
        sachObject = list.get(i);
        return sachObject.getMaSach();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        SpinerPmSAdapter.SpinPMSachViewHolder holder;
        if (view == null){
            holder = new SpinerPmSAdapter.SpinPMSachViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.tvSpTen = view.findViewById(R.id.tv_sp_Ten);
            view.setTag(holder);
        }else {
            holder = (SpinerPmSAdapter.SpinPMSachViewHolder) view.getTag();
        }
        holder.tvSpTen.setText(list.get(i).getTenSach());
        return view;
    }
    public  static class SpinPMSachViewHolder{
        private TextView tvSpTen;
    }
}
