package com.example.mylivedata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelWithLiveData extends ViewModel {

    private MutableLiveData<Integer> LikedNumber;

    public ViewModelWithLiveData() {
        LikedNumber = new MutableLiveData<>();
        LikedNumber.setValue(0);
    }

    public MutableLiveData<Integer> getLikedNumber() {
        if(LikedNumber == null){
            LikedNumber = new MutableLiveData<>();
            LikedNumber.setValue(0);
        }
        return LikedNumber;
    }

    public void addLikedNumber(int n){
        LikedNumber.setValue(LikedNumber.getValue() + n);
    }
}