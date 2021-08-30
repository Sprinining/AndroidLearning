package com.example.myviewmodelsp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;


public class MyViewModel extends AndroidViewModel {

    SavedStateHandle handle;
    String key = getApplication().getResources().getString(R.string.data_key);
    String spName = getApplication().getResources().getString(R.string.sp_name);

    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);

        this.handle = handle;
        if (!handle.contains(key)) {
            load();
        }
    }

    public LiveData<Integer> getNumber() {
        return handle.getLiveData(key);
    }

    private void load() {
        SharedPreferences sp = getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        int x = sp.getInt(key, 0);
        handle.set(key, x);
    }

    public void save() {
        SharedPreferences sp = getApplication().getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, getNumber().getValue());
        editor.apply();
    }

    public void add(int x) {
        handle.set(key, getNumber().getValue() + x);
//        save();
    }
}
