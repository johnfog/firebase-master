package com.gnz.firebasemaster.account;


import com.gnz.firebasemaster.common.ui.BaseFragmentComponent;

import co.netguru.android.commons.di.FragmentScope;
import dagger.Subcomponent;

@FragmentScope
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent extends BaseFragmentComponent {

    void inject(LoginFragment loginFragment);

    LoginPresenter getLoginPresenter();

    AuthController getAuthController();

}
