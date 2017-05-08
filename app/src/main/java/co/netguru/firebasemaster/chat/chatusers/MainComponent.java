package co.netguru.firebasemaster.chat.chatusers;


import dagger.Subcomponent;

@Subcomponent
public interface MainComponent {

    MainPresenter getPresenter();
}
