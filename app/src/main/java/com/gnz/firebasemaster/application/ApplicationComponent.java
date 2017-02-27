package com.gnz.firebasemaster.application;

import com.gnz.firebasemaster.FirebaseModule;
import com.gnz.firebasemaster.login.AuthController;
import com.gnz.firebasemaster.login.LoginComponent;
import com.gnz.firebasemaster.login.LoginModule;
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
