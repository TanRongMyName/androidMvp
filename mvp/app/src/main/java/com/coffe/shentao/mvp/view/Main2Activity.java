package com.coffe.shentao.mvp.view;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.coffe.shentao.mvp.R;
import com.coffe.shentao.mvp.presenter.IMainPrensenter;
import com.coffe.shentao.mvp.presenter.MainPresenterImpl;
import com.coffe.shentao.mvp.utils.ConstantUtil;

public class Main2Activity extends BaseMvpActivity<IMainPrensenter> implements IMainView2 {

    @Override
    protected IMainPrensenter createPresenter() {
        return new MainPresenterImpl();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        presenter.requestTestContent();
    }

    @Override
    protected void init() {
// 判断权限
        if (!hasPermission(Manifest.permission.READ_PHONE_STATE)) {
            requestPermission(ConstantUtil.PERMISSIONS_REQUEST_READ_PHONE_STATE, Manifest.permission.READ_PHONE_STATE);
        }
    }
    // 处理请求权限结果
    @Override
    public void doRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
        switch (requestCode) {
            case ConstantUtil.PERMISSIONS_REQUEST_READ_PHONE_STATE:// 读取手机信息权限
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 权限请求成功
                    Toast.makeText(this, "权限请求成功", Toast.LENGTH_SHORT).show();
                } else {
                    // 权限请求失败
                    Toast.makeText(this, "权限请求失败", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @Override
    public void onNetChange(boolean netWorkState) {
        Log.i(getClass().getSimpleName(),netWorkState ? "有网络" : "无网络");
    }

    @Override
    public void setTestContent() {
        Log.i(getClass().getSimpleName(), "测试成功");
    }
}
