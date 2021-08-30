package com.example.mygson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.mygson.bean.Job;
import com.example.mygson.bean.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        User user = new User("wmj", "666", 24, false);
        Job teacher = new Job("teacher", 10000);
        user.setJob(teacher);

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        // 序列化
        String json = gson.toJson(user);
//        Log.d("wmj", "onCreate: " + json);

        // 反序列化
        User user1 = gson.fromJson(json, User.class);
//        Log.d("wmj", "onCreate: " + user1.getUserName());

        User[] users = new User[3];
        users[0] = new User("wmj", "666", 24, false);
        users[0].setJob(teacher);
        users[2] = new User("wmj", "666", 24, false);
        users[2].setJob(new Job());

//        json = gson.toJson(users);
//        Log.d("wmj", "onCreate: " + json);
//
//        User[] users1 = gson.fromJson(json, User[].class);
//        Log.d("wmj", "onCreate: " + users1[0].getJob().getSalary());


//        List<User> list = new ArrayList<>();
//        list.add(users[0]);
//        list.add(users[1]);
//        list.add(users[2]);
//
//        // 序列化
//        json = gson.toJson(list);
//        Log.d("wmj", "onCreate: " + json);
//
//        // 反序列化
//        Type type = new TypeToken<List<User>>(){}.getType();
//        List<User> list1 = gson.fromJson(json, type);
//        Log.d("wmj", "onCreate: " + list1.get(0).getJob().getSalary());


//        Map<String, User> map = new HashMap<>();
//        map.put("1", users[0]);
//        map.put("2", users[1]);
//        map.put("3", users[2]);
//        map.put(null, null);
//
//        json = gson.toJson(map);
//        Log.d("wmj", "onCreate: " + json);
//
//        Type type = new TypeToken<Map<String, User>>(){}.getType();
//        Map<String, User> map1 = gson.fromJson(json, type);
//        Log.d("wmj", "onCreate: " + map1.get("1").getJob().getSalary());

        Set<User> set = new HashSet<>();
        set.add(users[0]);
        set.add(users[1]);
        set.add(users[2]);

        json = gson.toJson(set);
        Log.d("wmj", "onCreate: " + json);

        Type type = new TypeToken<List<User>>(){}.getType();
        List<User> list = gson.fromJson(json, type);
        Log.d("wmj", "onCreate: " + list.get(1).getJob().getSalary());

        Type type1 = new TypeToken<Set<User>>(){}.getType();
        Set<User> set1 = gson.fromJson(json, type1);
        Iterator<User> iterator = set1.iterator();
        while (iterator.hasNext()){
            User next = iterator.next();
            Log.d("wmj", "onCreate: " + next);
        }

    }
}