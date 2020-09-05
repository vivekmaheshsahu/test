package com.android.test.splash_screen;

import org.json.JSONObject;

public interface ISplashScreenInteractor {

    boolean checkPermissions();
    boolean storeData(JSONObject jsonObject);
    boolean userAlreadyLoggedIn();


}
