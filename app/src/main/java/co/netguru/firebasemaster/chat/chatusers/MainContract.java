package co.netguru.firebasemaster.chat.chatusers;


import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

public interface MainContract {

    interface View extends MvpView {

        void logOut();

        void showError(@NonNull String msg);

    }

    interface Presenter extends MvpPresenter<View> {

        void logOut();
    }

}
