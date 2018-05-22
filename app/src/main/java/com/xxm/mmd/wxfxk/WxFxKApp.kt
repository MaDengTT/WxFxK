package com.xxm.mmd.wxfxk

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.xxm.mmd.common.base.BaseApplication
import com.xxm.mmd.common.di.component.AppComponent
import com.xxm.mmd.common.di.component.DaggerAppComponent

/**
 * Created by MaDeng on 2018/4/19.
 */
class WxFxKApp : BaseApplication() {
    override fun AppComponent(): AppComponent? {
        return appComponent
    }
    private var appComponent:AppComponent? = null

    companion object {// 伴生对象
    lateinit var instance: WxFxKApp
        private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder().application(this).build()

        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        ARouter.openLog();     // 打印日志
        ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)

        ARouter.init(this)

    }
}