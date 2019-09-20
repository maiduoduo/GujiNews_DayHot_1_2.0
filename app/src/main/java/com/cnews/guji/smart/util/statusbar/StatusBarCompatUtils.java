package com.cnews.guji.smart.util.statusbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cnews.guji.smart.R;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 工具类
 * Created by SmileXie on 2017/6/29.
 */
public class StatusBarCompatUtils {
    public static int screenWidth;
    public static int screenHeight;
    public static int navigationHeight = 0;

    private static DisplayMetrics mMetrics;
    public static final String HOME_CURRENT_TAB_POSITION = "HOME_CURRENT_TAB_POSITION";

    /**
     * 通过反射的方式获取状态栏高度
     *
     * @return
     */
    public static int getStatusBarHeight(Context context) {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取底部导航栏高度
     *
     * @return
     */
    public static int getNavigationBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
        //获取NavigationBar的高度
        navigationHeight = resources.getDimensionPixelSize(resourceId);
        return navigationHeight;
    }

    //获取是否存在NavigationBar
    public static boolean checkDeviceHasNavigationBar(Context context) {
        boolean hasNavigationBar = false;
        Resources rs = context.getResources();
        int id = rs.getIdentifier("config_showNavigationBar", "bool", "android");
        if (id > 0) {
            hasNavigationBar = rs.getBoolean(id);
        }
        try {
            Class systemPropertiesClass = Class.forName("android.os.SystemProperties");
            Method m = systemPropertiesClass.getMethod("get", String.class);
            String navBarOverride = (String) m.invoke(systemPropertiesClass, "qemu.hw.mainkeys");
            if ("1".equals(navBarOverride)) {
                hasNavigationBar = false;
            } else if ("0".equals(navBarOverride)) {
                hasNavigationBar = true;
            }
        } catch (Exception e) {

        }
        return hasNavigationBar;

    }

