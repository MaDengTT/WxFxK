package com.xxm.mmd.common_circle;

import android.annotation.SuppressLint;

import com.xxm.mmd.common_circle.enums.TranslationState;
import com.xxm.mmd.common_circle.interfaces.OnTimerResultListener;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

/**
 * @author KCrason
 * @date 2018/5/2
 */
public class TimerUtils {

    @SuppressLint("CheckResult")
    public static void timerTranslation(final OnTimerResultListener onTimerResultListener) {
        Single.timer(1000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        if (onTimerResultListener != null) {
                            onTimerResultListener.onTimerResult();
                        }
                    }
                });
    }
}
