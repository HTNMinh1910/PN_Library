package com.example.librarypnlib.ui.statistics;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.librarypnlib.R;
import com.example.librarypnlib.database.DatabasePhieuMuon;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class DoanhThuFragment extends Fragment {
    public DoanhThuFragment() {
    }
    public static DoanhThuFragment newInstance(String param1, String param2) {
        DoanhThuFragment fragment = new DoanhThuFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doanh_thu, container, false);
    }
    private ImageView imgStartDate;
    private TextInputEditText edStartDate;
    private ImageView imgEndDate;
    private TextInputEditText edEndDate;
    private ImageView imgDoanhThu;
    private TextView tvDoanhThu;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    int mYear, mMonth, mDate;
    DatePickerDialog.OnDateSetListener TuNgay = (datePicker, year, monthOfYear, dayOfMonth) -> {
        mYear = year;
        mMonth = monthOfYear;
        mDate = dayOfMonth;

        GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth, mDate);
        edStartDate.setText(dateFormat.format(calendar.getTime()));
    };
    DatePickerDialog.OnDateSetListener DenNgay = (datePicker, year, monthOfYear, dayOfMonth) -> {
        mYear = year;
        mMonth = monthOfYear;
        mDate = dayOfMonth;

        GregorianCalendar calendar = new GregorianCalendar(mYear, mMonth, mDate);
        edEndDate.setText(dateFormat.format(calendar.getTime()));
    };
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imgStartDate = view.findViewById(R.id.img_start_date);
        edStartDate = view.findViewById(R.id.ed_start_date);
        imgEndDate = view.findViewById(R.id.img_end_date);
        edEndDate = view.findViewById(R.id.ed_end_date);
        imgDoanhThu = view.findViewById(R.id.img_doanh_thu);
        tvDoanhThu = view.findViewById(R.id.tv_doanh_thu);

        imgStartDate.setOnClickListener(view1 -> {
            Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDate = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(),0,TuNgay,mYear, mMonth, mDate);
            dialog.show();
        });
        imgEndDate.setOnClickListener(view1 -> {
            Calendar calendar = Calendar.getInstance();
            mYear = calendar.get(Calendar.YEAR);
            mMonth = calendar.get(Calendar.MONTH);
            mDate = calendar.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(),0,DenNgay,mYear, mMonth, mDate);
            dialog.show();
        });
        imgDoanhThu.setOnClickListener(view1 -> {
            String start = edStartDate.getText().toString();
            String end = edEndDate.getText().toString();
            tvDoanhThu.setText(DatabasePhieuMuon.getInstance(getContext()).phieuMuonDao().getDoanhThu(start,end)+" :VND");
        });
    }
}