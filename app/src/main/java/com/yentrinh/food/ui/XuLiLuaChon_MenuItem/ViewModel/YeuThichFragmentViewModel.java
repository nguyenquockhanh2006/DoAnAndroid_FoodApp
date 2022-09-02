package com.yentrinh.food.ui.XuLiLuaChon_MenuItem.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class YeuThichFragmentViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public YeuThichFragmentViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is YeuThichFragmentViewModel");
    }

    public LiveData<String> getText() {
        return mText;
    }
}