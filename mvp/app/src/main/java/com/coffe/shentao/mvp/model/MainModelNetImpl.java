package com.coffe.shentao.mvp.model;

public class MainModelNetImpl implements IMainModel {
    @Override
    public void loadData(OnLoadCompleteListener listener) {
        String data = "我是从网络加载的数据";
        listener.onComplete(data);
    }
}
