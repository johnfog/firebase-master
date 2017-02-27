package com.gnz.firebasemaster;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseModule {

    private final Context context;

    public FirebaseModule(Context context) {
        this.context = context;
        FirebaseApp.initializeApp(context);
    }

    @Provides
    @Singleton
    FirebaseAuth getAuth() {
        return FirebaseAuth.getInstance();
    }

}
