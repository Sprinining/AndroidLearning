package com.example.myviewmodelrestore;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MyViewModel extends ViewModel {
    private SavedStateHandle handle;
    public MyViewModel(SavedStateHandle handle){
        if (!handle.contains(MainActivity.KEY_NUMBER)){
            handle.set(MainActivity.KEY_NUMBER, 0);
        }
        this.handle = handle;
    }

    public MutableLiveData<Integer> getNumber() {
        return handle.getLiveData(MainActivity.KEY_NUMBER);
    }

    public void add(){
        handle.set(MainActivity.KEY_NUMBER, (int) handle.get(MainActivity.KEY_NUMBER) + 1);
    }
}
