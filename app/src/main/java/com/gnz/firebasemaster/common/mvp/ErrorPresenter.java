package com.gnz.firebasemaster.common.mvp;


public interface ErrorPresenter {

    void handleError(Throwable throwable, String errorText);

}
