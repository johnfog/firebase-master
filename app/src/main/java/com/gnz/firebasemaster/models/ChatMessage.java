package com.gnz.firebasemaster.models;


public interface ChatMessage {

    String getMessage();

    void setRecipientOrSenderStatus(int recipientOrSenderStatus);

    String getRecipient();

    String getSender();

    int getRecipientOrSenderStatus();
}
