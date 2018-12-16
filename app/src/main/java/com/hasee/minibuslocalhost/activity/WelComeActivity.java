package com.hasee.minibuslocalhost.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.hasee.minibuslocalhost.R;

/**
 * Created by fangju on 2018/12/15
 */
public class WelComeActivity extends BaseActivity {
    private Context mContext;//上下文
    private ImageView welComeImageView;//启动图片
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity_layout);
        mContext = WelComeActivity.this;
        welComeImageView = (ImageView)findViewById(R.id.welCome_imageView);
        Glide.with(mContext)
                .load("");

    }
}
