package com.example.mysp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    EditText et_name, et_pwd;
    CheckBox cb_rememberpwd, cb_autologin;
    Button bt_register, bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取首选项
        sp = getSharedPreferences("config", Context.MODE_PRIVATE);

        initView();

        // 第二次打开的时候，送sp获取数据，进行画面同步
        boolean rememberpwd = sp.getBoolean("rememberpwd", false);
        boolean autologin = sp.getBoolean("autologin", false);

        if(rememberpwd){
            // 从sp里获取数据，并放到edittext中
            String name = sp.getString("name", "");
            String pwd = sp.getString("pwd", "");
            et_name.setText(name);
            et_pwd.setText(pwd);
            cb_rememberpwd.setChecked(true);
        }
        
        if(autologin){
            cb_autologin.setChecked(true);
            Toast.makeText(this, "自动登录", Toast.LENGTH_SHORT).show();
        }

    }

    private void initView() {
        et_name = findViewById(R.id.et_name);
        et_pwd = findViewById(R.id.et_pwd);
        cb_rememberpwd = findViewById(R.id.cb_rememberpwd);
        cb_autologin = findViewById(R.id.cb_autologin);
        bt_register = findViewById(R.id.bt_register);
        bt_login = findViewById(R.id.bt_login);

        // 设置监听
        MyOnClickLister myOnClickLister = new MyOnClickLister();
        bt_register.setOnClickListener(myOnClickLister);
        bt_login.setOnClickListener(myOnClickLister);
    }

    private class MyOnClickLister implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.bt_register:
                    break;
                case R.id.bt_login:
                    String name = et_name.getText().toString().trim();
                    String pwd = et_pwd.getText().toString().trim();
                    if(TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)){
                        Toast.makeText(getApplicationContext(), "用户名或密码未填写", Toast.LENGTH_SHORT).show();
                    } else {
                        // 保存密码被勾选后，需要记录账号密码和勾选状态
                        if(cb_rememberpwd.isChecked()){
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("name", name);
                            editor.putString("pwd", pwd);
                            editor.putBoolean("rememberpwd", true);
                            editor.apply();
                        }

                        // 自动登录被勾选
                        if(cb_autologin.isChecked()){
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putBoolean("autologin", true);
                            editor.apply();
                        }

                    }
                    break;
                default:
                    break;
            }
        }
    }
}