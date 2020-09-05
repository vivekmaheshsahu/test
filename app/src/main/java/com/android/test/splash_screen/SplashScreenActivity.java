package com.android.test.splash_screen;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.test.newModule.*;
import com.android.test.R;
import com.android.test.database.DbHelper;
import java.util.Timer;
import java.util.TimerTask;

public class SplashScreenActivity extends AppCompatActivity implements ISplashScreen {

    DbHelper db;
    private SplashScreenPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen_activity);
        presenter = new SplashScreenPresenter();
        db = new DbHelper(this);
        presenter = new SplashScreenPresenter();
        presenter.attachView(this);
        if (presenter.checkPermissions()) {
            presenter.checkIfUserAlreadyLoggedIn();
        }

    }

    @Override
    public Context getContext() {
        return this;
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (presenter.checkPermissions()) {
            presenter.checkIfUserAlreadyLoggedIn();
        }
        else
        { System.out.println("Please allow user permission to app");}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void openHomeActivity() {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }, 2000);
    }

}
