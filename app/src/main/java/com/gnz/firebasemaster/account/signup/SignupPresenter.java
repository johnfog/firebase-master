package com.gnz.firebasemaster.account.signup;

import android.support.annotation.NonNull;
import android.util.Log;

import com.gnz.firebasemaster.common.mvp.RxPresenter;
import com.gnz.firebasemaster.controllers.AuthController;
import com.gnz.firebasemaster.controllers.RemoteDatabaseController;
import com.gnz.firebasemaster.models.User;
import com.gnz.firebasemaster.utils.DataValidator;

import java.util.Date;

import javax.inject.Inject;

public class SignupPresenter extends RxPresenter<SignupContract.View> implements SignupContract.Presenter {

    private final AuthController authController;
    private final RemoteDatabaseController remoteDatabaseController;
    private final DataValidator dataValidator;

    @Inject
    public SignupPresenter(AuthController authController,
                           RemoteDatabaseController remoteDatabaseController,
                           DataValidator dataValidator) {
        this.authController = authController;
        this.remoteDatabaseController = remoteDatabaseController;
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
                        authResult -> onAuthSuccessful(authResult.getUser().getUid(), email, name),
                        throwable -> getView().showSignUpError(throwable.getMessage()))
        );


    }

    private void onAuthSuccessful(@NonNull String id, @NonNull String email, @NonNull String name) {
        compositeSubscription.add(remoteDatabaseController
                .createNewUser(id, createUserModel(email, name))
                .subscribe(aVoid -> getView().singInUser(),
                        throwable -> Log.e("ERROR", throwable.getMessage())
                ));
    }

    // TODO change avatar
    private User createUserModel(String email, String name) {
        return new User.UserBuilder(name, email)
                .connection(User.ONLINE)
                .avatarId(12)
                .createAt(new Date().getTime())
                .build();
    }
}
