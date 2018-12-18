package com.coffe.shentao.mvp.model;

public interface IMainModel {
    void loadData(OnLoadCompleteListener listener);
    interface OnLoadCompleteListener{
        void onComplete(String data);
    }
}
