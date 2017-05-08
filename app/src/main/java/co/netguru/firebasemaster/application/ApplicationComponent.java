package co.netguru.firebasemaster.application;

import co.netguru.firebasemaster.account.login.LoginComponent;
import co.netguru.firebasemaster.account.signup.SignupComponent;
import co.netguru.firebasemaster.account.signup.SignupModule;
import co.netguru.firebasemaster.analytics.FirebaseEventController;
import co.netguru.firebasemaster.chat.chatmessages.MessageComponent;
import co.netguru.firebasemaster.chat.chatusers.MainComponent;
import co.netguru.firebasemaster.chat.chatusers.main.ChatComponent;
import co.netguru.firebasemaster.chat.chatusers.main.ChatModule;
import co.netguru.firebasemaster.auth.AuthController;
import co.netguru.firebasemaster.config.ConfigController;
import co.netguru.firebasemaster.remotedatabase.RemoteDatabaseController;
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
