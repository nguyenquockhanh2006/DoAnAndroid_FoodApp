package com.yentrinh.food.DoActivity;

import static com.yentrinh.food.DoActivity.dangnhap.idtkonl;
import static com.yentrinh.food.DoActivity.dangnhap.passonl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.yentrinh.food.DAO.FollowDAO;
import com.yentrinh.food.DAO.MonAnDAO;
import com.yentrinh.food.DAO.YeuThichDAO;
import com.yentrinh.food.XuLifragment.SuaThongTinFragment;
import com.yentrinh.food.DAO.TaiKhoanDAO;
import com.yentrinh.food.R;
import com.yentrinh.food.databinding.ActivityTrangchuBinding;
import com.yentrinh.food.ui.XuLiLuaChon_MenuItem.Fragment.HomeFragment;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class trangchu extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private int REQUEST_CODE_FOLDER = 456;
    private ActivityTrangchuBinding binding;
    private Context context;
    public static TaiKhoanDAO  dbTaiKhoan;
    public static MonAnDAO dbMonAn;
    public static Context trangchucontext;
    public static FollowDAO dbFollow;
    public static YeuThichDAO dbYeuThich;
    public static Activity aty;
    public static String TimMonAn = "";
    public static String mamonxetct = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        aty = this;
        context = this;
        trangchucontext = this;
        dbTaiKhoan = new TaiKhoanDAO(this);
        dbMonAn = new MonAnDAO(this);
        dbFollow = new FollowDAO(this);
        dbYeuThich = new YeuThichDAO(this);
        Intent intent = this.getIntent();
        String idtk = intent.getStringExtra("idtk");
        Cursor cs = dbTaiKhoan.GetTaiKhoan(idtk);
        byte[] hinh  = null;
        binding = ActivityTrangchuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarTrangchu.toolbar);
        binding.appBarTrangchu.toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_doipass, R.id.nav_doipass,  R.id.nav_save, R.id.nav_love, R.id.nav_Dangxuat)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_trangchu);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        TextView etten = binding.txtTen;
        TextView etgmai = binding.txtGmail;
        TextView etfl = binding.txtDangTheoDoi;
        TextView etfled = binding.txtDuocTheoDoi;
        int ietfl = 0, ietfled = 0;
        while (cs.moveToNext()) {
            String ten = cs.getString(2);
            String gmail = cs.getString(5);
            ietfled = dbFollow.DemFollow(cs.getString(0));
            ietfl = dbFollow.DemLuotFollow(cs.getString(0));
            if(cs.getBlob(6) != null){
                hinh = cs.getBlob(6);

                Bitmap bitmap = BitmapFactory.decodeByteArray(hinh, 0, hinh.length);
                ImageView avtr = (ImageView) binding.imgAvt;
                avtr.setImageBitmap(bitmap);
            }


            etten.setText(ten);
            etgmai.setText(gmail);
            etfled.setText("Được theo dõi: "+ietfled);
            etfl.setText("Đang theo dõi: "+ ietfl);
            break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.trangchu, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_trangchu);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void SuaTTTaiKhoan(View view) {
        TextView txt = (TextView) findViewById(R.id.txtTentk);
        Fragment fragment =  new SuaThongTinFragment();
        replaceFragment(fragment);
    }

    public void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment_content_trangchu, fragment);
        fragmentTransaction.commit();
    }
    public void LuuTTne(View view) {
        EditText hoten = (EditText) findViewById(R.id.txtHoTenEdit);
        EditText ngaysinh = (EditText) findViewById(R.id.txtNgaySinhedit);
        EditText diachi = (EditText) findViewById(R.id.txtDiaChiEdit);
        EditText gmail = (EditText) findViewById(R.id.txtGmailEdit);
        // set ve byte
        ImageView imgavatarne = (ImageView) findViewById(R.id.imgAvatar);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) imgavatarne.getDrawable();
        Bitmap bitmap = bitmapDrawable.getBitmap();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] hinhanh = byteArrayOutputStream.toByteArray();

        dbTaiKhoan.UpdateToTaiKhoan(idtkonl,passonl, hoten.getText().toString(),ngaysinh.getText().toString(), diachi.getText().toString(), gmail.getText().toString(), hinhanh );
        //reload();
        Toast.makeText(this, "Đã lưu!", Toast.LENGTH_LONG).show();
    }
    public  void Mofolder(View view){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, REQUEST_CODE_FOLDER);

        Toast.makeText(this, "abc", Toast.LENGTH_LONG).show();
    }
    @Override
    public void onActivityResult(int requesCode, int resultCode, Intent data) {

        if (requesCode == REQUEST_CODE_FOLDER && resultCode == RESULT_OK && data != null) {
            Uri uri  = data.getData();
            try{
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ImageView imghinh = (ImageView) findViewById(R.id.imgAvatar);
                imghinh.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requesCode, resultCode, data);
    }

    public void XacNhanPass(View view) {
        Cursor cs = dbTaiKhoan.GetTaiKhoan(idtkonl);


        EditText pc = (EditText) findViewById(R.id.txtpasscu);
        EditText mkm = (EditText) findViewById(R.id.txtpassmoi);
        EditText xnpm = (EditText) findViewById(R.id.txtxacnhanpassmoi);

        String smkc = pc.getText().toString();
        String smkm = mkm.getText().toString();
        String sxnmk = xnpm.getText().toString();

        if(passonl.equals(smkc) == false){
            Toast.makeText(this, "Mật khẩu củ không đúng! mk = "+passonl, Toast.LENGTH_LONG).show();
        }
        else{
            if(smkm.equals(smkc) == true){
                Toast.makeText(this, "Mật khẩu mới không thể giống mật khẩu cũ!", Toast.LENGTH_LONG).show();
            }else{
                if(smkm.equals(sxnmk) != true){
                    Toast.makeText(this, "Mật khẩu mới và xác nhận không giống nhau!", Toast.LENGTH_LONG).show();
                }
                else {
                    String truyvan = "UPDATE TaiKhoan SET MatKhau = '"+smkm+"' WHERE TenTK ='"+idtkonl+"'";
                    dbTaiKhoan.UpdateTaiKhoan(truyvan);
                    Toast.makeText(this, "Đã thay đổi mật khẩu!", Toast.LENGTH_LONG).show();
                }
            }
        }

    }


    public void btnmotrangdangbai(View view) {
        Toast.makeText(this, "mo trang dang ", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this , DangBV.class));

    }



    public void LoadKien(View view) {
        TextView txt = (TextView) findViewById(R.id.txtTentk);
        TimMonAn = "KIENG";
        Fragment fragment =  new HomeFragment();
        replaceFragment(fragment);
    }
    public void LoadMan(View view) {
        TextView txt = (TextView) findViewById(R.id.txtTentk);
        TimMonAn = "MAN";
        Fragment fragment =  new HomeFragment();
        replaceFragment(fragment);
    }
    public void LoadChay(View view) {
        TextView txt = (TextView) findViewById(R.id.txtTentk);
        TimMonAn = "CHAY";
        Fragment fragment =  new HomeFragment();
        replaceFragment(fragment);
    }
    public void LoadVat(View view) {
        TextView txt = (TextView) findViewById(R.id.txtTentk);
        TimMonAn = "VAT";
        Fragment fragment =  new HomeFragment();
        replaceFragment(fragment);
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
    public void ChuyenDenTimKiem(View view) {
        Toast.makeText(this, "Sẵn sàng để tìm kiếm ", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this , TimKiemActivity.class));
    }
    public void ChuyenDenDangXuat(View view) {
        startActivity(new Intent(this, dangnhap.class));
    }

}