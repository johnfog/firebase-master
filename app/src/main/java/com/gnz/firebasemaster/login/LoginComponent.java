package com.gnz.firebasemaster.login;


import com.gnz.firebasemaster.common.ui.BaseActivityComponent;

import co.netguru.android.commons.di.ActivityScope;
import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = LoginModule.class)
public interface LoginComponent extends BaseActivityComponent {

    void inject(LoginActivity loginActivity);

    LoginPresenter getLoginPresenter();

    AuthController getAuthController();

}
