package com.gnz.firebasemaster.application;

import com.gnz.firebasemaster.FirebaseModule;
import com.gnz.firebasemaster.account.login.LoginComponent;
import com.gnz.firebasemaster.account.signup.SignupComponent;
import com.gnz.firebasemaster.account.signup.SignupModule;
import com.gnz.firebasemaster.controllers.AuthController;
import com.gnz.firebasemaster.controllers.RemoteDatabaseController;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                ApplicationModule.class,
                FirebaseModule.class,
                RemoteResourcesModule.class
        }
)
public interface ApplicationComponent {

    LoginComponent loginComponent();

    SignupComponent plus(SignupModule signupModule);

    FirebaseAuth getFirebaseAuth();

    FirebaseDatabase getFirebaseDatabase();

    DebugMetricsHelper getDebugMetricsHelper();

    AuthController getAuthController();

    RemoteDatabaseController getRemoteDatabaseController();

}
