package com.gnz.firebasemaster.chat;


import com.gnz.firebasemaster.common.mvp.RxPresenter;
import com.gnz.firebasemaster.auth.AuthController;
import com.gnz.firebasemaster.remotedatabase.RemoteDatabaseController;
import com.gnz.firebasemaster.models.User;

import javax.inject.Inject;

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    private final AuthController authController;
    private final RemoteDatabaseController remoteDatabaseController;

    @Inject
    MainPresenter(AuthController authController, RemoteDatabaseController remoteDatabaseController) {
        this.authController = authController;
        this.remoteDatabaseController = remoteDatabaseController;
    }


    @Override
    public void logOut() {
        compositeSubscription.add(
                remoteDatabaseController.setUserConnection(authController.getCurrentUser().getUid(), User.OFFLINE)
                        .subscribe(
                                aVoid -> {
                                    authController.signOut();
                                    getView().logOut();
                                },
                                throwable -> getView().showError(throwable.getMessage()))
        );
    }
}
