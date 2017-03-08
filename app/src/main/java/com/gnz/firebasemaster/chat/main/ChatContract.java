package com.gnz.firebasemaster.chat.main;


import android.support.annotation.NonNull;

import com.gnz.firebasemaster.models.User;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

public interface ChatContract {

    interface View extends MvpView {

        void showProgress(boolean progress);

        void setCurrentUser(@NonNull User user);

        void addUser(@NonNull User user);

        void changeUser(int index, @NonNull User user);

        void showError(@NonNull String msg);

    }

    interface Presenter extends MvpPresenter<View> {

        void startListening();

    }
}
