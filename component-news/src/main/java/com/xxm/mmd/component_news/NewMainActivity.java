package com.xxm.mmd.component_news;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xxm.mmd.common.base.BaseActivity;
import com.xxm.mmd.router.RouterConstants;

@Route(path = RouterConstants.PATH_NEW_MAIN)
public class NewMainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity_new_main);


    }
}
