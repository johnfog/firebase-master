package com.gnz.firebasemaster.common.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpActivity;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

public abstract class BaseActivity<V extends MvpView, P extends MvpPresenter<V>> extends MvpActivity<V, P> {

    private BaseActivityComponent component;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        component = createActivityComponent();
        super.onCreate(savedInstanceState);

    }

    @NonNull
    protected abstract BaseActivityComponent createActivityComponent();

    @NonNull
    public BaseActivityComponent getComponent() {
        return component;
    }

}
