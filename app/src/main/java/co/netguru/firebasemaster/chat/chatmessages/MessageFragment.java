package co.netguru.firebasemaster.chat.chatmessages;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import co.netguru.firebasemaster.R;
import co.netguru.firebasemaster.application.App;
import co.netguru.firebasemaster.chat.chatmessages.adapter.ChatMessageAdapter;
import co.netguru.firebasemaster.common.ui.BaseFragmentComponent;
import co.netguru.firebasemaster.common.ui.BaseMvpFragment;
import co.netguru.firebasemaster.models.ChatMessage;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;


public class MessageFragment extends BaseMvpFragment<MessageContract.View, MessageContract.Presenter> implements MessageContract.View {

    public final static String TAG = MessageFragment.class.getSimpleName();

    @BindView(R.id.chat_recycler_view)
    RecyclerView chatRecyclerView;

    @BindView(R.id.message_editText)
    EditText messageEditText;

    @BindView(R.id.send_message_button)
    Button sendMessageButton;

    @BindView(R.id.progress_for_chat)
    ProgressBar progressBarChat;

    @Inject
    ChatMessageAdapter adapter;

    private String recipientId;
    private String currentUserId;
    private String chatReference;

    public static MessageFragment newInstance(String recipientId, String currentUserId, String chatReference) {
        final MessageFragment messageFragment = new MessageFragment();
        messageFragment.recipientId = recipientId;
        messageFragment.currentUserId = currentUserId;
        messageFragment.chatReference = chatReference;
        return messageFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    private void initRecyclerView() {
        chatRecyclerView.setAdapter(adapter);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chatRecyclerView.setHasFixedSize(true);
        getPresenter().startPresenter(chatReference, recipientId, currentUserId);

    }

    @NonNull
    @Override
    protected BaseFragmentComponent createFragmentComponent() {
        final MessageComponent messageComponent =
                App.getAppComponent(getContext()).messageComponent();
        messageComponent.inject(this);
        return messageComponent;
    }

    @Override
    public MessageContract.Presenter createPresenter() {
        return ((MessageComponent) getComponent()).getPresenter();
    }

    @Override
    public void addChatMessage(ChatMessage chatMessage) {
        adapter.refillAdapter(chatMessage);
        chatRecyclerView.scrollToPosition(adapter.getItemCount() - 1);
    }

    @Override
    public void showError(@NonNull String msg) {
        showTextOnSnackbar(msg);
    }

    @OnTextChanged(R.id.message_editText)
    void onStartWriting(CharSequence message) {
        sendMessageButton.setEnabled(message.length() > 0);
    }

    @OnClick(R.id.send_message_button)
    void sendMessage() {
        String senderMessage = messageEditText.getText().toString().trim();
        if (!senderMessage.isEmpty()) {
            final ChatMessage message = new ChatMessage(senderMessage, currentUserId, recipientId);
            getPresenter().sendMessage(message);
            messageEditText.setText("");
        }
    }
}
