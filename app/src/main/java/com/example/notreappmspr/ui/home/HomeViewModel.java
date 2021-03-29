package com.example.notreappmspr.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.notreappmspr.MainActivity;

public class HomeViewModel extends ViewModel {

    public static MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Scannez votre QRCODE");

    }


    public LiveData<String> getText() {
        return mText;
    }
}
