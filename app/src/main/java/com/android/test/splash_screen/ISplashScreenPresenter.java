package com.android.test.splash_screen;

import com.android.test.utility.*;

import java.util.List;

public interface ISplashScreenPresenter<v> extends IBasePresenter<v>  {

    boolean checkPermissions();

    void getPermissions(List<String> listPermissionsNeeded);

    void initializeDBHelper();

    void networkCall();
    void checkIfUserAlreadyLoggedIn();
}
