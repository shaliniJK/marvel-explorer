package com.marvel_explorer.ui.base;


import io.reactivex.disposables.CompositeDisposable;

/**
 *
 * @author koodun
 */
public abstract class BasePresenter<V> {

    protected V mView;

    protected CompositeDisposable mDisposable = new CompositeDisposable();

    public void attachView(V view) {
        this.mView = view;
    }

    public void cancelSubscription() {
        mDisposable.clear();
    }

    public void detachView() {
        mDisposable.dispose();
        mView = null;
    }

}
