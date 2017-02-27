package com.gnz.firebasemaster.account;

import com.google.firebase.auth.FirebaseAuth;

import co.netguru.android.commons.di.FragmentScope;
import dagger.Module;
import dagger.Provides;

@Module
public class LoginModule {


    @FragmentScope
    @Provides
    AuthController provideAuthController(FirebaseAuth firebaseAuth) {
        return new AuthController(firebaseAuth);
    }

}
