package com.quzy.coding.base;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.NonNull;

import com.apkfuns.logutils.LogUtils;
import com.quzy.coding.R;

import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ConcurrentHashMap;

/**
 * author : quzongyang
 * e-mail : quzongyang@xiaohe.com
 * time   : 2020/11/24
 * desc   : Activity管理类
 * version: 1.0
 */

public class ActivityManager {
    public static final String TAG = ActivityManager.class.getSimpleName();

    /**
     * activity  用一个栈来管理activity的生命周期 */
    private volatile Stack<Activity> activityStack;

    public static ActivityManager getInstance(){
        return getInstance(BASE);
    }

    private static String BASE = "base";

    private static volatile Map<String, ActivityManager> activityManagerMap = new ConcurrentHashMap<>();

    private String name;

    public static ActivityManager getInstance(String name){
        if(!activityManagerMap.containsKey(name)){
            synchronized (ActivityManager.class){
                if(!activityManagerMap.containsKey(name)){
                    ActivityManager activityManager = new ActivityManager();
                    activityManager.setName(name);
                    activityManagerMap.put(name, activityManager);
                }
            }
        }
        return activityManagerMap.get(name);
    }



    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActivityManager(){
        activityStack = new Stack<Activity>();
    }
    static WeakReference<Activity> currentActivity;

    /**
     * 设置当前活动的activity
     * @param ac
     */
    public static synchronized  void setCurrentActivity(Activity ac) {
        if (ac == null) {
            currentActivity = null;
        } else {
            currentActivity = new WeakReference<Activity>(ac);
        }
    }

    /**
     * 清除当前活动的activity
     * @param ac
     */
    public static synchronized  void clearCurrentActivity(Activity ac) {
        Activity cur = currentActivity == null ? null : currentActivity.get();
        if (cur != null && cur == ac) {
            currentActivity = null;
        }
    }

    /**
     * 得到当前活动的activity
     * @return
     */
    public synchronized static Activity getCurrentActivity() {
        return currentActivity == null ? null : currentActivity.get();
    }

    /**
     * 将Activity入栈
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void pushActivity(@NonNull Activity activity)
    {
        //if(!activity.isChangingConfigurations()){
        activityStack.add(activity);

        LogUtils.tag(TAG).d(activity.getString(R.string.libActivityFrom)+"("+SSystem.getClassName(activity.getClass())+":" + 1 + ")" +activity.getString(R.string.libActivityInStack));
        //}
    }

    /**
     * 退出Activity
     * <功能详细描述>
     * @param activity
     * @see [类、类#方法、类#成员]
     */
    public void popActivity(@NonNull Activity activity)
    {
        if (activity != null)
        {
            activityStack.remove(activity);
            LogUtils.tag(TAG).d(activity.getString(R.string.libActivityFrom)+"("+SSystem.getClassName(activity.getClass())+":" + 1 + ")"  +activity.getString(R.string.libActivityOutStack));
        }
    }

    /**
     * 栈中有多少activity
     * @return
     */
    public int activityInStack() {
        return activityStack.size();
    }

    /**
     * 退出栈中所有Activity
     */
    public void finishAllActivity()
    {
        while (true){
            if(activityInStack()>0){
                Activity activity = activityStack.pop();
                activity.finish();
                activity = null;
            }else {
                if(getName().equals(BASE))
                    LogUtils.tag(TAG).d(BaseApplication.getContext().getString(R.string.libQuit));
                break;
            }
        }
    }


    /**
     * 清activity并新建nowacitivyt
     * @param now
     */
    public void finishOtherActivity(Class<? extends Activity> now){
        while (true){
            if(activityInStack()>0){
                Activity activity = activityStack.pop();
                if(now!=null&&activity!=null){
                    if(activityInStack()==0){
                        Intent it = new Intent(activity,now);
                        activity.startActivity(it);
                        activity.finish();
                        activity = null;
                        break;
                    }
                }
                activity.finish();
                activity = null;
            }else{
                break;
            }
        }
    }


}