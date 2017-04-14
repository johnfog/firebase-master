package com.gnz.firebasemaster.chat.chatusers;


import dagger.Subcomponent;

@Subcomponent
public interface MainComponent {

    MainPresenter getPresenter();
}
