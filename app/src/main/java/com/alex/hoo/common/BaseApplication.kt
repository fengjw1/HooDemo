package com.alex.hoo.common

import android.app.Application
import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import java.io.InputStream

open class BaseApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        context = this

        //解决glide加载https证书问题
//        try {
//            Glide.get(this).registry.replace(
//                    GlideUrl::class.java, InputStream::class.java,
//                    Factory(getSSLOkHttpClient()))
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }

    }

    companion object{
        lateinit var context: Context
    }

}