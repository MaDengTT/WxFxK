package com.xxm.mmd.common_circle.ui.activitys;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.xxm.mmd.common_circle.R;

import ch.ielse.view.imagewatcher.ImageWatcher;

public class BigImagePreviewActivity extends AppCompatActivity implements ImageWatcher.OnPictureLongPressListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_activity_big_image_preview);
    }

    @Override
    public void onPictureLongPress(ImageView v, String url, int pos) {

    }
}
