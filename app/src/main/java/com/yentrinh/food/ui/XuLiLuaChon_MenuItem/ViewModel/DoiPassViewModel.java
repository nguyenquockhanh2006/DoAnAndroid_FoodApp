package com.yentrinh.food.ui.XuLiLuaChon_MenuItem.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DoiPassViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public DoiPassViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("doi pass");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
