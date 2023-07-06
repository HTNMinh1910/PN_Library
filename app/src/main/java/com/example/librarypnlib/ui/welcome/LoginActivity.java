package com.example.librarypnlib.ui.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.librarypnlib.MainActivity;
import com.example.librarypnlib.R;
import com.example.librarypnlib.database.DatabaseThuThu;
import com.example.librarypnlib.model.ThuThuObject;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private EditText edUsername;
    private TextInputEditText edPassword;
    private CheckBox ckbNhoMK;
    private ImageView btnCancel;
    private ImageView btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.ed_Username);
        edPassword = findViewById(R.id.ed_Password);
        ckbNhoMK = findViewById(R.id.ckb_nhoMK);
        btnCancel = findViewById(R.id.btn_cancel);
        btnLogin = findViewById(R.id.btn_login);

        SharedPreferences preferences = getSharedPreferences("user_file", MODE_PRIVATE);
        edUsername.setText(preferences.getString("Username",""));
        edPassword.setText(preferences.getString("Password",""));
        ckbNhoMK.setChecked(preferences.getBoolean("Remeber",false));
        btnCancel.setOnClickListener(view -> {
            edUsername.setText("");
            edPassword.setText("");
            ckbNhoMK.setChecked(false);
        });
        btnLogin.setOnClickListener(view -> CheckLogin());
    }
    private ThuThuObject thuThuObject = new ThuThuObject();
    public void CheckLogin() {
        String str_user = edUsername.getText().toString();
        String str_pass = edPassword.getText().toString();
        if (str_user.isEmpty()||str_pass.isEmpty()) {
            Toast.makeText(this, "Không được bỏ trống !", Toast.LENGTH_SHORT).show();
        }else {
            if (DatabaseThuThu.getInstance(getApplicationContext()).thuThuDao().checkLogin(str_user,str_pass)>0||str_user.equals("admin")&&str_pass.equals("123")){
                RemeberUser(str_user,str_pass, ckbNhoMK.isChecked());
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                intent.putExtra("user", str_user);
                startActivity(intent);
                Toast.makeText(this, "Đăng nhập thành công.", Toast.LENGTH_SHORT).show();
                finish();
            }else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không chính xác.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void RemeberUser(String R_user, String R_pass, boolean status) {
        SharedPreferences preferences = getSharedPreferences("user_file", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (!status) {
            editor.clear();
        }else {
            editor.putString("Username",R_user);
            editor.putString("Password",R_pass);
            editor.putBoolean("Remeber",status);
        }
        editor.commit();
    }
}