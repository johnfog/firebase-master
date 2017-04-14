package com.gnz.firebasemaster.chat.chatusers.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.gnz.firebasemaster.R;
import com.gnz.firebasemaster.application.App;
import com.gnz.firebasemaster.chat.chatusers.main.adapter.UserClickListener;
import com.gnz.firebasemaster.chat.chatusers.main.adapter.UsersAdapter;
import com.gnz.firebasemaster.common.ui.BaseFragmentComponent;
import com.gnz.firebasemaster.common.ui.BaseMvpFragment;
import com.gnz.firebasemaster.models.User;

import javax.inject.Inject;

import butterknife.BindView;


public class ChatFragment extends BaseMvpFragment<ChatContract.View, ChatContract.Presenter> implements ChatContract.View, UserClickListener {

    public final static String TAG = ChatFragment.class.getSimpleName();

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    @BindView(R.id.recycler_view_users)
    RecyclerView usersRecyclerView;

    @Inject
    UsersAdapter adapter;

    public static ChatFragment newInstance() {
        final ChatFragment chatFragment = new ChatFragment();
        return chatFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecycler();
        getPresenter().startListening();
    }

    private void initRecycler() {
        usersRecyclerView.setAdapter(adapter);
        usersRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        usersRecyclerView.setHasFixedSize(true);
    }

    @NonNull
    @Override
    protected BaseFragmentComponent createFragmentComponent() {
        final ChatComponent chatComponent =
                App.getAppComponent(getActivity()).plus(new ChatModule(this));
        chatComponent.inject(this);
        return chatComponent;
    }

    @Override
    public ChatContract.Presenter createPresenter() {
        return ((ChatComponent) getComponent()).getPresenter();
    }

    @Override
    public void onUserClick(User user) {
        // TODO open chat activity
    }

    @Override
    public void showProgress(boolean progress) {
        progressBar.setVisibility(progress ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setCurrentUser(User user) {
        adapter.setCurrentUser(user);
    }

    @Override
    public void addUser(User user) {
        adapter.addUser(user);
    }

    @Override
    public void changeUser(int index, @NonNull User user) {
        adapter.updateUser(index, user);
    }

    @Override
    public void showError(@NonNull String msg) {
        showTextOnSnackbar(msg);
    }

    @Override
    public void onDestroy() {
        getPresenter().detachView(false);
        super.onDestroy();
    }

}
