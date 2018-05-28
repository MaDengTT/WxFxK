package com.xxm.mmd.component_recipe.view;

import android.content.Context;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.xxm.mmd.common.utils.SizeUtils;

/**
 * Created by Dajavu on 26/10/2017.
 */

public abstract class SettingPopUpWindow extends PopupWindow {
    public SettingPopUpWindow(Context context) {
        super(context);
        setOutsideTouchable(true);
        setWidth(SizeUtils.dp2px(320,context));
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
    }
}
