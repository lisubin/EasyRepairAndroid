package com.repair.lsb.easyrepair.utils;



import java.util.concurrent.TimeUnit;


/**
 * Created by L'S'B 于 2019/1/28
 */

public class RxCountdown {

//    public static Observable<Integer> countdown(int time) {
//        if (time < 0) time = 0;
//        final int countTime = time;
//        return Observable.interval(0, 1, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(new Func1<Long, Integer>() {
//                    @Override
//                    public Integer call(Long increaseTime) {
//                        return countTime - increaseTime.intValue();
//                    }
//                })
//                .take(countTime + 1);
//    }
}
