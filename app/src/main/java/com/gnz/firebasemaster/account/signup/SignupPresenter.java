package com.gnz.firebasemaster.account.signup;

import android.support.annotation.NonNull;

import com.gnz.firebasemaster.account.AuthController;
import com.gnz.firebasemaster.common.mvp.RxPresenter;
import com.gnz.firebasemaster.utils.DataValidator;

import javax.inject.Inject;

public class SignupPresenter extends RxPresenter<SignupContract.View> implements SignupContract.Presenter {

    private final AuthController authController;
    private final DataValidator dataValidator;

    @Inject
    public SignupPresenter(AuthController authController, DataValidator dataValidator) {
        this.authController = authController;
        this.dataValidator = dataValidator;
    }

    @Override
    public void signupUser(@NonNull String name, @NonNull String email, @NonNull String password) {

        if (dataValidator.isEmpty(name)) {
            getView().showNameError();
            return;
        }

        if (!dataValidator.isEmailValid(email)) {
            getView().showEmailError();
            return;
        }

        if (!dataValidator.isPassLengthValid(password)) {
            getView().showPasswordError();
            return;
        }

        compositeSubscription.add(authController.createUser(email, password)
                .doOnSubscribe(() -> getView().showProgress(true))
                .doOnTerminate(() -> getView().showProgress(false))
                .subscribe(
                        authResult -> getView().singInUser(),
                        throwable -> getView().showSignUpError(throwable.getMessage()))
        );


    }
}
