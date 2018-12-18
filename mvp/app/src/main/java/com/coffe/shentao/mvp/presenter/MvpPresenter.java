package com.coffe.shentao.mvp.presenter;

import android.os.Bundle;

import com.coffe.shentao.mvp.view.MvpView;

/**
 * 生命周期绑定！！！
 * @param <V>
 */
public interface MvpPresenter <V extends MvpView> {
    void onMvpAttachView(V view, Bundle savedInstanceState);

    void onMvpStart();

    void onMvpResume();

    void onMvpPause();

    void onMvpStop();

    void onMvpSaveInstanceState(Bundle savedInstanceState);

    void onMvpDetachView(boolean retainInstance);

    void onMvpDestroy();

}
