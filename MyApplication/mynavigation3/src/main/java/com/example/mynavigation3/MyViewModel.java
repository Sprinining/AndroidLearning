package com.example.mynavigation3;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private MutableLiveData<Integer> number;

    public MutableLiveData<Integer> getNumber() {
        if (number == null){
            number = new MutableLiveData<>();
            number.setValue(0);
        }
        return number;
    }

    public void add(int n){
        if (number.getValue() < 0){
            number.setValue(0);
        } else {
            number.setValue(number.getValue() + n);
        }
    }
}
