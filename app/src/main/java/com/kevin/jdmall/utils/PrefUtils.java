package com.kevin.jdmall.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.kevin.jdmall.MyApplication;

/**
 * SharePreference封装
 * 
 * @author Kevin
 * @date 2015-10-17
 */
public class PrefUtils {
	public static String mPrefName = "config";
	private static SharedPreferences sp;
	private PrefUtils(){}
	private static SharedPreferences getPreferences() {
		//双重校验加锁
		if (sp == null) {
			synchronized (PrefUtils.class){
				if (sp == null){
					sp = MyApplication.mContext.getSharedPreferences(mPrefName, Context.MODE_PRIVATE);
				}
			}
		}
		return sp;
	}

	public static boolean getBoolean(String key, boolean defValue) {
		return getPreferences().getBoolean(key, defValue);
	}

	public static boolean setBoolean(String key, boolean value) {

		return getPreferences().edit().putBoolean(key, value).commit();
	}

	public static boolean setString(String key, String value) {
		return getPreferences().edit().putString(key, value).commit();
	}

	public static String getString(String key, String defValue) {
		return getPreferences().getString(key, defValue);
	}

	public static boolean setInt(String key, int value) {
		return getPreferences().edit().putInt(key, value).commit();
	}

	public static int getInt(String key, int defValue) {
		return getPreferences().getInt(key, defValue);
	}
}
