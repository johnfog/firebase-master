package com.gnz.firebasemaster.chat.main;


import android.util.Log;

import com.gnz.firebasemaster.common.mvp.RxPresenter;
import com.gnz.firebasemaster.auth.AuthController;
import com.gnz.firebasemaster.remotedatabase.RemoteDatabaseController;
import com.gnz.firebasemaster.models.User;
import com.google.firebase.database.DataSnapshot;
import com.kelvinapps.rxfirebase.RxFirebaseChildEvent;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscription;

public class ChatPresenter extends RxPresenter<ChatContract.View> implements ChatContract.Presenter {

    private final static int LIMIT = 50;

    private final AuthController authController;
    private final RemoteDatabaseController remoteDatabaseController;
    private Subscription authStateSubscription;
    private Map<String, Integer> usersKeyMap;

    private String currentUserUid;

    @Inject
    ChatPresenter(AuthController authController, RemoteDatabaseController remoteDatabaseController) {
        this.authController = authController;
        this.remoteDatabaseController = remoteDatabaseController;
        usersKeyMap = new HashMap<>();
    }

    @Override
    public void attachView(ChatContract.View view) {
        super.attachView(view);
    }


    private void listenAuthState() {
        authStateSubscription =
                authController.observeAuthState()
                        .doOnSubscribe(() -> getView().showProgress(true))
                        .subscribe(firebaseUser -> {
                            currentUserUid = firebaseUser.getUid();
                            listenToDatabase();
                            queryUsers();
                            getView().showProgress(false);
                        }, throwable -> {
                            Log.e("ListenAuth", ChatFragment.TAG, throwable);
                            getView().showError(throwable.getMessage());
                        });
    }

    private void listenToDatabase() {
        compositeSubscription.add(
                remoteDatabaseController.observeUsers()
                        .subscribe(dataSnapshotRxFirebaseChildEvent -> {
                            if (dataSnapshotRxFirebaseChildEvent.getValue().getKey().equals(currentUserUid)) {
                                final User currentUser = dataSnapshotRxFirebaseChildEvent.getValue().getValue(User.class);
                                getView().setCurrentUser(currentUser);
                            }
                        }, throwable -> {
                            Log.e("ListenAuth", ChatFragment.TAG, throwable);
                            getView().showError(throwable.getMessage());
                        }));
    }

    private void queryUsers() {
        compositeSubscription.add(
                remoteDatabaseController.observeChildEventUsers(LIMIT)
                        .subscribe(
                                dataSnapshotRxFirebaseChildEvent -> {
                                    final DataSnapshot dataSnapshot = dataSnapshotRxFirebaseChildEvent.getValue();
                                    if (dataSnapshot.exists()) {

                                        final String userUid = dataSnapshot.getKey();
                                        final User user = dataSnapshot.getValue(User.class);

                                        if (dataSnapshotRxFirebaseChildEvent.getEventType() == RxFirebaseChildEvent.EventType.ADDED) {
                                            if (dataSnapshot.getKey().equals(currentUserUid)) {
                                                getView().setCurrentUser(user);
                                            } else {
                                                final int index = usersKeyMap.size();
                                                user.setRecipientId(userUid);
                                                usersKeyMap.put(userUid, index);
                                                getView().addUser(user);
                                            }
                                        }

                                        if (dataSnapshotRxFirebaseChildEvent.getEventType() == RxFirebaseChildEvent.EventType.CHANGED) {
                                            if (!currentUserUid.equals(userUid)) {
                                                int index = usersKeyMap.get(userUid);
                                                if (index > -1) {
                                                    getView().changeUser(index, user);
                                                }
                                            }
                                        }
                                    }
                                },
                                throwable -> {
                                    Log.e("ListenAuth", ChatFragment.TAG, throwable);
                                    getView().showError(throwable.getMessage());
                                })
        );
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        compositeSubscription.clear();
        if (authStateSubscription != null && !authStateSubscription.isUnsubscribed()) {
            authStateSubscription.unsubscribe();
        }
    }

    @Override
    public void startListening() {
        listenAuthState();
    }
}
