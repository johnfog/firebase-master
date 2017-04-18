package com.gnz.firebasemaster.chat.chatmessages;


import android.support.annotation.NonNull;

import com.gnz.firebasemaster.models.ChatMessage;
import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

public interface MessageContract {

    interface View extends MvpView {

        void addChatMessage(ChatMessage chatMessage);

        void showError(@NonNull String msg);

    }

    interface Presenter extends MvpPresenter<View>, MvpView {

        void startPresenter(String chatReference, String recipientId, String currentUserId);

        void sendMessage(ChatMessage message);

    }
}
