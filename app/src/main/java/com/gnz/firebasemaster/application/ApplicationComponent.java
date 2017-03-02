package com.gnz.firebasemaster.application;

import com.gnz.firebasemaster.FirebaseModule;
import com.gnz.firebasemaster.account.AuthController;
import com.gnz.firebasemaster.account.login.LoginComponent;
import com.gnz.firebasemaster.account.signup.SignupComponent;
import com.gnz.firebasemaster.account.signup.SignupModule;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                FirebaseModule.class,
                AuthenticationModule.class
        }
)
public interface ApplicationComponent {

    LoginComponent loginComponent();

    SignupComponent plus(SignupModule signupModule);

    FirebaseAuth getFirebaseAuth();

    DebugMetricsHelper getDebugMetricsHelper();

    AuthController getAuthController();

}
