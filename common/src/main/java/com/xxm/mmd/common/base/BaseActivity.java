package com.xxm.mmd.common.base;

import android.support.v7.app.AppCompatActivity;

import com.xxm.mmd.common.di.component.AppComponent;

/**
 * Created by MaDeng on 2018/4/28.
 */
public class BaseActivity extends AppCompatActivity {

    protected AppComponent getAppComponent() {
        if (getApplication() instanceof BaseApplication) {
            return ((BaseApplication) getApplication()).AppComponent();
        }else {
            return null;
        }
    }

}
