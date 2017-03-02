package com.gnz.firebasemaster.account.signup;


import com.gnz.firebasemaster.common.ui.BaseFragmentComponent;

import co.netguru.android.commons.di.FragmentScope;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = SignupModule.class)
public interface SignupComponent extends BaseFragmentComponent {

    void inject(SignupFragment signupFragment);

    SignupPresenter getPresenter();

}
