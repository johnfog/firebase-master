package co.netguru.firebasemaster.application;

import co.netguru.firebasemaster.R;
import co.netguru.firebasemaster.analytics.FirebaseEventController;
import co.netguru.firebasemaster.auth.AuthController;
import co.netguru.firebasemaster.config.ConfigController;
import co.netguru.firebasemaster.remotedatabase.RemoteDatabaseController;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;

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

    @Singleton
    @Provides
    FirebaseEventController providesFirebaseEventController(FirebaseAnalytics firebaseAnalytics) {
        return new FirebaseEventController(firebaseAnalytics);
    }

    @Singleton
    @Provides
    ConfigController provideConfigController(FirebaseRemoteConfig firebaseRemoteConfig) {
        return new ConfigController(firebaseRemoteConfig, R.xml.remote_config_defaults);
    }

}
