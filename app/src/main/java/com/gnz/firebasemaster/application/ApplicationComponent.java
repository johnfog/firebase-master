package com.gnz.firebasemaster.application;

import com.gnz.firebasemaster.FirebaseModule;
import com.gnz.firebasemaster.account.LoginComponent;
import com.gnz.firebasemaster.account.LoginModule;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                FirebaseModule.class
        }
)
public interface ApplicationComponent {

    LoginComponent plus(LoginModule loginModule);

    FirebaseAuth getFirebaseAuth();

    DebugMetricsHelper getDebugMetricsHelper();

}
