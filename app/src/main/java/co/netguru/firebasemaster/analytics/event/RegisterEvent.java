package co.netguru.firebasemaster.analytics.event;


import com.google.firebase.analytics.FirebaseAnalytics;

public class RegisterEvent extends ContentEvent {

    public RegisterEvent(String method) {
        super(FirebaseAnalytics.Event.SIGN_UP, method);
    }
}
