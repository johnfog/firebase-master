package com.gnz.firebasemaster.account.login;

import android.support.annotation.NonNull;
import android.util.Log;

import com.gnz.firebasemaster.common.mvp.RxPresenter;
import com.gnz.firebasemaster.auth.AuthController;
import com.gnz.firebasemaster.remotedatabase.RemoteDatabaseController;
import com.gnz.firebasemaster.models.User;
import com.google.firebase.auth.FirebaseUser;

import javax.inject.Inject;

import co.netguru.android.commons.di.FragmentScope;
import co.netguru.android.commons.rx.RxTransformers;

@FragmentScope
public final class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    private final AuthController authController;
    private final RemoteDatabaseController remoteDatabaseController;

    @Inject
    LoginPresenter(AuthController authController, RemoteDatabaseController remoteDatabaseController) {
        super();
        this.authController = authController;
        this.remoteDatabaseController = remoteDatabaseController;
    }

    @Override
    public void attachView(LoginContract.View view) {
        super.attachView(view);
        compositeSubscription.add(
                authController.observeAuthState()
                        .subscribe(firebaseUser ->
                        {
                            if (firebaseUser != null) {
                                getView().userIsLoggedIn();
                            }
                        })
        );
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
                                authResult -> {
                                    getView().logUserIn();
                                    setUserOnline(authResult.getUser());
                                },
                                throwable -> getView().showInvalidCredentials()));
    }

    @Override
    public void handleError(Throwable throwable, String errorText) {

    }

    private void setUserOnline(FirebaseUser user) {
        compositeSubscription.add(
                remoteDatabaseController.setUserConnection(user.getUid(), User.ONLINE)
                        .compose(RxTransformers.androidIO())
                        .subscribe(aVoid -> log(),
                                throwable -> Log.e("ERROR", throwable.getMessage())));
    }

    private void log() {

    }
}
