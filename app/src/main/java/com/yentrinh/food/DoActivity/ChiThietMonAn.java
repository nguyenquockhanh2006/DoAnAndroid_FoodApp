package com.yentrinh.food.DoActivity;

import static com.yentrinh.food.DoActivity.dangnhap.idtkonl;
import static com.yentrinh.food.DoActivity.trangchu.mamonxetct;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.yentrinh.food.DAO.DanhGiaDAO;
import com.yentrinh.food.DAO.MonAnDAO;
import com.yentrinh.food.DAO.YeuThichDAO;
import com.yentrinh.food.R;

public class ChiThietMonAn extends AppCompatActivity {

    private Context context;
    private MonAnDAO dbMon;
    private DanhGiaDAO dbDanhGia;
    private YeuThichDAO dbYeuThich;
    private byte[] hinhanh;
    private ImageView imganhct;
    private TextView etTenCTmon;
    private TextView etCTLoai;
    private TextView etCTId;
    private TextView etCTTong;
    private RadioGroup radCTDanhgia;
    private TextView etCongthuc;
    private String mamon = "MM2";
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_thiet_mon_an);
        Intent intent = this.getIntent();
        mamon = intent.getStringExtra("mamonan");
        context = this;
        dbMon = new MonAnDAO(this);
        dbDanhGia = new DanhGiaDAO(this);
        dbYeuThich = new YeuThichDAO(this);

        etTenCTmon = (TextView) findViewById(R.id.txtCTtenmon);
        etCTLoai = (TextView) findViewById(R.id.txtCTLoai);
        etCTId = (TextView) findViewById(R.id.txtCTNguoiDang);
        etCTTong = (TextView) findViewById(R.id.txtCTTongDG);
        radCTDanhgia = (RadioGroup) findViewById(R.id.ragDanhGia);
        imganhct = (ImageView) findViewById(R.id.imgChiTietMon);
        etCongthuc = (TextView) findViewById(R.id.etCTCongThuc);

        Cursor cs = dbMon.GETALL();
        cs.moveToFirst();
        String ma = "";
        while (0==0) {
            ma = cs.getString(0);
            if(ma.contains(mamon) == true){
                if(cs.getBlob(5) != null){
                    hinhanh = cs.getBlob(5);

                    Bitmap bitmap = BitmapFactory.decodeByteArray(hinhanh, 0, hinhanh.length);

                    imganhct.setImageBitmap(bitmap);
                }
                etTenCTmon.setText(cs.getString(1));
                String loai = "";
                String temp = cs.getString(2);
                if(temp.contains("MAN")){
                    loai += "Món mặn / ";
                }
                if(temp.contains("CHAY")){
                    loai += "Món chay / ";
                }
                if(temp.contains("KIENG")){
                    loai += "Món kiêng / ";
                }
                if(temp.contains("VAT")){
                    loai += "Món ăn vặt / ";
                }
                etCTLoai.setText(loai);
                etCTId.setText(cs.getString(3));
                etCTTong.setText(cs.getString(4));
                etCongthuc.setText(cs.getString(4));
                break;
            }
            cs.moveToNext();
        }

        Cursor cs2 = dbDanhGia.GETSL(mamon, idtkonl);

        RadioButton radioButton;
        cs2.moveToFirst();
        Toast.makeText(this, String.valueOf(cs2.getCount()),Toast.LENGTH_LONG).show();

        if(cs2.getCount() != 0){
            while (0==0){
                String dadanh = cs2.getString(2);

                if(dadanh.equals("1")){
                    radioButton = (RadioButton) findViewById(R.id.rad1);
                    radioButton.setChecked(true);
                    break;
                }
                if(dadanh.equals("2")){
                    radioButton = (RadioButton) findViewById(R.id.rad2);
                    radioButton.setChecked(true);
                    break;
                }
                if(dadanh.equals("3")){
                    radioButton = (RadioButton) findViewById(R.id.rad3);
                    radioButton.setChecked(true);
                    break;
                }
                if(dadanh.equals("4")){
                    radioButton = (RadioButton) findViewById(R.id.rad4);
                    radioButton.setChecked(true);
                    break;
                }
                if(dadanh.equals("5")){
                    radioButton = (RadioButton) findViewById(R.id.rad5);
                    radioButton.setChecked(true);
                    break;
                }
                cs.moveToNext();
            }
        }

        Cursor cs3 = dbDanhGia.GETSLfull(mamon);

        if(cs3.getCount() != 0){
            cs3.moveToFirst();
            int tong = 0;
            int i = 0;
            while (i<cs3.getCount()){
                tong += Integer.parseInt(cs3.getString(2));
                cs3.moveToNext();
                i++;
            }
            float kq = tong/cs3.getCount();
            etCTTong.setText(String.valueOf(kq));
        }
        else{
            etCTTong.setText("0");
        }

        Cursor cs4 = dbYeuThich.GETSL(mamon, idtkonl);
        Button btnyeuthich = (Button) findViewById(R.id.btnluuvaokho);
        if(cs4.getCount() != 0)
        {
            btnyeuthich.setText("Đã lưu");
            int mau = R.color.mauchinh;
            btnyeuthich.setBackgroundColor(mau);
        }else{
            btnyeuthich.setText("Thêm vào kho");
            int mau = R.color.mauxam;
            btnyeuthich.setBackgroundColor(mau);
        }


    }
    public  void AnhXa(View view){
        etTenCTmon = (TextView) findViewById(R.id.txtCTtenmon);
        etCTLoai = (TextView) findViewById(R.id.txtCTLoai);
        etCTId = (TextView) findViewById(R.id.txtCTNguoiDang);
        etCTTong = (TextView) findViewById(R.id.txtCTTongDG);
        radCTDanhgia = (RadioGroup) findViewById(R.id.ragDanhGia);
    }

    public void luudanhgia(View view) {
        RadioButton rb1 = (RadioButton) findViewById(R.id.rad1);
        RadioButton rb2 = (RadioButton) findViewById(R.id.rad2);
        RadioButton rb3 = (RadioButton) findViewById(R.id.rad3);
        RadioButton rb4 = (RadioButton) findViewById(R.id.rad4);
        RadioButton rb5 = (RadioButton) findViewById(R.id.rad5);
        String danhgiamoi = "";
        if(rb1.isChecked() == true){
            danhgiamoi = "1";
        }
        if(rb2.isChecked() == true){
            danhgiamoi = "2";
        }
        if(rb3.isChecked() == true){
            danhgiamoi = "3";
        }
        if(rb4.isChecked() == true){
            danhgiamoi = "4";
        }
        if(rb5.isChecked() == true){
            danhgiamoi = "5";
        }

        dbDanhGia.Delete(mamon,idtkonl);
        dbDanhGia.Insert(mamon,idtkonl,danhgiamoi);
        Toast.makeText(this, "Đã lưu!", Toast.LENGTH_LONG).show();
        reload();
    }
    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    public void luuvaokho(View view) {
        Cursor cs = dbYeuThich.GETSL(mamon, idtkonl);
        if(cs.getCount() == 0){
            dbYeuThich.Insert(mamon, idtkonl);
        }else
        {
            dbYeuThich.Delete(mamon, idtkonl);
        }
        reload();
    }
}