    /**
     * @param activity
     * @param useThemestatusBarColor   是否要状态栏的颜色，不设置则为透明色
     * @param withoutUseStatusBarColor 是否不需要使用状态栏为暗色调
     */
    public static void setStatusBar(Activity activity, boolean useThemestatusBarColor, boolean withoutUseStatusBarColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = activity.getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            if (useThemestatusBarColor) {
                activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.orange));
            } else {
                activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = activity.getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !withoutUseStatusBarColor) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public static void reMeasure(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        mMetrics = new DisplayMetrics();

        if (Build.VERSION.SDK_INT >= 17) {
            display.getRealMetrics(mMetrics);
        } else {
            display.getMetrics(mMetrics);
        }

        screenWidth = mMetrics.widthPixels;
        screenHeight = mMetrics.heightPixels;
    }

    /**
     * 改变魅族的状态栏字体为黑色，要求FlyMe4以上
     */
    private static void processFlyMe(boolean isLightStatusBar, Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        try {
            Class<?> instance = Class.forName("android.view.WindowManager$LayoutParams");
            int value = instance.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON").getInt(lp);
            Field field = instance.getDeclaredField("meizuFlags");
            field.setAccessible(true);
            int origin = field.getInt(lp);
            if (isLightStatusBar) {
                field.set(lp, origin | value);
            } else {
                field.set(lp, (~value) & origin);
            }
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    /**
     * 改变小米的状态栏字体颜色为黑色, 要求MIUI6以上  lightStatusBar为真时表示黑色字体
     */
    private static void processMIUI(boolean lightStatusBar, Activity activity) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            int darkModeFlag;
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags",int.class,int.class);
            extraFlagField.invoke(activity.getWindow(), lightStatusBar? darkModeFlag : 0, darkModeFlag);
        } catch (Exception ignored) {
            ignored.printStackTrace();
        }
    }

    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";

    /**
     * 判断手机是否是小米
     * @return
     */
    public static boolean isMIUI() {
        try {
            final BuildProperties prop = BuildProperties.newInstance();
            return prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                    || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                    || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
        } catch (final IOException e) {
            return false;
        }
    }

    /**
     * 判断手机是否是魅族
     * @return
     */
    public static boolean isFlyme() {
        try {
            // Invoke Build.hasSmartBar()
            final Method method = Build.class.getMethod("hasSmartBar");
            return method != null;
        } catch (final Exception e) {
            return false;
        }
    }

    /**
     * 设置状态栏文字色值为深色调
     * @param useDart 是否使用深色调
     * @param activity
     */
    public static void setStatusTextColor(boolean useDart, Activity activity) {
        if (isFlyme()) {
            processFlyMe(useDart, activity);
        } else if (isMIUI()) {
            processMIUI(useDart, activity);
        } else {
            if (useDart) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                }
            } else {
                activity.getWindow().getDecorView().setSystemUiVisibility(
                        View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            }
            activity.getWindow().getDecorView().findViewById(android.R.id.content).setPadding(0, 0, 0, StatusBarCompatUtils.navigationHeight);
        }
    }


    /**
     * 背景图延伸到状态栏
     */
    private static final String STATUS_BAR_HEIGHT_RES_NAME = "status_bar_height";
    private static int mStatusBarHeight = 0;
    private static int threshold = 0;
    private static int mActionBarHeight = 0;
    private static int banSize = 0;
    private static GradientDrawable gradientDrawable;
    public static void setTintStatusBg(final Activity context, final LinearLayout topbar, NestedScrollView scrollView,final TextView view2){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            //StatusBar占位
            mStatusBarHeight = getInternalDimensionSize(context.getResources(),STATUS_BAR_HEIGHT_RES_NAME);
            //toolbar高度
            mActionBarHeight = getActionBarHeight(context);
            //图片高度
            banSize = context.getResources().getDimensionPixelSize(R.dimen.ban_size);
            //缓冲距离
            int bufferDistance = context.getResources().getDimensionPixelOffset(R.dimen.buffer_distance);
            //头部遮罩，之所以写在代码里，是因为要实现随滑动距离变色
            gradientDrawable = (GradientDrawable) context.getResources().getDrawable(R.drawable.gradient_bg);
            topbar.setBackgroundDrawable(gradientDrawable);
            threshold = banSize - mStatusBarHeight + bufferDistance;
        }else {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v,
                                       int scrollX,
                                       int scrollY,
                                       int oldScrollX,
                                       int oldScrollY) {
                if (scrollY <= threshold) {
                    float scale0 = Math.min(scrollY * 1.0f / (banSize - mStatusBarHeight
                            - mActionBarHeight), 1.0f);
                    float scale1 = Math.min(scrollY * 1.0f / (banSize - mStatusBarHeight), 1.0f);
//                    int startColor = Color.argb((int) (255 * scale0), (int) (254 * scale0),
//                            (int) (134 * scale0), (int) (158 * scale0));
//                    int endColor = Color.argb((int) (255 * scale1), (int) (254 * scale1),
//                            (int) (134 * scale1), (int) (158 * scale1));
//                    int startColor = Color.argb((int) (0 * scale0), (int) (255 * scale0),
//                            (int) (134 * scale0), (int) (158 * scale0));
//                    int endColor = Color.argb((int) (255 * scale1),(int) (255 * scale1),
//                            (int) (255 * scale1), (int) (255 * scale1));
//                    int[] colors = new int[]{startColor, endColor};
//                    gradientDrawable.setColors(colors);
                    topbar.setBackgroundDrawable(gradientDrawable);
//                    if (toolbar != null){
//                        toolbar.setNavigationIcon(R.drawable.ic_add_cc);
//                    }
                    if (view2 != null) {
                        view2.setVisibility(View.GONE);
                    }
                    setStatusTextColor(false, context);
                }else {
                    //头部遮罩，之所以写在代码里，
//                    gradientDrawable = (GradientDrawable) context是因为要实现随滑动距离变色.getResources().getDrawable(R.drawable.gradient_bg_scroll);
//                    topbar.setBackgroundDrawable(gradientDrawable);
                    topbar.setBackgroundResource(R.color.title_bg_red);
                    view2.setVisibility(View.VISIBLE);
                    view2.setBackgroundResource(R.color.title_bg_red);
//                    setStatusTextColor(false, context);
                    StatusBarCompatUtils.setDarkMode(context);
                }
             }
        });
    }


    /**
     * 背景图延伸到状态栏
     */
    private static final String STATUS_BAR_HEIGHT_RES_NAME1 = "status_bar_height";
    private static int mStatusBarHeight1 = 0;
    private static int threshold1 = 0;
    private static int mActionBarHeight1 = 0;
    private static int banSize1 = 0;
    private static GradientDrawable gradientDrawable1;
    public static void setTintStatusBg(final Activity context, final LinearLayout topbar, NestedScrollView scrollView){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            //StatusBar占位
            mStatusBarHeight1 = getInternalDimensionSize(context.getResources(),STATUS_BAR_HEIGHT_RES_NAME1);
            //toolbar高度
            mActionBarHeight1 = getActionBarHeight(context);
            //图片高度
            banSize1 = context.getResources().getDimensionPixelSize(R.dimen.ban_size);
            //缓冲距离
            int bufferDistance = context.getResources().getDimensionPixelOffset(R.dimen.buffer_distance);
            //头部遮罩，之所以写在代码里，是因为要实现随滑动距离变色
            gradientDrawable1 = (GradientDrawable) context.getResources().getDrawable(R.drawable.gradient_bg);
            topbar.setBackgroundDrawable(gradientDrawable1);
            threshold1 = banSize1 - mStatusBarHeight1 + bufferDistance+mStatusBarHeight1;
        }else {
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
            context.getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v,
                                       int scrollX,
                                       int scrollY,
                                       int oldScrollX,
                                       int oldScrollY) {
                if (scrollY <= threshold1) {
                    float scale0 = Math.min(scrollY * 1.0f / (banSize1 - mStatusBarHeight1
                            - mActionBarHeight1), 1.0f);
                    float scale1 = Math.min(scrollY * 1.0f / (banSize1 - mStatusBarHeight1), 1.0f);
//                    int startColor = Color.argb((int) (255 * scale0), (int) (254 * scale0),
//                            (int) (134 * scale0), (int) (158 * scale0));
//                    int endColor = Color.argb((int) (255 * scale1), (int) (254 * scale1),
//                            (int) (134 * scale1), (int) (158 * scale1));
//                    int startColor = Color.argb((int) (0 * scale0), (int) (255 * scale0),
//                            (int) (134 * scale0), (int) (158 * scale0));
//                    int endColor = Color.argb((int) (255 * scale1),(int) (255 * scale1),
//                            (int) (255 * scale1), (int) (255 * scale1));
//                    int[] colors = new int[]{startColor, endColor};
//                    gradientDrawable.setColors(colors);
                    topbar.setBackgroundDrawable(gradientDrawable1);
//                    if (toolbar != null){
//                        toolbar.setNavigationIcon(R.drawable.ic_add_cc);
//                    }
                    setStatusTextColor(false, context);
                }else {
                    //头部遮罩，之所以写在代码里，
//                    gradientDrawable = (GradientDrawable) context是因为要实现随滑动距离变色.getResources().getDrawable(R.drawable.gradient_bg_scroll);
//                    topbar.setBackgroundDrawable(gradientDrawable);
                    topbar.setBackgroundResource(R.color.title_bg_red);
//                    topbar.setBackgroundResource(R.color.material_red_300);
                    setStatusTextColor(true, context);
                }
            }
        });
    }

    @TargetApi(14)
    private static int getActionBarHeight(Context context) {
        int result = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            TypedValue tv = new TypedValue();
            context.getTheme().resolveAttribute(android.R.attr.actionBarSize, tv, true);
            result = TypedValue.complexToDimensionPixelSize(tv.data,
                    context.getResources().getDisplayMetrics());
        }
        return result;
    }

    private static int getInternalDimensionSize(Resources res, String key) {
        int result = 0;
        int resourceId = res.getIdentifier(key, "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }


    /**
     * 深色字体
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void setLightMode(Activity activity) {
        setMIUIStatusBarDarkIcon(activity, true);
        setMeizuStatusBarDarkIcon(activity, true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    /**
     * 白色字体
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void setDarkMode(Activity activity) {
        setMIUIStatusBarDarkIcon(activity, false);
        setMeizuStatusBarDarkIcon(activity, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }

    /**
     * 修改 MIUI V6  以上状态栏颜色
     */
    private static void setMIUIStatusBarDarkIcon(@NonNull Activity activity, boolean darkIcon) {
        Class<? extends Window> clazz = activity.getWindow().getClass();
        try {
            Class<?> layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
            int darkModeFlag = field.getInt(layoutParams);
            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
            extraFlagField.invoke(activity.getWindow(), darkIcon ? darkModeFlag : 0, darkModeFlag);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    /**
     * 修改魅族状态栏字体颜色 Flyme 4.0
     */
    private static void setMeizuStatusBarDarkIcon(@NonNull Activity activity, boolean darkIcon) {
        try {
            WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            Field darkFlag = WindowManager.LayoutParams.class.getDeclaredField("MEIZU_FLAG_DARK_STATUS_BAR_ICON");
            Field meizuFlags = WindowManager.LayoutParams.class.getDeclaredField("meizuFlags");
            darkFlag.setAccessible(true);
            meizuFlags.setAccessible(true);
            int bit = darkFlag.getInt(null);
            int value = meizuFlags.getInt(lp);
            if (darkIcon) {
                value |= bit;
            } else {
                value &= ~bit;
            }
            meizuFlags.setInt(lp, value);
            activity.getWindow().setAttributes(lp);
        } catch (Exception e) {
            //e.printStackTrace();
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取状态栏和标题栏的总高度
     * @param context
     * @return
     */
    public  static int getStatusBarAcionBarHeight(Context context){
        int statusBarAcionBarHeight = 0;
        int actionBarHeight = getActionBarHeight(context);
        int statusBarHeight = getStatusBarHeight(context);
        statusBarAcionBarHeight = actionBarHeight + statusBarHeight;
        return statusBarAcionBarHeight;
    }

}
