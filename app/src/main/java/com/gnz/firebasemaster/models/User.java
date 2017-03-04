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

    public final String displayName;
    public final String email;
    public final String connection;
    public final int avatarId;
    public final long createdAt;

    private String mRecipientId;

    public User(UserBuilder userBuilder) {
        displayName = userBuilder.displayName;
        email = userBuilder.email;
        connection = userBuilder.connection;
        avatarId = userBuilder.avatarId;
        createdAt = userBuilder.createdAt;
    }

    public String createUniqueChatRef(long createdAtCurrentUser, String currentUserEmail) {
        String uniqueChatRef = "";
        if (createdAtCurrentUser > getCreatedAt()) {
            uniqueChatRef = cleanEmailAddress(currentUserEmail) + "-" + cleanEmailAddress(getUserEmail());
        } else {

            uniqueChatRef = cleanEmailAddress(getUserEmail()) + "-" + cleanEmailAddress(currentUserEmail);
        }
        return uniqueChatRef;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    private String cleanEmailAddress(String email) {
        //replace dot since firebase does not allow dot
        return email.replace(".", "-");
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

