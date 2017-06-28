package com.kevin.jdmall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.kevin.jdmall.ui.activity.ProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Function:
 *
 * @FileName: com.kevin.jdmall.ActivityManager.java   
 * @author: zk
 * @date: 2017-03-15 22:15
 */

public class ActivityManager {
    public static List<Activity> activities = new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    public static void finishAll(){

        for (Activity activity : activities){
            if (!activity.isFinishing() && !activity.isDestroyed()){
                activity.finish();
            }
        }
        activities.clear();
    }

    public static void startActivity(Context context,Class<? extends Activity> clazz,boolean isFinishSelf){
        Intent intent = new Intent(context,clazz);
        context.startActivity(intent);
        if (isFinishSelf && context instanceof Activity)
            ((Activity)context).finish();
    }

    public static void toProductDetail(Context context,int productId){
        Intent intent = new Intent(context,ProductDetailActivity.class);
        intent.putExtra(MyConstants.EXTRA_PRODUCT_DETAIL_ID,productId);
        context.startActivity(intent);
    }
}
