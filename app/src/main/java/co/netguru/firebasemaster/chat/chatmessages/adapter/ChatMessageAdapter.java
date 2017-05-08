package co.netguru.firebasemaster.chat.chatmessages.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import co.netguru.firebasemaster.R;

import co.netguru.firebasemaster.models.ChatMessage;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.MessageViewHolder> {

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
        notifyDataSetChanged();
        //notifyItemInserted(getItemCount() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getRecipientOrSenderStatus();
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

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
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        ChatMessage chatMessage = items.get(position);
        holder.getMessageTextView().setText(chatMessage.getMessage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    /*==============ViewHolder===========*/

    /*ViewHolder for Sender*/

    public abstract class MessageViewHolder extends RecyclerView.ViewHolder {

        public MessageViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public abstract TextView getMessageTextView();
    }

    public class ViewHolderSender extends MessageViewHolder {

        @BindView(co.netguru.firebasemaster.R.id.text_view_sender_message)
        TextView mSenderMessageTextView;

        public ViewHolderSender(View itemView) {
            super(itemView);
        }

        public TextView getMessageTextView() {
            return mSenderMessageTextView;
        }

    }


    /*ViewHolder for Recipient*/
    public class ViewHolderRecipient extends MessageViewHolder {

        @BindView(co.netguru.firebasemaster.R.id.text_view_recipient_message)
        TextView mRecipientMessageTextView;

        public ViewHolderRecipient(View itemView) {
            super(itemView);
        }

        public TextView getMessageTextView() {
            return mRecipientMessageTextView;
        }

    }
}
