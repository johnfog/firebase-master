package com.gnz.firebasemaster.account.signup;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gnz.firebasemaster.R;
import com.gnz.firebasemaster.application.App;
import com.gnz.firebasemaster.chat.MainActivity;
import com.gnz.firebasemaster.common.ui.BaseFragmentComponent;
import com.gnz.firebasemaster.common.ui.BaseMvpFragment;

import butterknife.BindView;
import butterknife.OnClick;


public class SignupFragment extends BaseMvpFragment<SignupContract.View, SignupContract.Presenter> implements SignupContract.View {

    public final static String TAG = SignupFragment.class.getSimpleName();

    @BindView(R.id.name_editText)
    TextInputEditText nameEditText;

    @BindView(R.id.email_editText)
    TextInputEditText emailEditText;

    @BindView(R.id.password_editText)
    TextInputEditText passwordEditText;

    public static SignupFragment newInstance() {
        final SignupFragment signupFragment = new SignupFragment();
        return signupFragment;
    }

    @Override
    public SignupContract.Presenter createPresenter() {
        return ((SignupComponent) getComponent()).getPresenter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @OnClick(R.id.signup_button)
    void singUp() {
        getPresenter().signupUser(nameEditText.getText().toString(), emailEditText.getText().toString(), passwordEditText.getText().toString());
    }

    @NonNull
    @Override
    protected BaseFragmentComponent createFragmentComponent() {
        final SignupComponent signupComponent = App.getAppComponent(getActivity()).plus(new SignupModule());
        signupComponent.inject(this);
        return signupComponent;
    }

    @Override
    public void showProgress(boolean progress) {

    }

    @Override
    public void showNameError() {
        showTextOnSnackbar(R.string.signup_name_error);
    }

    @Override
    public void showEmailError() {
        showTextOnSnackbar(R.string.signup_email_error);
    }

    @Override
    public void showPasswordError() {
        showTextOnSnackbar(R.string.signup_password_error);
    }

    @Override
    public void singInUser() {
        MainActivity.startActivity(getActivity());
        getActivity().finish();
    }

    @Override
    public void showSignUpError(String error) {
        showTextOnSnackbar(error);
    }
}
