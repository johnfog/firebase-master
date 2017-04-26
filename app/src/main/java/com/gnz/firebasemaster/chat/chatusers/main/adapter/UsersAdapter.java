package com.gnz.firebasemaster.chat.chatusers.main.adapter;


import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gnz.firebasemaster.R;
import com.gnz.firebasemaster.models.User;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserViewHolder> {

    private List<User> items;
    private User currentUser;
    private UserClickListener listener;

    @Inject
    public UsersAdapter(@NonNull UserClickListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    public List<User> getData() {
        return items;
    }

    public void setData(List<User> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public void addMoreItems(@NonNull List<User> moreItems) {
        final int currentSize = this.items.size();
        this.items.addAll(items);
        notifyItemRangeChanged(currentSize - 1, items.size());
    }

    public void updateUser(@NonNull User user) {
        final int position = findPositionUser(user);
        items.set(position, user);
        notifyItemChanged(position);
    }

    public void updateUser(int index, @NonNull User user) {
        items.set(index, user);
        notifyDataSetChanged();
    }

    public void addUser(@NonNull User user) {
        items.add(user);
        notifyDataSetChanged();
    }

    public void setCurrentUser(@NonNull User user) {
        currentUser = user;
    }

    private int findPositionUser(@NonNull User userToFind) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getEmail().equals(userToFind.getEmail())) {
                return i;
            }
        }
        throw new IllegalArgumentException("There is no user with email :" + userToFind.getEmail());
    }

    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_view, parent, false);
        return new UserViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(UserViewHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        } else {
            return 0;
        }
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.avatar_img)
        ImageView avatarImageView;

        @BindView(R.id.display_name_textView)
        TextView displayNameTextView;

        @BindView(R.id.connectin_status_textView)
        TextView connectionStatusTextView;

        private User user;

        public UserViewHolder(View itemView, UserClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(User user) {
            this.user = user;
            displayNameTextView.setText(user.getDisplayName());
            connectionStatusTextView.setTextColor(user.getConnection().equals(User.ONLINE) ?
                    ContextCompat.getColor(itemView.getContext(), R.color.online_status) :
                    ContextCompat.getColor(itemView.getContext(), R.color.offline_status));
        }

        @OnClick(R.id.user_view_layout)
        void onUserClick() {
            listener.onUserClick(user);
        }
    }

}
