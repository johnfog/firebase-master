package co.netguru.firebasemaster.chat.chatusers;


import co.netguru.firebasemaster.common.mvp.RxPresenter;
import co.netguru.firebasemaster.auth.AuthController;
import co.netguru.firebasemaster.remotedatabase.RemoteDatabaseController;
import co.netguru.firebasemaster.models.User;

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
