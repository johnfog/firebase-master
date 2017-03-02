package com.gnz.firebasemaster.account.login;

import android.support.annotation.NonNull;

import com.gnz.firebasemaster.account.AuthController;
import com.gnz.firebasemaster.common.mvp.RxPresenter;

import javax.inject.Inject;

import co.netguru.android.commons.di.FragmentScope;

@FragmentScope
public final class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    private AuthController authController;

    @Inject
    LoginPresenter(AuthController authController) {
        super();
        this.authController = authController;
    }

    @Override
    public void showLoginView() {

    }

    @Override
    public void handleOauthCodeReceived(@NonNull String receivedCode) {

    }

    @Override
    public void logInUser(@NonNull String email, @NonNull String password) {
        compositeSubscription.add(
                authController.logInUser(email, password)
                        .doOnSubscribe(() -> getView().showProgress(true))
                        .doOnTerminate(() -> getView().showProgress(false))
                        .subscribe(
                                authResult -> getView().logUserIn(),
                                throwable -> getView().showInvalidCredentials()));
    }

    @Override
    public void handleError(Throwable throwable, String errorText) {

    }
}
