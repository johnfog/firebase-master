package com.gnz.firebasemaster.chat.chatmessages;


import android.util.Log;

import com.gnz.firebasemaster.common.mvp.RxPresenter;
import com.gnz.firebasemaster.models.ChatMessage;
import com.gnz.firebasemaster.remotedatabase.RemoteDatabaseController;
import com.google.firebase.database.DataSnapshot;

import javax.inject.Inject;

public class MessagePresenter extends RxPresenter<MessageContract.View> implements MessageContract.Presenter {

    private final static int LIMIT = 20;

    private final RemoteDatabaseController remoteDatabaseController;

    private String chatReference;
    private String currentUserId;
    private String recipientId;

    @Inject
    public MessagePresenter(RemoteDatabaseController remoteDatabaseController) {
        this.remoteDatabaseController = remoteDatabaseController;
    }

    @Override
    public void startPresenter(String chatReference, String recipientId, String currentUserId) {
        this.chatReference = chatReference;
        this.recipientId = recipientId;
        this.currentUserId = currentUserId;
        listenToDatabase(chatReference, currentUserId);
    }


    private void listenToDatabase(String chatRef, String currentUserId) {
        compositeSubscription.add(
                remoteDatabaseController.observeChildEventChat(chatRef, LIMIT)
                        .subscribe(
                                dataSnapshotRxFirebaseChildEvent ->
                                        populateMessages(dataSnapshotRxFirebaseChildEvent.getValue(), currentUserId)
                                , throwable -> {
                                    Log.e(MessageFragment.TAG, throwable.getMessage());
                                    getView().showError(throwable.getMessage());
                                }
                        )
        );
    }

    private void populateMessages(DataSnapshot dataSnapshot, String currentUserId) {
        if (dataSnapshot.exists()) {
            final ChatMessage chatMessage = dataSnapshot.getValue(ChatMessage.class);
            if (chatMessage.getSender().equals(currentUserId)) {
                chatMessage.setRecipientOrSenderStatus(ChatMessage.SENDER);
            } else {
                chatMessage.setRecipientOrSenderStatus(ChatMessage.RECIPIENT);
            }
            getView().addChatMessage(chatMessage);
        }
    }

    @Override
    public void sendMessage(ChatMessage message) {

        compositeSubscription.add(
                remoteDatabaseController.sendMessageToUser(chatReference, message)
                        .subscribe(
                                aVoid -> {
                                    // DO NOTHING
                                },
                                throwable -> {
                                    Log.e(MessageFragment.TAG, throwable.getMessage());
                                    getView().showError(throwable.getMessage());
                                }
                        )
        );

    }
}
