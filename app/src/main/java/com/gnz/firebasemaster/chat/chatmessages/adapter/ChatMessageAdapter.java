package com.gnz.firebasemaster.chat.chatmessages.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gnz.firebasemaster.R;
import com.gnz.firebasemaster.models.ChatMessage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatMessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChatMessage> items;

    @Inject
    public ChatMessageAdapter() {
        items = new ArrayList<>();
    }

    public List<ChatMessage> getData() {
        return items;
    }

    public void setData(List<ChatMessage> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addMoreItems(@NonNull List<ChatMessage> moreItems) {
        final int currentSize = this.items.size();
        this.items.addAll(items);
        notifyItemRangeChanged(currentSize - 1, items.size());
    }

    public void refillAdapter(ChatMessage chatMessage) {
        items.add(chatMessage);
        notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == ChatMessage.RECIPIENT) {
            View viewRecipient = inflater.inflate(R.layout.layout_recipient_message, parent, false);
            return new ViewHolderRecipient(viewRecipient);
        } else {
            View viewSender = inflater.inflate(R.layout.layout_sender_message, parent, false);
            return new ViewHolderSender(viewSender);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case ChatMessage.SENDER:
                ViewHolderSender viewHolderSender = (ViewHolderSender) holder;
                configureSenderView(viewHolderSender, position);
                break;
            case ChatMessage.RECIPIENT:
                ViewHolderRecipient viewHolderRecipient = (ViewHolderRecipient) holder;
                configureRecipientView(viewHolderRecipient, position);
                break;
        }
    }

    private void configureSenderView(ViewHolderSender viewHolderSender, int position) {
        ChatMessage senderFireMessage = items.get(position);
        viewHolderSender.getSenderMessageTextView().setText(senderFireMessage.getMessage());
    }

    private void configureRecipientView(ViewHolderRecipient viewHolderRecipient, int position) {
        ChatMessage recipientFireMessage = items.get(position);
        viewHolderRecipient.getRecipientMessageTextView().setText(recipientFireMessage.getMessage());
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    /*==============ViewHolder===========*/

    /*ViewHolder for Sender*/

    public class ViewHolderSender extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_sender_message)
        TextView mSenderMessageTextView;

        public ViewHolderSender(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public TextView getSenderMessageTextView() {
            return mSenderMessageTextView;
        }

    }


    /*ViewHolder for Recipient*/
    public class ViewHolderRecipient extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_recipient_message)
        TextView mRecipientMessageTextView;

        public ViewHolderRecipient(View itemView) {
            super(itemView);

        }

        public TextView getRecipientMessageTextView() {
            return mRecipientMessageTextView;
        }

    }
}
