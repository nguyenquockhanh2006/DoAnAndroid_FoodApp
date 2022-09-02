package com.yentrinh.food.ui.XuLiLuaChon_MenuItem.Fragment;

import static com.yentrinh.food.DoActivity.dangnhap.idtkonl;
import static com.yentrinh.food.DoActivity.trangchu.dbMonAn;
import static com.yentrinh.food.DoActivity.trangchu.trangchucontext;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.yentrinh.food.DoActivity.Suamonan;
import com.yentrinh.food.Model.MonAn;
import com.yentrinh.food.MonAnAdapter;
import com.yentrinh.food.R;
import com.yentrinh.food.databinding.FragmentSlideshowBinding;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private MonAnAdapter anAdapter;
    private MonAn[] mon;
    public static String MaSua;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_slideshow, container, false);

        ListView listView = (ListView) inf.findViewById(R.id.listbd);

        getmonantenloai();
        anAdapter = new MonAnAdapter(this.getParentFragment().getActivity(), mon);
        listView.setAdapter(anAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(inf.getContext(), Suamonan.class);
                MonAn mamon = mon[position];
                MaSua = mamon.getMamon();
                Toast.makeText(trangchucontext, "Bắt đầu sửa "+ mamon.getTenMon() +" tại đây!",
                        Toast.LENGTH_SHORT).show();
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


    private void getmonantenloai() {
        Cursor cs = dbMonAn.GetMonid(idtkonl);
        int a = 0;
        int i = 0;
        mon = new MonAn[cs.getCount()];
        cs.moveToFirst();
        while (i < cs.getCount()){

               MonAn tem = new MonAn(cs.getString(0), cs.getString(1),cs.getString(2), cs.getString(3), cs.getString(4), cs.getBlob(5));
               mon[i] = tem;
               i++;

            cs.moveToNext();
        }


//        cs.moveToFirst();
//        while (a < cs.getCount()){
//            if(cs.getString(3).equals(idtkonl) == true)
//            {
//                MonAn tem = new MonAn(cs.getString(0), cs.getString(1),cs.getString(2), cs.getString(3), cs.getString(4), cs.getBlob(5));
//                mon[a] = tem;
//                a++;
//            }
//            cs.moveToNext();
//        }

    }

}