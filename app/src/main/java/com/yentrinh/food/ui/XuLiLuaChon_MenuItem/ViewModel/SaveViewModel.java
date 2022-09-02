package com.yentrinh.food.ui.XuLiLuaChon_MenuItem.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SaveViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public SaveViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("day la save");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
