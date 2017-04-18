package com.gnz.firebasemaster.chat.chatmessages;


import com.gnz.firebasemaster.common.ui.BaseFragmentComponent;

import dagger.Subcomponent;

@Subcomponent
public interface MessageComponent extends BaseFragmentComponent {

    void inject(MessageFragment messageFragment);

    MessagePresenter getPresenter();

}
