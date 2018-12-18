package com.coffe.shentao.mvp.utils;

import com.coffe.shentao.mvp.view.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class ActivityUtil {
    private List<BaseActivity> activityList = new ArrayList<>();
    private static ActivityUtil instance;

    // 单例模式中获取唯一的ExitApplication实例
    public static synchronized ActivityUtil getInstance() {
        if (null == instance) {
            instance = new ActivityUtil();
        }
        return instance;
    }

    // 添加Activity到容器中
    public void addActivity(BaseActivity activity) {
        if (activityList == null)
            activityList = new ArrayList<>();
        activityList.add(activity);
    }

    // 移除Activity
    public void removeActivity(BaseActivity activity) {
        if (activityList != null)
            activityList.remove(activity);
    }

    // 遍历所有Activity并finish
    public void exitSystem() {
        for (BaseActivity activity : activityList) {
            if (activity != null)
                activity.finish();
        }
        // 退出进程
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }
}
