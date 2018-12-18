package com.coffe.shentao.mvp.view;

import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.WindowManager;
import android.widget.Toast;

import com.coffe.shentao.mvp.broadcastreceiver.NetBroadcastReceiver;
import com.coffe.shentao.mvp.utils.ActivityUtil;
import com.coffe.shentao.mvp.utils.ConstantUtil;

public abstract class BaseActivity extends AppCompatActivity implements NetBroadcastReceiver.NetChangeListener {
    public static NetBroadcastReceiver.NetChangeListener netEvent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        1、如果定义的BaseActivity是继承android.support.v7.app.AppCompatActivity，需要通过以下方法进行设置：
//// 隐藏标题栏
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

//        2、如果定义的BaseActivity是继承android.app.Activity或者android.support.v4.app.FragmentActivity，需要通过以下方法进行设置：
//// 隐藏标题栏
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 沉浸效果
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        netEvent=this;
        //activity 管理类---退出的时候 释放activity
        ActivityUtil.getInstance().addActivity(this);

        init();
    }

    @Override
    protected void onDestroy() {
        ActivityUtil.getInstance().removeActivity(this);
        netEvent=null;
        super.onDestroy();
    }

    //快速双击返回键 退出程序
    // 设置返回按钮的监听事件
    private long exitTime = 0;

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 监听返回键，点击两次退出程序
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 5000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_LONG).show();
                exitTime = System.currentTimeMillis();
            } else {
                ActivityUtil.getInstance().exitSystem();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


//    四、对BACK、HOME等键统一处理
//    在BaseActivity中可以对BACK、HOME等键统一处理，例如点击返回键的实现，关闭当前页面：
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // 点击手机上的返回键，返回上一层
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            this.finish();
//            ActivityUtil.getInstance().removeActivity(this);
//        }
//        return super.onKeyDown(keyCode, event);
//    }


   // 六、权限申请封装
    // 使用方法---调用 判断权限
    //                 获取权限
    //------------重写 返回结果  doRequestPermissionsResult
    /**
     * 权限检查方法，false代表没有该权限，ture代表有该权限
     */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 权限请求方法
     */
    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param permissions  权限组
     * @param grantResults 结果集
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doRequestPermissionsResult(requestCode, grantResults);
    }

    /**
     * 处理请求权限结果事件
     *
     * @param requestCode  请求码
     * @param grantResults 结果集
     */
    public void doRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
    }
//    七、更改应用程序字体大小
//
//    很多时候，应用程序需要实现修改字体大小功能，或者防止系统字体大小影响应用字体大小。
//    这个实现可以通过在基类的onResume方法中进行操作。

    @Override
    protected void onResume() {
        super.onResume();
        Resources resources = this.getResources();
        Configuration configuration = resources.getConfiguration();
        configuration.fontScale = ConstantUtil.TEXTVIEWSIZE;
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }


    protected abstract  void init();


}
