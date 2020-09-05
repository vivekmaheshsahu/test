package com.android.test.splash_screen;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.*;
import com.android.volley.Request;
import com.android.volley.toolbox.*;
import com.android.test.database.DatabaseManager;
import com.android.test.database.DbHelper;
import com.android.test.network_call.AppController;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class SplashScreenPresenter implements ISplashScreenPresenter<ISplashScreen> {

    public ISplashScreen viewSplashScreen;
    public SplashScreenInteractor splashScreenInteractor;
    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.READ_PHONE_STATE};

    @Override
    public void initializeDBHelper() {
        DbHelper dbHelper = new DbHelper(viewSplashScreen.getContext().getApplicationContext());
        DatabaseManager.initializeInstance(dbHelper);
        DatabaseManager.getInstance().openDatabase();
    }

    @Override
    public boolean checkPermissions() {
        List<String> listPermissionsNeeded = new ArrayList<>();

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(viewSplashScreen.getContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(permission);
            }
        }

        if (!listPermissionsNeeded.isEmpty()) {
            getPermissions(listPermissionsNeeded);
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void attachView(ISplashScreen view) {
        viewSplashScreen = view;
        splashScreenInteractor =new SplashScreenInteractor(viewSplashScreen.getContext(),this);
        if (checkPermissions()) {
            initializeDBHelper();
        }
    }

    @Override
    public void detachView() {
    viewSplashScreen = null;
    }

    @Override
    public void getPermissions(List listPermissionsNeeded) {
        ActivityCompat.requestPermissions((Activity) viewSplashScreen.getContext(), (String[]) listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 1);
    }

    @Override
    public void networkCall() {
        // Tag used to cancel the request
        String tag_json_obj = "json_obj_req";

        String url = "https://cricket.yahoo.net/sifeeds/cricket/live/json/sapk01222019186652.json";

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        splashScreenInteractor.storeData(response);
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                // hide the progress dialog
            }
        });
// Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
    }

    @Override
    public void checkIfUserAlreadyLoggedIn() {
        try {
            if (splashScreenInteractor.userAlreadyLoggedIn()) {
                viewSplashScreen.openHomeActivity();
            }
        } catch (IllegalStateException e) {
            initializeDBHelper();
            networkCall();
        }
    }
}
