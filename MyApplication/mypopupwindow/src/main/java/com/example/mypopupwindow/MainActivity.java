package com.example.mypopupwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void myClick(View view) {
        View popupview = getLayoutInflater().inflate(R.layout.popup_view, null);

        Button btn1 = popupview.findViewById(R.id.btn1);
        Button btn2 = popupview.findViewById(R.id.btn2);

        // 最后一个参数加上后，点击空白处popupwindow会消失
        PopupWindow popupWindow = new PopupWindow(popupview, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);

        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_baseline_airline_seat_recline_extra_24));

//        popupWindow.showAsDropDown(view);
        popupWindow.showAsDropDown(view, view.getWidth(), -view.getHeight());

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("wmj", "按钮1");
                popupWindow.dismiss();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("wmj", "按钮2");
                popupWindow.dismiss();
            }
        });
    }
}