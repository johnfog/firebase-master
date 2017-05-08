package co.netguru.firebasemaster.analytics;


import co.netguru.firebasemaster.analytics.event.BaseEvent;
import co.netguru.firebasemaster.analytics.event.LoginEvent;
import co.netguru.firebasemaster.analytics.event.RegisterEvent;
import com.google.firebase.analytics.FirebaseAnalytics;

public class FirebaseEventController implements AnalyticsEventLogger {

    private final FirebaseAnalytics firebaseAnalytics;

    private static final String REGISTER_EVENT = "register";
    private static final String LOGIN_EVENT = "login";

    public FirebaseEventController(FirebaseAnalytics firebaseAnalytics) {
        this.firebaseAnalytics = firebaseAnalytics;
    }

    @Override
    public void logEventRegister() {
        logEvent(new RegisterEvent(REGISTER_EVENT));
    }

    @Override
    public void logEventLogin() {
        logEvent(new LoginEvent(LOGIN_EVENT));
    }

    private void logEvent(BaseEvent event) {
        event.logEvent(firebaseAnalytics);
    }
}
