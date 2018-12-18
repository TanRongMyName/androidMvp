package com.coffe.shentao.mvp.presenter;



import android.os.Bundle;

import com.coffe.shentao.mvp.view.IMainView2;
public class MainPresenterImpl extends BaseMvpPresenter<IMainView2> implements IMainPrensenter {

    @Override
    public void requestTestContent() {
        //先进行非空判断
        if (isViewAttached()) {
            getView().setTestContent();
        }
    }

    @Override
    public void onMvpAttachView(IMainView2 view, Bundle savedInstanceState) {
        super.onMvpAttachView(view, savedInstanceState);
    }
    /**
     *重写P层需要的生命周期，进行相关逻辑操作
     */
    @Override
    public void onMvpResume() {
        super.onMvpResume();
    }
}
