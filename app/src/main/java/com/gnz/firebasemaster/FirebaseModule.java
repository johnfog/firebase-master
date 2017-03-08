package com.gnz.firebasemaster;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseModule {

    public FirebaseModule() {
    }

    @Provides
    @Singleton
    FirebaseAuth getAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    FirebaseDatabase getRemoteDatabase() {
        return FirebaseDatabase.getInstance();
    }

}
