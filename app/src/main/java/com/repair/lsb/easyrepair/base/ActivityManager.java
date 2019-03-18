package com.repair.lsb.easyrepair.base;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

public class ActivityManager {

    private static ActivityManager activityManager;
    private List<Activity> mList = new LinkedList<>();

    private ActivityManager() {
    }

    public synchronized static ActivityManager getInstance() {
        if (activityManager == null) {
            activityManager = new ActivityManager();
        }
        return activityManager;
    }

    public void addActivity(Activity activity) {
        if (mList.indexOf(activity) >= 0) {
            mList.get(mList.indexOf(activity)).finish() ;
        }
        mList.add(activity);
    }

    public void exitApplication() {
        int count = mList.size();
        for (int index = 0; index < count; index++) {
            mList.get(index).finish();
        }
    }
    /**
     * 退出指定Activity
     * @param clazz
     */
    public void finishActivityForClass(Class clazz){
        if(clazz != null){
            for (int i = 0; i < mList.size(); i++) {
                if(mList.get(i).getClass().equals(clazz)){
                    mList.get(i).finish() ;
                }
            }
        }else{
            throw new RuntimeException("clazz cannot be null ！") ;
        }
    }
    /**
     * 判断某个activity是否在栈里面
     * @param clazz
     */
    public boolean activityIsExist(Class clazz){
        if(clazz != null){
            for (int i = 0; i < mList.size(); i++) {
                if(mList.get(i).getClass().equals(clazz)){
                    return true ;
                }
            }
        }else{
            throw new RuntimeException("clazz cannot be null ！") ;
        }
        return false ;
    }
    /**
     * 保留主activity 关闭其他activity
     */
    public void remainMainActivityFinishOther(Class clazz){
        for (int i = 0; i < mList.size(); i++) {
            if(!mList.get(i).getClass().equals(clazz)){
                mList.get(i).finish() ;
            }
        }
    }
    public void finishForActivity(Activity activity) {
        if (mList.indexOf(activity) >= 0) {
            activity.finish();
        }
    }

}
