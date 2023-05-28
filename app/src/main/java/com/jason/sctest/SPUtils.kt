package com.jason.sctest

import android.content.Context

object SPUtils {

    /**
     * 保存在手机里面的文件名
     */
    private const val FILE_NAME = "share_date"

    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param context
     * @param key
     * @param object
     */
    fun setParam(context: Context, key: String?, `object`: Any) {
        val type = `object`.javaClass.simpleName
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        val editor = sp.edit()
        when (type) {
            "String" -> {
                editor.putString(key, `object` as String)
            }
            "Integer" -> {
                editor.putInt(key, (`object` as Int))
            }
            "Boolean" -> {
                editor.putBoolean(key, (`object` as Boolean))
            }
            "Float" -> {
                editor.putFloat(key, (`object` as Float))
            }
            "Long" -> {
                editor.putLong(key, (`object` as Long))
            }
        }
        editor.commit()
    }

    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param context
     * @param key
     * @param defaultObject
     * @return
     */
    fun getParam(context: Context, key: String?, defaultObject: Any): Any? {
        val type = defaultObject.javaClass.simpleName
        val sp = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)
        when (type) {
            "String" -> {
                return sp.getString(key, defaultObject as String)
            }
            "Integer" -> {
                return sp.getInt(key, (defaultObject as Int))
            }
            "Boolean" -> {
                return sp.getBoolean(key, (defaultObject as Boolean))
            }
            "Float" -> {
                return sp.getFloat(key, (defaultObject as Float))
            }
            "Long" -> {
                return sp.getLong(key, (defaultObject as Long))
            }
            else -> return null
        }
    }

    /**
     * 清除所有数据
     *
     * @param context
     */
    fun clear(context: Context) {
        val sp = context.getSharedPreferences(
            FILE_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sp.edit()
        editor.clear().commit()
    }

    /**
     * 清除指定数据
     *
     * @param context
     */
    fun clearAll(context: Context) {
        val sp = context.getSharedPreferences(
            FILE_NAME,
            Context.MODE_PRIVATE
        )
        val editor = sp.edit()
        editor.remove("定义的键名")
        editor.commit()
    }
}