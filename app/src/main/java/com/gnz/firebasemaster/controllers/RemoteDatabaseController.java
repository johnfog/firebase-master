package com.gnz.firebasemaster.controllers;


import android.support.annotation.NonNull;

import com.gnz.firebasemaster.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.kelvinapps.rxfirebase.RxFirebaseChildEvent;
import com.kelvinapps.rxfirebase.RxFirebaseDatabase;
import com.kelvinapps.rxfirebase.RxHandler;

import rx.Observable;

public class RemoteDatabaseController {

    private final FirebaseDatabase firebaseDatabase;

    public RemoteDatabaseController(FirebaseDatabase firebaseDatabase) {
        this.firebaseDatabase = firebaseDatabase;
    }

    public Observable<Void> createNewUser(@NonNull String userId, @NonNull User user) {
        DatabaseReference databaseReference = firebaseDatabase.getReference()
                .child(User.USER)
                .child(userId);
        return Observable.create(subscriber -> RxHandler.assignOnTask(subscriber, databaseReference.setValue(user)));
    }

    public Observable<Void> setUserConnection(@NonNull String userId, @NonNull String connection) {
        DatabaseReference databaseReference = firebaseDatabase.getReference()
                .child(User.USER)
                .child(userId)
                .child(User.CONNECTION);
        return Observable.create(subscriber -> RxHandler.assignOnTask(subscriber, databaseReference.setValue(connection)));
    }

    public Observable<RxFirebaseChildEvent<DataSnapshot>> observeUsers() {
        DatabaseReference databaseReference = firebaseDatabase.getReference()
                .child(User.USER);
        return RxFirebaseDatabase.observeChildEvent(databaseReference);
    }

    public Observable<RxFirebaseChildEvent<DataSnapshot>> observeChildEventUsers(int limit) {
        Query query = firebaseDatabase.getReference()
                .child(User.USER)
                .limitToFirst(limit);
        return RxFirebaseDatabase.observeChildEvent(query);
    }
}
