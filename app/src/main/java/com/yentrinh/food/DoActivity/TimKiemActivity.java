package com.yentrinh.food.DoActivity;

import static com.yentrinh.food.DoActivity.dangnhap.idtkonl;
import static com.yentrinh.food.DoActivity.trangchu.dbMonAn;
import static com.yentrinh.food.DoActivity.trangchu.trangchucontext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.yentrinh.food.DoActivity.ChiThietMonAn;
import com.yentrinh.food.Model.MonAn;
import com.yentrinh.food.MonAnAdapter;
import com.yentrinh.food.R;

public class TimKiemActivity extends AppCompatActivity {

    private MonAnAdapter anAdapter;
    private MonAn[] mon;
    private Context context;

    private static String maTK;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tim_kiem);
        context = this;
        ListView listView = (ListView) findViewById(R.id.listTimKiemcao);
        getmonantenloai();
        anAdapter = new MonAnAdapter(this, mon);
        listView.setAdapter(anAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(view.getContext() , ChiThietMonAn.class);
                MonAn mamon = mon[position];
                maTK = mamon.getMamon();
                i.putExtra("mamonan", mamon.getMamon());
                startActivity(i);
            }
        });

    }
    private void getmonantenloai() {
        EditText edttim = (EditText) findViewById(R.id.txtdoannaytim);
        Editable txttim = edttim.getText();
        Cursor cs = dbMonAn.GETALL();
        cs.moveToFirst();
        int a = cs.getCount();
        mon = new MonAn[a];
        int i = 0;
        while (i < a){

                MonAn tem = new MonAn(cs.getString(0), cs.getString(1),cs.getString(2), cs.getString(3), cs.getString(4), cs.getBlob(5));
                mon[i] = tem;
                cs.moveToNext();
                i++;

        }
    }


    public void clicktimkiemten(View view) {
        ListView listView = (ListView) findViewById(R.id.listTimKiemcao);
        createdata();
        anAdapter = new MonAnAdapter(this, mon);
        listView.setAdapter(anAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(view.getContext(), ChiThietMonAn.class);
                MonAn mamon = mon[position];
                i.putExtra("mamonan", mamon.getMamon());
                i.putExtra("idne", idtkonl);
                startActivity(i);
            }
        });

    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }

    public void createdata(){
        EditText edttim = (EditText) findViewById(R.id.txtdoannaytim);
        String txttim = edttim.getText().toString();
        Toast.makeText(this, txttim, Toast.LENGTH_LONG).show();
        Cursor cs = dbMonAn.GETALL();
        cs.moveToFirst();
        int a = cs.getCount();

        int i = 0, z = 0,k=0;
        while (z<a){
            if(cs.getString(1).contains(txttim) == true)
            {
                i++;
            }
                z++;
                cs.moveToNext();
        }
        mon = new MonAn[i];z=0;
        cs.moveToFirst();
        while (z<cs.getCount()){
            if(cs.getString(1).contains(txttim) == true)
            {
                MonAn tem = new MonAn(cs.getString(0), cs.getString(1),cs.getString(2), cs.getString(3), cs.getString(4), cs.getBlob(5));
                mon[k] = tem;
                k++;
            }
            z++;
            cs.moveToNext();
        }

    }
}
