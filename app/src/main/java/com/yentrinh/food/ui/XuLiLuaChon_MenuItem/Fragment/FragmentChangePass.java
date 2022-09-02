package com.yentrinh.food.ui.XuLiLuaChon_MenuItem.Fragment;
import static com.yentrinh.food.DoActivity.dangnhap.idtkonl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.yentrinh.food.DAO.TaiKhoanDAO;
import com.yentrinh.food.R;
import com.yentrinh.food.databinding.FragmentChangePassBinding;
import com.yentrinh.food.ui.XuLiLuaChon_MenuItem.ViewModel.DoiPassViewModel;

public class FragmentChangePass extends Fragment {

    private FragmentChangePassBinding binding;
    private String id;
    private View vv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = idtkonl;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_change_pass, container, false);

        vv = inf;
        return inf;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}