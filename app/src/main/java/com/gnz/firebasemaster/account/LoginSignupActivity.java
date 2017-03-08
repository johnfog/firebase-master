package com.gnz.firebasemaster.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.gnz.firebasemaster.R;
import com.gnz.firebasemaster.account.login.LoginFragment;
import com.gnz.firebasemaster.account.signup.SignupFragment;
import com.gnz.firebasemaster.common.ui.BaseActivity;

public class LoginSignupActivity extends BaseActivity {

    public static void startActivity(Context context) {
        final Intent intent = new Intent(context, LoginSignupActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.empty_activity);
        if (savedInstanceState == null) {
            replaceFragment(R.id.fragment_container, LoginFragment.newInstance(), LoginFragment.TAG).commit();
        }
    }

    public void signUp() {
        replaceFragmentAddBackStack(R.id.fragment_container, SignupFragment.newInstance(), SignupFragment.TAG).commit();
    }
}
