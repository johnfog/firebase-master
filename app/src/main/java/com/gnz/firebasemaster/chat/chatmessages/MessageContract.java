package com.gnz.firebasemaster.chat.chatmessages;


import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

public interface MessageContract {

    interface View extends MvpView {

    }

    interface Presenter extends MvpPresenter<View> {

    }
}
