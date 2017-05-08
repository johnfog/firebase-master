package co.netguru.firebasemaster.account.signup;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby.mvp.MvpPresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

public interface SignupContract {

    interface View extends MvpView {

        void showProgress(boolean progress);

        void showNameError();

        void showEmailError();

        void showPasswordError();

        void singInUser();

        void showSignUpError(String error);

    }

    interface Presenter extends MvpPresenter<View> {

        void signupUser(@NonNull String name, @NonNull String email, @NonNull String password);

    }

}
