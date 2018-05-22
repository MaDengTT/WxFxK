package com.xxm.mmd.wxfxk.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.xxm.mmd.router.RouterConstants
import com.xxm.mmd.router.RouterUtils
import com.xxm.mmd.wxfxk.R
import com.xxm.mmd.wxfxk.R.id.tv_1
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_1.setOnClickListener { _ ->RouterUtils.getInstance().build(RouterConstants.PATH_TexMath_MAIN).navigation() }

        Observable.create(ObservableOnSubscribe<String> {
            emitter ->
            run {
                if (true) {
                    emitter.onNext("a")
                } else {
                    emitter.onError(Throwable("aa"))
                }
            }
        })
    }
}
