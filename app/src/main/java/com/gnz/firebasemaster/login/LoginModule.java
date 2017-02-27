package com.gnz.firebasemaster.login;

import com.google.firebase.auth.FirebaseAuth;

import co.netguru.android.commons.di.ActivityScope;
import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {


    @ActivityScope
    @Provides
    AuthController provideAuthController(FirebaseAuth firebaseAuth) {
        return new AuthController(firebaseAuth);
    }

}
