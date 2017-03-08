package com.gnz.firebasemaster.chat;


import dagger.Subcomponent;

@Subcomponent
public interface MainComponent {

    MainPresenter getPresenter();
}
