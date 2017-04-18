package com.gnz.firebasemaster.application;

import com.gnz.firebasemaster.FirebaseModule;
import com.gnz.firebasemaster.account.login.LoginComponent;
import com.gnz.firebasemaster.account.signup.SignupComponent;
import com.gnz.firebasemaster.account.signup.SignupModule;
import com.gnz.firebasemaster.analytics.FirebaseEventController;
import com.gnz.firebasemaster.chat.chatmessages.MessageComponent;
import com.gnz.firebasemaster.chat.chatmessages.MessageContract;
import com.gnz.firebasemaster.chat.chatusers.MainComponent;
import com.gnz.firebasemaster.chat.chatusers.main.ChatComponent;
import com.gnz.firebasemaster.chat.chatusers.main.ChatModule;
import com.gnz.firebasemaster.auth.AuthController;
import com.gnz.firebasemaster.config.ConfigController;
import com.gnz.firebasemaster.remotedatabase.RemoteDatabaseController;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

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

    ChatComponent plus(ChatModule chatModule);

    MainComponent mainComponent();

    MessageComponent messageComponent();

    FirebaseAuth getFirebaseAuth();

    FirebaseDatabase getFirebaseDatabase();

    FirebaseRemoteConfig getFirebaseRemoteConfig();

    DebugMetricsHelper getDebugMetricsHelper();

    AuthController getAuthController();

    RemoteDatabaseController getRemoteDatabaseController();

    FirebaseEventController getFirebaseEventController();

    ConfigController getConfigController();

}
