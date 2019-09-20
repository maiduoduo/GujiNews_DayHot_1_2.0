package com.cnews.guji.smart.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.cnews.guji.smart.base.AppConstant;
import com.google.gson.Gson;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static android.content.Context.MODE_PRIVATE;


/**
 * SharePrefUtil
 * SharePreferences操作工具类
 **/
public class SharePrefUtil {
    private final static String SP_NAME = AppConstant.SP_NAME;
    private static SharedPreferences sp;

    /**
     * 记录服务器地址
     *
     * @param serverUrl 服务器地址
     */
    public void saveIpAddress(Context context, String serverUrl) {
        if (sp == null) {

            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("serverUrl", serverUrl);
            editor.commit();
        }


    }

    /**
     * 保存布尔值
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 保存字符串
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveString(Context context, String key, String value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();

    }

    public static void clear(Context context) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }

    /**
     * 保存long型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveLong(Context context, String key, long value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        sp.edit().putLong(key, value).commit();
    }

    /**
     * 保存int型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveInt(Context context, String key, int value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        sp.edit().putInt(key, value).commit();
    }

    /**
     * 保存float型
     *
     * @param context
     * @param key
     * @param value
     */
    public static void saveFloat(Context context, String key, float value) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        sp.edit().putFloat(key, value).commit();
    }

    /**
     * 获取字符值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static String getString(Context context, String key, String defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        return sp.getString(key, defValue);
    }

    /**
     * 获取int值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static int getInt(Context context, String key, int defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        return sp.getInt(key, defValue);
    }

    /**
     * 获取long值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static long getLong(Context context, String key, long defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        return sp.getLong(key, defValue);
    }

    /**
     * 获取float值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static float getFloat(Context context, String key, float defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        return sp.getFloat(key, defValue);
    }

    /**
     * 获取布尔值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static boolean getBoolean(Context context, String key,
                                     boolean defValue) {
        if (sp == null)
            sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
        return sp.getBoolean(key, defValue);
    }

    /**
     * 将对象进行base64编码后保存到SharePref中
     *
     * @param context
     * @param key
     * @param object
     */
//	public static void saveObj(Context context, String key, Object object) {
//		if (sp == null)
//			sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
//
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		ObjectOutputStream oos = null;
//		try {
//			oos = new ObjectOutputStream(baos);
//			oos.writeObject(object);
//			// 将对象的转为base64码
//			String objBase64 = new String(Base64.encodeBase64(baos
//					.toByteArray()));
//
//			sp.edit()
//					.putString(key,objBase64).commit();
//			oos.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

    /**
     * 将SharePref中经过base64编码的对象读取出来
     *
     * @param context
     * @param key
     * @return
     */
//	public static Object getObj(Context context, String key) {
//		if (sp == null)
//			sp = context.getSharedPreferences(SP_NAME, context.MODE_PRIVATE);
//		String objBase64 = sp.getString(key, null);
//		if (TextUtils.isEmpty(objBase64))
//			return null;
//
//		// 对Base64格式的字符串进行解码
//		byte[] base64Bytes = Base64.decodeBase64(objBase64.getBytes());
//		ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
//
//		ObjectInputStream ois;
//		Object obj = null;
//		try {
//			ois = new ObjectInputStream(bais);
//			obj = ois.readObject();
//			ois.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return obj;
//	}

    /**
     * 移除某个key值已经对应的值
     *
     * @param context
     * @param key
     */
    public static void remove(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(SP_NAME,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }

            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {
            }
            editor.commit();
        }
    }


    /**
     * 获取对象
     *      spr:json
     * @param key
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getObject(String key, Class<T> cls) {
        String strJson = SharePrefUtil.getString(AppCommonUtils.getContext(), key, null);
        T obj = null;
        if (null == strJson) {
            return null;
        }
        try {
            Gson gson = new Gson();
            obj = gson.fromJson(strJson, cls);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }
}
