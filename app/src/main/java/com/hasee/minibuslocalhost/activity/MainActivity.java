package com.hasee.minibuslocalhost.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hasee.minibuslocalhost.R;
import com.hasee.minibuslocalhost.util.ActivityCollector;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 返回键监听
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCollector.finshAll();
    }
}
