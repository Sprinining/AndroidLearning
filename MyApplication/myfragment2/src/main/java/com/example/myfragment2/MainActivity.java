package com.example.myfragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.btn1);
        Button button2 = findViewById(R.id.btn2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn1:

                // activity 与 fragment通信
                // 原生方法：Bundle
                Bundle bundle = new Bundle();
                bundle.putString("haha", "xixi");
                BlankFragment1 blankFragment1 = new BlankFragment1();
                blankFragment1.setArguments(bundle);

                // 使用接口通信
                blankFragment1.setFragmentCallback(new IFragmentCallback() {
                    @Override
                    public void sendMsgToActivity(String str) {
                        Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public String getMsgFromActivity() {
                        return "发送给Fragment的";
                    }
                });

                replaceFragment(blankFragment1);
                break;
            case R.id.btn2:
                replaceFragment(new ItemFragment());
                break;
            default:
                break;
        }
    }

    // 动态切换fragment
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl, fragment);

        // 放到同一个栈里，点返回时显示上一个fragment
//        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }
}