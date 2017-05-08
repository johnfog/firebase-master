package co.netguru.firebasemaster.chat.chatusers.main;


import co.netguru.firebasemaster.common.ui.BaseFragmentComponent;

import dagger.Subcomponent;

@Subcomponent(modules = ChatModule.class)
public interface ChatComponent extends BaseFragmentComponent {

    void inject(ChatFragment chatFragment);

    ChatPresenter getPresenter();

}
