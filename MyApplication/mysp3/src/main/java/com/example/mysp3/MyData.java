package com.example.mysp3;

import android.content.Context;
import android.content.SharedPreferences;

public class MyData {
    public int number;
    private Context context;

    public MyData(Context context) {
        this.context = context;
    }

    public void save(){
        String name = context.getResources().getString(R.string.my_data);
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        String key = context.getResources().getString(R.string.my_key);
        editor.putInt(key, number);
        editor.apply();
    }

    public int load(){
        String name = context.getResources().getString(R.string.my_data);
        SharedPreferences sp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        String key = context.getResources().getString(R.string.my_key);
        int x = sp.getInt(key, 0);
        number = x;
        return x;
    }
}
