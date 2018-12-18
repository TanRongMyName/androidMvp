package com.coffe.shentao.mvp.model;

public class MainModelImpl implements IMainModel {
    @Override
    public void loadData(OnLoadCompleteListener listener) {
        String data="我是从本地加载的数据......";
        listener.onComplete(data);
    }
}
