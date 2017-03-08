package com.gnz.firebasemaster.chat.main;


import dagger.Module;
import dagger.Provides;

@Module
public class ChatModule {

    private final ChatFragment chatFragment;

    public ChatModule(ChatFragment chatFragment) {
        this.chatFragment = chatFragment;
    }

    @Provides
    UserClickListener provideUserClickListener() {
        return chatFragment;
    }

}
