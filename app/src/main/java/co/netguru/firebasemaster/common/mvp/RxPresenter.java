package co.netguru.firebasemaster.common.mvp;

import com.hannesdorfmann.mosby.mvp.MvpNullObjectBasePresenter;
import com.hannesdorfmann.mosby.mvp.MvpView;

import rx.subscriptions.CompositeSubscription;

public abstract class RxPresenter<T extends MvpView> extends MvpNullObjectBasePresenter<T> {

    protected final CompositeSubscription compositeSubscription;

    public RxPresenter() {
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void detachView(boolean retainInstance) {
        super.detachView(retainInstance);
        compositeSubscription.clear();
    }
}
