package com.yentrinh.food.ui.XuLiLuaChon_MenuItem.Fragment;

import static com.yentrinh.food.DoActivity.dangnhap.idtkonl;
import static com.yentrinh.food.DoActivity.trangchu.aty;
import static com.yentrinh.food.DoActivity.trangchu.dbMonAn;
import static com.yentrinh.food.DoActivity.trangchu.dbYeuThich;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.yentrinh.food.DAO.YeuThichDAO;
import com.yentrinh.food.DoActivity.ChiThietMonAn;
import com.yentrinh.food.Model.MonAn;
import com.yentrinh.food.Model.YeuThich;
import com.yentrinh.food.MonAnAdapter;
import com.yentrinh.food.R;
import com.yentrinh.food.databinding.FragmentSaveBinding;
import com.yentrinh.food.ui.XuLiLuaChon_MenuItem.ViewModel.SaveViewModel;

import java.util.ArrayList;


public class SaveFragment extends Fragment {

    private FragmentSaveBinding binding;
    private MonAnAdapter anAdapter;
    private MonAn[] mon;
    private Activity acti = aty;
    private YeuThich[] yt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_save, container, false);

        ListView listView = (ListView) inf.findViewById(R.id.listviewSave);
        createdata();
        anAdapter = new MonAnAdapter(acti, mon);
        listView.setAdapter(anAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(inf.getContext(), ChiThietMonAn.class);
                MonAn mamon = mon[position];
                i.putExtra("mamonan", mamon.getMamon());
                i.putExtra("idne", idtkonl);
                inf.getContext().startActivity(i);
            }
        });
        return inf;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void createdata(){
        Cursor cs = dbMonAn.GETALL();
        Cursor cs2 = dbYeuThich.GETSLfull(idtkonl);

        int b = cs2.getCount();
        yt= new YeuThich[b];
        int i = 0;
        cs2.moveToFirst();
        while (i < b){
            YeuThich tem = new YeuThich(cs2.getString(0), cs2.getString(1));
            yt[i] = tem;
            cs2.moveToNext();
            i++;
        }

        cs.moveToFirst();
        int a = cs2.getCount();
        mon = new MonAn[a];
        i = 0;
        while (i < a){
            if(kiemtra(cs.getString(0),yt) == 1){
                MonAn tem = new MonAn(cs.getString(0), cs.getString(1),cs.getString(2), cs.getString(3), cs.getString(4), cs.getBlob(5));
                mon[i] = tem;
                i++;
            }
            cs.moveToNext();
        }



/*
        ArrayList<MonAn> ma = new ArrayList<>();
        int kq = 0;
        for(int j =0 ; j < mon.length; j ++){
            if(kiemtra(mon[j].getMamon(), yt) == 1){
                ma.add(mon[i]);
                kq ++;
            }
        }
        MonAn[] ketqua = new MonAn[kq];
        int vtri = 0;
        for(int j = 0 ; j < mon.length; j ++){
            if(kiemtra(mon[i].getMamon(),yt) == 1){
                ketqua[vtri] = mon[i];
                vtri ++ ;
            }
        }
        mon = new MonAn[kq];

*/
        ///Toast.makeText(this.getParentFragment().getContext(), String.valueOf(ketqua[1].getHinhAnh().toString()), Toast.LENGTH_LONG).show();
    }
    public int kiemtra(String id , YeuThich[] yt){
        for(int i = 0 ; i < yt.length ; i ++)
            if(yt[i].getMamon().equals(id) == true)
                return 1;
        return 0;
    }
}