package com.derry.jetpack_app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * androidx == JetPack
 *
 * 把 Activity 的 数据Modlel 给抽取出来
 */
public class MainViewModel extends AndroidViewModel {

    // LiveData 对数据的感应（数据发送改变，观察者设计模式，让我们的观察者 改变 == UI改变）
    private MutableLiveData<String> phoneInfo; // 这个phoneInfo == Model changes

    private Context context;
    // Google  Toast  Context
    // 如果不需要 环境 Context，就直接继承ViewModel
    // 如果需要 环境 Context ，就直接继承AndroidViewModel
    public MainViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
    }

    // 暴露出去 数据
    public MutableLiveData<String> getPhoneInfo() {
        if (phoneInfo == null) { // 提供一点点性能
            phoneInfo = new MutableLiveData<>();
            phoneInfo.setValue(""); // 初始化数据
        }
        return phoneInfo;
    }

    /**
     * 输入 所有普通按键
     * @param number
     */
    public void appendNumber(String number) {
        phoneInfo.setValue(phoneInfo.getValue() + number); // 弊端 性能 sb去完成
    }

    /**
     * 删除
     */
    public void backspaceNumber() {
        int length = phoneInfo.getValue().length();
        if (length > 0) {
            phoneInfo.setValue(phoneInfo.getValue().substring(0, phoneInfo.getValue().length() - 1));
        }
    }

    /**
     * 清空
     */
    public void clear() {
        phoneInfo.setValue("");
    }

    /**
     * 拨打
     */
    public void callPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneInfo));
        this.context.startActivity(intent);
    }
}
