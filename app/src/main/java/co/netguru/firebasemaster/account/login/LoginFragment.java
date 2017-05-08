package co.netguru.firebasemaster.account.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import co.netguru.firebasemaster.account.LoginSignupActivity;
import co.netguru.firebasemaster.analytics.FirebaseEventController;
import co.netguru.firebasemaster.application.App;
import co.netguru.firebasemaster.chat.chatusers.MainActivity;
import co.netguru.firebasemaster.common.ui.BaseFragmentComponent;
import co.netguru.firebasemaster.common.ui.BaseMvpFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginFragment extends BaseMvpFragment<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {

    public static final String TAG = LoginFragment.class.getSimpleName();

    @BindView(co.netguru.firebasemaster.R.id.email_editText)
    TextInputEditText emailEditText;

    @BindView(co.netguru.firebasemaster.R.id.password_editText)
    TextInputEditText passwordEditText;

    @BindView(co.netguru.firebasemaster.R.id.login_linearLayout)
    LinearLayout loginLinearLayout;

    @BindView(co.netguru.firebasemaster.R.id.signup_button)
    Button signUpButton;

    @Inject
    FirebaseEventController firebaseEventController;

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
        return inflater.inflate(co.netguru.firebasemaster.R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getPresenter().signUpEnable();
        getPresenter().fetchConfig();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPresenter().fetchConfig();
    }

    @OnClick(co.netguru.firebasemaster.R.id.login_button)
    void loginUser() {
        getPresenter().logInUser(emailEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @OnClick(co.netguru.firebasemaster.R.id.signup_button)
    void signUp() {
        ((LoginSignupActivity) getActivity()).signUp();
    }

    @Override
    public void showInvalidCredentials() {
        showTextOnSnackbar(co.netguru.firebasemaster.R.string.error);
    }

    @Override
    public void logUserIn() {
        firebaseEventController.logEventLogin();
        MainActivity.startActivity(getActivity());
        getActivity().finish();
    }

    @Override
    public void showProgress(boolean show) {

    }

    @Override
    public void userIsLoggedIn() {
        MainActivity.startActivity(getActivity());
        getActivity().finish();
    }

    @Override
    public void hideSignUpButton(boolean hide) {
        signUpButton.setVisibility(hide ? View.GONE : View.VISIBLE);
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
