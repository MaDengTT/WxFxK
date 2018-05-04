package com.xxm.mmd.wxfxk.data

import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe

/**
 * Created by MaDeng on 2018/4/19.
 */
object ServiceData {

    fun getBannerListUrl():Observable<List<String>> {
        return Observable.create({
            emitter ->
            run {
                val data = ArrayList<String>()
                data.add("www.baiduc.com")
                data.add("www.baiduc.com")
                data.add("www.baiduc.com")
                if (data != null) {
                    emitter.onNext(data)
                    emitter.onComplete()
                }else{
                    emitter.onError(Throwable("data is null"))
                }
            }
        })
    }

}