package com.example.user.musicplayer;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.musicplayer.adapter.*;

import java.util.ArrayList;
import java.util.List;

//实现OnClickListener的接口
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView logicTv;
    private ViewPager viewPager;


    //将Fragment放入List集合中，存放fragment对象
    private List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        //绑定id
        bindID();
        //设置监听
        listen();
        //创建fragment对象
        LocalFragment localFragment = new LocalFragment();
        //将fragment对象添加到fragmentList中
        fragmentList.add(localFragment);
        //通过MusicPagerAdapter类创建musicPagerAdapter的适配器，下面我将添加MusicPagerAdapter类的创建方法
        MusicPagerAdapter musicPagerAdapter = new MusicPagerAdapter(getSupportFragmentManager(), fragmentList);
        //viewPager绑定适配器
        viewPager.setAdapter(musicPagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            //页面切换，改变标题的样式
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        logicTv.setTextColor(getResources().getColor(R.color.white));
                        break;
                    case 1:
                        logicTv.setTextColor(getResources().getColor(R.color.white_60P));
                        break;
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void listen() {
        logicTv.setOnClickListener(this);
    }

    private void bindID() {
        logicTv = findViewById(R.id.main_logic_tv);
        viewPager = findViewById(R.id.main_vp);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_logic_tv:
                //实现点击TextView切换fragment
                viewPager.setCurrentItem(0);
                break;
            default:
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME) {
            final View view = LayoutInflater.from(this).inflate(R.layout.dialog1_layout, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("提示");
            builder.setView(view);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "确定退出播放器吗", Toast.LENGTH_SHORT).show();

                }
            });
            builder.show();
        }
        return super.onKeyDown(keyCode, event);
    }
}

