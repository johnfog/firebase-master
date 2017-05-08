package co.netguru.firebasemaster.application;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;

public class App extends Application {

    private static final long TIME_OUT = 30;

    private ApplicationComponent appComponent;

    public static ApplicationComponent getAppComponent(Context context) {
        return ((App) context.getApplicationContext()).appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent.getDebugMetricsHelper().init(this);
        initFirebase();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);

        // init dagger appComponent
        this.appComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(base))
                .firebaseModule(new FirebaseModule(base))
                .remoteResourcesModule(new RemoteResourcesModule())
                .build();
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(this);
    }

}
