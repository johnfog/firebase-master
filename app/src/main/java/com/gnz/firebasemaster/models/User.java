package com.gnz.firebasemaster.models;


import com.google.firebase.database.Exclude;

public class User {

    @Exclude
    public static final String USER = "user";
    @Exclude
    public static final String CONNECTION = "connection";
    @Exclude
    public static final String ONLINE = "online";
    @Exclude
    public static final String OFFLINE = "offline";

    public String displayName;
    public String email;
    public String connection;
    public int avatarId;
    public long createdAt;

    private String mRecipientId;

    public User() {

    }

    public User(UserBuilder userBuilder) {
        displayName = userBuilder.displayName;
        email = userBuilder.email;
        connection = userBuilder.connection;
        avatarId = userBuilder.avatarId;
        createdAt = userBuilder.createdAt;
    }

    public long getCreatedAt() {
        return createdAt;
    }


    private String getUserEmail() {
        return email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getEmail() {
        return email;
    }

    public String getConnection() {
        return connection;
    }

    public int getAvatarId() {
        return avatarId;
    }

    @Exclude
    public String getRecipientId() {
        return mRecipientId;
    }

    public void setRecipientId(String recipientId) {
        this.mRecipientId = recipientId;
    }

    public static class UserBuilder {
        private final String displayName;
        private final String email;
        private String connection;
        private int avatarId;
        private long createdAt;

        private String recipientId;

        public UserBuilder(String displayName, String email) {
            this.displayName = displayName;
            this.email = email;
        }

        public UserBuilder connection(String connection) {
            this.connection = connection;
            return this;
        }

        public UserBuilder avatarId(int avatarId) {
            this.avatarId = avatarId;
            return this;
        }

        public UserBuilder createAt(long createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public UserBuilder recipientId(String recipientId) {
            this.recipientId = recipientId;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

}

