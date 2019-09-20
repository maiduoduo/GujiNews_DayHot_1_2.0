package com.cnews.guji.smart.app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.commonsdk.UMConfigure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;

import static com.cnews.guji.smart.base.AppConstant.APPLICATION_BUGLY_APPID;
import static com.cnews.guji.smart.base.AppConstant.APPLICATION_UMENG_APPKEY;

/**
 * APPLICATION
 * @author JSYL-DCL
 */
public class BaseApplication extends Application {
    private static BaseApplication sInstance;
    private Typeface mtypeface;
    /**
     * 上下文
     */
    protected static Context context;
    /**
     * 主线程Handler
     */
    protected static Handler handler;
    /**
     * 主线程Handler
     */
    private static Handler mHandler;
    /**
     * 主线程
     */
    private static Thread mMainThread;

    /**
     * 主线程id
     */
    private static long mMainThreadId;
    /**
     * 循环队列
     */
    private static Looper mMainLooper;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        sInstance = this;
        handler = new Handler();
        mHandler = handler;
        changeFontTypaFace();
        Fresco.initialize(this);
        mMainThread = Thread.currentThread();
        mMainThreadId = android.os.Process.myTid();


        //bugly  init
        getBuglyProcess();
        initUmeng();
    }

    public static Context getAppContext() {
        return sInstance;
    }
    public static BaseApplication getAppContext2() {
        return sInstance;
    }

    public static Resources getAppResources() {
        return sInstance.getResources();
    }

    public static BaseApplication getsInstance() {
        return sInstance;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * 分包
     *
     * @param base
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }

    /**
     * 获取上下文对象
     *
     * @return context
     */
    public static Context getContext() {
        return context;
    }

    /**
     * 获取全局handler
     *
     * @return 全局handler
     */
    public static Handler getHandler() {
        return handler;
    }

    public static void setMainHandler(Handler mHandler) {
        BaseApplication.mHandler = mHandler;
    }

    public static long getMainThreadId() {
        return mMainThreadId;
    }

    public static void setMainThreadId(long mMainThreadId) {
        BaseApplication.mMainThreadId = mMainThreadId;
    }

    public static Thread getMainThread() {
        return mMainThread;
    }

    public static void setMainThread(Thread mMainThread) {
        BaseApplication.mMainThread = mMainThread;
    }

    public static Looper getMainThreadLooper() {
        return mMainLooper;
    }

    public static void setMainThreadLooper(Looper mMainLooper) {
        BaseApplication.mMainLooper = mMainLooper;
    }


    /**
     * 方正宋体
     * @return
     */
    public Typeface getFangZhengSong3() {
        if (mtypeface == null) {
            mtypeface = Typeface.createFromAsset(getAssets(), "fangzhengsongsan.ttf");
        }
        return mtypeface;
    }

    /**
     * 修改字体设置
     */
    public void changeFontTypaFace() {
        Typeface fangZhengSong3 = Typeface.createFromAsset(getAssets(), "fangzhengsongsan.ttf");
        try {
            Field field = Typeface.class.getDeclaredField("SERIF");
            field.setAccessible(true);
            field.set(null, fangZhengSong3);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 加入
     * @param mContext
     * @param key
     * @return
     */
    public static boolean joinQQGroup(Context mContext, String key) {

        Intent intent = new Intent();
        intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
        try {
            mContext.startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 清除缓存
     */
    public static void frescoClearMem(){
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        imagePipeline.clearCaches();
    }

    private void initUmeng() {
        /**
         * 初始化common库
         * 参数1:上下文，必须的参数，不能为空
         * 参数2:友盟 app key，非必须参数，如果Manifest文件中已配置app key，该参数可以传空，则使用Manifest中配置的app key，否则该参数必须传入
         * 参数3:友盟 channel，非必须参数，如果Manifest文件中已配置channel，该参数可以传空，则使用Manifest中配置的channel，否则该参数必须传入，channel命名请详见channel渠道命名规范
         * 参数4:设备类型，必须参数，传参数为UMConfigure.DEVICE_TYPE_PHONE则表示手机；传参数为UMConfigure.DEVICE_TYPE_BOX则表示盒子；默认为手机
         * 参数5:Push推送业务的secret，需要集成Push功能时必须传入Push的secret，否则传空
         * 注意：一定要在主进程进行该项操作，如果您使用到PUSH，还需在推送进程（channel进程）同样进行该项操作
         */
        //如果AndroidManifest.xml清单配置中没有设置appkey和channel，则可以在这里设置
        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, APPLICATION_UMENG_APPKEY);
    }

    private void getBuglyProcess() {
        Context context = getApplicationContext();
        // 获取当前包名
        String packageName = context.getPackageName();
        // 获取当前进程名
        String processName = getProcessName(android.os.Process.myPid());
        // 设置是否为上报进程
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
        strategy.setBuglyLogUpload(processName == null || processName.equals(packageName));
        // 初始化Bugly
        CrashReport.initCrashReport(getApplicationContext(), APPLICATION_BUGLY_APPID, false);
        // 如果通过“AndroidManifest.xml”来配置APP信息，初始化方法如下
        // CrashReport.initCrashReport(context, strategy);
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }



}
