package co.netguru.firebasemaster.chat.chatusers.main;


import co.netguru.firebasemaster.chat.chatusers.main.adapter.UserClickListener;

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
