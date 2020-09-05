package com.android.test.utility;

public interface IBasePresenter<V> {

    void attachView(V view);

    void detachView();

}
