package com.gnz.firebasemaster.chat.chatusers.main;


import com.gnz.firebasemaster.common.ui.BaseFragmentComponent;

import dagger.Subcomponent;

@Subcomponent(modules = ChatModule.class)
public interface ChatComponent extends BaseFragmentComponent {

    void inject(ChatFragment chatFragment);

    ChatPresenter getPresenter();

}
