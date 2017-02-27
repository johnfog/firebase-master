package com.gnz.firebasemaster.login;

import android.support.annotation.NonNull;

import com.gnz.firebasemaster.common.mvp.ErrorPresenter;
import com.gnz.firebasemaster.common.mvp.HttpErrorView;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

public interface LoginContract {

    interface View extends MvpView, HttpErrorView {

        void showInvalidCredentials();

        void logUserIn();

        void showProgress(boolean show);

        void disableLoginButton();

        void enableLoginButton();
    }

    interface Presenter extends MvpPresenter<View>, ErrorPresenter{

        void showLoginView();

        void handleOauthCodeReceived(@NonNull String receivedCode);

        void logInUser(String email, String password);

    }

}
