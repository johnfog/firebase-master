package com.gnz.firebasemaster.application;

import com.gnz.firebasemaster.controllers.AuthController;
import com.gnz.firebasemaster.controllers.RemoteDatabaseController;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RemoteResourcesModule {

    @Singleton
    @Provides
    AuthController provideAuthController(FirebaseAuth firebaseAuth) {
        return new AuthController(firebaseAuth);
    }

    @Singleton
    @Provides
    RemoteDatabaseController provideRemoteDatabaseController(FirebaseDatabase firebaseDatabase) {
        return new RemoteDatabaseController(firebaseDatabase);
    }

}
