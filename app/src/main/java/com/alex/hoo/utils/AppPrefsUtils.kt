package com.alex.hoo.utils

import android.content.Context
import android.content.SharedPreferences
import com.alex.hoo.common.BaseApplication
import com.alex.hoo.common.BaseConstant

object AppPrefsUtils {

    private var sp: SharedPreferences = BaseApplication.context
        .getSharedPreferences(BaseConstant.TABLE_PREFS, Context.MODE_PRIVATE)
    private var ed: SharedPreferences.Editor

    init {
        ed = sp.edit()
    }

    /**
     * Boolean 数据
     */
    fun putBoolean(key: String, value: Boolean){
        ed.putBoolean(key, value)
        ed.commit()
    }

    /**
     * 默认true
     */
    fun getBoolean(key: String): Boolean{
        return sp.getBoolean(key, true)
    }

    fun putString(key: String, value: String){
        ed.putString(key, value)
        ed.commit()
    }

    fun getString(key: String?): String? {
        return sp.getString(key, "")
    }

    fun putInt(key: String, value: Int){
        ed.putInt(key, value)
        ed.commit()
    }

    fun getInt(key: String): Int{
        return sp.getInt(key, 0)
    }

    fun putLong(key: String, value: Long){
        ed.putLong(key, value)
        ed.commit()
    }

    fun getLong(key: String): Long{
        return sp.getLong(key, 0)
    }

    fun putStringSet(key:String, set: Set<String>){
        val localSet = getStringSet(key)
        localSet?.addAll(set)
        ed.putStringSet(key, localSet)
        ed.commit()
    }

    fun getStringSet(key: String): MutableSet<String>? {
        val set = setOf<String>()
        return sp.getStringSet(key, set)
    }

    fun remove(key: String) {
        ed.remove(key)
        ed.commit()
    }

}