package co.netguru.firebasemaster.chat.chatmessages;


import co.netguru.firebasemaster.common.ui.BaseFragmentComponent;

import dagger.Subcomponent;

@Subcomponent
public interface MessageComponent extends BaseFragmentComponent {

    void inject(MessageFragment messageFragment);

    MessagePresenter getPresenter();

}
