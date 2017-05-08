package co.netguru.firebasemaster.analytics.event;


import com.google.firebase.analytics.FirebaseAnalytics;

public class LoginEvent extends ContentEvent {

    public LoginEvent(String method) {
        super(FirebaseAnalytics.Event.LOGIN, method);
    }
}