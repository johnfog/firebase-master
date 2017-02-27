package com.gnz.firebasemaster.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.widget.LinearLayout;

import com.gnz.firebasemaster.R;
import com.gnz.firebasemaster.application.App;
import com.gnz.firebasemaster.common.ui.BaseActivity;
import com.gnz.firebasemaster.common.ui.BaseActivityComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.email_editText)
    TextInputEditText emailEditText;

    @BindView(R.id.password_editText)
    TextInputEditText passwordEditText;

    @BindView(R.id.login_linearLayout)
    LinearLayout loginLinearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    protected BaseActivityComponent createActivityComponent() {
        final LoginComponent loginComponent = App.getAppComponent(this).plus(new LoginModule());
        loginComponent.inject(this);
        return loginComponent;
    }

    @OnClick(R.id.login_button)
    void loginUser() {
        getPresenter().logInUser(emailEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @NonNull
    @Override
    public LoginContract.Presenter createPresenter() {
        return ((LoginComponent) getComponent()).getLoginPresenter();
    }

    @Override
    public void showInvalidCredentials() {

    }

    @Override
    public void logUserIn() {
        Snackbar.make(loginLinearLayout, "Log in", Snackbar.LENGTH_LONG).show();
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
}
