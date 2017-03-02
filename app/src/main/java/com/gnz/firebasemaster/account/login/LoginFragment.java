package com.gnz.firebasemaster.account.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.gnz.firebasemaster.R;
import com.gnz.firebasemaster.account.LoginSignupActivity;
import com.gnz.firebasemaster.application.App;
import com.gnz.firebasemaster.common.ui.BaseFragmentComponent;
import com.gnz.firebasemaster.common.ui.BaseMvpFragment;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginFragment extends BaseMvpFragment<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

    public static final String TAG = LoginFragment.class.getSimpleName();

    @BindView(R.id.email_editText)
    TextInputEditText emailEditText;

    @BindView(R.id.password_editText)
    TextInputEditText passwordEditText;

    @BindView(R.id.login_linearLayout)
    LinearLayout loginLinearLayout;

    @Override
    public LoginContract.Presenter createPresenter() {
        return ((LoginComponent) getComponent()).getLoginPresenter();
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @OnClick(R.id.login_button)
    void loginUser() {
        getPresenter().logInUser(emailEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @OnClick(R.id.signup_button)
    void signUp() {
        ((LoginSignupActivity) getActivity()).signUp();
    }

    @Override
    public void showInvalidCredentials() {
        showTextOnSnackbar(R.string.error);
    }

    @Override
    public void logUserIn() {
        showTextOnSnackbar(R.string.login);
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void disableLoginButton() {

    }

    @Override
    public void enableLoginButton() {

    }

    @Override
    public void showMessageOnServerError(String errorText) {
    }

    @NonNull
    @Override
    protected BaseFragmentComponent createFragmentComponent() {
        final LoginComponent loginComponent =
                App.getAppComponent(getActivity()).loginComponent();
        loginComponent.inject(this);
        return loginComponent;
    }
}
