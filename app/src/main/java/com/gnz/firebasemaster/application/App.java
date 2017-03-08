package com.gnz.firebasemaster.application;

import android.app.Application;
import android.content.Context;

import com.gnz.firebasemaster.FirebaseModule;
import com.google.firebase.FirebaseApp;

public class App extends Application {

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
                .firebaseModule(new FirebaseModule())
                .remoteResourcesModule(new RemoteResourcesModule())
                .build();
    }

    private void initFirebase() {
        FirebaseApp.initializeApp(this);
    }

}
