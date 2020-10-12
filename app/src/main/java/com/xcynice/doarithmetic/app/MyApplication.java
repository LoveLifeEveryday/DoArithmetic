package com.xcynice.doarithmetic.app;


import android.app.Application;

import com.didichuxing.doraemonkit.DoraemonKit;
import com.xcynice.doarithmetic.util.ActivityUtil;
import com.xcynice.doarithmetic.util.LogUtil;
import com.xcynice.doarithmetic.util.XUtil;

/**
 * @Author 许朋友爱玩
 * @Date 2020/10/11 19:59
 * @Github https://github.com/LoveLifeEveryday
 * @JueJin https://juejin.im/user/5e429bbc5188254967066d1b/posts
 * @Description MyApplication
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        DoraemonKit.install(this,"46facab743d9e566a8b57525246e8e22");
        //初始化
        XUtil.initialize(this);
        //设置打印开关
        LogUtil.setIsLog(true);
        //注册Activity生命周期
        registerActivityLifecycleCallbacks(ActivityUtil.getActivityLifecycleCallbacks());
    }
}
