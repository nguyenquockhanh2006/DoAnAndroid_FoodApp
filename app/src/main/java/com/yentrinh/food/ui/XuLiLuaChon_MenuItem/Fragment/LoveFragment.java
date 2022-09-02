package com.yentrinh.food.ui.XuLiLuaChon_MenuItem.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.yentrinh.food.databinding.FragmentLoveBinding;
import com.yentrinh.food.ui.XuLiLuaChon_MenuItem.ViewModel.YeuThichFragmentViewModel;

public class LoveFragment extends Fragment {

    private FragmentLoveBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        YeuThichFragmentViewModel homeViewModel =
                new ViewModelProvider(this).get(YeuThichFragmentViewModel.class);

        binding = FragmentLoveBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        return root;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}