package com.example.librarypnlib.ui.user;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.librarypnlib.R;
import com.example.librarypnlib.database.DatabaseThuThu;
import com.example.librarypnlib.model.ThuThuObject;
import com.google.android.material.textfield.TextInputEditText;

public class MatKhauFragment extends Fragment {
    public MatKhauFragment() {
    }

    public static MatKhauFragment newInstance() {
        MatKhauFragment fragment = new MatKhauFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mat_khau, container, false);
    }
    private TextInputEditText NowPass;
    private TextInputEditText newPass;
    private TextInputEditText newPassAganin;
    private ImageView btnCancel;
    private ImageView btnSave;
    private ThuThuObject thuThuObject;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NowPass = (TextInputEditText) view.findViewById(R.id.ed_nowPass);
        newPass = (TextInputEditText) view.findViewById(R.id.newPass);
        newPassAganin = (TextInputEditText) view.findViewById(R.id.newPassAganin);
        btnCancel = (ImageView) view.findViewById(R.id.btn_cancel);
        btnSave = (ImageView) view.findViewById(R.id.btn_save);

        btnCancel.setOnClickListener(view1 -> {
            NowPass.setText("");
            newPass.setText("");
            newPassAganin.setText("");
        });
        btnSave.setOnClickListener(view1 -> {
            SharedPreferences preferences = getActivity().getSharedPreferences("user_file",MODE_PRIVATE);
            String user = preferences.getString("Username","");
            String MKcu = NowPass.getText().toString();
            String MKmoi = newPass.getText().toString();
            String MKlai = newPassAganin.getText().toString();

            if (Validate()>0){
                DatabaseThuThu.getInstance(getContext()).thuThuDao().getById(user,newPass.getText().toString());
                Toast.makeText(getContext(), "Đổi mật khẩu thành công.", Toast.LENGTH_SHORT).show();
                NowPass.setText("");
                newPass.setText("");
                newPassAganin.setText("");
                }else {
                    Toast.makeText(getContext(), "Đổi mật khẩu thất bại.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public int Validate(){
        int check = 1;
        if (NowPass.getText().toString().isEmpty() || newPass.getText().toString().isEmpty() || newPassAganin.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Không để trống !", Toast.LENGTH_SHORT).show();
                check = -1;
            }else {
            SharedPreferences preferences = getActivity().getSharedPreferences("user_file",MODE_PRIVATE);
            String MKcu = preferences.getString("Password","");
            String MKmoi = newPass.getText().toString();
            String MKlai = newPassAganin.getText().toString();
            if (!MKcu.equals(NowPass.getText().toString())){
                Toast.makeText(getContext(), "Mật khẩu hiện tại không đúng !", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!MKmoi.equals(MKlai)){
                Toast.makeText(getContext(), "Mật khẩu mới không trùng khớp !", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}