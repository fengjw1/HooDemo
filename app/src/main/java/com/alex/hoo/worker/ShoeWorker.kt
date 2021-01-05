package com.alex.hoo.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.alex.hoo.common.BaseConstant
import com.alex.hoo.db.RepositoryProvider
import com.alex.hoo.db.data.Shoe
import com.alex.hoo.utils.AppPrefsUtils
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import java.lang.Exception

class ShoeWorker(context: Context, workerParams: WorkerParameters)
    : CoroutineWorker(context, workerParams){

    private val TAG by lazy{
        ShoeWorker::class.java.simpleName
    }

    override val coroutineContext: CoroutineDispatcher
        get() = Dispatchers.IO

    override suspend fun doWork(): Result = coroutineScope {
        try {
            applicationContext.assets.open("shoes.json").use {
                JsonReader(it.reader()).use {
                    val shoeType = object : TypeToken<List<Shoe>>(){}.type
                    val shoeList : List<Shoe> = Gson().fromJson(it, shoeType)

                    val shoeDao = RepositoryProvider.providerShoeRepository(applicationContext)
                    shoeDao.insertShoes(shoeList)

                    for (i in 0..6){
                        for (shoe in shoeList){
                            shoe.id += shoeList.size
                        }
                        shoeDao.insertShoes(shoeList)
                    }
                    AppPrefsUtils.putBoolean(BaseConstant.IS_FIRST_LAUNCH, false)
                    Result.success()
                }
            }
        }catch (ex: Exception){
            Result.failure()
        }
    }

}

