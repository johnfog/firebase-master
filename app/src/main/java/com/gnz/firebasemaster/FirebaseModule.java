package com.gnz.firebasemaster;

import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FirebaseModule {

    private final Context context;

    public FirebaseModule(Context context) {
        this.context = context;
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

    @Provides
    @Singleton
    FirebaseAnalytics getFirebaseAnalytics() {
        return FirebaseAnalytics.getInstance(context);
    }

}
