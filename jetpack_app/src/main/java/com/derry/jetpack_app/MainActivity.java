package com.derry.jetpack_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.derry.jetpack_app.databinding.ActivityMainBinding;


/**
 * TODO 同学们 这是项目改造成 Jetpack 的过程
 *
 * JetPack更新比 Android 更快  ---> 自己独立出来的，所以很快 更新很快
 *
 * JetPack: Android很多的问题，很多第三方框架， JetPack（Google真正解决一系列问题）
 *
 * Google： MainActivity 就是管控者 --- （单例原则）
 */
public class MainActivity extends AppCompatActivity {

    // DataBind后续函数的名字，是根据 布局里面指定的  标记变化而变化  例如：setAaaaaaaaaaaaaaaaaaa
    private ActivityMainBinding binding;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setData(viewModel);
        // 如何建立感应 model 改变  -----------> 观察者（布局）
        binding.setLifecycleOwner(this);
    }
}
