package com.gnz.firebasemaster.config;


import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.kelvinapps.rxfirebase.RxHandler;

import rx.Observable;

public class ConfigController {

    private final FirebaseRemoteConfig firebaseRemoteConfig;

    public ConfigController(FirebaseRemoteConfig firebaseRemoteConfig) {
        this.firebaseRemoteConfig = firebaseRemoteConfig;
    }

    public Observable<Void> fetch(final long timeout) {
        return Observable.create(subscriber -> RxHandler.assignOnTask(subscriber, firebaseRemoteConfig.fetch(timeout)));
    }

    public boolean signUpEnable() {
        return firebaseRemoteConfig.getBoolean(Consts.SIGN_UP_ENABLE);
    }

    public void activateFetch() {
        firebaseRemoteConfig.activateFetched();
    }

}
