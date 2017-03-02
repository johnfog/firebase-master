package com.gnz.firebasemaster.account;

import android.support.annotation.NonNull;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kelvinapps.rxfirebase.RxFirebaseAuth;

import rx.Observable;

public class AuthController {

    private final FirebaseAuth firebaseAuth;

    public AuthController(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public Observable<AuthResult> logInUser(@NonNull String email, @NonNull String password) {
        return RxFirebaseAuth.signInWithEmailAndPassword(firebaseAuth, email, password);
    }

    public Observable<AuthResult> createUser(@NonNull String email, @NonNull String password) {
        return RxFirebaseAuth.createUserWithEmailAndPassword(firebaseAuth, email, password);
    }

    public FirebaseUser getCurrentUser() {
        return firebaseAuth.getCurrentUser();
    }

}
