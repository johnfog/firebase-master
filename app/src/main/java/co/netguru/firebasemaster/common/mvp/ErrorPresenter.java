package co.netguru.firebasemaster.common.mvp;


public interface ErrorPresenter {

    void handleError(Throwable throwable, String errorText);

}
