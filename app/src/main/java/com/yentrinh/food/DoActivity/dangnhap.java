package com.yentrinh.food.DoActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yentrinh.food.DAO.TaiKhoanDAO;
import com.yentrinh.food.R;

public class dangnhap extends AppCompatActivity {
    private TaiKhoanDAO taiKhoanDAO;
    private Context context;
    public  static String idtkonl;
    public static String passonl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);
        context = this;
        Intent intent = this.getIntent();
        String tki = "", mki = "";
        if(intent != null){
            tki = intent.getStringExtra("TenDn");
            mki = intent.getStringExtra("pass");
        }
        EditText etTK = (EditText) findViewById(R.id.etTK);
        EditText etMK = (EditText) findViewById(R.id.pass);
        etTK.setText(tki);
        etMK.setText(mki);
        taiKhoanDAO = new TaiKhoanDAO(this);
    }

    public void dangnhap(View view) {
        EditText etTK = (EditText) findViewById(R.id.etTK);
        EditText etMK = (EditText) findViewById(R.id.pass);
        Button btnDangNhap = (Button) findViewById(R.id.btnDN2);
        String tk = etTK.getText().toString();
        String mk = etMK.getText().toString();
        Cursor cursor = taiKhoanDAO.GetTaiKhoan(tk);
        if(tk.equals("") || mk.equals("")){
            Toast.makeText(this, "Vui lòng nhập đủ thông tin đăng nhập!", Toast.LENGTH_LONG).show();
        }else{
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "Tên đăng nhập không tồn tại!", Toast.LENGTH_LONG).show();
            } else {
                while (cursor.moveToNext()) {
                    String pass = cursor.getString(1);
                    if (pass.equals(mk)) {
                        Intent i2 = new Intent(dangnhap.this, trangchu.class);
                        i2.putExtra("idtk", tk);
                        idtkonl = tk;
                        passonl = mk;
                        startActivity(i2);


                        Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_LONG).show();
                        break;
                    } else {
                        Toast.makeText(this, "Sai mật khẩu!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }
}