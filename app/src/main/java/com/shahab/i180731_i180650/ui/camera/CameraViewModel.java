package com.shahab.i180731_i180650.ui.camera;

import android.util.MutableInt;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CameraViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<Integer> mImg;

    public CameraViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Send at recent contacts");


    }

    public void setmImg(MutableLiveData<Integer> mImg) {
        this.mImg = mImg;
    }

    public MutableLiveData<Integer> getmImg() {
        return mImg;
    }

    public LiveData<String> getText() {
        return mText;
    }
}