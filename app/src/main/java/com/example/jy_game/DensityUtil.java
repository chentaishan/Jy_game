package com.example.jy_game;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class DensityUtil {

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    /**
     * 获取状态栏的高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, sbar = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            sbar = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return sbar;
    }

    /**
     * 获取屏幕的宽度
     *
     * @return
     */
    public static int getScreenWidth(Activity context) {
        WindowManager windowManager = context.getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        return defaultDisplay.getWidth();
    }

    /**
     * 获取屏幕的高度
     *
     * @return
     */
    public static int getScreenHeight(Activity context) {
        WindowManager windowManager = context.getWindowManager();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        return defaultDisplay.getHeight();
    }




    public static View findViewByXY(View view, int x, int y) {
        View targetView = null;
        if (view instanceof ViewGroup) {
            // 父容器,遍历子控件
            ViewGroup v = (ViewGroup) view;
            for (int i = 0; i < v.getChildCount(); i++) {
                targetView = findViewByXY(v.getChildAt(i), x, y);
                if (targetView != null) {
                    break;
                }
            }
        } else if(view instanceof ImageView){
            targetView = getTouchTarget(view, x, y);
        }
        return targetView;

    }

    private static View getTouchTarget(View view, int x, int y) {
        View targetView = null;

            if (isTouchPointInView(view, x, y)) {
                targetView = view;

            }

        return targetView;
    }

    private static final String TAG = "DensityUtil";

    static int length = 30;
    /**
     * 检查拖动的view 和当前被覆盖的view 是否大致重叠
     * @param view
     * @param x
     * @param y
     * @return
     */
    private static boolean isTouchPointInView(View view, int x, int y) {


        Log.d(TAG, "isTouchPointInView: x="+(view instanceof ImageView));

        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int left = location[0];
        int top = location[1];


        Log.d(TAG, "isTouchPointInView:  x="+x+"  y="+y+ "  left="+left+" top="+top);
        if (Math.abs(y- top)<= length && Math.abs(x -left)  <= length  ) {
            return true;
        }
        return false;
    }
}
