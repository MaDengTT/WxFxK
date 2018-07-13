package com.xxm.mmd.component_accounts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xxm.mmd.router.RouterConstants;

@Route(path = RouterConstants.PATH_accounts_login)
public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.accounts_activity_login);
    }

}
