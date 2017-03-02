package com.gnz.firebasemaster.application;

import com.gnz.firebasemaster.account.AuthController;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AuthenticationModule {

    @Singleton
    @Provides
    AuthController provideAuthController(FirebaseAuth firebaseAuth) {
        return new AuthController(firebaseAuth);
    }

}
