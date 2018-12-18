package com.coffe.shentao.mvp.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

import com.coffe.shentao.mvp.utils.NetworkUtil;
import com.coffe.shentao.mvp.view.BaseActivity;

public class NetBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
            boolean netWorkState = NetworkUtil.isNetworkConnected(context);
            // 接口回调传过去状态的类型
            if (BaseActivity.netEvent != null) {
                BaseActivity.netEvent.onNetChange(netWorkState);
            }
        }


    }
    public interface NetChangeListener{
        void onNetChange(boolean netWorkState);
    }
}
